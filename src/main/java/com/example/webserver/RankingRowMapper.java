package com.example.webserver;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RankingRowMapper implements RowMapper<RankingTable> {
    @Override
    public RankingTable mapRow(ResultSet resultSet, int i) throws SQLException {
        RankingTable rankingTable = new RankingTable();
        rankingTable.setId(resultSet.getInt("id"));
        rankingTable.setName(resultSet.getString("name"));
        rankingTable.setGameTime(resultSet.getInt("game_time"));

        return rankingTable;
    }
}
