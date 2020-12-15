package main

import "fmt"

func main() {
	fmt.Println(numberOfMatches(7))
	fmt.Println(numberOfMatches(14))
}

func numberOfMatches(n int) int {
	if n == 0 {
		return 0
	}
	if n == 1 {
		return 0
	}
	if n % 2 == 0 {
		return n/2 + numberOfMatches(n/2)
	} else {
		return n/2 + numberOfMatches(n/2 + 1)
	}
}