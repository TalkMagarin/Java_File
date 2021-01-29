package com.magarin.files;

import com.magarin.MafiaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;

/**
 * YML File - Support Class
 *
 * 2021-01-21
 * @author Talk_Magarin
 */
public class File_Handler {

    /**
<pre>
MafiaPlugin 클래스의 특정 기능을 사용하도록 선언합니다.
Declare to use specific features of the MafiaPlugin class
</pre>
     */
    protected static Plugin m_Plugin = MafiaPlugin.getPlugin(MafiaPlugin.class);

    /**
<pre>
플러그인 폴더에 있는 지정된 파일을 불러옵니다.
Recalls the specified files that exist in the plug-in folder.
</pre>
     * @param in_Filename 파일명 / Filename
     * @return 불러올 파일 정보 / the specified files
     */
    protected static FileConfiguration __load_Yml_File__(String in_Filename) {
        File _File = new File(m_Plugin.getDataFolder(), in_Filename);

        if (_File.exists())
            return YamlConfiguration.loadConfiguration(new File(m_Plugin.getDataFolder(), in_Filename));
        else
            return null;
    }

    /**
<pre>
플러그인 폴더에 있는 지정된 파일을 저장합니다.
Saves the specified file in the plug-in folder.
</pre>
     * @param in_YmlConfigFile 저장할 파일 정보 / the specified files
     * @param in_Filename 파일명 / Filename
     */
    protected static void __save_Yml_File__(FileConfiguration in_YmlConfigFile, String in_Filename) {
        try {
            in_YmlConfigFile.save(new File(m_Plugin.getDataFolder(), in_Filename));
        } catch (Exception _Exception) {
            System.out.println("[System] Error, " + in_Filename + " Yaml file a not save...");
        }
    }

    /**
<pre>
YamlConfiguration 파일에서 Tag 값을 지정하여 값을 가져옵니다.
Tag Values to load at Yaml Configuration File.
</pre>
     * @param in_Filename 파일명 / Filename
     * @param in_Tag 불러올 태그 값 / Tag Values to load (ex: Info.Data)
     * @return Object
     */
    protected static Object __get_Object__(String in_Filename, String in_Tag) {
        FileConfiguration _Config = __load_Yml_File__(in_Filename);
        if (_Config != null)
            if (in_Tag.contains("Lore"))
                return _Config.getStringList(in_Tag);
            else
                return _Config.get(in_Tag);
        else
            return null;
    }

    /**
<pre>
YML 파일에서 Node 값을 지정하여 가져온 값을 저장합니다.
Saves the imported value to the Yaml file Tag value.
</pre>
     * @param in_Filename 파일명 / Filename
     * @param in_Tag 저장할 태그 값 / Tag Values to save (ex: Info.Data)
     * @param in_Function 데이터 타입 / Data Function to save
     * @param in_Value Data values to save
     */
    @SuppressWarnings("unchecked")
    protected static void __set_Object__(String in_Filename, String in_Tag, String in_Function, Object in_Value) {
        // 데이터를 저장하기 위해 yaml 파일 불러오기
        // Calling up the yaml file to store data.
        FileConfiguration _Config = __load_Yml_File__(in_Filename);
        // yaml 파일이 존재할 경우
        // If you have a yaml file
        if (_Config != null) {
            // 데이터를 문자열로 저장
            // save data with string
            if (in_Function.equalsIgnoreCase("String"))
                _Config.set(in_Tag, (String) in_Value);
            // 데이터를 부울로 저장
            // save data with boolean
            else if (in_Function.equalsIgnoreCase("boolean"))
                _Config.set(in_Tag, (boolean) in_Value);
            // 데이터를 정수로 저장
            // save data with int
            else if (in_Function.equalsIgnoreCase("int"))
                _Config.set(in_Tag, (int) in_Value);
            // 데이터를 소수로 저장
            // save data with double
            else if (in_Function.equalsIgnoreCase("double"))
                _Config.set(in_Tag, (double) in_Value);
            // 데이터를 Null로 저장 (데이터 삭제)
            // save data with null (Delete Data)
            else if (in_Function.equalsIgnoreCase("null"))
                _Config.set(in_Tag, null);
            // 데이터를 리스트<문자열>로 저장
            // save data with list<string>
            else if (in_Function.equalsIgnoreCase("List<String>"))
                _Config.set(in_Tag, (List<String>) in_Value);

            __save_Yml_File__(_Config, in_Filename);
        }
    }

}
