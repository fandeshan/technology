package main

import (
	"fmt"
)

func main() {
	fmt.Println(checkStraightLine([][]int{{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}}))
	fmt.Println(checkStraightLine([][]int{{0,0},{0,1},{0,-1}}))
}

func checkStraightLine(coordinates [][]int) bool {
	if len(coordinates) < 2 {
		return true
	}
	quickSort(coordinates,0,len(coordinates)-1)
	fmt.Println(coordinates)
	xAdd := coordinates[1][0] - coordinates[0][0]
	yAdd := coordinates[1][1] - coordinates[0][1]
	canMod := true
	if xAdd == 0 {
		canMod = false
	}
	for i :=2 ; i < len(coordinates);i++ {
		xAddTemp := coordinates[i][0]- coordinates[i-1][0]
		yAddTemp := coordinates[i][1]- coordinates[i-1][1]
		if canMod {
			if xAddTemp == 0 {
				return false
			}
			if  (yAdd/xAdd != yAddTemp/xAddTemp) || (yAdd%xAdd != yAddTemp%xAddTemp) {
				return false
			}
		} else {
			if xAddTemp != 0 {
				return false
			}
		}

	}
	return true
}

func quickSort(nums [][]int,begin int ,end int){
	if begin >= end {
		return
	}
	left := begin
	right := end
	base := nums[begin][0]
	base1 := nums[begin][1]
	for left <= right {
		for left <= right && (nums[left][0] < base || (nums[left][0] == base && nums[left][1]< base1))  {
			left ++
		}
		for left <= right && (nums[right][0] > base || (nums[right][0] == base && nums[right][1] > base1)) {
			right --
		}
		if left <= right {
			nums[left],nums[right] = nums[right],nums[left]
			left ++
			right --
		}
	}
	quickSort(nums,begin,right)
	quickSort(nums,left,end)
}