package main

func main() {

}

func numEquivDominoPairs(dominoes [][]int) int {
	tmpMap := make(map[int]int)
	for i := 0 ; i < len(dominoes) ; i++ {
		if dominoes[i][0] > dominoes[i][1] {
			tmpMap[dominoes[i][1]*10+dominoes[i][0]] ++
		} else {
			tmpMap[dominoes[i][0]*10+dominoes[i][1]] ++
		}
	}
	sum := 0
	for _,v := range tmpMap{
		sum += nAddTo1(v-1)
	}
	return sum
}
func nAddTo1(n int) (rst int){
	if n < 1 {
		return 0
	}
	rst = 1
	for i :=2;i <= n ;i++ {
		rst += i
	}
	return rst
}