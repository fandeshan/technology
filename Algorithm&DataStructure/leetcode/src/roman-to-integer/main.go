package main

import "fmt"

func main() {
	//fmt.Println("IV"[0:2])

	fmt.Println(romanToInt("IV"))
	fmt.Println(romanToInt("XIV"))
	fmt.Println(romanToInt("XIII"))
	fmt.Println(romanToInt("MCMXCIV"))
	fmt.Println(romanToInt("MMMCMXCIX"))
}

func romanToInt(s string) int {
	if len(s) < 1 {
		return 0
	}
	romanMap := make(map[string]int)
	romanMap["I"] = 1
	romanMap["IV"] = 4
	romanMap["V"] = 5
	romanMap["IX"] = 9
	romanMap["X"] = 10
	romanMap["XL"] = 40
	romanMap["L"] = 50
	romanMap["XC"] = 90
	romanMap["C"] = 100
	romanMap["CD"] = 400
	romanMap["D"] = 500
	romanMap["CM"] = 900
	romanMap["M"] = 1000
	if len(s) == 1 {
		return romanMap[s]
	}
	result := 0
	i:=0
	for ;i<len(s) -1 ;i ++  {
		if num,ok := romanMap[s[i:i+2]];ok {
			result += num
			i++
		}else{
			result += romanMap[s[i:i+1]]
		}
	}
	if i == len(s) -1 {
		result += romanMap[s[i:i+1]]
	}
	return result
}