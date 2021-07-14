package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println(replaceSpace("We are happy."))
}

func replaceSpace1(s string) string {
	return strings.ReplaceAll(s," ","%20")
}
func replaceSpace(s string) string {
	bytes := []byte(s)
	for i := 0;i<len(bytes) ;i++  {
		if bytes[i] == ' ' {
			tmp :=append([]byte{},bytes[:i]...)
			tmp =append(tmp,'%','2','0')
			bytes = append(tmp,bytes[i+1:]...)
			i = i+2
		}
	}
	return string(bytes)
}