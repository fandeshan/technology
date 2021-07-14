package main

import "fmt"

func main() {
	//points := [][]int{{1,3},{-2,2}}
	//K := 1
	points := [][]int{{3,3},{5,-1},{-2,4}}
	K := 2
	fmt.Println(kClosest(points,K))
}

func kClosest(points [][]int, K int) [][]int {
	if len(points) <= K {
		return points
	}
	dis := make([][]int,len(points))
	for i := 0; i < len(dis) ; i ++  {
		dis[i] = make([]int,2)
		dis[i][0] = points[i][0] * points[i][0] + points[i][1] * points[i][1]
		dis[i][1] = i
	}
	quickSort(dis)
	result := make([][]int,K)
	for i:=0;i<len(result) ;i++  {
		result[i] = make([]int,2)
		result[i][0] = points[dis[i][1]][0]
		result[i][1] = points[dis[i][1]][1]
	}
	return result
}
func quickSort(nums [][]int) {
	if len(nums) < 2 {
		return
	}
	i := 0
	j := len(nums) - 1
	base := nums[i][0]
	base1 := nums[i][1]
	for i < j {
		for i < j && nums[j][0] >= base {
			j--
		}
		if i < j {
			nums[i][0] = nums[j][0]
			nums[i][1] = nums[j][1]
			i++
		}

		for i < j && nums[i][0] < base {
			i++
		}
		if i < j {
			nums[j][0] = nums[i][0]
			nums[j][1] = nums[i][1]
			j--
		}
	}
	nums[i][0] = base
	nums[i][1] = base1
	quickSort(nums[:i])
	quickSort(nums[i+1:])
}