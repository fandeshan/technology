package main

import (
	"fmt"
	"time"
)

func main() {
	fmt.Println(partition2("aab"))
	fmt.Println(partition2("aabbac"))
	fmt.Println(partition2("aaaaa"))
	fmt.Println(partition2("seeslaveidemonstrateyetartsnomedievalsees"))
}

func partition(s string) [][]string {
	n := len(s)
	if n < 1 {
		return nil
	}
	if n == 1 {
		return [][]string{{s}}
	}
	partArr := make([][]bool,n)

	for i := 0; i < n ; i++  {
		partArr[i] = make([]bool,n)
		partArr[i][i] = true
		if i < n -1 && s[i+1] == s[i] {
			partArr[i][i+1] = true
		}
	}
	for i := 2; i < n ; i++  {
		for j := 0;j < n -i ; j ++  {
			if s[j] == s[j + i] && partArr[j +1][j + i -1] {
				partArr[j][j + i] = true
			}
		}
	}
	queue := make([][]int,0)
	for i := n - 1;i >= 0 ;i --  {
		for j := 0;j < n - i ; j++  {
			if partArr[j][j+i] {
				queue = append(queue,[]int{j,j+i})
			}
		}
	}
	result := make([][]string,0)
	for i := 0; i < len(queue) ; i++  {
		markArr := make([]bool,n)
		singleQueue := [][]int{queue[i]}
		for j := queue[i][0];j <= queue[i][1] ; j++  {
			markArr[j] = true
		}
		markString(i,queue,markArr,singleQueue,&result,s)
	}
	return result
}

func markString(pos int,queue [][]int,markArr []bool,singleQueue [][]int,result *[][]string,s string)  {
	j := 0
	for ;j < len(s) ; j ++  {
		if !markArr[j] {
			break
		}
	}
	if j == len(s) {
		singleRet := make([]string,0)
		dstQueue := make([][]int,len(singleQueue))
		copy(dstQueue,singleQueue)
		quickSort(dstQueue,0,len(singleQueue)-1)
		for i := 0;i < len(dstQueue) ;i++  {
			singleRet = append(singleRet,s[dstQueue[i][0]:dstQueue[i][1]+1])
		}
		*result = append(*result,singleRet)
		return
	}
	for i := pos + 1; i < len(queue) ; i++  {
		if markArr[queue[i][0]] || markArr[queue[i][1]]{
			continue
		}
		singleQueue = append(singleQueue,queue[i])
		for j := queue[i][0];j <= queue[i][1] ; j++  {
			markArr[j] = true
		}
		markString(i,queue,markArr,singleQueue,result,s)
		singleQueue = singleQueue[:len(singleQueue) -1]
		for j := queue[i][0];j <= queue[i][1] ; j++  {
			markArr[j] = false
		}
	}
}

func quickSort(nums [][]int,start int,end int)  {
	if end <= start {
		return
	}
	left := start
	right := end
	base := nums[start][1]
	for left <= right  {
		for left <= right && nums[left][1] < base  {
			left ++
		}
		for left <= right && nums[right][1] > base  {
			right --
		}
		if left <= right {
			nums[left],nums[right] = nums[right],nums[left]
			left ++
			right --
		}
	}
	quickSort(nums,start,right)
	quickSort(nums,left,end)
}

func partition2(s string) [][]string {
	t1 := time.Now()
	n := len(s)
	if n < 1 {
		return nil
	}
	if n == 1 {
		return [][]string{{s}}
	}
	partArr := make([][]bool,n)

	for i := 0; i < n ; i++  {
		partArr[i] = make([]bool,n)
		partArr[i][i] = true
		if i < n -1 && s[i+1] == s[i] {
			partArr[i][i+1] = true
		}
	}
	for i := 2; i < n ; i++  {
		for j := 0;j < n -i ; j ++  {
			if s[j] == s[j + i] && partArr[j +1][j + i -1] {
				partArr[j][j + i] = true
			}
		}
	}
	queue := make([][]int,0)
	for i := n - 1;i >= 0 ;i --  {
		for j := 0;j < n - i ; j++  {
			if partArr[j][j+i] {
				queue = append(queue,[]int{j,j+i})
			}
		}
	}
	subMap := make(map[int][][]int)
	for i := 0; i < len(queue) ; i++  {
		if tmp,ok := subMap[queue[i][0]];ok {
			subMap[queue[i][0]] = append(tmp,queue[i])
		}else {
			subMap[queue[i][0]] = [][]int{queue[i]}
		}
	}
	//quickSort(queue,0,len(queue)-1)
	//fmt.Println(queue)
	fmt.Println(time.Since(t1))
	result := make([][]string,0)
	singleRet := make([]string,0)
	dfs(0,subMap,singleRet,&result,s)
	//for i := 0; i < len(queue) ; i++  {
	//	markArr := make([]bool,n)
	//	singleQueue := []string{s[queue[i][0]:queue[i][1]+1]}
	//	for j := queue[i][0];j <= queue[i][1] ; j++  {
	//		markArr[j] = true
	//	}
	//	markString2(i,queue,markArr,singleQueue,&result,s)
	//}
	return result
}
func dfs(start int,subMap map[int][][]int,singleRet []string,result *[][]string,s string)  {
	if start == len(s) {
		tmpSingle := make([]string,len(singleRet))
		copy(tmpSingle,singleRet)
		*result = append(*result,tmpSingle)
		return
	}
	tmp,ok := subMap[start]
	if !ok || len(tmp) < 1  {
		return
	}
	for i := 0; i < len(tmp) ;i++  {
		end := tmp[i][1] +1
		singleRet = append(singleRet,s[start:end])
		dfs(end,subMap,singleRet,result,s)
		singleRet = singleRet[:len(singleRet) -1]
	}
}


func markString2(pos int,queue [][]int,markArr []bool,singleRet []string,result *[][]string,s string)  {
	j := 0
	for ;j < len(s) ; j ++  {
		if !markArr[j] {
			break
		}
	}
	if j == len(s) {
		*result = append(*result,singleRet)
		return
	}
	for i := pos + 1; i < len(queue) ; i++  {
		if markArr[queue[i][0]] || markArr[queue[i][1]]{
			continue
		}
		singleRet = append(singleRet,s[queue[i][0]:queue[i][1]+1])
		for j := queue[i][0];j <= queue[i][1] ; j++  {
			markArr[j] = true
		}
		markString2(i,queue,markArr,singleRet,result,s)
		singleRet = singleRet[:len(singleRet) -1]
		for j := queue[i][0];j <= queue[i][1] ; j++  {
			markArr[j] = false
		}
	}
}
