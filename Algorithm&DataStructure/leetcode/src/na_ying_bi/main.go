package main

import "fmt"

func main() {
	fmt.Println(minCount([]int{4,2,1}))
	fmt.Println(minCount([]int{2,3,10}))
	//fmt.Println(minCount([]int{4,2,1}))
}

func minCount(coins []int) int {
	result := 0
	for i :=0;i <len(coins) ; i++  {
		result += coins[i]/2
		result += coins[i]%2
	}
	return result
}