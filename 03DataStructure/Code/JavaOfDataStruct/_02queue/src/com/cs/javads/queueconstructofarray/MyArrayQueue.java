package com.cs.javads.queueconstructofarray;

/**
 * @author ：TrialCat
 * @description：循环数组实现队列
 * @date ：2024/04/01 18:07
 */

public class MyArrayQueue<T> {
    private static final int INIT_CAPACITY = 10;
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    Object[] array;
    int head;
    int tail;
    int size;

    public MyArrayQueue() {
        size = INIT_CAPACITY;
        array = new Object[size];
    }

    public MyArrayQueue(int size) {
        if (size >= MAX_CAPACITY) {
            throw new IllegalArgumentException("无法创建如此大的数组");
        }
        this.size = size;
        array = new Object[size];
    }

    /**
     * create by: TrialCat
     * description: 入队
     * create time: 2024/4/1 20:10
     *
     * @param data : 需要入队的数据
     * @return : 返回入队是否成功
     */
    public boolean offer(T data) {
        if (isFull()) {
            System.out.println("队列已满！");
            return false;
        }
        array[tail] = data;
        tail = (tail + 1) % size;
        return true;
    }

    /**
     * create by: TrialCat
     * description: 出队
     * create time: 2024/4/1 20:18
     *
     * @return : 返回出队的元素
     */
    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        Object temp = array[head];
        head = (head + 1) % size;
        return (T) temp;

    }


    /**
     * 判空操作
     *
     * @return : 返回是否为空
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * create by: TrialCat
     * description: 判空
     * create time: 2024/4/1 20:11
     *
     * @return ：返回是否为空
     */
    public boolean isFull() {
        // 浪费一个空间，便于判满
        return queueLength() >= size - 1;
    }

    /**
     * create by: TrialCat
     * description: 求队列的长度
     * create time: 2024/4/1 20:03
     *
     * @return : 返回队列的长度
     */
    public int queueLength() {
        return (tail + size - head) % size;
    }

}
