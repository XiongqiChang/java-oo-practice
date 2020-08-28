package com.twu.utils;

import java.util.Random;

/**
 * @Auther: xqc
 * @Date: 2020/8/28 - 08 - 28 - 14:40
 * @Description: com.twu.utils
 * @version: 1.0
 */
public class RandomUtil {

    public static int getRandomByInt() {
        long time = System.currentTimeMillis();
        String tempTime = (time+"").substring(6);
        Random r = new Random();
        int i = r.nextInt(1000);
        tempTime += i;
        return Integer.parseInt(tempTime);
    }

}
