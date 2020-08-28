package com.twu.model;

/**
 * @Auther: xqc
 * @Date: 2020/8/28 - 08 - 28 - 10:28
 * @Description: com.twu.model
 * @version: 1.0
 */
public class HotSearch {
    /**
     * 热搜排行序号
     */
    private  Integer hotId;

    /**
     * 热搜名称
     */
    private String hotName;

    /**
     * 热搜热度分数
      */
    private Integer hotScore;


    /**
     * 暂时存储的用来记录排序标志
     */
    private  Integer index;


    /**
     * 是否为双倍加分的
     */
    private boolean doubleAdd;


    public Integer getHotId() {
        return hotId;
    }

    public void setHotId(Integer hotId) {
        this.hotId = hotId;
    }

    public String getHotName() {
        return hotName;
    }

    public void setHotName(String hotName) {
        this.hotName = hotName;
    }

    public Integer getHotScore() {
        return hotScore;
    }

    public void setHotScore(Integer hotScore) {
        this.hotScore = hotScore;
    }

    public HotSearch(Integer hotId, String hotName, Integer hotScore) {
        this.hotId = hotId;
        this.hotName = hotName;
        this.hotScore = hotScore;
    }

    public HotSearch(){}
    @Override
    public String toString() {
        return "HotSearch{" +
                "hotId=" + hotId +
                ", hotName='" + hotName + '\'' +
                ", hotScore=" + hotScore +
                '}';
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
