package com.cs.javads.bstree;

import com.cs.javads.queueconstructofarray.MyArrayQueue;

import java.util.List;

/**
 * @author ：TrialCat
 * @description：测试类
 * @date ：2024/04/02 14:51
 */

public class DemoTest {
    public static void main(String[] args) throws IllegalAccessException {
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>();
        tree.add(100);
        tree.add(50);
        tree.add(200);
        tree.add(20);
        tree.add(1);
        tree.add(15);
        tree.add(10);
        tree.add(150);
        tree.add(120);
        tree.add(130);
        tree.add(300);
        tree.add(250);
        tree.add(170);

        System.out.println("tree.deletePlus(1) = " + tree.deletePlus(1));
        MyArrayQueue<Integer> que = new MyArrayQueue<>();
        que = tree.sequenceTraversal();
        for (int i = 0; i < tree.size; i++) {
            System.out.print(que.poll() + " ");
        }
        System.out.println();
        System.out.println("tree.sequenceTraversalPlus() = " + tree.sequenceTraversalPlus());

        List<Integer> list = tree.middleTraversal();
        System.out.println("list = " + list);

        System.out.println("tree.middleRecursion() = " + tree.middleRecursion());

        System.out.println("tree.firstOrderTraversal() = " + tree.firstOrderTraversal());
    }
}
