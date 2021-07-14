package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println(reformatNumber("123 4-567"))

	fmt.Println(reformatNumber("123 4-5678"))
	fmt.Println(reformatNumber("1-23-45 6"))
}
func reformatNumber(number string) string {
	number = strings.ReplaceAll(number,"-","")
	number = strings.ReplaceAll(number," ","")
	resultByte := make([]byte,0)
	n := len(number)
	for i :=0;i < n-3 ;i++ {
		resultByte = append(resultByte,number[i])
		if i%3 == 2 {
			resultByte = append(resultByte,'-')
		}
	}
	if n%3 != 0 {
		resultByte = append(resultByte,number[n-3],'-',number[n-2],number[n-1])
	} else {
		resultByte = append(resultByte,number[n-3],number[n-2],number[n-1])
	}
	return string(resultByte)
}