package com.magarin.hashmap;

import com.magarin.files.File_Setting;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

/**
<pre>
플레이어가 직업군 스킬을 사용했을때 적용되는 클래스
HashMap_Interface = Support Class
</pre>
 *
 * 2021-02-15
 * @author Talk_Magarin
 */
public class Map_Jobs_Skill implements HashMap_Interface {

    private static Map_Jobs_Skill __Map_Jobs_Skill = null;
    public static Map_Jobs_Skill __Instance__() {
        if (__Map_Jobs_Skill == null)
            __Map_Jobs_Skill = new Map_Jobs_Skill();
        return __Map_Jobs_Skill;
    }

    private Map_After_Start_Game __Map_After_Start_Game = null;

    // 플레이어가 게임에 참여하기 전 플레이어를 보관할 리스트
    // String = Player name, String = Chief of the room player name
    private HashMap<String, String> __Map_Jobs_Skill_List__ = null;

    public Map_Jobs_Skill() {
        this.__Map_Jobs_Skill_List__ = new HashMap<>();
        __Map_After_Start_Game = Map_After_Start_Game.__Instance__();
    }

    public Integer __Skill__Jobs_Skill_List_Count() {
        return this.__Map_Jobs_Skill_List__.size();
    }

    public void __Skill__Player_Clear__() {
        this.__Map_Jobs_Skill_List__.clear();
    }

    public boolean __Skill__Check_All_Player_Skill() {
        for (String __Player_name__ : __Map_After_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
            String __get_Player_jobs__ = __Map_After_Start_Game.__Interface_Get_HashMap__(__Player_name__);
            String __HashMap_Key__ = String.format("%s_%s", __Player_name__, __get_Player_jobs__);

            if (__Interface_Get_HashMap__(__HashMap_Key__).equalsIgnoreCase("%%%null%%%"))
                return false;
        }

        return true;
    }

    /**
     * 밤이 될때마다 사용할 스킬을 리셋할때 사용
     */
    public void __Skill__Reset_HashMap__() {
        for (String __Player_name__ : __Map_After_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
            String __get_Player_jobs__ = __Map_After_Start_Game.__Interface_Get_HashMap__(__Player_name__);
//            String __HashMap_Key__ = String.format("%s_%s", __Player_name__, __get_Player_jobs__);

            if (__Interface_Get_HashMap__(__get_Player_jobs__) == null) {
                // Null = 스킬 사용전
                this.__Map_Jobs_Skill_List__.put(__get_Player_jobs__, "%%%null%%%");
            } else {
                if (Map_Die_Player.__Die__Check_Player__(__Player_name__)) {
                    // 직업군이 죽지 않았을 경우 Null로 초기화
                    this.__Map_Jobs_Skill_List__.replace(__get_Player_jobs__, "%%%null%%%");
                }
            }
        }
    }

    @Override
    public HashMap<String, String> __Interface_Get_HashMap_List__() {
        return __Map_Jobs_Skill_List__;
    }

    @Override
    public String __Interface_Get_HashMap__(String __in_Player_name_And_Jobs) {
        return this.__Map_Jobs_Skill_List__.get(__in_Player_name_And_Jobs);
    }

    @Override
    public void __Interface_Set_HashMap__(String __in_Player_name, String __in_Select_Player_name) {
        Player __Player__ = Bukkit.getPlayer(__in_Player_name);
        String __get_Player_jobs__ = __Map_After_Start_Game.__Interface_Get_HashMap__(__in_Player_name);
        String __HashMap_Key__ = String.format("%s_%s", __in_Player_name, __get_Player_jobs__);

        if (__Interface_Get_HashMap__(__HashMap_Key__).equalsIgnoreCase("%%%null%%%")) {
            this.__Map_Jobs_Skill_List__.replace(__HashMap_Key__, __in_Select_Player_name);

            if (__get_Player_jobs__.equalsIgnoreCase("Mafia")) {
                __Player__.sendTitle(String.format("§c%s", __in_Select_Player_name), "처참하게 죽일 플레이어를 지목 하셨습니다.");
            } else if (__get_Player_jobs__.equalsIgnoreCase("Doctor"))
                __Player__.sendTitle(String.format("§a%s", __in_Select_Player_name), "해당 플레이어를 마피아의 공격으로 부터 치료 하였습니다.");
            else if (__get_Player_jobs__.equalsIgnoreCase("Police"))
                __Player__.sendTitle(String.format("§e%s", __in_Select_Player_name), "마피아 인지 확인할 플레이어를 지목 하셨습니다.");
            else if (__get_Player_jobs__.equalsIgnoreCase("Spy"))
                __Player__.sendTitle(String.format("§7%s", __in_Select_Player_name), "직업을 확인할 플레이어를 지목 하셨습니다.");
            else if (__get_Player_jobs__.equalsIgnoreCase("Spiritualistic_Medium"))
                __Player__.sendTitle(String.format("§8%s", __in_Select_Player_name), "죽은 플레이어의 직업을 확인할 플레이어를 지목 하셨습니다.");
            else if (__get_Player_jobs__.equalsIgnoreCase("Detective"))
                __Player__.sendTitle(String.format("§8%s", __in_Select_Player_name), "능력을 사용했는지 확인할 플레이어를 지목 하셨습니다.");
            else {
                __Player__.sendTitle("§4저런..", "당신은 능력을 사용할 수 없습니다.");
                return;
            }
        } else {
            __Player__.sendTitle("§c에러", "이미 능력을 사용했습니다.");
        }
    }

}
