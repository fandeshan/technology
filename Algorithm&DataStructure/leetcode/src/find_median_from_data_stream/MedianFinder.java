package find_median_from_data_stream;

import java.util.PriorityQueue;

/**
 * @author fandeshan
 * @description 295. 数据流的中位数
 *  中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 *
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/8/27
 */
public class MedianFinder {
    private PriorityQueue<Integer> maxQueue;
    private PriorityQueue<Integer> minQueue;
    /** initialize your data structure here. */
    public MedianFinder() {
        maxQueue = new PriorityQueue<Integer>( (a,b) ->{
            if (a > b) {
                return 1;
            } else if (a < b){
                return -1;
            }
            return 0;
        });
        minQueue = new PriorityQueue<Integer>( (a,b) ->{
            if (a < b) {
                return 1;
            } else if (a > b){
                return -1;
            }
            return 0;
        });
    }

    public void addNum(int num) {
        if (this.maxQueue.size() == 0 && this.minQueue.size() == 0){
            this.maxQueue.offer(num);
            return ;
        }
        if (this.maxQueue.size() > this.minQueue.size()){
            if (this.maxQueue.peek() > num){
                minQueue.offer(num);
            } else {
                maxQueue.offer(num);
                minQueue.offer(maxQueue.poll());
            }
        } else if (this.maxQueue.size() == this.minQueue.size()) {
            if (this.minQueue.peek() > num){
                maxQueue.offer(minQueue.poll());
                minQueue.offer(num);
            } else {
                maxQueue.offer(num);
            }
        }
    }

    public double findMedian() {
        if (this.maxQueue.size() == 0){
            return 0;
        }
        if (this.maxQueue.size() == this.minQueue.size()){
            return (double) (this.maxQueue.peek() + this.minQueue.peek())/2;
        }

        return this.maxQueue.peek();
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
