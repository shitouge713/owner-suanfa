package com.owner.suanfa.node;

/**
 * 链表的反转
 */
public class ListNodeReversal {
    int val;
    ListNodeReversal next;
    ListNodeReversal(int x, ListNodeReversal node) {
        val = x;
        next = node;
    }

    public static void main(String[] args) {
        ListNodeReversal node5 = new ListNodeReversal(5,null);
        ListNodeReversal node4 = new ListNodeReversal(4,node5);
        ListNodeReversal node3 = new ListNodeReversal(3,node4);
        ListNodeReversal node2 = new ListNodeReversal(2,node3);
        ListNodeReversal node1 = new ListNodeReversal(1,node2);
        ListNodeReversal rever = reverseList01(node1);
        System.out.println(rever.toString());
    }

    /**
     * 非递归实现
     * @param head
     * @return
     */
    public static ListNodeReversal reverseList01(ListNodeReversal head) {
        ListNodeReversal a = null;
        while (head != null) {
            ListNodeReversal temp = head.next;//借助一个临时链表节点
            head.next = a;//将摘出来的节点放到新的链表的上面
            a = head;//将新链表置为刚组装好链表
            head = temp;//最后将head置为新的链表，开启下次循环
        }
        return a;
    }

    /**
     * 递归实现
     * @param head
     * @return
     */
    public static ListNodeReversal reverseList02(ListNodeReversal head) {
        if(head==null||head.next ==null){
            return head;
        }
        ListNodeReversal prev = reverseList02(head.next);
        head.next.next = head;
        head.next = null;
        return prev;
    }


}
