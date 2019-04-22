package edu.uwm.capstone.controller;

import edu.uwm.capstone.db.PosterDao;
import edu.uwm.capstone.db.VoteDao;
import edu.uwm.capstone.util.TestDataUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VoteRestControllerUnitTest {

    @Mock
    private VoteDao voteDao;

    @Mock
    private PosterDao posterDao;

    @InjectMocks
    private VoteRestController voteRestController;

    @Test
    public void testSetVote() {
        String s1 = "Test";
        String s2 = "Votes";
        voteRestController.vote(s1, s2);
        verify(posterDao, times(1)).setVote(s1, s2);
    }

    @Test
    public void testReadVotes(){
        when(voteDao.read(any(String.class))).thenReturn(null);
        String tester = TestDataUtility.randomAlphabetic(10);
        voteRestController.getGradWinner(tester);
        verify(voteDao, times(1)).read(tester);
    }
}
