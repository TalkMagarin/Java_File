package com.magarin;

import com.magarin.hashmap.Map_After_Start_Game;
import com.magarin.hashmap.Map_Before_Start_Game;
import com.magarin.hashmap.Map_Die_Player;
import com.magarin.hashmap.Map_Jobs_Skill;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
<pre>
플레이어가 각 직업에 맞게 승패가 나누어 졌는지 확인하는 클래스
</pre>
 *
 * 2021-02-17
 * @author Talk_Magarin
 */
public class MafiaPlugin_Endgame {

    private static Map_Before_Start_Game __Map_Before_Start_Game = null;
    private static Map_After_Start_Game __Map_After_Start_Game = null;
    private static Map_Jobs_Skill __Map_Jobs_Skill__ = null;

    public static void __End__Game_Player_Check_Die__() {
        __Map_Before_Start_Game = Map_Before_Start_Game.__Instance__();
        __Map_After_Start_Game = Map_After_Start_Game.__Instance__();
        __Map_Jobs_Skill__ = Map_Jobs_Skill.__Instance__();

        // 마피아팀
        Integer __Mafia__ = 0;
        Integer __Mafia_Die__ = 0;
        // 시민팀
        Integer __Citizen__ = 0;
        Integer __Citizen_Die__ = 0;

        for (String __Player_Name__ : __Map_After_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
            String __Player_Jobs__ = __Map_After_Start_Game.__Interface_Get_HashMap__(__Player_Name__);

            if (__Player_Jobs__.equalsIgnoreCase("Mafia") || __Player_Jobs__.equalsIgnoreCase("Spy")) {
                __Mafia__++;

                if (!Map_Die_Player.__Die__Check_Player__(__Player_Name__))
                    __Mafia_Die__++;
            } else {
                __Citizen__++;

                if (!Map_Die_Player.__Die__Check_Player__(__Player_Name__))
                    __Citizen_Die__++;
            }
        }

        if ((__Mafia__ - __Mafia_Die__) >= (__Citizen__ - __Citizen_Die__)) {
            // 시민팀 패배
            MafiaPlugin_Timer.__After_Timer_Cancel();
            __End__Player__(true);
        } else if ((__Mafia__ - __Mafia_Die__) == 0) {
            // 시민팀 승리
            MafiaPlugin_Timer.__After_Timer_Cancel();
            __End__Player__(false);
        }
    }

    private static void __End__Player__(Boolean __in_Mafia_Win__) {
        for (String __Player_Name__ : __Map_After_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
            Player __Player__ = Bukkit.getPlayer(__Player_Name__);

            if (__in_Mafia_Win__) {
                __Player__.sendTitle("게임 종료", "마피아 팀이 승리하였습니다.");
                __Player__.sendMessage("마피아 팀이 승리하였습니다.");
            } else {
                __Player__.sendTitle("게임 종료", "시민 팀이 승리하였습니다.");
                __Player__.sendMessage("시민 팀이 승리하였습니다.");
            }
        }

        // 모든 HashMap 데이터 초기화
        __Map_Before_Start_Game.__Before__Player_Clear();
        __Map_After_Start_Game.__After__Player_Clear__();
        __Map_Jobs_Skill__.__Skill__Player_Clear__();
        Map_Die_Player.__Die__Join_Player_List_Clear__();
    }

}
