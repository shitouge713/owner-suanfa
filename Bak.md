1. 假设有一个函数
   bool testAndset(int &value, int target, int before)
   (不需要自己实现)
   可以线程安全的比较value值是否为before，如果是的话就设置为target,
   同时返回true，否则不设置值并且返回false
   请基于此函数实现一个长度不超过100的线程安全的栈
   (包含pop和push，不能使用synchonized/lock/mutex/atomic/semaphore等关键字/类/系统API，可以使用volatile)
2. 在一个抽奖活动种，用户抽奖必中，一共有6种奖品，每种奖品得数量和中奖概率分别为
   [5,0.1%],[20,5.1%],[30,8.4%],[40,15.4%],[100,21.5%],[200,49.5%]
   如果某一次抽奖抽中一类库存耗光的奖品后，则需要去除当前奖品后重新用剩余奖品得权重再算奖。
   请写一段代码来模拟这个抽奖，要求实际中奖概率可以基本符合设置要求
3. 数据库中有一张表category，存储了商品分类数据，字段包含:id,parent id，name，其中parent id表示父级分类得id
   parent id都会小于id，读取数据库后得到一个category[N]数组(可以用category[0]["id"]这样来获取第1行得分类id数据)
   请设计一个类Category用来表达一个分类，并且通过category[N]构建一个Category树,
   来表达整个商品分类得树型结构关系，所有分类得根节点可以自行构造一个id=0,parent id=0得category来表达。
4. 有一个数组ROOT[n],数组的元素类型为 class Node {
   public int index;//用来表示自己位于ROOT[n]中的位置序号
   public Node next;//用来表示自己指向的下一级Node
   next可能指向自身，也可能指向一个新的孤立的Node(并非ROOT数组中的根节点Node)，也可能指向其他ROOT里的Node.
   这样每个ROOT下面都可能形成一个单向的Node链表(可能会形成死循环链表)
   请写代码寻找不被其他Node链指向的Node链表(可以用 ROOT[n]= nu11 设置为删除)
5. 给你一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 target。 请你返回满足 0 <= i < j < n 且 nums[i] + nums[j] < target 的下标对 (i, j)(i,j) 的数目。
   https://juejin.cn/post/7332390317559349298?utm_source=gold_browser_extension
6. 给定一个 nn 叉树的根节点 rootroot ，返回 其节点值的后序遍历。 nn 叉树在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
   https://juejin.cn/post/7327188195770351635100Mb 的内存, 需要对 1 Gb 的数据进行排序 【ExternalSort】
7. 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
   你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
