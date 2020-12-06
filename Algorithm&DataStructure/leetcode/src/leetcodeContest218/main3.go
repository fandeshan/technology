package main

import (
	"fmt"
	"math/big"
	"strings"
)

func main() {
	fmt.Println(concatenatedBinary1(3))
	fmt.Println(concatenatedBinary1(12))
	fmt.Println(concatenatedBinary1(42))
	fmt.Println(concatenatedBinary1(100000))
	//fmt.Println("1001">"100")
	//fmt.Println(subBinaryStr("1111111100110101100101000000111"))
}
var modVal int = 1e9+7
func concatenatedBinary(n int) int {
	sb := strings.Builder{}
	for i := 1; i <= n ; i ++ {
		sb.WriteString(fmt.Sprintf("%b",i))
	}
	//bigNums := binaryToBigNums(sb.String())
	//fmt.Println(bigNums.String())
	//return int(bigNums.Mod(&bigNums,big.NewInt(1e9+7)).Int64())
	modValBinary := fmt.Sprintf("%b",modVal)
	lenMod := len(modValBinary)
	//fmt.Println(sb.String())


	if sb.Len() < lenMod {
		return binaryToNums(sb.String())
	}
	result := sb.String()
	for len(result) > lenMod {
		index := lenMod
		if result[0:lenMod] <= modValBinary {
			if lenMod + 1 <= len(result) {
				index ++
			} else {
				break
			}
		}
		//fmt.Println(result[:index])
		//fmt.Println(modValBinary)
		result = subBinaryStr(result[0:index])+result[index:]
		//fmt.Println(result)
		//fmt.Println("----------")
	}
	return binaryToNums(result)

}
func subBinaryStr(a string) string{
	num := binaryToNums(a)
	num -= modVal
	return fmt.Sprintf("%b",num)
}
func binaryToNums(a string) (num int){
	n := len(a)
	for i := n -1 ; i >= 0 ; i-- {
		num += (int(a[n - i - 1]) & 0xf) << uint8(i)
	}
	return
}

func binaryToBigNums(a string) (num big.Int){
	n := len(a)
	for i := n -1 ; i >= 0 ; i-- {
		num.Add(&num,big.NewInt(int64((int(a[n - i - 1]) & 0xf) << uint8(i))))
	}
	return
}

func concatenatedBinary1(n int) int {
	result := 1
	for i := 2; i <= n ; i ++ {
		lenI := len(fmt.Sprintf("%b",i))
		tmp := (result<<lenI) % 1000000007
		result =  tmp + i
	}
	return result
}