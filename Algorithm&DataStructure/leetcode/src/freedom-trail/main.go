package main

import (
	"fmt"
	"math"
	"time"
)

func main() {
	fmt.Println(findRotateSteps("godding","gd"))
	fmt.Println(findRotateSteps("abcde","ade"))
	fmt.Println(findRotateSteps("abcdefbc","abf"))
	fmt.Println(findRotateSteps("aaaaa","aaaaa"))
	t1 := time.Now()
	//fmt.Println(findRotateSteps("xrrakuulnczywjs","jrlucwzakzussrlckyjjsuwkuarnaluxnyzcnrxxwruyr"))
	fmt.Println(time.Since(t1))
}

func findRotateSteps(ring string, key string) int {

	keyBytes := []byte(key)
	ringBytes := []byte(ring)

	//如果ring的第i位和key的第i位计算过，则把结果存下来
	memoryArr := make([][]int,len(ringBytes))
	for i:=0;i<len(ringBytes) ;i++  {
		memoryArr[i] = make([]int,len(keyBytes))
		for j:=0;j<len(memoryArr[i]) ;j++  {
			memoryArr[i][j] = -1
		}
	}
	ringIndexArr := make([][]int,26)
	for i:=0;i<len(ringBytes) ;i++  {
		j := int(ringBytes[i])-97
		ringIndexArr[j] = append(ringIndexArr[j],i)
	}
	res := reserveWhirling(ringBytes,keyBytes,0,0,ringIndexArr,memoryArr)
	return res
}

func reserveWhirling(ringBytes []byte,keyBytes []byte,ri int,ki int,ringIndexArr [][]int,memoryArr [][]int) int {
	if  ki>=len(keyBytes) {
		return 0
	}
	key0 := keyBytes[ki]
	if memoryArr[ri][ki] != -1 {
		return memoryArr[ri][ki]
	}
	minRes := math.MaxInt32
	for _,ringIndex := range ringIndexArr[int(key0)-97]  {
		leftStep := absReduce(ringIndex, ri)
		rightStep := len(ringBytes) - leftStep
		leftStep = leftStep + 1
		rightStep = rightStep + 1
		minStep := min(leftStep,rightStep)
		minRes = min(minRes,minStep + reserveWhirling(ringBytes,keyBytes,ringIndex,ki+1,ringIndexArr,memoryArr))
	}
	memoryArr[ri][ki] = minRes
	return minRes
}

func min(res ...int) int{
	min := res[0]
	if len(res)<2 {
		return min
	}
	for i:=1;i<len(res) ;i++  {
		if res[i] < min {
			min = res[i]
		}
	}
	return min
}
func absReduce(a int,b int) int{
	if a - b > 0 {
		return a - b
	}
	return b - a
}