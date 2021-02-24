package com.magarin.economy_bank.sqlite;

import com.magarin.economy_bank.Economy_Handler;
import com.magarin.economy_bank.yaml.Yaml_Setting;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 데이터베이스에서 적금 테이블을 관리하는 클래스 입니다.
 *
 * @version 2021-02-23
 * @author Talk_Magarin
 */
public class Sqlite_Installment_Savings extends Sqlite_Handler {

    private static Sqlite_Installment_Savings __Sqlite_Installment_Savings__ = null;
    public static Sqlite_Installment_Savings __Instance__() {
        if (__Sqlite_Installment_Savings__ == null)
            __Sqlite_Installment_Savings__ = new Sqlite_Installment_Savings();
        return __Sqlite_Installment_Savings__;
    }

    private Yaml_Setting __Yaml_Setting__ = null;

    public Sqlite_Installment_Savings() {
        __Handler__Create_Database__();

        __Yaml_Setting__ = Yaml_Setting.__Instance__();
    }

    private final String __Table_Name__ = "eco_installment_savings";

    /**
     * 적금 관련 테이블을 생성합니다.
     */
    public void __Sqlite__Create_Table__() {
        String __Query__ = "CREATE TABLE IF NOT EXISTS %s (\n"
                + "	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                + "	player_uuid TEXT NOT NULL,\n"
                + "	balance INTEGER NOT NULL,\n"
                + "	open_day TEXT NOT NULL\n"
                + ");";

        __Handler__Create_Table__(__Table_Name__, __Query__);
    }

    /**
<pre>
플레이어가 개설한 적금이 최대 개설 수랑 같거나 큰지 확인합니다.
클 경우 : False
작을 경우 : True
</pre>
     * @param __in_Player_UUID__ 플레이어 고유아이디
     * @return True or False
     */
    public boolean __Sqlite__Is_Data__(String __in_Player_UUID__) {
        int __Max_Create__ = __Yaml_Setting__.__Installment_Savings__Get_Max_Create__();
        int __Player_Data_Count__ = __Sqlite__Get_Data_Count__(__in_Player_UUID__);
        
        if (__Player_Data_Count__ >= __Max_Create__)
            // 플레이어가 개설한 적금이 최대 개설 수랑 같거나 클 경우
            return false;
        else
            // 적을 경우
            return true;
    }

    public void __Sqlite__Create_Data__(String __in_Player_UUID__, int __in_Amount__) {
        String __Today__ = Economy_Handler.__Eco_Handler__Get_Today__();
        String __Query__ = String.format("INSERT INTO %s (player_uuid, balance, open_day) VALUES ('%s', %d, '%s');",
                __Table_Name__, __in_Player_UUID__, __in_Amount__, __Today__);
        __Handler__Set_Data__(__Query__);
    }

    /**
     * 플레이어의 데이터 수량을 불러옵니다.
     * @param __in_Player_UUID__ 플레이어 고유아이디
     * @return int
     */
    public int __Sqlite__Get_Data_Count__(String __in_Player_UUID__) {
        String __Query__ = String.format("SELECT COUNT(*) AS __total__ FROM %s WHERE player_uuid='%s'",
                __Table_Name__, __in_Player_UUID__);
        return __Handler__Get_Data_Count__(__Query__);
    }

    /**
     * 플레이어의 데이터 정보를 불러옵니다.
     * @param __in_Player_UUID__ 플레이어 고유아이디
     * @return String[][]
     */
    public String[][] __Sqlite__Get_Data__(String __in_Player_UUID__) {
        String[][] __Get_Datas__ = new String[__Sqlite__Get_Data_Count__(__in_Player_UUID__)][4];
        String __Query__ = String.format("SELECT * FROM %s WHERE player_uuid='%s'", __Table_Name__, __in_Player_UUID__);
        try {
            int __Count__ = 0;
            ResultSet __ResultSet__ = __Handler__Get_Data__(__Query__);

            while (__ResultSet__.next()) {
                __Get_Datas__[__Count__][0] = __ResultSet__.getInt(0) + "";
                __Get_Datas__[__Count__][1] = __ResultSet__.getString(1);
                __Get_Datas__[__Count__][2] = __ResultSet__.getInt(2) + "";
                __Get_Datas__[__Count__][3] = __ResultSet__.getString(3);
                __Count__++;
            }
        } catch (SQLException __SQL_Exception__) { __SQL_Exception__.printStackTrace(); }

        return __Get_Datas__;
    }

}
