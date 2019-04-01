package edu.uwm.capstone.db;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.model.Poster.Poster;
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
        assertNotNull(posterDao.sql("createPoster"));
        assertNotNull(posterDao.sql("updatePoster"));
    }

    /**
     * Verify that {@link PosterDao#create} is working correctly.
     */
    @Test
    public void create() {
        Poster createPoster = TestDataUtility.posterWithTestValues();
        posterDao.create(createPoster);
        assertNotNull(createPoster.getPoster_id());
    }


    /**
     * Verify that {@link PosterDao#create} is working correctly.
     */
    @Test
    public void createTestPosters() {
//        Poster g_poster = new Poster();
//        g_poster.setPoster_id("G01");
//        g_poster.setPin(1234);
//        g_poster.setFirst_name("FirstName");
//        g_poster.setLast_name("LastName");
//        g_poster.setTitle("This is the Graduate Poster Title");
//        g_poster.setStatus("Graduate");
//        g_poster.setDepartment("Computer Science");
//        g_poster.setEmail("gradposter@email.com");
//
//        posterDao.create(g_poster);
//        assertNotNull(g_poster.getId());
//
//        Poster u_poster = new Poster();
//        u_poster.setPoster_id("U01");
//        u_poster.setPin(1234);
//        u_poster.setFirst_name("FirstName");
//        u_poster.setLast_name("LastName");
//        u_poster.setTitle("This is the Undergraduate Poster Title");
//        u_poster.setStatus("Undergraduate");
//        u_poster.setDepartment("Electrical Engineering");
//        u_poster.setEmail("undergradposter@email.com");
//
//        posterDao.create(u_poster);
//        assertNotNull(u_poster.getId());
    }

    /**
     * Verify that {@link PosterDao#create} is working correctly when a request for creating a null object is made.
     */
    @Test(expected = RuntimeException.class)
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

    /**
     * Verify that {@link PosterDao#update} is working correctly.
     */
//    @Test
//    public void update() {
//        Poster createPoster = TestDataUtility.posterWithTestValues();
//        posterDao.create(createPoster);
//        assertNotNull(createPoster.getPoster_id());
//
//        Poster verifyCreatePoster = posterDao.read(createPoster.getPoster_id());
//        assertNotNull(verifyCreatePoster);
//        assertEquals(createPoster.getPoster_id(), verifyCreatePoster.getPoster_id());
//        assertEquals(createPoster, verifyCreatePoster);
//
//        Poster updatePoster = TestDataUtility.posterWithTestValues();
//        updatePoster.setPoster_id(createPoster.getPoster_id());
//        posterDao.update(updatePoster);
//
//        Poster verifyUpdatePoster = posterDao.read(updatePoster.getPoster_id());
//        assertNotNull(verifyUpdatePoster);
//        assertEquals(createPoster.getPoster_id(), verifyUpdatePoster.getPoster_id());
//        assertEquals(updatePoster.getFirst_name(), verifyUpdatePoster.getFirst_name());
//    }

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

//    /**
//     * Verify that {@link PosterDao#delete} is working correctly.
//     */
//    @Test
//    public void delete() {
//        Poster createPoster = TestDataUtility.posterWithTestValues();
//        posterDao.create(createPoster);
//        assertNotNull(createPoster.getPoster_id());
//
//        Poster verifyCreatePoster = posterDao.read(createPoster.getPoster_id());
//        assertNotNull(verifyCreatePoster);
//        assertEquals(createPoster.getPoster_id(), verifyCreatePoster.getPoster_id());
//        assertEquals(createPoster, verifyCreatePoster);
//
//        posterDao.delete(createPoster.getPoster_id());
//
//        Poster verifyDeletePoster = posterDao.read(createPoster.getPoster_id());
//        assertNull(verifyDeletePoster);
//    }

    /**
     * Verify that {@link PosterDao#delete} is working correctly when a request for a non-existent {@link Poster#getPoster_id()} is made.
     */
    @Test(expected = RuntimeException.class)
    public void deleteNonExistentPoster() {
        posterDao.delete(randomAlphabetic(randomInt(1, 100)));
    }


}

