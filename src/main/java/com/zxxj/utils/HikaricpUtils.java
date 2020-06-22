package com.zxxj.utils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * @author shkstart
 * @create 2020-06-17 13:49
 */
public class HikaricpUtils {
    public  static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig("/hikari.properties");
        dataSource = new HikariDataSource(config);
    }


    /**
     * 获取数据源
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public static DataSource getDataSource()  {
        return  dataSource;
    }

    /**
     * 通过数据源获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
