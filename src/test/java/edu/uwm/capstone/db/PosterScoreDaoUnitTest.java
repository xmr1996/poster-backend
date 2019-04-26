package edu.uwm.capstone.db;

import edu.uwm.capstone.model.PosterScore.PosterScore;
import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.util.TestDataUtility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfig.class)
public class PosterScoreDaoUnitTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    PosterScoreDao posterScoreDao;

    @Before
    public void setUp(){
        posterScoreDao.sql("readScoreByRoundandJudge");
    }

    public static int randomInt(int min, int max) {
        return new Random().ints(min, max).findAny().getAsInt();
    }
    public static long randomLong(long min, long max) { return new Random().longs(min,max).findAny().getAsLong(); }

    @Test
    public void readByRoundandJudge(){
        List<PosterScore> posterScores = posterScoreDao.readByRoundandJudge(randomInt(1,2),randomLong(1L,100L));
        assertNotNull(posterScores);
    }

    @Test
    public void create(){
        PosterScore posterScore = TestDataUtility.posterScoreWithTestValues();
        PosterScore createdPosterScore = posterScoreDao.create(posterScore);
        assertNull(createdPosterScore);
    }



}
