package com.magarin.files;

/**
<pre>
YML File - __Mafia_Game_Message.yml
File_Handler = Support Class
</pre>
 *
 * 2021-02-03
 * @author Talk_Magarin
 */
public class File_Message extends File_Handler {

    private final static String m_Filename = "__Mafia_game_Message.yml";

    public static String __get_Message__Before_The_Game_Starts_Title_Main__(String __in_Tag) {
        String _Full_Tag = String.format("Before_The_Game_Starts.%s.Title_Main", __in_Tag);
        return (String)__get_Object__(m_Filename, _Full_Tag);
    }

    public static String __get_Message__Before_The_Game_Starts_Title_Sub__(String __in_Tag) {
        String _Full_Tag = String.format("Before_The_Game_Starts.%s.Title_Sub", __in_Tag);
        return (String)__get_Object__(m_Filename, _Full_Tag);
    }

}
