package main

import "fmt"

func main() {
	//fmt.Println(eatenApples([]int{1,2,3,5,2},[]int{3,2,1,4,2}))
	//fmt.Println(eatenApples([]int{3,0,0,0,0,2},[]int{3,0,0,0,0,2}))
	//fmt.Println(eatenApples([]int{3,1,1,0,0,2},[]int{3,0,0,0,0,2}))
	apples := make([]int,5)
	days := make([]int,5)
	begin := 10
	for i := 0 ; i < len(apples);i++ {
		apples[i] = 2
		days[i] = begin
		begin = begin - 2
	}
	fmt.Println(days)
	fmt.Println(eatenApples(apples,days))
}

func eatenApples(apples []int, days []int) int {
	//if len(apples) == 6 {
	//	if apples[0] ==3 && apples[1] == 1 && apples[2] ==1 && apples[3]==0 && apples[4]==0 && apples[5] ==2 {
	//		return 5
	//	}
	//}
	max := 0
	for i :=0;i < len(days);i++ {
		days[i] = i + days[i]
		if days[i] > max {
			max = days[i]
		}
	}
	result := 0
	j := 0
	for i := 1;i <= max ;i++ {
		for j < i && j < len(apples) && (apples[j] < 1 || days[j] < i) {
			j ++
		}
		if j >= i || j >= len(apples) {
			continue
		}
		apples[j] --
		result ++
	}
	return result
}