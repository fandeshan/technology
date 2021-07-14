package main

import (
	"fmt"
	"strconv"
	"strings"
)

type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}

func (node *TreeNode) print()  {
	if node == nil {
		fmt.Print("nil ->")
		return
	}
	fmt.Print(node.Val,"->")
	node.Left.print()
	node.Right.print()
}

func main() {
	ser := Constructor();
	deser := Constructor();
	//root := &TreeNode{
	//	Val:   1,
	//	Left:  &TreeNode{
	//		Val:   2,
	//		Left:  nil,
	//		Right: nil,
	//	},
	//	Right: &TreeNode{
	//		Val:   3,
	//		Left:  &TreeNode{
	//			Val:   4,
	//			Left:  nil,
	//			Right: nil,
	//		},
	//		Right: &TreeNode{
	//			Val:   5,
	//			Left:  nil,
	//			Right: nil,
	//		},
	//	},
	//}
	root := &TreeNode{
		Val:   1,
		Left:  &TreeNode{
			Val:   2,
			Left:  nil,
			Right: nil,
		},
		Right: nil,
	}
	data := ser.serialize(root);
	fmt.Println(data)
	ans := deser.deserialize(data);
	ans.print()

}

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

type Codec struct {

}

func Constructor() Codec {
	return Codec{}
}

// Serializes a tree to a single string.
func (this *Codec) serialize(root *TreeNode) string {
	if root == nil {
		return ""
	}
	queue := []*TreeNode{root}
	for i:=0;i<len(queue) ;i++  {
		node := queue[i]
		if node == nil {
			continue
		}

		queue = append(queue,node.Left)
		queue = append(queue,node.Right)
	}
	for queue[len(queue)-1] == nil  {
		queue = queue[:len(queue)-1]
	}
	result :=  strconv.Itoa(queue[0].Val)
	for i:=1;i<len(queue) ;i++  {
		if queue[i] == nil {
			result = result + ",@"
		} else {
			result = result + "," + strconv.Itoa(queue[i].Val)
		}
	}
	return result
}

// Deserializes your encoded data to tree.
func (this *Codec) deserialize(data string) *TreeNode {
	if  "" == data {
		return nil
	}
	dataArr := strings.Split(data,",")
	ifLeft := true
	rootVal,_ := strconv.Atoi(dataArr[0])
	root := &TreeNode{
		Val:   rootVal,
		Left:  nil,
		Right: nil,
	}
	queue := []*TreeNode{root}
	index := 0
	for i := 1;i< len(dataArr) ;i++  {
		if dataArr[i] != "@" {
			nodeVal,_ := strconv.Atoi(dataArr[i])
			node := &TreeNode{
				Val:   nodeVal,
				Left:  nil,
				Right: nil,
			}
			if ifLeft {
				queue[index].Left = node
			}else {
				queue[index].Right = node
			}
			queue = append(queue,node)
		}

		if !ifLeft {
			index ++
		}

		ifLeft = !ifLeft
	}
	return queue[0]
}


/**
 * Your Codec object will be instantiated and called as such:
 * ser := Constructor();
 * deser := Constructor();
 * data := ser.serialize(root);
 * ans := deser.deserialize(data);
 */
