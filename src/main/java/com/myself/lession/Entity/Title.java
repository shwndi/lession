package com.myself.lession.Entity;

import lombok.Data;

import java.util.Comparator;

/**
 * @author czy
 * @date 2021/9/10
 */
@Data
public class Title implements Comparable<Title> {
    String name;
    String url;

    public Title(String name, String url) {
        this.name = name;
        this.url = "https://www.92qb.com/xiaoshuo/13/13487/" + url;
    }

    @Override
    public int compareTo(Title o) {
        if (this.name.hashCode()>o.name.hashCode()){
            return 1;
        }
        if (this.name.hashCode()<o.name.hashCode()){
            return 1;
        }

        return 0;
    }
}
