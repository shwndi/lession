package com.myself.lession.getTxt;

import cn.hutool.http.HttpUtil;
import com.myself.lession.Entity.Title;
import org.apache.catalina.connector.OutputBuffer;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 下载
 *
 * @author czy
 * @date 2021/9/10
 */
public class DownLoad {
    //下载位置
    private String post;
    private static String ERR_DOWN_LOAD = "err_down_load.txt";
    private Title title;

    public DownLoad(String post, Title title) {
        this.post = post;
        this.title = title;
    }

    public String DownLoad() {
        return HttpUtil.get("https://www.92qb.com/xiaoshuo/13/13487/"+title.getUrl());
        // 输入流
//        InputStream is = null;
//        BufferedReader br = null;
//        StringBuffer result = new StringBuffer();
//        //1，获取页面
//        HttpClient client = new HttpClient();
//        GetMethod method = new GetMethod(title.getUrl());
//        int i = 0;
//        try {
//            i = client.executeMethod(method);
//            if (i != HttpStatus.SC_OK) {
//                return null;
//            }
//            is = method.getResponseBodyAsStream();
//            br = new BufferedReader(new InputStreamReader(is, "GBK"));
//            String temp = null;
//            while ((temp = br.readLine()) != null) {
//                result.append(temp);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (null != br) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (null != is) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            // 释放连接
//            method.releaseConnection();
//        }
//        return result.toString();
    }

    //2.解析页面
    public static String getArticle(String html) {
        if (html == null || html == "") {
            return null;
        }
        Document parse = Jsoup.parse(html);
        String content = parse.getElementById("htmlContent").text();
        return content;
    }

    //3.放入输出流
    //4.写入文件
    public void write(String content) {
        FileOutputStream outputStream = null;
        File directory = new File(post);
        if (!directory.exists()) {
            directory.mkdir();
        }
        OutputStream os = null;
        try {
            if (content == null) {
                File file = new File(post, ERR_DOWN_LOAD);
                if (!file.exists()) {
                    file.createNewFile();
                    outputStream = new FileOutputStream(file);
                    outputStream.write(title.toString().getBytes());
                }
            }
            File file = new File(post, title.getName().replace("**","") + ".txt");
            System.out.println(file);
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            String s = content.replaceAll(" ", "\r\n");
            outputStream.write(s.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println(title.getName());
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void writeArticle() {
        write(getArticle(DownLoad()));
    }
//    public static void main(String[] args) {
//        Title title = new Title("第二十三章，物理劝退！", "/198_198868/60426574.html");
//        String s = DownLoad(title);
//        String article = getArticle(s);
//        DownLoad load = new DownLoad("E://myProject//凡人修仙");
//        load.write(article,title);
//    }
}
