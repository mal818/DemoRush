package design.maliang.jdcp;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import design.maliang.jdbc.util.JDBCUtilDemo2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class C3P0手动设置参数 {
    public static void main(String[] args) {
        /*
         * 1. 获取连接对象
         * 2. 获取可以执行SQL语句的对象.
         * 3. 执行SQL语句, 获取结果集.
         * 4. 操作结果集.
         * 5. 释放资源.
         */
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            //创建数据库连接池对象
            ComboPooledDataSource cpds = new ComboPooledDataSource();

            //设置参数
            cpds.setDriverClass("com.mysql.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql:///web03");
            cpds.setUser("root");
            cpds.setPassword("123456");

            //从数据库连接池对象中获取: 连接对象
            conn = cpds.getConnection();

            //2
            stat = conn.createStatement();
            //3
            String sql = "select * from t_money;";
            rs = stat.executeQuery(sql);
            //4
            while(rs.next()) {
                int uid = rs.getInt("uid");
                String name = rs.getString("name");
                long money = rs.getLong("money");
                System.out.println(uid + "---" + name + "----" + money);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtilDemo2.release(conn, stat, rs);
        }
    }
}
