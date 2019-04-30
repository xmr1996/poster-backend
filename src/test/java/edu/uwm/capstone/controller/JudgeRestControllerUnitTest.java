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
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void update() throws IOException {
        Judge judgeToCreate = judgeWithTestValues();
        when(judgeDao.readByJudgeID(anyLong())).thenReturn(judgeToCreate);

        judgeRestController.update(judgeToCreate, response);
        verify(judgeDao, times(1)).update(judgeToCreate);
    }

    @Test
    public void readById() throws IOException {
        Judge judgeToCreate = judgeWithTestValues();
        judgeDao.create(judgeToCreate);
        judgeRestController.readByJudgeId(judgeToCreate.getJudge_id(), response);
        verify(judgeDao, times(1)).readByJudgeID(judgeToCreate.getJudge_id());
    }

    @Test
    public void delete() throws IOException {
        Judge judgeToCreate = judgeWithTestValues();
        judgeDao.create(judgeToCreate);
        judgeRestController.deleteByJudgeId(judgeToCreate.getJudge_id(), response);
        verify(judgeDao, times(1)).deleteByJudgeId(judgeToCreate.getJudge_id());
    }

    @Test
    public void readAll() throws IOException {
        judgeRestController.readAllJudges( response);
        verify(judgeDao, times(1)).read();
    }

    @Test
    public void clearTable() throws IOException {
        judgeRestController.clearTable( response);
        verify(judgeDao, times(1)).clearTable();
    }

    @Test
    public void testImportCSV() {
        int num_of_judges = randomInt(0, 100);
        List<Judge> judges = new ArrayList<>();

        for(int i = 0; i < num_of_judges; i++){
            judges.add(judgeWithTestValues());
        }

        judgeRestController.importCSV(judges);
        verify(judgeDao, times(num_of_judges)).create(any(Judge.class));
    }

}
