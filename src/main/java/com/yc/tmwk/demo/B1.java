package com.yc.tmwk.demo;

import java.util.Scanner;

/**
 * @Author b1
 */
public class B1 {
    public static int[] recv(){
        int[] arr = new int[10];
        System.out.println("Please input 10 positive integer：" );
        Scanner input = new Scanner(System.in);
        for(int i = 0 ; i < 10; i++){
            arr[i] = input.nextInt();
        }
        return arr;
    }
}
