package main

import "fmt"

func main() {
	fmt.Println(minPatches([]int{1,2,3,4,5},10))
	fmt.Println(minPatches([]int{1,3},6))
	fmt.Println(minPatches([]int{1,5,10},20))
	fmt.Println(minPatches([]int{1,2,2},5))
	fmt.Println(minPatches([]int{},7))
}

func minPatches(nums []int, n int) int {
	result := 0
	i := 0
	sum := 1
	for sum <= n {
		if i < len(nums) && nums[i] <= sum {
			sum += nums[i]
			i++
		} else {
			sum *= 2
			result ++
		}
	}
	return result

}
func minPatches1(nums []int, n int) int {
	sums := make([]int,n+1)
	dfs(sums,0,nums)
	fmt.Println(sums)
	result := 0
	for i := 1 ; i < len(sums);i++ {
		if sums[i] == 0 {
			result ++
			addVal(sums,i)
			sums[i] = 1
			fmt.Println(sums)
		}
	}
	return result

}
func addVal(sums []int,val int)  {
	for i := 1;i < len(sums);i++ {
		if val + i >= len(sums) {
			break
		}
		if sums[i] == 1 {
			sums[val+i] = 1
		}
	}
}
func dfs(sums []int,base int,nums []int)  {
	for i := 0;i < len(nums);i++ {
		sum := base + nums[i]
		if sum < len(sums) {
			sums[sum] = 1
			dfs(sums,sum,nums[i+1:])
		}
	}
}