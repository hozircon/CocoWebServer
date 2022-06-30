桐生ココ回歸倒數計時器
===
## 專案背景

2021/7/1 桐生ココ正式從Hololive畢業，但這並不是結束，因為

`我們約定好囉，五百年後再見！`

2022年的五月份，桐生ココ即將畢業一周年，我決定利用所學練習並製作一個桐生ココ的紀念網站。

## 簡介

此專案主要透過Phaser.js與SpringBoot API Server所建立的粉絲紀念網站，並且部屬在Google Cloud 的 app engine上，資料儲存採用Google Cloud SQL中的Mysql資料庫。 (網站因免費用量於2022/6/24使用完畢而下架)

![GITHUB] (https://github.com/hozircon/CocoWebServer/blob/master/%E7%B6%B2%E9%A0%81%E9%A0%90%E8%A6%BD.png "網頁預覽.png")

網站頁面包含

+ 回歸時間倒數器
+ 玩家遊玩遊戲總時間
+ Phaser.js 2D橫向卷軸小遊戲
+ 玩家遊玩成績排行榜

## 系統架構

![GITHUB] (https://github.com/hozircon/CocoWebServer/blob/master/%E7%B3%BB%E7%B5%B1%E6%9E%B6%E6%A7%8B%E5%9C%96.png)

### 前端
![GITHUB] (https://github.com/hozircon/CocoWebServer/blob/master/front-end.png)
### 後端
![GITHUB] (https://github.com/hozircon/CocoWebServer/blob/master/back-end.png)
### API說明文件


| 說明 | Method | path | 參數|
| ---- | ---- | ---- | ----|
| 取得所有遊戲紀錄(編號與遊戲時間) | GET | /PlayerTable | 無 |
| 取得玩家遊戲總時間 | GET | /PlayerTableSum | 無 |
| 取得玩家遊戲排行榜前15筆資料 | GET | /RankingTable | 無 |
| 新增一筆玩家遊戲時間 | POST | /insertPlayerTableTime | "playSurvivalTime" : 遊戲時間|
| 新增一筆玩家遊戲排行紀錄 | POST | /InsertRankingTable | "name" : 玩家暱稱 , "gameTime" : 遊戲時間 |

## 程式架構

### 後端主要部分

    ├─src
    │  ├─main
    │  │  ├─java
    │  │  │  └─com
    │  │  │      └─example
    │  │  │          └─webserver
    │  │  │              │  GlobalCorsConfig.java
    │  │  │              │  PlayerTable.java
    │  │  │              │  RankingRowMapper.java
    │  │  │              │  RankingTable.java
    │  │  │              │  TotalPlayerRowMapper.java
    │  │  │              │  WebserverApplication.java
    │  │  │              │  
    │  │  │              ├─controller
    │  │  │              │      GameController.java
    │  │  │              │      IndexControlller.java
    │  │  │              │      
    │  │  │              ├─dao
    │  │  │              │      GamePlayerDao.java
    │  │  │              │      GamePlayerDaoImpl.java
    │  │  │              │      
    │  │  │              └─service
    │  │  │                      GamePlayerService.java
    │  │  │                      GamePlayerServiceImpl.java

### 前端主要部分

    ├─src
    │  ├─main
    │  │  └─resources
    │  │      ├─static
    │  │      │  ├─html
    │  │      │  │      form.html
    │  │      │  ├─image
    │  │      │  │  └─bg
    │  │      │  ├─js
    │  │      │  │  │  const.js
    │  │      │  │  │  gamePlay.js
    │  │      │  │  │  gameStart.js
    │  │      │  │  │  httpRequest.js
    │  │      │  │  │  index.js
    │  │      │  │  │  style.css
    │  │      │  │  │  webMethod.js
    │  │      │  │  │  
    │  │      │  │  └─lib
    │  │      │  │          phaser.min.js
    │  │      │  │          
    │  │      │  └─src
    │  │      └─templates
    │  │              index.html
   

## Issue

待優化項目
- [ ] API回應時間較久(約15-20秒才能跑出時間及排行資料)
- [ ] 時間顯示的邏輯改寫，目前每秒會請求一次http request。
- [ ] 無token與驗證機制
- [ ] 無回傳自訂錯誤訊息

問題
- [ ] 同一網域會遇到CORS error，須設定允許跨來源請求(GlobalCorsConfig.java)，原因不明。
