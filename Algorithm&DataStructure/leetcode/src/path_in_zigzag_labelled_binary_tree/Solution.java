package path_in_zigzag_labelled_binary_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author fandeshan
 * @description 1104. 二叉树寻路
 * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
 *
 * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 *
 * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 *
 *
 *
 * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：label = 14
 * 输出：[1,3,4,14]
 * 示例 2：
 *
 * 输入：label = 26
 * 输出：[1,2,6,10,26]
 *  
 *
 * 提示：
 *
 * 1 <= label <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/7/30
 */
class Solution {
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> result = new ArrayList<>();
        result.add(1);
        if ( label == 1 ){
            return result;
        }
        int n=1;
        int i = 1;
        while (label > n) {
            n = ((n+1)<<1)-1;
            i++;
        }
        while(label > 1){
            result.add(label);
            int tmp = n - label;
            label = tmp/2 + ((n+1)>>2);
            n = ((n+1)>>1) - 1;
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().pathInZigZagTree(14));
        System.out.println(new Solution().pathInZigZagTree(26));
    }
}