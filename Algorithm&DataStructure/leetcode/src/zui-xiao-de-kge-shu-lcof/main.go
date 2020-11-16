package main

func main() {
	
}

func getLeastNumbers(arr []int, k int) []int {
	if len(arr) < 1 || k > len(arr) {
		return arr
	}
	quickSortFindKArr(arr,0,len(arr)-1,k)
	return arr[:k]
}

func quickSortFindKArr(nums []int,start int,end int,k int) {
	if end <= start {
		return
	}
	left := start
	right := end
	base := nums[start]
	for left <= right  {
		for left <= right && nums[left] < base  {
			left ++
		}
		for left <= right && nums[right] > base  {
			right --
		}
		if left <= right {
			nums[right],nums[left] = nums[left],nums[right]
			right --
			left ++
		}

	}
	if k <= right {
		quickSortFindKArr(nums,start,right,k)
	}
	if k >= left {
		quickSortFindKArr(nums,left,end,k)
	}
}