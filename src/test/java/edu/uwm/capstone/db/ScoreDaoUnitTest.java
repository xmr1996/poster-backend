package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Score.Score;
import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.util.TestDataUtility;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfig.class)

public class ScoreDaoUnitTest {
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    ScoreDao scoreDao;

    @Before
    public void setUp() {
        assertNotNull(scoreDao);
        assertNotNull(scoreDao.sql("createScore"));
        assertNotNull(scoreDao.sql("readScore"));
        assertNotNull(scoreDao.sql("updateScore"));
        assertNotNull(scoreDao.sql("deleteScore"));
    }


    /**
     * Verify that {@link ScoreDao#create} is working correctly when a request for creating a null object is made.
     */
    @Test(expected = RuntimeException.class)
    public void createNullScore() {
        scoreDao.create(null);
    }


//    /**
//     * Verify that {@link ScoreDao#create} is working correctly.
//     */
//    @Test
//    public void create() {
//        Poster createPoster = TestDataUtility.posterWithTestValues();
//        String id1 = createPoster.getPoster_id();
////        assertNotNull(id1);
////        Judge createJudge = TestDataUtility.judgeWithTestValues();
//        Long id2 = createJudge.getId();
//        assertNotNull(id2);
//        Score createScore = TestDataUtility.scoreWithTestValues();
//        createScore.setPoster_id(id1);
//        createScore.setJudge_id(id2);
//        scoreDao.create(createScore);
//        assertNotNull(createScore.getId());
    //}

}
