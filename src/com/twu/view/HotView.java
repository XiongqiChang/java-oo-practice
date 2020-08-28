package com.twu.view;

import com.twu.model.HotSearch;
import com.twu.model.User;
import com.twu.service.HotService;
import com.twu.utils.ScannerUtil;

import java.util.List;
import java.util.Random;


/**
 * @Auther: xqc
 * @Date: 2020/8/28 - 08 - 28 - 10:42
 * @Description: com.twu.view
 * @version: 1.0
 */
public class HotView {

    private static Integer VOTE_COUNT = 10;
    private  static  Integer i = 0;

    private HotService hotService = new HotService();

    public void mainView() {
        /*  Scanner scanner = new Scanner(System.in);*/
        while (true) {
            System.out.println("欢迎来到热搜系统，您可以选择：1.普通用户\t 2.管理员\t 3.退出系统\t");
            char c = ScannerUtil.readMenuScanner(3);
            selectFirst(c);
        }
    }

    /**
     * 第一次选择
     * 根据不同的用户角色进入到不同的热搜展示
     *
     * @param choose
     */
    public void selectFirst(char choose) {
        switch (choose) {
            case '1':
                //展示热搜
                System.out.println("请登录账号");
                String read = ScannerUtil.readString(10);
                User user = new User(read);

                while (true) {
                    System.out.println("欢迎" + user.getUserName() + "进入");
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
                System.out.println("请登录账号");
                String userName = ScannerUtil.readString(10);
                if (userName.equals(admin.getUserName())){
                    System.out.println("请输入密码");
                    String password = ScannerUtil.readString(10);
                    if (password.equals(admin.getPassword())){
                        while (true) {
                            System.out.println("欢迎" + userName + "进入到热搜系统");
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

    public void selectSecond(char c) {

        switch (c) {
            case '1':
                System.out.println("展示的是所有的热搜：");
                printHotSearch(hotService.hotSearchList());
                break;
            case '2':
                System.out.println("你可以给热搜投票");
                updateHotSearch();
                break;
            case '3':
                System.out.println("您可以购买热搜");
                break;
            case '4':
                System.out.println("添加热搜");
                hotService.addHotSearch(addHotSearch());
                System.out.println("请选择：");
                break;
        }
    }

    /**
     * 展示管理人的登录
     * @param a
     */
    public void selectSecondAdmin(char a ) {
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
                System.out.println("设置为超级热搜");
                break;
        }
    }



    /**
     *热搜投票
     */
    private void updateHotSearch(){
       while (true){
           System.out.println("那你要给哪一个投票呢");
           HotSearch hotSearch = readIndex(hotService.hotSearchList());
           System.out.println("现在这个热搜有" + hotSearch.getHotScore() + "票");
            if (VOTE_COUNT > 0 ){
                System.out.println("你可以给这个投多少呢,您现在可以投" + VOTE_COUNT + "票");
                i = ScannerUtil.readInt();
                if (i <= 0 || i > VOTE_COUNT){
                    System.out.println("票数不对，请重新投票");
                    continue;
                }else{
                    hotSearch.setHotScore(hotSearch.getHotScore() + i);
                    VOTE_COUNT = VOTE_COUNT - i;
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
     * @param hotSearches
     */
    private void printHotSearch(List<HotSearch> hotSearches) {
        System.out.println("====热搜列表=====");
        if (hotSearches.size() == 0) {
            System.out.println("暂无热搜");
            return;
        }
      /*  //进行热度的排序
        hotService.hotSearchListSorted(hotSearches);*/
        int i = 1;
        for (HotSearch hotSearch : hotSearches) {
            System.out.println(i + "/" + hotSearch.getHotName() + "\t" + hotSearch.getHotScore());
            i++;
        }
    }

    /**
     * 添加热搜
     *
     * @return
     */
    private HotSearch addHotSearch() {
        System.out.println("热搜的描述");
        String hotName = ScannerUtil.readString(20);

        Integer id = new Random().nextInt(100);
        HotSearch hotSearch = new HotSearch(id, hotName, 0);
        return hotSearch;
    }

    /**
     * 根据下标返回值
     *
     * @return
     */
    private HotSearch readIndex(List<HotSearch> list) {

        HotSearch hotSearch = null;
        while (true) {
            int index = ScannerUtil.readInt();
            hotSearch = hotService.getHotSearchByIndex(--index, list);

            if (hotSearch == null) {
                System.out.println("输入的下标有误");
                continue;
            }
            break;
        }
        return hotSearch;
    }


}
