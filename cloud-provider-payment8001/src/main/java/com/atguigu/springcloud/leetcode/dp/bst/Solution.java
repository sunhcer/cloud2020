package com.atguigu.springcloud.leetcode.dp.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < queue.size(); i++) {
                TreeNode poll = queue.poll();
                if (poll!=null){
                    if (poll.left!=null){
                        queue.offer(poll.left);
                        list.add(poll.left.val);
                    }
                    if (poll.right!=null){
                        queue.offer(poll.right);
                        list.add(poll.right.val);
                    }
                }
            }
            if (!list.isEmpty()){
                result.add(list);
            }

        }
        return result;
    }
}