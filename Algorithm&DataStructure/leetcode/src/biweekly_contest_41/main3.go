package main

import "fmt"

func main() {
	fmt.Println(stoneGameVI1([]int{2,4,3},[]int{1,6,7}))
	fmt.Println(stoneGameVI1([]int{1,2},[]int{3,1}))
	fmt.Println(stoneGameVI1([]int{1,3},[]int{2,1}))
}
func stoneGameVI(aliceValues []int, bobValues []int) int {
	n := len(aliceValues)
	hold := make([]bool,n)
	arr := make([][]int,n)
	bobArr := make([][]int,n)
	for i := 0; i < n ; i ++ {
		arr[i] = make([]int,2)
		arr[i][0] = aliceValues[i]
		arr[i][1] = i
		bobArr[i] = make([]int,2)
		bobArr[i][0] = bobValues[i]
		bobArr[i][1] = i
	}
	quickSort(arr)
	quickSort(bobArr)
	indexA := n-1
	indexB := n-1
	scopeA := 0
	scopeB := 0
	for  {
		for indexB >= 0 && hold[arr[indexB][1]] {
			indexB --
		}
		maxB := 0
		if indexB > 0 {
			maxB = bobArr[indexB][0]
		}
		scopeATmp,indexA := takeStone(arr,hold,indexA,maxB,bobValues)
		scopeA +=scopeATmp
		for indexA >= 0 && hold[arr[indexA][1]] {
			indexA --
		}
		if indexA < 0 {
			break
		}
		maxA := arr[indexA][0]
		scopeBtmp,indexB := takeStone(bobArr,hold,indexB,maxA,aliceValues)
		indexB = indexB
		scopeB += scopeBtmp
	}
	fmt.Println(scopeA,scopeB)
	if scopeA > scopeB {
		return 1
	} else if scopeA == scopeB {
		return 0
	}
	return -1
}
func takeStone(arr [][]int,hold []bool,index int,max int,targetValue []int) (int,int){
	for index >= 0 && hold[arr[index][1]] {
		index --
	}
	if index < 0 {
		return 0,index
	}
	if arr[index][0] >= max {
		hold[arr[index][1]] = true
		return arr[index][0],index -1

	} else {
		nextAIndex := index - 1
		if nextAIndex < 0 {
			hold[arr[index][1]] = true
			return arr[index][0],index -1
		} else {
			for nextAIndex > 0 && hold[arr[nextAIndex][1]] {
				nextAIndex --
			}
			if nextAIndex < 0 {
				hold[arr[index][1]] = true
				return arr[index][0],index -1
			} else {
				if arr[index][0] - arr[nextAIndex][0] >= targetValue[arr[nextAIndex][1]] - targetValue[arr[index][1]] {
					hold[arr[index][1]] = true
					return arr[index][0],index -1
				}else {
					hold[arr[nextAIndex][1]] = true
					return arr[nextAIndex][0],index
				}
			}
		}

	}
}
func quickSort(nums [][]int) {
	if len(nums) < 2 {
		return
	}
	i := 0
	j := len(nums) - 1
	base := nums[i][0]
	base1 := nums[i][1]
	for i < j {
		for i < j && nums[j][0] >= base {
			j--
		}
		if i < j {
			nums[i][0] = nums[j][0]
			nums[i][1] = nums[j][1]
			i++
		}

		for i < j && nums[i][0] < base {
			i++
		}
		if i < j {
			nums[j][0] = nums[i][0]
			nums[j][1] = nums[i][1]
			j--
		}
	}
	nums[i][0] = base
	nums[i][1] = base1
	quickSort(nums[:i])
	quickSort(nums[i+1:])
}

func stoneGameVI1(aliceValues []int, bobValues []int) int {
	n := len(aliceValues)
	arr := make([][]int,n)
	for i := 0; i < n ; i ++ {
		arr[i] = make([]int,2)
		arr[i][0] = aliceValues[i] + bobValues[i]
		arr[i][1] = i
	}
	quickSort(arr)
	index := n-1
	scopeA := 0
	scopeB := 0
	aTake := true
	for index >=0 {
		if aTake {
			scopeA += aliceValues[arr[index][1]]
			aTake = false
		} else {
			scopeB += bobValues[arr[index][1]]
			aTake = true
		}
		index --
	}
	fmt.Println(scopeA,scopeB)
	if scopeA > scopeB {
		return 1
	} else if scopeA == scopeB {
		return 0
	}
	return -1
}