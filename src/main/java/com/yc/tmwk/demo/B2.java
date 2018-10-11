package com.yc.tmwk.demo;

/**
 * @Author b2
 */
public class B2 {

    public static int sum(int[] arr){
        int results = 0;
        for(int x = 0; x < arr.length; x++){
            results += arr[x];
        }
        return results;
    }

}
