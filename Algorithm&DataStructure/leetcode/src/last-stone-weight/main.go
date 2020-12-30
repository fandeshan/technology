package main

import (
	"container/heap"
	"fmt"
)

func main() {
	fmt.Println(lastStoneWeight([]int{2,7,4,1,8,1}))
}

func lastStoneWeight(stones []int) int {
	h := &IntHeap{}
	for i := 0;i < len(stones);i++ {
		h.Push(stones[i])
	}
	heap.Init(h)
	for h.Len() > 1 {
		y := heap.Pop(h).(int)
		x := heap.Pop(h).(int)
		if y > x {
			heap.Push(h,y-x)
		}
	}
	if h.Len() == 1 {
		return heap.Pop(h).(int)
	}
	return 0
}

type IntHeap []int  // 定义一个类型

func (h IntHeap) Len() int { return len(h) }  // 绑定len方法,返回长度
func (h IntHeap) Less(i, j int) bool {  // 绑定less方法
	return h[i] > h[j]  // 如果h[i]<h[j]生成的就是小根堆，如果h[i]>h[j]生成的就是大根堆
}
func (h IntHeap) Swap(i, j int) {  // 绑定swap方法，交换两个元素位置
	h[i], h[j] = h[j], h[i]
}

func (h *IntHeap) Pop() interface{} {  // 绑定pop方法，从最后拿出一个元素并返回
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func (h *IntHeap) Push(x interface{}) {  // 绑定push方法，插入新元素
	*h = append(*h, x.(int))
}

func (h *IntHeap) Print() {
	fmt.Println(*h)
}