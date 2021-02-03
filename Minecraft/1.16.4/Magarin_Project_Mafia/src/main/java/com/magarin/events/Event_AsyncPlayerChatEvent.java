package com.magarin.events;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import com.magarin.files.File_Command;

/**
 * Event - Char Player
 *
 * 2021-01-29
 * @author Talk_Magarin
 */
public class Event_AsyncPlayerChatEvent {

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

        if (_Split_Chat_Message[0].equalsIgnoreCase("Help")) {
            // 도움말
        } else if (_Split_Chat_Message[0].equalsIgnoreCase(File_Command.__get_Command__Before_The_Game_Starts_Command__("Join_Game"))) {
            // 게임 참가
        } else if (_Split_Chat_Message[0].equalsIgnoreCase(File_Command.__get_Command__Before_The_Game_Starts_Command__("Exit_Game"))) {
            // 게임 나가기
        } else if (_Split_Chat_Message[0].equalsIgnoreCase(File_Command.__get_Command__Before_The_Game_Starts_Command__("Check_Join_Player"))) {
            // 게임 참여자 확인
        } else if (_Split_Chat_Message[0].equalsIgnoreCase(File_Command.__get_Command__Before_The_Game_Starts_Command__("Start_Game__Chief_Of_The_Room_Player"))) {
            // 방장 게임 시작
        }
    }

}
