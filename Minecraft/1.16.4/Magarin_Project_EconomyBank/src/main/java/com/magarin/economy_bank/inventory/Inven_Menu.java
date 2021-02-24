package com.magarin.economy_bank.inventory;

import com.magarin.economy_bank.Economy_Bank;
import com.magarin.economy_bank.sqlite.Sqlite_Installment_Savings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 플레이어에게 보여줄 Inventory GUI 를 관리하는 클래스 입니다.
 *
 * @version 2021-02-24
 * @author Talk_Magarin
 */
public class Inven_Menu {

    private static final Plugin __Plugin_Class__ = Economy_Bank.getPlugin(Economy_Bank.class);
    private static final Sqlite_Installment_Savings __Sqlite_Installment_Savings__ = Sqlite_Installment_Savings.__Instance__();

    /**
     * 플레이어에게 Inventory GUI를 보여줍니다.
     * @param __in_Player__ 플레이어
     */
    public static void __Menu__Open_Inventory__(Player __in_Player__) {
        String __Player_Uuid__ = __in_Player__.getUniqueId().toString();

        Inventory __Inventory__ = __Plugin_Class__.getServer().createInventory(null, 45, "Menu");
        // 은행관련
        __Inventory__.setItem(29, __ItemStack__Icon_Bank__(__Player_Uuid__));
    }

    /**
     * 각 플레이어에 맞는 은행정보 아이콘을 불러옵니다.
     * @param __in_Player_Uuid__ 플레이어 고유아이디
     * @return ItemStack
     */
    private static ItemStack __ItemStack__Icon_Bank__(String __in_Player_Uuid__) {
        int __Player_Data_Count__ = __Sqlite_Installment_Savings__.__Sqlite__Get_Data_Count__(__in_Player_Uuid__);

        ItemStack __Icon_Bank__ = new ItemStack(Material.BOOK, 1);
        ItemMeta __Bank_ItemMeta__ = __Icon_Bank__.getItemMeta();
        __Bank_ItemMeta__.setDisplayName("은행정보");
        __Bank_ItemMeta__.setLore(new ArrayList<String>(Arrays.asList(
                "플레이어의 은행정보를 확인할 수 있습니다.", "",
                "보유중인 적금통장 : " + __Player_Data_Count__ + "개", ""
        )));
        __Icon_Bank__.setItemMeta(__Bank_ItemMeta__);

        return __Icon_Bank__;
    }

}
