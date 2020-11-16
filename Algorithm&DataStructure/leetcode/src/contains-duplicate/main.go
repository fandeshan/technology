package main

func main() {
	
}

func containsDuplicate(nums []int) bool {
	if len(nums) < 2 {
		return false
	}
	numMap := make(map[int]int)
	for i := 0;i < len(nums) ;i ++  {
		if _,ok := numMap[nums[i]];ok {
			return true
		} else {
			numMap[nums[i]] = 1
		}
	}
	return false
}