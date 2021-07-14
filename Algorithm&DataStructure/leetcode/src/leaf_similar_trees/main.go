package main

import "fmt"

type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}

func main() {

}

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func leafSimilar(root1 *TreeNode, root2 *TreeNode) bool {
	root1Arr := []int{}
	root2Arr := []int{}
	var travelTree func (node *TreeNode,arr *[]int)
	travelTree =  func (node *TreeNode,arr *[]int) {
		if node == nil {
			return
		}
		if node.Left == nil && node.Right == nil {
			*arr = append(*arr,node.Val)
		}
		travelTree(node.Left,arr)
		travelTree(node.Right,arr)
	}
	travelTree(root1,&root1Arr)
	travelTree(root2,&root2Arr)
	fmt.Println(root1Arr)
	fmt.Println(root2Arr)
	return checkSimilar(root1Arr,root2Arr)
}
func checkSimilar(arr1 []int,arr2 []int) bool {
	if len(arr1) != len(arr2){
		return false
	}
	for i :=0;i < len(arr1);i++ {
		if arr1[i]!=arr2[i] {
			return false
		}
	}
	return true
}