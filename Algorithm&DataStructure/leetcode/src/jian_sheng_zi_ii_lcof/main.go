package main

import "fmt"

func main() {
	fmt.Println(cuttingRope(4))
	fmt.Println(cuttingRope(5))
	fmt.Println(cuttingRope(10))
}

func cuttingRope(n int) int {
	if n < 2 {
		return 0
	}
	if n <= 3 {
		return n-1
	}
	mod := int(1e9+7)
	result := 1
	for n > 4 {
		result = (result*3)%mod
		n -= 3
	}
	if n > 0 {
		result = (result * n)%mod
	}
	return result

}
