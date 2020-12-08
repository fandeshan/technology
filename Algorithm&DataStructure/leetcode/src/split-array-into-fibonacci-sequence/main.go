package main

import (
	"fmt"
	"math"
	"strconv"
)

func main() {
	//S:="12345678"
	//S:="1"
	//for i := 1 ; i <= (len(S) +1 )/2 ; i++ {
	//	fmt.Println(S[:i],S[i:])
	//}
	//fmt.Println(splitIntoFibonacci("123456579"))
	//fmt.Println(splitIntoFibonacci("11235813"))
	//fmt.Println(splitIntoFibonacci("112358130"))
	//fmt.Println(splitIntoFibonacci("0123"))
	//fmt.Println(splitIntoFibonacci("10023"))
	//fmt.Println(splitIntoFibonacci("1101111"))
	fmt.Println(splitIntoFibonacci("44913954913380975110687015276688827466494293475165"))
	fmt.Println(splitIntoFibonacci("502113822114324228146342470570616913086148370223967883880490627727810157768164350462659281443027860696206741126485341822692082949177424771869507721046921249291642202139633432706879765292084310"))
	//t1 := time.Now()
	//fmt.Println(splitIntoFibonacci("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"))
	//fmt.Println(time.Since(t1))
}

func splitIntoFibonacci(S string) []int {
	result := []int{}
	var dfs func(string,[]int) bool
	dfs = func (str string,result1 []int) bool {
		//fmt.Println(str,result1)
		if str == "" {
			if len(result1) < 3 {
				return false
			}
			//fmt.Println("-------------------")
			result = make([]int,len(result1))
			copy(result,result1)
			//fmt.Println(result)
			//fmt.Println("-------------------")
			return true
		}
		for i := 1 ; i <= len(str) && i <= 10; i++ {
			if str[0] == '0' && i > 1 {
				break
			}
			curr := stringToInt(str[:i])
			if curr > math.MaxInt32 {
				continue
			}
			if len(result1) > 1 {
				if result1[len(result1)-1] + result1[len(result1)-2] != curr {
					continue
				}
			}
			result1 = append(result1,curr)
			//fmt.Println("++++++",str,result)
			flag := dfs(str[i:],result1)
			if flag {
				return true
			}
			result1 = result1[:len(result1)-1]
		}
		return false
	}
	for i := 1 ; i <= ( len(S) + 1 ) / 2  && i <= 10 ; i++ {
		if S[0] == '0' && i > 1 {
			break
		}
		curr := stringToInt(S[:i])
		if curr > math.MaxInt32 {
			continue
		}
		result = append(result,curr)
		flag := dfs(S[i:],result)
		if flag {
			return result
		}
		result = result[:len(result)-1]
	}

	return nil
}

func stringToInt(str string) int {
	result,_ := strconv.Atoi(str)
	return result
}

