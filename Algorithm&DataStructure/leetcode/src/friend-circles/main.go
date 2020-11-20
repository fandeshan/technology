package main

import "fmt"

func main() {
	fmt.Println(findCircleNum([][]int{{1,1,0},{1,1,0},{0,0,1}}))
	fmt.Println(findCircleNum([][]int{{1,1,0},{1,1,1},{0,1,1}}))
}

type UnionFind struct {
	father map[int]int
}

func Constructor(n int) UnionFind {
	unionFind := UnionFind{father:make(map[int]int)}
	for i := 0;i<n ;i++  {
		unionFind.father[i] = i
	}
	return unionFind
}

func (this *UnionFind) find(uniqueId int) int {
	parentInt,ok := this.father[uniqueId]
	if !ok {
		this.father[uniqueId] = uniqueId
		return uniqueId
	}
	for parentInt != this.father[parentInt]  {
		parentInt = this.father[parentInt]
	}
	fatherInt,ok := this.father[uniqueId]
	for fatherInt != this.father[fatherInt]  {
		tmp := this.father[fatherInt]
		this.father[fatherInt] = parentInt
		fatherInt = tmp
	}
	return parentInt
}

func (this *UnionFind) union(x int,y int) {
	parent_X := this.find(x)
	parent_Y := this.find(y)
	if parent_X != parent_Y {
		this.father[parent_Y] = parent_X
	}
}

func (this *UnionFind) genUniqueId(x  int,y int,col int)  int{
	return x * col + y
}

func findCircleNum(M [][]int) int {
	if len(M) < 1 {
		return 0
	}
	unionFind := Constructor(len(M))
	for i:=0;i<len(M) ;i++  {
		for j := i+1;j<len(M[i]) ;j++  {
			if M[i][j] == 1 {
				unionFind.union(i,j)
			}
		}
	}
	resultMap := make(map[int]int)
	for i := 0; i < len(M) ;i++  {
		resultMap[unionFind.find(i)] = 1
	}
	return len(resultMap)
}