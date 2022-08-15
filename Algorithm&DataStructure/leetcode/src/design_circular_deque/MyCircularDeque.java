package design_circular_deque;

import java.util.List;

/**
 * @author fandeshan
 * @description 641. 设计循环双端队列
 * 设计实现双端队列。
 *
 * 实现 MyCircularDeque 类:
 *
 * MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
 * boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
 * boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
 * boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
 * boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
 * int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
 * int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
 * boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false  。
 * boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。
 *  
 *
 * 示例 1：
 *
 * 输入
 * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * 输出
 * [null, true, true, true, false, 2, true, true, true, 4]
 *
 * 解释
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);			        // 返回 true
 * circularDeque.insertLast(2);			        // 返回 true
 * circularDeque.insertFront(3);			        // 返回 true
 * circularDeque.insertFront(4);			        // 已经满了，返回 false
 * circularDeque.getRear();  				// 返回 2
 * circularDeque.isFull();				        // 返回 true
 * circularDeque.deleteLast();			        // 返回 true
 * circularDeque.insertFront(4);			        // 返回 true
 * circularDeque.getFront();				// 返回 4
 *  
 *  
 *
 * 提示：
 *
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull  调用次数不大于 2000 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-circular-deque
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2022/8/15
 */
public class MyCircularDeque {
    Node front;
    Node last;
    int size;
    int currSize;
    public MyCircularDeque(int k) {
        this.size = k;
        this.currSize = 0;
    }

    public boolean insertFront(int value) {
        if (currSize >= size){
            return false;
        }
        this.currSize ++;
        Node newFront = new Node(value);
        if (currSize == 1){
            this.front = newFront;
            this.last = newFront;
        }
        newFront.next = this.front;
        newFront.pre = this.last;
        this.front.pre = newFront;
        this.front = newFront;
        return true;
    }

    public boolean insertLast(int value) {
        if (currSize >= size){
            return false;
        }
        this.currSize ++;
        Node newLast = new Node(value);
        if (currSize == 1){
            this.front = newLast;
            this.last = newLast;
        }
        newLast.pre = this.last;
        newLast.next = this.front;
        this.last.next = newLast;
        this.last = newLast;
        return true;
    }

    public boolean deleteFront() {
        if (currSize < 1){
            return false;
        }

        currSize --;
        if (currSize == 0){
            this.front = null;
            this.last = null;
            return true;
        }
        Node frontNext = this.front.next;
        frontNext.pre = this.last;
        this.front = frontNext;
        return true;
    }

    public boolean deleteLast() {
        if (currSize < 1){
            return false;
        }

        currSize --;
        if (currSize == 0){
            this.front = null;
            this.last = null;
            return true;
        }
        Node lastPre = this.last.pre;
        lastPre.next = this.front;
        this.last = lastPre;
        return true;
    }

    public int getFront() {
        if (this.front == null){
            return -1;
        }
        return this.front.val;
    }

    public int getRear() {
        if (this.last == null){
            return -1;
        }
        return this.last.val;
    }

    public boolean isEmpty() {
        return currSize == 0;
    }

    public boolean isFull() {
        return currSize == size;
    }
}
class Node{
    int val;
    Node pre;
    Node next;
    public Node(int val){
        this.val = val;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
