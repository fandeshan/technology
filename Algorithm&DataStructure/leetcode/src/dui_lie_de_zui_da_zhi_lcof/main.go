package main


func main() {

}

type MaxQueue struct {
	nums []int
	maxIndex []int
}


func Constructor() MaxQueue {
	return MaxQueue{
		nums: make([]int,0),
		maxIndex: make([]int,0),
	}
}


func (this *MaxQueue) Max_value() int {
	if len(this.nums) < 1 || len(this.maxIndex) < 1 {
		return -1
	}
	return this.maxIndex[0]
}


func (this *MaxQueue) Push_back(value int)  {
	this.nums = append(this.nums,value)
	for len(this.maxIndex) > 0 && value > this.maxIndex[len(this.maxIndex)-1] {
		this.maxIndex = this.maxIndex[:len(this.maxIndex)-1]
	}
	this.maxIndex = append(this.maxIndex,value)
}


func (this *MaxQueue) Pop_front() int {
	if len(this.nums) < 1 {
		return -1
	}
	value := this.nums[0]
	if value == this.maxIndex[0] {
		this.maxIndex = this.maxIndex[1:]
	}
	this.nums = this.nums[1:]
	return value
}


/**
 * Your MaxQueue object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Max_value();
 * obj.Push_back(value);
 * param_3 := obj.Pop_front();
 */