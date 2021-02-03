package com.magarin.hashmap;

import java.util.HashMap;

public class Map_StartGame implements HashMap_Interface {

    // 플레이어가 게임에 참여하여 게임이 시작하면 해당 플레이어를 보관할 리스트
    // String = Player name, String = Jobs name
    private HashMap<String, String> __Start_Player_List__;

    public boolean __StartGame__Check_join_player__(String __in_Player_name) {
        return false;
    }

    public void __StartGame_Join_Player__(String __in_Player_name) {
        this.__Start_Player_List__.put(__in_Player_name, "Not Start");
    }

    /**
     *
     * @param __in_Player_name
     * @return
     */
    @Override
    public String __Interface_Get_HashMap__(String __in_Player_name) {
        return this.__Start_Player_List__.get(__in_Player_name);
    }

    /**
     *
     * @param __in_Player_name
     */
    @Override
    public void __Interface_Set_HashMap__(String __in_Player_name, String __in_Replace_value) {
        this.__Start_Player_List__.replace(__in_Player_name, __in_Replace_value);
    }
}
