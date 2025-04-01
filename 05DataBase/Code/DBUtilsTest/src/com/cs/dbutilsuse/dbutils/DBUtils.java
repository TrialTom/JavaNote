package com.cs.dbutilsuse.dbutils;

import com.cs.dbutilsuse.connectionpool.DruidPool;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/04/29 21:46
 */

public class DBUtils {
    public static void main(String[] args) throws SQLException {

        QueryRunner queryRunner = new QueryRunner();

        QueryRunner queryRunner1 = new QueryRunner(DruidPool.getDataSource());

        int affectRow = queryRunner1.update("insert into clazz values(?,?)", 6, "六班");

        System.out.println("affectRow = " + affectRow);


    }
}
