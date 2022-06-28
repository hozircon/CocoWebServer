package com.example.webserver.dao;

import com.example.webserver.PlayerTable;
import com.example.webserver.RankingRowMapper;
import com.example.webserver.RankingTable;
import com.example.webserver.TotalPlayerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GamePlayerDaoImpl implements GamePlayerDao{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<PlayerTable> getPlayerTable() {
        String sql = "SELECT id, play_survival_time FROM player";
        Map<String,Object> map = new HashMap<>();

        List<PlayerTable> list = namedParameterJdbcTemplate.query(sql,map,new TotalPlayerRowMapper());
        return list;
    }

    @Override
    public void insertPlayerTable(PlayerTable playerTable){
        String sql = "INSERT INTO player(play_survival_time) VALUE(:PlaySurvivalTime)";
        Map<String,Object> map = new HashMap<>();
        map.put("PlaySurvivalTime",playerTable.getPlaySurvivalTime());
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public List<RankingTable> getRankingTable() {
        String sql = "SELECT id,name,game_time  FROM ranking order by game_time desc limit 15";
        Map<String,Object> map = new HashMap<>();

        List<RankingTable> list = namedParameterJdbcTemplate.query(sql,map,new RankingRowMapper());
        return list;
    }

    @Override
    public void insertRankingTable(RankingTable rankingTable) {
        String sql = "INSERT INTO ranking(name,game_time) VALUE(:PlayerName,:PlayerGameTime)";
        Map<String,Object> map = new HashMap<>();
        map.put("PlayerName",rankingTable.getName());
        map.put("PlayerGameTime",rankingTable.getGameTime());
        namedParameterJdbcTemplate.update(sql,map);
    }
}
