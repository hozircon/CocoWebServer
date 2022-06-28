function sendPlayerGameTime(gametime){

    var sendObject = {};
    sendObject["playSurvivalTime"] = gametime;

    $.ajax({
        type:"POST",
        url:"http://localhost:8080/insertPlayerTableTime",
        // dataType:"json",
        data:JSON.stringify(sendObject),
        contentType: "application/json;charset=utf-8",
        
        success:function(res){console.log(res)
            console.log("已成功發送playertime給Server"+gametime);
            ReloadPlayerTimeSum();
        },
        error:function(err){console.log(err)},
      });
}

function sendPlayerRankingData(playername,gametime){
    console.log("送RANK:" + gametime);
    var sendObject = {};
    sendObject["name"] = playername;
    sendObject["gameTime"] = gametime;

    $.ajax({
        type:"POST",
        url:"http://localhost:8080/InsertRankingTable",
        data:JSON.stringify(sendObject),
        contentType: "application/json;charset=utf-8",
        
        success:function(res){
            window.location.reload();
        },
        error:function(err){console.log(err)},
      });
}
