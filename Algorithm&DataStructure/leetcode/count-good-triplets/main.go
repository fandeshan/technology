package main

import (
	"fmt"
	"math"
)

func main() {
	//arr := []int{3,0,1,1,9,7}
	//a := 7
	//b := 2
	//c := 3
	arr := []int{1, 1, 2, 2, 3}
	a := 0
	b := 0
	c := 1
	fmt.Println(countGoodTriplets(arr, a, b, c))
}

func countGoodTriplets(arr []int, a int, b int, c int) int {
	result := 0
	lenArr := len(arr)
	for i := 0; i < lenArr; i++ {
		for j := i + 1; j < lenArr; j++ {
			for k := j + 1; k < lenArr; k++ {
				if math.Abs(float64(arr[i]-arr[j])) <= float64(a) && math.Abs(float64(arr[j]-arr[k])) <= float64(b) && math.Abs(float64(arr[i]-arr[k])) <= float64(c) {
					result++
				}
			}
		}
	}
	return result
}
