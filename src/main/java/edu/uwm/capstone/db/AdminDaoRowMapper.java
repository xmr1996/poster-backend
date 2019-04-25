package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Admin.Admin;
import edu.uwm.capstone.sql.dao.BaseRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static edu.uwm.capstone.db.AdminDaoRowMapper.AdminColumnType.*;

public class AdminDaoRowMapper extends BaseRowMapper<Admin> {

    public enum AdminColumnType {
        EMAIL(),
        FIRST_NAME(),
        LAST_NAME(),
        READ_R(),
        WRITE_W(),
        PIN(),
        ROLE(),
        ;

        private String columnName;

        AdminColumnType() {
            columnName = name().toLowerCase();
        }

        public String getColumnName() {
            return columnName;
        }
    }

    public Map<String, Object> mapObject(Admin object) {
        Map<String, Object> map = new HashMap<>();
        map.put(EMAIL.getColumnName(), object.getEmail());
        map.put(FIRST_NAME.getColumnName(), object.getFirst_name());
        map.put(LAST_NAME.getColumnName(), object.getLast_name());
        map.put(READ_R.getColumnName(), object.isRead_r());
        map.put(WRITE_W.getColumnName(), object.isWrite_w());
        map.put(PIN.getColumnName(), object.getPin());
        map.put(ROLE.getColumnName(), object.getRole());
        return map;
    }

    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Admin folder = new Admin();
        folder.setEmail(rs.getString(EMAIL.getColumnName()));
        folder.setFirst_name(rs.getString(FIRST_NAME.getColumnName()));
        folder.setLast_name(rs.getString(LAST_NAME.getColumnName()));
        folder.setRead_r(rs.getBoolean(READ_R.getColumnName()));
        folder.setWrite_w(rs.getBoolean(WRITE_W.getColumnName()));
        folder.setPin(rs.getString(PIN.getColumnName()));
        folder.setRole(rs.getString(ROLE.getColumnName()));
        return folder;
    }


}
