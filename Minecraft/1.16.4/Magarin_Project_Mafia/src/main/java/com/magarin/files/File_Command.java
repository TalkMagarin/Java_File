package com.magarin.files;

import java.util.List;

/**
<pre>
YML File - __Mafia_Game_Command.yml
File_Handler = Support Class
</pre>
 *
 * 2021-02-01
 * @author Talk_Magarin
 */
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

    /**
<pre>
Help 명령어에 관한 자료를 불러옵니다.
Recalls data related to the Help command.
</pre>
     * @param __in_Tag 명령어 태그 / Command tag
     * @return List<String>
     */
    public static List<String> __get_Command__Before_The_Game_Starts_Help__(String __in_Tag) {
        String Full_Tag = String.format("Before_The_Game_Starts.Help.%s", __in_Tag);
        return (List<String>)__get_Object__(m_Filename, Full_Tag);
    }

    /**
<pre>
입력한 Tag의 명령어를 가져옵니다.
Gets the command of the Tag you entered.
</pre>
     * @param __in_Tag 명령어 태그 / Command tag
     * @return String
     */
    public static String __get_Command__Before_The_Game_Starts_Command__(String __in_Tag) {
        String Full_Tag = String.format("Before_The_Game_Starts.%s.Command", __in_Tag);
        return (String)__get_Object__(m_Filename, Full_Tag);
    }

    /**
<pre>
입력한 Tag의 명령어 설명을 가져옵니다.
Gets the command description of the Tag you entered.
</pre>
     * @param __in_Tag 명령어 태그 / Command tag
     * @return String
     */
    public static String __get_Command__Before_The_Game_Starts_Description__(String __in_Tag) {
        String Full_Tag = String.format("Before_The_Game_Starts.%s.Description", __in_Tag);
        return (String)__get_Object__(m_Filename, Full_Tag);
    }

    /**
<pre>
List에 속해있는 Tag 값을 변환합니다.
Converts Tag values that belong to the list.
</pre>
     * @param __in_Help_Command_Text Help 명령어 값 / help command value
     * @return String
     */
    public static String __get_Command__Change_Help_Command__(String __in_Help_Command_Text) {
        __in_Help_Command_Text = __in_Help_Command_Text.replace("{C_C}", __get_Command__Command_Character__());
        __in_Help_Command_Text = __in_Help_Command_Text.replace("{C_Join_Game}", __get_Command__Before_The_Game_Starts_Command__("Join_Game"));
        __in_Help_Command_Text = __in_Help_Command_Text.replace("{D_Join_Game}", __get_Command__Before_The_Game_Starts_Description__("Join_Game"));
        __in_Help_Command_Text = __in_Help_Command_Text.replace("{C_Exit_Game}", __get_Command__Before_The_Game_Starts_Command__("Exit_Game"));
        __in_Help_Command_Text = __in_Help_Command_Text.replace("{D_Exit_Game}", __get_Command__Before_The_Game_Starts_Description__("Exit_Game"));
        __in_Help_Command_Text = __in_Help_Command_Text.replace("{C_Start_Game}", __get_Command__Before_The_Game_Starts_Command__("Start_Game__Chief_Of_The_Room_Player"));
        __in_Help_Command_Text = __in_Help_Command_Text.replace("{D_Start_Game}", __get_Command__Before_The_Game_Starts_Description__("Start_Game__Chief_Of_The_Room_Player"));
        __in_Help_Command_Text = __in_Help_Command_Text.replace("{C_Check_Player}", __get_Command__Before_The_Game_Starts_Command__("Check_Join_Player"));
        __in_Help_Command_Text = __in_Help_Command_Text.replace("{D_Check_Player}", __get_Command__Before_The_Game_Starts_Description__("Check_Join_Player"));

        return __in_Help_Command_Text;
    }

}
