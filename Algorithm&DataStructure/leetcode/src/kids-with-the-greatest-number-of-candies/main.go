package main

func main() {
	
}

func kidsWithCandies(candies []int, extraCandies int) []bool {
	if len(candies) < 1 {
		return nil
	}
	max := candies[0]
	for i:=1 ; i < len(candies) ; i++{
		if candies[i] > max {
			max = candies[i]
		}
	}
	result := make([]bool,len(candies))
	for i:=0 ; i < len(candies) ; i++{
		if candies[i] + extraCandies >= max {
			result[i] = true
		}
	}
	return result
}