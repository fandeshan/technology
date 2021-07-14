package main

import "fmt"

func main() {
	//fmt.Println(reorganizeString("aaaaaabbbcccdd"))
	//fmt.Println(reorganizeString("aab"))
	//fmt.Println(reorganizeString("aabb"))
	//fmt.Println(reorganizeString("aaab"))
	//fmt.Println(reorganizeString("aa"))
	fmt.Println(reorganizeString("todrnphcamnomskfrhe"))
}

func reorganizeString(S string) string {
	if len(S) < 2 {
		return S
	}
	n := 26
	cnts := make([][]int,n)
	for i := 0;i< n ;i++  {
		cnts[i] = make([]int,2)
	}
	for i := 0;i < len(S) ;i++  {
		cnts[S[i]-'a'][0] ++
		cnts[S[i]-'a'][1] =  int(S[i]-'a')
	}

	quickSort(cnts,0,len(cnts)-1)
	//for i := 0;i < n ; i++  {
	//	if cnts[i][0] > 0 {
	//		fmt.Printf("%d,%c \n",cnts[i][0],'a'+cnts[i][1])
	//	}
	//}
	sum := 0
	for i := 1;i < len(cnts) ;i++  {
		sum += cnts[i][0]
	}
	if cnts[0][0] > sum +1 {
		return ""
	}
	rst := ""
	index := 1
	i := 0
	for ;i < cnts[0][0] ;i++  {
		if cnts[index][0] == 0 {
			index ++
			if index == n || cnts[index][0] == 0 {
				break
			}
		}
		rst += string(cnts[0][1]+'a')
		rst += string(cnts[index][1]+'a')
		cnts[index][0]--
	}
	if index == n {
		if i < cnts[0][0] {
			rst += string(cnts[0][1]+'a')
		}
		return rst
	}
	if cnts[index][0] == 0 && index < n - 1 {
		if cnts[index + 1][0] != 0 {
			index ++
		}else{
			if i < cnts[0][0] {
				rst += string(cnts[0][1]+'a')
			}
			return rst
		}

	}
	if cnts[index][0] != 0 {
		for index < n  &&cnts[index][0] != 0  {
			s0 := rst[0:1]
			rst =  rst[1:] + s0 + string(cnts[index][1]+'a')
			cnts[index][0] --
			if cnts[index][0] == 0 {
				index ++
			}
		}
	}
	return rst
}

func quickSort(nums [][]int,start int,end int) {
	if end <= start {
		return
	}
	left := start
	right := end
	base := nums[start][0]
	for left <= right  {
		for left <= right && nums[left][0] > base  {
			left ++
		}
		for left <= right && nums[right][0] < base  {
			right --
		}
		if left <= right {
			nums[right],nums[left] = nums[left],nums[right]
			right --
			left ++
		}

	}
	quickSort(nums,start,right)
	quickSort(nums,left,end)
}