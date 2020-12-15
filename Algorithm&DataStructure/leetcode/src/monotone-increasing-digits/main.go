package main

import (
	"fmt"
	"strconv"
)

func main() {
	fmt.Println(monotoneIncreasingDigits(332))
	fmt.Println(monotoneIncreasingDigits(10))
	fmt.Println(monotoneIncreasingDigits(1234))
	fmt.Println(monotoneIncreasingDigits(34444442))
}
func monotoneIncreasingDigits(N int) int {
	if N < 10 {
		return N
	}
	str := strconv.Itoa(N)
	byteStr := []byte(str)
	modifyBytes(byteStr,1)
	result,_ := strconv.Atoi(string(byteStr))
	return result
}
func modifyBytes(byteStr []byte,start int){
	if start >= len(byteStr) {
		return
	}
	if byteStr[start] < byteStr[start-1] {
		byteStr[start-1] = byteStr[start-1]-1
		index := start - 1
		for index > 0 && byteStr[index] < byteStr[index -1]{
			byteStr[index] = '9'
			byteStr[index -1] = byteStr[index -1] - 1
			index --
		}
		for i := start;i < len(byteStr);i ++ {
			byteStr[i] = '9'
		}
	} else {
		modifyBytes(byteStr,start+1)
	}
}