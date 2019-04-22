package edu.uwm.capstone.model;
import edu.uwm.capstone.model.Admin.Admin;
import org.junit.Test;

import static edu.uwm.capstone.helper.TestHelper.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdminModelUnitTest {

    @Test
    public void getValidEmailUnitTest() {
        Admin admin = new Admin();
        String email = randomAlphabetic(10);
        admin.setEmail(email);
        assertTrue(admin.getEmail().toString() == email);
    }

    @Test
    public void getInValidEmailUnitTest() {
        Admin admin = new Admin();
        String email = randomAlphabetic(10);
        admin.setEmail(email);
        assertFalse(admin.getEmail().toString() == randomAlphanumeric(10));
    }

    @Test
    public void getValidFirstNameUnitTest(){
        Admin admin = new Admin();
        String firstName = randomAlphabetic(8);
        admin.setFirst_name(firstName);
        assertTrue(admin.getFirst_name().toString()== firstName);
    }

    @Test
    public void getInValidFirstNameUnitTest() {
        Admin admin = new Admin();
        String firstName = randomAlphabetic(8);
        admin.setFirst_name(firstName);
        assertFalse(admin.getFirst_name().toString() == randomAlphanumeric(8));
    }

    @Test
    public void getValidLastNameUnitTest() {
        Admin admin = new Admin();
        String lastName = randomAlphabetic(8);
        admin.setLast_name(lastName);
        assertTrue(admin.getLast_name().toString() == lastName);
    }

    @Test
    public void getInValidLastNameUnitTest() {
        Admin admin = new Admin();
        String lastName = randomAlphabetic(8);
        admin.setLast_name(lastName);
        assertFalse(admin.getLast_name().toString() == randomAlphanumeric(8));
    }

    @Test
    public void getValidPinUnitTest() {
        Admin admin = new Admin();
        String pin = randomNumeric(4);
        admin.setPin(pin);
        assertTrue(admin.getPin().toString() == pin);
    }

    @Test
    public void getInValidPinUnitTest() {
        Admin admin = new Admin();
        String pin = randomNumeric(4);
        admin.setPin(pin);
        assertFalse(admin.getPin().toString() == randomNumeric(4));
    }

    @Test
    public void getValidRoleUnitTest() {
        Admin admin = new Admin();
        String role = randomAlphabetic(5);
        admin.setRole(role);
        assertTrue(admin.getRole().toString() == role);
    }

    @Test
    public void getInValidRoleUnitTest() {
        Admin admin = new Admin();
        String role = randomAlphabetic(5);
        admin.setRole(role);
        assertFalse(admin.getRole().toString() == randomAlphabetic(5));
    }


}
