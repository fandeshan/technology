package main

func main() {
	
}
func allCellsDistOrder(R int, C int, r0 int, c0 int) [][]int {
	result := [][]int{{r0,c0}}
	if R == 0 || C == 0 {
		return result
	}
	arr := make([][]bool,R)
	for i := 0;i < R ; i ++ {
		arr[i] = make([]bool,C)
	}
	arr[r0][c0] = true
	arrQueue := [][]int{{r0,c0}}
	step :=[][]int{{-1,0},{0,-1},{1,0},{0,1}}
	for len(arrQueue) > 0  {
		lenQueue := len(arrQueue)
		for i:=0;i<lenQueue ;i++  {
			for j:=0;j<len(step) ;j++  {
				if arrQueue[i][0]+step[j][0] < 0 || arrQueue[i][0]+step[j][0] >= R  {
					continue
				}
				if arrQueue[i][1]+step[j][1] < 0 || arrQueue[i][1]+step[j][1] >= C  {
					continue
				}
				if arr[arrQueue[i][0]+step[j][0]][arrQueue[i][1]+step[j][1]] {
					continue
				}
				arr[arrQueue[i][0]+step[j][0]][arrQueue[i][1]+step[j][1]] = true
				arrQueue = append(arrQueue,[]int{arrQueue[i][0]+step[j][0],arrQueue[i][1]+step[j][1]})
				result = append(result,[]int{arrQueue[i][0]+step[j][0],arrQueue[i][1]+step[j][1]})
			}
		}
		for i:=0;i<lenQueue ;i++  {
			arrQueue = arrQueue[1:]
		}
	}
	//markArr(arr,r0,c0,&result)
	return result
}
func markArr(arr [][]bool,i int,j int,result *[][]int){
	if i<0 || i >= len(arr) {
		return
	}
	if j<0 || j >= len(arr[i]) {
		return
	}
	if arr[i][j] {
		return
	}
	*result = append(*result,[]int{i,j})
	arr[i][j] = true
	markArr(arr,i-1,j,result)
	markArr(arr,i+1,j,result)
	markArr(arr,i,j-1,result)
	markArr(arr,i,j+1,result)
}