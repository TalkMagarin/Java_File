package com.magarin.files;

import java.util.List;

/**
 <pre>
 YML File - __Mafia_Game_Setting.yml
 File_Handler = Support Class
 </pre>
 *
 * 2021-01-29
 * @author Talk_Magarin
 */
public class File_Setting extends File_Handler {

    private final static String m_Filename = "__Mafia_game_Setting.yml";

    /**
<pre>
시작할 최소 플레이어수를 가져옵니다.
get minimum Player count to start.
</pre>
     * @return int
     */
    public static int __get_Setting__Minimum_Join_Player__() {
        return (int)__get_Object__(m_Filename, "Game_Player.Min");
    }

    /**
<pre>
시작할 최대 플레이어수를 가져옵니다.
get max Player count to start.
</pre>
     * @return int
     */
    public static int __get_Setting__Max_Join_Player__() {
        return (int)__get_Object__(m_Filename, "Game_Player.Max");
    }

    /**
<pre>
게임 마지막 라운드를 불러옵니다.
load the last round Number.
</pre>
     * @return int
     */
    public static int __get_Setting__Last_Round__() {
        return (int)__get_Object__(m_Filename, "Game_Play_Option.Last_Round");
    }

    public static int __get_Setting__Day_Time__() {
        return (int)__get_Object__(m_Filename, "Game_Play_Option.Times.Day");
    }

    public static int __get_Setting__Night_Time__() {
        return (int)__get_Object__(m_Filename, "Game_Play_Option.Times.Night");
    }

    public static List<String> __get_Setting__Jobs_count_To_Player_count__(int __in_Tag_Join_Game_player_count) {
        String _Full_Tag = String.format("Game_Play_Jobs.Join_Player_Count_%d", __in_Tag_Join_Game_player_count);
        return (List<String>)__get_Object__(m_Filename, _Full_Tag);
    }

}
