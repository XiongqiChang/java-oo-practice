package com.twu.service;

import com.twu.model.HotSearch;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author r: xqc
 * @Date: 2020/8/28 - 08 - 28 - 14:15
 * @Description: com.twu.service
 * @version: 1.0
 */
public class HotService {

    private  List<HotSearch> hotList = new ArrayList<>();
     {
      hotList.add(new HotSearch("比特币今天的市场",4));
      hotList.add(new HotSearch("乘风破浪的姐姐",9));
      hotList.add(new HotSearch("火影忍者",300));
      hotList.add(new HotSearch("疫情情况",40));
      hotList.add(new HotSearch(12,"猪肉",0));
      hotList.add(new HotSearch(213,"天气",0));
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


        List<HotSearch> collect = hotList.stream().sorted(Comparator.comparing(HotSearch::getHotScore,Comparator.reverseOrder()).thenComparing(HotSearch::getHotId)).collect(Collectors.toList());
        List<HotSearch> newHotSearch = new ArrayList<>();

        Iterator<HotSearch> iterator = collect.iterator();
        while (iterator.hasNext()){
            HotSearch next = iterator.next();
            if (next.isBuy()){
                newHotSearch.add(next);
            }
        }
        //现在newHotSearch里面都是已经购买过热搜的热搜列表
        Collections.sort(newHotSearch,Comparator.comparingInt(HotSearch::getRank));

        for (HotSearch hotSearch : collect){
            if (!hotSearch.isBuy()){
                newHotSearch.add(hotSearch);
            }
        }

        for (int i = 0; i < newHotSearch.size(); i++){
            HotSearch hotSearch = newHotSearch.get(i);
            Integer rank = hotSearch.getRank();
            if (rank != null && rank != i+1){
                //获取到此时排位名rank-1的元素
                Collections.swap(newHotSearch,i,rank - 1);
                }
            }
        return  newHotSearch;
    }
    /**
     * 根据下标找到热搜的值
     */

    public HotSearch getHotSearchByIndex(int index,List<HotSearch> list){
        if (index < 0 || index >= list.size() )
            return  null;
        return list.get(index);
    }

    public List<HotSearch> deletHotSearch(String description){
        Iterator<HotSearch> iterator = hotList.iterator();
        while (iterator.hasNext()){
            HotSearch next = iterator.next();
            if (next.getHotName().equals(description)){
                iterator.remove();
            }
        }
        return  hotList;
    }


}
