package GUI.JPanel_QuanLyCuaHangDienThoai;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class AdminGUI extends JPanel{
	public AdminGUI() {
		
		JLabel lblNewLabel = new JLabel("admin");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 90));
		add(lblNewLabel);
	}

}
