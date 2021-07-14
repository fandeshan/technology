package main

import "fmt"

func main() {
	fmt.Println(isMatch("aab","c*a*b"))
}

func isMatch(s string, p string) bool {
	if len(s) < 1 {
		return true
	}
	if len(p) < 1 {
		return false
	}
	si := 0
	pi := 0
	for  si < len(s) && pi < len(p) {
		for p[pi]!='.' && p[pi]!='*' && s[si] != p[pi] && pi < len(p) {
			pi ++
		}
		
		if p[pi] == '*' {
			for p[pi] == '*'  {
				pi ++
			}
			
		}
		if s[si] != p[pi] {
			pi ++
		}
		si ++;pi++
	}
}