package main

import (
	"container/heap"
	"fmt"
)

func main() {
	//mid := Constructor()
	//mid.AddNum(1)
	//fmt.Println(mid.FindMedian())
	//mid.AddNum(2)
	//fmt.Println(mid.FindMedian())
	//mid.AddNum(3)
	//fmt.Println(mid.FindMedian())
	//mid.AddNum(4)
	//fmt.Println(mid.FindMedian())
	//mid.AddNum(5)
	//fmt.Println(mid.FindMedian())

	mid := Constructor()
	mid.AddNum(-1)
	fmt.Println(mid.FindMedian())
	mid.AddNum(-2)
	fmt.Println(mid.FindMedian())
	mid.AddNum(-3)
	fmt.Println(mid.FindMedian())
	mid.AddNum(-4)
	fmt.Println(mid.FindMedian())
	mid.AddNum(-5)
	fmt.Println(mid.FindMedian())
}

type IntMaxHeap []int  // 定义一个类型

func (h IntMaxHeap) Len() int { return len(h) }  // 绑定len方法,返回长度
func (h IntMaxHeap) Less(i, j int) bool {  // 绑定less方法
	return h[i] < h[j]  // 如果h[i]<h[j]生成的就是小根堆，如果h[i]>h[j]生成的就是大根堆
}
func (h IntMaxHeap) Swap(i, j int) {  // 绑定swap方法，交换两个元素位置
	h[i], h[j] = h[j], h[i]
}

func (h *IntMaxHeap) Pop() interface{} {  // 绑定pop方法，从最后拿出一个元素并返回
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func (h *IntMaxHeap) Peek() interface{} {  //获取最后一个元素
	old := *h
	x := old[0]
	return x
}

func (h *IntMaxHeap) Push(x interface{}) {  // 绑定push方法，插入新元素
	*h = append(*h, x.(int))
}
func (h *IntMaxHeap) print() {
	old := *h
	for i:=0;i<len(old) ;i++  {
		fmt.Print(old[i]," ")
	}
	fmt.Println()
}

type IntMinHeap []int  // 定义一个类型

func (h IntMinHeap) Len() int { return len(h) }  // 绑定len方法,返回长度
func (h IntMinHeap) Less(i, j int) bool {  // 绑定less方法
	return h[i] > h[j]  // 如果h[i]<h[j]生成的就是小根堆，如果h[i]>h[j]生成的就是大根堆
}
func (h IntMinHeap) Swap(i, j int) {  // 绑定swap方法，交换两个元素位置
	h[i], h[j] = h[j], h[i]
}

func (h *IntMinHeap) Pop() interface{} {  // 绑定pop方法，从最后拿出一个元素并返回
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func (h *IntMinHeap) Peek() interface{} {  //获取最后一个元素
	old := *h
	x := old[0]
	return x
}

func (h *IntMinHeap) Push(x interface{}) {  // 绑定push方法，插入新元素
	*h = append(*h, x.(int))
}
func (h *IntMinHeap) print() {
	old := *h
	for i:=0;i<len(old) ;i++  {
		fmt.Print(old[i]," ")
	}
	fmt.Println()
}

type MedianFinder struct {
	maxH *IntMaxHeap
	minH *IntMinHeap
}


/** initialize your data structure here. */
func Constructor() MedianFinder {
	return MedianFinder{
		maxH:   &IntMaxHeap{},
		minH:   &IntMinHeap{},
	}
}


func (this *MedianFinder) AddNum(num int)  {
	if this.maxH.Len() == 0 && this.minH.Len() == 0 {
		heap.Push(this.maxH,num)
		return
	}
	if this.maxH.Len() > this.minH.Len() {
		if this.maxH.Peek().(int) > num {
			heap.Push(this.minH,num)
		}else{
			heap.Push(this.maxH,num)
			heap.Push(this.minH,heap.Pop(this.maxH))
		}
	} else if this.maxH.Len() == this.minH.Len() {
		if this.minH.Peek().(int) > num {
			heap.Push(this.maxH,heap.Pop(this.minH))
			heap.Push(this.minH,num)
		} else {
			heap.Push(this.maxH,num)
		}
	}
}


func (this *MedianFinder) FindMedian() float64 {
	if this.maxH.Len() == 0 {
		return 0
	}
	if this.maxH.Len() == this.minH.Len() {
		return float64(this.maxH.Peek().(int) + this.minH.Peek().(int))/2
	}
	return float64(this.maxH.Peek().(int))
}


/**
 * Your MedianFinder object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddNum(num);
 * param_2 := obj.FindMedian();
 */

