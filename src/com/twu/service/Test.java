package com.twu.service;

import com.twu.model.HotSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: xqc
 * @Date: 2020/8/28 - 08 - 28 - 16:08
 * @Description: com.twu.service
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        HotService hotService = new HotService();
         List<HotSearch> hotList = new ArrayList<>();
        hotList.add(new HotSearch(11,"比特币你好",4));
        hotList.add(new HotSearch(22,"今天是干嘛呢",9));
        hotList.add(new HotSearch(31,"成分评论",300));
        hotList.add(new HotSearch(41,"你好",40));

        hotService.hotSearchListSorted(hotList);

        System.out.println(hotList.get(0));

    }
}
