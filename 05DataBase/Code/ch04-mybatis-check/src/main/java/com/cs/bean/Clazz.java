package com.cs.bean;

import lombok.Data;

import java.util.List;

/**
 * @author ：TrialCat
 * @description：班级类
 * @date ：2024/05/05 11:16
 */

@Data
public class Clazz {
    int id;
    String name;
    List<Stu> stuList;
}
