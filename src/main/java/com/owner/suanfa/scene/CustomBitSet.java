package com.owner.suanfa.scene;

import java.util.BitSet;

/**
 * bitset扩展： 自定义bitset
 * 2位来表示一位   00 0次 默认| 01 1次 | 10 2次 | 11 2次以上
 */
public class CustomBitSet {
    private BitSet bitSet;

    public CustomBitSet(int size) {
        this.bitSet = new BitSet(size * 2); // 每个整数使用两位来表示
    }

    public void set(int num) {
        // 计算整数对应的起始位索引
        int index = num * 2;
        // 第一次出现，设置第一位为 true, 即01
        if (!bitSet.get(index) && !bitSet.get(index + 1)) {
            bitSet.set(index);
        } else if (bitSet.get(index) && !bitSet.get(index + 1)) {
            // 如果出现了一次，再次出现则设置第二位为 true，重置第一位为false, 即10
            bitSet.set(index + 1);
            bitSet.clear(index);
        } else if (!bitSet.get(index) && bitSet.get(index + 1)) {
            // 如果出现过2次，再次出现，则重置第一位为true, 即11
            bitSet.set(index);
            return;
        } else if (bitSet.get(index) && bitSet.get(index + 1)) {
            //以后再出现无需重置
            return;
        }
    }

    //是否少于两次
    public boolean isLessThanTwo(int num) {
        int index = num * 2;
        return !bitSet.get(index + 1);
    }

    //是否等于两次
    public boolean isEqualsTwo(int num) {
        int index = num * 2;
        return !bitSet.get(index) && bitSet.get(index + 1);
    }

    //是否大于两次
    public boolean isGreaterThanTwo(int num) {
        int index = num * 2;
        return bitSet.get(index) && bitSet.get(index + 1);
    }

    public static void main(String[] args) {
        CustomBitSet customBitSet = new CustomBitSet(10); // 假设有10个整数
        // 模拟增加整数出现次数
        customBitSet.set(1);
        customBitSet.set(1);
        customBitSet.set(1);
        customBitSet.set(2);
        customBitSet.set(2);
        customBitSet.set(3);
        customBitSet.set(3);
        // 判断整数出现次数是否不超过2次
        System.out.println(customBitSet.isLessThanTwo(1));    //false
        System.out.println(customBitSet.isEqualsTwo(1));      //false
        System.out.println(customBitSet.isGreaterThanTwo(1)); //true

        System.out.println(customBitSet.isLessThanTwo(2));    //false
        System.out.println(customBitSet.isEqualsTwo(2));      //true
        System.out.println(customBitSet.isGreaterThanTwo(2)); //false

        System.out.println(customBitSet.isLessThanTwo(3));    //false
        System.out.println(customBitSet.isEqualsTwo(3));      //true
        System.out.println(customBitSet.isGreaterThanTwo(3)); //false

        System.out.println(customBitSet.isLessThanTwo(4));    //true
        System.out.println(customBitSet.isEqualsTwo(4));      //false
        System.out.println(customBitSet.isGreaterThanTwo(4)); //false

    }
}
