package com.ming.testandlearn.concurrent;

/**
 * Created by ming on 2016/1/21.
 */
public class LazzyInital {

    //private volatile FieldType field = new ComputeFieldValue();
    /**double check*/
    private volatile  Integer integer;
    Integer getInteger(){
        Integer result = integer;
        if(result == null){
            synchronized (this){
                result = integer;
                if(result == null){
                    integer = result = new Integer(100);
                }
            }
        }
        return result;
    }

}
