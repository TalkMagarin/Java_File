package com.magarin.economy_bank.yaml;

import com.magarin.economy_bank.Economy_Bank;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

/**
 * 기본적인 Yaml 파일을 제어하는 클래스 입니다.
 *
 * @version 2021-02-23
 * @author Talk_Magarin
 */
public class Yaml_Handler {

    private final Plugin __Plugin_Class__ = Economy_Bank.getPlugin(Economy_Bank.class);

    /**
     * Yaml 파일을 불러옵니다.
     * @param __in_Filename__ 파일명
     * @return FileConfiguration
     */
    private FileConfiguration __Handler__File_Load__(String __in_Filename__) {
        return YamlConfiguration.loadConfiguration(new File(__Plugin_Class__.getDataFolder(), __in_Filename__));
    }

    /**
     * Yaml 파일을 저장합니다.
     * @param __in_Yamlfile__ 저장할 파일정보
     * @param __in_Filename__ 파일명
     */
    private void __Handler__File_Save__(FileConfiguration __in_Yamlfile__, String __in_Filename__) {
        try {
            __in_Yamlfile__.save(new File(__Plugin_Class__.getDataFolder(), __in_Filename__));
        } catch (Exception __Exception__) {
            System.out.println(__Plugin_Class__.getName() + " Yaml파일을 저장하는 도중에 문제가 발생되었습니다.");
        }
    }

    /**
     * Yaml 파일에 데이터를 저장합니다.
     * @param __in_Filename__ 파일명
     * @param __in_Tag__      경로
     * @param __in_Value__    데이터
     */
    protected void __Handler__Data_Save__(String __in_Filename__, String __in_Tag__, Object __in_Value__) {
        FileConfiguration __Yaml_File__ = __Handler__File_Load__(__in_Filename__);

        if (__Yaml_File__ != null) {
            __Yaml_File__.set(__in_Tag__, __in_Value__);
            __Handler__File_Save__(__Yaml_File__, __in_Filename__);
        }
    }

    /**
     * Yaml 파일에 있는 데이터를 불러옵니다.
     * @param __in_Filename__ 파일명
     * @param __in_Tag__      경로
     * @return Object
     */
    protected Object __Handler__Data_Load__(String __in_Filename__, String __in_Tag__) {
        FileConfiguration __Yaml_File__ = __Handler__File_Load__(__in_Filename__);

        if (__Yaml_File__ != null)
             return __Yaml_File__.get(__in_Tag__);

        return null;
    }

}
