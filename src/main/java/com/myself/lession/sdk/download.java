package com.myself.lession.sdk;

import com.myself.lession.Entity.Title;
import com.myself.lession.getTxt.DownLoad;
import com.myself.lession.getTxt.GetUrl;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 爬取网页
 * @author czy
 * @date 2021/9/10
 */
public class download {

    public static void main(String[] args) {
        new HashMap<>();
        /**
         * 目录页
         */
        String url = "https://www.92qb.com/xiaoshuo/13/13487/";
        /**
         * 下载位置
         */
        String port = "E://myProject//重生之大科学家";
        /**
         * 最前面目录重复章节
         */
        int index = 0;
        extracted(index,url, port);
    }

    private static void extracted(int n,String url, String port) {
        GetUrl getUrl = new GetUrl(url);
        List<Title> all = getUrl.getAll();
        List<Title> use = all.subList(n, all.size());
        use.stream().forEach(title -> {
            DownLoad downLoad = new DownLoad(port, title);
            downLoad.writeArticle();
        });
    }
}
