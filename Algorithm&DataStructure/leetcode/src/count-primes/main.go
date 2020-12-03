package main

import (
	"fmt"
	"math"
	"time"
)

func main() {
	//fmt.Println(countPrimes(10))
	//fmt.Println(countPrimes(0))
	//fmt.Println(countPrimes(1))
	t1 := time.Now()
	fmt.Println(countPrimes(5000000))
	fmt.Println(time.Since(t1))
	t2 := time.Now()
	fmt.Println(countPrimes1(5000000))
	fmt.Println(time.Since(t2))
}

func countPrimes(n int) int {
	if n < 2 {
		return 0
	}
	result := 1
	for i := 3;i <= n ; i+=2  {
		m := int(math.Sqrt(float64(i)))
		isPrimes := true
		for j := 2;j <= m ; j++  {
			if i % j == 0  {
				isPrimes = false
				break
			}
		}
		if isPrimes {
			//fmt.Print(i," ")
			result ++
		}
	}
	//fmt.Println()
	return result
}

func countPrimes1(n int) int {
	if n < 2 {
		return 0
	}
	resultArr := make([]bool,n+1)
	resultArr[0],resultArr[1] = true,true
	//m := int(math.Sqrt(float64(n)))
	result := 0
	for i := 2 ;i <= n ;i++  {
		if resultArr[i] {
			continue
		}
		result ++
		for j := i; j*i <= n ;j++  {
			resultArr[j*i] = true
		}

	}

	//for i := 0;i <n ;i++  {
	//	if !resultArr[i] {
	//		result ++
	//	}
	//}
	return result
}