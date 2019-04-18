package edu.uwm.capstone.db;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.model.Admin.Admin;
import edu.uwm.capstone.util.TestDataUtility;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

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
        assertNotNull(adminDao.sql("readAllAdmins"));
        assertNotNull(adminDao.sql("readAdmin"));
        assertNotNull(adminDao.sql("readAdminEmailPin"));
        assertNotNull(adminDao.sql("updateAdmin"));
        assertNotNull(adminDao.sql("deleteAdmin"));
        assertNotNull(adminDao.sql("clearAdmins"));
    }

    /**
     * Verify that {@link AdminDao#create} is working correctly.
     */
    @Test
    public void create() {
        Admin createAdmin = TestDataUtility.adminWithTestValues();
        adminDao.create(createAdmin);
        assertNotNull(createAdmin.getEmail());
        assertNotNull(createAdmin.getPin());
        assertNotNull(createAdmin.getLast_name());
        assertNotNull(createAdmin.getFirst_name());
        assertNotNull(createAdmin.getRole());
        assertTrue(createAdmin.isRead_r());
        assertTrue(createAdmin.isWrite_w());
    }

    /**
     * Verify that {@link AdminDao#create} is working correctly when a request for creating a null object is made.
     */
    @Test(expected = RuntimeException.class)
    public void createNullAdmin() {
        adminDao.create(null);
    }

    /**
     * Verify that {@link AdminDao#create} is working correctly when a request for a {@link Admin} that contains a value
     * which exceeds the database configuration is made.
     */
    @Test(expected = RuntimeException.class)
    public void createAdminColumnTooLong() {
        // generate a test profile value with a column that will exceed the database configuration
        Admin createAdmin = TestDataUtility.adminWithTestValues();
        createAdmin.setFirst_name(RandomStringUtils.randomAlphabetic(2000));
        adminDao.create(createAdmin);
    }

    /**
     * Verify that {@link AdminDao#read} is working correctly.
     */
    @Test
    public void readAdmin() {
        Admin createAdmin = TestDataUtility.adminWithTestValues();
        adminDao.create(createAdmin);
        assertNotNull(createAdmin.getEmail());
        assertNotNull(createAdmin.getPin());
        assertNotNull(createAdmin.getLast_name());
        assertNotNull(createAdmin.getFirst_name());
        assertNotNull(createAdmin.getRole());
        assertTrue(createAdmin.isRead_r());
        assertTrue(createAdmin.isWrite_w());

        Admin readAdmin = adminDao.read(createAdmin.getEmail());
        assertNotNull(readAdmin);
        assertEquals(createAdmin.getEmail(), readAdmin.getEmail());
    }
    /**
     * Verify that {@link AdminDao#read} is working correctly when a request for a non-existent {@link Admin#getEmail()} is made.
     */
    @Test
    public void readNonExistentAdminByEmailAndPin(){
        Admin createAdmin = TestDataUtility.adminWithTestValues();
        adminDao.create(createAdmin);
        Admin invalidAdmin = TestDataUtility.adminWithTestValues();
        String email = invalidAdmin.getEmail();
        String pin = invalidAdmin.getPin();
        Admin admin = adminDao.read(email,pin);
        assertNull(admin);
    }

    /**
     * Verify that {@link AdminDao#read} is working correctly when a request for a existent {@link Admin#getEmail()} is made.
     */
    @Test
    public void readAdminEmailPin(){
        Admin createAdmin = TestDataUtility.adminWithTestValues();
        adminDao.create(createAdmin);
        assertNotNull(createAdmin.getEmail());
        assertNotNull(createAdmin.getPin());
        assertNotNull(createAdmin.getLast_name());
        assertNotNull(createAdmin.getFirst_name());
        assertNotNull(createAdmin.getRole());
        assertTrue(createAdmin.isRead_r());
        assertTrue(createAdmin.isWrite_w());

        Admin readAdmin = adminDao.read(createAdmin.getEmail(),createAdmin.getPin());
        assertNotNull(readAdmin);
        assertEquals(createAdmin.getEmail(),readAdmin.getEmail());
        assertEquals(createAdmin.getPin(),readAdmin.getPin());
    }

    /**
     * Verify that {@link AdminDao#update} is working correctly.
     */
    @Test
    public void update() {
        Admin createAdmin = TestDataUtility.adminWithTestValues();
        adminDao.create(createAdmin);
        assertNotNull(createAdmin.getEmail());
        assertNotNull(createAdmin.getPin());
        assertNotNull(createAdmin.getLast_name());
        assertNotNull(createAdmin.getFirst_name());
        assertNotNull(createAdmin.getRole());
        assertTrue(createAdmin.isRead_r());
        assertTrue(createAdmin.isWrite_w());

        Admin verifyCreateAdmin = adminDao.read(createAdmin.getEmail());
        assertNotNull(verifyCreateAdmin);
        assertEquals(createAdmin.getEmail(), verifyCreateAdmin.getEmail());

        Admin updateAdmin = TestDataUtility.adminWithTestValues();
        updateAdmin.setFirst_name(createAdmin.getFirst_name());
        updateAdmin.setLast_name(createAdmin.getLast_name());
        updateAdmin.setEmail(createAdmin.getEmail());
        updateAdmin.setPin(createAdmin.getPin());
        updateAdmin.setRole(createAdmin.getRole());
        updateAdmin.setWrite_w(createAdmin.isWrite_w());
        updateAdmin.setRead_r(createAdmin.isRead_r());
        adminDao.update(updateAdmin);

        Admin verifyUpdateAdmin = adminDao.read(updateAdmin.getEmail());
        assertNotNull(verifyUpdateAdmin);
        assertEquals(createAdmin.getEmail(), verifyUpdateAdmin.getEmail());
        assertEquals(createAdmin.getPin(), verifyUpdateAdmin.getPin());
        assertEquals(createAdmin.getFirst_name(), verifyUpdateAdmin.getFirst_name());
        assertEquals(createAdmin.getLast_name(), verifyUpdateAdmin.getLast_name());

    }

    /**
     * Verify that {@link AdminDao#update} is working correctly when a request for creating a null object is made.
     */
    @Test(expected = RuntimeException.class)
    public void updateNullAdmin() {
        adminDao.update(null);
    }

    /**
     * Verify that {@link AdminDao#update} is working correctly when a request for a non-existent {@link Admin#getEmail()} is made.
     */
    @Test(expected = RuntimeException.class)
    public void updateNonExistentAdmin() {
        // create a random admin id that will not be in our local database
        Admin updateAdmin = TestDataUtility.adminWithTestValues();
        updateAdmin.setEmail("@email.com");
        adminDao.update(updateAdmin);
    }

    /**
     * Verify that {@link AdminDao#delete} is working correctly.
     */
    @Test
    public void delete() {
        Admin createAdmin = TestDataUtility.adminWithTestValues();
        adminDao.create(createAdmin);
        assertNotNull(createAdmin.getEmail());
        assertNotNull(createAdmin.getPin());
        assertNotNull(createAdmin.getLast_name());
        assertNotNull(createAdmin.getFirst_name());
        assertNotNull(createAdmin.getRole());
        assertTrue(createAdmin.isRead_r());
        assertTrue(createAdmin.isWrite_w());

        Admin verifyCreateAdmin = adminDao.read(createAdmin.getEmail());

        assertNotNull(verifyCreateAdmin);
        assertEquals(createAdmin.getEmail(),verifyCreateAdmin.getEmail());
        assertEquals(createAdmin.getFirst_name(),verifyCreateAdmin.getFirst_name());
        assertEquals(createAdmin.getLast_name(),verifyCreateAdmin.getLast_name());
        assertEquals(createAdmin.getRole(),verifyCreateAdmin.getRole());
        assertEquals(createAdmin.getPin(),verifyCreateAdmin.getPin());
        adminDao.delete(createAdmin.getEmail());

        Admin verifyDeleteAdmin = adminDao.read(createAdmin.getEmail());
        assertNull(verifyDeleteAdmin);
    }

    /**
     * Verify that {@link AdminDao#delete} is working correctly when a request for a non-existent {@link Admin#getEmail()} is made.
     */
    @Test(expected = RuntimeException.class)
    public void deleteNonExistentAdmin() {
        adminDao.delete("notARealEmail@email.com");
    }

}
