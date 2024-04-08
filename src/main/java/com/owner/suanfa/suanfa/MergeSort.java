package com.owner.suanfa.suanfa;

/**
 * 归并排序
 * 对一个整型数组进行排序并输出结果。
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7, 3, 22, 11, 55, 66, 88, 99, 212, 9098, 213345, 455, 768, 234};
        mergeSort(array);

        System.out.println("Sorted array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }

    public static void mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int[] helper = new int[array.length];
        mergeSort(array, helper, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int[] helper, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(array, helper, low, mid);
            mergeSort(array, helper, mid + 1, high);
            merge(array, helper, low, mid, high);
        }
    }

    /**
     * 将左右两部分已排序的数组合并到原数组中
     * @param array
     * @param helper
     * @param low
     * @param mid
     * @param high
     */
    private static void merge(int[] array, int[] helper, int low, int mid, int high) {

        //先将需要合并的部分复制到辅助数组helper中
        for (int i = low; i <= high; i++) {
            helper[i] = array[i];
        }
        //通过三个指针i、j、k分别指向左部分起始位置、右部分起始位置和原数组起始位置。
        int i = low;
        int j = mid + 1;
        int k = low;
        //比较helper数组中i和j位置的元素大小，将较小的值放入原数组中，并移动相应指针
        while (i <= mid && j <= high) {
            if (helper[i] <= helper[j]) {
                array[k] = helper[i];
                i++;
            } else {
                array[k] = helper[j];
                j++;
            }
            k++;
        }
        //将剩余的元素依次放入原数组中，完成归并排序
        while (i <= mid) {
            array[k] = helper[i];
            k++;
            i++;
        }
    }
}
