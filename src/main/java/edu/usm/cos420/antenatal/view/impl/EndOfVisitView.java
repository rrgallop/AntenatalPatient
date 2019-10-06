package edu.usm.cos420.antenatal.view.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import edu.usm.cos420.antenatal.domain.AntenatalPatient;

public class EndOfVisitView extends JFrame {
	/**
	 * Main view for the program
	 */
	private static int WIDTH = 600;
	private static int HEIGHT = 700;
	private static int TEXTFIELD_WIDTH = 300;
	private static int TEXTFIELD_HEIGHT = 150;
	private static AntenatalPatient thisPatient;
	private static final long serialVersionUID = 2840735252655654044L;
	private static boolean hivStatus, syphilisStatus, anemiaStatus, peStatus;

	public EndOfVisitView(TreeMap<String, Boolean> ailments) {
		this.hivStatus = ailments.get("hiv");
		this.anemiaStatus = ailments.get("anemia");
		this.peStatus = ailments.get("pe");
		this.syphilisStatus = ailments.get("syphilis");
		createEndOfVisitView();
	}

	/**
	 * Creates the main view
	 */
	public static void createEndOfVisitView() {
		/*
		 * TODO put this into an init method
		 */
		JDialog dialog = new JDialog();
		dialog.setTitle("End of visit recommendations");
		JPanel endPanel = new JPanel();
		endPanel.setLayout(new BoxLayout(endPanel, BoxLayout.PAGE_AXIS));
		dialog.setModal(true);
		JTextArea ta = new JTextArea(TEXTFIELD_HEIGHT, TEXTFIELD_WIDTH);
		JScrollPane sp = new JScrollPane(ta);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		ta.setLineWrap(true);
		ta.setEditable(false);
		JButton dismissButton = new JButton("Dismiss");
		endPanel.add(sp);
		endPanel.add(dismissButton);

		/*
		 * Status results text strings
		 * 
		 * TODO: move positive and negative results into their own individual result
		 * strings and add them to the result screen as necessary.
		 */

		String preEclampsia = "Steps to take for pre-eclampsia: \n" + " \n"
				+ " Severe pre-eclampsia (diastolic blood pressure >100 mmHg) \n" + " - Give magnesium sulphate \n"
				+ " - Give appropriate anti-hypertensives \n" + " - Revise the birth plan \n"
				+ " - Refer urgently to the hospital\n" + " \n"
				+ " For less severe cases of pre-eclampsia (diastolic 90-110 mmHg) \n"
				+ " - Advise to reduce workload and to rest\n" + " - Advise on danger signs\n"
				+ " - Reasses at the next antenatal visit or in 1 week if >8 months pregnant\n"
				+ " - If hypertension persists after 1 week or at next visit, refer to\n"
				+ "    hospital or discuss case with doctor or midwife, if available.\n" + " \n"
				+ " For less severe cases of hypertension give aspirin and provide\n"
				+ "  calcium if low dietary intake area." + " \n" + " \n";

		String anemia = "Steps to take for cases of anaemia:\n" + " \n" + " Severe anaemia\n"
				+ " - Refer urgently to hospital\n"
				+ " - Revise birthplan so as to deliver in a facility with blood transfusion services\n"
				+ " - Give double dose of iron (1 tablet twice daily) for 3 months\n"
				+ " - Counsel on compliance with treatment\n" + " - Give appropriate oral antimalarial\n"
				+ " - Follow up in 2 weeks to check clinical progress, test results and\n"
				+ "    compliance with treatment\n" + " \n" + " Moderate anaemia\n"
				+ " - Give double dose of iron (1 tablet twice daily) for 3 months\n"
				+ " - Counsel on compliance with treatment\n"
				+ " - Give appropriate oral antimalarial if not given in the past month\n"
				+ " - Reasses at next antenatal visit (4-6 weeks).  If anaemia persists, refer\n" + "    to hospital.\n"
				+ " \n" + " No clinical anaemia\n" + " - Give iron 1 tablet once daily for 3 months\n"
				+ " - Counsel on compliance with treatment" + " \n" + " \n";

		String syphilis = "Steps to take for positive RPR test results:\n" + " \n"
				+ " - Give benzathine benzylpeniciliin IM.  If allergy, give erythromycin\n"
				+ " - Plan to treat the newborn\n" + " - Encourage woman to bring her sexual partner for treatment\n"
				+ " - Counsel on safer sex including use of condoms to prevent new infection\n" + " \n"
				+ " If RPR test negative\n" + " - Counsel on safer sex including use of condoms to prevent infection"
				+ " \n" + " \n";

		String hiv = "Steps to take for positive HIV test results:\n" + " \n" + " - Give her appropriate ART\n"
				+ " - Support adherence to ART\n" + " - Counsel on impoications of positive test\n"
				+ " - Refer her to HIV services for further assesment and initiation\n" + "    for lifelong ART\n"
				+ " - Provide additional care for HIV-infected woman\n"
				+ " - Provide support to the HIV-infected woman\n"
				+ " - Counsel on benefits of disclosure (involving) and testing her partner\n"
				+ " - Counsel on safer sex including use of condoms\n" + " - Counsel on family planning\n"
				+ " - Counsel on infant feeding options\n"
				+ " - Ask her to return to the next scheduled antenatal care visit\n" + " \n"
				+ " Negative HIV Test results:\n" + " - Counsel on implications of a negative test\n"
				+ " - Counsel on the importance of staying negative by practicing safer sex,\n"
				+ "    including use of condoms\n" + " - Counsel on benefits of involving and testing the partner\n"
				+ " - Repeat HIV testing in the 3rd trimester\n" + " \n" + " Unknown HIV status:\n"
				+ " -  Asses for signs suggesting severe or advanced HIV infection\n"
				+ " - Counsel on safer sex including use of condoms\n"
				+ " - Counsel on benefits of involving and testing the partner" + " \n" + " \n";

		if (hivStatus == true) {
			ta.append(hiv);
		}
		if (syphilisStatus == true) {
			ta.append(syphilis);
		}
		if (anemiaStatus == true) {
			ta.append(anemia);
		}
		if (peStatus == true) {
			ta.append(preEclampsia);
		}
		dismissButton.addActionListener((f) -> {
			dialog.dispose();
		});
		dismissButton.setAlignmentX(CENTER_ALIGNMENT);
		dialog.setSize(WIDTH, HEIGHT);
		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		ta.setCaretPosition(0);
		dialog.add(endPanel);
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);
	}

	/*
	 * TODO: remove if needed, creates an HTML document that is viewable in the
	 * dialog box, code can be kept in case we shift to using HTML.
	 * 
	 * 
	 * JEditorPane editorPane = new JEditorPane(); editorPane.setEditable(false);
	 * java.net.URL exampleURL = VisitView.class.getResource("/example.html"); if
	 * (exampleURL != null) { try { editorPane.setPage(exampleURL); } catch
	 * (IOException g) { System.err.println("Attempted to read a bad URL: " +
	 * exampleURL); } } else {
	 * System.err.println("Couldn't find file: TextSamplerDemoHelp.html"); }
	 */

}
