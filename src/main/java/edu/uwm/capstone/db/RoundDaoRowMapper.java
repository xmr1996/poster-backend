package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Round.Round;
import edu.uwm.capstone.sql.dao.BaseRowMapper;

import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import static edu.uwm.capstone.db.RoundDaoRowMapper.RoundColumnType.*;

public class RoundDaoRowMapper extends BaseRowMapper<Round> {
    public enum RoundColumnType{
        ROUND1(),
        ROUND2(),
        ;
        private String columnName;


        RoundColumnType() {
            columnName = name().toLowerCase();
        }

        RoundColumnType(String columnName) {
            this.columnName = columnName;
        }

        public String getColumnName(){
            return columnName;
        }

    }

    @Override
    public Map<String,Object> mapObject(Round object){
        Map<String,Object> map = new HashMap<>();
        map.put(ROUND1.getColumnName(), object.isRound1());
        map.put(ROUND2.getColumnName(), object.isRound2());
        return map;
    }

    @Override
    public Round mapRow(ResultSet rs, int rowNum) throws SQLException{
        Round folder = new Round();
        folder.setRound1(rs.getBoolean(ROUND1.getColumnName()));
        folder.setRound2(rs.getBoolean(ROUND2.getColumnName()));
        return folder;
    }

}
