package com.myself.lession.sdk;

import com.myself.lession.Entity.Title;
import com.myself.lession.getTxt.DownLoad;
import com.myself.lession.getTxt.GetUrl;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 爬取网页
 * @author czy
 * @date 2021/9/10
 */
public class download {

    public static void main(String[] args) {
        /**
         * 目录页
         */
        String url = "http://www.q1xs.com/198_198868/";
        /**
         * 下载位置
         */
        String port = "E://myProject//回到民国当小编";
        extracted(url, port);
    }

    private static void extracted(String url, String port) {
        GetUrl getUrl = new GetUrl(url);
        List<Title> all = getUrl.getAll();
        List<Title> use = all.subList(9, all.size());
        use.stream().parallel().forEach(title -> {
            DownLoad downLoad = new DownLoad(port, title);
            downLoad.writeArticle();
        });
    }
}
