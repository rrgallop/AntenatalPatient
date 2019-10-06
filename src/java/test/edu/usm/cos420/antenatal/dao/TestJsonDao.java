package edu.usm.cos420.antenatal.dao;

import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.usm.cos420.antenatal.domain.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class TestJsonDao 
{
	JsonDao<Long, AntenatalPatient> daoAP; 

	/** 
	 * Create a clean DAO before each test
	 */
	@Before
	public void setupData() {
		Type t1 = new TypeToken<Map<Long, AntenatalPatient>>(){}.getType(); 

		daoAP = new JsonDao<Long, AntenatalPatient>("_testAP.json",t1);
	}


	@Test
	public void testSaveandFind1() {
		Long id; 
		TreeMap<String, Boolean> ailments = new TreeMap<String, Boolean>();
		ailments.put("hiv", true);
		ailments.put("anemia", false);
		ailments.put("syphilis", false);
		ailments.put("pe", true);


		AntenatalPatient testPatient = new AntenatalPatient("Billy", "Johnson", "123 Street", "Manhattan", "NYC", "Male",
															"34", LocalDate.of(1999, Month.DECEMBER, 31), 178, 111,
															"O+", "no", LocalDate.of(1899, Month.JANUARY, 1), ailments, 100000L);


				id = testPatient.getPatientId();
				daoAP.add(id, testPatient);

				AntenatalPatient retrievedPatient = (AntenatalPatient) daoAP.find(id);

				assertNotNull("Dao returns a null item.", retrievedPatient);
				assertEquals("Stored patient and original patient are not equal ", retrievedPatient.getPatientId(), testPatient.getPatientId());
				assertEquals("Stored patient and original patient are not equal ", retrievedPatient.getAddress(), testPatient.getAddress());
				assertEquals("Stored patient and original patient are not equal ", retrievedPatient.getAge(), testPatient.getAge());
				assertEquals("Stored patient and original patient are not equal ", retrievedPatient.getAilments(), testPatient.getAilments());
				assertEquals("Stored patient and original patient are not equal ", retrievedPatient.getBloodType(), testPatient.getBloodType());
				assertEquals("Stored patient and original patient are not equal ", retrievedPatient.getDateOfBirth(), testPatient.getDateOfBirth());
				assertEquals("Stored patient and original patient are not equal ", retrievedPatient.getFirstName(), testPatient.getFirstName());
				assertEquals("Stored patient and original patient are not equal ", retrievedPatient.getGender(), testPatient.getGender());
				assertEquals("Stored patient and original patient are not equal ", retrievedPatient.getLastCycle(), testPatient.getLastCycle());
				assertEquals("Stored patient and original patient are not equal ", retrievedPatient.getLastName(), testPatient.getLastName());
				assertEquals("Stored patient and original patient are not equal ", retrievedPatient.getStatusPregnant(), testPatient.getStatusPregnant());
				assertEquals("Stored patient and original patient are not equal ", retrievedPatient.getVillage(), testPatient.getVillage());
				assertEquals("Stored patient and original patient are not equal ", retrievedPatient.getVisitHistory(), testPatient.getVisitHistory());
				assertEquals(retrievedPatient.getWeight(),testPatient.getWeight(),0.001);
				assertEquals(retrievedPatient.getHeight(),testPatient.getHeight(),0.001);

	}
	
	@Test
	public void testSaveAndRemove1() {
		Long id1; 
		TreeMap<String, Boolean> ailments1 = new TreeMap<String, Boolean>();
		ailments1.put("hiv", true);
		ailments1.put("anemia", true);
		ailments1.put("syphilis", true);
		ailments1.put("pe", true);


		AntenatalPatient testPatient1 = new AntenatalPatient("Billy", "Basket", "567 Some Street", "Portland","Maine", "Male",
				"34", LocalDate.of(1999, Month.DECEMBER, 31), 178.0, 111.0,
				"O+", "no", LocalDate.of(1899, Month.JANUARY, 1), ailments1,2L);
		Long id2; 
		TreeMap<String, Boolean> ailments2 = new TreeMap<String, Boolean>();
		ailments2.put("hiv", true);
		ailments2.put("anemia", false);
		ailments2.put("syphilis", false);
		ailments2.put("pe", true);

		AntenatalPatient testPatient2 = new AntenatalPatient("Charlie", "Cook", "444 Some Street", "Bronx","New York", "Male",
				"34", LocalDate.of(1999, Month.DECEMBER, 31), 178.0, 111.0,
				"O+", "no", LocalDate.of(1899, Month.JANUARY, 1), ailments1,3L);
		Long id3; 
		TreeMap<String, Boolean> ailments3 = new TreeMap<String, Boolean>();
		ailments3.put("hiv", false);
		ailments3.put("anemia", false);
		ailments3.put("syphilis", false);
		ailments3.put("pe", false);


		AntenatalPatient testPatient3 = new AntenatalPatient("Zoey", "Zoey", "888 Some Street", "Bronx","New York", "Female",
				"34", LocalDate.of(1999, Month.DECEMBER, 31), 178.0, 111.0,
				"A-", "yes", LocalDate.of(1899, Month.JANUARY, 1), ailments3,3L);

		id1 = testPatient1.getPatientId();
		daoAP.add(id1, testPatient1);
		
		id2 = testPatient2.getPatientId();
		daoAP.add(id2, testPatient2);
		
		id3 = testPatient3.getPatientId();
		daoAP.add(id3, testPatient3);

		// Remove a patient
		daoAP.remove(testPatient2.getPatientId());
		AntenatalPatient retrievedPatient = (AntenatalPatient) daoAP.find(id2);

		assertNull("Dao returns a null item.", retrievedPatient);

	}

	/** 
	 * Need to delete the file for next test
	 */
	@After
	public void tearDown()
	{

		/*
		 *   This will be run after every test
		 */
	    System.gc();
		Path path1 = FileSystems.getDefault().getPath(".", "_testAP.json");
		try {
			Files.delete(path1);
		} catch (NoSuchFileException x) {
		} catch (DirectoryNotEmptyException x) {
		} catch (IOException x) {
		}
	}
}
