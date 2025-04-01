package com.cs.javads.bstree;


import com.cs.javads.queueconstructofarray.MyArrayQueue;
import com.cs.javads.stackconstruct.MyStackTool;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ：TrialCat
 * @description：二叉排序树工具类
 * @date ：2024/04/02 14:51
 */

public class MyBinarySearchTree<T extends Comparable<T>> {
    TreeNode root;

    int size;

    public MyBinarySearchTree() {
    }

    /**
     * create by: TrialCat
     * description: 添加节点
     * create time: 2024/4/2 15:08
     *
     * @param data : 需要存储的数据
     */
    public void add(T data) {
        // 无法添加null
        if (data == null) {
            throw new IllegalArgumentException("无法添加null");
        }
        TreeNode treeNode = new TreeNode(data);
        // 为空直接插入即可
        if (isEmpty()) {
            root = treeNode;
            size++;
            return;
        }
        // 非空
        TreeNode tempNode = root;
        while (true) {
            if (tempNode.data.compareTo(data) == 0) {
                throw new IllegalArgumentException("无法添加重复值");
            }
            if (tempNode.data.compareTo(data) < 0) {
                // 右指针为空，插入
                if (tempNode.right == null) {
                    tempNode.right = treeNode;
                    size++;
                    return;
                }
                // 非空，进行下一轮比较
                tempNode = tempNode.right;
                continue;
            }
            // 左指针为空，直接插入
            if (tempNode.left == null) {
                tempNode.left = treeNode;
                size++;
                return;
            }
            // 非空，下一轮比较
            tempNode = tempNode.left;
        }
    }

    /**
     * create by: TrialCat
     * description: 删除结点
     * create time: 2024/4/2 16:34
     *
     * @param data ； 需要删除的数据
     * @return ： 是否删除成功
     */
    public boolean delete(T data) {
        // 无法删除null
        if (data == null) {
            return false;
        }
        // 树为空，删除失败
        if (isEmpty()) {
            return false;
        }
        // 记录需要查找结点以及它的父结点
        TreeNode temp = root;
        TreeNode tempPre = null;
        while (temp != null) {
            if (temp.data.compareTo(data) > 0) {
                tempPre = temp;
                temp = temp.left;
            } else if (temp.data.compareTo(data) < 0) {
                tempPre = temp;
                temp = temp.left;
            } else {
                // 查找成功
                break;
            }
        }
        // 查找失败
        if (temp == null) {
            return false;
        }
        // 以下都是查找到了结点，需要删除操作的逻辑

        // 删除的是根结点
        if (tempPre == null) {
            if (size != 1) {
                if (root.right == null) {
                    root = root.left;
                }
                exchange(root, null);
            }
            root.data = null;
            root = null;
            return true;
        }
        // 需要删除的结点处于左分支且
        if (tempPre.left.data.compareTo(data) == 0) {
            // 被删除的结点没有右孩子
            if (temp.right == null) {
                tempPre.left = temp.left;
                temp.data = null;
                return true;
            }
        } else if (temp.left == null) {
            // 被删除的结点处于右分支
            // 被删除的结点没有左孩子
            tempPre.right = temp.right;
            temp.data = null;
            return true;
        }
        // 被删除的结点具有左，右孩子
        // 得到右分支最小的结点的父节点
        exchange(temp, tempPre);
        temp.data = null;
        return true;
    }

    /**
     * create by: TrialCat
     * description: 替换逻辑
     * create time: 2024/4/3 8:50
     *
     * @param temp    : 需要替换的结点
     * @param tempPre : 需要替换结点的父节点
     */
    private void exchange(TreeNode temp, TreeNode tempPre) {
        TreeNode minPre = rightMinPreNode(temp);
        // 被替换的结点没有父节点，即为根结点
        if (tempPre == null) {
            TreeNode p = minPre.left;
            minPre.left = p.right;
            temp.left = p.left;
            temp.right = p.right;
            return;
        }
        // 被删除结点右分支最小的结点没有右分支
        if (minPre.left.right == null) {
            // 将右分支最小结点填充到删除结点的位置
            tempPre.left = minPre.left;
            // 将
            minPre.left.left = temp.left;
            minPre.left.right = temp.right;
        } else {
            TreeNode p = minPre.left;
            minPre.left = minPre.left.right;
            tempPre.left = p;
            p.left = temp.left;
            p.right = temp.right;
        }
    }

