package main

import "fmt"

func main() {
	fmt.Println(subarraySum([]int{1,1,1},2))
	fmt.Println(subarraySum([]int{1,1,1},1))
}
func subarraySum(nums []int, k int) int {
	n :=len(nums)
	if n < 1 {
		return 0
	}
	sums := make([]int,n+1)
	sums[0] = 0
	for i := 0;i < n ;i ++  {
		sums[i+1] = sums[i] + nums[i]
	}
	cnt := 0
	for i := 0;i < n+1 ;i ++  {
		for j := i+1;j < n+1 ;j ++  {
			if sums[j] - sums[i] == k {
				cnt ++
			}
		}
	}
	return cnt
}