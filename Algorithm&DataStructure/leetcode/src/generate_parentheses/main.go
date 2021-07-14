package main

import "fmt"

func main() {
	//fmt.Println(dfs(3,0))
	fmt.Println(generateParenthesis(1))
	fmt.Println(generateParenthesis(2))
	fmt.Println(generateParenthesis(3))
}

func generateParenthesis(n int) []string {
	result := []string{}
	var dfs func(int,int,int,string)
	dfs = func(n int,i int,j int,s string) {
		if i < j || i > n {
			return
		}
		if i == j && i == n {
			result = append(result,s)
		}
		dfs(n,i+1,j,s+"(")
		dfs(n,i,j+1,s+")")
	}
	dfs(n,0,0,"")
	return result
}
//func dfs(n int,step int) string  {
//	for i :=0;i <n ;i ++  {
//
//	}
//	s := ""
//	if n < 1 {
//		return s
//	}
//	if step == 0 {
//		s +="("
//	} else {
//		s +=")"
//	}
//	s +=dfs(n-1,0)
//	s +=dfs(n-1,1)
//	return s
//}
//func dfs(n int,i int,j int,s string) []string {
//
//	if i ==j && n ==i {
//
//	}
	//for i :=0;i <n ;i ++  {
	//	s +="("
	//	nextResult := dfs(n-i +1,s)
	//	for j := 0;j <len (nextResult) ; j ++  {
	//		nextResult[j] = append(nextResult)
	//	}
	//	s += ")"
	//}
//	return s
//}