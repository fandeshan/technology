package main

import "fmt"

func main() {
	nums := []int{1,2,3,4,5,6,7}
	rotate(nums,3)
	fmt.Println(nums)
}

func rotate(nums []int, k int)  {
	n := len(nums)
	if n < 2 {
		return
	}
	k = k % n
	for i := 0 ; i < k ;i++ {
		tmp := nums[n-1]
		for j := n-1; j >= 1 ;j-- {
			nums[j] = nums[j-1]
		}
		nums[0] = tmp
	}
}