package main

import "fmt"

func main() {
	//cache := Constructor(2)
	//cache.Put(1,1)
	//cache.Put(2,2)
	//fmt.Println(cache.Get(1))
	//cache.Put(3,3)
	//fmt.Println(cache.Get(2))
	//cache.Put(4,4)
	//fmt.Println(cache.Get(1))
	//fmt.Println(cache.Get(3))
	//fmt.Println(cache.Get(4))

	cache := Constructor(2)
	fmt.Println(cache.Get(2))
	cache.Put(2, 6)
	fmt.Println(cache.Get(1))
	cache.Put(1, 5)
	cache.Put(1, 2)
	fmt.Println(cache.Get(1))
	fmt.Println(cache.Get(2))

}

type LRUCache struct {
	Capacity   int
	ValNodeMap map[int]*CacheNode
	Head       *CacheNode
	Tail       *CacheNode
}

type CacheNode struct {
	Key  int
	Val  int
	Pre  *CacheNode
	Next *CacheNode
}

func Constructor(capacity int) LRUCache {
	head := &CacheNode{
		Key:  -1,
		Val:  -1,
		Pre:  nil,
		Next: nil,
	}
	tail := &CacheNode{
		Key:  -1,
		Val:  -1,
		Pre:  nil,
		Next: nil,
	}
	head.Next = tail
	tail.Pre = head
	return LRUCache{
		Capacity:   capacity,
		ValNodeMap: make(map[int]*CacheNode),
		Head:       head,
		Tail:       tail,
	}
}

func (this *LRUCache) Get(key int) int {
	current, ok := this.ValNodeMap[key]
	if !ok {
		return -1
	}
	current.Pre.Next = current.Next
	current.Next.Pre = current.Pre
	this.moveToTail(current)
	return current.Val
}

func (this *LRUCache) Put(key int, value int) {
	if this.Get(key) != -1 {
		this.ValNodeMap[key].Val = value
		return
	}
	if len(this.ValNodeMap) == this.Capacity {
		delete(this.ValNodeMap, this.Head.Next.Key)
		this.Head.Next = this.Head.Next.Next
		this.Head.Next.Pre = this.Head
	}
	insert := &CacheNode{
		Key:  key,
		Val:  value,
		Pre:  nil,
		Next: nil,
	}
	this.moveToTail(insert)
	this.ValNodeMap[key] = insert
}
func (this *LRUCache) moveToTail(curr *CacheNode) {
	curr.Pre = this.Tail.Pre
	this.Tail.Pre = curr
	curr.Pre.Next = curr
	curr.Next = this.Tail
}
