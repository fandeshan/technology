package main

func main() {
	
}


type MinStack struct {
	arr []int
	minArr []int
}


/** initialize your data structure here. */
func Constructor() MinStack {
	return MinStack{
		arr:    make([]int,0),
		minArr: make([]int,0),
	}
}


func (this *MinStack) Push(x int)  {
	this.arr = append(this.arr,x)
	if len(this.minArr)<1 || x < this.minArr[len(this.minArr)-1] {
		this.minArr = append(this.minArr,x)
	}else{
		this.minArr = append(this.minArr,this.minArr[len(this.minArr)-1])
	}
}


func (this *MinStack) Pop()  {
	this.arr = this.arr[:len(this.arr)-1]
	this.minArr = this.minArr[:len(this.minArr)-1]
}


func (this *MinStack) Top() int {
	return this.arr[len(this.arr)-1]
}


func (this *MinStack) Min() int {
	return this.minArr[len(this.minArr)-1]
}


/**
 * Your MinStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(x);
 * obj.Pop();
 * param_3 := obj.Top();
 * param_4 := obj.Min();
 */