package GUI.JPanel_QuanLyCuaHangDienThoai;

import javax.swing.*;
import java.awt.*;

public class TrangChuGUI extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public TrangChuGUI() {
		setLayout(new BorderLayout());
		JLabel jL = new JLabel("");
		jL.setIcon(new ImageIcon("D:\\University\\Second year\\2nd Semester\\Java\\DoAn_Java\\img\\login.jpg"));
		add(jL, BorderLayout.CENTER);

	}

}
