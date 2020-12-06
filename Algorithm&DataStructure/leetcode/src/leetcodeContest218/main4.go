package main

import (
	"fmt"
	"math"
	"sort"
)

func main() {
	fmt.Println(minimumIncompatibility2([]int{1,2,1,4},2))
	fmt.Println(minimumIncompatibility2([]int{6,3,8,1,3,1,2,2},4))
	fmt.Println(minimumIncompatibility2([]int{5,3,3,6,3,3},3))
	fmt.Println(minimumIncompatibility2([]int{5,3,2,11,5,8,7,7,6,2,4,5},12))

}

func minimumIncompatibility(nums []int, k int) int {
	sort.Ints(nums)
	cnts := make([]int, len(nums)+1)
	for i := 0 ; i < len(nums) ; i ++ {
		cnts[nums[i]] ++
	}
	result := 0
	for i := 0 ; i < k ; i ++ {
		index := 0
		min := len(nums)+1
		max := 0
		tmpSubLen := 0
		tmpcnt1 := make([]int,len(cnts))
		copy(tmpcnt1,cnts)
		for j := 0 ; j < len(nums)/k;j++ {
			for index < len(tmpcnt1) && tmpcnt1[index] == 0 {
				index ++
			}
			if index >= len(tmpcnt1) {
				break
			}
			tmpSubLen ++
			if max < index {
				max = index
			}
			if min > index {
				min = index
			}
			tmpcnt1[index] --
			index ++
		}
		if tmpSubLen < len(nums)/k {
			return -1
		}
		minValue1 := max - min
		fmt.Println(max,min)
		index = len(cnts) -1
		min = len(nums)+1
		max = 0
		tmpcnt2 := make([]int,len(cnts))
		copy(tmpcnt2,cnts)
		for j := 0 ; j < len(nums)/k;j++ {
			for index >= 0 && tmpcnt2[index] == 0 {
				index --
			}
			if index < 0 {
				break
			}
			if max < index {
				max = index
			}
			if min > index {
				min = index
			}
			tmpcnt2[index] --
			index --
		}
		fmt.Println(max,min)
		minValue2 := max - min
		if minValue2 < minValue1 {
			cnts = tmpcnt2
			result += minValue2
		} else {
			cnts = tmpcnt1
			result += minValue1
		}
		fmt.Println(cnts)
	}
	return result
}


