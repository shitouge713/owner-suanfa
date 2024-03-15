package com.owner.suanfa.node;

/**
 * 判断链表是否有环
 */
public class ListNode02 {
    int val;
    ListNode02 next;

    ListNode02(int x, ListNode02 node) {
        val = x;
        next = node;
    }

    public static boolean hasLoop3(ListNode02 node) {
        return true;
    }

    /**
     * 使用快慢指针的方式，一个指针每次移动2步，一个指针每次移动一步
     * 如果链表中有环，两个指针最终会相遇；如果没有环，快指针会先到达链表尾部
     * 时间复杂度：O(n)
     *
     * @param head
     * @return
     */
    public static boolean hasLoop2(ListNode02 head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode02 slow = head;
        ListNode02 fast = head.next;
        while (slow != fast) {
            //如果快的为null或快的next为null，表示没有环
            if (fast == null || fast.next == null) {
                return false;
            }
            //慢的每次移动1位
            slow = slow.next;
            //快的每次移动2位
            fast = fast.next.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode02 node5 = new ListNode02(5, null);
        ListNode02 node4 = new ListNode02(4, node5);
        ListNode02 node3 = new ListNode02(3, node4);
        ListNode02 node2 = new ListNode02(2, node3);
        ListNode02 node1 = new ListNode02(1, node2);
        node5.next = node3;
        System.out.println(hasLoop2(node1));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListNode02{");
        sb.append("val='").append(val).append('\'');
        sb.append(", next='").append(next).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
