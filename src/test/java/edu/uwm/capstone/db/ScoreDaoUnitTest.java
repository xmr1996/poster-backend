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
        assertNotNull(scoreDao.sql("updateScore"));
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
        judgeU1.setJudge_id(1L);
        judgeU1.setRole("judge");
        judgeU1.setEmail("judgeU1@email.com");
        judgeU1.setPin("1234");
        judgeU1.setFirst_name("FName");
        judgeU1.setLast_name("LName");
        judgeU1.setStatus("Undergraduate");
        judgeDao.create(judgeU1);
        assertNotNull(judgeU1.getJudge_id());

        Judge judgeU2 = new Judge();
        judgeU2.setJudge_id(2L);
        judgeU2.setRole("judge");
        judgeU2.setEmail("judgeU2@email.com");
        judgeU2.setPin("1234");
        judgeU2.setFirst_name("FName");
        judgeU2.setLast_name("LName");
        judgeU2.setStatus("Undergraduate");
        judgeDao.create(judgeU2);
        assertNotNull(judgeU2.getJudge_id());

        Judge judgeU3 = new Judge();
        judgeU3.setJudge_id(3L);
        judgeU3.setRole("judge");
        judgeU3.setEmail("judgeU3@email.com");
        judgeU3.setPin("1234");
        judgeU3.setFirst_name("FName");
        judgeU3.setLast_name("LName");
        judgeU3.setStatus("Undergraduate");
        judgeDao.create(judgeU3);
        assertNotNull(judgeU3.getJudge_id());

        Judge judgeU4 = new Judge();
        judgeU4.setJudge_id(4L);
        judgeU4.setRole("judge");
        judgeU4.setEmail("judgeU4@email.com");
        judgeU4.setPin("1234");
        judgeU4.setFirst_name("FName");
        judgeU4.setLast_name("LName");
        judgeU4.setStatus("Undergraduate");
        judgeDao.create(judgeU4);
        assertNotNull(judgeU4.getJudge_id());

        Judge judgeU5 = new Judge();
        judgeU5.setJudge_id(5L);
        judgeU5.setRole("judge");
        judgeU5.setEmail("judgeU5@email.com");
        judgeU5.setPin("1234");
        judgeU5.setFirst_name("FName");
        judgeU5.setLast_name("LName");
        judgeU5.setStatus("Undergraduate");
        judgeDao.create(judgeU5);
        assertNotNull(judgeU5.getJudge_id());

        Judge judgeU6 = new Judge();
        judgeU6.setJudge_id(6L);
        judgeU6.setRole("judge");
        judgeU6.setEmail("judgeU6@email.com");
        judgeU6.setPin("1234");
        judgeU6.setFirst_name("FName");
        judgeU6.setLast_name("LName");
        judgeU6.setStatus("Undergraduate");
        judgeDao.create(judgeU6);
        assertNotNull(judgeU6.getJudge_id());

        Judge judgeU7 = new Judge();
        judgeU7.setJudge_id(7L);
        judgeU7.setRole("judge");
        judgeU7.setEmail("judgeU7@email.com");
        judgeU7.setPin("1234");
        judgeU7.setFirst_name("FName");
        judgeU7.setLast_name("LName");
        judgeU7.setStatus("Undergraduate");
        judgeDao.create(judgeU7);
        assertNotNull(judgeU7.getJudge_id());

        Judge judgeU8 = new Judge();
        judgeU8.setJudge_id(8L);
        judgeU8.setRole("judge");
        judgeU8.setEmail("judgeU8@email.com");
        judgeU8.setPin("1234");
        judgeU8.setFirst_name("FName");
        judgeU8.setLast_name("LName");
        judgeU8.setStatus("Undergraduate");
        judgeDao.create(judgeU8);
        assertNotNull(judgeU8.getJudge_id());

        Judge judgeU9 = new Judge();
        judgeU9.setJudge_id(9L);
        judgeU9.setRole("judge");
        judgeU9.setEmail("judgeU9@email.com");
        judgeU9.setPin("1234");
        judgeU9.setFirst_name("FName");
        judgeU9.setLast_name("LName");
        judgeU9.setStatus("Undergraduate");
        judgeDao.create(judgeU9);
        assertNotNull(judgeU9.getJudge_id());

        Judge judgeU10 = new Judge();
        judgeU10.setJudge_id(10L);
        judgeU10.setRole("judge");
        judgeU10.setEmail("judgeU10@email.com");
        judgeU10.setPin("1234");
        judgeU10.setFirst_name("FName");
        judgeU10.setLast_name("LName");
        judgeU10.setStatus("Undergraduate");
        judgeDao.create(judgeU10);
        assertNotNull(judgeU10.getJudge_id());

        Judge judgeG11 = new Judge();
        judgeG11.setJudge_id(11L);
        judgeG11.setRole("judge");
        judgeG11.setEmail("judgeG11@email.com");
        judgeG11.setPin("5678");
        judgeG11.setFirst_name("FName");
        judgeG11.setLast_name("LName");
        judgeG11.setStatus("Graduate");
        judgeDao.create(judgeG11);
        assertNotNull(judgeG11.getJudge_id());

        Judge judgeG12 = new Judge();
        judgeG12.setJudge_id(12L);
        judgeG12.setRole("judge");
        judgeG12.setEmail("judgeG12@email.com");
        judgeG12.setPin("5678");
        judgeG12.setFirst_name("FName");
        judgeG12.setLast_name("LName");
        judgeG12.setStatus("Graduate");
        judgeDao.create(judgeG12);
        assertNotNull(judgeG12.getJudge_id());

        Judge judgeG13 = new Judge();
        judgeG13.setJudge_id(13L);
        judgeG13.setRole("judge");
        judgeG13.setEmail("judgeG13@email.com");
        judgeG13.setPin("5678");
        judgeG13.setFirst_name("FName");
        judgeG13.setLast_name("LName");
        judgeG13.setStatus("Graduate");
        judgeDao.create(judgeG13);
        assertNotNull(judgeG13.getJudge_id());

        Judge judgeG14 = new Judge();
        judgeG14.setJudge_id(14L);
        judgeG14.setRole("judge");
        judgeG14.setEmail("judgeG14@email.com");
        judgeG14.setPin("5678");
        judgeG14.setFirst_name("FName");
        judgeG14.setLast_name("LName");
        judgeG14.setStatus("Graduate");
        judgeDao.create(judgeG14);
        assertNotNull(judgeG14.getJudge_id());

        Judge judgeG15 = new Judge();
        judgeG15.setJudge_id(15L);
        judgeG15.setRole("judge");
        judgeG15.setEmail("judgeG15@email.com");
        judgeG15.setPin("5678");
        judgeG15.setFirst_name("FName");
        judgeG15.setLast_name("LName");
        judgeG15.setStatus("Graduate");
        judgeDao.create(judgeG15);
        assertNotNull(judgeG15.getJudge_id());

        Judge judgeG16 = new Judge();
        judgeG16.setJudge_id(16L);
        judgeG16.setRole("judge");
        judgeG16.setEmail("judgeG16@email.com");
        judgeG16.setPin("5678");
        judgeG16.setFirst_name("FName");
        judgeG16.setLast_name("LName");
        judgeG16.setStatus("Graduate");
        judgeDao.create(judgeG16);
        assertNotNull(judgeG16.getJudge_id());

        Judge judgeG17 = new Judge();
        judgeG17.setJudge_id(17L);
        judgeG17.setRole("judge");
        judgeG17.setEmail("judgeG17@email.com");
        judgeG17.setPin("5678");
        judgeG17.setFirst_name("FName");
        judgeG17.setLast_name("LName");
        judgeG17.setStatus("Graduate");
        judgeDao.create(judgeG17);
        assertNotNull(judgeG17.getJudge_id());

        Judge judgeG18 = new Judge();
        judgeG18.setJudge_id(18L);
        judgeG18.setRole("judge");
        judgeG18.setEmail("judgeG18@email.com");
        judgeG18.setPin("5678");
        judgeG18.setFirst_name("FName");
        judgeG18.setLast_name("LName");
        judgeG18.setStatus("Graduate");
        judgeDao.create(judgeG18);
        assertNotNull(judgeG18.getJudge_id());

        Judge judgeG19 = new Judge();
        judgeG19.setJudge_id(19L);
        judgeG19.setRole("judge");
        judgeG19.setEmail("judgeG19@email.com");
        judgeG19.setPin("5678");
        judgeG19.setFirst_name("FName");
        judgeG19.setLast_name("LName");
        judgeG19.setStatus("Graduate");
        judgeDao.create(judgeG19);
        assertNotNull(judgeG19.getJudge_id());

        Judge judgeG20 = new Judge();
        judgeG20.setJudge_id(20L);
        judgeG20.setRole("judge");
        judgeG20.setEmail("judgeG20@email.com");
        judgeG20.setPin("5678");
        judgeG20.setFirst_name("FName");
        judgeG20.setLast_name("LName");
        judgeG20.setStatus("Graduate");
        judgeDao.create(judgeG20);
        assertNotNull(judgeG20.getJudge_id());



        Poster posterU01 = new Poster();
        posterU01.setPoster_id("U01");
        posterU01.setPin("1234");
        posterU01.setRole("student");
        posterU01.setFirst_name("FName U01");
        posterU01.setLast_name("LName U01");
        posterU01.setTitle("Undergraduate U01");
        posterU01.setStatus("Undergraduate");
        posterU01.setDepartment("Computer Science");
        posterU01.setEmail("posterU01@email.com");
        posterDao.create(posterU01);
        assertNotNull(posterU01.getId());

        Poster U02 = new Poster();
        U02.setPoster_id("U02");
        U02.setPin("1234");
        U02.setRole("student");
        U02.setFirst_name("FName U02");
        U02.setLast_name("LName U02");
        U02.setTitle("Undergraduate U02");
        U02.setStatus("Undergraduate");
        U02.setDepartment("Computer Science");
        U02.setEmail("posterU02@email.com");
        posterDao.create(U02);
        assertNotNull(U02.getId());

        Poster U03 = new Poster();
        U03.setPoster_id("U03");
        U03.setPin("1234");
        U03.setRole("student");
        U03.setFirst_name("FName U03");
        U03.setLast_name("LName U03");
        U03.setTitle("Undergraduate U03");
        U03.setStatus("Undergraduate");
        U03.setDepartment("Computer Science");
        U03.setEmail("posterU03@email.com");
        posterDao.create(U03);
        assertNotNull(U03.getId());

        Poster U04 = new Poster();
        U04.setPoster_id("U04");
        U04.setPin("1234");
        U04.setRole("student");
        U04.setFirst_name("FName U04");
        U04.setLast_name("LName U04");
        U04.setTitle("Undergraduate U04");
        U04.setStatus("Undergraduate");
        U04.setDepartment("Computer Science");
        U04.setEmail("posterU04@email.com");
        posterDao.create(U04);
        assertNotNull(U04.getId());

        Poster U05 = new Poster();
        U05.setPoster_id("U05");
        U05.setPin("1234");
        U05.setRole("student");
        U05.setFirst_name("FName U05");
        U05.setLast_name("LName U05");
        U05.setTitle("Undergraduate U05");
        U05.setStatus("Undergraduate");
        U05.setDepartment("Computer Science");
        U05.setEmail("posterU05@email.com");
        posterDao.create(U05);
        assertNotNull(U05.getId());

        Poster U06 = new Poster();
        U06.setPoster_id("U06");
        U06.setPin("1234");
        U06.setRole("student");
        U06.setFirst_name("FName U06");
        U06.setLast_name("LName U06");
        U06.setTitle("Undergraduate U06");
        U06.setStatus("Undergraduate");
        U06.setDepartment("Computer Science");
        U06.setEmail("posterU06@email.com");
        posterDao.create(U06);
        assertNotNull(U06.getId());

        Poster U07 = new Poster();
        U07.setPoster_id("U07");
        U07.setPin("1234");
        U07.setRole("student");
        U07.setFirst_name("FName U07");
        U07.setLast_name("LName U07");
        U07.setTitle("Undergraduate U07");
        U07.setStatus("Undergraduate");
        U07.setDepartment("Computer Science");
        U07.setEmail("posterU07@email.com");
        posterDao.create(U07);
        assertNotNull(U07.getId());

        Poster U08 = new Poster();
        U08.setPoster_id("U08");
        U08.setPin("1234");
        U08.setRole("student");
        U08.setFirst_name("FName U08");
        U08.setLast_name("LName U08");
        U08.setTitle("Undergraduate U08");
        U08.setStatus("Undergraduate");
        U08.setDepartment("Computer Science");
        U08.setEmail("posterU08@email.com");
        posterDao.create(U08);
        assertNotNull(U08.getId());

        Poster U09 = new Poster();
        U09.setPoster_id("U09");
        U09.setPin("1234");
        U09.setRole("student");
        U09.setFirst_name("FName U09");
        U09.setLast_name("LName U09");
        U09.setTitle("Undergraduate U09");
        U09.setStatus("Undergraduate");
        U09.setDepartment("Computer Science");
        U09.setEmail("posterU09@email.com");
        posterDao.create(U09);
        assertNotNull(U09.getId());

        Poster U10 = new Poster();
        U10.setPoster_id("U10");
        U10.setPin("1234");
        U10.setRole("student");
        U10.setFirst_name("FName U10");
        U10.setLast_name("LName U10");
        U10.setTitle("Undergraduate U10");
        U10.setStatus("Undergraduate");
        U10.setDepartment("Computer Science");
        U10.setEmail("posterU10@email.com");
        posterDao.create(U10);
        assertNotNull(U10.getId());

        Poster G01 = new Poster();
        G01.setPoster_id("G01");
        G01.setPin("5678");
        G01.setRole("student");
        G01.setFirst_name("FName G01");
        G01.setLast_name("LName G01");
        G01.setTitle("Graduate G01");
        G01.setStatus("Graduate");
        G01.setDepartment("Computer Science");
        G01.setEmail("posterG01@email.com");
        posterDao.create(G01);
        assertNotNull(G01.getId());

        Poster G02 = new Poster();
        G02.setPoster_id("G02");
        G02.setPin("5678");
        G02.setRole("student");
        G02.setFirst_name("FName G02");
        G02.setLast_name("LName G02");
        G02.setTitle("Graduate G02");
        G02.setStatus("Graduate");
        G02.setDepartment("Computer Science");
        G02.setEmail("posterG02@email.com");
        posterDao.create(G02);
        assertNotNull(G02.getId());

        Poster G03 = new Poster();
        G03.setPoster_id("G03");
        G03.setPin("5678");
        G03.setRole("student");
        G03.setFirst_name("FName G03");
        G03.setLast_name("LName G03");
        G03.setTitle("Graduate G03");
        G03.setStatus("Graduate");
        G03.setDepartment("Computer Science");
        G03.setEmail("posterG03@email.com");
        posterDao.create(G03);
        assertNotNull(G03.getId());

        Poster G04 = new Poster();
        G04.setPoster_id("G04");
        G04.setPin("5678");
        G04.setRole("student");
        G04.setFirst_name("FName G04");
        G04.setLast_name("LName G04");
        G04.setTitle("Graduate G04");
        G04.setStatus("Graduate");
        G04.setDepartment("Computer Science");
        G04.setEmail("posterG04@email.com");
        posterDao.create(G04);
        assertNotNull(G04.getId());

        Poster G05 = new Poster();
        G05.setPoster_id("G05");
        G05.setPin("5678");
        G05.setRole("student");
        G05.setFirst_name("FName G05");
        G05.setLast_name("LName G05");
        G05.setTitle("Graduate G05");
        G05.setStatus("Graduate");
        G05.setDepartment("Computer Science");
        G05.setEmail("posterG05@email.com");
        posterDao.create(G05);
        assertNotNull(G05.getId());

        Poster G06 = new Poster();
        G06.setPoster_id("G06");
        G06.setPin("5678");
        G06.setRole("student");
        G06.setFirst_name("FName G06");
        G06.setLast_name("LName G06");
        G06.setTitle("Graduate G06");
        G06.setStatus("Graduate");
        G06.setDepartment("Computer Science");
        G06.setEmail("posterG06@email.com");
        posterDao.create(G06);
        assertNotNull(G06.getId());

        Poster G07 = new Poster();
        G07.setPoster_id("G07");
        G07.setPin("5678");
        G07.setRole("student");
        G07.setFirst_name("FName G07");
        G07.setLast_name("LName G07");
        G07.setTitle("Graduate G07");
        G07.setStatus("Graduate");
        G07.setDepartment("Computer Science");
        G07.setEmail("posterG07@email.com");
        posterDao.create(G07);
        assertNotNull(G07.getId());

        Poster G08 = new Poster();
        G08.setPoster_id("G08");
        G08.setPin("5678");
        G08.setRole("student");
        G08.setFirst_name("FName G08");
        G08.setLast_name("LName G08");
        G08.setTitle("Graduate G08");
        G08.setStatus("Graduate");
        G08.setDepartment("Computer Science");
        G08.setEmail("posterG08@email.com");
        posterDao.create(G08);
        assertNotNull(G08.getId());

        Poster G09 = new Poster();
        G09.setPoster_id("G09");
        G09.setPin("5678");
        G09.setRole("student");
        G09.setFirst_name("FName G09");
        G09.setLast_name("LName G09");
        G09.setTitle("Graduate G09");
        G09.setStatus("Graduate");
        G09.setDepartment("Computer Science");
        G09.setEmail("posterG09@email.com");
        posterDao.create(G09);
        assertNotNull(G09.getId());

        Poster G10 = new Poster();
        G10.setPoster_id("G10");
        G10.setPin("5678");
        G10.setRole("student");
        G10.setFirst_name("FName G10");
        G10.setLast_name("LName G10");
        G10.setTitle("Graduate G10");
        G10.setStatus("Graduate");
        G10.setDepartment("Computer Science");
        G10.setEmail("posterG10@email.com");
        posterDao.create(G10);
        assertNotNull(G10.getId());


        Score s1 = new Score();
        s1.setPoster_id(posterU01.getPoster_id());
        s1.setJudge_id(judgeU1.getJudge_id());
        s1.setRound(1);
