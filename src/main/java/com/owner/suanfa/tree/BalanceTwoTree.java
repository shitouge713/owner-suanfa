package com.owner.suanfa.tree;

/**
 * 判断一个二叉数是否是平衡二叉树
 * 递归判断
 */
public class BalanceTwoTree {
    public static boolean isBalance = true;

    public boolean isIsBalanceTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        isBalance(root);
        return isBalance;
    }

    public int isBalance(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = isBalance(root.left);
        int rightHeight = isBalance(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalance = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        //root.left.left.left.left = new TreeNode(8);
        //root.left.left.left.left.left = new TreeNode(9);
        BalanceTwoTree solution = new BalanceTwoTree();
        System.out.println(solution.isIsBalanceTree(root));
    }
}
