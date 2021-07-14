package main

import "fmt"

func main() {
	//nums := [][]int{{10,16},{2,8},{1,6},{7,12},{2,6}}
	//nums := [][]int{{1,2},{3,4},{5,6},{7,8}}
	nums := [][]int{{7,15},{6,14},{8,12},{3,4},{4,13},{6,14},{9,11},{6,12},{4,13}}
	fmt.Println(findMinArrowShots(nums))
}

func findMinArrowShots(points [][]int) int {
	if len(points) < 2 {
		return len(points)
	}
	quickSort(points,0,len(points) - 1)
	fmt.Println(points)
	x := points[0][1]
	cnt := 1
	for i := 1; i < len(points) ; i++  {
		if points[i][0] > x {
			x = points[i][1]
			cnt ++
		}
	}
	return cnt
}

func quickSort(nums [][]int,start int,end int)  {
	if end <= start {
		return
	}
	left := start
	right := end
	base := nums[start][1]
	for left <= right  {
		for left <= right && nums[left][1] < base  {
			left ++
		}
		for left <= right && nums[right][1] > base  {
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