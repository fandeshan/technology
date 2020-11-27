package main

import (
	"fmt"
	"math"
	"strings"
)

func main() {
	fmt.Println(myAtoi("   4193 with words   "))
	fmt.Println(myAtoi("   -42"))
	fmt.Println(myAtoi("42"))
	fmt.Println(myAtoi("words and 987"))
	fmt.Println(myAtoi("-91283472332"))
	fmt.Println(myAtoi("-10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000522545459"))
	fmt.Println(myAtoi("-000000000000000001522545459"))
	fmt.Println(myAtoi("000000"))
}

func myAtoi(s string) int {
	if len(s) < 1 {
		return 0
	}
	s = strings.Trim(s," ")
	if len(s) < 1 {
		return 0
	}
	nagetive := false
	if s[0] == '-' {
		nagetive = true
		s = s[1:]
	} else if s[0] == '+' {
		s = s[1:]
	} else if s[0]<'0'||s[0]>'9' {
		return 0
	}
	for len(s)>0 && s[0] == '0'  {
		s = s[1:]
	}

	for i:=0;i<len(s) ;i++  {
		if s[i] < '0' || s[i] > '9' {
			s = s[:i]
		}
	}
	if len(s) > 10 {
		if nagetive {
			return math.MinInt32
		}else {
			return math.MaxInt32
		}
	}
	radio := 1
	result := 0
	for i := len(s) -1;i >=0 ;i--  {
		if nagetive {
			result -= radio*int(s[i]-'0')
			if result < math.MinInt32 {
				return math.MinInt32
			}
		} else {
			result += radio*int(s[i]-'0')
			if result > math.MaxInt32 {
				return math.MaxInt32
			}
		}
		radio *=10
	}
	return result
}