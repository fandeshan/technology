package main

import "fmt"

func main() {
	fmt.Println(subsets([]int{1,2,3}))
}

func subsets(nums []int) [][]int {
	result := make([][]int,0)
	if len(nums) < 1 {
		return result
	}
	arr := []int{}
	dfs(nums,arr,&result,0)
	return result
}
func dfs(nums []int,arr []int,result *[][]int,pos int) {
	newArr := make([]int,len(arr))
	copy(newArr,arr)
	*result = append(*result,newArr)
	for i := pos;i<len(nums) ;i++  {
		arr = append(arr,nums[i])
		dfs(nums,arr,result,i+1)
		arr = arr[:len(arr)-1]
	}
}