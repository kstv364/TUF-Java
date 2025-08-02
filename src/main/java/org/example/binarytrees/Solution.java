package org.example.binarytrees;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/** Definition for a binary tree node.
         * public class TreeNode {
 *     int data;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int val) { data = val; left = null, right = null }
 * }
 **/
class TreeNode {
     int data;
     TreeNode left;
     TreeNode right;

     TreeNode(int val) {
         data = val;
         left = null;
         right = null;
     }
 }
class Solution {
    public int maxDepth(TreeNode root) {
        //your code goes here
        if (root == null) {
            return 0;
        }
        int lh = maxDepth(root.left);
        int rh = maxDepth(root.right);
        return 1 + Math.max(lh, rh);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        //your code goes here
        if(p == q) return true;
        if( p == null && q != null) return false;
        if(q == null && p != null ) return  false;

        // both exist
        if(p.data != q.data) return  false;

        boolean isLeftSame = isSameTree(p.left, q.left);
        boolean isRightSame = isSameTree(p.right, q.right);
        return isRightSame && isLeftSame;
    }

    public boolean isBalanced(TreeNode root) {
        //your code goes here
        if(root == null) return true;
        int lh = maxDepth(root.left);
        int rh = maxDepth(root.right);
        if(Math.abs(rh - lh) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }


    public int diameterOfBinaryTree(TreeNode root) {
        //your code goes here
        if(root == null) return 0;

        int lh = maxDepth(root.left);
        int rh = maxDepth(root.right);

        // case 1 : pass through root
        int longestPathThroughRoot = lh + rh + 1;
        // case 2 : does not pass
        int ld = diameterOfBinaryTree(root.left);
        int rd = diameterOfBinaryTree(root.right);

        return Math.max(longestPathThroughRoot, Math.max(ld, rd));
    }


    int pathSum(TreeNode root){
        if(root == null) return 0;
        int lsum = pathSum(root.left);
        int rsum = pathSum(root.right);

        return Math.max(lsum, rsum)  + root.data;
    }

    private int ans = Integer.MIN_VALUE;
    public int helper(TreeNode root) {
        if(root == null) return 0;

        int lh = helper(root.left);
        lh = Math.max(0, lh);
        int rh = helper(root.right);
        rh = Math.max(0, rh);
        // case 1 : pass through root
        int longestPathThroughRoot = lh + rh + root.data;
        ans = Math.max(ans, longestPathThroughRoot);

        return Math.max(lh, rh)  + root.data;
    }

    public int maxPathSum(TreeNode root) {
        //your code goes here
        helper(root);
        return ans;

    }

    public boolean isValid(String str) {
        HashMap<Character, Character> pairings = new HashMap<>();
        pairings.put('(', ')');
        pairings.put('[', ']');
        pairings.put('{', '}');
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<str.length() ; i++){
            char current = str.charAt(i);
            if(current == '(' || current == '[' || current == '{')
                stack.push(pairings.get(current));
            else{
                if(stack.empty()) return false;
                char top = stack.peek();
                if(top != current) return false;
                else stack.pop();
            }
        }
        return stack.empty();
    }

    class Pair{
        int first;
        int second;
        public Pair(int first, int second){
            this.first = first;
            this.second = second;
        }
    }
    class MinStack {

        private Stack<Pair> stack;
        private int currentMin;
        public MinStack() {
            stack = new Stack<>();
            currentMin = Integer.MAX_VALUE;
        }

        public void push(int val) {
            currentMin = Math.min(val, currentMin);
            stack.push(new Pair(val, currentMin));
        }

        public void pop() {

            stack.pop();
            if(stack.empty()){
                currentMin = Integer.MAX_VALUE;
            }
            else{
                currentMin = stack.peek().second;
            }

        }

        public int top() {
            Pair top = stack.peek();
            return top.first;
        }

        public int getMin() {
            Pair top = stack.peek();
            return top.second;
        }
    }
}