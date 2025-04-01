package com.cs.javads.doublelinklist;


public class DemoTest {

    public static void main(String[] args) {
        MyDoubleLinkList doubleList = MyDoubleLinkList.createList("A");
        doubleList.addNode("B");
        doubleList.addNode("C");
        doubleList.addNode("D");
        doubleList.addNode("E");

        System.out.println("doubleList.deleteValue(\"D\") = " + doubleList.deleteValue("D"));
        System.out.println("doubleList.deleteValue(\"D\") = " + doubleList.deleteValue("D"));

        System.out.println("doubleList.editNode(2, \"G\") = " + doubleList.editNode(2, "G"));
        System.out.println(doubleList);
        ;
    }
}

class MyDoubleLinkList {
    int size;
    LinkNode head;

    public MyDoubleLinkList(int size, LinkNode head) {
        this.size = size;
        this.head = head;
    }

    /**
     * 传建一个双端链表（不带头结点）
     *
     * @param data : 传入第一结点的值
     * @return : 返回链表
     */
    public static MyDoubleLinkList createList(String data) {
        return new MyDoubleLinkList(0, new LinkNode(data, null, null));
    }

    /**
     * 判断链表是否为空
     *
     * @return ： 返回true表示链表非空，反之亦然
     */
    public boolean isEmpty() {
        return size != 0;
    }

    /**
     * 添加结点(头插法)
     *
     * @param data ：所需添加结点的值
     */
    public void addNode(String data) {
        // 创建新结点
        LinkNode linkNode = new LinkNode(data, null, head);
        head.pre = linkNode;
        head = linkNode;
        size++;
    }

    /**
     * 删除链表中第一个数据域为data的结点
     *
     * @param data : 所需删除的指定值
     * @return : 返回删除是否成功
     */
    public boolean deleteValue(String data) {
        int index = checkOfValue(data);
        // 删除的结点不存在
        if (index == -1) {
            return false;
        }
        // 根据索引查找指定结点
        LinkNode deleteNode = checkOfIndex(index);
        // 删除的是尾结点
        if (deleteNode.next == null) {
            deleteNode.pre.next = null;
            deleteNode.pre = null;
            size--;
            return true;
        }
        // 删除的是头结点
        if (deleteNode.pre == null) {
            head = deleteNode.next;
            deleteNode.next.pre = null;
            deleteNode.next = null;
            size--;
            return true;
        }
        // 删除的是中间结点
        deleteNode.pre.next = deleteNode.next;
        deleteNode.next.pre = deleteNode.pre;
        deleteNode.pre = null;
        deleteNode.next = null;
        size--;
        return true;
    }

    /**
     * 查找指定索引位置的结点
     *
     * @param index ： 所需查找的索引
     * @return : 返回索引位置的结点
     */
    public LinkNode checkOfIndex(int index) {
        // 判空
        if (!isEmpty()) {
            return null;
        }
        // 索引值不合法
        if (index < 0) {
            return null;
        }
        // 循环遍历链表
        int pointIndex = 0;
        LinkNode temp = head;
        while (temp != null) {
            if (pointIndex == index) {
                return temp;
            }
            temp = temp.next;
            pointIndex++;
        }
        return null;
    }

    /**
     * 按值查找结点，返回结点索引
     *
     * @param data ： 需要查找的值
     * @return ： 返回结点的索引值
     */
    public int checkOfValue(String data) {
        // 判断传入的双端链表是否为空
        if (!isEmpty()) {
            // 为空返回-1
            return -1;
        }
        // 链表非空
        // 记录结点位置以及设置查找指针
        int num = 0;
        LinkNode temp = head;
        // 若传入的值为null单独判断
        if (data == null) {
            while (temp != null) {
                if (temp.data == null) {
                    return num;
                }
                temp = temp.next;
                num++;
            }
            // 查找失败
            return -1;
        }
        // 非空查找
        while (temp != null) {
            if (data.equals(temp.data)) {
                return num;
            }
            temp = temp.next;
            num++;
        }
        return -1;
    }

    /**
     * 修改指定索引位置的结点的值
     *
     * @param index ： 修改结点所在位置
     * @param data  : 修改后的值
     * @return ： 返回修改是否成功
     */
    public boolean editNode(int index, String data) {
        // 找到修改位置的结点
        LinkNode linkNode = checkOfIndex(index);
        // 没有此节点
        if (linkNode == null) {
            return false;
        }
        // 修改值
        linkNode.data = data;
        return true;
    }

    @Override
    public String toString() {
        return "MyDoubleLinkList{" +
                "size=" + size +
                ", head=" + head +
                '}';
    }

    static class LinkNode {
        String data;
        LinkNode pre;
        LinkNode next;

        public LinkNode(String data, LinkNode pre, LinkNode next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }

        @Override
        public String toString() {
            return "LinkNode{" +
                    "data='" + data + '\'' +
                    ", next=" + next +
                    '}';
        }
    }
}
