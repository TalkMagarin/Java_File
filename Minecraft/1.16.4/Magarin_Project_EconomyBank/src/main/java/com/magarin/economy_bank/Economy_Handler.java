package com.magarin.economy_bank;


import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 공통적으로 자주 사용되는 함수를 모아놓은 클래스 입니다.
 *
 * @version 2021-02-23
 * @author Talk_Magarin
 */
public class Economy_Handler {

    /**
     * 금일 날짜를 불러옵니다.
     * @return String
     */
    public static String __Eco_Handler__Get_Today__() {
        SimpleDateFormat __Date_Format__ = new SimpleDateFormat("yyyy-MM-dd");
        Calendar __Today_Calender__ = Calendar.getInstance();

        return __Date_Format__.format(__Today_Calender__.getTime());
    }

    /**
     * 개설된 일로부터 경과된 일 수를 불러옵니다.
     * @param __in_Open_Day__ 개설일
     * @return int
     */
    public static int __Eco_Handler__Get_Elapsed_date__(String __in_Open_Day__) {
        String[] __Split_Day__ = __in_Open_Day__.split("-");
        int __Elapsed_date__ = 0;

        SimpleDateFormat __Date_Format__ = new SimpleDateFormat("yyyy-MM-dd");
        Calendar __Today_Calender__ = Calendar.getInstance();
        Calendar __Openday_Calender__ = Calendar.getInstance();
        __Openday_Calender__.set(Integer.parseInt(__Split_Day__[0]), (Integer.parseInt(__Split_Day__[1]) - 1), Integer.parseInt(__Split_Day__[2]));

        String __Today__ = __Date_Format__.format(__Today_Calender__.getTime());

        while(true) {
            String __Day__ = __Date_Format__.format(__Openday_Calender__.getTime());

            if (__Today__.equalsIgnoreCase(__Day__)) {
                break;
            } else {
                __Elapsed_date__++;
                __Openday_Calender__.add(Calendar.DATE, 1);
            }
        }

        return __Elapsed_date__;
    }

}
