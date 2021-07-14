package main

import "fmt"

func main() {
	fmt.Println(orchestraLayout(3,0,2))
}

func orchestraLayout(num int, xPos int, yPos int) int {
	x := 0
	y := 0
	curr := 1
	size := num
	for  {
		if xPos == x {

		}
		curr =(curr+size)%9-1
		x = 0
		y = size - 1
		size = size - 1
		curr += (curr+size)%9
		x = size - 1
		y = size - 1
		curr +=
	}
}