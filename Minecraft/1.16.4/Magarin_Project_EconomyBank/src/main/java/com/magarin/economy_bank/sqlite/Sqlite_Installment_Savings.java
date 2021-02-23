package com.magarin.economy_bank.sqlite;

import com.magarin.economy_bank.Economy_Handler;
import com.magarin.economy_bank.yaml.Yaml_Setting;

import java.sql.ResultSet;

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
        String __Query__ = String.format("SELECT COUNT(*) AS __total__ FROM %s WHERE player_uuid='%s'",
                __Table_Name__, __in_Player_UUID__);

        int __Max_Create__ = __Yaml_Setting__.__Installment_Savings__Get_Max_Create__();
        int __Player_Data_Count__ = __Handler__Get_Data_Count__(__Query__);
        
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

}
