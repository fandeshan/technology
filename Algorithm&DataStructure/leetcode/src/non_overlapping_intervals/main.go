package main

import "fmt"

func main() {
	nums := [][]int{{1,2},{2,3},{3,4},{1,3}}
	fmt.Println(eraseOverlapIntervals(nums))
	fmt.Println(eraseOverlapIntervals([][]int{{1,2},{1,2},{1,2}}))
	fmt.Println(eraseOverlapIntervals([][]int{{1,2},{2,3}}))
}

func eraseOverlapIntervals(intervals [][]int) int {
	if len(intervals) < 2 {
		return 0
	}
	quickSort(intervals,0,len(intervals) - 1)
	//lastStart := intervals[0][0]
	index := 0
	result := 0
	for i := 0 ; i < len(intervals) ; i++ {
		minEndIndex := index
		for j :=index;j < len(intervals);j++ {
			if intervals[j][0] >= intervals[minEndIndex][1] {
				break
			}
			if intervals[j][1] < intervals[minEndIndex][1] {
				minEndIndex = j
			}
		}
		result += minEndIndex - index
		index = minEndIndex + 1
		if index >= len(intervals){
			break
		}
		for j :=index;j < len(intervals);j++ {
			if intervals[j][0] >= intervals[minEndIndex][1] {
				break
			}
			index ++
			result ++
		}
		if index >= len(intervals){
			break
		}
	}
	return result
}

func quickSort(nums [][]int,start int,end int) {
	if end <= start {
		return
	}
	left := start
	right := end
	base := nums[start][0]
	base1 := nums[start][1]
	for left <= right  {
		for left <= right && (nums[left][0] < base || (nums[left][0] == base && nums[left][1] < base1))  {
			left ++
		}
		for left <= right && (nums[right][0] > base ||(nums[right][0] == base && nums[right][1] > base1)) {
			right --
		}
		if left <= right {
			nums[right],nums[left] = nums[left],nums[right]
			right --
			left ++
		}

	}
	quickSort(nums,start,right)
	quickSort(nums,left,end)
}