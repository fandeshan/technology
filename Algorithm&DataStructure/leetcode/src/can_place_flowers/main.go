package main

import "fmt"

func main() {
	fmt.Println(canPlaceFlowers([]int{1,0,0,0,1},1))
	fmt.Println(canPlaceFlowers([]int{1,0,0,0,1},2))
	fmt.Println(canPlaceFlowers([]int{1,0,0,0,0,1},2))
	fmt.Println(canPlaceFlowers([]int{1,0,0,0,0,0,1},2))
	fmt.Println(canPlaceFlowers([]int{0},1))
}

func canPlaceFlowers(flowerbed []int, n int) bool {
	index := 0
	for index < len(flowerbed) && flowerbed[index] != 1 {
		index ++
	}
	canN := 0
	if index == len(flowerbed) {
		canN = (index+1)/2
	} else {
		canN = index/2
	}

	for i := index +1 ;i < len(flowerbed);i ++ {
		if flowerbed[i] == 0 && flowerbed[i-1] == 0 {
			if i == len(flowerbed) - 1 {
				flowerbed[i] = 1
				canN ++
			} else {
				if flowerbed[i+1] != 1 {
					flowerbed[i] = 1
					canN ++
				}
			}
		}
	}
	return n <= canN
}