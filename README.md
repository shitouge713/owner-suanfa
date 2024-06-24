算法题
## **数组**
1. 一个字符串数组，求字符串的最长公共前缀/最长公共字符串【LongestCommonPrefixOfArrays】
2. 数组int[] sums中计算两个数之和=sum，求得这些组合【SameSumOfArrays】
3. 二分查找法查找数组中是否包含某个数【BinarySearch】
4. (存疑)一个数组，里面全是数字, 从左致右的特征是:从小到大，然后从大到小，数组中可能会出现连续重复的数字【FindMaxInArray】
   请写一段代码<最快>找出数组中最大的数值(最大值可能为1到多个)，输出最大数字和个数，比如数组 [1,3,3,3,4,5,6,7,8,8,8,8,8,9,10,10,11,11,12,23,33,33,10,2,2,2,2,2,2,1,1,1]
   类似：有一个数组，里面元素非重复，先升序再降序，找出里面最大的值
5. 有⼀些词，需要将词中的字⺟出现位置不同但是出现次数相同的词分为⼀组，⽐如eat，ccok,ate,cock会被划分成两组，分别是[eat,ate],[ccok,cock]【WordGrouping】


## **字符串**
1. 实现字符串的反转【StringInversion】
    方式一：一次for循环（只循环一半） + char数组
    方式二：使用异或操作交换字符值，通过3次异或操作，可以将两个字符位置互换
2. 一个整数，它加上 100 后是一个完全平方数，加上 168 又是一个完全平方数，请问该数是多少？【SquareNumbers】
3. 寻找字符串中不含有重复字符的最长字串【WithoutRepeatingChar】借助hashMap
4. 判断一个数是不是回数【whetherReturnNumber】
5. 给定一个正整数,找到由相同数字组成的下一个刚好比该数字大的数字【IntegerJustLarge】【NextGreaterNumber】
6. 寻找字符串中的最大回文【FindLargestPalindrome】【LongestPalindromeSubstring】
7. 有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？【CombinationNumbers】


## **链表**
1. 链表的反转【ListNodeReversal】
2. 判断链表是否有环【WhetherListNodeHasRing】
3. 将两个有序链表合并为一个新的有序链表并返回。示例：输入 1->2->4, 1->3->4，输出 1->1->2->3->4->4【MergeTwoSortedLists】
4. 将一个无序的链表排升序排序，比如1->7-->6-->4-->5 输出1->4->5->6->7【LinkedListMergeSort】【****】
5. 删除链表的倒数第n个节点【DeleteListNodeOfN】
6. 将一个链表向前提K位，比如1，2，3，4，5；输出后4，5，1，2，3

## **多线程**
1. 实现一个生产者，消费者通信【ProducerConsumerCommunication】
2. 三个线程同时启动，要求等待所有线程执⾏完任务后⼀起返回，如果超时五秒也直接返回

## **打印**
1. 两个线程循环打印AB、两个线程循环打印10以内的奇偶数、三个线程循环打印ABC【PrintAB】、【PrintABC】
2. 使用生产者消费者循环打印123【MainClass】

## **设计模式**
1. 单例模式【Singleton】

## **树**
1. 判断一个二叉数是否是平衡二叉树【BalanceTwoTree】

## **八大排序**
1. 冒泡排序，两次for循环方式【BubbleSort】
2. 选择排序【SelectionSort】
3. 归并排序【MergeSort】
4. 令牌桶算法

## **场景**
## 排序、值TOP、次数TOP、次数、超过2次/3次、去重、查找、交集、并集
1. 【排序】100Mb 的内存, 需要对 1 Gb 的数据进行排序 【ExternalStrSort】
2. 【次数TOP】给定一个1G大小的文件file.txt,里面每行是一个词,词的大小不超过16字节。内存限制为1M。要求返回文件中词频最高的100个词
   【次数TOP】1GB的单词文件，怎么只用1M的内存找出出现次数TOP20的词【ExternalStrSortTop20】
   【次数TOP】给一个超过100G大小的log file, log中存着IP地址, 设计算法找到出现次数最多的IP地址
   【次数】给定100亿个整数，设计算法找到只出现一次的整数【BitSet】
   【次数】位图应用变形：1个文件有100亿个int，1G内存，设计算法找到出现次数不超过2次/3次的所有整数【BitSetSort2Times】
3. 【值TOP】1GB的数字文件，怎么只用1M的内存找出TOP20的数字【ExternalNumberSortTop20】
4. 【去重】10亿个int整数，只有其中N个数重复出现过，读取到内存中并将重复的整数删除【BitSet】
5. 【去重】已知某个文件内包含若干个电话号码，要求统计不同的号码的个数
6. 【去重】2.5亿个整数中找出不重复的整数的个数，内存空间不足以容纳这2.5亿个整数【BitSet】
7. 【去重】40亿个QQ号，限制1G内存，如何去重？
8. 【查找】给40亿个不重复的无符号整数，没排过序。给一个无符号整数，如何快速判断这个数是否在这40亿个数中【BitSet】
9. 【查找】如何在十亿级别用户中检查用户名是否存在？
10. 【交集】给两个文件，分别有100亿个整数，我们只有1G内存，如何找到两个文件交集【BitSet】
## 其他
1. 合并区间,以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
   请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。【MergingInterval】
    示例 1：
    输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
    输出：[[1,6],[8,10],[15,18]]
    解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
    示例 2：
    输入：intervals = [[1,4],[4,5]]
    输出：[[1,5]]
    解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
2. 给你两个单词 `word1` 和 `word2`， 请返回将 `word1` 转换成 `word2` 所使用的最少操作数。
   你可以对一个单词进行如下三种操作：
   - 插入一个字符
   - 删除一个字符
   - 替换一个字符





