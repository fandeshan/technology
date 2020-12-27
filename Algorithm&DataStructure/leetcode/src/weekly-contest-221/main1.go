package main

func main() {

}

func halvesAreAlike(s string) bool {
	yByte := make([]byte,100)
	yByte['a'-'A'] = 1
	yByte['e'-'A'] = 1
	yByte['i'-'A'] = 1
	yByte['o'-'A'] = 1
	yByte['u'-'A'] = 1
	yByte['A'-'A'] = 1
	yByte['E'-'A'] = 1
	yByte['I'-'A'] = 1
	yByte['O'-'A'] = 1
	yByte['U'-'A'] = 1
	left := 0
	for i:= 0 ;i < len(s)/2;i++{
		if yByte[s[i]-'A'] == 1 {
			left ++
		}
	}
	right := 0
	for i:= len(s)/2 ;i < len(s);i++{
		if yByte[s[i]-'A'] == 1 {
			right ++
		}
	}
	return left == right
}