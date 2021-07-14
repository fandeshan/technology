package main

import "fmt"

func main() {
	//s,t:="ab#c","ad#c"
	//s,t:="ab##","c#d#"
	//s,t:="a##c","#a#c"
	s, t := "a#c", "b"
	fmt.Println(backspaceCompare(s, t))
}

func backspaceCompare(S string, T string) bool {
	return doBackSpace(S) == doBackSpace(T)
}

func doBackSpace(orgin string) string {
	byteS := []byte(orgin)
	for i := 0; i < len(byteS); i++ {
		if byteS[i] == '#' {
			if i-1 >= 0 {
				tmp1 := append([]byte{}, byteS[:i-1]...)
				byteS = append(tmp1, byteS[i+1:]...)
				i = i - 2
			} else {
				byteS = byteS[1:]
				i--
			}
		}
	}
	return string(byteS)
}
