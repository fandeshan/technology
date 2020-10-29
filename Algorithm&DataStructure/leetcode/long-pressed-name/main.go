package main

import "fmt"

func main() {
	//name := "alex"; typed := "aaleex"
	name := "saeed"
	typed := "ssaaedd"
	//name = "leelee"; typed = "lleeelee"
	//name = "laiden"; typed = "laiden"
	//name ="vtkgn";typed = "vttkgnn"
	//name = ""; typed = " "
	//name ="kikcxmvzi";typed = "kiikcxxmmvvzz"
	//name ="alex";typed = "aaleelx"
	name = "alex"
	typed = "alexxr"
	name = "saeedi"
	typed = "ssaaeediixxxiii"
	fmt.Print(isLongPressedName(name, typed))
}

func isLongPressedName(name string, typed string) bool {
	i, j := 0, 0
	nameArr := []byte(name)
	typedArr := []byte(typed)
	for i < len(name) && j < len(typed) {
		for j < len(typed) && nameArr[i] != typedArr[j] {
			if i < 1 {
				return false
			}
			if nameArr[i-1] != typedArr[j] {
				return false
			}
			j++
		}
		i++
		j++
	}
	if i == len(name) {
		if j > len(typed) {
			return false
		}
		if j == len(typed) && nameArr[i-1] == typedArr[j-1] {
			return true
		}
		for j < len(typed) {
			if nameArr[i-1] != typedArr[j] {
				return false
			}
			j++
		}
		return true
	}
	return false
}
