package com.myself.lession.search;

import com.myself.lession.Entity.Title;
import com.myself.lession.getTxt.GetUrl;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author czy
 * @date 2021/9/10
 */
public class Baidu {
    private static String url  = "https://www.baidu.com/s?ie=utf-8&wd=";
    public static void getHtml(){
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(url + "华强北");
        InputStream in = null;
        BufferedReader br = null;
        StringBuilder result = null;
        try {
            in = getMethod.getResponseBodyAsStream();
            br = new BufferedReader(new InputStreamReader(in,"GBK"));
            // 读取封装的输入流
            String temp = null;
            while ((temp = br.readLine()) != null) {
                result.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            getMethod.releaseConnection();
        }
        System.out.println(result.toString());
    }

//    public static void main(String[] args) {
//        getHtml();
//        Comparable<Title> objectComparable = new Comparable<Title>(){
//            @Override
//            public int compareTo(Title o){
//                return 0;
//            }
//        };
//        ArrayList<Title> titles = new ArrayList<>(10);
//        Collections.sort(titles);
//    }

    public static void main(String[] args) {
         //getData(0,0);
       // System.out.println(list.toString());
        HashSet<Object> objects = new HashSet<>();
        boolean add = objects.add(null);
        System.out.println(add);
        LinkedList<Object> objects1 = new LinkedList<>();
        for (int i = 0; i < objects.size(); i++) {

        }
    }

    private static int[][] a = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
    private static final int x  = 3;
    private static final int y  = 3;
    private static  final List<Integer> list = new ArrayList<Integer>(20);
    private static int count = 0;
    private static int state = 0;
    /**
     * 获取值
     * 最后一个不输出留给下一个状态输出，下一个没有则直接输出

     * @param m 横坐标
     * @param n 纵坐标
     */
    public static void getData(int m, int n) {
        if (state==10){
            return ;
        }else {
            if (count == 2) {
                list.add(a[m][n]);
                state = 10;
            }
        }
        //向右
        if (state ==0){
            //可遍历
            if (m< (float)x/2){
                int i =m==0&&n==0?x - m - 1:x-m-2;
                for (;m<i;m++){
                    list.add(a[n][m]);
                }
                count = 0;
                state =1;
                getData(m,n);
            }
            //不可遍历
            else{
                count++;
                state =1;
                getData(m,n);
            }

        }
        //向下
        if (state ==1){
            //可遍历
            if (n+1< (float)y/2){
                int i = y - n - 1;
                for (;n<i;n++){
                    list.add(a[n][m]);
                }
                count = 0;
                state =2;
                getData(m,n);
            }
            //不可遍历
            else{
                count++;
                state =2;
                getData(m,n);
            }
        }
        //向左
        if (state ==2){
            //可遍历m< (float)x/2
            if (m>(float)x/2){
                int i = x - m - 1;
                for (;m>i;m--){
                    list.add(a[n][m]);
                }
                count = 0;
                state =3;
                getData(m,n);
            }
            //不可遍历
            else{
                count++;
                state =3;
                getData(m,n);
            }
        }
        //向上
        if (state ==3){
            //可遍历m< (float)x/2
            if (n>(float)y/2){
                int i = y - n;
                for (;n>i;n--){
                    list.add(a[n][m]);
                }
                count = 0;
                state =0;
                getData(m,n);
            }
            //不可遍历
            else{
                count++;
                state =0;
                getData(m,n);
            }
        }
    }

}
