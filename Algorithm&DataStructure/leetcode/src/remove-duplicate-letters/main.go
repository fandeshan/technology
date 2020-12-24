package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println(removeDuplicateLetters1("abacb"))
	//fmt.Println(removeDuplicateLetters1("bcabc"))
	//fmt.Println(removeDuplicateLetters1("cbacdcbc"))
	//fmt.Println(removeDuplicateLetters1("ecbacba"))

}
func removeDuplicateLetters1(s string) string {
	fmt.Println(s)
	n := len(s)
	if n < 1 {
		return ""
	}
	letterMap := make(map[byte]int)
	for i := 0; i < n;i ++ {
		letterMap[s[i]] ++
	}
	i := n-1
	minByte := byte('z')
	minIndex := 0
	for ; i >= 0;i -- {
		if len(letterMap) == 0 {
			if s[i] <= minByte  {
				minByte = s[i]
				minIndex = i
			}
		} else {
			if _,ok := letterMap[s[i]];ok {
				delete(letterMap,s[i])
			}
			if len(letterMap) == 0 {
				minByte = s[i]
				minIndex = i
			}
		}
	}
	fmt.Println(minIndex)
	return string(minByte) + removeDuplicateLetters1(strings.ReplaceAll(s[minIndex:],string(minByte),""))
}


func removeDuplicateLetters(s string) string {
	n := len(s)
	resultBytes := make([]byte,0)
	letterMap := make([]int,26)
	for i := 0; i < n;i ++ {
		letterMap[s[i]-'a'] ++
		if letterMap[s[i]-'a'] < 2 {
			resultBytes = append(resultBytes,s[i])
		}
	}
	resultS := string(resultBytes)
	nRst := len(resultBytes)
	tmpLetterMap := make([]int,26)
	for i := 0;i <= len(s)-nRst;i++ {
		check := true
		sbSingle := strings.Builder{}
		if tmpLetterMap[s[i]-'a'] == 1 {
			continue
		}
		if s[i] > resultS[0]{
			continue
		}

		if s[i] < resultS[0] {
			check = false
		}
		tmpLetterMap[s[i]-'a'] ++
		sbSingle.WriteByte(s[i])
		sub := dfs(s[i+1:],nRst-1,resultS,tmpLetterMap,check)
		tmpLetterMap[s[i]-'a'] --
		sbSingle.WriteString(sub)
		fmt.Println(sbSingle.String())
		resultS = checkReplace(resultS,sbSingle.String())
	}
	return resultS
}

func dfs(s string,n int,origin string,tmpLetterMap []int,check bool) string {

	result := ""
	if n  < 1 {
		//fmt.Println("---------",result)
		return result
	}
	result = origin[len(origin)-n:]
	//minByte := byte('z')
	for i := 0;i <= len(s) - n;i++ {
		fmt.Println(s,n,origin,tmpLetterMap,check,i)
		sbSingle := strings.Builder{}
		if tmpLetterMap[s[i]-'a'] == 1 {
			continue
		}
		if s[i] > origin[len(origin)-n] && check {
			continue
		}
		tmpLetterMap[s[i]-'a'] ++
		lastCheck := check
		if s[i] < origin[len(origin)-n] {
			check = false
		}
		sbSingle.WriteByte(s[i])
		sub := dfs(s[i+1:],n-1,origin,tmpLetterMap,check)
		if origin[len(origin)-n] > s[i]{
			check = lastCheck
		}
		tmpLetterMap[s[i]-'a'] --
		if len(sub) != n - 1 {
			break
		}
		sbSingle.WriteString(sub)
		//fmt.Println("singleSb:",sbSingle.String())
		result = sbSingle.String()
		//fmt.Println("++++++++ RESULT :",result)
		//sb = strings.Builder{}
		//sb.WriteString(checkReplace(sb.String(),sbSingle.String()))
		//if s[i] < minByte  {
		//	minByte = s[i]
		//
		//	if n == 4 {
		//		fmt.Println(".... "+sb.String())
		//	}
		//
		//
		//}
	}
	//fmt.Println("-----g----",result)
	return result
}


func checkReplace(a,b string) string {
	if len(b) != len(a) {
		return a
	}
	for i := 0;i < len(a);i++ {
		if a[i] == b[i] {
			continue
		}
		if a[i] > b[i] {
			return b
		}
		if a[i] < b[i] {
			return a
		}
	}
	return a
}