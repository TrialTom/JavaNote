package com.cs.javads.stackconstruct;

/**
 * @author ：TrialCat
 * @description：栈的工具类
 * @date ：2024/04/01 9:50
 */

public class MyStackTool <T>{

    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;
    /**
     * 栈顶指针
     */
    StackNode top;
    /**
     * 栈的深度
     */
    int size;

    /**
     * create by: TrialCat
     * description: 压栈
     * create time: 2024/4/1 10:38
     *
      * @param data : 需要存入的数据
     * @return ： 返回是否入栈成功
     */
    public boolean push(T data){
        if(size >= MAX_CAPACITY){
            return false;
        }
        StackNode temp = new StackNode(data);
        temp.next = top;
        top = temp;
        size++;
        return true;
    }

    /**
     * create by: TrialCat
     * description: 查看栈顶元素
     * create time: 2024/4/1 10:47
     *
     * @return :返回栈顶结点数据域的值
     */
    public T peek(){
        return top.data;
    }

    /**
     * create by: TrialCat
     * description: 判空
     * create time: 2024/4/1 10:48
     *
     * @return ： 返回栈是否为空
     */
    public boolean isEmpty(){
        return size <= 0;
    }

    /**
     * create by: TrialCat
     * description: 出栈
     * create time: 2024/4/1 10:50
     *
     * @return : 弹出栈顶元素
     */
    public T pop(){
        // 栈为空
        if(isEmpty()){
            throw new RuntimeException("栈为空，无法出栈");
        }
        // 栈非空
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public MyStackTool() {
        this.top = null;
        this.size = 0;
    }

    public MyStackTool(T data) {
        this.top = new StackNode(data);
        this.size = 1;
    }

    /**
     * create by: TrialCat
     * description: 栈结点
     * create time: 2024/4/1 9:54
     */
    class StackNode{
        T data;
        StackNode next;

        public StackNode(T data) {
            this.data = data;
        }
    }
}
