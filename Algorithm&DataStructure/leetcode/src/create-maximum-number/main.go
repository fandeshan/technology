package main

import "fmt"

func main() {
	//fmt.Println(maxNumber([]int{3, 4, 6, 5},[]int{9, 1, 2, 5, 8, 3},5))
	//fmt.Println(maxNumber([]int{3,9},[]int{8,9},3))
	//fmt.Println(maxNumber([]int{6,7},[]int{6,0,4},5))

	fmt.Println(maxNumber([]int{7,6,1,9,3,2,3,1,1},[]int{4,0,9,9,0,5,5,4,7},9)) //[9,9,9,7,3,2,3,1,1]
	//fmt.Println(mergeResult([]int{9 ,3 ,2 ,3 ,1 ,1},[]int{9 ,9 ,7}))
}

func maxNumber(nums1 []int, nums2 []int, k int) []int {

	rst := []int{}
	for i := 0; i <= k ; i++  {
		if len(nums1) < i || len(nums2) < k - i {
			continue
		}
		maxK1 := maxKNumber(nums1,i)
		maxK2 := maxKNumber(nums2,k-i)
		fmt.Println(maxK1)
		fmt.Println(maxK2)
		maxK := mergeResult(maxK1,maxK2)
		fmt.Println(maxK)
		if numsCompare(maxK,rst){
			rst = maxK
		}
		fmt.Println(rst)
		fmt.Println("======")
	}
	return rst
}
func maxKNumber(nums []int,k int)  []int{
	rst := []int{}
	if k < 1 {
		return rst
	}
	for i := 0;i<len(nums) ;i++  {
		rLen := len(rst)
		for rLen > 0 && rst[rLen-1] < nums[i] && rLen+len(nums) -i > k {
			rst = rst[:rLen-1]
			rLen --
		}
		rst = append(rst,nums[i])
	}
	for rLen:=len(rst);rLen > k;  {
		rst = rst[:rLen-1]
		rLen --
	}
	return rst
}
func mergeResult(nums1 []int,nums2 []int)  []int{
	rst := []int{}
	i := 0
	j := 0
	for i <len(nums1)&& j < len(nums2) {
		if nums1[i] > nums2[j] {
			rst = append(rst,nums1[i])
			i++
		} else if nums1[i] < nums2[j]{
			rst = append(rst,nums2[j])
			j++
		} else {
			if numsCompare(nums1[i+1:],nums2[j+1:]) {
				rst = append(rst,nums1[i])
				i++
			} else {
				rst = append(rst,nums2[j])
				j++
			}
		}

	}
	for i < len(nums1)  {
		rst = append(rst,nums1[i])
		i++
	}
	for j < len(nums2)  {
		rst = append(rst,nums2[j])
		j++
	}
	return rst
}
func numsCompare(nums1 []int,nums2 []int) bool{
	n2 := len(nums2)
	i := 0
	for ;i < len(nums1) ;i ++  {
		if i >= n2 {
			return true
		}
		if nums1[i] < nums2[i] {
			return false
		}
		if nums1[i] > nums2[i] {
			return true
		}
	}
	if i < n2 {
		return false
	}
	return true
}
//func maxNumber(nums1 []int, nums2 []int, k int) []int {
//	n1 := len(nums1)
//	num1MaxArr := make([][]int,len(nums1))
//	for i :=n1-1;i >= 0 ;i--  {
//		num1MaxArr[i] = make([]int,2)
//		if i == n1 -1 {
//			num1MaxArr[i][0] = nums1[]
//		}
//	}
//	nums1Map := make(map[int][]int,10)
//	nums2Map := make(map[int][]int,10)
//	for i := 0 ; i < len(nums1) ; i++  {
//		nums1Map[nums1[i]] = append(nums1Map[nums1[i]],i)
//	}
//	for i := 0 ; i < len(nums2) ; i++  {
//		nums2Map[nums2[i]] = append(nums2Map[nums2[i]],i)
//	}
//	fmt.Println(nums1Map)
//	fmt.Println(nums2Map)
//	return nil
//}
//func dfs(start1 int,end1 int, start2 int,end2 int, k int,num1Map map[int][]int,num2Map map[int][]int,n int)  {
//	if n < 0 {
//		return
//	}
//	for i := 9; i >=0 ;i--  {
//		indexs1,ok1 :=num1Map[i]
//		indexs2,ok2 :=num2Map[i]
//		if !ok1 && !ok2 {
//			continue
//		}
//		if ok1 {
//			index1 := indexs1[0]
//			if indexs1 < start1 || indexs1 >  {
//
//			}
//			num1Map[i] = indexs1[1:]
//			dfs(index1+1,end1,start2,end2,k,num1Map,num2Map,n)
//		}
//		if ok2 {
//
//		}
//		//dfs
//	}
//
//}
