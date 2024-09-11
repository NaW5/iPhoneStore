package GUI.Dialog.SanPhamDialog;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import DAO.SanPhamDAO;
import DAO.ctSanPhamDAO;
////Sửa chỗ này///////////////////////////////////////////////////////////////////////////////////////////////

import DTO.SanPhamDTO;
import DTO.ctSanPhamDTO;
import helper.ExtractString;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class suaSanPham_Dialog extends JDialog{
	private JTextField txt_tensp;
	private JTextField txt_gianhap;
	private JTextField txt_giaban;
	private JTextField txt_soluong;
	private JTextField txt_chip;
	private JTextField txt_pin;
	private JTextField txt_hdh;
	private JTextField txt_camerasau;
	private JTextField txt_cameratruoc;
	private String imagePath = "null";
	private int idSP;

	public boolean isNumeric(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public suaSanPham_Dialog(int idSP) {
		ExtractString extractString = new ExtractString();
		SanPhamDTO spdto = SanPhamDAO.getInstance().selectById(idSP);
		ctSanPhamDTO ctspdto = ctSanPhamDAO.getInstance().selectById(idSP);
		////Sửa chỗ này///////////////////////////////////////////////////////////////////////////////////////////////

		getContentPane().setLayout(null);
		JLabel lbl_hinhAnh = new JLabel("");
		lbl_hinhAnh.setIcon(new ImageIcon("C:\\Users\\Smile\\eclipse-workspace\\MobileStore\\" + spdto.getHinhAnh()));
		lbl_hinhAnh.setBounds(79, 90, 221, 272);
		getContentPane().add(lbl_hinhAnh);
		JLabel lbl_suasp = new JLabel("SỬA SẢN PHẨM " + String.valueOf(idSP));
		lbl_suasp.setBounds(0, 0, 1048, 17);
		lbl_suasp.setBackground(new Color(128, 255, 255));
		lbl_suasp.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_suasp.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lbl_suasp);

		JButton btn_hinhAnh = new JButton("Hình minh họa");
		btn_hinhAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg");
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showOpenDialog(suaSanPham_Dialog.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					imagePath = fileChooser.getSelectedFile().getAbsolutePath();
					ImageIcon imageIcon = new ImageIcon(imagePath);
					lbl_hinhAnh.setIcon(imageIcon);
				}
			}
		});
		btn_hinhAnh.setBounds(125, 56, 129, 23);
		getContentPane().add(btn_hinhAnh);


		JLabel lbl_tensp = new JLabel("Tên sản phẩm");
		lbl_tensp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_tensp.setBounds(383, 59, 93, 14);
		getContentPane().add(lbl_tensp);

		txt_tensp = new JTextField(spdto.getTenSP());
		txt_tensp.setBounds(384, 84, 129, 30);
		getContentPane().add(txt_tensp);
		txt_tensp.setColumns(10);

		JLabel lbl_gianhap = new JLabel("Giá nhập");
		lbl_gianhap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_gianhap.setBounds(384, 142, 93, 14);
		getContentPane().add(lbl_gianhap);

		txt_gianhap = new JTextField(String.format("%.0f", spdto.getGiaNhap()));
		txt_gianhap.setColumns(10);
		txt_gianhap.setBounds(384, 167, 129, 30);
		getContentPane().add(txt_gianhap);

		JLabel lbl_giaban = new JLabel("Giá bán");
		lbl_giaban.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_giaban.setBounds(384, 222, 93, 14);
		getContentPane().add(lbl_giaban);

		txt_giaban = new JTextField(String.format("%.0f", spdto.getGiaBan()));
		txt_giaban.setColumns(10);
		txt_giaban.setBounds(384, 247, 129, 30);
		getContentPane().add(txt_giaban);

		JLabel lbl_soluong = new JLabel("Số lượng");
		lbl_soluong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_soluong.setBounds(383, 307, 93, 14);
		getContentPane().add(lbl_soluong);

		txt_soluong = new JTextField(String.valueOf(spdto.getSoLuong()));
		txt_soluong.setColumns(10);
		txt_soluong.setBounds(383, 332, 129, 30);
		getContentPane().add(txt_soluong);

		JLabel lbl_mausac = new JLabel("Màu sắc");
		lbl_mausac.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_mausac.setBounds(607, 60, 93, 14);
		getContentPane().add(lbl_mausac);

		JComboBox cbb_mausac = new JComboBox();
		cbb_mausac.setModel(new DefaultComboBoxModel(new String[] {"Trắng", "Đen", "Vàng", "Xanh", "Xanh lá", "Hồng", "Tím", "Xám", "Đỏ"}));
		cbb_mausac.setBounds(607, 84, 93, 30);
		getContentPane().add(cbb_mausac);
		cbb_mausac.setSelectedItem(spdto.getMauSac());

		JLabel lbl_chip = new JLabel("Chip xử lý");
		lbl_chip.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_chip.setBounds(607, 142, 93, 14);
		getContentPane().add(lbl_chip);

		txt_chip = new JTextField();
		txt_chip.setColumns(10);
		txt_chip.setBounds(607, 166, 129, 30);
		getContentPane().add(txt_chip);
		txt_chip.setText(ctspdto.getChip());

		JLabel lbl_pin = new JLabel("Dung lượng pin");
		lbl_pin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_pin.setBounds(607, 222, 115, 14);
		getContentPane().add(lbl_pin);

		txt_pin = new JTextField();
		txt_pin.setColumns(10);
		txt_pin.setBounds(607, 246, 129, 30);
		getContentPane().add(txt_pin);
		txt_pin.setText(ctspdto.getPin());

		JLabel lbl_manhinh = new JLabel("Kích thước màn hình");
		lbl_manhinh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_manhinh.setBounds(607, 307, 148, 14);
		getContentPane().add(lbl_manhinh);

		JComboBox cbb_manhinh = new JComboBox();
		cbb_manhinh.setModel(new DefaultComboBoxModel(new String[] {"6.1 Inch", "6.7 Inch"}));
		cbb_manhinh.setBounds(607, 331, 93, 30);
		getContentPane().add(cbb_manhinh);
		cbb_manhinh.setSelectedItem(ctspdto.getManHinh());

		JLabel lbl_hdh = new JLabel("Hệ điều hành");
		lbl_hdh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_hdh.setBounds(828, 59, 93, 14);
		getContentPane().add(lbl_hdh);

		txt_hdh = new JTextField();
		txt_hdh.setColumns(10);
		txt_hdh.setBounds(828, 83, 129, 30);
		getContentPane().add(txt_hdh);
		txt_hdh.setText(ctspdto.getPhienBanHDH());

		JLabel lbl_camerasau = new JLabel("Camera sau");
		lbl_camerasau.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_camerasau.setBounds(828, 142, 93, 14);
		getContentPane().add(lbl_camerasau);

		txt_camerasau = new JTextField();
		txt_camerasau.setColumns(10);
		txt_camerasau.setBounds(828, 166, 129, 30);
		getContentPane().add(txt_camerasau);
		txt_camerasau.setText(ctspdto.getCameraSau());

		JLabel lbl_cameratruoc = new JLabel("Camera trước");
		lbl_cameratruoc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_cameratruoc.setBounds(828, 222, 93, 14);
		getContentPane().add(lbl_cameratruoc);

		txt_cameratruoc = new JTextField();
		txt_cameratruoc.setColumns(10);
		txt_cameratruoc.setBounds(828, 246, 129, 30);
		getContentPane().add(txt_cameratruoc);
		txt_cameratruoc.setText(ctspdto.getCameraTruoc());

		JLabel lbl_ram = new JLabel("Ram");
		lbl_ram.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_ram.setBounds(765, 307, 93, 14);
		getContentPane().add(lbl_ram);

		JLabel lbl_rom = new JLabel("Rom");
		lbl_rom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_rom.setBounds(916, 307, 93, 14);
		getContentPane().add(lbl_rom);

		JComboBox cbb_rom = new JComboBox();
		cbb_rom.setModel(new DefaultComboBoxModel(new String[] {"64GB", "128GB", "512GB", "1TB"}));
		cbb_rom.setBounds(916, 331, 93, 30);
		getContentPane().add(cbb_rom);
		cbb_rom.setSelectedItem(ctspdto.getRom());

		JComboBox cbb_ram = new JComboBox();
		cbb_ram.setModel(new DefaultComboBoxModel(new String[] {"2GB", "3GB", "4GB", "6GB"}));
		cbb_ram.setBounds(765, 331, 93, 30);
		getContentPane().add(cbb_ram);
		cbb_ram.setSelectedItem(ctspdto.getRam());

		JButton btn_sua = new JButton("Sửa");
		btn_sua.setBounds(383, 407, 129, 23);
		getContentPane().add(btn_sua);
		btn_sua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pattern = "^(iPhone)\\s(11|12|13|14|15|X|XS|XR|XS\\sMax)(\\s(Plus|Pro|mini))?(\\s\\(\\d{4}\\))?$";


				// Kiểm tra đầu vào với biểu thức chính quy
				Pattern regex = Pattern.compile(pattern);
				Matcher matcher = regex.matcher(txt_tensp.getText());


				if(!matcher.find()) {
					JOptionPane.showMessageDialog(null, "Tên sản phẩm không hợp lệ");
				}

				else if(!isNumeric(txt_gianhap.getText())) {
					JOptionPane.showMessageDialog(null, "Giá nhập phải là kiểu số");
				}

				else if(!isNumeric(txt_giaban.getText())) {
					JOptionPane.showMessageDialog(null, "Giá bán phải là kiểu số");
				}
				else if(!isNumeric(txt_soluong.getText())) {
					JOptionPane.showMessageDialog(null, "Số lượng phải là kiểu số");
				}
				else if(txt_chip.getText().equals("") || txt_pin.getText().equals("") || txt_hdh.getText().equals("") || txt_camerasau.getText().equals("") || txt_cameratruoc.getText().equals("") || imagePath.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
				}
				else {
					int idsp = idSP;
					String tensp = txt_tensp.getText();
					int giaNhap = Integer.parseInt(txt_gianhap.getText());
					int giaBan = Integer.parseInt(txt_giaban.getText());
					int soLuong = Integer.parseInt(txt_soluong.getText());
					String hinhAnh ="";
					if (imagePath.equals("null")) {
						hinhAnh= spdto.getHinhAnh();
					}
					else {
						hinhAnh = extractString.catLinkAnh(imagePath);
					}
					String mauSac = (String) cbb_mausac.getSelectedItem();
					SanPhamDTO spdto = new SanPhamDTO(idsp, tensp, giaNhap, giaBan, soLuong, hinhAnh, mauSac, 0);
					SanPhamDAO.getInstance().update(spdto);
					////Sửa chỗ này///////////////////////////////////////////////////////////////////////////////////////////////


					String chip = txt_chip.getText();
					String pin = txt_chip.getText();
					String manHinh = (String) cbb_manhinh.getSelectedItem();
					String hdh = txt_hdh.getText();
					String sau = txt_camerasau.getText();
					String truoc = txt_cameratruoc.getText();
					String ram = (String) cbb_ram.getSelectedItem();
					String rom = (String) cbb_rom.getSelectedItem();
					ctSanPhamDTO ctsp = new ctSanPhamDTO(chip, pin, manHinh, hdh, sau, truoc, ram, rom, idsp);
					ctSanPhamDAO.getInstance().update(ctsp);
					////Sửa chỗ này///////////////////////////////////////////////////////////////////////////////////////////////

				}
			}
		});

		JButton btn_huybo = new JButton("Hủy bỏ");
		btn_huybo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_huybo.setBounds(559, 407, 129, 23);
		getContentPane().add(btn_huybo);
	}

}

