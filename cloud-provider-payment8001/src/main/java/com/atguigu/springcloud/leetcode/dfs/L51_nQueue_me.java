package com.atguigu.springcloud.leetcode.dfs;

import cn.hutool.core.util.StrUtil;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * N皇后问题
 *
 * @author sunhcer.shi
 * @date 2021/04/17 14:35
 **/

public class L51_nQueue_me {
    /**
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * <p>
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     * <p>
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *
     * @param n
     * @return
     */
    //存放所有路径的list
    List<List<String>> list = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        //'.' 表示空,'Q'表示皇后,初始化空棋盘
        // 用'.'填充行
        List<String> board = new ArrayList<String>(Collections.nCopies(n, "."));
        backtrack(board, 0);
        return list;
    }

    // 路径:board中小于 row的那些行都已经成功放置了皇后
    // 选择列表: 第row行的所有列都是放置皇后的选择
    // 结束条件: row超过了row的最后一行
    private void backtrack(List<String> board, int row) {
        //结束
        if (row == board.size()){
            list.add(new ArrayList<>(board));
            return;
        }

        int n = board.get(row).length();

        StringBuffer stringBuffer = new StringBuffer();
        for (int col=0;col<n;col++){
            if (!isValid(board,row,col)) continue;
            //做选择
            stringBuffer.setLength(0);
            stringBuffer.append(board.get(row)).setCharAt(col,'Q');//这里不对!!!
            // 进入下一行皇后
            backtrack(board,row+1);
            //撤销选择

        }
    }

    //是否可以在目标位置放皇后
    private boolean isValid(List<String> board, int row, int col) {
        int n = board.size();
        // 检查列是否有皇后冲突
        for (int i = 0; i < n; i++) {
            if (board.get(i).charAt(col)=='Q'){
                return false;
            }
        }
        //检查右上方是否有皇后冲突
        for (int i = row-1, j =col+1 ; i>=0&& j<n; i--,j++) {
            if (board.get(i).charAt(j)=='Q'){
                return false;
            }
        }

        //检查左上方是否有皇后冲突
        for (int i = row-1,j=col-1; i >=0&&j>=0; i--,j--) {
            if (board.get(i).charAt(j)=='Q'){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String pattern="^([\u4e00-\u9fa5]|[0-9_a-zA-Z]){1,1000}$";
        boolean matches = Pattern.matches(pattern, "");
        System.out.println(matches);
    }
}
