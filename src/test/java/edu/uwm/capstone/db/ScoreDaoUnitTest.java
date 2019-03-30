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
        Judge judgeU1 = new Judge();
        judgeU1.setJudge_id(1L);
        judgeU1.setRole("judge");
        judgeU1.setEmail("judgeU1@email.com");
        judgeU1.setPin(1234);
        judgeU1.setFirst_name("FName");
        judgeU1.setLast_name("LName");
        judgeU1.setStatus("Undergraduate");
        judgeDao.create(judgeU1);
        assertNotNull(judgeU1.getId());

        Judge judgeU2 = new Judge();
        judgeU2.setJudge_id(2L);
        judgeU2.setRole("judge");
        judgeU2.setEmail("judgeU2@email.com");
        judgeU2.setPin(1234);
        judgeU2.setFirst_name("FName");
        judgeU2.setLast_name("LName");
        judgeU2.setStatus("Undergraduate");
        judgeDao.create(judgeU2);
        assertNotNull(judgeU2.getId());

        Judge judgeU3 = new Judge();
        judgeU3.setJudge_id(3L);
        judgeU3.setRole("judge");
        judgeU3.setEmail("judgeU3@email.com");
        judgeU3.setPin(1234);
        judgeU3.setFirst_name("FName");
        judgeU3.setLast_name("LName");
        judgeU3.setStatus("Undergraduate");
        judgeDao.create(judgeU3);
        assertNotNull(judgeU3.getId());

        Judge judgeU4 = new Judge();
        judgeU4.setJudge_id(4L);
        judgeU4.setRole("judge");
        judgeU4.setEmail("judgeU4@email.com");
        judgeU4.setPin(1234);
        judgeU4.setFirst_name("FName");
        judgeU4.setLast_name("LName");
        judgeU4.setStatus("Undergraduate");
        judgeDao.create(judgeU4);
        assertNotNull(judgeU4.getId());

        Poster posterU01 = new Poster();
        posterU01.setPoster_id("U01");
        posterU01.setPin(1234);
        posterU01.setFirst_name("FNameU01");
        posterU01.setLast_name("LNameU01");
        posterU01.setTitle("This is the Undtergraduate Poster Title");
        posterU01.setStatus("Undergraduate");
        posterU01.setDepartment("Computer Science");
        posterU01.setEmail("posterU01@email.com");
        posterDao.create(posterU01);
        assertNotNull(posterU01.getId());

        Score socreU1 = new Score();
        socreU1.setPoster_id(posterU01.getPoster_id());
        socreU1.setJudge_id(judgeU1.getJudge_id());
        socreU1.setRound(1);
        socreU1.setResearch_score(randomInt(1,50));
        socreU1.setComm_score(randomInt(1,30));
        socreU1.setPoster_score(randomInt(1,20));
        socreU1.setTotal_score(socreU1.getResearch_score()+ socreU1.getComm_score()+socreU1.getPoster_score());
        scoreDao.create(socreU1);
        assertNotNull(socreU1.getId());
