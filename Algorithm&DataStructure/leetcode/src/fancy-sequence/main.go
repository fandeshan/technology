package main

import "fmt"

func main() {
	fancy := Constructor()
	fancy.Append(2)
	fancy.AddAll(3)
	fancy.Append(7)
	fancy.MultAll(2)
	fmt.Println(fancy.GetIndex(0))
	fancy.AddAll(3)
	fancy.Append(10)
	fancy.MultAll(2)
	fmt.Println(fancy.GetIndex(0))
	fmt.Println(fancy.GetIndex(1))
	fmt.Println(fancy.GetIndex(2))

}

type Fancy struct {
	arr []int
}

func Constructor() Fancy {
	return Fancy{arr: make([]int, 0)}
}

func (this *Fancy) Append(val int) {
	this.arr = append(this.arr, val)
}

func (this *Fancy) AddAll(inc int) {
	for i, v := range this.arr {
		this.arr[i] = v + inc
	}
}

func (this *Fancy) MultAll(m int) {
	for i, v := range this.arr {
		this.arr[i] = v * m
	}
}

func (this *Fancy) GetIndex(idx int) int {
	if idx >= len(this.arr) {
		return -1
	}
	return this.arr[idx]
}
