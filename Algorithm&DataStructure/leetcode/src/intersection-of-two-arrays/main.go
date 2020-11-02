package main

import "fmt"

func main() {
	fmt.Println(intersection([]int{4,9,5},[]int{9,4,9,8,4}))
	fmt.Println(intersection([]int{4,9,5},[]int{}))
}

func intersection(nums1 []int, nums2 []int) []int {
	if len(nums1) < 1 || len(nums2) < 1 {
		return nil
	}

	nums1Map := make(map[int]int)
	for i := 0;i < len(nums1) ;i++  {
		nums1Map[nums1[i]] = 1
	}

	for i := 0;i < len(nums2) ;i++  {
		if val,ok := nums1Map[nums2[i]];ok{
			nums1Map[nums2[i]] = val + 1
		}
	}
	result := make([]int,0)
	for k,v := range nums1Map {
		if v > 1 {
			result = append(result,k)
		}
	}
	return result
}