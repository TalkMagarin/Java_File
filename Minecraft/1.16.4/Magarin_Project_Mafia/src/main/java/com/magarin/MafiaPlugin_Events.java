package com.magarin;

import com.magarin.events.Event_AsyncPlayerChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Plugin Events - Support Class
 *
 * 2021-01-29
 * @author Talk_Magarin
 */
public class MafiaPlugin_Events implements Listener {

    @EventHandler
    public static void __on_Event_AsyncPlayerChatEvent__(AsyncPlayerChatEvent __in_Event) {
        String _Chat_Player__Message = __in_Event.getMessage();

        if (_Chat_Player__Message.charAt(0) == '^') {
            Event_AsyncPlayerChatEvent.__on_Event_AsyncPlayerChatEvent__(__in_Event);
        }

        return;
    }

}
