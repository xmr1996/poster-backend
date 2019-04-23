package edu.uwm.capstone.db;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.model.PosterScore.PosterScore;
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
public class PosterScoreRowMapperTest {

    @Autowired
    PosterScoreRowMapper posterScoreRowMapper;

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp(){
        assertNotNull(posterScoreRowMapper);
    }

    /**
     * Verify that {@link ProfileDaoRowMapper.ProfileColumnType#values()} is working correctly.
     */
    @Test
    public void posterScoreColumnType() {
        for (PosterScoreRowMapper.PosterScoreColumnType columnType : PosterScoreRowMapper.PosterScoreColumnType.values()) {
            assertNotNull(columnType.name());
            assertNotNull(columnType.getColumnName());
        }
    }

    /**
     * Verify that {@link ProfileDaoRowMapper#mapObject} is working correctly.
     */
    @Test
    public void mapObject() {
        // generate a profile object with test values
        PosterScore posterScore = TestDataUtility.posterScoreWithTestValues();
        assertNotNull(posterScore);

        Map<String, Object> mapObject = posterScoreRowMapper.mapObject(posterScore);
        assertNotNull(mapObject);

        assertEquals(posterScore.getJudge_id(), mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.JUDGE_ID.getColumnName()));
        assertEquals(posterScore.getRound(), mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.ROUND.getColumnName()));
        assertEquals(posterScore.getResearch_score(), mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.RESEARCH_SCORE.getColumnName()));
        assertEquals(posterScore.getComm_score(), mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.COMM_SCORE.getColumnName()));
        assertEquals(posterScore.getPoster_score(), (mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.POSTER_SCORE.getColumnName())));
        assertEquals(posterScore.getPoster_id(), mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.POSTER_ID.getColumnName()));
        assertEquals(posterScore.getTitle(), mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.TITLE.getColumnName()));
        assertEquals(posterScore.getEmail(), mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.EMAIL.getColumnName()));
        assertEquals(posterScore.getFirst_name(), mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.FIRST_NAME.getColumnName()));
        assertEquals(posterScore.getLast_name(), mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.LAST_NAME.getColumnName()));
        assertEquals(posterScore.getStatus(), mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.STATUS.getColumnName()));
        assertEquals(posterScore.getPin(), mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.PIN.getColumnName()));
        assertEquals(posterScore.getDepartment(), mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.DEPARTMENT.getColumnName()));
        assertEquals(posterScore.getRole(), mapObject.get(PosterScoreRowMapper.PosterScoreColumnType.ROLE.getColumnName()));
    }


    /**
     * Verify that {@link PosterScoreRowMapper#mapRow} is working correctly.
     */
    @Test
    public void mapRow() throws SQLException {
        // generate a profile object with test values
        PosterScore posterScore = TestDataUtility.posterScoreWithTestValues();
        assertNotNull(posterScore);

        // define the behavior of the resultSet that is being mocked
        when(resultSet.getLong(PosterScoreRowMapper.PosterScoreColumnType.JUDGE_ID.getColumnName())).thenReturn(posterScore.getJudge_id());
        when(resultSet.getInt(PosterScoreRowMapper.PosterScoreColumnType.ROUND.getColumnName())).thenReturn(posterScore.getRound());
        when(resultSet.getInt(PosterScoreRowMapper.PosterScoreColumnType.RESEARCH_SCORE.getColumnName())).thenReturn(posterScore.getResearch_score());
        when(resultSet.getInt(PosterScoreRowMapper.PosterScoreColumnType.COMM_SCORE.getColumnName())).thenReturn(posterScore.getComm_score());
        when(resultSet.getInt(PosterScoreRowMapper.PosterScoreColumnType.POSTER_SCORE.getColumnName())).thenReturn(posterScore.getPoster_score());
        when(resultSet.getString(PosterScoreRowMapper.PosterScoreColumnType.POSTER_ID.getColumnName())).thenReturn(posterScore.getPoster_id());
        when(resultSet.getString(PosterScoreRowMapper.PosterScoreColumnType.TITLE.getColumnName())).thenReturn(posterScore.getTitle());
        when(resultSet.getString(PosterScoreRowMapper.PosterScoreColumnType.EMAIL.getColumnName())).thenReturn(posterScore.getEmail());
        when(resultSet.getString(PosterScoreRowMapper.PosterScoreColumnType.FIRST_NAME.getColumnName())).thenReturn(posterScore.getFirst_name());
        when(resultSet.getString(PosterScoreRowMapper.PosterScoreColumnType.LAST_NAME.getColumnName())).thenReturn(posterScore.getLast_name());
        when(resultSet.getString(PosterScoreRowMapper.PosterScoreColumnType.STATUS.getColumnName())).thenReturn(posterScore.getStatus());
        when(resultSet.getInt(PosterScoreRowMapper.PosterScoreColumnType.PIN.getColumnName())).thenReturn(posterScore.getPin());
        when(resultSet.getString(PosterScoreRowMapper.PosterScoreColumnType.DEPARTMENT.getColumnName())).thenReturn(posterScore.getDepartment());
        when(resultSet.getString(PosterScoreRowMapper.PosterScoreColumnType.ROLE.getColumnName())).thenReturn(posterScore.getRole());

        // exercise the mapRow functionality and verify the expected results
        PosterScore verifyPosterScore = posterScoreRowMapper.mapRow(resultSet, 0);
        assertNotNull(verifyPosterScore);

        assertEquals(posterScore.getJudge_id(), verifyPosterScore.getJudge_id());
        assertEquals(posterScore.getRound(), verifyPosterScore.getRound());
        assertEquals(posterScore.getResearch_score(), verifyPosterScore.getResearch_score());
        assertEquals(posterScore.getComm_score(), verifyPosterScore.getComm_score());
        assertEquals(posterScore.getPoster_score(), verifyPosterScore.getPoster_score());
        assertEquals(posterScore.getPoster_id(), verifyPosterScore.getPoster_id());
        assertEquals(posterScore.getTitle(), verifyPosterScore.getTitle());
        assertEquals(posterScore.getEmail(), verifyPosterScore.getEmail());
        assertEquals(posterScore.getFirst_name(), verifyPosterScore.getFirst_name());
        assertEquals(posterScore.getLast_name(), verifyPosterScore.getLast_name());
        assertEquals(posterScore.getStatus(), verifyPosterScore.getStatus());
        assertEquals(posterScore.getPin(), verifyPosterScore.getPin());
        assertEquals(posterScore.getDepartment(), verifyPosterScore.getDepartment());
        assertEquals(posterScore.getRole(), verifyPosterScore.getRole());
    }
}