    /**
     * 删除逻辑的优化
     *
     * @param data ： 需要删除的节点值
     * @return ： 返回是否删除成功
     */
    public boolean deletePlus(T data) {
        // 无法删除值为null的结点
        if (data == null) {
            throw new IllegalArgumentException("无法删除null");
        }
        // 若树为空，则删除失败
        if (isEmpty()) {
            return false;
        }
        // 查找该结点,
        // 若查找成功，则返回其父节点，若无父节点（即为根结点）则返回null
        // 若查找失败直接抛出异常即可
        TreeNode deleteNodePre = searchNode(data);
        // 被删除的是根结点
        if (deleteNodePre == null) {
            // 删除的结点是叶子结点(只有一个根结点)
            if (root.left == null && root.right == null) {
                root = null;
                return true;
            }
            // 删除的结点是双分支结点
            if (root.left != null && root.right != null) {
                // 查找右分支中最左下结点（即做分支中最小值结点）
                // 查找成功，返回此结点的父结点（只能查找成功）
                TreeNode rightMinNodePre = searchRightNodePreOfMin(root);
                // 若返回的是传入的结点，说明此结点的右孩子即为右分支最小的结点
                if (rightMinNodePre == root) {
                    TreeNode rightMinNode = rightMinNodePre.right;
                    root.data = null;
                    rightMinNode.left = root.left;
                    root = null;
                    size--;
                    return true;
                }
                // 右分支左下结点为最小

                // 右分支下最小的结点是叶子结点
                if (isLeafNode(rightMinNodePre.left)) {
                    TreeNode rightMinNode = rightMinNodePre.left;
                    rightMinNodePre.left = null;
                    root.data = rightMinNode.data;
                    size--;
                    return true;
                }
                // 右分支最小结点是单分支结点
                TreeNode rightMinNode = rightMinNodePre.left;
                rightMinNode.right = rightMinNodePre.left;
                rightMinNode.left = root.left;
                rightMinNode.right = root.right;
                root.left = null;
                root.right = null;
                root.data = null;
                size--;
                return true;
            }
            // 删除的根结点是单分支结点
            if (root.left == null) {
                root = root.right;
                size--;
                return true;
            }
            root = root.left;
            size--;
            return true;

        }
        // 被删除的结点是左孩子结点
        if (deleteNodePre.left.data.compareTo(data) == 0) {
            // 记录被删除的结点
            TreeNode deleteNode = deleteNodePre.left;
            // 被删除的结点是叶子结点
            if (isLeafNode(deleteNode)) {
                deleteNodePre.left = null;
                deleteNode.data = null;
                size--;
                return true;
            }
            // 被删除的结点是双分支结点
            if (deleteNode.left != null && deleteNode.right != null) {
                TreeNode rightMinPre = searchRightNodePreOfMin(deleteNode);
                // 右下最小结点是被删除结点的右孩子
                if (rightMinPre == deleteNodePre) {
                    rightMinPre.right.left = deleteNode.left;
                    deleteNodePre.left = rightMinPre.right;
                    deleteNode.data = null;
                    deleteNode.left = null;
                    deleteNode.right = null;
                    size--;
                    return true;
                }
                // 一般情况(右下最小结点)
                // 右下最小结点为叶子结点
                if (isLeafNode(rightMinPre.left)) {
                    rightMinPre.left.left = deleteNode.left;
                    rightMinPre.left.right = deleteNode.right;
                    deleteNodePre.left = rightMinPre.left;
                    rightMinPre.left = null;
                    deleteNode.data = null;
                    deleteNode.left = null;
                    deleteNode.right = null;
                    size--;
                    return true;
                }
                // 右下最小结点有右分支
                TreeNode rightMin = rightMinPre.left;
                rightMinPre.left = rightMin.right;
                rightMin.left = deleteNode.left;
                rightMin.right = deleteNode.right;
                deleteNodePre.left = rightMin;
                deleteNode.data = null;
                deleteNode.left = null;
                deleteNode.right = null;
                size--;
                return true;
            }
            // 被删除的结点是单分支结点
            // 但分支结点的孩子上移动即可
            if (deleteNode.left == null) {
                deleteNodePre.left = deleteNode.right;
                deleteNode.data = null;
                deleteNode.right = null;
                size--;
                return true;
            }
            deleteNodePre.left = deleteNode.left;
            deleteNode.data = null;
            deleteNode.left = null;
            size--;
            return true;
        }
        // 被删除的右孩子结点
        // 记录被删除的结点
        TreeNode deleteNode = deleteNodePre.right;
        // 被删除的结点是叶子结点
        if (isLeafNode(deleteNode)) {
            deleteNodePre.right = null;
            deleteNode.data = null;
            size--;
            return true;
        }
        // 删除的结点是双分支结点
        if (deleteNode.left != null && deleteNode.right != null) {
            TreeNode rightMinPre = searchRightNodePreOfMin(deleteNode);
            // 右下最小结点是被删除结点的右孩子
            if (rightMinPre == deleteNodePre) {
                rightMinPre.right.left = deleteNode.left;
                deleteNodePre.right = rightMinPre.right;
                deleteNode.data = null;
                deleteNode.left = null;
                deleteNode.right = null;
                size--;
                return true;
            }
            // 一般情况(右下最小结点)
            // 右下最小结点为叶子结点
            if (isLeafNode(rightMinPre.right)) {
                rightMinPre.left.left = deleteNode.left;
                rightMinPre.left.right = deleteNode.right;
                deleteNodePre.right = rightMinPre.left;
                rightMinPre.left = null;
                deleteNode.data = null;
                deleteNode.left = null;
                deleteNode.right = null;
                size--;
                return true;
            }
            // 右下最小结点有右分支
            TreeNode rightMin = rightMinPre.left;
            rightMinPre.left = rightMin.right;
            rightMin.left = deleteNode.left;
            rightMin.right = deleteNode.right;
            deleteNodePre.right = rightMin;
            deleteNode.data = null;
            deleteNode.left = null;
            deleteNode.right = null;
            size--;
            return true;
        }
        // 删除的结点是单分支结点
        if (deleteNode.left == null) {
            deleteNodePre.right = deleteNode.right;
            deleteNode.data = null;
            deleteNode.right = null;
            size--;
            return true;
        }
        deleteNodePre.right = deleteNode.left;
        deleteNode.data = null;
        deleteNode.left = null;
        size--;
        return true;
    }

