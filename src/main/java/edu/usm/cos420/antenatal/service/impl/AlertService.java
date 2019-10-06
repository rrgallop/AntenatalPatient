package edu.usm.cos420.antenatal.service.impl;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import domain.GenericPatient;
import edu.usm.cos420.antenatal.dao.domain.AntenatalPatientDao;
import edu.usm.cos420.antenatal.domain.AntenatalPatient;

/*
 * AlertService.java
 * Handles sending out alerts to patients via email
 */
public class AlertService {
	private static final String encryptionKey = "ABCDEFGHIJKLMNOP";
	private static final String characterEncoding = "UTF-8";
	private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
	private static final String aesEncryptionAlgorithem = "AES";
	private static Properties props = new Properties();
	private static Properties emailProps = new Properties();
	AntenatalPatientDao pDao = new AntenatalPatientDao();



	/*
	 * @param Long the id of the patient
	 * 
	 * @return if the patient was found it returns the name otherwise null
	 */
	public String displayPatientAlerts(Long i) {
		AntenatalPatient p = pDao.find(i);
		if (p != null) {
			String patietnInfo = p.getFirstName() + " " + p.getLastName();
			return patietnInfo;
		} else {
			return null;
		}
	}
	
	/**
	 * Load the properties from the config.properties file
	 */
	public static void loadProps() {
		InputStream in = AlertService.class.getClassLoader().getResourceAsStream("config.properties");
		InputStream in2 = AlertService.class.getClassLoader().getResourceAsStream("email.properties");
		try {
			props.load(in);
			emailProps.load(in2);
		} catch (IOException e) {
			System.err.println("Error reading the configuration file.");
		}
	
	}

	/*
	 * 
	 * @param emailAddress the email address to send a message to
	 * 
	 * @return if the email was sent successfully or not
	 */
	public boolean sendEmail(String emailAddress, String patientName) {
		loadProps();
		String sendEmailTo = emailAddress;
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		Session session = Session.getDefaultInstance(emailProps, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, decrypt(password));
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(username);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendEmailTo));
			message.setSubject(patientName + " YOU NEED A CHECKUP!");
			message.setText("Dear " + patientName + ",\n\n"
					+ "You are getting this message because you need a checkup. Please visit the local health center in your area.");
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * Encrypt a string
	 * 
	 * @return the encrypted String
	 */
	public String encrypt(String plainText) {
		String encryptedText = "";
		try {
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			byte[] key = encryptionKey.getBytes(characterEncoding);
			SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
			IvParameterSpec ivparameterspec = new IvParameterSpec(key);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
			byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
			Base64.Encoder encoder = Base64.getEncoder();
			encryptedText = encoder.encodeToString(cipherText);

		} catch (Exception e) {
			System.err.println("Encrypt Exception : " + e.getMessage());
		}
		return encryptedText;
	}

	/*
	 * Decrypt a string
	 * 
	 * @return the plaintext string
	 */
	public static String decrypt(String encryptedText) {
		String decryptedText = "";
		try {
			Cipher cipher = Cipher.getInstance(cipherTransformation);
			byte[] key = encryptionKey.getBytes(characterEncoding);
			SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
			IvParameterSpec ivparameterspec = new IvParameterSpec(key);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
			Base64.Decoder decoder = Base64.getDecoder();
			byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF8"));
			decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

		} catch (Exception E) {
			System.err.println("decrypt Exception : " + E.getMessage());
		}
		return decryptedText;
	}

	public ArrayList<String> getPatientsNames() {
		ArrayList<String> names = new ArrayList<String>();
		for(GenericPatient p : pDao.list()) names.add(p.getFirstName() + " " + p.getLastName());

		return names;
	}

}
