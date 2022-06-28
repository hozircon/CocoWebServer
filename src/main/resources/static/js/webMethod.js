// 網頁載入時，執行下列func. (左側遊玩時間總表、上方倒數計時器、右側玩家排行榜)
window.onload=function() { ReloadPlayerTimeSum(); showTime(); ReloadRankingTableData(); }


function computeTime(timeSec){
    //轉換時間： sec > 年 天 時 分 秒
    var dif_year, dif_day, dif_hour, dif_min, dif_sec = 0;

    dif_year = Math.floor(timeSec/31557600);
    var tmp = timeSec%31557600;
    dif_day = Math.floor(tmp/86400);
    tmp = tmp%86400;
    dif_hour = Math.floor(tmp/3600);
    tmp = tmp%3600;
    dif_min = Math.floor(tmp/60);
    tmp = tmp%60;
    dif_sec = Math.floor(tmp);

    var timeResult = [dif_year,dif_day,dif_hour,dif_min,dif_sec];
    return timeResult;
}

function ReloadPlayerTimeSum(){
    // api request > get sum of "play_survival_time" from table (player)
    $.ajax({
        type:"GET",
        url: "http://api server address/PlayerTableSum",
        dataType:"json",

        // 時間轉換 : sec > 年 天 時 分 秒 : 渲染畫面左側sidebar1區塊
        success:function(playerTimeSum){
            var playerTime = computeTime(playerTimeSum);
            var timeWord = ['playerYear','playerDay','playerHour','playerMin','playerSec'];
            for(var i = 0; i<timeWord.length; i++){
                if(playerTime[i]<10){
                    playerTime[i] = "0" + playerTime[i];
                }
                document.getElementById(timeWord[i]).innerHTML =   playerTime[i];
            }
        }
    })
}

function showTime(){
    //計算距離目標時間，並扣除玩家總遊玩時間(sum of play_survival_time)後，渲染到畫面上方time_data2區塊
    var endDate = new Date("07/01/2521");
    var nowDate = new Date($.ajax({async: false}).getResponseHeader("Date"));
    difference = (endDate.getTime()-nowDate.getTime())/1000;

    $.ajax({
        type:"GET",
        url: "http://api server address/PlayerTableSum",
        dataType:"json",
        
        success:function(playerTimeSum){
            difference -= playerTimeSum * 100 ;
            timeNum = computeTime(difference);
            var timeWord = ['year','day','hour','min','sec'];
            for(var i = 0; i<timeWord.length; i++){
                if(timeNum[i]<10){
                    timeNum[i] = "0" + timeNum[i];
                }
                document.getElementById(timeWord[i]).innerHTML =   timeNum[i];
            }
        }
    })
    setTimeout('showTime()',1000);
}

function ReloadRankingTableData()
{ 
    //api request > get table data ("name"、"game_time") from table (ranking)
    $.ajax({
        type:"GET",
        url: "http://api server address/RankingTable",
        dataType:"json",

        // 動態建立表格
        success:function(rankingTable){
            let playerName = [];
            let gameTime = [];
            let index = [];
        
            // 建立兩張表格   1. tableTOP3 > 為前三名設計表格
            //              2.tableRanking > 第三名之後的名次顯示
            let tableTOP3 = document.createElement('table');
            let tableRanking = document.createElement('table');

            //欄位設計 tbody:3*2  tbody2:3*1
            let tbody = document.createElement('tbody');
            let tbody2 = [];
        
            tableTOP3.appendChild(tbody);       
            document.getElementById('top3Table').appendChild(tableTOP3);
            // row_1 row_2 > tableTOP3 ； row_3 > tableRanking
            let row_1 = document.createElement('tr');
            let row_2 = document.createElement('tr');
            let row_3 = [];
            
        
            for(var i =0 ; i < rankingTable.length ;i++){

                index[i] = document.createElement('td');
                playerName[i] = document.createElement('td');
                gameTime[i] = document.createElement('td');

                index[i].innerHTML = i + 1 ;
                playerName[i].innerHTML = rankingTable[i].name;
                gameTime[i].innerHTML = rankingTable[i].gameTime + 's';

                if(i < 3 ){
                    row_1.appendChild(playerName[i]);
                    row_2.appendChild(gameTime[i]);
                 }else{
                    tbody2[i-3] = document.createElement('tbody');
                    tableRanking.appendChild(tbody2[i-3]);
                    document.getElementById('rankingTable').appendChild(tableRanking);
                    row_3[i-3] = document.createElement('tr');
                    row_3[i-3].appendChild((index[i]));
                    row_3[i-3].appendChild(playerName[i]);
                    row_3[i-3].appendChild(gameTime[i]);
                    tbody2[i-3].appendChild(row_3[i-3]);
                 }
            }
            tbody.appendChild(row_1);
            tbody.appendChild(row_2);

        },
    
        error:function(err){console.log(err)},
      });

}

function sendPlayerGameTime(gameTime){
    var sendObject = {};
    sendObject["playSurvivalTime"] = gameTime;

    //api request > Post json data("play_survival_time":"playSurvivalTime") to table (player)
    $.ajax({
        type:"POST",
        url: "http://api server address/insertPlayerTableTime",
        data:JSON.stringify(sendObject),
        contentType: "application/json;charset=utf-8",
        
        success:function(res){console.log(res)
            ReloadPlayerTimeSum();
        },
        error:function(err){console.log(err)},
      });
}

function sendPlayerRankingData(playername,gametime){
    var sendObject = {};
    sendObject["name"] = playername;
    sendObject["gameTime"] = gametime;
    //api request > Post json data ("name":"name" , "game_time":"gameTime") to table (ranking)
    $.ajax({
        type:"POST",
        url: "http://api server address/InsertRankingTable",
        data:JSON.stringify(sendObject),
        contentType: "application/json;charset=utf-8",
        
        success:function(res){
            window.location.reload();
        },
        error:function(err){console.log(err)},
      });
}
