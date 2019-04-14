package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Score.Score;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.uwm.capstone.UnitTestConfig;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import edu.uwm.capstone.util.TestDataUtility;
import static edu.uwm.capstone.sql.dao.BaseRowMapper.dateFromJavaTime;
import static edu.uwm.capstone.sql.dao.BaseRowMapper.javaTimeFromDate;
import static edu.uwm.capstone.util.TestDataUtility.randomLocalDateTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UnitTestConfig.class)
public class ScoreDaoRowMapperUnitTest{
    @Autowired
    ScoreDaoRowMapper scoreDaoRowMapper;

    @Mock
    private ResultSet resultset;

    @Before
    public void setUp() throws IOException{
        assertNotNull(scoreDaoRowMapper);
    }

    /**
     * Verify that {@link ScoreDaoRowMapper.ScoreColumnType#values()} is working correctly.
     */
    @Test
    public void ScoreColumnType() {
        for(ScoreDaoRowMapper.ScoreColumnType columnType : ScoreDaoRowMapper.ScoreColumnType.values()){
            assertNotNull(columnType.name());
            assertNotNull(columnType.getColumnName());
        }
    }

    /**
     * Verify that {@link ScoreDaoRowMapper#mapObject} is working correctly.
     */
    @Test
    public void mapObject(){
        Score score = TestDataUtility.scoreWithTestValues();
        assertNotNull(score);

        Map<String, Object> mapObject = scoreDaoRowMapper.mapObject(score);

        assertEquals(score.getPoster_id(),mapObject.get(ScoreDaoRowMapper.ScoreColumnType.POSTER_ID.getColumnName()));
        assertEquals(score.getJudge_id(),mapObject.get(ScoreDaoRowMapper.ScoreColumnType.JUDGE_ID.getColumnName()));
        assertEquals(score.getComm_score(),mapObject.get(ScoreDaoRowMapper.ScoreColumnType.COMM_SCORE.getColumnName()));
        assertEquals(score.getResearch_score(),mapObject.get(ScoreDaoRowMapper.ScoreColumnType.RESEARCH_SCORE.getColumnName()));
        assertEquals(score.getPoster_score(),mapObject.get(ScoreDaoRowMapper.ScoreColumnType.POSTER_SCORE.getColumnName()));
        assertEquals(score.getTotal_score(),mapObject.get(ScoreDaoRowMapper.ScoreColumnType.TOTAL_SCORE.getColumnName()));
        assertEquals(score.getRound(),mapObject.get(ScoreDaoRowMapper.ScoreColumnType.ROUND.getColumnName()));
    }

    /**
     * Verify that {@link ScoreDaoRowMapper#mapRow} is working correctly.
     */
    @Test
    public void mapRow() throws SQLException{
        Score score = TestDataUtility.scoreWithTestValues();
        assertNotNull(score);

        when(resultset.getString(ScoreDaoRowMapper.ScoreColumnType.POSTER_ID.getColumnName())).thenReturn(score.getPoster_id());
        when(resultset.getLong(ScoreDaoRowMapper.ScoreColumnType.JUDGE_ID.getColumnName())).thenReturn(score.getJudge_id());
        when(resultset.getInt(ScoreDaoRowMapper.ScoreColumnType.COMM_SCORE.getColumnName())).thenReturn(score.getComm_score());
        when(resultset.getInt(ScoreDaoRowMapper.ScoreColumnType.RESEARCH_SCORE.getColumnName())).thenReturn(score.getResearch_score());
        when(resultset.getInt(ScoreDaoRowMapper.ScoreColumnType.POSTER_SCORE.getColumnName())).thenReturn(score.getPoster_score());
        when(resultset.getInt(ScoreDaoRowMapper.ScoreColumnType.TOTAL_SCORE.getColumnName())).thenReturn(score.getTotal_score());
        when(resultset.getInt(ScoreDaoRowMapper.ScoreColumnType.ROUND.getColumnName())).thenReturn(score.getRound());

        Score verifyResult = scoreDaoRowMapper.mapRow(resultset,0);
        assertNotNull(verifyResult);

        assertEquals(score.getJudge_id(),verifyResult.getJudge_id());
        assertEquals(score.getPoster_id(),verifyResult.getPoster_id());
        assertEquals(score.getComm_score(),verifyResult.getComm_score());
        assertEquals(score.getResearch_score(),verifyResult.getResearch_score());
        assertEquals(score.getPoster_score(),verifyResult.getPoster_score());
        assertEquals(score.getTotal_score(),verifyResult.getTotal_score());
        assertEquals(score.getRound(),verifyResult.getRound());
    }

}