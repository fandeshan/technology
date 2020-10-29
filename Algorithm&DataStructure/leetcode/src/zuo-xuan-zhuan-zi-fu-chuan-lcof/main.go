package main

import "fmt"

func main() {
	s := "abcdefg"
	k := 6
	fmt.Println(reverseLeftWords(s, k))
}

func reverseLeftWords(s string, n int) string {
	if len(s) < 1 || n > len(s) {
		return s
	}
	sArr := []byte(s)
	tmp := append([]byte{}, sArr[:n]...)
	return string(append(sArr[n:], tmp...))
}
