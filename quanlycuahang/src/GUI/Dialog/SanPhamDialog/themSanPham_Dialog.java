package GUI.Dialog.SanPhamDialog;

import BUS.IMEIBUS;
import BUS.SanPhamBUS;
import BUS.ctSanPhamBUS;
import DAO.IMEIDAO;
import DAO.SanPhamDAO;
import DTO.IMEIDTO;
import DTO.SanPhamDTO;
import DTO.ctSanPhamDTO;
import helper.ExtractString;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class themSanPham_Dialog extends JDialog{
	private JTextField txt_tensp;
	private JTextField txt_gianhap;
	private JTextField txt_giaban;
	private JTextField txt_soluong;
	private JTextField txt_chip;
	private JTextField txt_pin;
	private JTextField txt_hdh;
	private JTextField txt_camerasau;
	private JTextField txt_cameratruoc;
	private String imagePath;
	public SanPhamBUS spBUS = new SanPhamBUS();
	public ctSanPhamBUS ctspBUS = new ctSanPhamBUS();
	public IMEIBUS imeiBUS = new IMEIBUS();
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

	public themSanPham_Dialog() {
		ExtractString extractString = new ExtractString();
		getContentPane().setLayout(null);
		JLabel lbl_hinhAnh = new JLabel("");
		lbl_hinhAnh.setIcon(new ImageIcon(imagePath));
		lbl_hinhAnh.setBounds(60, 89, 240, 272);
		getContentPane().add(lbl_hinhAnh);
		JLabel lbl_themsp = new JLabel("THÊM SẢN PHẨM MỚI");
		lbl_themsp.setBounds(10, 11, 1170, 17);
		lbl_themsp.setBackground(new Color(128, 255, 255));
		lbl_themsp.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_themsp.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lbl_themsp);

		JButton btn_hinhAnh = new JButton("Hình minh họa");
		btn_hinhAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg");
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showOpenDialog(themSanPham_Dialog.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					imagePath = fileChooser.getSelectedFile().getAbsolutePath();
					ImageIcon imageIcon = new ImageIcon(imagePath);
					lbl_hinhAnh.setIcon(imageIcon);
				}
			}
		});
		btn_hinhAnh.setBounds(115, 55, 129, 23);
		getContentPane().add(btn_hinhAnh);


		JLabel lbl_tensp = new JLabel("Tên sản phẩm");
		lbl_tensp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_tensp.setBounds(383, 58, 93, 14);
		getContentPane().add(lbl_tensp);

		txt_tensp = new JTextField();
		txt_tensp.setBounds(384, 83, 129, 30);
		getContentPane().add(txt_tensp);
		txt_tensp.setColumns(10);

		JLabel lbl_gianhap = new JLabel("Giá nhập");
		lbl_gianhap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_gianhap.setBounds(384, 141, 93, 14);
		getContentPane().add(lbl_gianhap);

		txt_gianhap = new JTextField();
		txt_gianhap.setColumns(10);
		txt_gianhap.setBounds(384, 166, 129, 30);
		getContentPane().add(txt_gianhap);

		JLabel lbl_giaban = new JLabel("Giá bán");
		lbl_giaban.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_giaban.setBounds(384, 221, 93, 14);
		getContentPane().add(lbl_giaban);

		txt_giaban = new JTextField();
		txt_giaban.setColumns(10);
		txt_giaban.setBounds(384, 246, 129, 30);
		getContentPane().add(txt_giaban);

		JLabel lbl_IMEI = new JLabel("Mã IMEI");
		lbl_IMEI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_IMEI.setBounds(384, 307, 93, 14);
		getContentPane().add(lbl_IMEI);

		JTextField txt_IMEI = new JTextField();
		txt_IMEI.setColumns(10);
		txt_IMEI.setBounds(384, 331, 129, 30);
		getContentPane().add(txt_IMEI);

		JLabel lbl_mausac = new JLabel("Màu sắc");
		lbl_mausac.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_mausac.setBounds(607, 59, 93, 14);
		getContentPane().add(lbl_mausac);

		JComboBox cbb_mausac = new JComboBox();
		cbb_mausac.setModel(new DefaultComboBoxModel(new String[] {"Trắng", "Đen", "Vàng", "Xanh", "Xanh lá", "Hồng", "Tím"}));
		cbb_mausac.setBounds(607, 83, 93, 30);
		getContentPane().add(cbb_mausac);

		JLabel lbl_chip = new JLabel("Chip xử lý");
		lbl_chip.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_chip.setBounds(607, 142, 93, 14);
		getContentPane().add(lbl_chip);

		txt_chip = new JTextField();
		txt_chip.setColumns(10);
		txt_chip.setBounds(607, 166, 129, 30);
		getContentPane().add(txt_chip);

		JLabel lbl_pin = new JLabel("Dung lượng pin");
		lbl_pin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_pin.setBounds(607, 222, 115, 14);
		getContentPane().add(lbl_pin);

		txt_pin = new JTextField();
		txt_pin.setColumns(10);
		txt_pin.setBounds(607, 246, 129, 30);
		getContentPane().add(txt_pin);

		JLabel lbl_manhinh = new JLabel("Kích thước màn hình");
		lbl_manhinh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_manhinh.setBounds(607, 307, 148, 14);
		getContentPane().add(lbl_manhinh);

		JComboBox cbb_manhinh = new JComboBox();
		cbb_manhinh.setModel(new DefaultComboBoxModel(new String[] {"6.1 Inch", "6.7 Inch"}));
		cbb_manhinh.setBounds(607, 331, 93, 30);
		getContentPane().add(cbb_manhinh);

		JLabel lbl_hdh = new JLabel("Hệ điều hành");
		lbl_hdh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_hdh.setBounds(828, 59, 93, 14);
		getContentPane().add(lbl_hdh);

		txt_hdh = new JTextField();
		txt_hdh.setColumns(10);
		txt_hdh.setBounds(828, 83, 129, 30);
		getContentPane().add(txt_hdh);

		JLabel lbl_camerasau = new JLabel("Camera sau");
		lbl_camerasau.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_camerasau.setBounds(828, 142, 93, 14);
		getContentPane().add(lbl_camerasau);

		txt_camerasau = new JTextField();
		txt_camerasau.setColumns(10);
		txt_camerasau.setBounds(828, 166, 129, 30);
		getContentPane().add(txt_camerasau);

		JLabel lbl_cameratruoc = new JLabel("Camera trước");
		lbl_cameratruoc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_cameratruoc.setBounds(828, 222, 93, 14);
		getContentPane().add(lbl_cameratruoc);

		txt_cameratruoc = new JTextField();
		txt_cameratruoc.setColumns(10);
		txt_cameratruoc.setBounds(828, 246, 129, 30);
		getContentPane().add(txt_cameratruoc);

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

		JComboBox cbb_ram = new JComboBox();
		cbb_ram.setModel(new DefaultComboBoxModel(new String[] {"2GB", "3GB", "4GB", "6GB"}));
		cbb_ram.setBounds(765, 331, 93, 30);
		getContentPane().add(cbb_ram);

		JButton btn_them = new JButton("Thêm");
		btn_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pattern = "^(iPhone)\\s(11|12|13|14|15|X|XS|XR|XS\\sMax)(\\s(Plus|Pro|mini))?(\\s\\(\\d{4}\\))?$";
				Pattern regex = Pattern.compile(pattern);
				Matcher matcher = regex.matcher(txt_tensp.getText());
				if(!matcher.find()) {
					JOptionPane.showMessageDialog(null, "Tên sản phẩm không hợp lệ");
				}
				else if(!isNumeric(txt_gianhap.getText())) {
					JOptionPane.showMessageDialog(null, "Giá nhập phải là số");
				}
				else if(!isNumeric(txt_giaban.getText())) {
					JOptionPane.showMessageDialog(null, "Giá bán phải là số");
				}
				else if(txt_chip.getText().equals("") || txt_pin.getText().equals("") || txt_hdh.getText().equals("") || txt_camerasau.getText().equals("") || txt_cameratruoc.getText().equals("") || imagePath.equals("") || txt_IMEI.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
				}
				else {
					int idsp = SanPhamDAO.getInstance().selectAllAll().get(SanPhamDAO.getInstance().selectAllAll().size()-1).getIdSP() +1;
					String tensp = txt_tensp.getText();
					int giaNhap = Integer.parseInt(txt_gianhap.getText());
					int giaBan = Integer.parseInt(txt_giaban.getText());
					String mauSac = (String) cbb_mausac.getSelectedItem();
					String hinhAnh =  extractString.catLinkAnh(imagePath);
					SanPhamDTO spdto = new SanPhamDTO(idsp, tensp, giaNhap, giaBan, 0, hinhAnh, mauSac, 0);
					spBUS.themSanPham(spdto);

					String chip = txt_chip.getText();
					String pin = txt_pin.getText();
					String manHinh = (String) cbb_manhinh.getSelectedItem();
					String hdh = txt_hdh.getText();
					String cameraSau = txt_camerasau.getText();
					String cameraTruoc = txt_cameratruoc.getText();
					String ram = (String) cbb_ram.getSelectedItem();
					String rom = (String) cbb_rom.getSelectedItem();
					int SANPHAM_idSP = idsp;
					ctSanPhamDTO ctspdto = new ctSanPhamDTO(chip, pin, manHinh, hdh, cameraSau, cameraTruoc, ram, rom, SANPHAM_idSP);
					ctspBUS.themctSanPham(ctspdto);

					int maIMEI = Integer.parseInt(txt_IMEI.getText());
					IMEIDTO imei = new IMEIDTO(maIMEI, idsp);
					imeiBUS.themIMEI(imei);
				}
			}
		});
		btn_them.setBounds(450, 400, 129, 23);
		getContentPane().add(btn_them);

		JButton btn_huybo = new JButton("Hủy bỏ");
		btn_huybo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_huybo.setBounds(627, 400, 129, 23);
		getContentPane().add(btn_huybo);
	}



	public themSanPham_Dialog(int idSP) {
		SanPhamDTO spdto = spBUS.laySanPhamTheoId(idSP);
		ctSanPhamDTO ctspdto = ctspBUS.timctSanPhamTheoId(idSP);
		getContentPane().setLayout(null);
		JLabel lbl_hinhAnh = new JLabel("");
		lbl_hinhAnh.setIcon(new ImageIcon("C:\\Users\\Smile\\eclipse-workspace\\MobileStore\\" + spdto.getHinhAnh()));
		lbl_hinhAnh.setBounds(60, 89, 240, 272);
		getContentPane().add(lbl_hinhAnh);
		JLabel lbl_themsp = new JLabel("THÊM IMEI MỚI VÀO SẢN PHẨM " + String.valueOf(idSP));
		lbl_themsp.setBounds(0, 0, 1084, 17);
		lbl_themsp.setBackground(new Color(128, 255, 255));
		lbl_themsp.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_themsp.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lbl_themsp);

		JButton btn_hinhAnh = new JButton("Hình minh họa");
		btn_hinhAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg");
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showOpenDialog(themSanPham_Dialog.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					imagePath = fileChooser.getSelectedFile().getAbsolutePath();
					ImageIcon imageIcon = new ImageIcon(imagePath);
					lbl_hinhAnh.setIcon(imageIcon);
				}
			}
		});
		btn_hinhAnh.setBounds(115, 55, 129, 23);
		getContentPane().add(btn_hinhAnh);
		btn_hinhAnh.setEnabled(false);

		JLabel lbl_tensp = new JLabel("Tên sản phẩm");
		lbl_tensp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_tensp.setBounds(383, 58, 93, 14);
		getContentPane().add(lbl_tensp);

		txt_tensp = new JTextField(spdto.getTenSP());
		txt_tensp.setBounds(384, 83, 129, 30);
		getContentPane().add(txt_tensp);
		txt_tensp.setColumns(10);
		txt_tensp.setEditable(false);

		JLabel lbl_gianhap = new JLabel("Giá nhập");
		lbl_gianhap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_gianhap.setBounds(384, 141, 93, 14);
		getContentPane().add(lbl_gianhap);

		txt_gianhap = new JTextField(String.format("%.0f", spdto.getGiaBan()));
		txt_gianhap.setColumns(10);
		txt_gianhap.setBounds(384, 166, 129, 30);
		getContentPane().add(txt_gianhap);
		txt_gianhap.setEditable(false);

		JLabel lbl_giaban = new JLabel("Giá bán");
		lbl_giaban.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_giaban.setBounds(384, 221, 93, 14);
		getContentPane().add(lbl_giaban);

		txt_giaban = new JTextField(String.format("%.0f", spdto.getGiaNhap()));
		txt_giaban.setColumns(10);
		txt_giaban.setBounds(384, 246, 129, 30);
		getContentPane().add(txt_giaban);
		txt_giaban.setEditable(false);

		JLabel lbl_IMEI = new JLabel(" Mã IMEI");
		lbl_IMEI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_IMEI.setBounds(384, 307, 93, 14);
		getContentPane().add(lbl_IMEI);

		JTextField txt_IMEI = new JTextField();
		txt_IMEI.setColumns(10);
		txt_IMEI.setBounds(384, 331, 129, 30);
		getContentPane().add(txt_IMEI);

		JLabel lbl_mausac = new JLabel("Màu sắc");
		lbl_mausac.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_mausac.setBounds(607, 59, 93, 14);
		getContentPane().add(lbl_mausac);

		JComboBox cbb_mausac = new JComboBox();
		cbb_mausac.setModel(new DefaultComboBoxModel(new String[] {"Trắng", "Đen", "Vàng", "Xanh", "Xanh lá", "Hồng", "Tím", "Xám", "Đỏ"}));
		cbb_mausac.setBounds(607, 83, 93, 30);
		getContentPane().add(cbb_mausac);
		cbb_mausac.setSelectedItem(spdto.getMauSac());
		cbb_mausac.setEditable(false);

		JLabel lbl_chip = new JLabel("Chip xử lý");
		lbl_chip.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_chip.setBounds(607, 142, 93, 14);
		getContentPane().add(lbl_chip);

		txt_chip = new JTextField(String.valueOf(ctspdto.getChip()));
		txt_chip.setColumns(10);
		txt_chip.setBounds(607, 166, 129, 30);
		getContentPane().add(txt_chip);
		txt_chip.setEditable(false);

		JLabel lbl_pin = new JLabel("Dung lượng pin");
		lbl_pin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_pin.setBounds(607, 222, 115, 14);
		getContentPane().add(lbl_pin);

		txt_pin = new JTextField(String.valueOf(ctspdto.getPin()));
		txt_pin.setColumns(10);
		txt_pin.setBounds(607, 246, 129, 30);
		getContentPane().add(txt_pin);
		txt_pin.setEditable(false);

		JLabel lbl_manhinh = new JLabel("Kích thước màn hình");
		lbl_manhinh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_manhinh.setBounds(607, 307, 148, 14);
		getContentPane().add(lbl_manhinh);

		JComboBox cbb_manhinh = new JComboBox();
		cbb_manhinh.setModel(new DefaultComboBoxModel(new String[] {"6.1 Inch", "6.7 Inch"}));
		cbb_manhinh.setBounds(607, 331, 93, 30);
		getContentPane().add(cbb_manhinh);
		cbb_manhinh.setSelectedItem(String.valueOf(ctspdto.getManHinh()));
		cbb_manhinh.setEditable(false);

		JLabel lbl_hdh = new JLabel("Hệ điều hành");
		lbl_hdh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_hdh.setBounds(828, 59, 93, 14);
		getContentPane().add(lbl_hdh);

		txt_hdh = new JTextField(String.valueOf(ctspdto.getPhienBanHDH()));
		txt_hdh.setColumns(10);
		txt_hdh.setBounds(828, 83, 129, 30);
		getContentPane().add(txt_hdh);
		txt_hdh.setEditable(false);

		JLabel lbl_camerasau = new JLabel("Camera sau");
		lbl_camerasau.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_camerasau.setBounds(828, 142, 93, 14);
		getContentPane().add(lbl_camerasau);

		txt_camerasau = new JTextField(String.valueOf(ctspdto.getCameraSau()));
		txt_camerasau.setColumns(10);
		txt_camerasau.setBounds(828, 166, 129, 30);
		getContentPane().add(txt_camerasau);
		txt_camerasau.setEditable(false);

		JLabel lbl_cameratruoc = new JLabel("Camera trước");
		lbl_cameratruoc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_cameratruoc.setBounds(828, 222, 93, 14);
		getContentPane().add(lbl_cameratruoc);

		txt_cameratruoc = new JTextField(String.valueOf(ctspdto.getCameraTruoc()));
		txt_cameratruoc.setColumns(10);
		txt_cameratruoc.setBounds(828, 246, 129, 30);
		getContentPane().add(txt_cameratruoc);
		txt_cameratruoc.setEditable(false);

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
		cbb_rom.setSelectedItem(String.valueOf(ctspdto.getRom()));
		cbb_rom.setEditable(false);

		JComboBox cbb_ram = new JComboBox();
		cbb_ram.setModel(new DefaultComboBoxModel(new String[] {"2GB", "3GB", "4GB", "6GB"}));
		cbb_ram.setBounds(765, 331, 93, 30);
		getContentPane().add(cbb_ram);
		cbb_ram.setSelectedItem(String.valueOf(ctspdto.getRam()));
		cbb_ram.setEditable(false);

		JButton btn_them = new JButton("Thêm");
		btn_them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Thêm mới vào bảng IMEI
				int maIMEI = Integer.parseInt(txt_IMEI.getText());
				IMEIDTO IMEI = new IMEIDTO(maIMEI, idSP, 0);
				imeiBUS.themIMEI(IMEI);
			}
		});
		btn_them.setBounds(469, 400, 129, 23);
		getContentPane().add(btn_them);

		JButton btn_huybo = new JButton("Hủy bỏ");
		btn_huybo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_huybo.setBounds(637, 400, 129, 23);
		getContentPane().add(btn_huybo);
	}

}

