package com.magarin.economy_bank;

import com.magarin.economy_bank.sqlite.Sqlite_Installment_Savings;
import com.magarin.economy_bank.yaml.Yaml_Setting;
import org.bukkit.plugin.java.JavaPlugin;

public final class Economy_Bank extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        __on_Resource_file__();

        __on_Sqlite_Class_Instance__();
        __on_Sqlite_Table__();
        __Sqlite_Installment_Savings__.__Sqlite__Create_Data__("Talk_Magarin", 2000);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * 커스텀으로 제작된 Yaml 파일을 생성합니다.
     */
    private void __on_Resource_file__() {
        saveResource("__Bank_Setting__.yml", false);
    }

    private Sqlite_Installment_Savings __Sqlite_Installment_Savings__ = null;

    private Yaml_Setting __Yaml_Setting__ = null;

    /**
     * 데이터베이스를 관리하는 클래스를 Instance 로 생성합니다.
     */
    private void __on_Sqlite_Class_Instance__() {
        __Sqlite_Installment_Savings__ = Sqlite_Installment_Savings.__Instance__();

        __Yaml_Setting__ = Yaml_Setting.__Instance__();
    }

    /**
     * 데이터베이스에 각 테이블을 생성합니다.
     */
    private void __on_Sqlite_Table__() {
        __Sqlite_Installment_Savings__.__Sqlite__Create_Table__();
    }

}
