package com.twu.view;

import com.twu.model.HotSearch;
import com.twu.model.User;
import com.twu.service.HotService;
import com.twu.utils.ScannerUtil;

import java.util.*;


/**
 * @author r: xqc
 * @Date: 2020/8/28 - 08 - 28 - 10:42
 * @Description: com.twu.view
 * @version: 1.0
 */
public class HotView {

    private  static  Integer  VOTE_COUNT = 10;
    private  static  Integer i = 0;
    private  static  Map<String, User> map = new HashMap<>();
    private  static  String userName ;
    private HotService hotService = new HotService();

    public void mainView() {

        while (true) {
            System.out.println("欢迎来到热搜系统，您可以选择：1.普通用户\t 2.管理员\t 3.退出系统\t");
            char c = ScannerUtil.readMenuScanner(3);
            selectFirst(c);
        }

    }

    /**
     * 第一次选择
     * 根据不同的用户角色进入到不同的热搜
     * @param choose
     */
    private void selectFirst(char choose) {
        switch (choose) {
            case '1':
                //展示热搜
                System.out.println("请登录账号");
                userName= ScannerUtil.readString(10);
                User user;
                if (!map.containsKey(userName)){
                    user = new User(userName,VOTE_COUNT);
                    map.put(userName,user);
                }else {
                    user = map.get(userName);
                }
                while (true) {
                    System.out.println("=======欢迎" + user.getUserName() + "进入======");
                    System.out.println("您可以选择：\n 1.查看热搜排行榜\n 2.给热搜事件投票\n 3.购买热搜\n 4.添加热搜\n 5.退出\n");
                    char c = ScannerUtil.readMenuScanner(5);
                    selectSecond(c);
                    if (c == '5') {
                        break;
                    }
                }
                break;
            case '2':
                User admin = new User("admin","admin");
                System.out.println("请登录账号(管理人员账号=admin,密码=admin)");
                String userName = ScannerUtil.readString(10);
                if (userName.equals(admin.getUserName())){
                    System.out.println("请输入密码");
                    String password = ScannerUtil.readString(10);
                    if (password.equals(admin.getPassword())){
                        while (true) {
                            System.out.println("======欢迎" + userName + "进入到热搜系统======");
                            System.out.println("您可以选择：\n 1.查看热搜排行榜\n 2.添加热搜\n 3.设置超级热搜\n 4.退出\n ");
                            char c = ScannerUtil.readMenuScanner(4);
                            selectSecondAdmin(c);
                            if (c == '4') {
                                break;
                            }
                        }
                    }else{
                        System.out.println("密码错误，请重新登录");
                    }
                }else {
                    System.out.println("用户名错误,请重新登录");
                }
                break;
            default:
                System.exit(0);//退出系统
                break;
        }
    }
    /**
     * 二级选择，为普通用户之后可以进行的操作
     */

    private void selectSecond(char c) {

        switch (c) {
            case '1':
                System.out.println("展示的是所有的热搜：");
                printHotSearch(hotService.hotSearchList());
                break;
            case '2':
                System.out.println("你可以给热搜投票");
                updateHotSearch2(userName);
                break;
            case '3':
                System.out.println("您可以购买热搜");
                buyHotSearch();
                break;
            case '4':
                System.out.println("添加热搜");
                hotService.addHotSearch(addHotSearch());
                System.out.println("请选择：");
                break;
        }
    }
    /**
     * 判断是否买了热搜
     */
        private List<HotSearch>  hotIsBuyed(List<HotSearch> list,Integer rank){

         List<HotSearch> isBuyedList = new ArrayList<>();

        Iterator<HotSearch> iterator = list.iterator();
        while (iterator.hasNext()){
            HotSearch next = iterator.next();
            if (next.isBuy() && next.getRank().equals(rank)){
                isBuyedList.add(next);
            }
        }
        return  isBuyedList;
    }

