package com.magarin;

import com.magarin.files.File_Setting;
import com.magarin.hashmap.Map_After_Start_Game;
import com.magarin.hashmap.Map_Before_Start_Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Timer;
import java.util.TimerTask;

public class MafiaPlugin_Timer {

    public static void __Before_Game_Timer__() {
        Mafia_Before_Game_TimerTask __Mafia_Before_Game_TimerTask = new Mafia_Before_Game_TimerTask();
        Timer __Timer__ = new Timer();
        __Timer__.scheduleAtFixedRate(__Mafia_Before_Game_TimerTask, 0, 3500);
    }

    public static void __After_Game_Timer__() {
        Mafia_After_Game_TimerTask __Mafia_After_Game_TimerTask = new Mafia_After_Game_TimerTask();
        Timer __Timer__ = new Timer();
        __Timer__.scheduleAtFixedRate(__Mafia_After_Game_TimerTask, 0, 1000);
    }

}

class Mafia_Before_Game_TimerTask extends TimerTask {

    int __Message_Count__ = 0;

    public void run() {
        Map_Before_Start_Game __Map_Before_Start_Game = Map_Before_Start_Game.__Instance__();
        for (String __Playername__ : __Map_Before_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
            Player __Player__ = Bukkit.getPlayer(__Playername__);

            switch(__Message_Count__) {
                case 0:
                    __Player__.sendMessage("- 마피아 게임 설명 -");
                    __Player__.sendMessage("마피아 게임은 낮동안 직업을 가진 플레이어의 능력을 바탕으로 밤마다 살인을 저지르고 다니는 마피아를 찾아 투표를 통해 죽이는 게임입니다.");
                    __Player__.sendMessage("");
                    break;
                case 1:
                    __Player__.sendMessage("- 마피아 게임 룰 -");
                    __Player__.sendMessage("직업을 가진 플레이어는 밤마다 한명을 지정하여 능력을 사용할 수 있고, 살아남은 플레이어 능력을 모두 사용하지 않을 경우 낮이 오지 않습니다.");
                    break;
                case 2:
                    String __Format_Message = String.format("라운드(낮)는/은 총 %s라운드 이며, 마지막 라운드가 끝날때 까지 마피아를 낮시간은 %s분, 투표시간은 %s분 입니다.", File_Setting.__get_Setting__Last_Round__()
                    , File_Setting.__get_Setting__Day_Time__(), File_Setting.__get_Setting__Vote_Time__());
                    __Player__.sendMessage(__Format_Message);
                    break;
                case 3:
                    __Player__.sendMessage("추리를 통해 마피아를 찾아주시면 됩니다.");
                    __Player__.sendMessage("Made in player - Talk_Magarin");
                    break;
                case 4:
                    this.cancel();
                    break;
            }
        }
        __Message_Count__++;
    }
}

class Mafia_After_Game_TimerTask extends TimerTask {

    boolean __Night__ = false;

    int __Day_Round__ = 0;
    int __Now_Times__ = 0;

    int __Day_Times__ = File_Setting.__get_Setting__Day_Time__();
    int __Vote_Times__ = File_Setting.__get_Setting__Vote_Time__();

    public void run() {
        Map_After_Start_Game __Map_After_Start_Game = Map_After_Start_Game.__Instance__();

        if (!__Night__) {
            // 낮일 경우
            if (__Now_Times__ == 60) {
                // 시간이 60초가 되었을때
                if (__Day_Times__ > 0) {
                    // 낮시간이 0보다 크면
                    __Day_Times__--;
                } else if (__Day_Times__ == 0) {
                    // 낮시간이 0이면 투표시간으로 넘어감
                    for (String __Playername__ : __Map_After_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
                        Player __Player__ = Bukkit.getPlayer(__Playername__);
                        __Player__.sendTitle("§d투표 시간", "투표를 통해 마피아로 의심되는 사람을 지목하면 됩니다. 지목하지 않을 경우 스킵됩니다.");
                    }
                } else if (__Vote_Times__ > 0) {
                    // 투표시간이 0보다 크면
                    __Vote_Times__--;
                }

                __Now_Times__ = 0;
            }

            if (__Vote_Times__ == 0) {
                // 투표시간이 0이면
                __Day_Times__ = File_Setting.__get_Setting__Day_Time__();
                __Vote_Times__ = File_Setting.__get_Setting__Vote_Time__();

                __Night__ = true;

                for (String __Playername__ : __Map_After_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
                    Player __Player__ = Bukkit.getPlayer(__Playername__);
                    __Player__.sendTitle("§d투표 결과", "투표를 통해 플레이어님이 처형당했습니다.");
                }
            }
        } else {
            // 밤일 경우
        }

        __Now_Times__++;
    }

}