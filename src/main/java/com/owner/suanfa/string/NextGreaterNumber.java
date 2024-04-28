package com.owner.suanfa.string;

public class NextGreaterNumber {
    public static void main(String[] args) {
        //12345687    ->  12345768    7移动到6前，7后面的按最小排序
        //12345697    ->  12345679    7移动到9前，7后面的按最小排序
        //1234569873  ->  1234573689  7移动到6前，7后面的6983按最小排序3689
        //123456973  ->  123457369  7移动到6前，7后面的693按最小排序369
        /**
         * 通过以上示例，总结出规则
         * 1. 从右向左，找到第一个出现左<右的元素A
         * 2. 从1步骤中获取的元素A，右侧找到比A大的最小值B
         * 3. A和B置换位置
         * 4. B之后的字符串按增序排序
         */
        int num = 123456973;
        int result = nextGreaterNumberSameDigits(num);
        System.out.println("下一个元素: " + result);
    }

    public static int nextGreaterNumberSameDigits(int num) {
        char[] digits = String.valueOf(num).toCharArray();

        //1. 从右向左，找到第一个出现左<右的元素A
        int i = digits.length - 2;
        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        // 2. 从1步骤中获取的元素A，右侧找到比A大的最小值B
        int j = digits.length - 1;
        while (j > i && digits[j] <= digits[i]) {
            j--;
        }
        //3. A和B置换位置
        swap(digits, i, j);
        //4. B之后的字符串按增序排序
        reverse(digits, i + 1, digits.length - 1);

        return Integer.parseInt(new String(digits));
    }

    //置换元素
    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 该增量排序很特殊，建立的前提是，start~end的元素是降序排列的
     * 满足这个前提，只需要将start~end的位置置换即可（即字符串的反转）
     * @param arr
     * @param start
     * @param end
     */
    private static void reverse(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }
}
