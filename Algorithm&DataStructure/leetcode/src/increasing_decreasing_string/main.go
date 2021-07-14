package main

import "fmt"

func main() {
	fmt.Println(sortString("aaaabbbbcccc"))
	fmt.Println(sortString("rat"))
	fmt.Println(sortString("leetcode"))
	fmt.Println(sortString("ggggggg"))
	fmt.Println(sortString("spo"))
}

func sortString(s string) string {
	if len(s) < 2 {
		return s
	}
	cnts := make([]int,26)
	for i := 0; i < len(s) ;i++  {
		cnts[s[i] - 'a'] ++
	}
	result := ""
	for   {
		result1 := roundOne(cnts)
		if result1 =="" {
			break
		}
		result += result1
		result2 := roundTwo(cnts)
		if result2 =="" {
			break
		}
		result += result2
	}

	return result
}
func roundOne(cnts []int) string{
	result := ""
	for i:=0;i <len(cnts) ;i++  {
		if cnts[i] > 0 {
			cnts[i] --
			result += string('a' + i)
		}
	}
	return result
}
func roundTwo(cnts []int) string{
	result := ""
	for i:=len(cnts) - 1;i >= 0 ;i--  {
		if cnts[i] > 0 {
			cnts[i] --
			result += string('a' + i)
		}
	}
	return result
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