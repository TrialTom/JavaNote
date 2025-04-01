package com.cs.java.charstream;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * @author ：TrialCat
 * @description：字母排序
 * @date ：2024/03/25 17:09
 */

public class DemoTest {
    @Test
    public void test01() throws IOException {
        String s = randomOfChar(26);
        byte[] randArray = s.getBytes();
        // 写入文件
        FileOutputStream out = new FileOutputStream("a.text");
        out.write(randArray);
        Arrays.sort(randArray);
        System.out.println(new String(randArray));
        // 读
        FileInputStream in = new FileInputStream("a.text");
        byte[] bytes = new byte[1024];
        int readData = 0;
        readData = in.read(bytes);
        // 排序
        sortOfArray(bytes, 0, readData - 1);
        // 写
        out.write(System.lineSeparator().getBytes());
        out.write(bytes, 0, readData);

        System.out.println(new String(bytes, 0, readData));
        out.close();
    }

    /**
     * create by: TrialCat
     * description: 数组排序
     * create time: 2024/3/25 19:28
     */
    public void sortOfArray(byte[] arr, int low, int high) {
        if (high <= low) {
            return;
        }
        int pivot = determinationOfThePivot(arr, low, high);
        sortOfArray(arr, low, pivot - 1);
        sortOfArray(arr, pivot + 1, high);
    }

    /**
     * create by: TrialCat
     * description: 枢轴确定
     * create time: 2024/3/25 19:31
     */
    public int determinationOfThePivot(byte[] arr, int low, int high) {
        byte pivot = arr[low];
        while (high > low) {
            while (arr[high] >= pivot && high > low) {
                high--;
            }
            arr[low] = arr[high];
            while (arr[low] <= pivot && high > low) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    /**
     * create by: TrialCat
     * description: 生成随机的一串字母
     * create time: 2024/3/25 19:26
     */
    public String randomOfChar(int num) {
        String resultChar = "";
        Random rand = new Random();
        for (int i = 0; i < num; ) {
            int character = rand.nextInt(51) + 65;
            // 不是字母
            if (character >= 90 && character < 97) {
                continue;
            }
            resultChar += (char) character;
            i++;
        }
        return resultChar;
    }
}

