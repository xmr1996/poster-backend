package edu.uwm.capstone.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.sql.dao.BaseRowMapper;

import static edu.uwm.capstone.db.JudgeDaoRowMapper.JudgeColumnType.*;
import static edu.uwm.capstone.sql.dao.BaseRowMapper.BaseColumnType.*;

public class JudgeDaoRowMapper extends BaseRowMapper<Judge> {
    public enum JudgeColumnType {
        JUDGE_ID(),
        FIRST_NAME(),
        LAST_NAME(),
        EMAIL(),
        STATUS(),
        PIN(),
        ROLE()
        ;

        private String columnName;

        JudgeColumnType() {
            columnName = name().toLowerCase();
        }

        JudgeColumnType(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnName(){
            return columnName;
        }
    }

    @Override
    public Map<String, Object> mapObject(Judge object) {
        Map<String, Object> map = new HashMap<>();
        map.put(ID.getColumnName(), object.getId());
        map.put(JUDGE_ID.getColumnName(), object.getJudge_id());
        map.put(FIRST_NAME.getColumnName(), object.getFirst_name());
        map.put(LAST_NAME.getColumnName(), object.getLast_name());
        map.put(EMAIL.getColumnName(), object.getEmail());
        map.put(STATUS.getColumnName(), object.getStatus());
        map.put(PIN.getColumnName(), object.getPin());
        map.put(ROLE.getColumnName(), object.getRole());
        return map;
    }

    @Override
    public Judge mapRow(ResultSet rs, int rowNum) throws SQLException {
        Judge folder = new Judge();
        folder.setId(rs.getLong(ID.getColumnName()));
        folder.setJudge_id(rs.getLong(JUDGE_ID.getColumnName()));
        folder.setFirst_name(rs.getString(FIRST_NAME.getColumnName()));
        folder.setLast_name(rs.getString(LAST_NAME.getColumnName()));
        folder.setEmail(rs.getString(EMAIL.getColumnName()));
        folder.setStatus(rs.getString(STATUS.getColumnName()));
        folder.setPin(rs.getString(PIN.getColumnName()));
        folder.setRole(rs.getString(ROLE.getColumnName()));
        return folder;
    }
}
