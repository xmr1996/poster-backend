package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.model.Score.Score;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import static edu.uwm.capstone.db.ScoreDaoRowMapper.ScoreColumnType.POSTERID;
import static edu.uwm.capstone.db.ScoreDaoRowMapper.ScoreColumnType.JUDGEID;
import static edu.uwm.capstone.db.ScoreDaoRowMapper.ScoreColumnType.ROUND;
import static edu.uwm.capstone.db.ScoreDaoRowMapper.ScoreColumnType.RESEARCHSCORE;
import static edu.uwm.capstone.db.ScoreDaoRowMapper.ScoreColumnType.COMMSCORE;
import static edu.uwm.capstone.db.ScoreDaoRowMapper.ScoreColumnType.POSTERSCORE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static edu.uwm.capstone.sql.dao.BaseRowMapper.BaseColumnType.*;

public class ScoreDaoRowMapper extends BaseRowMapper<Score> {

    public enum ScoreColumnType {
        POSTERID(),
        JUDGEID(),
        ROUND(),
        RESEARCHSCORE(),
        COMMSCORE(),
        POSTERSCORE()
        ;

        private String columnName;

        ScoreColumnType() {
            columnName = name().toLowerCase();
        }

        ScoreColumnType(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnName(){
            return columnName;
        }
    }

    @Override
    public Map<String, Object> mapObject(Score object) {
        Map<String, Object> map = new HashMap<>();
        map.put(ID.getColumnName(), object.getScoreID());
        map.put(POSTERID.getColumnName(), object.getPosterID());
        map.put(JUDGEID.getColumnName(), object.getJudgeID());
        map.put(ROUND.getColumnName(), object.getRound());
        map.put(RESEARCHSCORE.getColumnName(), object.getResearch_Score());
        map.put(COMMSCORE.getColumnName(), object.getComm_Score());
        map.put(POSTERSCORE.getColumnName(), object.getPoster_Score());
        map.put(CREATED_DATE.getColumnName(), javaTimeFromDate(object.getCreatedDate()));
        map.put(UPDATED_DATE.getColumnName(), javaTimeFromDate(object.getUpdatedDate()));
        return map;
    }

    @Override
    public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
        Score folder = new Score();
        folder.setScoreID(rs.getLong(ID.getColumnName()));
        folder.setPosterID((Poster) rs.getObject(POSTERID.getColumnName()));
        folder.setJudgeID((Judge) rs.getObject(JUDGEID.getColumnName()));
        folder.setRound(rs.getInt(ROUND.getColumnName()));
        folder.setResearch_Score(rs.getInt(RESEARCHSCORE.getColumnName()));
        folder.setComm_Score(rs.getInt(COMMSCORE.getColumnName()));
        folder.setPoster_Score(rs.getInt(POSTERSCORE.getColumnName()));
        folder.setCreatedDate(dateFromJavaTime(rs.getObject(CREATED_DATE.getColumnName())));
        folder.setUpdatedDate(dateFromJavaTime(rs.getObject(UPDATED_DATE.getColumnName())));
        return folder;
    }

}
