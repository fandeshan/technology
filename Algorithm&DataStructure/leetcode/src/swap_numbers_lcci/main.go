package main

import "fmt"

func main() {
	fmt.Println(swapNumbers([]int{1,2}))
}
func swapNumbers(numbers []int) []int {
	/**
	异或原理:
	第一步、a ^= b
	去掉了 a和b的比特位的交集，此时a为原来a仅有的那部分和原来b仅有的那部分
	第二步 b ^= a
	去掉了 新a的 与b的比特位的交际，即去掉了原来b仅有的那部分（b异或原来b那部分则那部分被去掉），
	增加了原来a和b共有的部分（第一次a^b去掉了交际，在异或一次交际又回到本身，即交际又回来了）
	则此时 a∩b + （a∪b-b），则b已经为a
	第三步 a^= b
	此时注意a为新a，实际上为原始 a∪b，b为a
	同理第二步 知道 最总a 为 (a∪b-a) + a∩b = 原来b，
	 */
	numbers[0]= numbers[0]^numbers[1]
	numbers[1]= numbers[1]^numbers[0]
	numbers[0]= numbers[0]^numbers[1]
	return numbers
}

func swapNumbers1(numbers []int) []int {
	numbers[0],numbers[1] = numbers[1],numbers[0]
	return numbers
}