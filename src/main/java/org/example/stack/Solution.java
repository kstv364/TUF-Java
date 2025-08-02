package org.example.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Solution {


    public int[] nextLargerElement(int[] arr) {
        int n = arr.length;
        int ans[] = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i=n-1; i>= 0; i--) stack.push(arr[i]);
        for(int i=n-1; i>=0; i--){
            while(!stack.empty() && stack.peek() <= arr[i]) stack.pop();
            if(stack.empty())
                ans[i] = -1;
            else
                ans[i] = stack.peek();
            stack.push(arr[i]);
        }
        return ans;

    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for(int i=0; i< asteroids.length; i++){

            if(asteroids[i] > 0) st.push(asteroids[i]);
            else{
                while(!st.empty() && -asteroids[i] >= st.peek()) {
                    // negative value is gteater
                    st.pop();
                }
                if(st.empty()) st.push(asteroids[i]);
                if(st.peek() > -asteroids[i]) continue;

            }
        }
        ArrayList<Integer> ans = new ArrayList<Integer>();
        while (st.empty()){
            ans.add(st.pop());
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
