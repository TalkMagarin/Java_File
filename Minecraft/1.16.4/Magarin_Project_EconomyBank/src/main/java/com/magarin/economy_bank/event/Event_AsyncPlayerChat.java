package com.magarin.economy_bank.event;

import com.magarin.economy_bank.inventory.Inven_Menu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

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
        __Split_Commands__[0] = __Split_Commands__[0].replace("^", "");

        if (__Split_Commands__[0].equalsIgnoreCase("mbank")
                || __Split_Commands__[0].equalsIgnoreCase("은행")) {
            Player __Player__ = Bukkit.getPlayer(__in_Event__.getPlayer().getUniqueId());
            if (__Player__ == null)
                System.out.println("플레이어 없음");
            System.out.println(__in_Event__.getPlayer().getName());

            // 명령어 입력 시 메뉴 GUI 보여줌
            assert __Player__ != null;
            __Player__.openInventory(Inven_Menu.__Menu__Inventory__(__Player__));
        }
    }

}
