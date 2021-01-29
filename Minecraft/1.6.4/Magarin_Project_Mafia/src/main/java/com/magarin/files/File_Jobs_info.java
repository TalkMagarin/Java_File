package com.magarin.files;

/**
<pre>
YML File - __Mafia_Game_Jobs_Info.yml
File_Handler = Support Class
</pre>
 *
 * 2021-01-29
 * @author Talk_Magarin
 */
public class File_Jobs_info extends File_Handler {

    private final static String m_Filename = "__Mafia_game_jobs_info.yml";

    /**
<pre>
태그한 직업의 이름을 가져옵니다.
Gets the name of the jobs entered in the tag.
</pre>
     * @param __in_Tag_Jobs 직업 이름 태그 / Job name tag
     * @return String
     */
    public static String __get_Jobs_info__Name__(String __in_Tag_Jobs) {
        String _Full_Tag = String.format("%s.Name", __in_Tag_Jobs);
        return (String)__get_Object__(m_Filename, _Full_Tag);
    }

    /**
<pre>
태그한 직업의 설명을 가져옵니다.
Gets the description of the jobs entered in the tag.
</pre>
     * @param __in_Tag_Jobs 직업 이름 / Job name
     * @return String
     */
    public static String __get_Jobs_info__Description__(String __in_Tag_Jobs) {
        String _Full_Tag = String.format("%s.Description", __in_Tag_Jobs);
        return (String)__get_Object__(m_Filename, _Full_Tag);
    }

}
