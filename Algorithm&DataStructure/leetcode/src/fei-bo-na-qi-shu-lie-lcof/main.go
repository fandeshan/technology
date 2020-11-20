package main

import (
	"fmt"
	"math/big"
)

func main() {
	fmt.Println(fib(95))
}

func fib(n int) int {
	if n < 1 {
		return 0
	}
	if n < 3 {
		return 1
	}
	result := [3]*big.Int{big.NewInt(1),big.NewInt(1),big.NewInt(1)}
	big.NewInt(1).Int64()
	for i := 2;i < n ;i ++  {
		result[i%3] = result[i%3].Add(result[(i-1)%3],result[(i-2)%3])
	}
	fmt.Println(result)
	return int(result[(n-1)%3].Mod(result[(n-1)%3],big.NewInt(1000000007)).Int64())
}