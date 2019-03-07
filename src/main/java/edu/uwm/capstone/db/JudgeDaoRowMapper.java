package edu.uwm.capstone.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import static edu.uwm.capstone.db.JudgeDaoRowMapper.JudgeColumnType.FIRST_NAME;
import static edu.uwm.capstone.db.JudgeDaoRowMapper.JudgeColumnType.LAST_NAME;
import static edu.uwm.capstone.db.JudgeDaoRowMapper.JudgeColumnType.EMAIL;
import static edu.uwm.capstone.db.JudgeDaoRowMapper.JudgeColumnType.STATUS;
import static edu.uwm.capstone.db.JudgeDaoRowMapper.JudgeColumnType.PROJECT;
import static edu.uwm.capstone.sql.dao.BaseRowMapper.BaseColumnType.*;

public class JudgeDaoRowMapper extends BaseRowMapper<Judge> {
    public enum JudgeColumnType {
        FIRST_NAME(),
        LAST_NAME(),
        EMAIL(),
        STATUS(),
        PROJECT(),
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
        map.put(ID.getColumnName(), object.getJudgeID());
        map.put(FIRST_NAME.getColumnName(), object.getJudgeFirstName());
        map.put(LAST_NAME.getColumnName(), object.getJudgeLastName());
        map.put(EMAIL.getColumnName(), object.getJudgeEmail());
        map.put(STATUS.getColumnName(), object.getJudgeStatus());


        return map;
    }

    @Override
    public Judge mapRow(ResultSet rs, int rowNum) throws SQLException {
        Judge folder = new Judge();
        folder.setJudgeID(rs.getLong(ID.getColumnName()));
        folder.setJudgeFirstName(rs.getString(FIRST_NAME.getColumnName()));
        folder.setJudgeLastName(rs.getString(LAST_NAME.getColumnName()));
        folder.setJudgeEmail(rs.getString(EMAIL.getColumnName()));
        folder.setJudgeStatus(rs.getString(STATUS.getColumnName()));
        return folder;
    }
}
