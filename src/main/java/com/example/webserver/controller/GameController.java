package com.example.webserver.controller;

import com.example.webserver.PlayerTable;
import com.example.webserver.RankingTable;
import com.example.webserver.service.GamePlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GamePlayerService gamePlayerService;

    @GetMapping("/playerTable")
    public List<PlayerTable> selectPlayerTable(){
       return gamePlayerService.getPlayerTable();
    }

    @GetMapping("/PlayerTableSum")
    public int selectPlayerTableTimeSum(){
        return gamePlayerService.getPlayerTableSum();
    }

    @PostMapping("/insertPlayerTableTime")
    public String insertPlayerTable(@RequestBody PlayerTable playerTable){
        gamePlayerService.insertPlayerTable(playerTable);
        return "insetPlayerTableTime";
    }

    @GetMapping("/RankingTable")
    public List<RankingTable> selectRankingTable(){
        return gamePlayerService.getRankingTable();
    }

    @PostMapping("/InsertRankingTable")
    public String updateRankingTable(@RequestBody RankingTable rankingTable){
         gamePlayerService.insertRankingTable(rankingTable);
        return "InsertRankingTable";

    }

}
