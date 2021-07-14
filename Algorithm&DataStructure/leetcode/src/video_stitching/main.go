package main

import "fmt"

func main() {
	fmt.Println(videoStitching([][]int{{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}}, 10))
	fmt.Println(videoStitching([][]int{{0, 1}, {6, 8}, {0, 2}, {5, 6}, {0, 4}, {0, 3}, {6, 7}, {1, 3}, {4, 7}, {1, 4}, {2, 5}, {2, 6}, {3, 4}, {4, 5}, {5, 7}, {6, 9}}, 9))
}

func videoStitching(clips [][]int, T int) int {
	if len(clips) < 1 {
		return -1
	}
	//如果最小值大于0，最大值小于T，则直接过滤掉
	min := clips[0][0]
	max := clips[0][1]
	n := len(clips)
	for i := 1; i < n; i++ {
		if clips[i][0] < min {
			min = clips[i][0]
		}
		if clips[i][1] > max {
			max = clips[i][1]
		}
	}
	fmt.Println(min, max)
	if min > 0 || max < T {
		return -1
	}
	//快速排序
	quickSort(clips)
	fmt.Println(clips)
	max = clips[0][1]
	count := 0
	for i := 1; i < n; i++ {
		if clips[i][0] <= clips[i-1][1] && clips[i][1] > max {
			if i+1 < n && clips[i+1][1] > clips[i][1] {
				continue
			}
			max = clips[i][1]
			count++
		}
	}
	if max >= T {
		return count
	}
	return -1
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