//        s1.setPoster_score(20);
//        s1.setResearch_score(20);
//        s1.setComm_score(30);
//        s1.setTotal_score(100);
        scoreDao.create(s1);

        Score s2 = new Score();
        s2.setPoster_id(posterU01.getPoster_id());
        s2.setJudge_id(judgeU2.getJudge_id());
        s2.setRound(1);
//        s2.setPoster_score(10);
//        s2.setResearch_score(10);
//        s2.setComm_score(15);
//        s2.setTotal_score(50);
        scoreDao.create(s2);

        Score s3 = new Score();
        s3.setPoster_id(posterU01.getPoster_id());
        s3.setJudge_id(judgeU3.getJudge_id());
        s3.setRound(1);
        scoreDao.create(s3);

        Score s4 = new Score();
        s4.setPoster_id(posterU01.getPoster_id());
        s4.setJudge_id(judgeU4.getJudge_id());
        s4.setRound(1);
        scoreDao.create(s4);

        Score s5 = new Score();
        s5.setPoster_id(U02.getPoster_id());
        s5.setJudge_id(judgeU5.getJudge_id());
        s5.setRound(1);
//        s5.setPoster_score(20);
//        s5.setResearch_score(20);
//        s5.setComm_score(30);
//        s5.setTotal_score(100);
        scoreDao.create(s5);

        Score s6 = new Score();
        s6.setPoster_id(U02.getPoster_id());
        s6.setJudge_id(judgeU6.getJudge_id());
        s6.setRound(1);
        scoreDao.create(s6);

        Score s7 = new Score();
        s7.setPoster_id(U02.getPoster_id());
        s7.setJudge_id(judgeU7.getJudge_id());
        s7.setRound(1);
        scoreDao.create(s7);

        Score s8 = new Score();
        s8.setPoster_id(U03.getPoster_id());
        s8.setJudge_id(judgeU8.getJudge_id());
        s8.setRound(1);
        scoreDao.create(s8);

        Score s9 = new Score();
        s9.setPoster_id(U03.getPoster_id());
        s9.setJudge_id(judgeU9.getJudge_id());
        s9.setRound(1);
        scoreDao.create(s9);

        Score s10 = new Score();
        s10.setPoster_id(U03.getPoster_id());
        s10.setJudge_id(judgeU10.getJudge_id());
        s10.setRound(1);
        scoreDao.create(s10);

        Score s11 = new Score();
        s11.setPoster_id(U04.getPoster_id());
        s11.setJudge_id(judgeU1.getJudge_id());
        s11.setRound(1);
        scoreDao.create(s11);

        Score s12 = new Score();
        s12.setPoster_id(U04.getPoster_id());
        s12.setJudge_id(judgeU2.getJudge_id());
        s12.setRound(1);
        scoreDao.create(s12);

        Score s13 = new Score();
        s13.setPoster_id(U04.getPoster_id());
        s13.setJudge_id(judgeU3.getJudge_id());
        s13.setRound(1);
        scoreDao.create(s13);

        Score s14 = new Score();
        s14.setPoster_id(U05.getPoster_id());
        s14.setJudge_id(judgeU4.getJudge_id());
        s14.setRound(1);
        scoreDao.create(s14);

        Score s15 = new Score();
        s15.setPoster_id(U05.getPoster_id());
        s15.setJudge_id(judgeU5.getJudge_id());
        s15.setRound(1);
        scoreDao.create(s15);

        Score s16 = new Score();
        s16.setPoster_id(U05.getPoster_id());
        s16.setJudge_id(judgeU6.getJudge_id());
        s16.setRound(1);
        scoreDao.create(s16);

        Score s17 = new Score();
        s17.setPoster_id(U06.getPoster_id());
        s17.setJudge_id(judgeU7.getJudge_id());
        s17.setRound(1);
        scoreDao.create(s17);

        Score s18 = new Score();
        s18.setPoster_id(U06.getPoster_id());
        s18.setJudge_id(judgeU8.getJudge_id());
        s18.setRound(1);
        scoreDao.create(s18);

        Score s19 = new Score();
        s19.setPoster_id(U06.getPoster_id());
        s19.setJudge_id(judgeU9.getJudge_id());
        s19.setRound(1);
        scoreDao.create(s19);

        Score s20 = new Score();
        s20.setPoster_id(U07.getPoster_id());
        s20.setJudge_id(judgeU9.getJudge_id());
        s20.setRound(1);
        scoreDao.create(s20);

        Score s21 = new Score();
        s21.setPoster_id(U07.getPoster_id());
        s21.setJudge_id(judgeU1.getJudge_id());
        s21.setRound(1);
        scoreDao.create(s21);

        Score s22 = new Score();
        s22.setPoster_id(U07.getPoster_id());
        s22.setJudge_id(judgeU2.getJudge_id());
        s22.setRound(1);
        scoreDao.create(s22);

        Score s24 = new Score();
        s24.setPoster_id(U08.getPoster_id());
        s24.setJudge_id(judgeU3.getJudge_id());
        s24.setRound(1);
        scoreDao.create(s24);

        Score s25 = new Score();
        s25.setPoster_id(U08.getPoster_id());
        s25.setJudge_id(judgeU4.getJudge_id());
        s25.setRound(1);
        scoreDao.create(s25);

        Score s26 = new Score();
        s26.setPoster_id(U08.getPoster_id());
        s26.setJudge_id(judgeU5.getJudge_id());
        s26.setRound(1);
        scoreDao.create(s26);

        Score s27 = new Score();
        s27.setPoster_id(U09.getPoster_id());
        s27.setJudge_id(judgeU6.getJudge_id());
        s27.setRound(1);
        scoreDao.create(s27);

        Score s28 = new Score();
        s28.setPoster_id(U09.getPoster_id());
        s28.setJudge_id(judgeU9.getJudge_id());
        s28.setRound(1);
        scoreDao.create(s28);

        Score s29 = new Score();
        s29.setPoster_id(U10.getPoster_id());
        s29.setJudge_id(judgeU10.getJudge_id());
        s29.setRound(1);
        scoreDao.create(s29);

        Score s30 = new Score();
        s30.setPoster_id(G01.getPoster_id());
        s30.setJudge_id(judgeG11.getJudge_id());
        s30.setRound(1);
        scoreDao.create(s30);


        Score s31 = new Score();
        s31.setPoster_id(G02.getPoster_id());
        s31.setJudge_id(judgeG12.getJudge_id());
        s31.setRound(1);
        scoreDao.create(s31);

        Score s32 = new Score();
        s32.setPoster_id(G02.getPoster_id());
        s32.setJudge_id(judgeG13.getJudge_id());
        s32.setRound(1);
        scoreDao.create(s32);

        Score s33 = new Score();
        s33.setPoster_id(G03.getPoster_id());
        s33.setJudge_id(judgeG14.getJudge_id());
        s33.setRound(1);
        scoreDao.create(s33);

        Score s34 = new Score();
        s34.setPoster_id(G03.getPoster_id());
        s34.setJudge_id(judgeG15.getJudge_id());
        s34.setRound(1);
        scoreDao.create(s34);

        Score s35 = new Score();
        s35.setPoster_id(G03.getPoster_id());
        s35.setJudge_id(judgeG16.getJudge_id());
        s35.setRound(1);
        scoreDao.create(s35);

        Score s36 = new Score();
        s36.setPoster_id(G04.getPoster_id());
        s36.setJudge_id(judgeG17.getJudge_id());
        s36.setRound(1);
        scoreDao.create(s36);

        Score s37 = new Score();
        s37.setPoster_id(G04.getPoster_id());
        s37.setJudge_id(judgeG18.getJudge_id());
        s37.setRound(1);
        scoreDao.create(s37);

        Score s38 = new Score();
        s38.setPoster_id(G04.getPoster_id());
        s38.setJudge_id(judgeG19.getJudge_id());
        s38.setRound(1);
        scoreDao.create(s38);

        Score s39 = new Score();
        s39.setPoster_id(G04.getPoster_id());
        s39.setJudge_id(judgeG20.getJudge_id());
        s39.setRound(1);
        scoreDao.create(s39);

        Score s40 = new Score();
        s40.setPoster_id(G05.getPoster_id());
        s40.setJudge_id(judgeG12.getJudge_id());
        s40.setRound(1);
        scoreDao.create(s40);

        Score s41 = new Score();
        s41.setPoster_id(G05.getPoster_id());
        s41.setJudge_id(judgeG13.getJudge_id());
        s41.setRound(1);
        scoreDao.create(s41);

        Score s42 = new Score();
        s42.setPoster_id(G05.getPoster_id());
        s42.setJudge_id(judgeG14.getJudge_id());
        s42.setRound(1);
        scoreDao.create(s42);

        Score s43 = new Score();
        s43.setPoster_id(G06.getPoster_id());
        s43.setJudge_id(judgeG15.getJudge_id());
        s43.setRound(1);
        scoreDao.create(s43);

        Score s44 = new Score();
        s44.setPoster_id(G06.getPoster_id());
        s44.setJudge_id(judgeG16.getJudge_id());
        s44.setRound(1);
        scoreDao.create(s44);

        Score s45 = new Score();
        s45.setPoster_id(G06.getPoster_id());
        s45.setJudge_id(judgeG17.getJudge_id());
        s45.setRound(1);
        scoreDao.create(s45);

        Score s46 = new Score();
        s46.setPoster_id(G07.getPoster_id());
        s46.setJudge_id(judgeG18.getJudge_id());
        s46.setRound(1);
        scoreDao.create(s46);

        Score s47 = new Score();
        s47.setPoster_id(G07.getPoster_id());
        s47.setJudge_id(judgeG12.getJudge_id());
        s47.setRound(1);
        scoreDao.create(s47);

        Score s48 = new Score();
        s48.setPoster_id(G07.getPoster_id());
        s48.setJudge_id(judgeG13.getJudge_id());
        s48.setRound(1);
        scoreDao.create(s48);

        Score s49 = new Score();
        s49.setPoster_id(G08.getPoster_id());
        s49.setJudge_id(judgeG14.getJudge_id());
        s49.setRound(1);
        scoreDao.create(s49);

        Score s50 = new Score();
        s50.setPoster_id(G08.getPoster_id());
        s50.setJudge_id(judgeG15.getJudge_id());
        s50.setRound(1);
        scoreDao.create(s50);

        Score s51 = new Score();
        s51.setPoster_id(G08.getPoster_id());
        s51.setJudge_id(judgeG16.getJudge_id());
        s51.setRound(1);
        scoreDao.create(s51);

        Score s52 = new Score();
        s52.setPoster_id(G09.getPoster_id());
        s52.setJudge_id(judgeG17.getJudge_id());
        s52.setRound(1);
        scoreDao.create(s52);

        Score s53 = new Score();
        s53.setPoster_id(G09.getPoster_id());
        s53.setJudge_id(judgeG18.getJudge_id());
        s53.setRound(1);
        scoreDao.create(s53);

        Score s54 = new Score();
        s54.setPoster_id(G09.getPoster_id());
        s54.setJudge_id(judgeG19.getJudge_id());
        s54.setRound(1);
        scoreDao.create(s54);

        Score s55 = new Score();
        s55.setPoster_id(G10.getPoster_id());
        s55.setJudge_id(judgeG20.getJudge_id());
        s55.setRound(1);
        scoreDao.create(s55);

        Score s56 = new Score();
        s56.setPoster_id(G10.getPoster_id());
        s56.setJudge_id(judgeG12.getJudge_id());
        s56.setRound(1);
        scoreDao.create(s56);

        Score s57 = new Score();
        s57.setPoster_id(G10.getPoster_id());
        s57.setJudge_id(judgeG13.getJudge_id());
        s57.setRound(1);
        scoreDao.create(s57);





    }


}
