package com.myself.lession.read;

/**
 * @author czy
 * @date 2021/8/17
 */
public class Test {
    public static void main(String[] args) {
        String s = "asfldkjasasfsfa";
        String[] split = s.split("a");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }
}