func minimumIncompatibility1(nums []int, k int) int {
	cnts := make([]int, len(nums)+1)
	for i := 0 ; i < len(nums) ; i ++ {
		cnts[nums[i]] ++
	}
	result := 0
	subLen := len(nums)/k
	for  {
		subTotalLen := 0
		for i := 0; i < len(cnts); i++ {
			if cnts[i] > 0 {
				subTotalLen ++
			}
		}
		if subTotalLen == 0 {
			break
		} else if subTotalLen < subLen {
			return -1
		}
		minResult := len(cnts)-1
		minCnts := cnts
		//fmt.Println(subTotalLen,subLen)
		for i := 0; i <= subTotalLen-subLen; i++ {
			tmpCnts := make([]int,len(cnts))
			copy(tmpCnts,cnts)
			index := 0
			for j := 0;j <= i;j++ {
				for tmpCnts[index] == 0 {
					index ++
				}
				tmpCnts[index] --
				index ++
			}
			minIndex := index - 1
			maxIndex := minIndex
			for j := 1;j < subLen;j++ {
				for tmpCnts[index] == 0 {
					index ++
				}
				tmpCnts[index] --
				index ++
			}
			maxIndex = index - 1
			if maxIndex - minIndex < minResult {
				fmt.Println(maxIndex,minIndex)
				minResult = maxIndex - minIndex
				minCnts = tmpCnts
			}
		}
		result += minResult
		cnts = minCnts
	}
	return result
}
func minimumIncompatibility2(nums []int, k int) int {
	cnts := make([]int, len(nums)+1)
	for i := 0 ; i < len(nums) ; i ++ {
		cnts[nums[i]] ++
	}
	cntsNew := [][]int{}
	for i := 0 ; i < len(cnts) ; i ++ {
		if cnts[i] > 0 {
			cntsNew = append(cntsNew,[]int{i,cnts[i]})
		}
	}
	//fmt.Println(cntsNew)
	minResult := math.MaxInt32
	var dfs func([][]int ,int,int,int)
	dfs = func (cnts [][]int,subLen int,result int,k int)  {
		if len(cnts) == 0 {
			if k > 0 {
				return
			}
			if minResult > result {
				minResult = result
			}
			return
		}
		if len(cnts) < subLen {
			return
		}
		currMin := math.MaxInt32
		for i := 0 ; i <= len(cnts) - subLen ; i ++ {
			if currMin == math.MaxInt32 {
				currMin = result + cnts[subLen+i-1][0] - cnts[i][0]
			}
			if result + cnts[subLen+i-1][0] - cnts[i][0] < currMin {
				currMin = result + cnts[subLen+i-1][0] - cnts[i][0]
			}
			if result + cnts[subLen+i-1][0] - cnts[i][0] > currMin {
				continue
			}
			cntsNew := make([][]int,len(cnts))
			for j := 0 ; j < len(cnts);j++ {
				cntsNew[j] = make([]int,len(cnts[j]))
				copy(cntsNew[j],cnts[j])
			}
			for j := i;j < subLen + i;j++ {
				cntsNew[j][1]--
				//if cntsNew[j][1] == 0 {
				//	tmp := cntsNew[:j]
				//	fmt.Println(tmp)
				//	cntsNew = append(tmp,cntsNew[j+1:]...)
				//	j --
				//}
			}
			//fmt.Println(cntsNew)
			index := 0
			for j := 0;j < len(cntsNew);j++ {
				cntsNew[index] = cntsNew[j]
				if cntsNew[j][1] > 0 {
					index ++
				}

			}
			cntsNew = cntsNew[:index]
			fmt.Println(cntsNew,result)
			dfs(cntsNew,subLen,result + cnts[subLen+i-1][0] - cnts[i][0],k - 1)

		}
	}
	if len(nums)/k == 1 {
		return 0
	}
	dfs(cntsNew,len(nums)/k,0,k)
	if minResult == math.MaxInt32 {
		return -1
	}
	return minResult
}

//var minResult =100000
//func dfs(cnts [][]int,subLen int,result int,k int)  {
//	if len(cnts) == 0 {
//		if k > 0 {
//			return
//		}
//		fmt.Println(result)
//		if minResult > result {
//			minResult = result
//		}
//		return
//	}
//	if len(cnts) < subLen {
//		return
//	}
//	for i := 0 ; i <= len(cnts) - subLen ; i ++ {
//		cntsNew := make([][]int,len(cnts))
//		for j := 0 ; j < len(cnts);j++ {
//			cntsNew[j] = make([]int,len(cnts[j]))
//			copy(cntsNew[j],cnts[j])
//		}
//		for j := i;j < subLen + i;j++ {
//			cntsNew[j][1]--
//			//if cntsNew[j][1] == 0 {
//			//	tmp := cntsNew[:j]
//			//	fmt.Println(tmp)
//			//	cntsNew = append(tmp,cntsNew[j+1:]...)
//			//	j --
//			//}
//		}
//		//fmt.Println(cntsNew)
//		index := 0
//		for j := 0;j < len(cntsNew);j++ {
//			cntsNew[index] = cntsNew[j]
//			if cntsNew[j][1] > 0 {
//				index ++
//			}
//
//		}
//		cntsNew = cntsNew[:index]
//		//fmt.Println(cntsNew,result)
//		dfs(cntsNew,subLen,result + cnts[subLen+i-1][0] - cnts[i][0],k - 1)
//
//	}
//}