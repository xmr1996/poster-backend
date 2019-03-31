package edu.uwm.capstone.db;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.model.Admin.Admin;
import edu.uwm.capstone.util.TestDataUtility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfig.class)
public class AdminDaoUnitTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    AdminDao adminDao;

    @Before
    public void setUp() {
        assertNotNull(adminDao);
        assertNotNull(adminDao.sql("createAdmin"));
        assertNotNull(adminDao.sql("readAdmin"));
        assertNotNull(adminDao.sql("updateAdmin"));
        assertNotNull(adminDao.sql("deleteAdmin"));
    }

    /**
     * Verify that {@link AdminDao#create} is working correctly.
     */
    @Test
    public void create() {
        Admin admin1 = new Admin();
        admin1.setEmail("john3885@uwm.edu");
        admin1.setWrite(true);
        admin1.setRead(true);
        admin1.setFirst_name("Tyler");
        admin1.setLast_name("Johnson");
        admin1.setPin("1234");
        adminDao.create(admin1);

        Admin admin2 = new Admin();
        admin2.setEmail("pero@uwm.edu");
        admin2.setWrite(true);
        admin2.setRead(true);
        admin2.setFirst_name("Wendy");
        admin2.setLast_name("Pero");
        admin2.setPin("1234");
        adminDao.create(admin2);

        Admin admin3 = new Admin();
        admin3.setEmail("munson@uwm.edu");
        admin3.setWrite(true);
        admin3.setRead(true);
        admin3.setFirst_name("Ethan");
        admin3.setLast_name("Munson");
        admin3.setPin("1234");
        adminDao.create(admin3);

        Admin admin4 = new Admin();
        admin4.setEmail("cjscholl@uwm.edu");
        admin4.setWrite(true);
        admin4.setRead(true);
        admin4.setFirst_name("Catelyn");
        admin4.setLast_name("Scholl");
        admin4.setPin("1234");
        adminDao.create(admin4);

        Admin admin5 = new Admin();
        admin5.setEmail("xiangm@uwm.edu");
        admin5.setWrite(true);
        admin5.setRead(true);
        admin5.setFirst_name("Mingren");
        admin5.setLast_name("Xiang");
        admin5.setPin("1234");
        adminDao.create(admin5);

        Admin admin6 = new Admin();
        admin6.setEmail("faassad@uwm.edu");
        admin6.setWrite(true);
        admin6.setRead(true);
        admin6.setFirst_name("Fawzieh");
        admin6.setLast_name("Assad");
        admin6.setPin("1234");
        adminDao.create(admin6);

        Admin admin7 = new Admin();
        admin7.setEmail("adamdunn@uwm.edu");
        admin7.setWrite(true);
        admin7.setRead(true);
        admin7.setFirst_name("Adam");
        admin7.setLast_name("Dunn");
        admin7.setPin("1234");
        adminDao.create(admin7);

        Admin admin8 = new Admin();
        admin8.setEmail("doneil@uwm.edu");
        admin8.setWrite(true);
        admin8.setRead(true);
        admin8.setFirst_name("David");
        admin8.setLast_name("O'neil");
        admin8.setPin("1234");
        adminDao.create(admin8);

        Admin admin9 = new Admin();
        admin9.setEmail("jhortman@uwm.edu");
        admin9.setWrite(true);
        admin9.setRead(true);
        admin9.setFirst_name("James");
        admin9.setLast_name("Hortman");
        admin9.setPin("1234");
        adminDao.create(admin9);

        Admin admin10 = new Admin();
        admin10.setEmail("mdshahr3@uwm.edu");
        admin10.setWrite(true);
        admin10.setRead(true);
        admin10.setFirst_name("Zane");
        admin10.setLast_name("Shahrin");
        admin10.setPin("1234");
        adminDao.create(admin10);

        Admin admin11 = new Admin();
        admin11.setEmail("testadmin@uwm.edu");
        admin11.setWrite(false);
        admin11.setRead(true);
        admin11.setFirst_name("Test");
        admin11.setLast_name("User");
        admin11.setPin("1234");
        adminDao.create(admin11);
    }


}
