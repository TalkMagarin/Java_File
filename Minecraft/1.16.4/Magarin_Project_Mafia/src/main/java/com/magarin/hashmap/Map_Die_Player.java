package com.magarin.hashmap;

import java.util.HashMap;

/**
<pre>
플레이어가 죽었는지 확인만 하는 클래스
</pre>
 *
 * 2021-02-17
 * @author Talk_Magarin
 */
public class Map_Die_Player {

    private static HashMap<String, Boolean> __Map_Jobs_DiePLayer_List__ = new HashMap<>();

    /**
     * 플레이어가 죽었는지 살았는지 확인합니다. 살았을 경우 True
     * @param __in_Player_name__ 플레이어명
     * @return Boolean
     */
    public static Boolean __Die__Check_Player__(String __in_Player_name__) {
        if (__Map_Jobs_DiePLayer_List__.get(__in_Player_name__) == null)
            return null;
        else
            return __Map_Jobs_DiePLayer_List__.get(__in_Player_name__);
    }

    /**
     * 플레이어가 죽었을 경우 데이터를 True 에서 False 로 변경합니다.
     * @param __in_Player_name 플레이어명
     */
    public static void __Die__Set_Player__(String __in_Player_name) {
        __Map_Jobs_DiePLayer_List__.replace(__in_Player_name, false);
    }

    /**
     * 플레이어를 데이터에 추가합니다.
     * @param __in_Player_name 플레이어명
     */
    public static void __Die__Join_Player_List__(String __in_Player_name) {
        __Map_Jobs_DiePLayer_List__.put(__in_Player_name, true);
    }

    /**
     * 저장되어있는 데이터를 초기화 합니다.
     */
    public static void __Die__Join_Player_List_Clear__() {
        __Map_Jobs_DiePLayer_List__.clear();
    }

}
