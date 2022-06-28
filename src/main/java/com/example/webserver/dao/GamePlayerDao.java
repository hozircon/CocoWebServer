package com.example.webserver.dao;

import com.example.webserver.PlayerTable;
import com.example.webserver.RankingTable;

import java.util.List;

public interface GamePlayerDao {
    List<PlayerTable> getPlayerTable();
    public void insertPlayerTable(PlayerTable playerTable);
    List<RankingTable> getRankingTable();
    public void insertRankingTable(RankingTable rankingTable);
}
