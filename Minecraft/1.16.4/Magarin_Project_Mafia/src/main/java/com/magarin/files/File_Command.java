package com.magarin.files;

import java.util.List;

public class File_Command extends File_Handler {

    private final static String m_Filename = "__Mafia_game_Command.yml";

    /**
<pre>
명령어를 사용할 단축키를 가져옵니다.
Gets the shortcut to use the command.
</pre>
     * @return String
     */
    public static String __get_Command__Command_Character__() {
        return (String)__get_Object__(m_Filename, "Command_Character");
    }

    public static List<String> __get_Command__Before_The_Game_Starts_Help__(String __in_Tag) {
        String Full_Tag = String.format("Before_The_Game_Starts.Help.%s", __in_Tag);
        return (List<String>)__get_Object__(m_Filename, Full_Tag);
    }

    public static String __get_Command__Before_The_Game_Starts_Command__(String __in_Tag) {
        String Full_Tag = String.format("Before_The_Game_Starts.%s.Command", __in_Tag);
        return (String)__get_Object__(m_Filename, Full_Tag);
    }

    public static String __get_Command__Before_The_Game_Starts_Description__(String __in_Tag) {
        String Full_Tag = String.format("Before_The_Game_Starts.%s.Description", __in_Tag);
        return (String)__get_Object__(m_Filename, Full_Tag);
    }

}
