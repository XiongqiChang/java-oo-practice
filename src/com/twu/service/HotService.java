package com.twu.service;

import com.twu.model.HotSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther: xqc
 * @Date: 2020/8/28 - 08 - 28 - 14:15
 * @Description: com.twu.service
 * @version: 1.0
 */
public class HotService {

    private  List<HotSearch> hotList = new ArrayList<>();



  {
      hotList.add(new HotSearch(11,"比特币你好",4));
      hotList.add(new HotSearch(22,"今天是干嘛呢",9));
      hotList.add(new HotSearch(31,"成分评论",300));
      hotList.add(new HotSearch(41,"你好",40));


  }

    /**
     * 添加热搜
     */
    public boolean addHotSearch(HotSearch hotSearch){
        hotList.add(hotSearch);
        return  true;
    }

    /**
     * 展示所有的已经排序的热搜
     */
    public List<HotSearch> hotSearchList(){
        Collections.sort(hotList, Comparator.comparingInt(HotSearch::getHotScore));
        Collections.reverse(hotList);
        return  hotList;
    }

    /**
     * 根据热度进行排序操作
     */
    public void hotSearchListSorted(List<HotSearch> hotSearches){

         Collections.sort(hotSearches, Comparator.comparingInt(HotSearch::getHotScore));
         Collections.reverse(hotSearches);
    }

    /**
     * 给热搜投票
     */
    public void updateHotScore(int index, List<HotSearch> list, Integer hotScore){

        HotSearch hotSearch = getHotSearchByIndex(index,list);
        hotSearch.setHotScore(hotSearch.getHotScore()+ hotScore);
    }

    /**
     * 根据下标找到热搜的值
     */

    public HotSearch getHotSearchByIndex(int index,List<HotSearch> list){
        if (index < 0 || index >= list.size() )
            return  null;
        return list.get(index);

    }

}
