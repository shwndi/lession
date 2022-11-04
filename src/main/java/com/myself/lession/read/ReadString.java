package com.myself.lession.read;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.File;
import java.util.Arrays;
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
    private volatile static int number =27;
    private volatile static int intline = 0;

    //用电脑自带的语音读字符串str
    public static void main(String[] args) throws InterruptedException {
        String fileName = "E:\\myProject\\重生之大科学家";
        String[] fileNames = readFileNames(fileName);
        for (int i = number; i < fileNames.length; i++) {
            String readFileName = fileName + "\\" + fileNames[i];
            String s = readFileContent(readFileName);
            extracted(s);
        }
//        String s = readFileContent(fileName);
//        String[] strings = spiltArtical(s);
        new Thread(() -> {
            while (true) {
                Scanner input = new Scanner(System.in);
                String next = input.next();
                char c = next.charAt(0);
                switch (c) {
                    //暂停
                    case '0':
                        break;
                    //启动
                    case '1':
                        break;
                    //跳转到底章节
                    case '2':
                        System.out.println("章节跳转");
                        number = Integer.valueOf(next.substring(1, next.length()));
                        break;
                    //跳转的第几行
                    case '3':
                        System.out.println("行数跳转");
                        intline = Integer.valueOf(next.substring(1, next.length()));
                        break;
                    case '4':
                        System.out.println("下一章");
                        number = number + 1;
                        break;
                    case '5':
                        System.out.println("上一章");
                        number = number - 2;
                        break;
                    case '6':
                        System.out.println("第" + number + "章 第" + intline + "行");
                        break;
                    case '9':
                        demoInfo();
                        break;
                    default:
                        System.out.println("请输入正确的指令");
                }
            }
        }).start();
//        new Thread(() -> {
//
//        }, "A").start();
//        for (int i = 1; i < strings.length; i++) {
//            number = i;
//            extracted(strings[number]);
//            System.out.println("第" + i + "章");
//            String str = strings[i];
//            String regex = "   ";
//            String[] aaa = str.split(regex);
//            System.out.println(aaa[0]);
//            for (intline = 0; intline < aaa.length; intline++) {
//                extracted(aaa[intline]);
//                if (number != i) {
//                    i = number;
//                    break;
//                }
//            }
    }

    private static String[] readFileNames(String fileName) {
        File file = new File(fileName);
        String[] list = file.list();
        Object[] objects = Arrays.stream(list).sorted((a, b) ->
                Integer.valueOf(a.substring(0, a.indexOf("_"))) - Integer.valueOf(b.substring(0, b.indexOf("_")))
        ).toArray();
        for (int i = 0; i < objects.length; i++) {
            list[i] = (String) objects[i];
        }
        return list;
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
        System.out.println("    4:下一章；   例如：“4”    ");
        System.out.println("    5:上一章；   例如：“5”    ");
        System.out.println("    6:当前位置； 例如：“第X章 第Y行”    ");
        System.out.println("    9:帮助；     例如：“9”    ");
        System.out.println("*****************************************");
    }

    /**
     * 阅读
     *
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
