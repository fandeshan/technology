package main

func main() {

}

func maxSlidingWindow(nums []int, k int) []int {
	stack :=[]int{}
	result :=[]int{}
	for i := 0 ;i < len(nums) ; i++ {
		if len(stack) > 0 && i-stack[0] > k-1 {
			stack = stack[1:]
		}
		for len(stack) > 0 && nums[stack[len(stack) -1]] <= nums[i] {
			stack = stack[:len(stack)-1]
		}
		stack = append(stack,i)
		if i >= k-1 {
			result = append(result,nums[stack[0]])
		}
	}
	return result
}