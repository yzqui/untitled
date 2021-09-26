package linkedTest;

public class linedTest {

    /**
     * 获取链表中点，奇数获取中点，偶数获取上中点
     *
     * @return
     */
    public static Node midOrUpMidNode() {

    }

    /**
     * 给定一个单链表的头结点 head，请判断该链表是否为回文结构
     * 1）辅助栈
     * 2）改原链表的方法就需要注意边界了
     */
    public static boolean isPalindrome1(Node node) {

    }

    /**
     * O(1) 空间复杂度
     *
     * @param node
     * @return
     */
    public static boolean isPalindrome3(Node node) {

    }

    /**
     * 将单链表按某值划分成左边小，中间相等，右边大的形式
     * 1）把链表放入数组中，在数组上走 partition
     * 2）分成小、中、大三部分，再把各个部分之间串起来
     */
    public static Node listPartition2(Node head, int x) {
        Node sh = null;
        Node st = null;
        Node eh = null;
        Node et = null;
        Node bh = null;
        Node bt = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val < x) {
                if (sh == null) {
                    sh = head;
                    st = head;
                } else {
                    st.next = head;
                    st = head;
                }
            } else if (head.val == x) {
                if (eh == null) {
                    eh = head;
                    et = head;
                } else {
                    et.next = head;
                    et = head;
                }
            } else {
                if (bh == null) {
                    bh = head;
                    bt = head;
                } else {
                    bt.next = head;
                    bt = head;
                }
            }
            head = next;
        }
        sh = sh != null ? sh : (eh != null ? eh : bh);
        if (st != null) {
            st.next = eh;
            et = et == null ? st : et;
        }
        if (et != null) {
            et.next = bh;
        }
        return sh;
    }

    /**
     * 一种特殊的单链表节点描述如下
     * CLass Node{
     * int value;
     * Node next;
     * Node rand;
     * Node(int val){
     * value = val;
     * }
     * }
     * <p>
     * rand 指针是单链表节点结构中新增的指针，rand 可能只想链表中的任意一个节点，也可能指向 null；
     * 给定一个由 Node 节点类型组成的无环链表的头结点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头结点。
     * 【要求】
     * 时间复杂度O(N),空间复杂度O(1)
     */
    public static Node copyListWithRand1(Node node) { // 使用哈希表

    }

    public static Node copyListWithRand2(Node node) { // 不用哈希表

    }

    /**
     * 链表中环的入口节点
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode sNode = head;
        ListNode fNode = head.next;
        while (sNode != fNode) {
            if (fNode.next == null || fNode.next.next != null) {
                return null;
            }
            sNode = sNode.next;
            fNode = fNode.next.next;
        }
        fNode = head;
        while (sNode != fNode) {
            sNode = sNode.next;
            fNode = fNode.next;
        }
        return sNode;
    }

    /**
     * 两个链表的第一个重合点
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int n = 0;
        ListNode curA = headA;
        while (curA != null) {
            n++;
            curA = curA.next;
        }
        ListNode curB = headB;
        while (curB != null) {
            n--;
            curB = curB.next;
        }
        curA = n > 0 ? headA : headB;
        curB = curA == headA ? headB : headA;
        n = Math.abs(n);
        while (n > 0) {
            n--;
            curA = curA.next;
        }
        while (curA != curB) {
            curA = curA.next;
            curB = curB.next;
        }
        return curA;
    }


    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
