package com.magarin;

import com.magarin.files.File_Setting;
import com.magarin.hashmap.Map_After_Start_Game;
import com.magarin.hashmap.Map_Before_Start_Game;
import com.magarin.hashmap.Map_Die_Player;

import java.util.List;
import java.util.Random;

/**
 * Random Set jobs
 *
 * 2021-02-08
 * @author Talk_Magarin
 */
public class MafiaPlugin_Random {

    /**
     * 게임 시작 시 플레이의 직업을 랜덤으로 설정합니다.
     */
    public static void __Random_Set_Jobs_Players__() {
        Map_Before_Start_Game __Map_Before_Start_Game = Map_Before_Start_Game.__Instance__();
        Map_After_Start_Game __Map_After_Start_Game = Map_After_Start_Game.__Instance__();

        Integer __JoinPlayer_Count__ = __Map_Before_Start_Game.__Before__Player_List_Count();

        List<String> __Game_Play_Jobs__ = File_Setting.__get_Setting__Jobs_count_To_Player_count__(__JoinPlayer_Count__);
        String[] __To_Jobs_Name__ = new String[__JoinPlayer_Count__];

        Integer __Choice_Jobs_Count = 0;
        for (String __Jobs : __Game_Play_Jobs__) {
            String[] __Split_Jobs = __Jobs.split(":");
            String __Jobs_name = __Split_Jobs[0];
            Integer __Jobs_count = Integer.parseInt(__Split_Jobs[1]);

            for (int __Count = 0; __Count < __Jobs_count; __Count++) {
                __To_Jobs_Name__[__Choice_Jobs_Count] = __Jobs_name;
                __Choice_Jobs_Count++;
            }
        }

        String[] __Mix_To_Jobs_Name__ = new String[__To_Jobs_Name__.length];
        System.arraycopy(__To_Jobs_Name__, 0, __Mix_To_Jobs_Name__, 0, __To_Jobs_Name__.length);

        Random __Random__ = new Random();
        for (int __Count = __Mix_To_Jobs_Name__.length - 1; __Count > 0; __Count--) {
            int __Random_Index__ = __Random__.nextInt(__Count + 1);
            String temp = __Mix_To_Jobs_Name__[__Random_Index__];
            __Mix_To_Jobs_Name__[__Random_Index__] = __Mix_To_Jobs_Name__[__Count];
            __Mix_To_Jobs_Name__[__Count] = temp;
        }

         Integer __Mix_Jobs_Count = 0;
        for (String __Player__ : __Map_Before_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
            Map_Die_Player.__Die__Join_Player_List__(__Player__);
            __Map_After_Start_Game.__After__Join_Player__(__Player__, __Mix_To_Jobs_Name__[__Mix_Jobs_Count]);
            __Mix_Jobs_Count++;
        }
    }

}
