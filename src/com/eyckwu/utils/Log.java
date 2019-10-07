package com.eyckwu.utils;

import java.util.Arrays;

/**
 * @author : EyckWu
 * @date : 2019/7/20
 * desc    : 打印工具类
 */
public class Log {
    public static void d(Object msg){
        d("", msg);
    }

    public static void d(Object[] msgs){
        d("", msgs);
    }

    public static void d(char[] c){
        d("", c);
    }

    public static void d(int[] i){
        d("", i);
    }

    public static void d(String tag, Object msg){
        System.out.println(tag + " : " + msg.toString());
    }

    public static void d(String tag, Object[] msgs){
        System.out.println(tag + " : " + Arrays.toString(msgs));
    }

    public static void d(String tag, char[] c){
        System.out.println(tag + " : " + Arrays.toString(c));
    }

    public static void d(String tag, int[] i){
        System.out.println(tag + " : " + Arrays.toString(i));
    }
}
