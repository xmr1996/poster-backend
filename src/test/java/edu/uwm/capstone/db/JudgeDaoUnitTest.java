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
        assertNotNull(judgeDao.sql("readJudge"));
        assertNotNull(judgeDao.sql("updateJudge"));
        assertNotNull(judgeDao.sql("deleteJudge"));
    }

    /**
     * Verify that {@link JudgeDao#create} is working correctly.
     */
    @Test
    public void create() {
        Judge createJudge = TestDataUtility.judgeWithTestValues();
        judgeDao.create(createJudge);
        assertNotNull(createJudge.getId());
    }

    /**
     * Verify that {@link JudgeDao#create} is working correctly when a request for creating a null object is made.
     */
    @Test(expected = RuntimeException.class)
    public void createNullJudge() {
        judgeDao.create(null);
    }

    /**
     * Verify that {@link JudgeDao#create} is working correctly when a request for a {@link Judge} with a non-null id is made.
     */
    @Test(expected = RuntimeException.class)
    public void createNonNullJudgeId() {
        Judge createJudge = TestDataUtility.judgeWithTestValues();
        createJudge.setId(new Random().longs(1L, Long.MAX_VALUE).findAny().getAsLong());
        judgeDao.create(createJudge);
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
        assertNotNull(createJudge.getId());

        Judge readJudge = judgeDao.read(createJudge.getId());
        assertNotNull(readJudge);
        assertEquals(createJudge.getId(), readJudge.getId());
        assertEquals(createJudge, readJudge);
    }

    /**
     * Verify that {@link JudgeDao#read} is working correctly when a request for a non-existent {@link Judge#getId} is made.
     */
    @Test
    public void readNonExistentJudge() {
        // create a random judge id that will not be in our local database
        Long id = new Random().longs(10000L, Long.MAX_VALUE).findAny().getAsLong();
        Judge judge = judgeDao.read(id);
        assertNull(judge);
    }

    /**
     * Verify that {@link JudgeDao#update} is working correctly.
     */
    @Test
    public void update() {
        Judge createJudge = TestDataUtility.judgeWithTestValues();
        judgeDao.create(createJudge);
        assertNotNull(createJudge.getId());

        Judge verifyCreateJudge = judgeDao.read(createJudge.getId());
        assertNotNull(verifyCreateJudge);
        assertEquals(createJudge.getId(), verifyCreateJudge.getId());
        assertEquals(createJudge, verifyCreateJudge);

        Judge updateJudge = TestDataUtility.judgeWithTestValues();
        updateJudge.setId(createJudge.getId());
        judgeDao.update(updateJudge);

        Judge verifyUpdateJudge = judgeDao.read(updateJudge.getId());
        assertNotNull(verifyUpdateJudge);
        assertEquals(createJudge.getId(), verifyUpdateJudge.getId());
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
     * Verify that {@link JudgeDao#update} is working correctly when a request for a non-existent {@link Judge#getId} is made.
     */
    @Test(expected = RuntimeException.class)
    public void updateNonExistentJudge() {
        // create a random judge id that will not be in our local database
        Judge updateJudge = TestDataUtility.judgeWithTestValues();
        updateJudge.setId(new Random().longs(10000L, Long.MAX_VALUE).findAny().getAsLong());
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
        assertNotNull(createJudge.getId());

        Judge verifyCreateJudge = judgeDao.read(createJudge.getId());
        assertNotNull(verifyCreateJudge);
        assertEquals(createJudge.getId(), verifyCreateJudge.getId());
        assertEquals(createJudge, verifyCreateJudge);

        Judge updateJudge = TestDataUtility.judgeWithTestValues();
        updateJudge.setId(createJudge.getId());
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
        assertNotNull(createJudge.getId());

        Judge verifyCreateJudge = judgeDao.read(createJudge.getId());
        assertNotNull(verifyCreateJudge);
        assertEquals(createJudge.getId(), verifyCreateJudge.getId());
        assertEquals(createJudge, verifyCreateJudge);

        judgeDao.delete(createJudge.getId());

        Judge verifyDeleteJudge = judgeDao.read(createJudge.getId());
        assertNull(verifyDeleteJudge);
    }


    /**
     * Verify that {@link JudgeDao#delete} is working correctly when a request for a non-existent {@link Judge#getId} is made.
     */
    @Test(expected = RuntimeException.class)
    public void deleteNonExistentJudge() {
        Long id = new Random().longs(10000L, Long.MAX_VALUE).findAny().getAsLong();
        judgeDao.delete(id);
    }
}
