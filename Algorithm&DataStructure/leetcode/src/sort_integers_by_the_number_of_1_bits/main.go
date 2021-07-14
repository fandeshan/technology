package main

import "fmt"

func main() {
	fmt.Println(sortByBits([]int{0,1,2,3,4,5,6,7,8,10000-1}))
	fmt.Println(sortByBits([]int{1024,512,256,128,64,32,16,8,4,2,1}))
	fmt.Println(sortByBits([]int{10000,10000}))
	fmt.Println(sortByBits([]int{2,3,5,7,11,13,17,19}))
	fmt.Println(sortByBits([]int{10,100,1000,10000}))
	fmt.Println(sortByBits([]int{8848,4205,113,3764,2376,6352,6372,9927,2990,4286,7783,2121,3749,7800,7479,2723,8305,8276,3598,5776,8016,5053,3113,4395,3595,5079,3065,3158,6141,4187,1328,4900,2120,3239,6474,2218,4892,7993}))
}

func sortByBits(arr []int) []int {
	if len(arr) < 2 {
		return arr
	}
	//最大值为10^4，不超过14个1
	tmpArr := make([][]int,14)
	for i:=0;i<len(arr) ;i++  {
		cnt1 := getCount1(arr[i])
		if tmpArr[cnt1] == nil {
			tmpArr[cnt1] = make([]int,1)
			tmpArr[cnt1][0] = arr[i]
			continue
		}
		pos := len(tmpArr[cnt1])
		for j:=0;j<len(tmpArr[cnt1]);j++  {
			if arr[i] < tmpArr[cnt1][j] {
				pos = j
				break
			}
		}
		tmp := append([]int{arr[i]},tmpArr[cnt1][pos:]...)
		tmpArr[cnt1] = append(tmpArr[cnt1][:pos],tmp...)
	}
	k := 0
	for i:=0;i<len(tmpArr) ;i++  {
		for j:=0;j<len(tmpArr[i]) ;j++  {
			arr[k] = tmpArr[i][j]
			k++
		}
	}
	return arr
}
func getCount1(target int) int{
	cnt :=0
	for target!=0  {
		if target&1 == 1 {
			cnt ++
		}
		target = target >>1
	}
	return cnt
}