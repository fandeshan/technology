package main

import (
	"fmt"
	"math/big"
)

func main() {
	fmt.Println(findTheDifference("abcd","abcde"))
	fmt.Println(findTheDifference("","y"))
	fmt.Println(findTheDifference("a","aa"))
	fmt.Println(findTheDifference("ae","aea"))
}

func findTheDifference(s string, t string) byte {
	var primeList = []int{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101}
	multiVal := big.NewInt(1)
	for i := 0 ; i < len(s) ; i++ {
		multiVal = multiVal.Mul(multiVal,big.NewInt(int64(primeList[s[i]-'a'])))

	}
	for i := 0 ; i < len(t) ; i++ {
		tiBig := big.NewInt(int64(primeList[t[i]-'a']))
		multiVal,modVal := multiVal.DivMod(multiVal,tiBig,tiBig)
		multiVal = multiVal
		if modVal.Int64() != 0 {
			return t[i]
		}
	}
	return t[0]
}