package main

import "fmt"

func main() {
	fmt.Println(sortedSquares([]int{-4, -1, 0, 3, 10}))
	fmt.Println(sortedSquares([]int{-7, -3, 2, 3, 11}))
}

func sortedSquares(A []int) []int {
	orginLen := len(A)
	for i := 0; i < orginLen; i++ {
		result := A[i] * A[i]
		appendFlag := false
		for j := orginLen; j < len(A); j++ {
			if result <= A[j] {
				tmp1 := append([]int{}, A[j:]...)
				A = append(A[:j], result)
				A = append(A, tmp1...)
				appendFlag = true
				break
			}
		}
		if !appendFlag {
			A = append(A, result)
		}
	}
	return A[orginLen:]
}
