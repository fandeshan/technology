package design_linked_list;

/**
 * @author fandeshan
 * @description 707. 设计链表
 *
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 *
 * 在链表类中实现这些功能：
 *
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *  
 *
 * 示例：
 *
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 *  
 *
 * 提示：
 *
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2022/9/23
 */
public class MyLinkedList {
    class Node{
        int val;
        Node pre;
        Node next;
        public Node(int val){
            this.val = val;
        }
    }
    Node head;
    int size;
    public MyLinkedList() {
        this.size = 0;
    }

    public int get(int index) {
        if (index <0 || index >= size){
            return -1;
        }
        Node result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0,val);
//        Node node = new Node(val);
//        if (size == 0){
//            head = node;
//            tail = node;
//            head.next = tail;
//            head.pre = tail;
//            tail.next = head;
//            tail.pre = head;
//            return ;
//        }
//        tail.next = node;
//        node.pre = tail;
//        node.next = head;
//        head.pre = node;
//        head = node;
//        this.size ++;
    }

    public void addAtTail(int val) {
        addAtIndex(this.size,val);
//        Node node = new Node(val);
//        if (size == 0){
//            head = node;
//            tail = node;
//            head.next = tail;
//            head.pre = tail;
//            tail.next = head;
//            tail.pre = head;
//            return ;
//        }
//        node.pre =tail;
//        tail.next = node;
//        tail = node;
//        head.pre = node;
//        node.next = head;
//        this.size ++;

    }

    public void addAtIndex(int index, int val) {
        if (index > this.size){
            return ;
        }
        if (index < 0){
            index = 0;
        }
        Node node = new Node(val);
        if (this.size == 0){
            head = node;
            head.next = head;
            head.pre = head;
            this.size ++;
            return ;
        }
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        node.next = curr;
        node.pre = curr.pre;
        curr.pre.next = node;
        curr.pre = node;
        if (index == 0){
            head = node;
        }
        this.size ++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 ||this.size == 0 || index>=this.size){
            return ;
        }
        if (this.size == 1){
            this.head = null;
            this.size = 0;
            return ;
        }
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        if (index == 0){
            head = head.next;
        }
        curr.pre.next = curr.next;
        curr.next.pre = curr.pre;

        this.size --;
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
        linkedList.get(1);            //返回2
        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
        linkedList.get(1);            //返回3

    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
