package GUI.Main;

import GUI.Panel.NhanVien;
import GUI.Panel.TaiKhoan;
//import GUI.Panel.TaiKhoan;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Test extends JFrame {
    private Panel panel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Test frame = new Test();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Test(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 900); // Increase the size of the JFrame
        panel = new Panel();
//        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        NhanVien nhanVienPanel = new NhanVien();
        nhanVienPanel.setBounds(50, 100, 800, 700); // Increase the size of the NhanVien panel
        nhanVienPanel.setOpaque(false); // Make the panel transparent
        panel.add(nhanVienPanel); // Add the panel to the contentPane
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setVisible(true);
//        TaiKhoan taiKhoanPanel = new TaiKhoan();
//        taiKhoanPanel.setBounds(50, 100, 800, 700); // Increase the size of the NhanVien panel
//        taiKhoanPanel.setOpaque(false); // Make the panel transparent
//        panel.add(taiKhoanPanel); // Add the panel to the contentPane
//        setLocationRelativeTo(null); // Center the JFrame on the screen
//        setVisible(true);
    }

}
