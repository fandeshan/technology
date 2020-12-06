package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println(interpret("(al)G(al)()()G"))
}

func interpret(command string) string {
	if len(command) < 1 {
		return ""
	}

	command = strings.ReplaceAll(command,"(al)","al")
	command = strings.ReplaceAll(command,"()","o")
	return command
}