package com.example.webserver.service;

import com.example.webserver.PlayerTable;
import com.example.webserver.RankingTable;

import java.util.List;

public interface GamePlayerService {
    public List<PlayerTable> getPlayerTable();
    public int getPlayerTableSum();
    public void insertPlayerTable(PlayerTable playerTable);
    public List<RankingTable> getRankingTable();
    public void insertRankingTable(RankingTable rankingTable);
}
