package edu.uwm.capstone.controller;


import edu.uwm.capstone.db.AdminDao;
import edu.uwm.capstone.db.JudgeDao;
import edu.uwm.capstone.db.PosterDao;
import edu.uwm.capstone.model.Admin.Admin;
import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.model.Poster.Poster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static edu.uwm.capstone.util.TestDataUtility.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * This test class uses Mockito framework to mock out all of the dependencies for the class under test and isolate it from the rest of the application.
 * This way the test suite verifies if the class under test works correctly assuming all of its dependencies are behaving as expected.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginRestControllerUnitTest {
    @Mock
    JudgeDao judgeDao;

    @Mock
    PosterDao posterDao;

    @Mock
    AdminDao adminDao;

    @InjectMocks
    LoginRestController loginRestController;


    @Test
    public void createJudge(){
        Judge judge = judgeWithTestValues();
        when(judgeDao.read(anyString(),anyString())).thenReturn(judge);

        Object returnedObject = loginRestController.create(judge.getEmail(),judge.getPin());
        assertEquals(judge,returnedObject);

    }

    @Test
    public void createPoster(){
        Poster poster = posterWithTestValues();
        when(posterDao.read(anyString(),anyString())).thenReturn(poster);

        Object returnedObject = loginRestController.create(poster.getEmail(),poster.getPin());
        assertEquals(poster,returnedObject);
    }

    @Test
    public void createAdmin(){
        Admin admin = adminWithTestValues();
        when(adminDao.read(anyString(),anyString())).thenReturn(admin);

        Object returnedObject = loginRestController.create(admin.getEmail(),admin.getPin());
        assertEquals(admin,returnedObject);
    }

    @Test
    public void createNull(){
        when(adminDao.read(anyString(),anyString())).thenReturn(null);

        Object returnedObject = loginRestController.create(anyString(),anyString());
        assertNull(returnedObject);
    }
}
