package main

import (
	"container/heap"
	"fmt"
)

func main() {
	fmt.Println(smallestK1([]int{1,3,5,7,2,4,6,8},4))
}

func smallestK(arr []int, k int) []int {
	if len(arr) < 1 || k > len(arr) {
		return arr
	}
	h := &IntHeap{}
	for i:=0;i<len(arr) ;i++  {
		h.Push(arr[i])
	}
	heap.Init(h)
	result := make([]int,0)
	for i:=0;i< k  ;i++  {
		result = append(result,heap.Pop(h).(int))
	}
	return result
}

type IntHeap []int  // 定义一个类型

func (h IntHeap) Len() int { return len(h) }  // 绑定len方法,返回长度
func (h IntHeap) Less(i, j int) bool {  // 绑定less方法
	return h[i] < h[j]  // 如果h[i]<h[j]生成的就是小根堆，如果h[i]>h[j]生成的就是大根堆
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

func smallestK1(arr []int, k int) []int {
	if len(arr) < 1 || k > len(arr) {
		return arr
	}
	quickSortFindKArr(arr,0,len(arr)-1,k)
	return arr[:k]
}

func quickSortFindKArr(nums []int,start int,end int,k int) {
	if end <= start {
		return
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
		quickSortFindKArr(nums,start,right,k)
	}
	if k >= left {
		quickSortFindKArr(nums,left,end,k)
	}
}
