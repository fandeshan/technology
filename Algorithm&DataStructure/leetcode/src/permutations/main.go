package main

import "fmt"

func main() {
	fmt.Println(permute([]int{1,2,3}))
	fmt.Println(permute([]int{5,4,6,2}))
}

func permute(nums []int) [][]int {
	result := make([][]int,0)
	if len(nums) < 1 {
		return result
	}
	visitMap := make(map[int]int)
	visitQueue := make([]int,0)
	dfs(nums,visitMap,visitQueue,&result)
	return result
}
func dfs(nums []int,visitMap map[int]int,visitQueue []int,result *[][]int)  {
	if len(visitMap) == len(nums) {
		singleRet := make([]int,len(visitMap))
		for i,v := range visitQueue {
			singleRet[i] = visitMap[v]
		}
		fmt.Println(visitMap)
		*result = append(*result,singleRet)
		return
	}
	for i :=0 ;i < len(nums) ;i ++  {
		if _,ok := visitMap[i] ;ok {
			continue
		}
		visitMap[i] = nums[i]
		visitQueue = append(visitQueue,i)
		dfs(nums,visitMap,visitQueue,result)
		delete(visitMap,i)
		visitQueue = visitQueue[:len(visitQueue)-1]
	}
}