    /**
     * 判断此结点是否为叶子结点
     *
     * @param node ：需要判断的结点
     * @return ： 返回是否为叶子结点
     */
    private boolean isLeafNode(TreeNode node) {
        if (node == null) {
            throw new NullPointerException("判断的结点不能为空");
        }
        return node.left == null && node.right == null;
    }

    /**
     * 查找右分支下最小的结点
     *
     * @param root ： 从此结点的右分支下查找
     * @return ： 返回右分支下最小结点的父节点
     */
    private TreeNode searchRightNodePreOfMin(TreeNode root) {
        TreeNode minNode = root.right;
        TreeNode father = null;
        while (minNode.left != null) {
            father = minNode;
            minNode = minNode.left;
        }
        if (father == null) {
            return minNode;
        }
        return father;
    }

    /**
     * 查找结点，并返回其父节点
     *
     * @param data : 需要查找结点的值
     * @return ： 返回被查找到的结点的父节点，若此结点是根结点，则返回null；若查找失败则抛出异常
     */
    private TreeNode searchNode(T data) {
        TreeNode son = root;
        TreeNode father = null;
        // 默认此树不为空
        while (son != null) {
            if (son.data.compareTo(data) == 0) {
                break;
            } else if (son.data.compareTo(data) > 0) {
                father = son;
                son = son.left;
            } else {
                father = son;
                son = son.right;
            }
        }
        // 根结点
        if (father == null) {
            return null;
        }
        // 树中没有需要查找的结点，查找失败
        if (son == null) {
            throw new IllegalArgumentException("树中没有查找到此值！");
        }
        // 查找成功
        return father;
    }

