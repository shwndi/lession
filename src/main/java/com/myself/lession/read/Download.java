package com.myself.lession.read;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * 下载
 *
 * @author czy
 * @date 2021/8/19
 */
public class Download {
    public static void main(String[] args) throws IOException {
        String url = "http://down.360wxw.com/all/18179/1247135/%E8%81%94%E7%9B%9F%E4%B9%8B%E6%90%9E%E4%BA%8B%E5%B0%B1%E8%83%BD%E5%8F%98%E5%BC%BA%E5%85%A8%E6%96%87@www.360wxw.com.txt";
        HttpClient client = new HttpClient();
        HttpMethod method = new GetMethod(url);
        client.executeMethod(method);


    }
}
