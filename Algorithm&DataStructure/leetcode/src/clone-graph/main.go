package main

import "fmt"

type Node struct {
	Val int
	Neighbors []*Node
}
func main() {
	node1 := &Node{
		Val:       1,
		Neighbors: nil,
	}
	node2 := &Node{
		Val:       2,
		Neighbors: nil,
	}
	node3 := &Node{
		Val:       3,
		Neighbors: nil,
	}
	node4 := &Node{
		Val:       3,
		Neighbors: nil,
	}
	node1.Neighbors = []*Node{node2,node4}
	node2.Neighbors = []*Node{node1,node3}
	node3.Neighbors = []*Node{node2,node4}
	node4.Neighbors = []*Node{node1,node3}
	fmt.Println(node1)
	fmt.Println(node1.Neighbors)
	newNode :=cloneGraph(node1)
	fmt.Println(newNode)
	fmt.Println(newNode.Neighbors)
}

/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Neighbors []*Node
 * }
 */

func cloneGraph(node *Node) *Node {
	if node == nil {
		return nil
	}
	listNode := getAllNode(node)
	relationMap := make(map[*Node]*Node)
	for i:=0;i<len(listNode) ;i++  {
		relationMap[listNode[i]] = &Node{
			Val:       listNode[i].Val,
			Neighbors: nil,
		}
	}
	for oldNode,newNode := range relationMap {
		newNodeNeighbors := []*Node{}
		for i:=0;i<len(oldNode.Neighbors) ;i++  {
			newNodeNeighbors = append(newNodeNeighbors,relationMap[oldNode.Neighbors[i]])
		}
		newNode.Neighbors = newNodeNeighbors
		relationMap[oldNode] = newNode
	}
	return relationMap[node]
}
func getAllNode(node *Node) []*Node{
	nodeQueue := []*Node{node}
	nodeMap := make(map[*Node]int)
	nodeMap[node] = 1
	result := []*Node{node}
	for len(nodeQueue) > 0  {
		currNode := nodeQueue[0]
		nodeQueue = nodeQueue[1:]
		for _,neighborNode := range currNode.Neighbors  {
			if _,ok := nodeMap[neighborNode];!ok {
				nodeMap[neighborNode] = 1
				result = append(result,neighborNode)
				nodeQueue = append(nodeQueue,neighborNode)
			}
		}
	}
	return result
}
