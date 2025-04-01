package com.cs.javads.queueconstructoflink;

/**
 * @author ：TrialCat
 * @description：队列工具类
 * @date ：2024/04/01 17:37
 */

public class MyLinkQueue <T>{
    LinkNode head;
    LinkNode tail;
    int size;

    /**
     * 初始队为空
     */
    public MyLinkQueue() {
    }

    /**
     * create by: TrialCat
     * description: 入队
     * create time: 2024/4/1 17:46
     *
      * @param data : 入队的数据
     */
    public void offer(T data){
        LinkNode tempNode = new LinkNode(data);
        if(isEmpty()){
            head = tempNode;
            tail = tempNode;
            size++;
            return;
        }
        tail.next = tempNode;
        tail = tempNode;
        size++;
        return;
    }

    /**
     * create by: TrialCat
     * description: 出队
     * create time: 2024/4/1 17:48
     *
     * @return ；返回队头元素的值
     */
    public T poll(){
        // 队空，抛出异常
        if(isEmpty()){
            throw new RuntimeException("队为空");
        }
        // 非空
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    /**
     * create by: TrialCat
     * description: 查看队头元素
     * create time: 2024/4/1 17:56
     *
     * @return : 返回队头元素
     */
    public T peek(){
        if(isEmpty()){
            throw new RuntimeException("队为空");
        }
        return head.data;
    }

    /**
     * create by: TrialCat
     * description: 判空
     * create time: 2024/4/1 17:55
     *
     * @return : 返回队列是否为空
     */
    private boolean isEmpty() {
        return size <= 0;
    }

    class LinkNode{
        T data;
        LinkNode next;

        private LinkNode(T data) {
            this.data = data;
        }
    }
}
