package edu.uwm.capstone.db;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.sql.exception.DaoException;
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

import static edu.uwm.capstone.db.ScoreDaoUnitTest.randomInt;
import static edu.uwm.capstone.util.TestDataUtility.randomAlphabetic;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfig.class)

public class PosterDaoUnitTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    PosterDao posterDao;

    @Before
    public void setUp() {
        assertNotNull(posterDao);
        assertNotNull(posterDao.sql("upsertPoster"));
        assertNotNull(posterDao.sql("updatePoster"));
        assertNotNull(posterDao.sql("getPosters"));
        assertNotNull(posterDao.sql("readPosterByID"));
        assertNotNull(posterDao.sql("readPosterEmailPin"));
        assertNotNull(posterDao.sql("readPostersByStatus"));
        assertNotNull(posterDao.sql("insertAvgR1"));
        assertNotNull(posterDao.sql("updatePoster"));
        assertNotNull(posterDao.sql("setVote"));
        assertNotNull(posterDao.sql("deletePoster"));
        assertNotNull(posterDao.sql("getTop6PostersR1"));
        assertNotNull(posterDao.sql("insertAvgTotalR1"));
        assertNotNull(posterDao.sql("insertAvgTotalR2"));
        assertNotNull(posterDao.sql("insertAvgCommR1"));
        assertNotNull(posterDao.sql("insertAvgCommR2"));
        assertNotNull(posterDao.sql("insertAvgResearchR1"));
        assertNotNull(posterDao.sql("insertAvgResearchR2"));
        assertNotNull(posterDao.sql("insertAvgPresR1"));
        assertNotNull(posterDao.sql("insertAvgPresR2"));
        assertNotNull(posterDao.sql("clearPosters"));

    }

    /**
     * Verify that {@link PosterDao#create} is working correctly.
     */
    @Test
    public void create() {
        Poster createPoster = TestDataUtility.posterWithTestValues();
        posterDao.create(createPoster);
        assertEquals("student", createPoster.getRole());
    }

    /**
     * Verify that {@link PosterDao#create} is working correctly when a request for creating a null object is made.
     */
    @Test(expected = DaoException.class)
    public void createNullPoster() {
        posterDao.create(null);
    }


    /**
     * Verify that {@link PosterDao#create} is working correctly when a request for a {@link Poster} that contains a value
     * which exceeds the database configuration is made.
     */
    @Test(expected = RuntimeException.class)
    public void createPosterColumnTooLong() {
        // generate a test poster value with a column that will exceed the database configuration
        Poster createPoster = TestDataUtility.posterWithTestValues();
        createPoster.setFirst_name(RandomStringUtils.randomAlphabetic(2000));
        posterDao.create(createPoster);
    }

    @Test(expected = DaoException.class)
    public void createPosterEmptyID(){
        Poster poster = TestDataUtility.posterWithTestValues();
        poster.setPoster_id("");
        posterDao.create(poster);
    }

    /**
     * Verify that {@link PosterDao#read} is working correctly.
     */
    @Test
    public void read() {
        Poster createPoster = TestDataUtility.posterWithTestValues();
        posterDao.create(createPoster);
        assertNotNull(createPoster.getPoster_id());

        Poster readPoster = posterDao.read(createPoster.getPoster_id());
        assertNotNull(readPoster);
        assertEquals(createPoster.getPoster_id(), readPoster.getPoster_id());
        assertEquals(createPoster, readPoster);
    }

    @Test(expected = DaoException.class)
    public void readInvalidID(){
        posterDao.read(TestDataUtility.randomAlphanumeric(3));
    }

    @Test
    public void readByEmailAndPin(){
        Poster poster = TestDataUtility.posterWithTestValues();
        String email = poster.getEmail();
        String pin = poster.getPin();
        posterDao.create(poster);
        assertEquals(poster, posterDao.read(email, pin));
    }

    @Test
    public void invalidReadByEmailAndPin(){
        assertNull(posterDao.read(TestDataUtility.randomAlphanumeric(100), TestDataUtility.randomAlphanumeric(5)));
    }


    /**
     * Verify that {@link PosterDao#update} is working correctly.
     */
    @Test
    public void update() {
        Poster createPoster = TestDataUtility.posterWithTestValues();
        posterDao.create(createPoster);
        assertEquals("student", createPoster.getRole());

        Poster verifyCreatePoster = posterDao.read(createPoster.getPoster_id());
        assertNotNull(verifyCreatePoster);
        assertEquals(createPoster.getPoster_id(), verifyCreatePoster.getPoster_id());
        assertEquals(createPoster, verifyCreatePoster);

        Poster updatePoster = TestDataUtility.posterWithTestValues();
        updatePoster.setPoster_id(createPoster.getPoster_id());
        posterDao.update(updatePoster);

        Poster verifyUpdatePoster = posterDao.read(updatePoster.getPoster_id());
        assertNotNull(verifyUpdatePoster);
        assertEquals(createPoster.getPoster_id(), verifyUpdatePoster.getPoster_id());
        assertEquals(updatePoster.getFirst_name(), verifyUpdatePoster.getFirst_name());
    }

    /**
     * Verify that {@link PosterDao#update} is working correctly when a request for creating a null object is made.
     */
    @Test(expected = RuntimeException.class)
    public void updateNullPoster() {
        posterDao.update(null);
    }

    /**
     * Verify that {@link PosterDao#update} is working correctly when a request for a non-existent {@link Poster#getPoster_id()} is made.
     */
    @Test(expected = RuntimeException.class)
    public void updateNonExistentPoster() {
        // create a random poster id that will not be in our local database
        Poster updatePoster = TestDataUtility.posterWithTestValues();
        updatePoster.setPoster_id(randomAlphabetic(randomInt(1, 100)));
        posterDao.update(updatePoster);
    }

    /**
     * Verify that {@link PosterDao#update} is working correctly when a request for a {@link Poster} that contains a value
     * which exceeds the database configuration is made.
     */
    @Test(expected = RuntimeException.class)
    public void updatePosterColumnTooLong() {
        // generate a test poster value with a column that will exceed the database configuration
        Poster createPoster = TestDataUtility.posterWithTestValues();
        posterDao.create(createPoster);
        assertNotNull(createPoster.getPoster_id());

        Poster verifyCreatePoster = posterDao.read(createPoster.getPoster_id());
        assertNotNull(verifyCreatePoster);
        assertEquals(createPoster.getPoster_id(), verifyCreatePoster.getPoster_id());
        assertEquals(createPoster, verifyCreatePoster);

        Poster updatePoster = TestDataUtility.posterWithTestValues();
        updatePoster.setPoster_id(createPoster.getPoster_id());
        updatePoster.setFirst_name(RandomStringUtils.randomAlphabetic(2000));
        posterDao.update(updatePoster);
    }

    @Test
    public void castVote(){
        Poster poster = TestDataUtility.posterWithTestValues();
        posterDao.create(poster);
        String vote = TestDataUtility.randomAlphanumeric(3);
        posterDao.setVote(poster.getPoster_id(), vote);
        poster = posterDao.read(poster.getPoster_id());
        assertEquals(vote, poster.getVoted_for());
    }

    @Test(expected = DaoException.class)
    public void castInvalidVote(){
        posterDao.setVote(TestDataUtility.randomAlphanumeric(3), TestDataUtility.randomAlphabetic(3));
    }

    /**
     * Verify that {@link PosterDao#delete} is working correctly.
     */
    @Test(expected = DaoException.class)
    public void delete() {
        Poster createPoster = TestDataUtility.posterWithTestValues();
        createPoster.setPoster_id("U12345");
        posterDao.create(createPoster);
        assertEquals("student", createPoster.getRole());

        Poster verifyCreatePoster = posterDao.read("U12345");
        assertNotNull(verifyCreatePoster);
        assertEquals(createPoster.getPoster_id(), verifyCreatePoster.getPoster_id());
        assertEquals(createPoster, verifyCreatePoster);

        posterDao.delete(createPoster.getPoster_id());

        posterDao.read(createPoster.getPoster_id());
    }

    /**
     * Verify that {@link PosterDao#delete} is working correctly when a request for a non-existent {@link Poster#getPoster_id()} is made.
     */
    @Test(expected = RuntimeException.class)
    public void deleteNonExistentPoster() {
        posterDao.delete(randomAlphabetic(randomInt(1, 100)));
    }


}

