package edu.uwm.capstone.db;


import edu.uwm.capstone.model.Assignment.Assignment;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import static edu.uwm.capstone.db.AssignmentDaoRowMapper.AssignmentColumnType.POSTER_ID;
import static edu.uwm.capstone.db.AssignmentDaoRowMapper.AssignmentColumnType.JUDGE_ID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


public class AssignmentDaoRowMapper extends BaseRowMapper<Assignment> {

    public enum AssignmentColumnType {
        POSTER_ID(),
        JUDGE_ID(),
        ;

        private String columnName;

        AssignmentColumnType() {
            columnName = name().toLowerCase();
        }

        public String getColumnName(){
            return columnName;
        }
    }


    public Map<String, Object> mapObject(Assignment object) {
        return null;
    }

    @Override
    public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Assignment folder = new Assignment();
        folder.setPoster_id(rs.getString(POSTER_ID.getColumnName()));
        folder.setJudge_id(rs.getLong(JUDGE_ID.getColumnName()));

        return folder;
    }

}
