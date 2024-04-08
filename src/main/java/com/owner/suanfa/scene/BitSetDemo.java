package com.owner.suanfa.scene;

import java.util.BitSet;

public class BitSetDemo {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet(10); // 创建一个大小为10的BitSet
        // 设置特定位置的位为1
        bitSet.set(2); // 将索引为2的位设置为1
        bitSet.set(5); // 将索引为5的位设置为1
        bitSet.set(6);
        bitSet.set(7);
        bitSet.set(8);
        bitSet.set(9);

        // 输出设置后的BitSet
        System.out.println("BitSet after setting values: " + bitSet);

        // 检查特定位置的位是否为1
        boolean isSet = bitSet.get(2); // 检查索引为2的位是否为1
        System.out.println("Bit at index 2 is set to 1: " + isSet);

        // 清除特定位置的位
        bitSet.clear(5); // 将索引为5的位清除为0
        System.out.println("BitSet after clearing value at index 5: " + bitSet);
    }
}
