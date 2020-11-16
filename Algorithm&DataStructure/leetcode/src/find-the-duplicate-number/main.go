package main

func main() {
	
}

func findDuplicate(nums []int) int {
	for i := 0; i < len(nums); i++ {
		if nums[i] != i {
			for {
				if nums[i] == i {
					break
				}
				if nums[i] == nums[nums[i]] {
					return nums[i]
				} else {
					nums[i], nums[nums[i]] = nums[nums[i]], nums[i]
				}
			}
		}
	}
	return -1
}