    /**
     * 购买热搜
     */
    private void buyHotSearch(){
        System.out.println("您想为哪一个购买热搜呢,请输入热搜序号:(例如1）");
        List<HotSearch> list =  hotService.hotSearchList();
        HotSearch hotSearch = readIndex(list);
        System.out.println("您想花多少钱呢：");
        int price = ScannerUtil.readInt();
        System.out.println("您想买到第几位呢？");
        int rank = ScannerUtil.readInt();
        List<HotSearch> list1 = hotIsBuyed(list,rank);
        if (list1.size() > 0){
            System.out.println("当前有人买了热搜,");
            //那就要判断现在的钱够不够
            for (HotSearch hotSearchBuyed : list1){
                if (price > hotSearchBuyed.getPrice()){
                    hotSearch.setPrice(price);
                    hotSearch.setBuy(true);
                    hotSearch.setRank(rank);
                    String desc = hotSearchBuyed.getHotName();
                    hotService.deletHotSearch(desc);
                    System.out.println("购买成功");
                }else {
                    System.out.println("money不足，购买失败");
                    return;
                }
            }
        }else{
            System.out.println("当前没有人买热搜");
            hotSearch.setPrice(price);
            hotSearch.setBuy(true);
            hotSearch.setRank(rank);
            System.out.println("购买成功");
        }

      }

       /**
     * 二级选择，展示管理人员的登录
     * @param a 用户在控制台中的输入
     */
    private void selectSecondAdmin(char a ) {
        switch (a) {
            case '1':
                System.out.println("展示的是所有的热搜：");
                printHotSearch(hotService.hotSearchList());
                break;
            case '2':
                System.out.println("添加热搜");
                hotService.addHotSearch(addHotSearch());
                break;
            case '3':
                System.out.println("您想设置哪个为超级热搜,请输入编号: ");
                setSuperHotSearch();
                System.out.println("设置完成");
                break;
        }
    }

    /**
     * 设置为超级热搜
     */
    private void setSuperHotSearch(){
        //获取到下标的值
        HotSearch hotSearch = readIndex(hotService.hotSearchList());
        if (!hotSearch.isDoubleAdd()){
            hotSearch.setDoubleAdd(true);
        }else {
            System.out.println("这个已经是超级热搜了");
        }

    }

    /**
     * 多用户互相不干扰投票
     * @param userName
     */
    private void updateHotSearch2(String  userName){
        while (true){
            System.out.println("那你要给哪一个投票呢,请输入热搜排行的序号：");
            HotSearch hotSearch = readIndex(hotService.hotSearchList());
            System.out.println("现在这个热搜有" + hotSearch.getHotScore() + "票");
            //通过userName获取到user
            User user =  map.get(userName);
            int count = user.getVoteCount();
            if (count > 0 ){
                System.out.println("您给这个投多少呢,您现在可以投" + count + "票");
                i = ScannerUtil.readInt();
                if (i < 0 || i > count){
                    System.out.println("票数不对，请重新投票");
                    //continue;
                }else{
                    if (hotSearch.isDoubleAdd()){

                        hotSearch.setHotScore(hotSearch.getHotScore() + i*2);
                    }else{
                        hotSearch.setHotScore(hotSearch.getHotScore() + i);
                    }
                    count = count - i;
                    user.setVoteCount(count);
                    System.out.println("投票成功");
                    return;
                }
            }else{
                System.out.println("剩余的票数不足");
                break;
            }
        }
        System.out.println("投票结束");
    }

    /**
     * 展示出所有的热搜
     * @param hotSearches 热搜列表
     */
    private void printHotSearch(List<HotSearch> hotSearches) {
        System.out.println("====热搜列表=====");
        if (hotSearches.size() == 0) {
            System.out.println("暂无热搜");
            return;
        }
        int i = 1;
        for (HotSearch hotSearch : hotSearches) {
            System.out.println(i + "." + hotSearch.getHotName() + "\t" + hotSearch.getHotScore());
            i++;
        }
    }
    /**
     * 添加热搜
     *
     * @return 添加新的热搜
     */
    private HotSearch addHotSearch() {
        System.out.println("热搜的描述");
        //设置热搜的描述，热搜的值不能20位
        String hotName = ScannerUtil.readString(20);
        //添加热搜，默认的是热搜的分数为0的
        int i = 0;
        HotSearch hotSearch = new HotSearch( i++,hotName, 0);
        return hotSearch;
    }

    /**
     * 根据下标返回值
     *
     * @return 返回热搜值
     */
    private HotSearch readIndex(List<HotSearch> list) {

        HotSearch hotSearch = null;
        while (true) {
            //从控制台中输入一个排序的下标
            int index = ScannerUtil.readInt();
            //--index因为在热搜排序的时候index为1初始值
            hotSearch = hotService.getHotSearchByIndex(--index, list);
            if (hotSearch == null) {
                System.out.println("输入的排序下标有误");
                continue;
            }
            break;
        }
        return hotSearch;
    }
}
