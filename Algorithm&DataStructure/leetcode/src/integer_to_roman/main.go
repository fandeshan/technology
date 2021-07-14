package main

import "fmt"

func main() {
	fmt.Println(intToRoman(3999))
	fmt.Println(intToRoman(1994))
}

func intToRoman(num int) string {
	if num/1000 > 3{
		return ""
	}
	I := []string{"","I","II","III","IV","V","VI","VII","VIII","IX"}
	X := []string{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"}
	C := []string{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"}
	M := []string{"","M","MM","MMM"}

	return M[num/1000] + C[num%1000/100] + X[num%100/10] + I[num%10]
}