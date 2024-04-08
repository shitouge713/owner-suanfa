package com.owner.suanfa.scene;

import cn.hutool.json.JSONUtil;

import java.util.BitSet;

/**
 * 合并区间
 */
public class MergingInterval {
    public static void main(String[] args) {
        int[][] intervals = new int[4][2];
        intervals[0][0] = 1;
        intervals[0][1] = 3;
        intervals[1][0] = 2;
        intervals[1][1] = 6;
        intervals[2][0] = 8;
        intervals[2][1] = 10;
        intervals[3][0] = 15;
        intervals[3][1] = 18;
        System.out.println(JSONUtil.parse(intervals));
        System.out.println(JSONUtil.parse(merge(intervals)));
    }

    public static int[][] merge(int[][] intervals) {
        BitSet bitSet = new BitSet();
        int max = 0;//记录最大值
        for (int[] interval : intervals) {
            // 比如[1,4]和[5,6]两个区间在数轴上是不连续的，但在BitSet上却是连续的。乘2是为了让它们从BitSet上看也是不连续的
            // bitSet.set() 函数 [x,y)
            //对于每个区间，将区间的起始点乘以 2 作为 BitSet 的起始位置，
            // 将区间的终点乘以 2 加 1 作为 BitSet 的结束位置，并设置为 true
            int temp = interval[1] * 2 + 1;
            bitSet.set(interval[0] * 2, temp, true);
            //更新 max 的值为当前区间的结束位置与 max 中较大的值
            max = temp >= max ? temp : max;
        }
        int index = 0, count = 0;
        while (index < max) {
            //在 BitSet 中找到下一个被设置为 true 的位置作为 start，再找到下一个为 false 的位置作为 end。
            int start = bitSet.nextSetBit(index);
            int end = bitSet.nextClearBit(start);
            //根据 start 和 end 计算出不重叠的区间，存入 intervals 数组，并更新 index
            int[] item = {start / 2, (end - 1) / 2};
            intervals[count++] = item;
            index = end;
        }
        //将合并后的不重叠区间存入 ret 数组中，并返回 ret
        int[][] ret = new int[count][2];
        for (int i = 0; i < count; i++) {
            ret[i] = intervals[i];
        }
        return ret;
    }
}
