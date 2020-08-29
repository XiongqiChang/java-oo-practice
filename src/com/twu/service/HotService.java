package com.twu.service;

import com.twu.model.HotSearch;

import java.util.*;

/**
 * @author r: xqc
 * @Date: 2020/8/28 - 08 - 28 - 14:15
 * @Description: com.twu.service
 * @version: 1.0
 */
public class HotService {

    private  List<HotSearch> hotList = new ArrayList<>();

  {
      hotList.add(new HotSearch(11,"比特币今天的市场",4));
      hotList.add(new HotSearch(22,"乘风破浪的姐姐",9));
      hotList.add(new HotSearch(31,"火影忍者",300));
      hotList.add(new HotSearch(41,"疫情情况",40));
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

        List<HotSearch> newHotSearch = new ArrayList<>();

        Iterator<HotSearch> iterator = hotList.iterator();
        while (iterator.hasNext()){
            HotSearch next = iterator.next();
           if (next.isBuy()){
               newHotSearch.add(next);
           }
        }
        //现在newHotSearch里面都是已经购买过热搜的热搜列表
        Collections.sort(newHotSearch,Comparator.comparingInt(HotSearch::getPrice));
        Collections.reverse(newHotSearch);

        if (newHotSearch.size() >=2){
            int price = newHotSearch.get(0).getPrice();
            /*while (iterator1.hasNext()){
                HotSearch next = iterator1.next();
                if (next.getPrice() < price){
                    iterator1.remove();
                }
            }*/

            newHotSearch.removeIf(next -> next.getPrice() < price);
        }

        /**
         * 将之前的热搜合并到一起
         */
        for (HotSearch hotSearch : hotList){
            if (!hotSearch.isBuy()){
                newHotSearch.add(hotSearch);
            }
        }
        return  newHotSearch;
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
