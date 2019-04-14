package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.ScoreDao;
import edu.uwm.capstone.model.Score.Score;
import edu.uwm.capstone.sql.exception.DaoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static edu.uwm.capstone.util.TestDataUtility.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * This test class uses Mockito framework to mock out all of the dependencies for the class under test and isolate it from the rest of the application.
 * This way the test suite verifies if the class under test works correctly assuming all of its dependencies are behaving as expected.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScoreRestControllerUnitTest{
    @Mock
    private ScoreDao scoreDao;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private ScoreRestController scoreRestController;

    private static final String TEST_ERROR_MESSAGE = randomAlphabetic(15);

    @Test
    public void create() throws IOException {
        Score scoreToCreate = scoreWithTestValues();
        when(scoreDao.create(any(Score.class))).thenReturn(scoreToCreate);

        Score createdScore = scoreRestController.create(scoreToCreate, response);
        assertEquals(scoreToCreate, createdScore);
    }

    @Test
    public void createNull() throws IOException{
        scoreRestController.create(null,response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_PRECONDITION_FAILED,"Received null Score object");
    }
//
//    @Test
//    public void createException() throws IOException{
//        Score scoreToCreate = scoreWithTestValues();
//    }

}