package main

import (
	"fmt"
	"math/big"
)

func main() {
	fmt.Println(prefixesDivBy5([]int{0,1,1}))
	fmt.Println(prefixesDivBy5([]int{0,1,1,1,1,1}))
}

func prefixesDivBy5(A []int) []bool {
	n := len(A)
	result := make([]bool,n)
	bi := big.NewInt(0)
	for i := 0 ;i < n ; i ++ {
		bi = bi.Lsh(bi,1)
		bi = bi.Add(bi,big.NewInt(int64(A[i])))
		if bi.Mod(bi,big.NewInt(5)).Int64() == 0 {
			result[i] = true
		}
	}
	return result
}