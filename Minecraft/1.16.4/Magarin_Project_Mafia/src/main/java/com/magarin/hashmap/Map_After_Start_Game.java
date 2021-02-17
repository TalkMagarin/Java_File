package com.magarin.hashmap;

import java.util.HashMap;

/**
<pre>
After_Start_Game
HashMap_Interface = Support Class
</pre>
 *
 * 2021-02-03
 * @author Talk_Magarin
 */
public class Map_After_Start_Game implements HashMap_Interface {

    private static Map_After_Start_Game __Map_After_Start_Game = null;
    public static Map_After_Start_Game __Instance__() {
        if (__Map_After_Start_Game == null)
            __Map_After_Start_Game = new Map_After_Start_Game();
        return __Map_After_Start_Game;
    }

    // 플레이어가 게임에 참여하여 게임이 시작하면 해당 플레이어를 보관할 리스트
    // String = Player name, String = Jobs name
    private HashMap<String, String> __After_Start_Game_Player_List__ = null;

    public Map_After_Start_Game() {
        this.__After_Start_Game_Player_List__ = new HashMap<>();
    }

    public void __After__Player_Clear__() {
        this.__After_Start_Game_Player_List__.clear();
    }

    /**
     * 시작한 게임에 플레이어가 존재하는지 확인
     * @param __in_Player_name Player name
     * @return boolean
     */
    public boolean __After__Check_join_player__(String __in_Player_name) {
        if (this.__After_Start_Game_Player_List__.get(__in_Player_name) == null)
            return false;
        else
            return true;
    }

    /**
     * 게임이 시작될때 플레이어 등록
     * @param __in_Player_name Player name
     * @param __in_Jobs_name Jobs name
     */
    public void __After__Join_Player__(String __in_Player_name, String __in_Jobs_name) {
        this.__After_Start_Game_Player_List__.put(__in_Player_name, __in_Jobs_name);
    }

    /**
     * 플레이어 직업 가져오기
     * @param __in_Player_name Player name
     * @return String
     */
    @Override
    public String __Interface_Get_HashMap__(String __in_Player_name) {
        return this.__After_Start_Game_Player_List__.get(__in_Player_name);
    }

    /**
     * 플레이어 직업 설정하기
     * @param __in_Player_name Player name
     * @param __in_Replace_value  Set value
     */
    @Override
    public void __Interface_Set_HashMap__(String __in_Player_name, String __in_Replace_value) {
        this.__After_Start_Game_Player_List__.replace(__in_Player_name, __in_Replace_value);
    }

    /**
     * 플레이어 리스트 가져오기
     * @return __After_Start_Game_Player_List__
     */
    @Override
    public HashMap<String, String> __Interface_Get_HashMap_List__() {
        return __After_Start_Game_Player_List__;
    }
}
