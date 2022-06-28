package com.example.webserver.service;

import com.example.webserver.PlayerTable;
import com.example.webserver.RankingTable;
import com.example.webserver.dao.GamePlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GamePlayerServiceImpl implements GamePlayerService{

    @Autowired
    private GamePlayerDao gamePlayerDao;

    @Override
    public List<PlayerTable> getPlayerTable() {
        return gamePlayerDao.getPlayerTable();
    }

    @Override
    public int getPlayerTableSum(){
        int timeSum = 0;
        List<PlayerTable> list = gamePlayerDao.getPlayerTable();
        for(int i= 0; i<list.size();i++){
            timeSum += list.get(i).getPlaySurvivalTime();
        }
        return timeSum;
    }

    @Override
    public void insertPlayerTable(PlayerTable playerTable) {
        gamePlayerDao.insertPlayerTable(playerTable);
    }

    @Override
    public List<RankingTable> getRankingTable() {
        return gamePlayerDao.getRankingTable();
    }

    @Override
    public void insertRankingTable(RankingTable rankingTable) {
        gamePlayerDao.insertRankingTable(rankingTable);

    }
}
