package edu.uwm.capstone.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import edu.uwm.capstone.model.PosterScore.PosterScore;
import edu.uwm.capstone.sql.dao.BaseRowMapper;

import static edu.uwm.capstone.db.PosterScoreRowMapper.PosterScoreColumnType.*;

public class PosterScoreRowMapper extends BaseRowMapper<PosterScore> {
    public enum PosterScoreColumnType{
        TITLE(),
        EMAIL(),
        FIRST_NAME(),
        LAST_NAME(),
        STATUS(),
        PIN(),
        DEPARTMENT(),
        ROLE(),
        POSTER_ID(),
        JUDGE_ID(),
        ROUND(),
        RESEARCH_SCORE(),
        COMM_SCORE(),
        POSTER_SCORE()
        ;

        private String columnName;

        PosterScoreColumnType() {
            columnName = name().toLowerCase();
        }

        public String getColumnName(){
            return columnName;
        }
    }

    @Override
    public Map<String, Object> mapObject(PosterScore object) {
        Map<String, Object> map = new HashMap<>();
        map.put(JUDGE_ID.getColumnName(), object.getJudge_id());
        map.put(ROUND.getColumnName(), object.getRound());
        map.put(RESEARCH_SCORE.getColumnName(), object.getResearch_score());
        map.put(COMM_SCORE.getColumnName(), object.getComm_score());
        map.put(POSTER_SCORE.getColumnName(), object.getPoster_score());
        map.put(POSTER_ID.getColumnName(), object.getPoster_id());
        map.put(TITLE.getColumnName(),object.getTitle());
        map.put(EMAIL.getColumnName(),object.getEmail());
        map.put(FIRST_NAME.getColumnName(),object.getFirst_name());
        map.put(LAST_NAME.getColumnName(),object.getLast_name());
        map.put(STATUS.getColumnName(),object.getStatus());
        map.put(PIN.getColumnName(),object.getPin());
        map.put(DEPARTMENT.getColumnName(),object.getDepartment());
        map.put(ROLE.getColumnName(), object.getRole());
        return map;
    }

    @Override
    public PosterScore mapRow(ResultSet rs, int rowNum) throws SQLException {
        PosterScore folder = new PosterScore();
        folder.setJudge_id(rs.getLong(JUDGE_ID.getColumnName()));
        folder.setRound(rs.getInt(ROUND.getColumnName()));
        folder.setResearch_score(getNullableInt(RESEARCH_SCORE.getColumnName(), rs));
        folder.setComm_score(getNullableInt(COMM_SCORE.getColumnName(), rs));
        folder.setPoster_score(getNullableInt(POSTER_SCORE.getColumnName(), rs));
        folder.setPoster_id(rs.getString(PosterDaoRowMapper.PosterColumnType.POSTER_ID.getColumnName()));
        folder.setTitle(rs.getString(TITLE.getColumnName()));
        folder.setEmail(rs.getString(EMAIL.getColumnName()));
        folder.setFirst_name(rs.getString(FIRST_NAME.getColumnName()));
        folder.setLast_name(rs.getString(LAST_NAME.getColumnName()));
        folder.setStatus(rs.getString(STATUS.getColumnName()));
        folder.setPin(rs.getInt(PIN.getColumnName()));
        folder.setDepartment(rs.getString(DEPARTMENT.getColumnName()));
        folder.setRole(rs.getString(ROLE.getColumnName()));
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
