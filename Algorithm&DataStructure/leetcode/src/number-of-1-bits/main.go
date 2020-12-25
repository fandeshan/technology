package main

func main() {

}

func hammingWeight(num uint32) int {
	if num == 0 {
		return 0
	}
	curr := int(num&1)
	if num < 0 {
		curr +=1
		num = num & (1<<31 -1)
	}
	return curr + hammingWeight(num >> 1)
}