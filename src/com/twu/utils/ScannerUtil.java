package com.twu.utils;

import java.util.Scanner;

/**
 * @Auther: xqc
 * @Date: 2020/8/28 - 08 - 28 - 11:07
 * @Description: com.twu.utils
 * @version: 1.0
 */
public class ScannerUtil {

    private static  Scanner scanner = new Scanner(System.in);


    /**
     * 获取到的下标然后展示成一个int型的
     * @return int
     */

    public static  int  readInt(){
        int i = 0;
        while (true){
            String string = readScanner(4,false);
            i = Integer.parseInt(string);
            break;
        }
        return  i;
    }


    /**
     * 获取指定的字符长度
     * @param limit
     * @return
     */
    public  static  String  readString(int limit) {
        String s = readScanner(limit, false);
        return  s;
    }


    /**
     * 在控制台中展示的数据的个数
     * @param  size ： 展示的控制台的个数
     * @return
     */
    public static  char readMenuScanner(int size){
        char c = ' ';
        while (true){
            String scannerString = readScanner(1,false);
            c = scannerString.charAt(0);
            boolean result = true;
            for (int i = 1; i <= size; i++){
                result = result && (c != (i +'0'));
                //result为假的话，就是匹配到一个了，那么就是为真的
                if (!result){
                    break;
                }
            }
            //result为真的话，那么就是说明输入的数据有误
           if (result){
               System.out.println("您输入的数据有误，请重新输入：");
           }else{
               break;
           }
        }
        return  c;
    }

    /**
     * 对输入的格式进行判断
     * @param limit 不能超过的长度
     * @param enter 保证用户在输入空格之后还是能够继续输入的
     * @return
     */
    private   static  String readScanner(int limit, boolean enter) {
        String input = "";
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            input = input.trim();
            if (input.length() == 0) {
                if (enter){
                    return input;
                }else {
                    continue;
                }
            }
            if (input.length() > limit) {
                System.out.println("您输入的长度有误，不能大于" + limit + "请重新输入：");
                continue;
            }
            break;
        }
        return input;
    }
}
