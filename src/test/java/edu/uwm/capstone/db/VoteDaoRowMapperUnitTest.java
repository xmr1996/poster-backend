package edu.uwm.capstone.db;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.model.Vote.Vote;
import edu.uwm.capstone.util.TestDataUtility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfig.class)
public class VoteDaoRowMapperUnitTest {

    @Autowired
    VoteDaoRowMapper voteDaoRowMapper;

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() {
        assertNotNull(voteDaoRowMapper);
    }

    /**
     * Verify that {@link VoteDaoRowMapper.VoteColumnType#values()} is working correctly.
     */
    @Test
    public void voteColumnType() {
        for (VoteDaoRowMapper.VoteColumnType columnType : VoteDaoRowMapper.VoteColumnType.values()) {
            assertNotNull(columnType.name());
            assertNotNull(columnType.getColumnName());
        }
    }

    /**
     * Verify that {@link VoteDaoRowMapper#mapObject} is working correctly.
     */
    @Test
    public void mapObject() {
        // generate a vote object with test values
        Vote vote = TestDataUtility.voteWithTestValues();
        assertNotNull(vote);

        Map<String, Object> mapObject = voteDaoRowMapper.mapObject(vote);
        assertNotNull(mapObject);

        assertEquals(vote.getPoster_id(), mapObject.get(VoteDaoRowMapper.VoteColumnType.POSTER_ID.getColumnName()));
        assertEquals(vote.getVotes(), mapObject.get(VoteDaoRowMapper.VoteColumnType.VOTES.getColumnName()));
    }

    /**
     * Verify that {@link ProfileDaoRowMapper#mapRow} is working correctly.
     */
    @Test
    public void mapRow() throws SQLException {
        // generate a profile object with test values
        Vote vote = TestDataUtility.voteWithTestValues();
        assertNotNull(vote);

        // define the behavior of the resultSet that is being mocked
        when(resultSet.getString(VoteDaoRowMapper.VoteColumnType.POSTER_ID.getColumnName())).thenReturn(vote.getPoster_id());
        when(resultSet.getInt(VoteDaoRowMapper.VoteColumnType.VOTES.getColumnName())).thenReturn(vote.getVotes());

        // exercise the mapRow functionality and verify the expected results
        Vote verifyVote = voteDaoRowMapper.mapRow(resultSet, 0);
        assertNotNull(verifyVote);

        assertEquals(vote.getPoster_id(), verifyVote.getPoster_id());
        assertEquals(vote.getVotes(), verifyVote.getVotes());
    }

}