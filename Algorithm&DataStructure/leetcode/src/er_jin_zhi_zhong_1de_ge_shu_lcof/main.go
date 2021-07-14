package main

import (
	"fmt"
)

func main() {
	//tmp := 1<<31-1
	//fmt.Printf("%b,%d\n",tmp,tmp)
	fmt.Println(hammingWeight(1))
}

func hammingWeight(num uint32) int {
	if num == 0 {
		return 0
	}
	curr := int(num&1)
	if num < 0 {
		curr +=1
		num = num & (1<<31 -1)
	}
	return curr + hammingWeight(num >> 1)
}