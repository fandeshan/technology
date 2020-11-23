package main

import (
	"fmt"
	"math/big"
)

func main() {
	fmt.Println(numSub("0110111"))
	fmt.Println(numSub("111111"))
	fmt.Println(numSub("000"))
}

func numSub(s string) int {
	if len(s) < 1 {
		return 0
	}
	cntOne := 0
	sumedMap := make(map[int]*big.Int)
	total := big.NewInt(0)
	for i := 0; i < len(s) ; i++  {
		if s[i] == '1' {
			cntOne ++
		} else {
			total.Add(total,addSum(cntOne,sumedMap))
			cntOne = 0
		}
	}
	total.Add(total,addSum(cntOne,sumedMap))
	return int(total.Mod(total,big.NewInt(1000000007)).Int64())
}
func addSum(n int,sumedMap map[int]*big.Int) *big.Int {
	if n  < 2 {
		return big.NewInt(int64(n))
	}
	if sumed,ok := sumedMap[n];ok {
		return sumed
	}
	bigN := big.NewInt(int64(n))
	result := bigN.Add(bigN,addSum(n - 1,sumedMap))
	sumedMap[n] = result
	return result
}