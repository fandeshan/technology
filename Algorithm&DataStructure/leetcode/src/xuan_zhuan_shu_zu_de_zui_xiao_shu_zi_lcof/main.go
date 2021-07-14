package main

func main() {
	
}

func minArray(numbers []int) int {
	start := 0
	end := len(numbers)-1

	for start+1 < end  {
		mid := (end + start)/2
		if numbers[mid] > numbers[start] {
			if numbers[end] < numbers[mid]{
				start = mid
			}else {
				return numbers[start]
			}

		} else if numbers[mid] == numbers[start] {
			if numbers[end] > numbers[mid] {
				return numbers[start]
			}else if numbers[end] == numbers[mid] {
				start++
			} else{
				start = mid
			}
		} else {
			end = mid
		}
	}
	if numbers[start] > numbers[end] {
		return numbers[end]
	}
	return numbers[start]
}