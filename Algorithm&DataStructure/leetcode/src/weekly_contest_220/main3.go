package main

import (
	"container/heap"
	"fmt"
)

func main() {
	fmt.Println(maxResult2([]int{1,-1,-2,4,-7,3},2))
	fmt.Println(maxResult2([]int{10,-5,-2,4,0,3},3))
	fmt.Println(maxResult2([]int{1,-5,-20,4,-1,3,-6,-3},2))
}

func maxResult(nums []int, k int) int {
	n := len(nums)
	dp := make([]int,n)
	dp[0] = nums[0]
	if n == 1 {
		return dp[0]
	}
	h := &IntHeap{}
	h.Push(dp[0])

	for i :=1; i < n;i++ {
		//dp[i] = nums[i]
		//j := i-k
		//if j < 0 {
		//	j = 0
		//}
		//max := math.MinInt32
		//for ;j < i ;j ++ {
		//	if max < nums[i]+dp[j] {
		//		max = nums[i]+dp[j]
		//	}
		//}

		max := heap.Pop(h).(int)
		//max := heap.Fix(h,h.Len()-1)
		fmt.Println(max,h.Len())
		dp[i] = nums[i] + max
		if h.Len() < k {
			h.Push(dp[i])
		} else {
			h.Pop()
			h.Push(dp[i])
		}
		heap.Init(h)
		//dp[i] = max
	}
	//fmt.Println(dp)
	return dp[n-1]
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
	x := old[0]
	*h = old[1 : n]
	return x
}

func (h *IntHeap) Push(x interface{}) {  // 绑定push方法，插入新元素
	*h = append(*h, x.(int))
}


func maxResult1(nums []int, k int) int {
	n := len(nums)
	dp := make([]int,n)
	dp[0] = nums[0]
	if n == 1 {
		return dp[0]
	}
	for i :=1; i < n;i++ {
		max := dp[i-1]
		min := i-k
		if min < 0 {
			min = 0
		}
		for j:=i-1;j >= min ;j -- {
			if max < dp[j] {
				max = dp[j]
			}
			if nums[j] > 0 {
				break
			}
		}
		dp[i] = max + nums[i]
	}
	//fmt.Println(dp)
	return dp[n-1]
}
func maxResult2(nums []int, k int) int {
	n := len(nums)
	result := nums[0]
	index := 0
	maxIndex := 1
	for i :=1; i < n; i++ {
		if nums[i] >= 0 {
			result += nums[i]
			if i == n -1 {
				break
			}
			index = i
			maxIndex = i + 1
		} else {
			if nums[i] >= nums[maxIndex] {
				maxIndex = i
			}
			if i == n -1 {
				result += nums[n-1]
				break
			}
			if i - index == k {
				result += nums[maxIndex]
				index = maxIndex
			}
		}
	}
	return result
}