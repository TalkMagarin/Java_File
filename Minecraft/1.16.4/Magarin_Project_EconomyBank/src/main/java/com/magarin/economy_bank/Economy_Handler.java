package com.magarin.economy_bank;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 공통적으로 자주 사용되는 함수를 모아놓은 클래스 입니다.
 *
 * @version 2021-02-23
 * @author Talk_Magarin
 */
public class Economy_Handler {

    /**
     * 금일 날짜를 불러옵니다.
     * @return String
     */
    public static String __Eco_Handler__Get_Today__() {
        SimpleDateFormat __Date_Format__ = new SimpleDateFormat("yyyy-MM-dd");
        Calendar __Today_Calender__ = Calendar.getInstance();

        return __Date_Format__.format(__Today_Calender__.getTime());
    }

    /**
     * 개설된 일로부터 경과된 일 수를 불러옵니다.
     * @param __in_Open_Day__ 개설일
     * @return int
     */
    public static int __Eco_Handler__Get_Elapsed_date__(String __in_Open_Day__) {
        String[] __Split_Day__ = __in_Open_Day__.split("-");
        int __Elapsed_date__ = 0;

        SimpleDateFormat __Date_Format__ = new SimpleDateFormat("yyyy-MM-dd");
        Calendar __Today_Calender__ = Calendar.getInstance();
        Calendar __Openday_Calender__ = Calendar.getInstance();
        __Openday_Calender__.set(Integer.parseInt(__Split_Day__[0]), (Integer.parseInt(__Split_Day__[1]) - 1), Integer.parseInt(__Split_Day__[2]));

        String __Today__ = __Date_Format__.format(__Today_Calender__.getTime());

        while(true) {
            String __Day__ = __Date_Format__.format(__Openday_Calender__.getTime());

            if (__Today__.equalsIgnoreCase(__Day__)) {
                break;
            } else {
                __Elapsed_date__++;
                __Openday_Calender__.add(Calendar.DATE, 1);
            }
        }

        return __Elapsed_date__;
    }

    private static Economy __Economy__;

    /**
     * Vault 의 라이브러리를 정상적으로 사용할 수 있는지 확인합니다.
     * @return boolean
     */
    public static boolean __Eco_Handler__Setup_Economy__() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null)
            return false;

        RegisteredServiceProvider<Economy> __Provider_Economy__ = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
        if (__Provider_Economy__ == null)
            return false;

        __Economy__ = __Provider_Economy__.getProvider();

        return true;
    }

    /**
     * 플레이어의 소지금을 설정합니다.
     * @param __in_Player__ 플레이어
     * @param __in_Amount__ 소지금
     */
    public static void __Eco_Handler__Set_Balance__(Player __in_Player__, double __in_Amount__) {
        __Economy__.depositPlayer(__in_Player__, __in_Amount__);
    }

    /**
     * 플레이어의 소지금을 불러옵니다.
     * @param __in_Player__ 플레이어
     * @return double
     */
    public static double __Eco_Handler__Get_Balance__(Player __in_Player__) {
        return __Economy__.getBalance(__in_Player__);
    }

}
