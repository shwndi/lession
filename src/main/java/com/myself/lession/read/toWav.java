package com.myself.lession.read;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 音频输出
 *
 * @author czy
 * @date 2021/8/17
 */
public class toWav {
    private volatile static int number ;
    public static void main(String[] args) {
        String fileName = "E:\\从木叶开始逃亡.txt";
        String s = readFileContent(fileName);
        String[] strings = spiltArtical(s);
        new Thread(()->{
            while(true){
                Scanner input=new Scanner(System.in);
                number = input.nextInt();
            }
        }).start();
        for (int i = 0; i < strings.length; i++) {
            //指定文件音频输出文件位置
            number = i;
            String path = "E:\\第" + number + "章.wav";
            getWav(strings[number], path);
        }
    }

    public static String readFileContent(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    public static String[] spiltArtical(String s) {
        String[] result = s.split("正文 第");
        for (int i = 1; i < result.length; i++) {
            int star = result[i].indexOf(" ");
            int end =result[i].indexOf("   ");
            String substring = result[i].substring(star, end);
            System.out.println("第"+i +"章  "+substring);
        }
        return result;
    }

    public static void getWav(String s, String path) {
        //指定文件音频输出文件位置
        String output = path;
        ActiveXComponent ax = null;
        String str = s;
        try {
            ax = new ActiveXComponent("Sapi.SpVoice");

            //运行时输出语音内容
            Dispatch spVoice = ax.getObject();
            // 音量 0-100
            ax.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            ax.setProperty("Rate", new Variant(-3));
            // 进行朗读
            Dispatch.call(spVoice, "Speak", new Variant(str));

            //下面是构建文件流把生成语音文件

            ax = new ActiveXComponent("Sapi.SpFileStream");
            Dispatch spFileStream = ax.getObject();

            ax = new ActiveXComponent("Sapi.SpAudioFormat");
            Dispatch spAudioFormat = ax.getObject();

            //设置音频流格式
            Dispatch.put(spAudioFormat, "Type", new Variant(22));
            //设置文件输出流格式
            Dispatch.putRef(spFileStream, "Format", spAudioFormat);
            //调用输出 文件流打开方法，在指定位置输出一个.wav文件
            Dispatch.call(spFileStream, "Open", new Variant(output), new Variant(3), new Variant(true));
            //设置声音对象的音频输出流为输出文件对象
            Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
            //设置音量 0到100
            Dispatch.put(spVoice, "Volume", new Variant(100));
            //设置朗读速度
            Dispatch.put(spVoice, "Rate", new Variant(-2));
            //开始朗读
            Dispatch.call(spVoice, "Speak", new Variant(str));

            //关闭输出文件
            Dispatch.call(spFileStream, "Close");
            Dispatch.putRef(spVoice, "AudioOutputStream", null);

            spAudioFormat.safeRelease();
            spFileStream.safeRelease();
            spVoice.safeRelease();
            ax.safeRelease();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
