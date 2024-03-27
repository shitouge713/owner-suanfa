package com.owner.suanfa.string;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 给定一个正整数,找到由相同数字组成的下一个刚好比该数字大的数字
 * 思路
 * 从右向左移动，找到第一个出现的比右边某个字符数字小的数字
 * 从右侧数字中选择一个刚好比该数字大的数，拿出来
 * 将拿出来的数字放置到原数字位置
 * 拿出来的数字和右侧剩余的数字放一起，由小到大排序
 * 将前面的和后面的拼接到一起即可
 */
public class IntegerJustLarge {
    public static int getNextBigger(int num) {
        int numLength = Integer.toString(num).length();
        //如果是负数或者数字位数为1，那么直接返回-1，代表不存在需要找的数字
        if (num <= 0 || numLength <= 1) {
            return -1;
        }
        int[] nums = new int[numLength];
        for (int i = numLength - 1; i >= 0; i--) {
            int tmp = num % 10;
            nums[i] = tmp;
            num = num / 10;
        }
        //找到给定数字中，从右至左没有按从大到小顺序排列的一段数字。假如给定数字式76985432，那么没有按顺序排列的一段数字就是，6985432。
        //对找到的这段数字重新排列。先把给定数字变成两个列表，[7]与[6,9,8,5,4,3,2]，
        // 然后对后面列表中的这些数字，取比6大一位的数字，即8。最后，把除8以外的数字，按从小到大得顺序重新排列。
        List<Integer> list = new ArrayList<>();
        //transition用来保存出现转折的数字，假如给定数字式76985432，没有按顺序排列的一段数字就是，6985432，转折的数字为6
        int transition = -1;
        for (int i = numLength - 1; i >= 0; i--) {
            if (i == 0) {
                return -1;
            }
            list.add(nums[i]);
            if (nums[i] > nums[i - 1]) {
                transition = nums[i - 1];
                break;
            }
        }
        list.sort(Comparator.comparing(Integer::intValue));
        String resultStr = "";
        //将转折数字之前的数先整合起来
        for (int i = 0; i < numLength - list.size() - 1; i++) {
            resultStr += nums[i];
        }
        //找出比转折数大一位的数字，整合进resultStr
        for (int i = 0; i < list.size(); i++) {
            if (transition < list.get(i)) {
                resultStr += list.get(i);
                list.remove(i);
                break;
            }
        }
        //将转折数字加入list并进行排序，然后整合进resultStr
        list.add(transition);
        list.sort(Comparator.comparing(Integer::intValue));
        for (int i = 0; i < list.size(); i++) {
            resultStr += list.get(i);
        }
        return Integer.parseInt(resultStr);
    }

    public static int getNextBig(int num) {
        //将num转换成int数组，转换后顺序发生变化
        String numStr = String.valueOf(num);
        int length = numStr.length();
        int[] allArr = new int[length];
        for (int i = 0; i < length; i++) {
            int temp = num % 10;
            allArr[length - 1 - i] = temp;
            num = num / 10;
        }
        //比较数组中从右侧数第一个出现的比右边任何一个数字小的数字，获取其数组下标
        //同时将右侧放入到rightList中
        int position = -1;
        List<Integer> rightList = new ArrayList<>();
        for (int i = allArr.length - 1; i > 0; i--) {
            rightList.add(allArr[i]);
            if (allArr[i - 1] < allArr[i]) {
                position = i - 1;
                break;
            }
        }
        int temp = allArr[position];
        //左边的不动
        String result = "";
        for (int i = 0; i < position; i++) {
            result += allArr[i];
        }
        //找出右侧比temp刚好大的值
        int compareTemp = -1;
        int compareTempPosition = -1;
        for (int i = 0; i < rightList.size(); i++) {
            if (temp < rightList.get(i) && (compareTemp == -1 || rightList.get(i) < compareTemp)) {
                compareTemp = rightList.get(i);
                compareTempPosition = i;
            }
        }
        //将该值从list取出后放入左侧结果
        int newInt = rightList.remove(compareTempPosition);
        result += newInt;
        //将temp放入到rightList中，由小到大排序
        rightList.add(temp);
        rightList.sort(Comparator.comparing(Integer::intValue));
        //组装结果
        for (Integer intt : rightList) {
            result += intt;
        }
        return Integer.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println(getNextBig(76985432));//78234569
        //System.out.println(getNextBigger(213));
        //System.out.println(getNextBigger(321));
        //System.out.println(getNextBigger(786871));
    }
}