//
//        Poster g_poster2 = new Poster();
//        g_poster2.setPoster_id("G11");
//        g_poster2.setPin(1234);
//        g_poster2.setFirst_name("testScoreF2");
//        g_poster2.setLast_name("testScoreL2");
//        g_poster2.setTitle("This is the Graduate Poster Title");
//        g_poster2.setStatus("Graduate");
//        g_poster2.setDepartment("Computer Science");
//        g_poster2.setEmail("gradposterG11@email.com");
//        posterDao.create(g_poster2);
//        assertNotNull(g_poster2.getId());
//
//        Poster g_poster3 = new Poster();
//        g_poster3.setPoster_id("G12");
//        g_poster3.setPin(1234);
//        g_poster3.setFirst_name("testScoreF3");
//        g_poster3.setLast_name("testScoreL3");
//        g_poster3.setTitle("This is the Graduate Poster Title");
//        g_poster3.setStatus("Graduate");
//        g_poster3.setDepartment("Computer Science");
//        g_poster3.setEmail("gradposterG12@email.com");
//        posterDao.create(g_poster3);
//        assertNotNull(g_poster3.getId());
//
//        Score g_score1 = new Score();
//        g_score1.setPoster_id(g_poster1.getPoster_id());
//        g_score1.setJudge_id(g_judge.getId());
//        g_score1.setRound(1);
//        g_score1.setResearch_score(randomInt(1,50));
//        g_score1.setComm_score(randomInt(1,30));
//        g_score1.setPoster_score(randomInt(1,20));
//        g_score1.setTotal_score(g_score1.getResearch_score()+ g_score1.getComm_score()+g_score1.getPoster_score());
//        scoreDao.create(g_score1);
//        assertNotNull(g_score1.getId());
//
//        Score g_score2 = new Score();
//        g_score2.setPoster_id(g_poster2.getPoster_id());
//        g_score2.setJudge_id(g_judge.getId());
//        g_score2.setRound(1);
//        g_score2.setResearch_score(randomInt(1,50));
//        g_score2.setComm_score(randomInt(1,30));
//        g_score2.setPoster_score(randomInt(1,20));
//        g_score2.setTotal_score(g_score2.getResearch_score()+ g_score2.getComm_score()+g_score2.getPoster_score());
//        scoreDao.create(g_score2);
//        assertNotNull(g_score2.getId());
//
//        Score g_score3 = new Score();
//        g_score3.setPoster_id(g_poster3.getPoster_id());
//        g_score3.setJudge_id(g_judge.getId());
//        g_score3.setRound(1);
//        g_score3.setResearch_score(randomInt(1,50));
//        g_score3.setComm_score(randomInt(1,30));
//        g_score3.setPoster_score(randomInt(1,20));
//        g_score3.setTotal_score(g_score3.getResearch_score()+ g_score3.getComm_score()+g_score3.getPoster_score());
//        scoreDao.create(g_score3);
//        assertNotNull(g_score3.getId());
//
//
//        //undergrad stuff
//        Judge u_judge = new Judge();
//        u_judge.setRole("judge");
//        u_judge.setEmail("undergradjudge11@email.com");
//        u_judge.setPin(1234);
//        u_judge.setFirst_name("undergradJudge1");
//        u_judge.setLast_name("undergradJudge1");
//        u_judge.setStatus("Undergraduate");
//        judgeDao.create(u_judge);
//        assertNotNull(u_judge.getId());
//
//        Judge u_judge2 = new Judge();
//        u_judge2.setRole("judge");
//        u_judge2.setEmail("undergradjudge12@email.com");
//        u_judge2.setPin(1234);
//        u_judge2.setFirst_name("undergradJudge2");
//        u_judge2.setLast_name("undergradJudge2");
//        u_judge2.setStatus("Undergraduate");
//        judgeDao.create(u_judge2);
//        assertNotNull(u_judge2.getId());
//
//        Poster u_poster1 = new Poster();
//        u_poster1.setPoster_id("U10");
//        u_poster1.setPin(1234);
//        u_poster1.setFirst_name("testScoreF1");
//        u_poster1.setLast_name("testScoreL1");
//        u_poster1.setTitle("This is the Undergraduate Poster Title");
//        u_poster1.setStatus("Undergraduate");
//        u_poster1.setDepartment("Computer Science");
//        u_poster1.setEmail("undergradposterU10@email.com");
//        posterDao.create(u_poster1);
//        assertNotNull(u_poster1.getId());
//
//        Poster u_poster2 = new Poster();
//        u_poster2.setPoster_id("U11");
//        u_poster2.setPin(1234);
//        u_poster2.setFirst_name("testScoreF2");
//        u_poster2.setLast_name("testScoreL2");
//        u_poster2.setTitle("This is the Undergraduate Poster Title");
//        u_poster2.setStatus("Undergraduate");
//        u_poster2.setDepartment("Computer Science");
//        u_poster2.setEmail("undergradposterU11@email.com");
//        posterDao.create(u_poster2);
//        assertNotNull(u_poster2.getId());
//
//        Poster u_poster3 = new Poster();
//        u_poster3.setPoster_id("U12");
//        u_poster3.setPin(1234);
//        u_poster3.setFirst_name("testScoreF3");
//        u_poster3.setLast_name("testScoreL3");
//        u_poster3.setTitle("This is the Undergraduate Poster Title");
//        u_poster3.setStatus("Undergraduate");
//        u_poster3.setDepartment("Computer Science");
//        u_poster3.setEmail("undergradposterU12@email.com");
//        posterDao.create(u_poster3);
//        assertNotNull(u_poster3.getId());
//
//        Score u_score1 = new Score();
//        u_score1.setPoster_id(u_poster1.getPoster_id());
//        u_score1.setJudge_id(u_judge.getId());
//        u_score1.setRound(1);
//        u_score1.setResearch_score(randomInt(1,50));
//        u_score1.setComm_score(randomInt(1,30));
//        u_score1.setPoster_score(randomInt(1,20));
//        u_score1.setTotal_score(u_score1.getResearch_score()+ u_score1.getComm_score()+u_score1.getPoster_score());
//        scoreDao.create(u_score1);
//        assertNotNull(u_score1.getId());
//
//        Score u_score2 = new Score();
//        u_score2.setPoster_id(u_poster2.getPoster_id());
//        u_score2.setJudge_id(u_judge.getId());
//        u_score2.setRound(1);
//        u_score2.setResearch_score(randomInt(1,50));
//        u_score2.setComm_score(randomInt(1,30));
//        u_score2.setPoster_score(randomInt(1,20));
//        u_score2.setTotal_score(u_score2.getResearch_score()+ u_score2.getComm_score()+u_score2.getPoster_score());
//        scoreDao.create(u_score2);
//        assertNotNull(u_score2.getId());
//
//        Score u_score3 = new Score();
//        u_score3.setPoster_id(u_poster3.getPoster_id());
//        u_score3.setJudge_id(u_judge.getId());
//        u_score3.setRound(1);
//        u_score3.setResearch_score(randomInt(1,50));
//        u_score3.setComm_score(randomInt(1,30));
//        u_score3.setPoster_score(randomInt(1,20));
//        u_score3.setTotal_score(u_score3.getResearch_score()+ u_score3.getComm_score()+u_score3.getPoster_score());
//        scoreDao.create(u_score3);
//        assertNotNull(u_score3.getId());
//
//        Score u_score4 = new Score();
//        u_score4.setPoster_id(u_poster1.getPoster_id());
//        u_score4.setJudge_id(u_judge2.getId());
//        u_score4.setRound(1);
//        u_score4.setResearch_score(randomInt(1,50));
//        u_score4.setComm_score(randomInt(1,30));
//        u_score4.setPoster_score(randomInt(1,20));
//        u_score4.setTotal_score(u_score4.getResearch_score()+ u_score4.getComm_score()+u_score4.getPoster_score());
//        scoreDao.create(u_score4);
//        assertNotNull(u_score4.getId());
//
//        Score u_score5 = new Score();
//        u_score5.setPoster_id(u_poster2.getPoster_id());
//        u_score5.setJudge_id(u_judge2.getId());
//        u_score5.setRound(1);
//        u_score5.setResearch_score(randomInt(1,50));
//        u_score5.setComm_score(randomInt(1,30));
//        u_score5.setPoster_score(randomInt(1,20));
//        u_score5.setTotal_score(u_score5.getResearch_score()+ u_score5.getComm_score()+u_score5.getPoster_score());
//        scoreDao.create(u_score5);
//        assertNotNull(u_score5.getId());
//
//        Score u_score6 = new Score();
//        u_score6.setPoster_id(u_poster3.getPoster_id());
//        u_score6.setJudge_id(u_judge2.getId());
//        u_score6.setRound(1);
//        u_score6.setResearch_score(randomInt(1,50));
//        u_score6.setComm_score(randomInt(1,30));
//        u_score6.setPoster_score(randomInt(1,20));
//        u_score6.setTotal_score(u_score6.getResearch_score()+ u_score6.getComm_score()+u_score6.getPoster_score());
//        scoreDao.create(u_score6);
//        assertNotNull(u_score6.getId());
    }


}
