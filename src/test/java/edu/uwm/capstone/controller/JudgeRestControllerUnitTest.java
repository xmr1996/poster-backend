package edu.uwm.capstone.controller;
import edu.uwm.capstone.db.JudgeDao;
import edu.uwm.capstone.model.Judge.Judge;
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
public class JudgeRestControllerUnitTest {

    @Mock
    private JudgeDao judgeDao;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private JudgeRestController judgeRestController;

    private static final String TEST_ERROR_MESSAGE = randomAlphabetic(15);

    @Test
    public void create() throws IOException {
        Judge judgeToCreate = judgeWithTestValues();
        when(judgeDao.create(any(Judge.class))).thenReturn(judgeToCreate);

        Judge createJudge = judgeRestController.create(judgeToCreate, response);
        assertEquals(judgeToCreate, createJudge);
    }

    @Test
    public void createNull() throws IOException {
        judgeRestController.create(null, response);

        verify(response, times(1)).sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "Received null Judge object");
    }

    @Test
    public void createException() throws IOException {
        Judge judgeToCreate = judgeWithTestValues();
        when(judgeDao.create(any(Judge.class))).thenThrow(new DaoException(TEST_ERROR_MESSAGE));

        judgeRestController.create(judgeToCreate, response);
        verify(response, times(1)).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, TEST_ERROR_MESSAGE);
    }


}
