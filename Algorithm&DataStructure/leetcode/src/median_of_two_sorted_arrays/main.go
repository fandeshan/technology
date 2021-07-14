package main

import "fmt"

func main() {
	nums1 := []int{1, 3}
	nums2 := []int{2}
	fmt.Println(findMedianSortedArrays(nums1, nums2))
}

func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	resultArr := nums1
	for i := 0; i < len(nums2); i++ {
		if len(resultArr) < 1 || resultArr[0] >= nums2[i] {
			resultArr = append([]int{nums2[i]}, resultArr...)
			continue
		}
		var low = 0
		var high = len(resultArr) - 1
		var mid = 0
		if resultArr[high] <= nums2[i] {
			resultArr = append(resultArr, nums2[i])
			continue
		}
		for high-1 >= low {
			mid = (low + high + 1) / 2
			if resultArr[mid] >= nums2[i] && resultArr[mid-1] <= nums2[i] {
				tmp := append([]int{}, resultArr[mid:]...)
				resultArr = append(append(resultArr[:mid], nums2[i]), tmp...)
				break
			} else if resultArr[mid] > nums2[i] {
				high = mid
			} else {
				low = mid
			}
		}

	}
	fmt.Println(resultArr)
	lenRst := len(resultArr)
	if lenRst == 0 {
		return float64(0)
	}
	if lenRst%2 == 0 {
		return float64(resultArr[(lenRst+1)/2-1]+resultArr[(lenRst+1)/2]) / 2
	}

	return float64(resultArr[(lenRst+1)/2-1])
}

//func halfSearchInsert(nums []int, target int) int{
//	lenNums := len(nums)
//	if lenNums < 1 {
//		return -1
//	}
//	if target<=nums[0] {
//		nums = append([]int{target},nums[0:]...)
//		return 0
//	}
//	if target>=lenNums-1 {
//		nums = append(nums,target)
//		return lenNums-1
//	}
//	mid := nums[lenNums/2]
//	if  mid<=target{
//		halfSearchInsert(nums[:mid],target)
//	}else {
//		halfSearchInsert(nums[mid:],target)
//	}
//
//}
