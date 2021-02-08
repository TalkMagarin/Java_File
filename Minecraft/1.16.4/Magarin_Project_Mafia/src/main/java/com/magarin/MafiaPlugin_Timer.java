package com.magarin;

import com.magarin.hashmap.Map_Before_Start_Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Timer;
import java.util.TimerTask;

public class MafiaPlugin_Timer {

    public static void __Before_Game_Timer__() {
        Mafia_Before_Game_TimerTask __Mafia_Before_Game_TimerTask = new Mafia_Before_Game_TimerTask();
        Timer __Timer__ = new Timer();
        __Timer__.scheduleAtFixedRate(__Mafia_Before_Game_TimerTask, 0, 1000);
    }

    public static void __After_Game_Timer__() {
        Mafia_After_Game_TimerTask __Mafia_After_Game_TimerTask = new Mafia_After_Game_TimerTask();
        Timer __Timer__ = new Timer();
        __Timer__.scheduleAtFixedRate(__Mafia_After_Game_TimerTask, 0, 1000);
    }

}

class Mafia_Before_Game_TimerTask extends TimerTask {

    public void run() {
        Map_Before_Start_Game __Map_Before_Start_Game = Map_Before_Start_Game.__Instance__();
        for (String __Playername__ : __Map_Before_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
            Player __Player__ = Bukkit.getPlayer(__Playername__);
        }
    }
}

class Mafia_After_Game_TimerTask extends TimerTask {

    int __Day_Round__ = 1;
    int __Test_Count = 0;

    public void run() {
        System.out.println(__Day_Round__);
        if (__Test_Count == 2) {
            this.cancel();
            System.out.println("끝");
        }
        if (__Day_Round__ == 10) {
            __Day_Round__ = 1;
            __Test_Count++;
        } else
            __Day_Round__++;
    }

}