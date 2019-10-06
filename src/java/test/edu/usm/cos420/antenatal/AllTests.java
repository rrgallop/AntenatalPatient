package edu.usm.cos420.antenatal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.usm.cos420.antenatal.dao.TestJsonDao;
import edu.usm.cos420.antenatal.dao.domain.testPatient;
import edu.usm.cos420.antenatal.dao.domain.testVisit;
import edu.usm.cos420.antenatal.service.impl.TestAlertService;
import edu.usm.cos420.antenatal.service.impl.TestValidationService;


@RunWith(Suite.class)
@SuiteClasses({  testPatient.class, testVisit.class, TestJsonDao.class, TestValidationService.class})//, TestAlertService.class })

public class AllTests {

}