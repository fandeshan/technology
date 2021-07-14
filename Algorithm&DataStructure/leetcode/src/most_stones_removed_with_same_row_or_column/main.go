package main

import "fmt"

func main() {
	fmt.Println(removeStones([][]int{{}}))
}

func removeStones(stones [][]int) int {
	n := len(stones)
	uf := Constructor(10001)
	for i := 0;i < n;i++ {
		uf.union(stones[i][0],stones[i][1]+10001)
	}
	rstMap := make(map[int]int)
	for i := 0;i < n;i++ {
		rstMap[uf.find(stones[i][0])] = 1
	}
	return n-len(rstMap)
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

func (this *UnionFind) find(x int) int {
	parentInt,ok := this.father[x]
	if !ok {
		this.father[x] = x
		return x
	}
	for parentInt != this.father[parentInt]  {
		parentInt = this.father[parentInt]
	}
	fatherInt,ok := this.father[x]
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