    /**
     * create by: TrialCat
     * description: node结点右分支最小的结点
     * create time: 2024/4/2 17:29
     *
     * @param node : 分支结点
     * @return ： 返回此分支节点右分支最小的结点
     */
    public TreeNode rightMinPreNode(TreeNode node) {
        TreeNode min = node.right;
        TreeNode minPre = null;
        if (min == null) {
            return null;
        }
        while (min.left != null) {
            minPre = min;
            min = min.left;
        }
        if (minPre == null) {
            return node;
        }
        return minPre;
    }

    /**
     * create by: TrialCat
     * description: 层序遍历
     * create time: 2024/4/3 11:16
     *
     * @return : 返回队列
     */
    public MyArrayQueue<T> sequenceTraversal() throws IllegalAccessException {
        if (isEmpty()) {
            throw new IllegalAccessException("树为空，无法遍历!");
        }
        // 设置一个存储结点data的队列
        MyArrayQueue<T> queue = new MyArrayQueue<>(size + 1);
        // 设置一个存储树结点的队列
        MyArrayQueue<TreeNode> treeQueue = new MyArrayQueue<>(size + 1);
        treeQueue.offer(root);
        TreeNode temp;
        // 入队出队
        for (int i = 0; i < size; i++) {
            temp = treeQueue.poll();
            if (temp == null) {
                break;
            }
            if (temp.left != null) {
                treeQueue.offer(temp.left);
            }
            if (temp.right != null) {
                treeQueue.offer(temp.right);
            }
            queue.offer(temp.data);
        }
        return queue;
    }

    /**
     * 层序遍历改进版本
     *
     * @return : 返回遍历的到的结果
     */
    public List<T> sequenceTraversalPlus() {
        // 创建一个容器存储结果
        LinkedList<T> list = new LinkedList<>();
        // 创建一个队列存储遍历的结点
        MyArrayQueue<TreeNode> queue = new MyArrayQueue<>();
        queue.offer(root);
        TreeNode temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            list.add(temp.data);
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
        return list;
    }

    /**
     * 中根遍历
     *
     * @return : 返回遍历的结果
     */
    public List<T> middleTraversal() {
        // 一个容器记录遍历的结果
        LinkedList<T> list = new LinkedList<>();
        // 创建一个栈
        MyStackTool<TreeNode> stack = new MyStackTool<>();
        // 标记节点
        TreeNode tag = root;
        stack.push(tag);
        TreeNode temp;
        while (!stack.isEmpty()) {
            // 得到最左下结点
            while (tag.left != null) {
                stack.push(tag.left);
                tag = tag.left;
            }
            temp = stack.pop();
            // 将左最下结点入栈
            list.add(temp.data);
            if (temp.right != null) {
                tag = temp.right;
                stack.push(tag);
            }
        }
        return list;
    }

    /**
     * 调用递归方法
     *
     * @return ： 返回中序遍历的结果
     */
    public List<T> middleRecursion() {
        LinkedList<T> list = new LinkedList<>();
        middleTraversalOfRecursion(list, root);
        return list;
    }

    /**
     * 递归实现中序遍历
     *
     * @param list ： 记录遍历的结果
     * @param node ： 需要遍历的树
     */
    public void middleTraversalOfRecursion(List<T> list, TreeNode node) {
        if (node == null) {
            return;
        }
        middleTraversalOfRecursion(list, node.left);
        list.add(node.data);
        middleTraversalOfRecursion(list, node.right);
    }

    /**
     * 先序遍历
     *
     * @return ： 返回遍历序列
     */
    public List<T> firstOrderTraversal() {
        // 创建一个存储遍历结果的容器
        LinkedList<T> list = new LinkedList<>();
        // 创建一个栈
        MyStackTool<TreeNode> stack = new MyStackTool<>();
        stack.push(root);
        TreeNode temp;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            list.add(temp.data);
            if(temp.left != null){
                stack.push(temp.left);
            }
            if(temp.right != null){
                stack.push(temp.right);
            }
        }
        return list;
    }

    /**
     * create by: TrialCat
     * description: 判空
     * create time: 2024/4/2 15:52
     */
    public boolean isEmpty() {
        return root == null;
    }

    class TreeNode {
        T data;
        TreeNode right;
        TreeNode left;

        private TreeNode(T data) {
            this.data = data;
        }
    }
}
