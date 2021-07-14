package main

import (
	"fmt"
	"sort"
)

func main() {
	//a := []int{0, 1, 2, 3, 4}
	////删除第i个元素
	//i := 4
	//a = append(a[:i], a[i+1:]...)
	//fmt.Println(a)
	fmt.Println(reconstructQueue([][]int{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}}))
	fmt.Println(reconstructQueue1([][]int{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}}))
}

func reconstructQueue(people [][]int) [][]int {
	if len(people) < 2 {
		return people
	}
	quickSort(people,0,len(people)-1)
	newPeople := make([][]int,len(people))
	fillPeople(people,newPeople)
	return  newPeople
}
func fillPeople(odlPeople [][]int,newPeople [][]int) {
	if len(odlPeople) < 1{
		return
	}
	canFill(newPeople,odlPeople[0][0],odlPeople[0][1])
	fillPeople(odlPeople[1:],newPeople)
}
func canFill(newPeople [][]int,tall int,rank int){
	pos := rank
	for pos < len(newPeople)  {
		if newPeople[pos] != nil {
			pos ++
			continue
		}
		cnt := 0
		for i:=0;i<pos ;i++  {
			if newPeople[i] == nil {
				cnt ++
				continue
			}
			if newPeople[i][0] >= tall {
				cnt ++
			}
		}
		if cnt >= rank {
			newPeople[pos] = make([]int,2)
			newPeople[pos][0] = tall
			newPeople[pos][1] = rank
			break
		}
		pos ++
	}

}
func quickSort(nums [][]int,start int,end int) {
	if end <= start {
		return
	}
	left := start
	right := end
	base := nums[start][0]
	for left <= right  {
		for left <= right && nums[left][0] < base  {
			left ++
		}
		for left <= right && nums[right][0] > base  {
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

func reconstructQueue1(people [][]int) [][]int {
	sort.Slice(people,func(i,j int) bool {
		p,q := people[i],people[j]
		return p[0] > q[0] || p[0] == q[0] && p[1] < q[1]
	})
	fmt.Println(people)
	for i,_ := range people {
		temp := people[i]
		index := people[i][1]
		for j := i; j > index; j-- {
			people[j] = people[j - 1]
		}
		people[index] = temp
	}
	return people
}
