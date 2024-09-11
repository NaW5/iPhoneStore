package BUS;

import javax.swing.JFrame;

import GUI.JPanel_QuanLyCuaHangDienThoai.SanPhamGUI;

public class temp {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Your Frame Title");
	    SanPhamGUI panel = new SanPhamGUI();
	    frame.getContentPane().add(panel);
	    frame.pack();
	    frame.setVisible(true);
	}
}
