package GUI.Dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;

public class DangKy_Dialog extends JDialog {
	private JTextField txt_maNV;
	private JTextField txt_username;
	private JPasswordField passwordField;
	private JPasswordField rePasswordField;
	private JCheckBox showPasswordCheckBox;
	public NhanVienBUS nvBUS = new NhanVienBUS();
	public TaiKhoanBUS tkBUS = new TaiKhoanBUS();

	public DangKy_Dialog() {
		setTitle("Đăng ký");
		setSize(900, 460);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel lblTitle = new JLabel("ĐĂNG KÝ");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		mainPanel.add(lblTitle, gbc);

		gbc.gridwidth = 1;
		gbc.gridy++;

		JLabel lbl_maNV = new JLabel("Mã nhân viên:");
		lbl_maNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gbc.gridx = 0;
		mainPanel.add(lbl_maNV, gbc);

		txt_maNV = new JTextField(10);
		gbc.gridx = 1;
		mainPanel.add(txt_maNV, gbc);

		gbc.gridy++;
		gbc.gridx = 0;

		JLabel lbl_username = new JLabel("Username:");
		lbl_username.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainPanel.add(lbl_username, gbc);

		txt_username = new JTextField(10);
		gbc.gridx = 1;
		mainPanel.add(txt_username, gbc);

		gbc.gridy++;
		gbc.gridx = 0;

		JLabel lbl_password = new JLabel("Mật khẩu:");
		lbl_password.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainPanel.add(lbl_password, gbc);

		passwordField = new JPasswordField(10);
		gbc.gridx = 1;
		mainPanel.add(passwordField, gbc);

		gbc.gridy++;
		gbc.gridx = 0;

		JLabel lbl_rePassword = new JLabel("Nhập lại mật khẩu:");
		lbl_rePassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainPanel.add(lbl_rePassword, gbc);

		rePasswordField = new JPasswordField(10);
		gbc.gridx = 1;
		mainPanel.add(rePasswordField, gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		gbc.gridwidth = 2;

		showPasswordCheckBox = new JCheckBox("Hiện mật khẩu");
		showPasswordCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (showPasswordCheckBox.isSelected()) {
					passwordField.setEchoChar((char) 0);
					rePasswordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
					rePasswordField.setEchoChar('*');
				}
			}
		});
		mainPanel.add(showPasswordCheckBox, gbc);

		gbc.gridy++;

		JButton btn_dangKy = new JButton("Đăng ký");
		btn_dangKy.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_dangKy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleDangKy();
			}
		});
		mainPanel.add(btn_dangKy, gbc);

		add(mainPanel, BorderLayout.CENTER);
	}

	private boolean validateMaNV() {
		int idNV;
		try {
			idNV = Integer.parseInt(txt_maNV.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Mã nhân viên phải là số!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txt_maNV.requestFocus();
			return false;
		}
		ArrayList<NhanVienDTO> nvList = nvBUS.getAll();
		for (NhanVienDTO nv : nvList) {
			if (nv.getIdNV() == idNV) {
				return true;
			}
		}
		JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại trong cửa hàng!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
		txt_maNV.requestFocus();
		return false;
	}

	private boolean validateUsername() {
		ArrayList<TaiKhoanDTO> tkList = tkBUS.selectAll();
		String pattern = "^(?!.*\\s)(?!.*[àáảãạâầấẩẫậăằắẳẵặèéẻẽẹêềếểễệđìíỉĩịòóỏõọôồốổỗộơờớởỡợùúủũụưừứửữựỳýỷỹỵ]).*$";
		Pattern regex = Pattern.compile(pattern);
		Matcher matcher = regex.matcher(txt_username.getText());
		for (TaiKhoanDTO tk : tkList) {
			if (txt_username.getText().equals(tk.getUsername())) {
				JOptionPane.showMessageDialog(null, "Username đã tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		if (!matcher.find()) {
			JOptionPane.showMessageDialog(null, "Username không hợp lệ");
			return false;
		}
		return true;
	}

	private boolean validatePassword() {
		if (passwordField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu");
			return false;
		}
		return true;
	}

	private boolean validateRePassword() {
		if (!passwordField.getText().equals(rePasswordField.getText())) {
			JOptionPane.showMessageDialog(null, "Mật khẩu không trùng khớp!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private void handleDangKy() {
		if (!validateMaNV() || !validateUsername() || !validatePassword() || !validateRePassword()) {
			return;
		}

		int idNV = Integer.parseInt(txt_maNV.getText());
		ArrayList<NhanVienDTO> nvList = nvBUS.getAll();
		ArrayList<TaiKhoanDTO> tkList = tkBUS.selectAll();
		boolean flagNV = false;
		boolean flagTK = true;

		for (NhanVienDTO nv : nvList) {
			if (nv.getIdNV() == idNV) {
				flagNV = true;
				break;
			}
		}
		for (TaiKhoanDTO tk : tkList) {
			if (idNV == tk.getNHANVIEN_idNV()) {
				flagTK = false;
				break;
			}
		}
		if (!flagNV) {
			JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại trong cửa hàng!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txt_maNV.requestFocus();
		} else if (!flagTK) {
			JOptionPane.showMessageDialog(null, "Nhân viên này đã có tài khoản!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txt_maNV.requestFocus();
		} else {
			String userName = txt_username.getText();
			String password = passwordField.getText();
			int trangThai = 1;
			TaiKhoanDTO tk = new TaiKhoanDTO(userName, password, trangThai, idNV);
			tkBUS.insert(tk);
			JOptionPane.showMessageDialog(null, "Đăng ký thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			//close the dialog
			dispose();
		}
	}
}