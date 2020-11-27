package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println(fourSum([]int{1, 0, -1, 0, -2, 2},0))
	fmt.Println(fourSum([]int{-2,-1,-1,1,1,2,2},0))
}
func fourSum(nums []int, target int) [][]int {
	result := make([][]int,0)
	if len(nums) < 4 {
		return result
	}
	twoSumMap := make(map[int][][]int,len(nums)*(len(nums) -1 ))
	for i := 0;i < len(nums) ;i++  {
		for j := i +1; j < len(nums) ;j++  {
			tmpSum := nums[i]+ nums[j]
			if arr,ok := twoSumMap[tmpSum];ok{
				finded := false
				for k := 0;k < len(arr) ;k++  {
					if arr[k][0] == nums[i] || arr[k][1] == nums[i] {
						finded = true
						break
					}
				}
				if !finded {
					twoSumMap[tmpSum] = append(arr,[]int{nums[i],nums[j],i,j})
				}
			}else {
				twoSumMap[tmpSum] = [][]int{{nums[i],nums[j],i,j}}
			}
		}
	}
	//lastTwoSumMap := make(map[int][][]int,len(nums)*(len(nums) -1 ))
	for i := 2;i < len(nums) ;i++  {
		for j := i +1; j < len(nums) ;j++  {
			tmpSum := nums[i]+ nums[j]
			if vab,ok :=twoSumMap[target-tmpSum];ok {
				for l :=0;l < len(vab) ;l ++  {
					if vab[l][2] == i || vab[l][3] == i || vab[l][2] == j|| vab[l][3] == j {
						continue
					}
					tmp := []int{vab[l][0],vab[l][1],nums[i],nums[j]}
					sort.Ints(tmp)
					added := true
					for k := 0; k < len(result) ;k++  {
						if result[k][0] == tmp[0] && result[k][1] == tmp[1] && result[k][2] == tmp[2] && result[k][3] == tmp[3] {
							added = false
							break
						}
					}
					if added {
						result = append(result,tmp)
					}
				}

			}


			//if arr,ok := lastTwoSumMap[tmpSum];ok{
			//	finded := false
			//	for k := 0;k < len(arr) ;k++  {
			//		if arr[k][0] == nums[i] || arr[k][1] == nums[i] {
			//			finded = true
			//			break
			//		}
			//	}
			//	if !finded {
			//		lastTwoSumMap[tmpSum] = append(arr,[]int{nums[i],nums[j],i,j})
			//	}
			//}else {
			//	lastTwoSumMap[tmpSum] = [][]int{{nums[i],nums[j],i,j}}
			//}
		}
	}
	//for kab,vab := range twoSumMap {
	//	kcd := target-kab
	//	if vcd,ok :=lastTwoSumMap[kcd];ok {
	//		for i :=0 ; i < len(vab) ;i++  {
	//			for j :=0 ; j < len(vcd) ;j++  {
	//				if vab[i][2] == vcd[j][2] || vab[i][3] == vcd[j][2] || vab[i][2] == vcd[j][3] || vab[i][3] == vcd[j][3] {
	//					continue
	//				}
	//				if kcd == kab {
	//					if vab[i][0] != vcd[j][0] && vab[i][0] != vcd[j][1] {
	//						result = append(result,append(vab[i][:2],vcd[j][:2]...))
	//					}
	//				}else{
	//					result = append(result,append(vab[i][:2],vcd[j][:2]...))
	//				}
	//
	//			}
	//		}
	//	}
	//}
	return result
}
