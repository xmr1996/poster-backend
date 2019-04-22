package edu.uwm.capstone.db;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import edu.uwm.capstone.model.Round.Round;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.util.TestDataUtility;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfig.class)
public class RoundDaoRowMapperUnitTest {

    @Autowired
    RoundDaoRowMapper roundDaoRowMapper;

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() throws IOException{
        assertNotNull(roundDaoRowMapper);
    }

    /**
     * Verify that {@link RoundDaoRowMapper.RoundColumnType#values()} is working correctly.
     */
    @Test
    public void RoundColumnType(){
        for(RoundDaoRowMapper.RoundColumnType columnType : RoundDaoRowMapper.RoundColumnType.values()){
            assertNotNull(columnType.name());
            assertNotNull(columnType.getColumnName());
        }
    }

    /**
     * Verify that {@link RoundDaoRowMapper#mapObject} is working correctly.
     */
    @Test
    public void mapObject(){
        Round round  = TestDataUtility.roundWithTestValues();
        assertNotNull(round);

        Map<String,Object> mapObject = roundDaoRowMapper.mapObject(round);
        assertEquals(round.isRound1(),mapObject.get(RoundDaoRowMapper.RoundColumnType.ROUND1.getColumnName()));
        assertEquals(round.isRound2(),mapObject.get(RoundDaoRowMapper.RoundColumnType.ROUND2.getColumnName()));
    }

    /**
     * Verify that {@link RoundDaoRowMapper#mapRow} is working correctly.
     */
    @Test
    public void mapRow() throws SQLException{
        Round round = TestDataUtility.roundWithTestValues();
        assertNotNull(round);


        when(resultSet.getBoolean(RoundDaoRowMapper.RoundColumnType.ROUND1.getColumnName())).thenReturn(round.isRound1());
        when(resultSet.getBoolean(RoundDaoRowMapper.RoundColumnType.ROUND2.getColumnName())).thenReturn(round.isRound2());

        Round verifyResult = roundDaoRowMapper.mapRow(resultSet,0);
        assertNotNull(verifyResult);

        assertEquals(round.isRound1(),verifyResult.isRound1());
        assertEquals(round.isRound2(),verifyResult.isRound2());

    }
}
