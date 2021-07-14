package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println(predictPartyVictory("RDD"))
	fmt.Println(predictPartyVictory("RD"))
}
var RADIANT byte = 'R'
var DIRE byte = 'D'
var Rstr string = "Radiant"
var Dstr string = "Dire"
func predictPartyVictory(senate string) string {
	//campBytes := []byte{'R','D'}
	//campStrs := []string{"Radiant","Dire"}
	//n := len(senate)
	//stackR := []int{}
	//stackD := []int{}
	//if senate[n-1] == RADIANT {
	//	stackR = append(stackR,n-1)
	//} else {
	//	stackR = append(stackR,n-1)
	//}
	//if len(senate) < 2 {
	//	if len(stackR) > 0 {
	//		return Rstr
	//	}
	//	return Dstr
	//}

	for  {
		senateBytes := []byte(senate)
		for i := 0 ; i < len(senateBytes); i++ {
			if senateBytes[i] == ' ' {
				continue
			}
			indexDiff := findFirstDiff(senateBytes,i,senate[i])
			if indexDiff == -1 {
				if senate[i] == 'R' {
					return "Radiant"
				}
				return "Dire"
			}
			senateBytes[indexDiff] = ' '
		}
		senate = strings.ReplaceAll(string(senateBytes)," ","")
	}

}
func findFirstDiff(senateBytes []byte,index int,camp byte) int {
	j :=index
	for i := 0 ;i < len(senateBytes) ; i++ {
		if senateBytes[j] != camp && senateBytes[j] != ' ' {
			return j
		}
		j ++
		if j >= len(senateBytes) {
			j = 0
		}
	}
	return -1
}
