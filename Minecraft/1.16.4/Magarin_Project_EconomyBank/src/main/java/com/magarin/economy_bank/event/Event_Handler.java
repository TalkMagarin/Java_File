package com.magarin.economy_bank.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * 모든 이벤트를 쓰임새에 맞게 제어하는 이벤트 클래스 입니다.
 *
 * @version 2021-02-24
 * @author Talk_Magarin
 */
public class Event_Handler implements Listener {

    /**
     * 플레이어가 채팅을 입력시 작동하는 이벤트 메소드 입니다.
     * @param __in_Event__ 이벤트
     */
    @EventHandler
    public void __Handler__AsyncPlayerChat__(AsyncPlayerChatEvent __in_Event__) {
        String __Message__ = __in_Event__.getMessage();

        if (__Message__.charAt(0) == '^') {
            Event_AsyncPlayerChat.__on_AsyncPlayerChat__(__in_Event__);
            __in_Event__.setCancelled(true);
        }

        return;
    }

    /**
     * 플레이어가 행동을 했을때 작동하는 이벤트 메소드 입니다.
     * @param __in_Event__ 이벤트
     */
    @EventHandler
    public void __Handler__PlayerInteract__(PlayerInteractEvent __in_Event__) {
        Player __Player__ = __in_Event__.getPlayer();

        if (__in_Event__.getAction() == Action.LEFT_CLICK_BLOCK || __in_Event__.getAction() == Action.LEFT_CLICK_AIR)
            Event_PlayerInteract.__on_PlayerInteract__(__in_Event__);
    }

    /**
     * 플레이어가 인벤토리에 있는 아이템을 클릭했을때 작동하는 이벤트 메소드 입니다.
     * @param __in_Event__ 이벤트
     */
    @EventHandler
    public void __Handler__InventoryClick__(InventoryClickEvent __in_Event__) {
        Event_InventoryClick.__on_InventoryClick__(__in_Event__);
    }

}
