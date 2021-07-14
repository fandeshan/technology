package main

import "fmt"

func main() {
	//nums := []int{3,2,4,1,5}
	//nums := []int{3,2}
	//nums := []int{1}
	//quickSort(nums,0,len(nums)-1)
	//fmt.Println(nums)
	fmt.Println(largestPerimeter([]int{3,6,2,3}))
	fmt.Println(largestPerimeter([]int{3,2,3,4}))
	fmt.Println(largestPerimeter([]int{1,2,1}))
	fmt.Println(largestPerimeter([]int{2,1,2}))
}

func largestPerimeter(A []int) int {
	if len(A) < 3 {
		return 0
	}
	quickSort(A,0,len(A)-1)
	result := 0
	for i := 2;i < len(A) ;i ++  {
		if A[i-2] < A[i] + A[i-1] {
			result = A[i-1] + A[i-2] + A[i]
			break
		}
	}
	return result
}

func quickSort(nums [] int,start int,end int){
	if start >= end {
		return
	}
	left := start
	right := end
	base := nums[left]
	for left <= right  {
		for left <= right && nums[left] > base  {
			left ++
		}
		for left <= right && nums[right] < base  {
			right --
		}
		if left <= right {
			nums[left],nums[right] = nums[right],nums[left]
			left ++
			right --
		}
	}
	quickSort(nums,start,right)
	quickSort(nums,left,end)
}