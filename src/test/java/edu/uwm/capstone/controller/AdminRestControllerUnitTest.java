package edu.uwm.capstone.controller;


import edu.uwm.capstone.db.AdminDao;
import edu.uwm.capstone.model.Admin.Admin;
import edu.uwm.capstone.model.Admin.Admin;
import edu.uwm.capstone.sql.exception.DaoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;
import static edu.uwm.capstone.util.TestDataUtility.*;

import java.io.IOException;

import static edu.uwm.capstone.util.TestDataUtility.randomAlphabetic;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminRestControllerUnitTest {
    /**
     * This test class uses Mockito framework to mock out all of the dependencies for the class under test and isolate it from the rest of the application.
     * This way the test suite verifies if the class under test works correctly assuming all of its dependencies are behaving as expected.
     */

    @Mock
    private AdminDao adminDao;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private AdminRestController adminRestController;

    private static final String TEST_ERROR_MESSAGE = randomAlphabetic(15);

    @Test
    public void create() throws IOException {
        Admin adminCreate = adminWithTestValues();
        when(adminDao.create(any(Admin.class))).thenReturn(adminCreate);

        Admin createdAdmin = adminRestController.create(adminCreate, response);
        assertEquals(adminCreate, createdAdmin);
    }
    @Test
    public void createNull() throws IOException {
        adminRestController.create(null, response);

        verify(response, times(1)).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Received null Admin object");
    }
    @Test
    public void createException() throws IOException {
        Admin adminCreate = adminWithTestValues();
        when(adminDao.create(any(Admin.class))).thenThrow(new DaoException(TEST_ERROR_MESSAGE));

        adminRestController.create(adminCreate, response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, TEST_ERROR_MESSAGE);
    }
    @Test
    public void update() throws IOException {
        Admin adminCreate = adminWithTestValues();
        when(adminDao.read(anyString())).thenReturn(adminCreate);

        adminRestController.update(adminCreate, response);
        verify(adminDao, times(1)).update(adminCreate);
    }

    @Test
    public void updateException() throws IOException {
        Admin adminCreate = adminWithTestValues();
        when(adminDao.read(anyString())).thenReturn(adminCreate);
        doThrow(new DaoException(TEST_ERROR_MESSAGE)).when(adminDao).update(any(Admin.class));

        adminRestController.update(adminCreate, response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, TEST_ERROR_MESSAGE);
    }

    @Test
    public void updatePreconditionFailed() throws IOException {
        adminRestController.update(adminWithTestValues(), response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Could not update Admin - record not found.");
    }

    @Test
    public void readByIdNotFound() throws IOException {
        String adminEmail = randomAlphabetic(randomInt(1,100));
        when(adminDao.read(anyString())).thenThrow(DaoException.class);

        adminRestController.readById(adminEmail, response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_NOT_FOUND, null);
    }
}