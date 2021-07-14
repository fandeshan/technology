package main

import "fmt"

func main() {
	fmt.Println(isPalindrome(121))
	fmt.Println(isPalindrome(122))
}
func isPalindrome(x int) bool {
	if x < 0 {
		return false
	}
	if x > 0 && x < 10 {
		return true
	}
	tmp := x
	arr := []int{}
	for tmp > 0  {
		arr = append(arr,tmp%10)
		tmp=tmp/10
	}
	i := 0
	j := len(arr) -1
	for i < j  {
		if arr[i]!=arr[j] {
			return false
		}
		i ++
		j --
	}
	return true
}