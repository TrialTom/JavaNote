package com.cs.javads.linkconstruct01;


/**
 * @author ：TrialCat
 * @description：链表实现线性表
 * @date ：2024/03/30 10:04
 */

public class DemoTest {
    public static void main(String[] args) {
        // 创建一个单链表
        LinkNode head = MyLink.createLink("A");

        head = MyLink.addNode("B", head);

        System.out.println(head);
    }

}

/**
 * create by: TrialCat
 * description: 单链表实现线性表
 * create time: 2024/3/30 10:07
 */
class MyLink {

    /**
     * create by: TrialCat
     * description: 默认的创建单链表
     * create time: 2024/3/30 10:16
     *
     * @return :头节点
     */
    public static LinkNode createLink() {
        return new LinkNode();
    }

    /**
     * create by: TrialCat
     * description: 传参创建单链表
     * create time: 2024/3/30 10:20
     *
     * @param value:数据域赋值
     * @return :返回头指针
     */
    public static LinkNode createLink(String value) {
        return new LinkNode(value);
    }

    /**
     * create by: TrialCat
     * description: 单链表的结点添加
     * create time: 2024/3/30 10:23
     *
     * @param value : 添加结点数据域的赋值;
     * @param head  : 所需添加单链表的头结点
     * @return : 返回头节点
     */
    public static LinkNode addNode(String value, LinkNode head) {
        // 创建新结点
        LinkNode node = new LinkNode(value);
        // 采用头插法将结点插入旧的单链表中
        node.next = head;
        head = node;
        // 返回新链表
        return head;
    }

    /**
     * create by: TrialCat
     * description: 指定位置之前插入结点
     * create time: 2024/3/30 10:34
     *
     * @param index : 指定结点的位置
     * @return : 返回插入后新链表的头结点
     */
    public static LinkNode addNode(String value, LinkNode head, int index) {
        // 存放需要插入的结点
        LinkNode linkNode = createLink(value);
        // 保存头结点
        LinkNode temp = head;
        int pointIndex = 0;
        // 循环找到指定索引位置的结点
        while (temp != null) {
            // 找到指定结点，将此节点的值与待插入结点的值交换，
            // 并将待插入结点插到结点后
            if (pointIndex == index) {
                String sTemp = temp.value;
                temp.value = linkNode.value;
                linkNode.next = temp.next;
                temp.next = linkNode;
                linkNode.value = sTemp;
                return head;
            }
            // 更新temp和pointIndex
            temp = temp.next;
            pointIndex++;
        }
        // 输入的索引有误
        System.out.println("输入索引大于链表长度！");
        return null;
    }

    /**
     * create by: TrialCat
     * description: 查找指定位置结点的值
     * create time: 2024/3/30 10:40
     *
     * @param index : 结点位置（头结点索引为0）
     * @return : 返回数据域的值
     */
    public static String checkNodeReturnValue(int index, LinkNode head) {
        // 从头开始遍历单链表
        // LinkNode temp = head;
        // int pointIndex = 0;
        // while (head != null) {
        //     if (pointIndex == index) {
        //         // 查找成功返回所在位置的索引值z
        //         return temp.value;
        //     }
        //     temp = temp.next;
        //     pointIndex++;
        // }
        // // 查找失败返回null
        // return null;

        LinkNode linkNode = checkNode(index, head);
        if(linkNode == null){
            return null;
        }
        return linkNode.value;
    }

    /**
     * create by: TrialCat
     * description: 按值查找结点，返回结点所在位置的索引
     * create time: 2024/3/30 10:42
     *
     * @param value : 所需要查找的值
     * @return : 返回结点所在位置的索引值（头结点索引为0）
     */
    public static int checkNode(String value, LinkNode head) {
        // 从头开始遍历单链表
        LinkNode temp = head;
        int index = 0;
        while (temp != null) {
            if (value.equals(temp.value)) {
                // 查找成功返回所在位置的索引值
                return index;
            }
            temp = temp.next;
            index++;
        }
        // 查找失败返回-1
        return -1;
    }

    /**
     * create by: TrialCat
     * description: 返回指定位置的结点
     * create time: 2024/3/30 11:22
     *
     * @param index : 索引位置
     * @return : 返回结点
     */
    public static LinkNode checkNode(int index, LinkNode head) {
        LinkNode temp = head;
        int pointIndex = 0;
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
     * create by: TrialCat
     * description: 修改指定位置的值
     * create time: 2024/3/30 10:45
     *
     * @param index : 指定位置的索引
     * @param value : 修改后的值
     * @return : 返回是否修改成功
     */
    public static boolean reviseNode(int index, String value, LinkNode head) {
        // 查找指定结点
        LinkNode linkNode = checkNode(index, head);
        if (linkNode == null) {
            return false;
        }
        // 修改值
        linkNode.value = value;
        return true;
    }
}

class LinkNode {
    /**
     * 数据域
     */
    String value;
    /**
     * 指针域
     */
    LinkNode next;

    public LinkNode() {
        this.value = "-1";
        this.next = null;
    }

    public LinkNode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LinkNode{" +
                "value='" + value + '\'' +
                ", next=" + next +
                '}';
    }
}
