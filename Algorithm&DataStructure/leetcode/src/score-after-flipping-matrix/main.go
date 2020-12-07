package main

import "fmt"

func main() {
	fmt.Println(matrixScore([][]int{{0,0,1,1},{1,0,1,0},{1,1,0,0}}))
}

func matrixScore(A [][]int) int {
	if len(A) < 1 {
		return 0
	}
	for{
		move1 := false
		for i := 0; i < len(A);i ++ {
			if A[i][0]  == 1 {
				continue
			}
			newAI := moveArr(A[i])
			move1 = true
			A[i] = newAI
			//if !compareArr(A[i],newAI) {
			//	move1 = true
			//	A[i] = newAI
			//}
		}
		move2 := false
		for j := 0; j < len(A[0]);j ++ {
			//if A[0][j]  == 1 {
			//	continue
			//}
			sum := 0
			for i := 0; i < len(A);i ++ {
				sum += A[i][j]
			}
			if sum*2 < len(A) {
				move2 = true
				for i := 0; i < len(A);i ++ {
					A[i][j]= abs(A[i][j]-1)
				}
			}
		}
		if (!move1)&&(!move2) {
			break
		}
	}
	result := 0
	for i := 0; i < len(A);i ++ {
		result += binaryToNums(A[i])
	}
	return result
}
func binaryToNums(binaryArr []int) (num int){
	n := len(binaryArr)
	for i := n -1 ; i >= 0 ; i-- {
		num += (binaryArr[n - i - 1] & 0xf) << uint8(i)
	}
	return
}
func moveArr(origin []int) []int{
	result := make([]int,len(origin))
	for i := 0; i < len(origin) ;i ++ {
		result[i] = abs(origin[i]-1)
	}
	return result
}
func abs(a int) int {
	if a < 0 {
		a = -a
	}
	return a
}
func compareArr(origin []int,new []int) bool{
	for i := 0; i < len(origin) && i < len(new);i ++ {
		if origin[i] == new[i] {
			continue
		}
		return origin[i] > new[i]
	}
	return len(origin) >= len(new)
}