package main

import "fmt"

func main() {
	fmt.Println(findRedundantConnection([][]int{{1,2},{1,3},{2,3}}))
	fmt.Println(findRedundantConnection([][]int{{1,4},{3,4},{1,3},{1,2},{4,5}}))
}

func findRedundantConnection(edges [][]int) []int {
	n := len(edges)
	uf := Constructor(n)
	result := make([]int,2)
	for i := 0;i < n;i++ {
		if !uf.union(edges[i][0],edges[i][1]) {
			result = edges[i]
		}
	}
	return result
}

type UnionFind struct {
	father []int
}

func Constructor(n int) UnionFind {
	unionFind := UnionFind{
		father: make([]int,n+1),
	}
	for i := 0;i< n+1 ;i++  {
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

func (this *UnionFind) union(x int,y int) bool {
	fatherY := this.find(y)
	fatherX := this.find(x)
	if fatherY == fatherX {
		return false
	}
	this.father[fatherX] = fatherY
	return true
}