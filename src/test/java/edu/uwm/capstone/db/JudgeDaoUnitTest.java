package edu.uwm.capstone.db;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.util.TestDataUtility;
import org.apache.commons.lang3.RandomStringUtils;
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
public class JudgeDaoUnitTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    JudgeDao judgeDao;

    @Before
    public void setUp() {
        assertNotNull(judgeDao);
        assertNotNull(judgeDao.sql("createJudge"));
        assertNotNull(judgeDao.sql("upsertJudge"));
        assertNotNull(judgeDao.sql("readJudges"));
        assertNotNull(judgeDao.sql("updateJudge"));
        assertNotNull(judgeDao.sql("readJudgeByJudgeId"));
        assertNotNull(judgeDao.sql("readJudgesByStatus"));
        assertNotNull(judgeDao.sql("deleteJudgeByJudgeId"));
        assertNotNull(judgeDao.sql("readJudgeEmailPin"));
        assertNotNull(judgeDao.sql("clearJudges"));
    }

    /**
     * Verify that {@link JudgeDao#create} is working correctly.
     */
    @Test
    public void create() {
        Judge createJudge = TestDataUtility.judgeWithTestValues();
        judgeDao.create(createJudge);
        assertNotNull(createJudge.getRole());
    }



    /**
     * Verify that {@link JudgeDao#create} is working correctly when a request for creating a null object is made.
     */
    @Test(expected = RuntimeException.class)
    public void createNullJudge() {
        judgeDao.create(null);
    }


    /**
     * Verify that {@link JudgeDao#create} is working correctly when a request for a {@link Judge} that contains a value
     * which exceeds the database configuration is made.
     */
    @Test(expected = RuntimeException.class)
    public void createJudgeColumnTooLong() {
        // generate a test profile value with a column that will exceed the database configuration
        Judge createJudge = TestDataUtility.judgeWithTestValues();
        createJudge.setFirst_name(RandomStringUtils.randomAlphabetic(2000));
        judgeDao.create(createJudge);
    }

    /**
     * Verify that {@link JudgeDao#read} is working correctly.
     */
    @Test
    public void read() {
        Judge createJudge = TestDataUtility.judgeWithTestValues();
        judgeDao.create(createJudge);
        assertNotNull(createJudge.getRole());

        Judge readJudge = judgeDao.readByJudgeID(createJudge.getJudge_id());
        assertNotNull(readJudge);
        assertEquals(createJudge.getJudge_id(), readJudge.getJudge_id());
        assertEquals(createJudge, readJudge);
    }

    /**
     * Verify that {@link JudgeDao#read()} is working correctly.
     */
    @Test
    public void readAllJudge() {

        List<Judge> result = judgeDao.read();
        assertNotNull(result);
        assertTrue(result.size()>0);
    }

    /**
     * Verify that {@link JudgeDao#read(String, String)} is working correctly.
     */
    @Test
    public void readByEmailPin() {
        Judge createJudge = TestDataUtility.judgeWithTestValues();
        judgeDao.create(createJudge);
        assertNotNull(createJudge.getRole());

        Judge readJudge = judgeDao.read(createJudge.getEmail(),createJudge.getPin());
        assertNotNull(readJudge);
        assertEquals(createJudge.getJudge_id(), readJudge.getJudge_id());
        assertEquals(createJudge, readJudge);
    }
    /**
     * Verify that {@link JudgeDao#read} is working correctly when a request for a non-existent {@link Judge#getJudge_id()} is made.
     */
    @Test
    public void readNonExistentJudge() {
        //create a random judge id that will not be in our local database
        Long id = new Random().longs(10000L, Long.MAX_VALUE).findAny().getAsLong();
        Judge judge = judgeDao.readByJudgeID(id);
        assertNull(judge);
    }

    /**
     * Verify that {@link JudgeDao#update} is working correctly.
     */
    @Test
    public void update() {
        Judge createJudge = TestDataUtility.judgeWithTestValues();
        judgeDao.create(createJudge);
        assertNotNull(createJudge.getRole());

        Judge verifyCreateJudge = judgeDao.readByJudgeID(createJudge.getJudge_id());
        assertNotNull(verifyCreateJudge);
        assertEquals(createJudge.getJudge_id(), verifyCreateJudge.getJudge_id());
        assertEquals(createJudge, verifyCreateJudge);

        Judge updateJudge = TestDataUtility.judgeWithTestValues();
        updateJudge.setJudge_id(createJudge.getJudge_id());
        judgeDao.update(updateJudge);

        Judge verifyUpdateJudge = judgeDao.readByJudgeID(updateJudge.getJudge_id());
        assertNotNull(verifyUpdateJudge);
        assertEquals(createJudge.getJudge_id(), verifyUpdateJudge.getJudge_id());
        assertEquals(updateJudge.getFirst_name(), verifyUpdateJudge.getFirst_name());
    }

    /**
     * Verify that {@link JudgeDao#update} is working correctly when a request for creating a null object is made.
     */
    @Test(expected = RuntimeException.class)
    public void updateNullJudge() {
        judgeDao.update(null);
    }

    /**
     * Verify that {@link JudgeDao#update} is working correctly when a request for a non-existent {@link Judge#getJudge_id()} is made.
     */
    @Test(expected = RuntimeException.class)
    public void updateNonExistentJudge() {
        // create a random judge id that will not be in our local database
        Judge updateJudge = TestDataUtility.judgeWithTestValues();
        updateJudge.setJudge_id(new Random().longs(10000L, Long.MAX_VALUE).findAny().getAsLong());
        judgeDao.update(updateJudge);
    }

    /**
     * Verify that {@link JudgeDao#update} is working correctly when a request for a {@link Judge} that contains a value
     * which exceeds the database configuration is made.
     */
    @Test(expected = RuntimeException.class)
    public void updateJudgeColumnTooLong() {
        // generate a test judge value with a column that will exceed the database configuration
        Judge createJudge = TestDataUtility.judgeWithTestValues();
        judgeDao.create(createJudge);
        assertNotNull(createJudge.getRole());

        Judge verifyCreateJudge = judgeDao.readByJudgeID(createJudge.getJudge_id());
        assertNotNull(verifyCreateJudge);
        assertEquals(createJudge.getJudge_id(), verifyCreateJudge.getJudge_id());
        assertEquals(createJudge, verifyCreateJudge);

        Judge updateJudge = TestDataUtility.judgeWithTestValues();
        updateJudge.setJudge_id(createJudge.getJudge_id());
        updateJudge.setFirst_name(RandomStringUtils.randomAlphabetic(2000));
        judgeDao.update(updateJudge);
    }

    /**
     * Verify that {@link JudgeDao#delete} is working correctly.
     */
    @Test
    public void delete() {
        Judge createJudge = TestDataUtility.judgeWithTestValues();
        judgeDao.create(createJudge);
        assertNotNull(createJudge.getRole());

        Judge verifyCreateJudge = judgeDao.readByJudgeID(createJudge.getJudge_id());
        assertNotNull(verifyCreateJudge);
        assertEquals(createJudge.getJudge_id(), verifyCreateJudge.getJudge_id());
        assertEquals(createJudge, verifyCreateJudge);

        judgeDao.deleteByJudgeId(createJudge.getJudge_id());

        Judge verifyDeleteJudge = judgeDao.readByJudgeID(createJudge.getJudge_id());
        assertNull(verifyDeleteJudge);
    }


    /**
     * Verify that {@link JudgeDao#delete} is working correctly when a request for a non-existent {@link Judge#getJudge_id()} is made.
     */
    @Test(expected = RuntimeException.class)
    public void deleteNonExistentJudge() {
        Long id = new Random().longs(10000L, Long.MAX_VALUE).findAny().getAsLong();
        judgeDao.deleteByJudgeId(id);
    }

    @Test
    public void TestClearTable() {
        judgeDao.clearTable();
        List<Judge> result = judgeDao.read();
        assertNotNull(result);
        assertTrue(result.size()==0);
    }
}
