package main

import (
	"fmt"
	"math"
	"sort"
)

func main() {
	fmt.Println(maxHeight([][]int{{7,11,17},{7,17,11},{11,7,17},{11,17,7},{17,7,11},{17,11,7}}))
	fmt.Println(maxHeight([][]int{{38,25,45},{76,35,3}}))
	fmt.Println(maxHeight([][]int{{50,45,20},{95,37,53},{45,23,12}}))
	fmt.Println(maxHeight([][]int{{12,76,13},{68,55,30},{48,85,52},{91,7,41},{29,65,35}}))
}
func maxHeight(cuboids [][]int) int {
	n := len(cuboids)
	for i := 0 ; i < n ; i++ {
		sort.Ints(cuboids[i])
	}
	quickSort(cuboids,0,n-1)
	fmt.Println(cuboids)
	dp := make([]int,n)
	ans := math.MinInt32
	for i := 0 ; i < n ; i++ {
		dp[i] = cuboids[i][2]
		for j := 0 ; j < i;j ++ {
			if cuboids[i][0] >= cuboids[j][0] && cuboids[i][1] >= cuboids[j][1] && cuboids[i][2] >= cuboids[j][2] {
				dp[i] = max1(dp[i],dp[j] + cuboids[i][2])
			}
		}
		ans = max1(ans,dp[i])
	}
	return ans
}

func max1(a int,b int) int {
	if a > b {
		return a
	}
	return b
}



func quickSort(nums [][]int,start int,end int) {
	if end <= start {
		return
	}
	left := start
	right := end
	base := nums[start][0]
	base1 := nums[start][1]
	base2 := nums[start][2]
	for left <= right  {
		for left <= right && (nums[left][0] < base || (nums[left][0] == base && (nums[left][1] < base1 ||(nums[left][1] == base1 && nums[left][2] < base2))))  {
			left ++
		}
		for left <= right && (nums[right][0] > base || (nums[right][0] == base && (nums[right][1] > base1 ||(nums[right][1] == base1 && nums[right][2] > base2))))  {
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

func sum3(nums []int) int {
	return nums[0] + nums[1] + nums[2]
}

//func dfs(cuboids [][]int){
//
//}

func comp(ax int,ay int,bx int,by int) bool{
	if ax == bx {
		return ay > by
	}
	return ax > bx
}