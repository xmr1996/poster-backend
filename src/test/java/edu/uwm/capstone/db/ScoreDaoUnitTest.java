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

    @Autowired
    PosterDao posterDao;

    @Autowired
    JudgeDao judgeDao;


    /**
     * Generate a random {@link int} using the provided minimum and maximum values.
     * @param min {@link int} minimum value
     * @param max {@link int} maximum value
     * @return random {@link int}
     */
    public static int randomInt(int min, int max) {
        return new Random().ints(min, max).findAny().getAsInt();
    }


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


    /**
     * Verify that {@link ScoreDao#create} is working correctly.
     */
    @Test
    public void createTestScorre() {
        //grad stuff
        Judge g_judge = new Judge();
        g_judge.setRole("judge");
        g_judge.setEmail("gradjudge@email.com");
        g_judge.setPin(1234);
        g_judge.setFirst_name("gradJudge1");
        g_judge.setLast_name("gradJudge1");
        g_judge.setStatus("Graduate");
        judgeDao.create(g_judge);
        assertNotNull(g_judge.getId());

        Poster g_poster1 = new Poster();
        g_poster1.setPoster_id("G10");
        g_poster1.setPin(1234);
        g_poster1.setFirst_name("GtestScoreF1");
        g_poster1.setLast_name("GtestScoreL1");
        g_poster1.setTitle("This is the Graduate Poster Title");
        g_poster1.setStatus("Graduate");
        g_poster1.setDepartment("Computer Science");
        g_poster1.setEmail("gradposter@email.com");
        posterDao.create(g_poster1);
        assertNotNull(g_poster1.getId());

        Poster g_poster2 = new Poster();
        g_poster2.setPoster_id("G11");
        g_poster2.setPin(1234);
        g_poster2.setFirst_name("testScoreF2");
        g_poster2.setLast_name("testScoreL2");
        g_poster2.setTitle("This is the Graduate Poster Title");
        g_poster2.setStatus("Graduate");
        g_poster2.setDepartment("Computer Science");
        g_poster2.setEmail("gradposter@email.com");
        posterDao.create(g_poster2);
        assertNotNull(g_poster2.getId());

        Poster g_poster3 = new Poster();
        g_poster3.setPoster_id("G12");
        g_poster3.setPin(1234);
        g_poster3.setFirst_name("testScoreF3");
        g_poster3.setLast_name("testScoreL3");
        g_poster3.setTitle("This is the Graduate Poster Title");
        g_poster3.setStatus("Graduate");
        g_poster3.setDepartment("Computer Science");
        g_poster3.setEmail("gradposter@email.com");
        posterDao.create(g_poster3);
        assertNotNull(g_poster3.getId());

        Score g_score1 = new Score();
        g_score1.setPoster_id(g_poster1.getPoster_id());
        g_score1.setJudge_id(g_judge.getId());
        g_score1.setRound(1);
        g_score1.setResearch_score(randomInt(1,50));
        g_score1.setComm_score(randomInt(1,30));
        g_score1.setPoster_score(randomInt(1,20));
        g_score1.setTotal_score(g_score1.getResearch_score()+ g_score1.getComm_score()+g_score1.getPoster_score());
        scoreDao.create(g_score1);
        assertNotNull(g_score1.getId());

        Score g_score2 = new Score();
        g_score2.setPoster_id(g_poster2.getPoster_id());
        g_score2.setJudge_id(g_judge.getId());
        g_score2.setRound(1);
        g_score2.setResearch_score(randomInt(1,50));
        g_score2.setComm_score(randomInt(1,30));
        g_score2.setPoster_score(randomInt(1,20));
        g_score2.setTotal_score(g_score2.getResearch_score()+ g_score2.getComm_score()+g_score2.getPoster_score());
        scoreDao.create(g_score2);
        assertNotNull(g_score2.getId());

        Score g_score3 = new Score();
        g_score3.setPoster_id(g_poster3.getPoster_id());
        g_score3.setJudge_id(g_judge.getId());
        g_score3.setRound(1);
        g_score3.setResearch_score(randomInt(1,50));
        g_score3.setComm_score(randomInt(1,30));
        g_score3.setPoster_score(randomInt(1,20));
        g_score3.setTotal_score(g_score3.getResearch_score()+ g_score3.getComm_score()+g_score3.getPoster_score());
        scoreDao.create(g_score3);
        assertNotNull(g_score3.getId());


        //undergrad stuff
        Judge u_judge = new Judge();
        u_judge.setRole("judge");
        u_judge.setEmail("undergradjudge@email.com");
        u_judge.setPin(1234);
        u_judge.setFirst_name("undergradJudge1");
        u_judge.setLast_name("undergradJudge1");
        u_judge.setStatus("Undergraduate");
        judgeDao.create(u_judge);
        assertNotNull(u_judge.getId());

        Judge u_judge2 = new Judge();
        u_judge2.setRole("judge");
        u_judge2.setEmail("undergradjudge2@email.com");
        u_judge2.setPin(1234);
        u_judge2.setFirst_name("undergradJudge2");
        u_judge2.setLast_name("undergradJudge2");
        u_judge2.setStatus("Undergraduate");
        judgeDao.create(u_judge2);
        assertNotNull(u_judge2.getId());

        Poster u_poster1 = new Poster();
        u_poster1.setPoster_id("U10");
        u_poster1.setPin(1234);
        u_poster1.setFirst_name("testScoreF1");
        u_poster1.setLast_name("testScoreL1");
        u_poster1.setTitle("This is the Undergraduate Poster Title");
        u_poster1.setStatus("Undergraduate");
        u_poster1.setDepartment("Computer Science");
        u_poster1.setEmail("undergradposter@email.com");
        posterDao.create(u_poster1);
        assertNotNull(u_poster1.getId());

        Poster u_poster2 = new Poster();
        u_poster2.setPoster_id("U11");
        u_poster2.setPin(1234);
        u_poster2.setFirst_name("testScoreF2");
        u_poster2.setLast_name("testScoreL2");
        u_poster2.setTitle("This is the Undergraduate Poster Title");
        u_poster2.setStatus("Undergraduate");
        u_poster2.setDepartment("Computer Science");
        u_poster2.setEmail("undergradposter@email.com");
        posterDao.create(u_poster2);
        assertNotNull(u_poster2.getId());

        Poster u_poster3 = new Poster();
        u_poster3.setPoster_id("U12");
        u_poster3.setPin(1234);
        u_poster3.setFirst_name("testScoreF3");
        u_poster3.setLast_name("testScoreL3");
        u_poster3.setTitle("This is the Undergraduate Poster Title");
        u_poster3.setStatus("Undergraduate");
        u_poster3.setDepartment("Computer Science");
        u_poster3.setEmail("undergradposter@email.com");
        posterDao.create(u_poster3);
        assertNotNull(u_poster3.getId());

        Score u_score1 = new Score();
        u_score1.setPoster_id(u_poster1.getPoster_id());
        u_score1.setJudge_id(u_judge.getId());
        u_score1.setRound(1);
        u_score1.setResearch_score(randomInt(1,50));
        u_score1.setComm_score(randomInt(1,30));
        u_score1.setPoster_score(randomInt(1,20));
        u_score1.setTotal_score(u_score1.getResearch_score()+ u_score1.getComm_score()+u_score1.getPoster_score());
        scoreDao.create(u_score1);
        assertNotNull(u_score1.getId());

        Score u_score2 = new Score();
        u_score2.setPoster_id(u_poster2.getPoster_id());
        u_score2.setJudge_id(u_judge.getId());
        u_score2.setRound(1);
        u_score2.setResearch_score(randomInt(1,50));
        u_score2.setComm_score(randomInt(1,30));
        u_score2.setPoster_score(randomInt(1,20));
        u_score2.setTotal_score(u_score2.getResearch_score()+ u_score2.getComm_score()+u_score2.getPoster_score());
        scoreDao.create(u_score2);
        assertNotNull(u_score2.getId());

        Score u_score3 = new Score();
        u_score3.setPoster_id(u_poster3.getPoster_id());
        u_score3.setJudge_id(u_judge.getId());
        u_score3.setRound(1);
        u_score3.setResearch_score(randomInt(1,50));
        u_score3.setComm_score(randomInt(1,30));
        u_score3.setPoster_score(randomInt(1,20));
        u_score3.setTotal_score(u_score3.getResearch_score()+ u_score3.getComm_score()+u_score3.getPoster_score());
        scoreDao.create(u_score3);
        assertNotNull(u_score3.getId());

        Score u_score4 = new Score();
        u_score4.setPoster_id(u_poster1.getPoster_id());
        u_score4.setJudge_id(u_judge2.getId());
        u_score4.setRound(1);
        u_score4.setResearch_score(randomInt(1,50));
        u_score4.setComm_score(randomInt(1,30));
        u_score4.setPoster_score(randomInt(1,20));
        u_score4.setTotal_score(u_score4.getResearch_score()+ u_score4.getComm_score()+u_score4.getPoster_score());
        scoreDao.create(u_score4);
        assertNotNull(u_score4.getId());

        Score u_score5 = new Score();
        u_score5.setPoster_id(u_poster2.getPoster_id());
        u_score5.setJudge_id(u_judge2.getId());
        u_score5.setRound(1);
        u_score5.setResearch_score(randomInt(1,50));
        u_score5.setComm_score(randomInt(1,30));
        u_score5.setPoster_score(randomInt(1,20));
        u_score5.setTotal_score(u_score5.getResearch_score()+ u_score5.getComm_score()+u_score5.getPoster_score());
        scoreDao.create(u_score5);
        assertNotNull(u_score5.getId());

        Score u_score6 = new Score();
        u_score6.setPoster_id(u_poster3.getPoster_id());
        u_score6.setJudge_id(u_judge2.getId());
        u_score6.setRound(1);
        u_score6.setResearch_score(randomInt(1,50));
        u_score6.setComm_score(randomInt(1,30));
        u_score6.setPoster_score(randomInt(1,20));
        u_score6.setTotal_score(u_score6.getResearch_score()+ u_score6.getComm_score()+u_score6.getPoster_score());
        scoreDao.create(u_score6);
        assertNotNull(u_score6.getId());
    }


}
