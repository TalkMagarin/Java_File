package com.magarin.economy_bank.event;

import com.magarin.economy_bank.inventory.Inven_Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * 플레이어가 지정된 명령어를 채팅으로 입력시 작동되는 이벤트 클래스 입니다.
 *
 * @version 2021-02-24
 * @author Talk_Magarin
 */
public class Event_AsyncPlayerChat {

    public static void __on_AsyncPlayerChat__(AsyncPlayerChatEvent __in_Event__) {
        String __Message__ = __in_Event__.getMessage();
        String[] __Split_Commands__ = __Message__.split(" ");

        if (__Split_Commands__[0].equalsIgnoreCase("mbank")
                || __Split_Commands__[0].equalsIgnoreCase("은행")) {
            Player __Player__ = __in_Event__.getPlayer();
            // 명령어 입력 시 메뉴 GUI 보여줌
            Inven_Menu.__Menu__Open_Inventory__(__Player__);
        }
    }

}
