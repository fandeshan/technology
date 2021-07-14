package main

import "fmt"

func main() {
	fmt.Println(canCompleteCircuit([]int{1,2,3,4,5},[]int{3,4,5,1,2}))
	fmt.Println(canCompleteCircuit([]int{2,3,4},[]int{3,4,3}))
	fmt.Println(canCompleteCircuit([]int{5,1,2,3,4},[]int{4,4,1,5,1}))
	fmt.Println(canCompleteCircuit([]int{5,8,2,8},[]int{6,5,6,6}))
	fmt.Println(canCompleteCircuit([]int{1},[]int{6}))
	fmt.Println(canCompleteCircuit([]int{6},[]int{1}))
}

func canCompleteCircuit1(gas []int, cost []int) int {
	lenGas := len(gas)
	if lenGas < 1 || lenGas != len(cost){
		return -1
	}
	subArr := make([]int,lenGas)
	totalSub := 0
	for i:=0;i<lenGas ;i++  {
		sub := gas[i] - cost[i]
		totalSub += sub
		subArr[i] = sub
	}
	//如果总相减都小于0，肯定不满足
	if totalSub < 0 {
		return -1
	}
	//找 相减为负数开始的
	//startIndex := lenGas
	//for i:=0;i<lenGas ;i++  {
	//	if subArr[i] < 0 {
	//		startIndex = i
	//		break
	//	}
	//}
	////没有找到相减负数的，那随便那一个点都满足了
	//if startIndex == lenGas {
	//	return 0
	//}
	//fmt.Println(subArr,startIndex)
	//从负数开始，找到最正数和最长子串
	//k := 0
	//maxSubSum := 0
	//maxSubIndex := -1
	//for i := 0; i < lenGas ;i ++  {
	//	if subArr[startIndex%lenGas] >= 0 {
	//		k ++
	//	} else {
	//		subSum := 0
	//		for j := 0;j < k ;j ++  {
	//			subSum += subArr[(startIndex -j-1)%lenGas]
	//		}
	//		if subSum > maxSubSum {
	//			maxSubSum = subSum
	//			maxSubIndex = (startIndex -k)%lenGas
	//			k = 0
	//		}
	//	}
	//	startIndex ++
	//}
	//subSum := 0
	//for j := 0;j < k ;j ++  {
	//	subSum += subArr[(startIndex -j-1)%lenGas]
	//}
	//if subSum > maxSubSum {
	//	maxSubSum = subSum
	//	maxSubIndex = (startIndex -k)%lenGas
	//	k = 0
	//}
	//checkIndex := maxSubIndex
	//checkSum := 0
	//for i:=0;i<lenGas ;i++  {
	//	checkSum += subArr[checkIndex%lenGas]
	//	if checkSum < 0 {
	//		return -1
	//	}
	//	checkIndex ++
	//}
	i :=0
	for ;i<lenGas ;i++  {
		checkIndex := i
		checkSum := 0
		canRound := true
		for j:=0;j<lenGas ;j++  {
			checkSum += subArr[checkIndex%lenGas]
			if checkSum < 0 {
				canRound = false
				break
			}
			checkIndex ++
		}
		if canRound {
			return i
		}
	}
	return -1
}


func canCompleteCircuit(gas []int, cost []int) int {
	lenGas := len(gas)
	if lenGas < 1 || lenGas != len(cost){
		return -1
	}
	back := lenGas
	front := lenGas
	totalSub := gas[0] - cost[0]
	for front - back < lenGas - 1  {
		if totalSub < 0 {
			back --
			totalSub += gas[back%lenGas] - cost[back%lenGas]
		} else {
			front ++
			totalSub += gas[front%lenGas] - cost[front%lenGas]
		}
	}
	if totalSub < 0 {
		back --
		totalSub += gas[back%lenGas] - cost[back%lenGas]
		if totalSub < 0 {
			return -1
		}else {
			return back%lenGas
		}
	} else {
		front ++
		totalSub += gas[front%lenGas] - cost[front%lenGas]
		if totalSub < 0 {
			return -1
		}else {
			return back%lenGas
		}
	}

	return -1
}