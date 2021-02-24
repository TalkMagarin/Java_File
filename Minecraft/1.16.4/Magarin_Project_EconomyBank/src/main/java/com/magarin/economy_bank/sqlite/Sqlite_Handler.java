package com.magarin.economy_bank.sqlite;

import com.magarin.economy_bank.Economy_Bank;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 기본적인 SQLite 을 제어하는 클래스 입니다.
 *
 * @version 2021-02-23
 * @author Talk_Magarin
 */
public class Sqlite_Handler {

    private final Plugin __Plugin_Class__ = Economy_Bank.getPlugin(Economy_Bank.class);
    private final String __Database_Filename__ = "__m__Economy_Bank__.db";

    private final String __Sqlite_JDBC__ = "jdbc:sqlite:";

    private String __Handler__Get_JDBC__() {
        return String.format("%s%s\\%s", __Sqlite_JDBC__
                , __Plugin_Class__.getDataFolder().getAbsolutePath(), __Database_Filename__);
    }

    private void __Handler__Create_Plugin_Directory__() {
        if (!__Plugin_Class__.getDataFolder().exists())
            __Plugin_Class__.getDataFolder().mkdirs();
    }

    /**
     * 데이터베이스 파일 존재하지 않을 경우 생성합니다.
     */
    protected void __Handler__Create_Database__() {
        __Handler__Create_Plugin_Directory__();

        File __Create_File__ = new File(__Plugin_Class__.getDataFolder(), __Database_Filename__);
        // 입력한 파일이 존재하지 않을 경우
        if (!__Create_File__.exists()) {
            try {
                // 파일 생성
                __Create_File__.createNewFile();
            } catch (IOException __IO_Exception__) {
                __IO_Exception__.printStackTrace();
            }
        }

    }

    /**
     * 데이터베이스에 테이블을 생성합니다.
     *
     * @param __in_Table_Name__ 테이블 명
     * @param __in_Query__ 적용할 쿼리문
     */
    protected void __Handler__Create_Table__(String __in_Table_Name__, String __in_Query__) {
        String __Format_Query__ = String.format(__in_Query__, __in_Table_Name__);

        try (Connection __Connection__ = DriverManager.getConnection(__Handler__Get_JDBC__());
             Statement __Statement__ = __Connection__.createStatement()) {
            __Statement__.execute(__Format_Query__);
        } catch (SQLException __SQL_Exception__) {
            __SQL_Exception__.printStackTrace();
        }
    }

    /**
     * 테이블에 데이터가 존재하는지 확인합니다.
     * @param __in_Query__ 적용할 쿼리문
     * @return int
     */
    protected int __Handler__Get_Data_Count__(String __in_Query__) {
        try (Connection __Connection__ = DriverManager.getConnection(__Handler__Get_JDBC__());
             Statement __Statement__ = __Connection__.createStatement();
             ResultSet __ResultSet__ = __Statement__.executeQuery(__in_Query__)) {
            if (__ResultSet__.next())
                return __ResultSet__.getInt("__total__");
        } catch (SQLException __SQL_Exception__) {
            __SQL_Exception__.printStackTrace();
        }

        return 0;
    }

    /**
     * 테이블에서 데이터를 불러옵니다.
     * @param __in_Query__ 적용할 쿼리문
     * @return ResultSet
     */
    protected ResultSet __Handler__Get_Data__(String __in_Query__) {
        try (Connection __Connection__ = DriverManager.getConnection(__Handler__Get_JDBC__());
             Statement __Statement__ = __Connection__.createStatement();
             ResultSet __ResultSet__ = __Statement__.executeQuery(__in_Query__)) {
            return __ResultSet__;
        } catch (SQLException __SQL_Exception__) {
            __SQL_Exception__.printStackTrace();
        }

        return null;
    }

    /**
     * 테이블에 데이터를 추가합니다.
     * @param __in_Query__ 적용할 쿼리문
     */
    protected void __Handler__Set_Data__(String __in_Query__) {
        try (Connection __Connection__ = DriverManager.getConnection(__Handler__Get_JDBC__());
             PreparedStatement __Statement__ = __Connection__.prepareStatement(__in_Query__)){
            __Statement__.executeUpdate();
        } catch (SQLException __SQL_Exception__) {
            __SQL_Exception__.printStackTrace();
        }
    }

}
