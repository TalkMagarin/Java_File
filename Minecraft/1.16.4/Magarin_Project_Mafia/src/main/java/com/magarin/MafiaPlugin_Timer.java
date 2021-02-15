package com.magarin;

import com.magarin.files.File_Setting;
import com.magarin.hashmap.Map_After_Start_Game;
import com.magarin.hashmap.Map_Before_Start_Game;
import com.magarin.hashmap.Map_Jobs_Skill;
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
                    __Player__.sendTitle("§60일차", "낮이 되었습니다. 첫날은 자유롭게 자기소개를 하며 토론하시기 바랍니다.");
                    this.cancel();
                    break;
            }
        }
        __Message_Count__++;
    }
}

class Mafia_After_Game_TimerTask extends TimerTask {

    boolean __Night__ = false;
    boolean __Day__ = true;

    int __Day_Round__ = 0;
    int __Now_Times__ = 0;

    int __Day_Times__ = File_Setting.__get_Setting__Day_Time__();
    int __Vote_Times__ = File_Setting.__get_Setting__Vote_Time__();

    public void run() {
        Map_After_Start_Game __Map_After_Start_Game = Map_After_Start_Game.__Instance__();
        Map_Jobs_Skill __Map_Jobs_Skill = Map_Jobs_Skill.__Instance__();

        if (!__Night__) {
            // 낮일 경우
            if (__Day_Times__ == 1) {
                for (String __Playername__ : __Map_After_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
                    Player __Player__ = Bukkit.getPlayer(__Playername__);
                    if (__Now_Times__ == 50) {
                        __Player__.sendTitle("§d시간 안내", "마피아 투표 시간까지 10초 남았습니다.");
                        __Player__.sendMessage("마피아 투표 시간까지 10초 남았습니다.");
                    } else if (__Now_Times__ == 55) {
                        __Player__.sendTitle("§d시간 안내", "마피아 투표 시간까지 5초 남았습니다.");
                        __Player__.sendMessage("마피아 투표 시간까지 5초 남았습니다.");
                    } else if (__Now_Times__ == 56) {
                        __Player__.sendTitle("§d시간 안내", "마피아 투표 시간까지 4초 남았습니다.");
                        __Player__.sendMessage("마피아 투표 시간까지 4초 남았습니다.");
                    } else if (__Now_Times__ == 57) {
                        __Player__.sendTitle("§d시간 안내", "마피아 투표 시간까지 3초 남았습니다.");
                        __Player__.sendMessage("마피아 투표 시간까지 3초 남았습니다.");
                    } else if (__Now_Times__ == 58) {
                        __Player__.sendTitle("§d시간 안내", "마피아 투표 시간까지 2초 남았습니다.");
                        __Player__.sendMessage("마피아 투표 시간까지 2초 남았습니다.");
                    } else if (__Now_Times__ == 59) {
                        __Player__.sendTitle("§d시간 안내", "마피아 투표 시간까지 1초 남았습니다.");
                        __Player__.sendMessage("마피아 투표 시간까지 1초 남았습니다.");
                    }
                }
            }

            if (__Now_Times__ == 60) {
                // 시간이 60초가 되었을때
                if (__Day__) {
                    // 낮일 경우
                    if (__Day_Times__ > 0) {
                        // 낮시간이 0보다 크면
                        __Day_Times__--;
                    } else if (__Day_Times__ == 1) {
                        for (String __Playername__ : __Map_After_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
                            Player __Player__ = Bukkit.getPlayer(__Playername__);
                            __Player__.sendTitle("§d시간 안내", "마피아 투표 시간까지 60초 남았습니다.");
                            __Player__.sendMessage("마피아 투표 시간까지 60초 남았습니다.");
                        }
                    } else if (__Day_Times__ == 0) {
                        // 낮시간이 0이면 투표시간으로 넘어감
                        for (String __Playername__ : __Map_After_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
                            Player __Player__ = Bukkit.getPlayer(__Playername__);
                            __Player__.sendTitle("§d투표 시간", "투표를 통해 마피아로 의심되는 사람을 지목하면 됩니다. 지목하지 않을 경우 스킵됩니다.");
                            __Player__.sendMessage("투표를 통해 마피아로 의심되는 사람을 지목하면 됩니다. 지목하지 않을 경우 스킵됩니다.");
                        }

                        __Day__ = false;
                    }
                } else {
                    // 투표시간일 경우
                    if (__Vote_Times__ > 0) {
                        // 투표시간이 0보다 크면
                        __Vote_Times__--;
                    } else if (__Vote_Times__ == 0) {
                        // 투표시간이 0이면
                        __Day_Times__ = File_Setting.__get_Setting__Day_Time__();
                        __Vote_Times__ = File_Setting.__get_Setting__Vote_Time__();

                        __Night__ = true;

                        for (String __Playername__ : __Map_After_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
                            Player __Player__ = Bukkit.getPlayer(__Playername__);
                            __Player__.sendTitle("§d투표 결과", "투표를 통해 플레이어님이 처형 당했습니다.");
                            __Player__.sendMessage("투표를 통해 플레이어님이 처형 당했습니다.");
                        }
                    }
                }

                __Now_Times__ = -1;
            }
        } else {
            // 밤일 경우
            if (__Map_Jobs_Skill.__Skill__Check_All_Player_Skill()) {
                __Day_Round__++;
                __Night__ = false;
                __Day__ = true;

                for (String __Playername__ : __Map_After_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
                    Player __Player__ = Bukkit.getPlayer(__Playername__);
                    __Player__.sendTitle(String.format("%s일차", __Day_Round__), "낮이 되었습니다.");
                    __Player__.sendMessage("낮이 되었습니다.토론을 하여 마피아를 찾아내시기 바랍니다.");
                }

                __Now_Times__ = -1;
            } else if (__Now_Times__ == 0) {
                __Map_Jobs_Skill.__Skill__Reset_HashMap__();

                for (String __Playername__ : __Map_After_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
                    Player __Player__ = Bukkit.getPlayer(__Playername__);
                    __Player__.sendTitle("§7별이 빛나는 밤", "밤이 되었습니다. 각 직업군 들은 눙력을 사용해 주시기 바랍니다.");
                    __Player__.sendMessage("밤이 되었습니다. 각 직업군 들은 눙력을 사용해 주시기 바랍니다.");
                    __Player__.sendMessage("능력을 사용할 수 있는 모든 직업군들이 능력을 사용하지 않을 경우 라운드는 넘어가지 않습니다");
                }
            }
        }

        __Now_Times__++;
    }

}