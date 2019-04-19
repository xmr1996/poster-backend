package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Score.Score;
import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.UnitTestConfig;
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
        assertNotNull(scoreDao.sql("readScoreByRoundAndJudge"));
        assertNotNull(scoreDao.sql("readAllScores"));
        assertNotNull(scoreDao.sql("getScoreByID"));
        assertNotNull(scoreDao.sql("getScoreByPosterID"));
        assertNotNull(scoreDao.sql("getScoreByJudgeID"));
        assertNotNull(scoreDao.sql("getScoreByRound"));
        assertNotNull(scoreDao.sql("updateScore"));
        assertNotNull(scoreDao.sql("deleteScoreByPosterId"));
        assertNotNull(scoreDao.sql("getAllAssignments"));
        assertNotNull(scoreDao.sql("upsertScore"));
        assertNotNull(scoreDao.sql("clearScore"));
        assertNotNull(scoreDao.sql("clearScoreByRound"));
        assertNotNull(scoreDao.sql("readScoreByRound"));
        assertNotNull(scoreDao.sql("deleteScoreByID"));

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
    public void createTestScore() {
        //grad stuff
        Judge judgeU1 = new Judge();
        judgeU1.setJudgeId(1L);
        judgeU1.setRole("judge");
        judgeU1.setEmail("judgeU1@email.com");
        judgeU1.setPin("1234");
        judgeU1.setFirstName("FName");
        judgeU1.setLastName("LName");
        judgeU1.setStatus("Undergraduate");
        judgeDao.create(judgeU1);
        assertNotNull(judgeU1.getJudgeId());

        Judge judgeU2 = new Judge();
        judgeU2.setJudgeId(2L);
        judgeU2.setRole("judge");
        judgeU2.setEmail("judgeU2@email.com");
        judgeU2.setPin("1234");
        judgeU2.setFirstName("FName");
        judgeU2.setLastName("LName");
        judgeU2.setStatus("Undergraduate");
        judgeDao.create(judgeU2);
        assertNotNull(judgeU2.getJudgeId());

        Judge judgeU3 = new Judge();
        judgeU3.setJudgeId(3L);
        judgeU3.setRole("judge");
        judgeU3.setEmail("judgeU3@email.com");
        judgeU3.setPin("1234");
        judgeU3.setFirstName("FName");
        judgeU3.setLastName("LName");
        judgeU3.setStatus("Undergraduate");
        judgeDao.create(judgeU3);
        assertNotNull(judgeU3.getJudgeId());

        Judge judgeU4 = new Judge();
        judgeU4.setJudgeId(4L);
        judgeU4.setRole("judge");
        judgeU4.setEmail("judgeU4@email.com");
        judgeU4.setPin("1234");
        judgeU4.setFirstName("FName");
        judgeU4.setLastName("LName");
        judgeU4.setStatus("Undergraduate");
        judgeDao.create(judgeU4);
        assertNotNull(judgeU4.getJudgeId());

        Judge judgeU5 = new Judge();
        judgeU5.setJudgeId(5L);
        judgeU5.setRole("judge");
        judgeU5.setEmail("judgeU5@email.com");
        judgeU5.setPin("1234");
        judgeU5.setFirstName("FName");
        judgeU5.setLastName("LName");
        judgeU5.setStatus("Undergraduate");
        judgeDao.create(judgeU5);
        assertNotNull(judgeU5.getJudgeId());

        Judge judgeU6 = new Judge();
        judgeU6.setJudgeId(6L);
        judgeU6.setRole("judge");
        judgeU6.setEmail("judgeU6@email.com");
        judgeU6.setPin("1234");
        judgeU6.setFirstName("FName");
        judgeU6.setLastName("LName");
        judgeU6.setStatus("Undergraduate");
        judgeDao.create(judgeU6);
        assertNotNull(judgeU6.getJudgeId());

        Judge judgeU7 = new Judge();
        judgeU7.setJudgeId(7L);
        judgeU7.setRole("judge");
        judgeU7.setEmail("judgeU7@email.com");
        judgeU7.setPin("1234");
        judgeU7.setFirstName("FName");
        judgeU7.setLastName("LName");
        judgeU7.setStatus("Undergraduate");
        judgeDao.create(judgeU7);
        assertNotNull(judgeU7.getJudgeId());

        Judge judgeU8 = new Judge();
        judgeU8.setJudgeId(8L);
        judgeU8.setRole("judge");
        judgeU8.setEmail("judgeU8@email.com");
        judgeU8.setPin("1234");
        judgeU8.setFirstName("FName");
        judgeU8.setLastName("LName");
        judgeU8.setStatus("Undergraduate");
        judgeDao.create(judgeU8);
        assertNotNull(judgeU8.getJudgeId());

        Judge judgeU9 = new Judge();
        judgeU9.setJudgeId(9L);
        judgeU9.setRole("judge");
        judgeU9.setEmail("judgeU9@email.com");
        judgeU9.setPin("1234");
        judgeU9.setFirstName("FName");
        judgeU9.setLastName("LName");
        judgeU9.setStatus("Undergraduate");
        judgeDao.create(judgeU9);
        assertNotNull(judgeU9.getJudgeId());

        Judge judgeU10 = new Judge();
        judgeU10.setJudgeId(10L);
        judgeU10.setRole("judge");
        judgeU10.setEmail("judgeU10@email.com");
        judgeU10.setPin("1234");
        judgeU10.setFirstName("FName");
        judgeU10.setLastName("LName");
        judgeU10.setStatus("Undergraduate");
        judgeDao.create(judgeU10);
        assertNotNull(judgeU10.getJudgeId());

        Judge judgeG11 = new Judge();
        judgeG11.setJudgeId(11L);
        judgeG11.setRole("judge");
        judgeG11.setEmail("judgeG11@email.com");
        judgeG11.setPin("5678");
        judgeG11.setFirstName("FName");
        judgeG11.setLastName("LName");
        judgeG11.setStatus("Graduate");
        judgeDao.create(judgeG11);
        assertNotNull(judgeG11.getJudgeId());

        Judge judgeG12 = new Judge();
        judgeG12.setJudgeId(12L);
        judgeG12.setRole("judge");
        judgeG12.setEmail("judgeG12@email.com");
        judgeG12.setPin("5678");
        judgeG12.setFirstName("FName");
        judgeG12.setLastName("LName");
        judgeG12.setStatus("Graduate");
        judgeDao.create(judgeG12);
        assertNotNull(judgeG12.getJudgeId());

        Judge judgeG13 = new Judge();
        judgeG13.setJudgeId(13L);
        judgeG13.setRole("judge");
        judgeG13.setEmail("judgeG13@email.com");
        judgeG13.setPin("5678");
        judgeG13.setFirstName("FName");
        judgeG13.setLastName("LName");
        judgeG13.setStatus("Graduate");
        judgeDao.create(judgeG13);
        assertNotNull(judgeG13.getJudgeId());

        Judge judgeG14 = new Judge();
        judgeG14.setJudgeId(14L);
        judgeG14.setRole("judge");
        judgeG14.setEmail("judgeG14@email.com");
        judgeG14.setPin("5678");
        judgeG14.setFirstName("FName");
        judgeG14.setLastName("LName");
        judgeG14.setStatus("Graduate");
        judgeDao.create(judgeG14);
        assertNotNull(judgeG14.getJudgeId());

        Judge judgeG15 = new Judge();
        judgeG15.setJudgeId(15L);
        judgeG15.setRole("judge");
        judgeG15.setEmail("judgeG15@email.com");
        judgeG15.setPin("5678");
        judgeG15.setFirstName("FName");
        judgeG15.setLastName("LName");
        judgeG15.setStatus("Graduate");
        judgeDao.create(judgeG15);
        assertNotNull(judgeG15.getJudgeId());

        Judge judgeG16 = new Judge();
        judgeG16.setJudgeId(16L);
        judgeG16.setRole("judge");
        judgeG16.setEmail("judgeG16@email.com");
        judgeG16.setPin("5678");
        judgeG16.setFirstName("FName");
        judgeG16.setLastName("LName");
        judgeG16.setStatus("Graduate");
        judgeDao.create(judgeG16);
        assertNotNull(judgeG16.getJudgeId());

        Judge judgeG17 = new Judge();
        judgeG17.setJudgeId(17L);
        judgeG17.setRole("judge");
        judgeG17.setEmail("judgeG17@email.com");
        judgeG17.setPin("5678");
        judgeG17.setFirstName("FName");
        judgeG17.setLastName("LName");
        judgeG17.setStatus("Graduate");
        judgeDao.create(judgeG17);
        assertNotNull(judgeG17.getJudgeId());

        Judge judgeG18 = new Judge();
        judgeG18.setJudgeId(18L);
        judgeG18.setRole("judge");
        judgeG18.setEmail("judgeG18@email.com");
        judgeG18.setPin("5678");
        judgeG18.setFirstName("FName");
        judgeG18.setLastName("LName");
        judgeG18.setStatus("Graduate");
        judgeDao.create(judgeG18);
        assertNotNull(judgeG18.getJudgeId());

        Judge judgeG19 = new Judge();
        judgeG19.setJudgeId(19L);
        judgeG19.setRole("judge");
        judgeG19.setEmail("judgeG19@email.com");
        judgeG19.setPin("5678");
        judgeG19.setFirstName("FName");
        judgeG19.setLastName("LName");
        judgeG19.setStatus("Graduate");
        judgeDao.create(judgeG19);
        assertNotNull(judgeG19.getJudgeId());

        Judge judgeG20 = new Judge();
        judgeG20.setJudgeId(20L);
        judgeG20.setRole("judge");
        judgeG20.setEmail("judgeG20@email.com");
        judgeG20.setPin("5678");
        judgeG20.setFirstName("FName");
        judgeG20.setLastName("LName");
        judgeG20.setStatus("Graduate");
        judgeDao.create(judgeG20);
        assertNotNull(judgeG20.getJudgeId());



        Poster posterU01 = new Poster();
        posterU01.setPosterId("U01");
        posterU01.setPin("1234");
        posterU01.setRole("student");
        posterU01.setFirstName("FName U01");
        posterU01.setLastName("LName U01");
        posterU01.setTitle("Undergraduate U01");
        posterU01.setStatus("Undergraduate");
        posterU01.setDepartment("Computer Science");
        posterU01.setEmail("posterU01@email.com");
        posterDao.create(posterU01);
        assertNotNull(posterU01.getPosterId());

        Poster U02 = new Poster();
        U02.setPosterId("U02");
        U02.setPin("1234");
        U02.setRole("student");
        U02.setFirstName("FName U02");
        U02.setLastName("LName U02");
        U02.setTitle("Undergraduate U02");
        U02.setStatus("Undergraduate");
        U02.setDepartment("Computer Science");
        U02.setEmail("posterU02@email.com");
        posterDao.create(U02);
        assertNotNull(U02.getPosterId());

        Poster U03 = new Poster();
        U03.setPosterId("U03");
        U03.setPin("1234");
        U03.setRole("student");
        U03.setFirstName("FName U03");
        U03.setLastName("LName U03");
        U03.setTitle("Undergraduate U03");
        U03.setStatus("Undergraduate");
        U03.setDepartment("Computer Science");
        U03.setEmail("posterU03@email.com");
        posterDao.create(U03);
        assertNotNull(U03.getPosterId());

        Poster U04 = new Poster();
        U04.setPosterId("U04");
        U04.setPin("1234");
        U04.setRole("student");
        U04.setFirstName("FName U04");
        U04.setLastName("LName U04");
        U04.setTitle("Undergraduate U04");
        U04.setStatus("Undergraduate");
        U04.setDepartment("Computer Science");
        U04.setEmail("posterU04@email.com");
        posterDao.create(U04);
        assertNotNull(U04.getPosterId());

        Poster U05 = new Poster();
        U05.setPosterId("U05");
        U05.setPin("1234");
        U05.setRole("student");
        U05.setFirstName("FName U05");
        U05.setLastName("LName U05");
        U05.setTitle("Undergraduate U05");
        U05.setStatus("Undergraduate");
        U05.setDepartment("Computer Science");
        U05.setEmail("posterU05@email.com");
        posterDao.create(U05);
        assertNotNull(U05.getPosterId());

        Poster U06 = new Poster();
        U06.setPosterId("U06");
        U06.setPin("1234");
        U06.setRole("student");
        U06.setFirstName("FName U06");
        U06.setLastName("LName U06");
        U06.setTitle("Undergraduate U06");
        U06.setStatus("Undergraduate");
        U06.setDepartment("Computer Science");
        U06.setEmail("posterU06@email.com");
        posterDao.create(U06);
        assertNotNull(U06.getPosterId());

        Poster U07 = new Poster();
        U07.setPosterId("U07");
        U07.setPin("1234");
        U07.setRole("student");
        U07.setFirstName("FName U07");
        U07.setLastName("LName U07");
        U07.setTitle("Undergraduate U07");
        U07.setStatus("Undergraduate");
        U07.setDepartment("Computer Science");
        U07.setEmail("posterU07@email.com");
        posterDao.create(U07);
        assertNotNull(U07.getPosterId());

        Poster U08 = new Poster();
        U08.setPosterId("U08");
        U08.setPin("1234");
        U08.setRole("student");
        U08.setFirstName("FName U08");
        U08.setLastName("LName U08");
        U08.setTitle("Undergraduate U08");
        U08.setStatus("Undergraduate");
        U08.setDepartment("Computer Science");
        U08.setEmail("posterU08@email.com");
        posterDao.create(U08);
        assertNotNull(U08.getPosterId());

        Poster U09 = new Poster();
        U09.setPosterId("U09");
        U09.setPin("1234");
        U09.setRole("student");
        U09.setFirstName("FName U09");
        U09.setLastName("LName U09");
        U09.setTitle("Undergraduate U09");
        U09.setStatus("Undergraduate");
        U09.setDepartment("Computer Science");
        U09.setEmail("posterU09@email.com");
        posterDao.create(U09);
        assertNotNull(U09.getPosterId());

        Poster U10 = new Poster();
        U10.setPosterId("U10");
        U10.setPin("1234");
        U10.setRole("student");
        U10.setFirstName("FName U10");
        U10.setLastName("LName U10");
        U10.setTitle("Undergraduate U10");
        U10.setStatus("Undergraduate");
        U10.setDepartment("Computer Science");
        U10.setEmail("posterU10@email.com");
        posterDao.create(U10);
        assertNotNull(U10.getPosterId());

        Poster G01 = new Poster();
        G01.setPosterId("G01");
        G01.setPin("5678");
        G01.setRole("student");
        G01.setFirstName("FName G01");
        G01.setLastName("LName G01");
        G01.setTitle("Graduate G01");
        G01.setStatus("Graduate");
        G01.setDepartment("Computer Science");
        G01.setEmail("posterG01@email.com");
        posterDao.create(G01);
        assertNotNull(G01.getPosterId());

        Poster G02 = new Poster();
        G02.setPosterId("G02");
        G02.setPin("5678");
        G02.setRole("student");
        G02.setFirstName("FName G02");
        G02.setLastName("LName G02");
        G02.setTitle("Graduate G02");
        G02.setStatus("Graduate");
        G02.setDepartment("Computer Science");
        G02.setEmail("posterG02@email.com");
        posterDao.create(G02);
        assertNotNull(G02.getPosterId());

        Poster G03 = new Poster();
        G03.setPosterId("G03");
        G03.setPin("5678");
        G03.setRole("student");
        G03.setFirstName("FName G03");
        G03.setLastName("LName G03");
        G03.setTitle("Graduate G03");
        G03.setStatus("Graduate");
        G03.setDepartment("Computer Science");
        G03.setEmail("posterG03@email.com");
        posterDao.create(G03);
        assertNotNull(G03.getPosterId());

        Poster G04 = new Poster();
        G04.setPosterId("G04");
        G04.setPin("5678");
        G04.setRole("student");
        G04.setFirstName("FName G04");
        G04.setLastName("LName G04");
        G04.setTitle("Graduate G04");
        G04.setStatus("Graduate");
        G04.setDepartment("Computer Science");
        G04.setEmail("posterG04@email.com");
        posterDao.create(G04);
        assertNotNull(G04.getPosterId());

        Poster G05 = new Poster();
        G05.setPosterId("G05");
        G05.setPin("5678");
        G05.setRole("student");
        G05.setFirstName("FName G05");
        G05.setLastName("LName G05");
        G05.setTitle("Graduate G05");
        G05.setStatus("Graduate");
        G05.setDepartment("Computer Science");
        G05.setEmail("posterG05@email.com");
        posterDao.create(G05);
        assertNotNull(G05.getPosterId());

        Poster G06 = new Poster();
        G06.setPosterId("G06");
        G06.setPin("5678");
        G06.setRole("student");
        G06.setFirstName("FName G06");
        G06.setLastName("LName G06");
        G06.setTitle("Graduate G06");
        G06.setStatus("Graduate");
        G06.setDepartment("Computer Science");
        G06.setEmail("posterG06@email.com");
        posterDao.create(G06);
        assertNotNull(G06.getPosterId());

        Poster G07 = new Poster();
        G07.setPosterId("G07");
        G07.setPin("5678");
        G07.setRole("student");
        G07.setFirstName("FName G07");
        G07.setLastName("LName G07");
        G07.setTitle("Graduate G07");
        G07.setStatus("Graduate");
        G07.setDepartment("Computer Science");
        G07.setEmail("posterG07@email.com");
        posterDao.create(G07);
        assertNotNull(G07.getPosterId());

        Poster G08 = new Poster();
        G08.setPosterId("G08");
        G08.setPin("5678");
        G08.setRole("student");
        G08.setFirstName("FName G08");
        G08.setLastName("LName G08");
        G08.setTitle("Graduate G08");
        G08.setStatus("Graduate");
        G08.setDepartment("Computer Science");
        G08.setEmail("posterG08@email.com");
        posterDao.create(G08);
        assertNotNull(G08.getPosterId());

        Poster G09 = new Poster();
        G09.setPosterId("G09");
        G09.setPin("5678");
        G09.setRole("student");
        G09.setFirstName("FName G09");
        G09.setLastName("LName G09");
        G09.setTitle("Graduate G09");
        G09.setStatus("Graduate");
        G09.setDepartment("Computer Science");
        G09.setEmail("posterG09@email.com");
        posterDao.create(G09);
        assertNotNull(G09.getPosterId());

        Poster G10 = new Poster();
        G10.setPosterId("G10");
        G10.setPin("5678");
        G10.setRole("student");
        G10.setFirstName("FName G10");
        G10.setLastName("LName G10");
        G10.setTitle("Graduate G10");
        G10.setStatus("Graduate");
        G10.setDepartment("Computer Science");
        G10.setEmail("posterG10@email.com");
        posterDao.create(G10);
        assertNotNull(G10.getPosterId());


        Score s1 = new Score();
        s1.setPoster_id(posterU01.getPosterId());
        s1.setJudge_id(judgeU1.getJudgeId());
        s1.setRound(1);
//        s1.setPoster_score(20);
//        s1.setResearch_score(20);
//        s1.setComm_score(30);
//        s1.setTotal_score(100);
        scoreDao.create(s1);

        Score s2 = new Score();
        s2.setPoster_id(posterU01.getPosterId());
        s2.setJudge_id(judgeU2.getJudgeId());
        s2.setRound(1);
//        s2.setRound(2);
//        s2.setPoster_score(20);
//        s2.setResearch_score(20);
//        s2.setComm_score(30);
//        s2.setTotal_score(100);
        scoreDao.create(s2);

        Score s3 = new Score();
        s3.setPoster_id(posterU01.getPosterId());
        s3.setJudge_id(judgeU3.getJudgeId());
        s3.setRound(1);
        scoreDao.create(s3);

        Score s4 = new Score();
        s4.setPoster_id(posterU01.getPosterId());
        s4.setJudge_id(judgeU4.getJudgeId());
        s4.setRound(1);
        scoreDao.create(s4);

        Score s5 = new Score();
        s5.setPoster_id(U02.getPosterId());
        s5.setJudge_id(judgeU5.getJudgeId());
        s5.setRound(1);
//        s5.setRound(2);
//        s5.setPoster_score(15);
//        s5.setResearch_score(20);
//        s5.setComm_score(30);
//        s5.setTotal_score(100);
        scoreDao.create(s5);

        Score s6 = new Score();
        s6.setPoster_id(U02.getPosterId());
        s6.setJudge_id(judgeU6.getJudgeId());
        s6.setRound(1);
//        s6.setRound(2);
//        s6.setPoster_score(20);
//        s6.setResearch_score(20);
//        s6.setComm_score(50);
//        s6.setTotal_score(100);
//        scoreDao.create(s6);

        Score s7 = new Score();
        s7.setPoster_id(U02.getPosterId());
        s7.setJudge_id(judgeU7.getJudgeId());
        s7.setRound(1);
        scoreDao.create(s7);

        Score s8 = new Score();
        s8.setPoster_id(U03.getPosterId());
        s8.setJudge_id(judgeU8.getJudgeId());
        s8.setRound(1);
        scoreDao.create(s8);

        Score s9 = new Score();
        s9.setPoster_id(U03.getPosterId());
        s9.setJudge_id(judgeU9.getJudgeId());
        s9.setRound(1);
        scoreDao.create(s9);

        Score s10 = new Score();
        s10.setPoster_id(U03.getPosterId());
        s10.setJudge_id(judgeU10.getJudgeId());
        s10.setRound(1);
        scoreDao.create(s10);

        Score s11 = new Score();
        s11.setPoster_id(U04.getPosterId());
        s11.setJudge_id(judgeU1.getJudgeId());
        s11.setRound(1);
        scoreDao.create(s11);

        Score s12 = new Score();
        s12.setPoster_id(U04.getPosterId());
        s12.setJudge_id(judgeU2.getJudgeId());
        s12.setRound(1);
        scoreDao.create(s12);

        Score s13 = new Score();
        s13.setPoster_id(U04.getPosterId());
        s13.setJudge_id(judgeU3.getJudgeId());
        s13.setRound(1);
        scoreDao.create(s13);

        Score s14 = new Score();
        s14.setPoster_id(U05.getPosterId());
        s14.setJudge_id(judgeU4.getJudgeId());
        s14.setRound(1);
        scoreDao.create(s14);

        Score s15 = new Score();
        s15.setPoster_id(U05.getPosterId());
        s15.setJudge_id(judgeU5.getJudgeId());
        s15.setRound(1);
        scoreDao.create(s15);

        Score s16 = new Score();
        s16.setPoster_id(U05.getPosterId());
        s16.setJudge_id(judgeU6.getJudgeId());
        s16.setRound(1);
        scoreDao.create(s16);

        Score s17 = new Score();
        s17.setPoster_id(U06.getPosterId());
        s17.setJudge_id(judgeU7.getJudgeId());
        s17.setRound(1);
        scoreDao.create(s17);

        Score s18 = new Score();
        s18.setPoster_id(U06.getPosterId());
        s18.setJudge_id(judgeU8.getJudgeId());
        s18.setRound(1);
        scoreDao.create(s18);

        Score s19 = new Score();
        s19.setPoster_id(U06.getPosterId());
        s19.setJudge_id(judgeU9.getJudgeId());
        s19.setRound(1);
        scoreDao.create(s19);

        Score s20 = new Score();
        s20.setPoster_id(U07.getPosterId());
        s20.setJudge_id(judgeU9.getJudgeId());
        s20.setRound(1);
        scoreDao.create(s20);

        Score s21 = new Score();
        s21.setPoster_id(U07.getPosterId());
        s21.setJudge_id(judgeU1.getJudgeId());
        s21.setRound(1);
        scoreDao.create(s21);

        Score s22 = new Score();
        s22.setPoster_id(U07.getPosterId());
        s22.setJudge_id(judgeU2.getJudgeId());
        s22.setRound(1);
        scoreDao.create(s22);

        Score s24 = new Score();
        s24.setPoster_id(U08.getPosterId());
        s24.setJudge_id(judgeU3.getJudgeId());
        s24.setRound(1);
        scoreDao.create(s24);

        Score s25 = new Score();
        s25.setPoster_id(U08.getPosterId());
        s25.setJudge_id(judgeU4.getJudgeId());
        s25.setRound(1);
        scoreDao.create(s25);

        Score s26 = new Score();
        s26.setPoster_id(U08.getPosterId());
        s26.setJudge_id(judgeU5.getJudgeId());
        s26.setRound(1);
        scoreDao.create(s26);

        Score s27 = new Score();
        s27.setPoster_id(U09.getPosterId());
        s27.setJudge_id(judgeU6.getJudgeId());
        s27.setRound(1);
        scoreDao.create(s27);

        Score s28 = new Score();
        s28.setPoster_id(U09.getPosterId());
        s28.setJudge_id(judgeU9.getJudgeId());
        s28.setRound(1);
        scoreDao.create(s28);

        Score s29 = new Score();
        s29.setPoster_id(U10.getPosterId());
        s29.setJudge_id(judgeU10.getJudgeId());
        s29.setRound(1);
        scoreDao.create(s29);

        Score s30 = new Score();
        s30.setPoster_id(G01.getPosterId());
        s30.setJudge_id(judgeG11.getJudgeId());
        s30.setRound(1);
        scoreDao.create(s30);


        Score s31 = new Score();
        s31.setPoster_id(G02.getPosterId());
        s31.setJudge_id(judgeG12.getJudgeId());
        s31.setRound(1);
        scoreDao.create(s31);

        Score s32 = new Score();
        s32.setPoster_id(G02.getPosterId());
        s32.setJudge_id(judgeG13.getJudgeId());
        s32.setRound(1);
        scoreDao.create(s32);

        Score s33 = new Score();
        s33.setPoster_id(G03.getPosterId());
        s33.setJudge_id(judgeG14.getJudgeId());
        s33.setRound(1);
        scoreDao.create(s33);

        Score s34 = new Score();
        s34.setPoster_id(G03.getPosterId());
        s34.setJudge_id(judgeG15.getJudgeId());
        s34.setRound(1);
        scoreDao.create(s34);

        Score s35 = new Score();
        s35.setPoster_id(G03.getPosterId());
        s35.setJudge_id(judgeG16.getJudgeId());
        s35.setRound(1);
        scoreDao.create(s35);

        Score s36 = new Score();
        s36.setPoster_id(G04.getPosterId());
        s36.setJudge_id(judgeG17.getJudgeId());
        s36.setRound(1);
        scoreDao.create(s36);

        Score s37 = new Score();
        s37.setPoster_id(G04.getPosterId());
        s37.setJudge_id(judgeG18.getJudgeId());
        s37.setRound(1);
        scoreDao.create(s37);

        Score s38 = new Score();
        s38.setPoster_id(G04.getPosterId());
        s38.setJudge_id(judgeG19.getJudgeId());
        s38.setRound(1);
        scoreDao.create(s38);

        Score s39 = new Score();
        s39.setPoster_id(G04.getPosterId());
        s39.setJudge_id(judgeG20.getJudgeId());
        s39.setRound(1);
        scoreDao.create(s39);

        Score s40 = new Score();
        s40.setPoster_id(G05.getPosterId());
        s40.setJudge_id(judgeG12.getJudgeId());
        s40.setRound(1);
        scoreDao.create(s40);

        Score s41 = new Score();
        s41.setPoster_id(G05.getPosterId());
        s41.setJudge_id(judgeG13.getJudgeId());
        s41.setRound(1);
        scoreDao.create(s41);

        Score s42 = new Score();
        s42.setPoster_id(G05.getPosterId());
        s42.setJudge_id(judgeG14.getJudgeId());
        s42.setRound(1);
        scoreDao.create(s42);

        Score s43 = new Score();
        s43.setPoster_id(G06.getPosterId());
        s43.setJudge_id(judgeG15.getJudgeId());
        s43.setRound(1);
        scoreDao.create(s43);

        Score s44 = new Score();
        s44.setPoster_id(G06.getPosterId());
        s44.setJudge_id(judgeG16.getJudgeId());
        s44.setRound(1);
        scoreDao.create(s44);

        Score s45 = new Score();
        s45.setPoster_id(G06.getPosterId());
        s45.setJudge_id(judgeG17.getJudgeId());
        s45.setRound(1);
        scoreDao.create(s45);

        Score s46 = new Score();
        s46.setPoster_id(G07.getPosterId());
        s46.setJudge_id(judgeG18.getJudgeId());
        s46.setRound(1);
        scoreDao.create(s46);

        Score s47 = new Score();
        s47.setPoster_id(G07.getPosterId());
        s47.setJudge_id(judgeG12.getJudgeId());
        s47.setRound(1);
        scoreDao.create(s47);

        Score s48 = new Score();
        s48.setPoster_id(G07.getPosterId());
        s48.setJudge_id(judgeG13.getJudgeId());
        s48.setRound(1);
        scoreDao.create(s48);

        Score s49 = new Score();
        s49.setPoster_id(G08.getPosterId());
        s49.setJudge_id(judgeG14.getJudgeId());
        s49.setRound(1);
        scoreDao.create(s49);

        Score s50 = new Score();
        s50.setPoster_id(G08.getPosterId());
        s50.setJudge_id(judgeG15.getJudgeId());
        s50.setRound(1);
        scoreDao.create(s50);

        Score s51 = new Score();
        s51.setPoster_id(G08.getPosterId());
        s51.setJudge_id(judgeG16.getJudgeId());
        s51.setRound(1);
        scoreDao.create(s51);

        Score s52 = new Score();
        s52.setPoster_id(G09.getPosterId());
        s52.setJudge_id(judgeG17.getJudgeId());
        s52.setRound(1);
        scoreDao.create(s52);

        Score s53 = new Score();
        s53.setPoster_id(G09.getPosterId());
        s53.setJudge_id(judgeG18.getJudgeId());
        s53.setRound(1);
        scoreDao.create(s53);

        Score s54 = new Score();
        s54.setPoster_id(G09.getPosterId());
        s54.setJudge_id(judgeG19.getJudgeId());
        s54.setRound(1);
        scoreDao.create(s54);

        Score s55 = new Score();
        s55.setPoster_id(G10.getPosterId());
        s55.setJudge_id(judgeG20.getJudgeId());
        s55.setRound(1);
        scoreDao.create(s55);

        Score s56 = new Score();
        s56.setPoster_id(G10.getPosterId());
        s56.setJudge_id(judgeG12.getJudgeId());
        s56.setRound(1);
        scoreDao.create(s56);

        Score s57 = new Score();
        s57.setPoster_id(G10.getPosterId());
        s57.setJudge_id(judgeG13.getJudgeId());
        s57.setRound(1);
        scoreDao.create(s57);





    }


}
