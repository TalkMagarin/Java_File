package com.magarin.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
    public static void __on_Event_AsyncPlayerChatEvent__(AsyncPlayerChatEvent __in_Event) {
        String _Chat_Player__Message = __in_Event.getMessage();
        String[] _Split_Chat_Message = _Chat_Player__Message.split(" ");
        _Split_Chat_Message[0] = _Split_Chat_Message[0].replace("^", "");
    }

}
