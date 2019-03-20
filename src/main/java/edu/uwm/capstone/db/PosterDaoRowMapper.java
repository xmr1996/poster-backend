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
        HAS_VOTED(),
        VOTES(),
        ROLE()
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
        poster.setId(rs.getLong(ID.getColumnName()));
        poster.setPoster_id(rs.getString(POSTER_ID.getColumnName()));
        poster.setTitle(rs.getString(TITLE.getColumnName()));
        poster.setEmail(rs.getString(EMAIL.getColumnName()));
        poster.setFirst_name(rs.getString(FIRST_NAME.getColumnName()));
        poster.setLast_name(rs.getString(LAST_NAME.getColumnName()));
        poster.setStatus(rs.getString(STATUS.getColumnName()));
        poster.setPin(rs.getInt(PIN.getColumnName()));
        poster.setDepartment(rs.getString(DEPARTMENT.getColumnName()));
        poster.setHas_voted(rs.getString(HAS_VOTED.getColumnName()));
        poster.setVotes(rs.getInt(VOTES.getColumnName()));
        poster.setRole(rs.getString(ROLE.getColumnName()));
        return poster;
    }

    @Override
    public Map<String, Object> mapObject(Poster object) {
        Map<String,Object> map = new HashMap<>();
        map.put(ID.getColumnName(),object.getId());
        map.put(POSTER_ID.getColumnName(), object.getPoster_id());
        map.put(TITLE.getColumnName(),object.getTitle());
        map.put(EMAIL.getColumnName(),object.getEmail());
        map.put(FIRST_NAME.getColumnName(),object.getFirst_name());
        map.put(LAST_NAME.getColumnName(),object.getLast_name());
        map.put(STATUS.getColumnName(),object.getStatus());
        map.put(PIN.getColumnName(),object.getPin());
        map.put(DEPARTMENT.getColumnName(),object.getDepartment());
        map.put(HAS_VOTED.getColumnName(), object.getHas_voted());
        map.put(VOTES.getColumnName(), object.getVotes());
        map.put(ROLE.getColumnName(), object.getRole());
        return map;
    }

}