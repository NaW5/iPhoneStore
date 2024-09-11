package BUS;

import GUI.JPanel_QuanLyCuaHangDienThoai.SanPhamGUI;

import javax.swing.*;

public class temp {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Your Frame Title");
	    SanPhamGUI panel = new SanPhamGUI();
	    frame.getContentPane().add(panel);
	    frame.pack();
	    frame.setVisible(true);
	}
}
