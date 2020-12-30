package main

import "fmt"

func main() {
	fmt.Println(cuttingRope(4))
	fmt.Println(cuttingRope(58))
}

func cuttingRope(n int) int {
	if n < 2 {
		return 0
	}
	if n <= 3 {
		return n-1
	}
	if n%3==0 {
		return myPow(3,n/3)
	} else if n%3==1 {
		return myPow(3,n/3-1) * 4
	}
	return myPow(3,n/3)*2
}

func myPow(x int, n int) int {
	negative := false
	if n < 0 {
		negative = true
		n = -n
	}

	result := 1
	for{
		tmp := x
		if n == 0 {
			break
		}
		if n == 1 {
			result = tmp * result
			break
		}

		i := 2
		for ; i <= n; {
			tmp = tmp * tmp
			if i*i > n {
				break
			}
			i = i + i
		}
		result = tmp*result
		n = n-i
	}


	if negative {
		return 1/result
	}
	return result
}