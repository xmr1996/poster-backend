package edu.uwm.capstone.db;

import edu.uwm.capstone.model.Vote.Vote;
import edu.uwm.capstone.sql.dao.BaseRowMapper;
import static edu.uwm.capstone.db.VoteDaoRowMapper.VoteColumnType.POSTER_ID;
import static edu.uwm.capstone.db.VoteDaoRowMapper.VoteColumnType.VOTES;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class VoteDaoRowMapper extends BaseRowMapper<Vote> {

    public enum VoteColumnType {
        POSTER_ID(),
        VOTES(),
        ;

        private String columnName;

        VoteColumnType() {
            columnName = name().toLowerCase();
        }

        public String getColumnName(){
            return columnName;
        }
    }

    @Override
    public Map<String, Object> mapObject(Vote object) {
        Map<String, Object> map = new HashMap<>();
        map.put(POSTER_ID.getColumnName(), object.getPoster_id());
        map.put(VOTES.getColumnName(), object.getVotes());
        return map;
    }

    @Override
    public Vote mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vote folder = new Vote();
        folder.setPoster_id(rs.getString(POSTER_ID.getColumnName()));
        folder.setVotes(rs.getInt(VOTES.getColumnName()));
        return folder;
    }

}
