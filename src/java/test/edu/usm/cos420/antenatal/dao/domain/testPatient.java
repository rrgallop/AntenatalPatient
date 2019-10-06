package edu.usm.cos420.antenatal.dao.domain;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.usm.cos420.antenatal.dao.domain.AntenatalPatientDao;
import edu.usm.cos420.antenatal.domain.AntenatalPatient;

public class testPatient {

    AntenatalPatientDao pDao;

    @Before
    public void setupData() {
        pDao = new AntenatalPatientDao("patient_test.json");
    }

    @Test
    /*
     * Test adding an antenatal patient
     */
    public void testaddAntenatalPatient() {
        TreeMap<String, Boolean> ailments = new TreeMap<String, Boolean>();
        ailments.put("hiv", true);
        ailments.put("anemia", false);
        ailments.put("syphilis", false);
        ailments.put("pe", true);
        AntenatalPatient testPatient = new AntenatalPatient("Billy", "Johnson", "123 Street", "Manhattan", "NYC", "Male",
                "34", LocalDate.of(1999, Month.DECEMBER, 31), 178, 111,
                "O+", "no", LocalDate.of(1899, Month.JANUARY, 1), ailments, 100000L);
        List<AntenatalPatient> pList;

        pDao.add(testPatient);
        pList = pDao.aplist();

        testPatient = pDao.find(pList.get(0).getPatientId());

        assertNotNull("Dao returns a null patient.", testPatient);
    }

    @Test
    /*
     * Test removing an antenatal patient
     */
    public void testremoveAntenatalPatient() {
        TreeMap<String, Boolean> ailments = new TreeMap<String, Boolean>();
        ailments.put("hiv", true);
        ailments.put("anemia", false);
        ailments.put("syphilis", false);
        ailments.put("pe", true);
        AntenatalPatient testPatient = new AntenatalPatient("Billy", "Johnson", "123 Street", "Manhattan", "NYC", "Male",
                "34", LocalDate.of(1999, Month.DECEMBER, 31), 178, 111,
                "O+", "no", LocalDate.of(1899, Month.JANUARY, 1), ailments, 100000L);
        List<AntenatalPatient> pList;

        pDao.add(testPatient);
        pList = pDao.aplist();

        testPatient = pDao.find(pList.get(0).getPatientId());

        pDao.remove(testPatient.getPatientId());
        testPatient = pDao.find(pList.get(0).getPatientId());

        assertNull("Dao does not accurately remove patient.", testPatient);
    }

    @Test
    /*
     * Test to make sure patient ID numbers are generated randomly
     */
    public void testantenatalPatientIDNumbers() {
        TreeMap<String, Boolean> ailments1 = new TreeMap<String, Boolean>();
        ailments1.put("hiv", true);
        ailments1.put("anemia", false);
        ailments1.put("syphilis", false);
        ailments1.put("pe", true);
        AntenatalPatient testPatientOne = new AntenatalPatient("Billy", "Johnson", "123 Street", "Manhattan", "NYC", "Male",
                "34", LocalDate.of(1999, Month.DECEMBER, 31), 178, 111,
                "O+", "no", LocalDate.of(1899, Month.JANUARY, 1), ailments1, 100000L);

        TreeMap<String, Boolean> ailments2 = new TreeMap<String, Boolean>();
        ailments2.put("hiv", true);
        ailments2.put("anemia", true);
        ailments2.put("syphilis", true);
        ailments2.put("pe", true);
        AntenatalPatient testPatientTwo = new AntenatalPatient("Zoey", "Zipper", "888 Ave", "Chicago", "Hyde Park", "Female",
                "33", LocalDate.of(1977, Month.JULY, 7), 133, 133,
                "A+", "yes", LocalDate.of(2000, Month.JANUARY, 1), ailments2, 1000002L);
        List<AntenatalPatient> pList;

        pDao.add(testPatientOne);
        pDao.add(testPatientTwo);
        pList = pDao.aplist();

        testPatientOne = pDao.find(pList.get(0).getPatientId());
        testPatientTwo = pDao.find(pList.get(1).getPatientId());

        assertNotEquals("Patient ID numbers are equal", testPatientOne.getPatientId(), testPatientTwo.getPatientId());
    }

    //	@Test
    /*
     * 
     */

    /**
     * Need to delete the file for next test
     */
    @After
    public void tearDown() {
        System.gc();
        Path path = FileSystems.getDefault().getPath(".", "patient_test.json");
        try {
            Files.delete(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
    }
}
