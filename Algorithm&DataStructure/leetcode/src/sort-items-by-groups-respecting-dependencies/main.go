package main

import "fmt"

func main() {
	fmt.Println(sortItems(8,2,[]int{-1,-1,1,0,0,1,0,-1},[][]int{{},{6},{5},{6},{3,6},{},{},{}}))
}


func sortItems(n, m int, group []int, beforeItems [][]int) (ans []int) {
	groupItems := make([][]int, m+n)
	for i := range group {
		if group[i] == -1 {
			group[i] = m + i
		}
		groupItems[group[i]] = append(groupItems[group[i]], i)
	}
	groupGraph := make([][]int, m+n)
	groupDegree := make([]int, m+n)
	itemGraph := make([][]int, n)
	itemDegree := make([]int, n)
	for cur, items := range beforeItems {
		curGroupID := group[cur]
		for _, pre := range items {
			preGroupID := group[pre]
			if preGroupID != curGroupID {
				groupGraph[preGroupID] = append(groupGraph[preGroupID], curGroupID)
				groupDegree[curGroupID]++
			} else {
				itemGraph[pre] = append(itemGraph[pre], cur)
				itemDegree[cur]++
			}
		}
	}
	items := make([]int, m+n)
	for i := range items {
		items[i] = i
	}
	groupOrders := topSort(groupGraph, groupDegree, items)
	if len(groupOrders) < len(items) {
		return nil
	}
	for _, groupID := range groupOrders {
		items := groupItems[groupID]
		orders := topSort(itemGraph, itemDegree, items)
		if len(orders) < len(items) {
			return nil
		}
		ans = append(ans, orders...)
	}
	return
}
func topSort(graph [][]int, deg, items []int) (orders []int) {
	q := []int{}
	for _, i := range items {
		if deg[i] == 0 {
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		from := q[0]
		q = q[1:]
		orders = append(orders, from)
		for _, to := range graph[from] {
			deg[to]--
			if deg[to] == 0 {
				q = append(q, to)
			}
		}
	}
	return
}