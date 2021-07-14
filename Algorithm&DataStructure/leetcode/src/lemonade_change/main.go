package main

import (
	"fmt"
)

func main() {
	//fmt.Println(lemonadeChange([]int{5,5,5,10,20}))
	//fmt.Println(lemonadeChange([]int{5,5,10}))
	//fmt.Println(lemonadeChange([]int{10,10}))
	//fmt.Println(lemonadeChange([]int{5,5,10,10,20}))
	fmt.Println(lemonadeChange([]int{5,5,5,10,5,5,10,20,20,20}))
}
func lemonadeChange(bills []int) bool {
	price := 5
	if len(bills) < 1 {
		return true
	}
	cntQueue1 := make([]int,0)
	cntQueue2 := make([]int,0)
	for i := 0; i < len(bills) ; i++ {
		if bills[i] == price {
			cntQueue1 = append(cntQueue1,bills[i])
		} else if bills[i] == 2 * price {
			if len(cntQueue1) < 1 {
				return false
			}
			cntQueue1 = cntQueue1[1:]
			cntQueue2 = append(cntQueue2,bills[i])
		} else {
			lave := bills[i] - price
			if len(cntQueue2) > 0 {
				cntQueue2 = cntQueue2[1:]
				lave -= 2*price
			}
			for j := 0 ; j < lave/price ; j++ {
				if len(cntQueue1) < 1 {
					return false
				}
				cntQueue1 = cntQueue1[1:]
			}
		}
		fmt.Println(cntQueue1)
		fmt.Println(cntQueue2)
	}
	return true
}
