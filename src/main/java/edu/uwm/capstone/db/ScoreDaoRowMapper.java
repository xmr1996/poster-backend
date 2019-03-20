package edu.uwm.capstone.db;


import edu.uwm.capstone.sql.dao.BaseRowMapper;
import edu.uwm.capstone.model.Score.Score;
//import static edu.uwm.capstone.db.ScoreDaoRowMapper.ScoreColumnType.CREATED_DATE;
//import static edu.uwm.capstone.db.ScoreDaoRowMapper.ScoreColumnType.UPDATED_DATE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static edu.uwm.capstone.db.ScoreDaoRowMapper.ScoreColumnType.*;
import static edu.uwm.capstone.sql.dao.BaseRowMapper.BaseColumnType.*;

public class ScoreDaoRowMapper extends BaseRowMapper<Score> {

    public enum ScoreColumnType {
        POSTER_ID(),
        JUDGE_ID(),
        ROUND(),
        RESEARCH_SCORE(),
        COMM_SCORE(),
        POSTER_SCORE(),
        TOTAL_SCORE()
//        CREATED_DATE(),
//        UPDATED_DATE()
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
        map.put(ID.getColumnName(), object.getId());
        map.put(POSTER_ID.getColumnName(), object.getPoster_id());
        map.put(JUDGE_ID.getColumnName(), object.getJudge_id());
        map.put(ROUND.getColumnName(), object.getRound());
        map.put(RESEARCH_SCORE.getColumnName(), object.getResearch_score());
        map.put(COMM_SCORE.getColumnName(), object.getComm_score());
        map.put(POSTER_SCORE.getColumnName(), object.getPoster_score());
        map.put(TOTAL_SCORE.getColumnName(),object.getTotal_score());
//        map.put(CREATED_DATE.getColumnName(), javaTimeFromDate(object.getCreatedDate()));
//        map.put(UPDATED_DATE.getColumnName(), javaTimeFromDate(object.getUpdatedDate()));
        return map;
    }

    @Override
    public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
        Score folder = new Score();
        folder.setId(rs.getLong(ID.getColumnName()));
        folder.setPoster_id(rs.getString(POSTER_ID.getColumnName()));
        folder.setJudge_id(rs.getLong(JUDGE_ID.getColumnName()));
        folder.setRound(rs.getInt(ROUND.getColumnName()));
        folder.setResearch_score(rs.getInt(RESEARCH_SCORE.getColumnName()));
        folder.setComm_score(rs.getInt(COMM_SCORE.getColumnName()));
        folder.setPoster_score(rs.getInt(POSTER_SCORE.getColumnName()));
        folder.setTotal_score(rs.getInt(TOTAL_SCORE.getColumnName()));
//        folder.setCreatedDate(dateFromJavaTime(rs.getObject(CREATED_DATE.getColumnName())));
//        folder.setUpdatedDate(dateFromJavaTime(rs.getObject(UPDATED_DATE.getColumnName())));
        return folder;
    }

}
