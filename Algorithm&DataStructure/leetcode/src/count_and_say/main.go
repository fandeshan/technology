package main

import (
	"fmt"
	"strconv"
)

func main() {
	for i :=2 ; i <= 30 ; i ++ {
		fmt.Println(countAndSay(i))
	}

}

func countAndSay(n int) string {
	str := "1"
	if n == 1 {
		return str
	}
	for i :=2 ; i <= n ; i ++ {
		cnt := 0
		base := str[0]
		newStr := ""
		for j := 0 ; j < len(str); j++ {
			if str[j] == base {
				cnt++
			}else{
				newStr += strconv.Itoa(cnt)+string(base)
				base = str[j]
				cnt = 1
			}
		}
		newStr += strconv.Itoa(cnt)+string(base)
		str = newStr
	}
	return str
}