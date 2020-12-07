package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(firstMissingPositive([]int{-1,-2,-3,-4,-5,-6,-7,7,8,9,11,12}))
	fmt.Println(firstMissingPositive([]int{1,1}))
	fmt.Println(firstMissingPositive([]int{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19}))
}

func firstMissingPositive(nums []int) int {
	memo := make([]int,10)
	for i := 0 ; i < len(nums) ; i ++ {
		if nums[i] <= 0 {
			continue
		}
		tmp := 1<<(nums[i]/10)
		if tmp & memo[nums[i]%10] == tmp {
			continue
		}
		memo[nums[i]%10] += tmp
	}
	n := len(nums)/10
	fmt.Println(memo)
	for i := 0 ; i <= n ; i ++ {
		for j := 0 ; j < 10 ; j ++ {
			if i == 0 && j == 0 {
				continue
			}
			memo[j] -= 1<<i
			if memo[j] % (1<<(i+1)) != 0 {
				return i*10+j
			}
		}
	}
	return (n+1)*10
}
func pow2(r int) int {
	return int(math.Pow(2,float64(r)))
}
func log2(r int) int {
	return int(math.Log2(float64(r)))
}

func pow10(r int) int {
	return int(math.Pow(10,float64(r)))
}

func firstMissingPositive1(nums []int) int {
	numsMap := make(map[int]int)
	for i := 0 ; i < len(nums) ; i ++ {
		if nums[i] <= 0 {
			continue
		}
		numsMap[nums[i]] = 1
	}
	for i := 1 ; i <= len(nums) ; i ++ {
		if _,ok := numsMap[i];!ok {
			return i
		}
	}
	return len(nums) + 1
}