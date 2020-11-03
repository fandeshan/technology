package main

import "fmt"

func main() {
	fmt.Println(validMountainArray([]int{3,5,5}))
	fmt.Println(validMountainArray([]int{0,3,2,1}))
	fmt.Println(validMountainArray([]int{}))
	fmt.Println(validMountainArray([]int{0,3,2,1,4}))
	fmt.Println(validMountainArray([]int{0,1,2}))
	fmt.Println(validMountainArray([]int{3,2,1}))
}

func validMountainArray(A []int) bool {
	if len(A) < 3 {
		return false
	}
	up := true
	down := false
	for i := 1;i < len(A) ;i++  {
		if A[i] > A[i-1]  {
			if up {
				continue
			}else{
				return false
			}
		}else if A[i] < A[i-1]  {
			if up && i>1 {
				up = false
				down = true
			}
			if down {
				continue
			} else {
				return false
			}
		}else{
			return false
		}
	}
	if !up && down {
		return true
	}
	return false
}