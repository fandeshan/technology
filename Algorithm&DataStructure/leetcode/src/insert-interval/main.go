package main

import "fmt"

func main() {
	//intervals := [][]int{{1,2},{3,5},{6,7},{8,10},{12,16}}
	//newInterval := []int{4,8}
	intervals := [][]int{{3,6},{9,12},{15,18},{21,24},{27,30}}
	newInterval := []int{0,3}
	fmt.Println(insert(intervals,newInterval))
}

func insert(intervals [][]int, newInterval []int) [][]int {
	if len(newInterval) < 1 {
		return intervals
	}
	if len(intervals) < 1 {
		return [][]int{newInterval}
	}
	startLocation,startIn := halfSearch(intervals,newInterval[0])
	endLocation,endIn := halfSearch(intervals,newInterval[1])
	fmt.Println(startLocation,startIn)
	fmt.Println(endLocation,endIn)
	result := make([][]int,0)
	if startLocation == -1 {
		return append(intervals,newInterval)
	}
	if endLocation == -1 {
		result = intervals[:startLocation]
		if startIn {
			return append(result,[]int{intervals[startLocation][0],newInterval[1]})
		}else {
			return append(result,newInterval)
		}
	}
	if startIn {
		result = append(result,intervals[:startLocation]...)
		if endIn {
			result = append(result,[]int{intervals[startLocation][0],intervals[endLocation][1]})
		} else {
			result = append(result,[]int{intervals[startLocation][0],newInterval[1]})
			result = append(result,[]int{intervals[endLocation][0],intervals[endLocation][1]})
		}
		result = append(result,intervals[endLocation+1:len(intervals)]...)
		return result
	}else{
		if startLocation > 0 {
			result = append(result,intervals[:startLocation]...)
		}
		if endIn {
			result = append(result,[]int{newInterval[0],intervals[endLocation][1]})
		}else {
			if endLocation == 0 {
				result = append(result,newInterval)
				result = append(result,[]int{intervals[startLocation][0],intervals[endLocation][1]})
			}else {
				result = append(result,newInterval)
				result = append(result,[]int{intervals[endLocation][0],intervals[endLocation][1]})
			}
		}
		result = append(result,intervals[endLocation+1:len(intervals)]...)
		return result
	}
}

func halfSearch(intervals [][]int, target int) (int,bool) {
	start := 0
	end := len(intervals)-1
	for start+1 < end  {
		mid := start + (end - start)/2
		if intervals[mid-1][1] < target && intervals[mid][1] >= target  {
			if intervals[mid][0] <= target {
				return mid,true
			}
			return mid,false
		}else if intervals[mid][1] < target {
			start = mid
		}else {
			end = mid
		}
	}
	if  intervals[start][1] >= target {
		if intervals[start][0]> target {
			return start,false
		}
		return start,true
	}
	if  intervals[end][1] >= target {
		if intervals[end][0]> target {
			return end,false
		}
		return end,true
	}

	return -1,false
}
