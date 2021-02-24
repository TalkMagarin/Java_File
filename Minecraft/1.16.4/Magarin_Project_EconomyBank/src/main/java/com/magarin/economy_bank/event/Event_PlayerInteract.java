package com.magarin.economy_bank.event;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * 플레이어가 지정된 행동을 했을때 작동되는 이벤트 클래스 입니다.
 *
 * @version 2021-02-24
 * @author Talk_Magarin
 */
public class Event_PlayerInteract {

    public static void __on_PlayerInteract__(PlayerInteractEvent __in_Event__) {
        Player __Player__ = __in_Event__.getPlayer();

        if (__Player__.getOpenInventory().getTitle().equalsIgnoreCase("Menu")) {
            
        }
    }

}
