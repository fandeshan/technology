package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println(fairCandySwap([]int{1,1},[]int{2,2}))
}
func fairCandySwap(A []int, B []int) []int {
	result := make([]int,2)
	sort.Ints(A)
	sort.Ints(B)
	sumA := 0
	for _,v := range A{
		sumA += v
	}
	sumB := 0
	for _,v := range B{
		sumB += v
	}
	temp := sumA-(sumA+sumB)/2
	for i,j:=0,0;i < len(A)&&j< len(B); {
		if A[i]-B[j] == temp {
			result[0] = A[i]
			result[1] = B[j]
			break
		} else if A[i]-B[j] > temp{
			j++
		} else {
			i++
		}
	}
	return result
}
