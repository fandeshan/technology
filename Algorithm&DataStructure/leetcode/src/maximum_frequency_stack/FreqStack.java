package maximum_frequency_stack;

import java.util.*;

/**
 * @author fandeshan
 * @description 895. 最大频率栈
 *
 * 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
 *
 * 实现 FreqStack 类:
 *
 * FreqStack() 构造一个空的堆栈。
 * void push(int val) 将一个整数 val 压入栈顶。
 * int pop() 删除并返回堆栈中出现频率最高的元素。
 * 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
 *
 *
 * 示例 1：
 *
 * 输入：
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * 输出：[null,null,null,null,null,null,null,5,7,5,4]
 * 解释：
 * FreqStack = new FreqStack();
 * freqStack.push (5);//堆栈为 [5]
 * freqStack.push (7);//堆栈是 [5,7]
 * freqStack.push (5);//堆栈是 [5,7,5]
 * freqStack.push (7);//堆栈是 [5,7,5,7]
 * freqStack.push (4);//堆栈是 [5,7,5,7,4]
 * freqStack.push (5);//堆栈是 [5,7,5,7,4,5]
 * freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
 * freqStack.pop ();//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
 * freqStack.pop ();//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
 * freqStack.pop ();//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。
 *
 *
 * 提示：
 *
 * 0 <= val <= 109
 * push 和 pop 的操作数不大于 2 * 104。
 * 输入保证在调用 pop 之前堆栈中至少有一个元素。
 *
 * @date 2022/11/30
 */
public class FreqStack {
    Map<Integer, PriorityQueue<Node>> countMap;
    Map<Integer,Node> numCountMap;
    int maxCount ;
    int size;
    public FreqStack() {
        countMap = new HashMap<>();
        numCountMap = new HashMap<>();
        maxCount = 0;
        size = 0;
    }

    public void push(int val) {
        Node currNode = numCountMap.getOrDefault(val,new Node());
        currNode.val = val;
        if (currNode.indexStack == null){
            currNode.indexStack = new Stack<>();
        }
        maxCount = Math.max(maxCount,currNode.indexStack.size()+1);
        PriorityQueue<Node> countNodes = countMap.getOrDefault(currNode.indexStack.size(), newQueue());
        if (currNode.indexStack.size() !=0){
            countNodes.remove(currNode);
        }
        currNode.indexStack.push(size);
        PriorityQueue<Node> countNodes1 = countMap.getOrDefault(currNode.indexStack.size(), newQueue());
        countNodes1.offer(currNode);
        countMap.put(currNode.indexStack.size(),countNodes1);
        numCountMap.put(val,currNode);
        this.size ++;
    }

    public int pop() {
        PriorityQueue<Node> nodes = countMap.get(maxCount);
        Node popNode = nodes.poll();

        popNode.indexStack.pop();
        if (maxCount>1){
            PriorityQueue<Node> newCountNodes = countMap.getOrDefault(maxCount - 1, newQueue());
            newCountNodes.offer(popNode);
            countMap.put(maxCount - 1,newCountNodes);
        }

        if (nodes.size() == 0){
            maxCount --;
        }
        return popNode.val;
    }

    private PriorityQueue<Node> newQueue(){
        return new PriorityQueue<Node>((a,b)->{
            return b.indexStack.peek().compareTo(a.indexStack.peek());
        });
    }
    class Node{
        int val;
        Stack<Integer> indexStack;
    }
//    public static void main(String[] args) {
//        FreqStack freqStack = new FreqStack();
//        freqStack.push (4);
//        freqStack.push (0);
//        freqStack.push (9);
//        freqStack.push (3);
//        freqStack.push (4);
//        freqStack.push (2);
//        System.out.println(freqStack.pop ());
//        freqStack.push (6);
//
//    }
    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push (5);//堆栈为 [5]
        freqStack.push (7);//堆栈是 [5,7]
        freqStack.push (5);//堆栈是 [5,7,5]
        freqStack.push (7);//堆栈是 [5,7,5,7]
        freqStack.push (4);//堆栈是 [5,7,5,7,4]
        freqStack.push (5);//堆栈是 [5,7,5,7,4,5]

        System.out.println(freqStack.pop ());//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,5,7,4]。
        System.out.println(freqStack.pop ());//返回 7 ，因为 5 和 7 出现频率最高，但7最接近顶部。堆栈变成 [5,7,5,4]。
        System.out.println(freqStack.pop ());//返回 5 ，因为 5 出现频率最高。堆栈变成 [5,7,4]。
        System.out.println(freqStack.pop ());//返回 4 ，因为 4, 5 和 7 出现频率最高，但 4 是最接近顶部的。堆栈变成 [5,7]。
        System.out.println(freqStack.pop ());
        System.out.println(freqStack.pop ());
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
