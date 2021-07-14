package main

import "fmt"

func main() {
	fmt.Println(minPartitions("32"))
	fmt.Println(minPartitions("27346209830709182346"))
}

func minPartitions(n string) int {
	max := 0
	for i := 0 ; i < len(n);i++ {
		val := int(n[i]-'0')
		if val > max {
			max = val
		}
	}
	return max
}