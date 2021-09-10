package com.myself.lession.Entity;

import lombok.Data;

/**
 * @author czy
 * @date 2021/9/10
 */
@Data
public class Title {
    String name;
    String url;

    public Title(String name, String url) {
        this.name = name;
        this.url = "http://www.q1xs.com" + url;
    }
}
