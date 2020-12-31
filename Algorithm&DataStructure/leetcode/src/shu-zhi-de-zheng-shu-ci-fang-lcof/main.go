package main

func main() {

}

func myPow(x float64, n int) float64 {
	negative := false
	if n < 0 {
		negative = true
		n = -n
	}

	result := float64(1)
	for{
		tmp := x
		if n == 0 {
			break
		}
		if n == 1 {
			result = tmp * result
			break
		}

		i := 2
		for ; i <= n; {
			tmp = tmp * tmp
			if i*i > n {
				break
			}
			i = i + i
		}
		result = tmp*result
		n = n-i
	}


	if negative {
		return 1/result
	}
	return result
}