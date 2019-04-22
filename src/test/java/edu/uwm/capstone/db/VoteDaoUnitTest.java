package edu.uwm.capstone.db;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.model.Vote.Vote;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static edu.uwm.capstone.util.TestDataUtility.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfig.class)
public class VoteDaoUnitTest {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    VoteDao voteDao;

    @Autowired
    PosterDao posterDao;

    @Before
    public void setUp() {
        assertNotNull(posterDao);
        assertNotNull(posterDao.sql("setVote"));
    }

    @Test
    public void createVote(){
        assertNull(voteDao.create(voteWithTestValues()));
    }

    @Test
    public void readVotes(){
        assertNull(voteDao.read(randomLong()));
    }

    @Test
    public void testGetEmptyVotes(){
        posterDao.clearTable();
        List<Vote> votes = voteDao.read("Invalid");
        assertEquals(votes.size(), 0);
    }

}
