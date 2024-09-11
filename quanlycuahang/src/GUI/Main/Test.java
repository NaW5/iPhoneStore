package GUI.Main;

import GUI.JPanel_QuanLyCuaHangDienThoai.Login_GUI;

import javax.swing.*;

public class Test extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Đăng Nhập");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 460);
        frame.getContentPane().add(new Login_GUI(frame));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}