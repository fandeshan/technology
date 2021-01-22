package main

import "fmt"

func main() {
	fmt.Println(addToArrayForm([]int{1,2,0,0},34))
	fmt.Println(addToArrayForm([]int{1,2},3400))
	fmt.Println(addToArrayForm([]int{9,9,9,9,9,9,9,9,9,9},1))
	fmt.Println(addToArrayForm([]int{2,7,4},181))
	fmt.Println(addToArrayForm([]int{2,1,5},806))
}

func addToArrayForm(A []int, K int) []int {
	if len(A) == 0 {
		arr := make([]int,0)
		for K > 9 {
			arr = append([]int{K%10},arr...)
			K /=10
		}
		if K%10 > 0 {
			arr = append([]int{K%10},arr...)
		}
		return arr
	}
	sum := A[len(A)-1]+(K%10)
	nextK :=K/10
	if  sum > 9 {
		sum%=10
		nextK +=1
	}
	return append(addToArrayForm(A[:len(A)-1],nextK),sum)
}