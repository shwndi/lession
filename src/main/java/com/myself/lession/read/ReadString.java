package com.myself.lession.read;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.util.Scanner;

import static com.myself.lession.read.toWav.readFileContent;
import static com.myself.lession.read.toWav.spiltArtical;

/**
 * 语音朗读
 *
 * @author czy
 * @date 2021/8/17
 */
public class ReadString {
    private volatile static int number ;
    private volatile static int intline;
    //用电脑自带的语音读字符串str
    public static void main(String[] args) {
        String fileName = "E:\\从木叶开始逃亡.txt";
        String s = readFileContent(fileName);
        String[] strings = spiltArtical(s);
        new Thread(()->{
            while(true){
                Scanner input=new Scanner(System.in);
                String next = input.next();
                char c = next.charAt(0);
                switch (c){
                    //暂停
                    case '0': break;
                    //启动
                    case '1':break;
                    //跳转到底章节
                    case '2':
                        System.out.println("章节跳转");
                        number = Integer.valueOf(next.substring(1,next.length()));
                        break;
                    //跳转的第几行
                    case '3':
                        System.out.println("行数跳转");
                        intline = Integer.valueOf(next.substring(1,next.length()));
                        break;
                    case '9':
                        demoInfo();
                        break;
                }
            }
        }).start();
        new Thread(()->{

        },"A").start();
        for (int i = 120; i < strings.length; i++) {
            number = i;
            System.out.println("第"+i+"章");
            String str = strings[i];
            String regex = "   ";
            String[] aaa = str.split(regex);
            System.out.println(aaa[0]);
            for ( intline = 0; intline < aaa.length; intline++) {
                if (number!=i){
                    i=number;
                    break;
                }
                System.out.println("第"+intline+"行");
                extracted(aaa[intline]);
            }
        }
    }

    /**
     * 说明书
     */
    private static void demoInfo() {
        System.out.println("*****************************************");
        System.out.println("    0:暂停；     例如：“0”       ");
        System.out.println("    1:继续；     例如：“1”       ");
        System.out.println("    2:跳转章节； 例如：“2XXX”    ");
        System.out.println("    3:跳转行数； 例如：“3XXX”    ");
        System.out.println("*****************************************");
    }

    /**
     * 阅读
     * @param str
     */
    private static void extracted(String str) {
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
        Dispatch sapo = sap.getObject();
        try {
            // 音量 0-100
            sap.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            sap.setProperty("Rate", new Variant(5));
            // 执行朗读
            Dispatch.call(sapo, "Speak", new Variant(str));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sapo.safeRelease();
            sap.safeRelease();
        }
    }
}
