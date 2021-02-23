package com.magarin.economy_bank.yaml;

/**
 * Setting Yaml 파일을 제어하는 클래스 입니다.
 *
 * @version 2021-02-23
 * @author Talk_Magarin
 */
public class Yaml_Setting extends Yaml_Handler {

    private static Yaml_Setting __Yaml_Setting__ = null;
    public static Yaml_Setting __Instance__() {
        if (__Yaml_Setting__ == null)
            __Yaml_Setting__ = new Yaml_Setting();
        return __Yaml_Setting__;
    }

    private final String __Yaml_Filename__ = "__Bank_Setting__.yml";

    /**
     * 개설이 가능한 최대 수를 불러옵니다.
     * @return int
     */
    public int __Installment_Savings__Get_Max_Create__() {
        String __Tag__ = "__Installment_Savings__.__Max_Create__";
        return (int)__Handler__Data_Load__(__Yaml_Filename__, __Tag__);
    }

    /**
     * 개설시 필요한 최소한의 금액을 불러옵니다.
     * @return int
     */
    public int __Installment_Savings__Get_Min_Amount__() {
        String __Tag__ = "__Installment_Savings__.__Min_Amount__";
        return (int)__Handler__Data_Load__(__Yaml_Filename__, __Tag__);
    }

    /**
     * 개설시 필요한 최대한의 금액을 불러옵니다.
     * @return int
     */
    public int __Installment_Savings__Get_Max_Amount__() {
        String __Tag__ = "__Installment_Savings__.__Max_Amount__";
        return (int)__Handler__Data_Load__(__Yaml_Filename__, __Tag__);
    }

    /**
     * 개설 후 이자율을 상승시길 일 수를 불러옵니다.
     * @return int
     */
    public int __Installment_Savings__Get_Cycle_Day__() {
        String __Tag__ = "__Installment_Savings__.__Cycle__.__Day__";
        return (int)__Handler__Data_Load__(__Yaml_Filename__, __Tag__);
    }

    /**
     * 개설시 최초 이자율을 불러옵니다.
     * @return double
     */
    public double __Installment_Savings__Get_Interest_Rate__() {
        String __Tag__ = "__Installment_Savings__.__Interest_Rate__";
        return (double)__Handler__Data_Load__(__Yaml_Filename__, __Tag__);
    }

    /**
     * 개설 후 지정된 일 수 마다 상승될 이자율을 불러옵니다.
     * @return double
     */
    public double __Installment_Savings__Get_Cycle_Interest_Rate__() {
        String __Tag__ = "__Installment_Savings__.__Cycle__.__Interest_Rate__";
        return (double)__Handler__Data_Load__(__Yaml_Filename__, __Tag__);
    }

}
