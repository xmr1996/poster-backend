package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Round.Round;
import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.util.TestDataUtility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfig.class)
public class RoundDaoUnitTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    RoundDao roundDao;

    @Before
    public void setUp(){
        assertNotNull(roundDao);
        assertNotNull(roundDao.sql("readAllRounds"));
        assertNotNull(roundDao.sql("updateRound1"));
        assertNotNull(roundDao.sql("updateRound2"));
    }

    @Test
    public void create(){
        Round roundToCreate = TestDataUtility.roundWithTestValues();
        Round round = roundDao.create(roundToCreate);
        assertNull(round);
    }

    @Test
    public void read(){
        Round readRound = roundDao.read();
        assertNotNull(readRound);
        assertFalse(readRound.isRound1());
        assertTrue(readRound.isRound2());
    }


    @Test
    public void update(){
        Round readRound = roundDao.read();
        assertNotNull(readRound);
        assertFalse(readRound.isRound1());
        assertFalse(readRound.isRound2());
        roundDao.update(1,false);
        roundDao.update(2, true);
        Round updatedRound = roundDao.read();
        assertNotNull(updatedRound);
        assertFalse(updatedRound.isRound1());
        assertTrue(updatedRound.isRound2());
    }

    @Test
    public void updateInvalidRound(){
        int val = roundDao.update(3,false);
        assertTrue(val == 0);
    }

}