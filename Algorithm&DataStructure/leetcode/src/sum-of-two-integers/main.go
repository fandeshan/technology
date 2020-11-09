package main

import "fmt"

func main() {
	fmt.Printf("%b",-1)
	fmt.Printf("%x",-1)
}

func getSum(a int, b int) int {
	sum :=0
	//nextAdd := false
	for a !=0 && b != 0  {
		a1 := a & 1
		b1 := b & 1
		if a1 & b1 == 1 {
			//nextAdd = true
		}else if a1 | b1 == 1 {

		}

	}
	return sum
}