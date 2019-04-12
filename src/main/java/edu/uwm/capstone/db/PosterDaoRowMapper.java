package edu.uwm.capstone.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import edu.uwm.capstone.model.Poster.Poster;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import static edu.uwm.capstone.db.PosterDaoRowMapper.PosterColumnType.*;
import static edu.uwm.capstone.sql.dao.BaseRowMapper.BaseColumnType.*;

public class PosterDaoRowMapper extends BaseRowMapper<Poster> {

    public enum PosterColumnType{
        POSTER_ID,
        TITLE(),
        EMAIL(),
        FIRST_NAME(),
        LAST_NAME(),
        STATUS(),
        PIN(),
        DEPARTMENT(),
        VOTED_FOR(),
        ROLE(),
        AVG_R1(),
        AVG_R2(),
        AVG_COMM_R1(),
        AVG_COMM_R2(),
        AVG_RESEARCH_R1(),
        AVG_RESEARCH_R2(),
        AVG_PRES_R1(),
        AVG_PRES_R2()
        ;

        private String columnName;
        PosterColumnType(){
            columnName = name().toLowerCase();
        }

        PosterColumnType(String columnName){
            this.columnName = columnName;
        }

        public String getColumnName(){
            return columnName;
        }
    }

    @Override
    public Poster mapRow(ResultSet rs, int rowNum) throws SQLException {
        Poster poster = new Poster();
        poster.setPoster_id(rs.getString(POSTER_ID.getColumnName()));
        poster.setTitle(rs.getString(TITLE.getColumnName()));
        poster.setEmail(rs.getString(EMAIL.getColumnName()));
        poster.setFirst_name(rs.getString(FIRST_NAME.getColumnName()));
        poster.setLast_name(rs.getString(LAST_NAME.getColumnName()));
        poster.setStatus(rs.getString(STATUS.getColumnName()));
        poster.setPin(rs.getString(PIN.getColumnName()));
        poster.setDepartment(rs.getString(DEPARTMENT.getColumnName()));
        poster.setVoted_for(rs.getString(VOTED_FOR.getColumnName()));
        poster.setRole(rs.getString(ROLE.getColumnName()));
        poster.setAvg_r1(getNullableDouble(AVG_R1.getColumnName(), rs));
        poster.setAvg_r2(getNullableDouble(AVG_R2.getColumnName(), rs));
        poster.setAvg_comm_r1(getNullableDouble(AVG_COMM_R1.getColumnName(), rs));
        poster.setAvg_comm_r2(getNullableDouble(AVG_COMM_R2.getColumnName(), rs));
        poster.setAvg_research_r1(getNullableDouble(AVG_RESEARCH_R1.getColumnName(), rs));
        poster.setAvg_research_r2(getNullableDouble(AVG_RESEARCH_R2.getColumnName(), rs));
        poster.setAvg_pres_r1(getNullableDouble(AVG_PRES_R1.getColumnName(), rs));
        poster.setAvg_pres_r2(getNullableDouble(AVG_PRES_R2.getColumnName(), rs));
        return poster;
    }

    private Double getNullableDouble(String colName, ResultSet rs) throws SQLException {
        Double colValue = rs.getDouble(colName);
        if(rs.wasNull()){
            return null;
        }
        return colValue;
    }

    @Override
    public Map<String, Object> mapObject(Poster object) {
        Map<String,Object> map = new HashMap<>();
        map.put(POSTER_ID.getColumnName(), object.getPoster_id());
        map.put(TITLE.getColumnName(),object.getTitle());
        map.put(EMAIL.getColumnName(),object.getEmail());
        map.put(FIRST_NAME.getColumnName(),object.getFirst_name());
        map.put(LAST_NAME.getColumnName(),object.getLast_name());
        map.put(STATUS.getColumnName(),object.getStatus());
        map.put(PIN.getColumnName(),object.getPin());
        map.put(DEPARTMENT.getColumnName(),object.getDepartment());
        map.put(VOTED_FOR.getColumnName(), object.getVoted_for());
        map.put(ROLE.getColumnName(), object.getRole());
        map.put(AVG_R1.getColumnName(),object.getAvg_r1());
        map.put(AVG_R2.getColumnName(),object.getAvg_r2());
        map.put(AVG_COMM_R1.getColumnName(),object.getAvg_comm_r1());
        map.put(AVG_COMM_R2.getColumnName(),object.getAvg_comm_r2());
        map.put(AVG_RESEARCH_R1.getColumnName(),object.getAvg_research_r1());
        map.put(AVG_RESEARCH_R2.getColumnName(),object.getAvg_research_r2());
        map.put(AVG_PRES_R1.getColumnName(),object.getAvg_pres_r1());
        map.put(AVG_PRES_R2.getColumnName(),object.getAvg_pres_r2());


        return map;
    }

}