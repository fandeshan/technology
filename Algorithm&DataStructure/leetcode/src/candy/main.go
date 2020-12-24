package main

import "fmt"

func main() {
	fmt.Println(candy([]int{1,0,2}))
	fmt.Println(candy([]int{1,2,2}))
	fmt.Println(candy([]int{3,2,1,1,2,3,4,2,2,1}))
}

func candy(ratings []int) int {
	n := len(ratings)
	if n < 1 {
		return 0
	}
	if n < 2 {
		return 1
	}
	minCandys := make([]int,n)
	markOne(ratings,minCandys)

	for i := 0 ;i < n;i++ {
		if minCandys[i] != 1 {
			continue
		}
		for j := i -1;j>=0;j-- {
			if ratings[j] > ratings[j+1] {
				minCandys[j] = max(minCandys[j+1]+1,minCandys[j])
			} else {
				break
			}
		}
		for j := i+1;j < n;j++ {
			if ratings[j] > ratings[j-1] {
				minCandys[j] = max(minCandys[j-1]+1,minCandys[j])
			} else {
				break
			}
		}
	}

	result := 0
	for i := 0 ;i < n;i++ {
		result += minCandys[i]
	}
	return result
}
func max(a,b int) int {
	if a > b {
		return a
	}
	return b
}
func markOne(ratings,minCandys []int){
	n := len(ratings)
	for i := 0 ;i < n ;i++ {
		if i == 0 {
			if ratings[i+1] >= ratings[i] {
				minCandys[i] = 1
			}
			continue
		}
		if i == n-1 {
			if ratings[i] <= ratings[i-1] {
				minCandys[i] = 1
			}
			continue
		}
		if ratings[i+1] >= ratings[i] && ratings[i] <= ratings[i-1] {
			minCandys[i] = 1
		}
	}
}