package com.magarin.events;

import com.magarin.files.File_Message;
import com.magarin.files.File_Setting;
import com.magarin.hashmap.Map_After_Start_Game;
import com.magarin.hashmap.Map_Before_Start_Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import com.magarin.files.File_Command;

import java.nio.Buffer;

/**
 * Event - Char Player
 *
 * 2021-01-29
 * @author Talk_Magarin
 */
public class Event_AsyncPlayerChatEvent {

    public static Map_Before_Start_Game __Map_Before_Start_Game = Map_Before_Start_Game.__Instance__();
    public static Map_After_Start_Game __Map_After_Start_Game = Map_After_Start_Game.__Instance__();

    /**
<pre>
플러그인의 사용자 지정 명령어를 사용할 수 있습니다.
You can use the plug-in's custom command.
</pre>
     * @param __in_Event 플레이어 채팅 이벤트 / Chat Player Event
     */
    public static void __on_Event_AsyncPlayerChatEvent__(AsyncPlayerChatEvent __in_Event, char __in_Command_Character) {
        String _Chat_Player__Message = __in_Event.getMessage();
        String[] _Split_Chat_Message = _Chat_Player__Message.split(" ");
        _Split_Chat_Message[0] = _Split_Chat_Message[0].replace(__in_Command_Character + "", "");

        if (!__Map_After_Start_Game.__After__Check_join_player__(__in_Event.getPlayer().getName())) {
            // 게임 시작 전
            if (_Split_Chat_Message[0].equalsIgnoreCase("Help")) {
                // 도움말
                if (__Map_Before_Start_Game.__Before__Check_join_player__(__in_Event.getPlayer().getName())) {
                    // 게임 참여 여부
                    if (__Map_Before_Start_Game.__Interface_Get_HashMap__(__in_Event.getPlayer().getName()).equalsIgnoreCase("Room Manager")) {
                        // 방장일 경우
                        for (String __Help_Text : File_Command.__get_Command__Before_The_Game_Starts_Help__("Chief_Of_The_Room_Player"))
                            __in_Event.getPlayer().sendMessage(File_Command.__get_Command__Change_Help_Command__(__Help_Text));
                    } else {
                        // 방장이 아닐 경우
                        for (String __Help_Text : File_Command.__get_Command__Before_The_Game_Starts_Help__("Join_Game_Player"))
                            __in_Event.getPlayer().sendMessage(File_Command.__get_Command__Change_Help_Command__(__Help_Text));
                    }
                } else {
                    // 게임 미참여
                    for (String __Help_Text : File_Command.__get_Command__Before_The_Game_Starts_Help__("Not_Join_Game_Player"))
                        __in_Event.getPlayer().sendMessage(File_Command.__get_Command__Change_Help_Command__(__Help_Text));
                }
            } else if (_Split_Chat_Message[0].equalsIgnoreCase(File_Command.__get_Command__Before_The_Game_Starts_Command__("Join_Game"))) {
                // 게임 참가
                if (!__Map_Before_Start_Game.__Before__Check_join_player__(__in_Event.getPlayer().getName())) {
                    if (__Map_Before_Start_Game.__Before__Player_List_Count() == 0) {
                        // 참여자가 없을 경우 방장 부여
                        __Map_Before_Start_Game.__Before__Join_Player__(__in_Event.getPlayer().getName(), "Room Manager");
                        __in_Event.getPlayer().sendTitle(File_Message.__get_Message__Before_The_Game_Starts_Title_Main__("Join_Game"),
                                File_Message.__get_Message__Before_The_Game_Starts_Title_Sub__("Join_Game"));
                    } else if (__Map_Before_Start_Game.__Before__Player_List_Count() == File_Setting.__get_Setting__Max_Join_Player__()) {
                        __in_Event.getPlayer().sendTitle(File_Message.__get_Message__Before_The_Game_Starts_Title_Main__("MaxPlayer_Join_Game"),
                                File_Message.__get_Message__Before_The_Game_Starts_Title_Sub__("MaxPlayer_Join_Game"));
                    } else {
                        // 일반 플레이어
                        __Map_Before_Start_Game.__Before__Join_Player__(__in_Event.getPlayer().getName(), "Player");
                        __in_Event.getPlayer().sendTitle(File_Message.__get_Message__Before_The_Game_Starts_Title_Main__("Join_Game"),
                                File_Message.__get_Message__Before_The_Game_Starts_Title_Sub__("Join_Game"));
                    }
                } else {
                    __in_Event.getPlayer().sendTitle(File_Message.__get_Message__Before_The_Game_Starts_Title_Main__("Already_Join_Game"),
                            File_Message.__get_Message__Before_The_Game_Starts_Title_Sub__("Already_Join_Game"));
                }
            } else if (_Split_Chat_Message[0].equalsIgnoreCase(File_Command.__get_Command__Before_The_Game_Starts_Command__("Exit_Game"))) {
                // 게임 나가기
                if (__Map_Before_Start_Game.__Before__Check_join_player__(__in_Event.getPlayer().getName())) {
                    if (__Map_Before_Start_Game.__Interface_Get_HashMap__(__in_Event.getPlayer().getName()).equalsIgnoreCase("Room Manager")) {
                        __in_Event.getPlayer().sendTitle(File_Message.__get_Message__Before_The_Game_Starts_Title_Main__("Exit_Game"),
                                File_Message.__get_Message__Before_The_Game_Starts_Title_Sub__("Exit_Game"));

                        for (Player __Search_Player : Bukkit.getOnlinePlayers())  {
                            // 게임에 참여한 플레이어에게 보내는 메시지
                            if (__Map_Before_Start_Game.__Before__Check_join_player__(__Search_Player.getName())) {
                                if (__Map_Before_Start_Game.__Interface_Get_HashMap__(__Search_Player.getName()).equalsIgnoreCase("Player")) {
                                    __Search_Player.sendTitle(File_Message.__get_Message__Before_The_Game_Starts_Title_Main__("Exit_Game_Room_Manager"),
                                            File_Message.__get_Message__Before_The_Game_Starts_Title_Sub__("Exit_Game_Room_Manager"));
                                }
                            }
                        }
                        // 방장이 나가면 방 폭파
                        __Map_Before_Start_Game.__Before__Player_Clear();
                    } else {
                        // 일반 플레이어
                        __Map_Before_Start_Game.__Before__UnJoin_Player__(__in_Event.getPlayer().getName());
                        __in_Event.getPlayer().sendTitle(File_Message.__get_Message__Before_The_Game_Starts_Title_Main__("Exit_Game"),
                                File_Message.__get_Message__Before_The_Game_Starts_Title_Sub__("Exit_Game"));
                    }
                } else {
                    __in_Event.getPlayer().sendTitle(File_Message.__get_Message__Before_The_Game_Starts_Title_Main__("Error"),
                            File_Message.__get_Message__Before_The_Game_Starts_Title_Sub__("Error"));
                }
            } else if (_Split_Chat_Message[0].equalsIgnoreCase(File_Command.__get_Command__Before_The_Game_Starts_Command__("Check_Join_Player"))) {
                // 게임 참여자 확인
                if (__Map_Before_Start_Game.__Before__Check_join_player__(__in_Event.getPlayer().getName())) {
                    __in_Event.getPlayer().sendMessage("-- 게임 참여자 명단 --");
                    __in_Event.getPlayer().sendMessage("");

                    Integer __Count = 1;
                    for (String __Player : __Map_Before_Start_Game.__Interface_Get_HashMap_List__().keySet()) {
                        // 플레이어 이름 가져오기
                        String __Message = String.format("%d. %s : ", __Count, __Player);
                        if (__Map_Before_Start_Game.__Interface_Get_HashMap__(__Player).equalsIgnoreCase("Room Manager"))
                            __Message += "방장 / Room Manager";
                        else
                            __Message += "참여자 / Join Player";
                        __in_Event.getPlayer().sendMessage(__Message);
                        __Count++;
                    }
                } else {
                    __in_Event.getPlayer().sendTitle(File_Message.__get_Message__Before_The_Game_Starts_Title_Main__("Error"),
                            File_Message.__get_Message__Before_The_Game_Starts_Title_Sub__("Error"));
                }
            } else if (_Split_Chat_Message[0].equalsIgnoreCase(File_Command.__get_Command__Before_The_Game_Starts_Command__("Start_Game__Chief_Of_The_Room_Player"))) {
                // 방장 게임 시작
            }
        } else {
            // 게임 시작
        }
    }

}
