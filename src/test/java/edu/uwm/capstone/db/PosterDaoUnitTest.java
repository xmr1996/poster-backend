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
        assertNotNull(posterDao.sql("readPoster"));
        assertNotNull(posterDao.sql("updatePoster"));
        assertNotNull(posterDao.sql("deletePoster"));
    }

    /**
     * Verify that {@link PosterDao#create} is working correctly.
     */
    @Test
    public void create() {
        Poster createPoster = TestDataUtility.posterWithTestValues();
        posterDao.create(createPoster);
        assertNotNull(createPoster.getId());
    }


    /**
     * Verify that {@link PosterDao#create} is working correctly.
     */
    @Test
    public void createTestPosters() {
        Poster g_poster = new Poster();
        g_poster.setPoster_id("G01");
        g_poster.setPin(1234);
        g_poster.setFirst_name("FirstName");
        g_poster.setLast_name("LastName");
        g_poster.setTitle("This is the Graduate Poster Title");
        g_poster.setStatus("Graduate");
        g_poster.setDepartment("Computer Science");
        g_poster.setEmail("gradposter@email.com");

        posterDao.create(g_poster);
        assertNotNull(g_poster.getId());

        Poster u_poster = new Poster();
        u_poster.setPoster_id("U01");
        u_poster.setPin(1234);
        u_poster.setFirst_name("FirstName");
        u_poster.setLast_name("LastName");
        u_poster.setTitle("This is the Undergraduate Poster Title");
        u_poster.setStatus("Undergraduate");
        u_poster.setDepartment("Electrical Engineering");
        u_poster.setEmail("undergradposter@email.com");

        posterDao.create(u_poster);
        assertNotNull(u_poster.getId());
    }

    /**
     * Verify that {@link PosterDao#create} is working correctly when a request for creating a null object is made.
     */
    @Test(expected = RuntimeException.class)
    public void createNullPoster() {
        posterDao.create(null);
    }


    /**
     * Verify that {@link PosterDao#create} is working correctly when a request for a {@link Poster} with a non-null id is made.
     */
    @Test
    public void createNonNullPosterId(){
        Poster createPoster = TestDataUtility.posterWithTestValues();
        createPoster.setId(new Random().longs(1L, Long.MAX_VALUE).findAny().getAsLong());
        posterDao.create(createPoster);
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
        assertNotNull(createPoster.getId());

        Poster readPoster = posterDao.read(createPoster.getId());
        assertNotNull(readPoster);
        assertEquals(createPoster.getId(), readPoster.getId());
        assertEquals(createPoster, readPoster);
    }

    /**
     * Verify that {@link PosterDao#read} is working correctly when a request for a non-existent {@link Poster#getId} is made.
     */
    @Test
    public void readNonExistentPoster() {
        // create a random poster id that will not be in our local database
        Long id = new Random().longs(10000L, Long.MAX_VALUE).findAny().getAsLong();
        Poster poster = posterDao.read(id);
        assertNull(poster);
    }

    /**
     * Verify that {@link PosterDao#update} is working correctly.
     */
    @Test
    public void update() {
        Poster createPoster = TestDataUtility.posterWithTestValues();
        posterDao.create(createPoster);
        assertNotNull(createPoster.getId());

        Poster verifyCreatePoster = posterDao.read(createPoster.getId());
        assertNotNull(verifyCreatePoster);
        assertEquals(createPoster.getId(), verifyCreatePoster.getId());
        assertEquals(createPoster, verifyCreatePoster);

        Poster updatePoster = TestDataUtility.posterWithTestValues();
        updatePoster.setId(createPoster.getId());
        posterDao.update(updatePoster);

        Poster verifyUpdatePoster = posterDao.read(updatePoster.getId());
        assertNotNull(verifyUpdatePoster);
        assertEquals(createPoster.getId(), verifyUpdatePoster.getId());
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
     * Verify that {@link PosterDao#update} is working correctly when a request for a non-existent {@link Poster#getId} is made.
     */
    @Test(expected = RuntimeException.class)
    public void updateNonExistentPoster() {
        // create a random poster id that will not be in our local database
        Poster updatePoster = TestDataUtility.posterWithTestValues();
        updatePoster.setId(new Random().longs(10000L, Long.MAX_VALUE).findAny().getAsLong());
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
        assertNotNull(createPoster.getId());

        Poster verifyCreatePoster = posterDao.read(createPoster.getId());
        assertNotNull(verifyCreatePoster);
        assertEquals(createPoster.getId(), verifyCreatePoster.getId());
        assertEquals(createPoster, verifyCreatePoster);

        Poster updatePoster = TestDataUtility.posterWithTestValues();
        updatePoster.setId(createPoster.getId());
        updatePoster.setFirst_name(RandomStringUtils.randomAlphabetic(2000));
        posterDao.update(updatePoster);
    }

    /**
     * Verify that {@link PosterDao#delete} is working correctly.
     */
    @Test
    public void delete() {
        Poster createPoster = TestDataUtility.posterWithTestValues();
        posterDao.create(createPoster);
        assertNotNull(createPoster.getId());

        Poster verifyCreatePoster = posterDao.read(createPoster.getId());
        assertNotNull(verifyCreatePoster);
        assertEquals(createPoster.getId(), verifyCreatePoster.getId());
        assertEquals(createPoster, verifyCreatePoster);

        posterDao.delete(createPoster.getId());

        Poster verifyDeletePoster = posterDao.read(createPoster.getId());
        assertNull(verifyDeletePoster);
    }

    /**
     * Verify that {@link PosterDao#delete} is working correctly when a request for a non-existent {@link Poster#getId} is made.
     */
    @Test(expected = RuntimeException.class)
    public void deleteNonExistentPoster() {
        Long id = new Random().longs(10000L, Long.MAX_VALUE).findAny().getAsLong();
        posterDao.delete(id);
    }


}

