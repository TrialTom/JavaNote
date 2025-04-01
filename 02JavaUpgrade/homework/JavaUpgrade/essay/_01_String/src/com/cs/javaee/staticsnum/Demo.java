package com.cs.javaee.staticsnum;

import org.junit.Test;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/02/29 11:25
 */

public class Demo {

    @Test
    public void test01() {
        String source = "abcdabcefgha";
        String dest = "ac";
        System.out.println("staticsCount(source,dest) = " + staticsCount(source, dest));
    }

    /**
     * create by: TrialCat
     * description: 统计abc在字符串中abcdabcfgh中出现的次数
     * create time: 2024/2/29 11:39
     *
     * @return int
     */
    public int staticsCount(String source, String dest) {
        int num = 0;
        int tag = 0;
        while (true) {
            if (tag == source.length()) {
                break;
            }
            if (source.indexOf(dest, tag) == -1) {
                break;
            }
            tag = source.indexOf(dest, tag) + dest.length();
            num++;
        }
        return num;
    }
}
