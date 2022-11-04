package com.myself.lession.getTxt;

import ch.qos.logback.core.joran.spi.XMLUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpUtil;
import com.myself.lession.Entity.Title;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author czy
 * @date 2021/9/10
 */
public class GetUrl {
    //http://www.q1xs.com/198_198868/
    //http://www.q1xs.com/198_198868/60426524.html
    public String getUrl() {
//        // 输入流
//        InputStream is = null;
//        BufferedReader br = null;
//        StringBuffer result = new StringBuffer();
//        HttpClient client = new HttpClient();
//        GetMethod getMethod = new GetMethod(url);
//        try {
//            int statusCode = client.executeMethod(getMethod);
//            // 判断返回码
//            if (statusCode != HttpStatus.SC_OK) {
//                // 如果状态码返回的不是ok,说明失败了,打印错误信息
//                System.err.println("Method faild: " + getMethod.getStatusLine());
//            } else {
//                // 通过getMethod实例，获取远程的一个输入流
//                is = getMethod.getResponseBodyAsStream();
//                // 包装输入流
//                br = new BufferedReader(new InputStreamReader(is, "GBK"));
//
//                // 读取封装的输入流
//                String temp = null;
//                while ((temp = br.readLine()) != null) {
//                    result.append(temp);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 关闭资源
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
//            getMethod.releaseConnection();
//        }
//        return result.toString();
        return HttpUtil.get(url);

    }

    //    public static void main(String[] args) throws IOException {
//        String url = "http://www.q1xs.com/198_198868/";
//        String content = getUrl(url);
//        List<Title> ddList = getInfo(content);
//        System.out.println(ddList);
//    }
    private static List<Title> getInfo(String content) {
        Document parse = Jsoup.parse(content);
        // Element title = parse.getElementsByTag("title").first();
//        System.out.println(document.getElementsByTag("title").first());
//        //像js一样，通过id 获取文章列表元素对象
//        Element postList = document.getElementById("post_list");
//        //像js一样，通过class 获取列表下的所有博客
//        Elements postItems = postList.getElementsByClass("post_item");
        Element listmain = parse.getElementsByClass("mulu_list").first();
        // Element all = listmain.getElementsByTag("dl").first();
        //Element all = parse.getElementById("list");
        Elements dd = listmain.getElementsByTag("a");
        List<Title> ddList = new ArrayList<>();

        for (int i = 0; i < dd.size(); i++) {
//            Element a = dd.get(i).getElementsByTag("a").first();
//            String href = a.attr("href");
//            String name = i+"_"+a.text();
//            Title title1 = new Title(name, href);
//            ddList.add(title1);
            String href = dd.get(i).attr("href");
            String name = i + "_" + dd.get(i).text();
            Title title1 = new Title(name, href);
            ddList.add(title1);
        }
        return ddList;
    }

    private String url;

    public GetUrl(String url) {
        this.url = url;
    }

    public List<Title> getAll() {
        String content = getUrl();
        List<Title> ddList = getInfo(content);
        return ddList;
    }


}
