package edu.uwm.capstone.db;


import edu.uwm.capstone.sql.dao.BaseRowMapper;
import edu.uwm.capstone.model.Score.Score;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static edu.uwm.capstone.db.ScoreDaoRowMapper.ScoreColumnType.*;

public class ScoreDaoRowMapper extends BaseRowMapper<Score> {

    public enum ScoreColumnType {
        POSTER_ID(),
        JUDGE_ID(),
        ROUND(),
        RESEARCH_SCORE(),
        COMM_SCORE(),
        POSTER_SCORE(),
        TOTAL_SCORE()
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
        map.put(POSTER_ID.getColumnName(), object.getPoster_id());
        map.put(JUDGE_ID.getColumnName(), object.getJudge_id());
        map.put(ROUND.getColumnName(), object.getRound());
        map.put(RESEARCH_SCORE.getColumnName(), object.getResearch_score());
        map.put(COMM_SCORE.getColumnName(), object.getComm_score());
        map.put(POSTER_SCORE.getColumnName(), object.getPoster_score());
        map.put(TOTAL_SCORE.getColumnName(),object.getTotal_score());
        return map;
    }

    @Override
    public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
        Score folder = new Score();
        folder.setPoster_id(rs.getString(POSTER_ID.getColumnName()));
        folder.setJudge_id(rs.getLong(JUDGE_ID.getColumnName()));
        folder.setRound(rs.getInt(ROUND.getColumnName()));
        folder.setResearch_score(getNullableInt(RESEARCH_SCORE.getColumnName(), rs));
        folder.setComm_score(getNullableInt(COMM_SCORE.getColumnName(), rs));
        folder.setPoster_score(getNullableInt(POSTER_SCORE.getColumnName(), rs));
        folder.setTotal_score(getNullableInt(TOTAL_SCORE.getColumnName(), rs));
        return folder;
    }

    private Integer getNullableInt(String colName, ResultSet rs) throws SQLException {
        int colValue = rs.getInt(colName);
        if(rs.wasNull()){
            return null;
        }
        return colValue;
    }


}
