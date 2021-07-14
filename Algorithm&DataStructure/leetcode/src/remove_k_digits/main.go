package main

func main() {
	
}

func removeKdigits(num string, k int) string {
	if k < 1 {
		return num
	}
	numBytes := []byte(num)
	for i:=0;i<k;i++{
		j :=1
		for ;j<len(numBytes);j++{
			if numBytes[j-1]>numBytes[j] {
				break
			}
		}
		if j== 1 && len(numBytes)>1 {
			numBytes = numBytes[1:]
		} else if j < len(numBytes){
			temp:= append([]byte{},numBytes[0:j-1]...)
			numBytes=append(temp,numBytes[j:]...)
		}else{
			numBytes=numBytes[:len(numBytes)-1]
		}
		for len(numBytes) > 0 &&numBytes[0]=='0' {
			numBytes=numBytes[1:]
		}
		if len(numBytes) < k - i -1{
			return "0"
		}
		// fmt.Println(string(numBytes))
	}
	if len(numBytes) < 1{
		return "0"
	}
	return string(numBytes)

}