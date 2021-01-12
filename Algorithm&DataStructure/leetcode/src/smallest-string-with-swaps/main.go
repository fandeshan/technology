package main

import (
	"fmt"
	"sort"
)

func main() {
	fmt.Println(smallestStringWithSwaps1("dcab",[][]int{{0,3},{1,2}}))
	fmt.Println(smallestStringWithSwaps1("dcab",[][]int{{0,3},{1,2},{0,2}}))
}


func smallestStringWithSwaps(s string, pairs [][]int) string {
	if len(pairs) < 1 {
		return s
	}
	pairsMapArr := make([]map[int]int,1)
	pairsMapArr[0] = make(map[int]int)
	pairsMapArr[0][pairs[0][0]] = 1
	pairsMapArr[0][pairs[0][1]] = 1

	for i := 1; i < len(pairs); i++ {
		matched := false
		lastMatchedIndex := -1
		for j := 0 ;j < len(pairsMapArr);j++ {
			_,ok1 := pairsMapArr[j][pairs[i][0]]
			_,ok2 := pairsMapArr[j][pairs[i][1]]
			if ok1 || ok2 {
				if matched {
					for k,_ := range pairsMapArr[j] {
						pairsMapArr[lastMatchedIndex][k] = 1
						delete(pairsMapArr[j],k)
					}
				} else {
					matched = true
					lastMatchedIndex = j
					pairsMapArr[j][pairs[i][0]] = 1
					pairsMapArr[j][pairs[i][1]] = 1
				}
			}
		}
		if !matched {
			tmpMap := make(map[int]int)
			tmpMap[pairs[i][0]] = 1
			tmpMap[pairs[i][1]] = 1
			pairsMapArr = append(pairsMapArr,tmpMap)
		}
	}
	//fmt.Println(pairsMapArr)
	byteS := []byte(s)
	for i :=0 ;i < len(pairsMapArr);i++ {
		tmpN := len(pairsMapArr[i])
		if tmpN < 1 {
			continue
		}
		sortIndex := make([]int,tmpN)
		index := 0
		for k,_ := range pairsMapArr[i] {
			sortIndex[index] = k
			index ++
		}
		sort.Ints(sortIndex)
		selfQuickSort(byteS,sortIndex,0,tmpN-1)


	}
	return string(byteS)
}

func selfQuickSort(bytes []byte,sortIndex []int,start int,end int){
	if start >= end {
		return
	}

	left := start
	right := end
	base := bytes[sortIndex[start]]
	for left <= right {
		for left <= right && bytes[sortIndex[left]] < base {
			left ++
		}
		for left <= right && bytes[sortIndex[right]] > base {
			right --
		}
		if left <= right {

			bytes[sortIndex[left]],bytes[sortIndex[right]] = bytes[sortIndex[right]],bytes[sortIndex[left]]

			left ++
			right --
		}
	}
	selfQuickSort(bytes,sortIndex,start,right)
	selfQuickSort(bytes,sortIndex,left,end)
}
func smallestStringWithSwaps1(s string, pairs [][]int) string {
	n := len(s)
	strArr := make([][]byte,100010)
	cnts := make([]int,100010)
	uf := Constructor(n)
	for _,pairOne := range pairs{
		uf.union(pairOne[1],pairOne[0])
	}
	for i := 0;i < n; i++ {
		indexS := uf.find(i)
		strArr[indexS]  =  append(strArr[indexS],s[i])
	}
	for i := 0;i < n; i++ {
		quickSort(strArr[i],0,len(strArr[i])-1)
	}
	bytes := []byte(s)
	for i := 0;i < n; i++ {
		f := uf.find(i)
		bytes[i] = strArr[uf.findCurrFather(i)][cnts[f]]
		cnts[f]++
	}
	return string(bytes)
}
type UnionFind struct {
	father [100010]int
}

func Constructor(n int) UnionFind {
	unionFind := UnionFind{}
	for i := 0;i<n ;i++  {
		unionFind.father[i] = i
	}
	return unionFind
}

func (this *UnionFind) find(x int) int {
	if this.father[x] == x {
		return x
	} else {
		this.father[x] = this.find(this.father[x])
		return this.father[x]
	}

}

func (this *UnionFind) findCurrFather(x int) int {
	return this.father[x]

}

func (this *UnionFind) union(x int,y int) {
	this.father[this.find(x)] = this.find(y)
}

func quickSort(bytes []byte,start int,end int){
	if start >= end {
		return
	}
	left := start
	right := end
	base := bytes[start]
	for left <= right {
		for left <= right && bytes[left] < base {
			left ++
		}
		for left <= right && bytes[right] > base {
			right --
		}
		if left <= right {
			bytes[left],bytes[right] = bytes[right],bytes[left]
			left ++
			right --
		}
	}
	quickSort(bytes,start,right)
	quickSort(bytes,left,end)
}