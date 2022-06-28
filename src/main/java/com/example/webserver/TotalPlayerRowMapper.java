package com.example.webserver;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TotalPlayerRowMapper implements RowMapper<PlayerTable> {

    @Override
    public PlayerTable mapRow(ResultSet resultSet, int i) throws SQLException {
        PlayerTable playerTable = new PlayerTable();
        playerTable.setId(resultSet.getInt("id"));
        playerTable.setPlaySurvivalTime(resultSet.getInt("play_survival_time"));

        return playerTable;
    }
}
