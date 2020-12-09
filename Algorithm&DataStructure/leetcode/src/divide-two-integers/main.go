package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(divide(10,3))
	fmt.Println(divide(7,-3))
	fmt.Println(divide(-2147483648,-1))
}

func divide(dividend int, divisor int) int {
	posv := true
	if (dividend < 0 && divisor > 0) || ( dividend > 0 && divisor < 0 )  {
		posv = false
	}
	if dividend < 0 {
		dividend = -dividend
	}
	if divisor < 0 {
		divisor = -divisor
	}
	if dividend < divisor {
		return 0
	}
	tmp := divisor
	res := 1
	for tmp + tmp <= dividend {
		res = res + res
		tmp = tmp + tmp
	}
	res = res + divide(dividend-tmp,divisor)
	if posv {
		return catchBound(res)
	}
	return catchBound(-res)

}
func catchBound(res int) int{
	if res > math.MaxInt32 {
		return math.MaxInt32
	}
	if res < math.MinInt32 {
		return math.MinInt32
	}
	return res
}
func divide1(dividend int, divisor int) int {
	result := dividend/divisor
	if result > math.MaxInt32 {
		return math.MaxInt32
	}
	return result
}