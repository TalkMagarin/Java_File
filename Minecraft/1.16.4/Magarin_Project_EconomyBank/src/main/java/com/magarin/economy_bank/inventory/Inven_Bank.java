package com.magarin.economy_bank.inventory;

import com.magarin.economy_bank.Economy_Bank;
import com.magarin.economy_bank.Economy_Handler;
import com.magarin.economy_bank.sqlite.Sqlite_Installment_Savings;
import com.magarin.economy_bank.yaml.Yaml_Setting;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 플레이어에게 보여줄 Inventory GUI 를 관리하는 클래스 입니다.
 *
 * @version 2021-02-24
 * @author Talk_Magarin
 */
public class Inven_Bank {

    private static final Plugin __Plugin_Class__ = Economy_Bank.getPlugin(Economy_Bank.class);

    private static final Sqlite_Installment_Savings __Sqlite_Installment_Savings__ = Sqlite_Installment_Savings.__Instance__();
    private static final Yaml_Setting __Yaml_Setting__ = Yaml_Setting.__Instance__();

    /**
     * 플레이어에게 Inventory GUI를 보여줍니다.
     * @param __in_Player__ 플레이어
     */
    public static void __Bank__Open_Inventory__(Player __in_Player__) {
        String __Player_Uuid__ = __in_Player__.getUniqueId().toString();

        Inventory __Inventory__ = __Plugin_Class__.getServer().createInventory(null, 45, "Banks");

        if (__Sqlite_Installment_Savings__.__Sqlite__Get_Data_Count__(__Player_Uuid__) < 3)
            __Inventory__.setItem(13, __ItemStack__Icon_Create__());

        int __Item_Location_Index__ = 29;
        for (String[] __Data__ : __Sqlite_Installment_Savings__.__Sqlite__Get_Data__(__Player_Uuid__)) {
            __Inventory__.setItem(__Item_Location_Index__, __ItemStack__Icon_Bank__(__Data__));
            __Item_Location_Index__ += 2;
        }

        __in_Player__.openInventory(__Inventory__);
    }

    /**
     * 적금 개설 아이콘을 불러옵니다.
     * @return ItemStank
     */
    private static ItemStack __ItemStack__Icon_Create__() {
        // 연두색 유리판
        ItemStack __Icon_Create__ = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
        ItemMeta __Create_ItemMeta__ = __Icon_Create__.getItemMeta();

        __Create_ItemMeta__.setDisplayName("적금통장 개설");
        __Icon_Create__.setItemMeta(__Create_ItemMeta__);

        return __Icon_Create__;
    }

    /**
     * 각 플레이어에 맞는 적금정보 아이콘을 불러옵니다.
     * @return ItemStack
     */
    private static ItemStack __ItemStack__Icon_Bank__(String[] __in_Data__) {
        int __Elapsed_date__ = Economy_Handler.__Eco_Handler__Get_Elapsed_date__(__in_Data__[3]);
        int __Cycle_Elapsed_date__ = __Elapsed_date__ / __Yaml_Setting__.__Installment_Savings__Get_Cycle_Day__();

        double __Interest_Rate__ = __Yaml_Setting__.__Installment_Savings__Get_Interest_Rate__();
        double __Cycle_Interest_Rate__ = __Yaml_Setting__.__Installment_Savings__Get_Cycle_Interest_Rate__()
                * (double) __Cycle_Elapsed_date__;

        __Interest_Rate__ += __Cycle_Interest_Rate__;

        ItemStack __Icon_Bank__ = new ItemStack(Material.BOOK, 1);
        ItemMeta __Bank_ItemMeta__ = __Icon_Bank__.getItemMeta();
        __Bank_ItemMeta__.setDisplayName("§f적금통장");
        __Bank_ItemMeta__.setLore(new ArrayList<String>(Arrays.asList(
                "플레이어가 개설한 적금통장 입니다.", "",
                "개설일 : " + __in_Data__[3] + "(" + __Elapsed_date__ + " 일 경과)",
                "적금액 : " + __in_Data__[2], "적금이자 : " + __Interest_Rate__, "",
                "적금이자는 매일 증가합니다."
        )));
        __Icon_Bank__.setItemMeta(__Bank_ItemMeta__);

        return __Icon_Bank__;
    }

}
