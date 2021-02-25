package com.magarin.economy_bank.event;

import com.magarin.economy_bank.inventory.Inven_Bank;
import com.magarin.economy_bank.inventory.Inven_Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * 플레이어가 Inventory 를 클릭했을때 발생되는 이벤트 입니다.
 *
 * @version 2021-02-25
 * @author Talk_Magarin
 */
public class Event_InventoryClick {

    public static void __on_InventoryClick__(InventoryClickEvent __in_Event__) {
        Inventory __Open_Inventory__ = __in_Event__.getClickedInventory();

        if (__Open_Inventory__ == null)
            return;

        String __Title_name__ = __in_Event__.getView().getTitle();

        if (__Title_name__.equalsIgnoreCase("Menu")) {
            __Support_Menu__(__in_Event__, __Open_Inventory__);
            __in_Event__.setCancelled(true);
        } else if (__Title_name__.equalsIgnoreCase("Bank")) {
            __Support_Bank__(__in_Event__, __Open_Inventory__);
            __in_Event__.setCancelled(true);
        } else {
            return;
        }
    }

    /**
     *
     * @param __in_Event__
     * @param __in_Open_Inventory__
     */
    private static void __Support_Menu__(InventoryClickEvent __in_Event__, Inventory __in_Open_Inventory__) {
        ItemStack __ItemStack_Click_item__ = __in_Event__.getCurrentItem();
        // 클릭한 아이템이 존재하는지 확인
        if (__ItemStack_Click_item__ == null || __ItemStack_Click_item__.hasItemMeta())
            return;
        
        if (__in_Open_Inventory__.getItem(29).getItemMeta().getDisplayName().equalsIgnoreCase("§f은행정보")) {
            for (String __Lore__ : __in_Open_Inventory__.getItem(29).getItemMeta().getLore()) {
                if (__Lore__.equalsIgnoreCase("§0Bank")) {
                    // 은행정보
                    Player __Player__ = (Player)__in_Event__.getWhoClicked();
                    __Player__.closeInventory();
                    Inven_Bank.__Bank__Open_Inventory__(__Player__);
                }
            }

            return;
        }
    }

    private static void __Support_Bank__(InventoryClickEvent __in_Event__, Inventory __in_Open_Inventory__) {
        // 플레이어가 클릭한 아이템
        ItemStack __ItemStack_Click_item__ = __in_Event__.getCurrentItem();
        // 클릭한 아이템이 존재하는지 확인
        if (__ItemStack_Click_item__ == null || __ItemStack_Click_item__.hasItemMeta())
            return;

        if (__in_Open_Inventory__.getItem(13) == __ItemStack_Click_item__) {
            Player __Player__ = (Player)__in_Event__.getWhoClicked();
            // 개설
        } else if (__in_Open_Inventory__.getItem(29) == __ItemStack_Click_item__ || __in_Open_Inventory__.getItem(31) == __ItemStack_Click_item__
                || __in_Open_Inventory__.getItem(33) == __ItemStack_Click_item__) {
            // 적금정보
            Player __Player__ = (Player)__in_Event__.getWhoClicked();
            __Player__.closeInventory();
        } else if (__in_Open_Inventory__.getItem(40) == __ItemStack_Click_item__) {
            // 돌아가기
            Player __Player__ = (Player)__in_Event__.getWhoClicked();
            __Player__.closeInventory();
            Inven_Menu.__Menu__Open_Inventory__(__Player__);
        }
    }

}
