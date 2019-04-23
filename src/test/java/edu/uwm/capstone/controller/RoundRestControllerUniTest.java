package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.RoundDao;
import edu.uwm.capstone.model.Round.Round;
import edu.uwm.capstone.sql.exception.DaoException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


import static edu.uwm.capstone.util.TestDataUtility.randomAlphabetic;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import static edu.uwm.capstone.util.TestDataUtility.roundWithTestValues;

@RunWith(MockitoJUnitRunner.class)
public class RoundRestControllerUniTest {
    @Mock
    private RoundDao roundDao;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private RoundRestController roundRestController;

    private static final String TEST_ERROR_MESSAGE = randomAlphabetic(15);

    @Test
    public void getRounds() throws IOException {
        Round createdRound = roundWithTestValues();
        when(roundDao.read()).thenReturn(createdRound);

        Round returnedRound = roundRestController.getRounds(response);
        assertEquals(createdRound,returnedRound);
    }

    @Test
    public void getRoundsNull() throws IOException{
        when(roundDao.read()).thenReturn(null);
        Round returnedRound = roundRestController.getRounds(response);
        assertNull(returnedRound);
        verify(response,times(1)).sendError(HttpServletResponse.SC_NOT_FOUND,"No Rounds were found.");
    }

    @Test
    public void updateRound()throws IOException{
        when(roundDao.update(anyInt(),anyBoolean())).thenReturn(1);
        roundRestController.updateRound(Integer.toString(1),Integer.toString(1),response);
        verify(roundDao,times(1)).update(anyInt(),anyBoolean());
    }

    @Test
    public void updateRoundInvalid()throws IOException{
        String roundNum = Integer.toString(3);
        when(roundDao.update(anyInt(),anyBoolean())).thenReturn(0);
        roundRestController.updateRound(Integer.toString(3),roundNum,response);
        verify(response,times(1)).sendError(HttpServletResponse.SC_NOT_FOUND,"Round num " + roundNum + " is not found");

    }

}
