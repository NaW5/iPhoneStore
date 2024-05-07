package config;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtil {
    public static Connection getConnection(){
        Connection kq = null;
        try{
            // Đăng ký Mysql Driver với driveManager
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://localhost:3306/quanlycuahang";
            String user = "root";
            String password = "";
            //tạo kết nối
            kq = DriverManager.getConnection(url, user, password);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Kết nối thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return kq;
    }

    public static void closeConnection(Connection con){
        try{
            if(con != null){
                con.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
