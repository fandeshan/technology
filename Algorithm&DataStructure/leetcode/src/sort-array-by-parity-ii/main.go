package main

import "fmt"

func main() {
	fmt.Println(sortArrayByParityII([]int{4,2,5,7}))
}

func sortArrayByParityII(A []int) []int {
	oddIndex := 1
	evenIndex := len(A) - 2
	for oddIndex <= len(A)-1 && oddIndex >= 0  {
		if  A[oddIndex] % 2 == 1 {
			oddIndex += 2
			continue
		}
		if A[evenIndex] % 2 == 0 {
			evenIndex -= 2
			continue
		}
		A[oddIndex],A[evenIndex] = A[evenIndex],A[oddIndex]
	}
	return A
}