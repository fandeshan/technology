package main

import (
	"container/heap"
	"fmt"
)

func main() {
	fmt.Println(findKthLargest1([]int{3,2,1,5,6,4},2))
	fmt.Println(findKthLargest1([]int{3,2,3,1,2,4,5,5,6},4))
}

func findKthLargest(nums []int, k int) int {
	h := &IntHeap{}
	for i:=0;i<len(nums) ;i++  {
		h.Push(nums[i])
	}
	heap.Init(h)
	for i:=0;i< k - 1 ;i++  {
		heap.Pop(h)
	}
	return heap.Pop(h).(int)
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


func findKthLargest1(nums []int, k int) int {
	if len(nums) < 1 {
		return 0
	}
	return quickSortFindK(nums,0,len(nums)-1,len(nums)-k)
}
func quickSortFindK(nums []int,start int,end int,k int) int{
	if end <= start {
		return nums[k]
	}
	left := start
	right := end
	base := nums[start]
	for left <= right  {
		for left <= right && nums[left] < base  {
			left ++
		}
		for left <= right && nums[right] > base  {
			right --
		}
		if left <= right {
			nums[right],nums[left] = nums[left],nums[right]
			right --
			left ++
		}

	}
	if k <= right {
		return quickSortFindK(nums,start,right,k)
	}
	if k >= left {
		return quickSortFindK(nums,left,end,k)
	}
	return nums[k]
}