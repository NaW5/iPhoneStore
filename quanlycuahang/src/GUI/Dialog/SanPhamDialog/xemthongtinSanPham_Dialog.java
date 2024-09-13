package GUI.Dialog.SanPhamDialog;

import DAO.SanPhamDAO;
import DAO.ctSanPhamDAO;
import DTO.SanPhamDTO;
import DTO.ctSanPhamDTO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class xemthongtinSanPham_Dialog extends JDialog{
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
	private int idSP;

	public xemthongtinSanPham_Dialog(int idSP) {
		SanPhamDTO spdto = SanPhamDAO.getInstance().selectById(idSP);
		ctSanPhamDTO ctspdto = ctSanPhamDAO.getInstance().selectById(idSP);
		getContentPane().setLayout(null);
		JLabel lbl_hinhAnh = new JLabel("");
		lbl_hinhAnh.setIcon(new ImageIcon("D:\\University\\Second year\\2nd Semester\\Java\\DoAn_Java\\img\\" + spdto.getHinhAnh()));
		lbl_hinhAnh.setBounds(35, 117, 268, 308);
		getContentPane().add(lbl_hinhAnh);
		JLabel lbl_suasp = new JLabel("THÔNG TIN SẢN PHẨM " + String.valueOf(idSP));
		lbl_suasp.setBounds(0, 0, 1088, 17);
		lbl_suasp.setBackground(new Color(128, 255, 255));
		lbl_suasp.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_suasp.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lbl_suasp);

		JButton btn_hinhAnh = new JButton("Hình minh họa");
		btn_hinhAnh.setEnabled(false);
		btn_hinhAnh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg");
				fileChooser.setFileFilter(filter);
				int result = fileChooser.showOpenDialog(xemthongtinSanPham_Dialog.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					imagePath = fileChooser.getSelectedFile().getAbsolutePath();
					ImageIcon imageIcon = new ImageIcon(imagePath);
					lbl_hinhAnh.setIcon(imageIcon);
				}
			}
		});
		btn_hinhAnh.setBounds(85, 68, 129, 23);
		getContentPane().add(btn_hinhAnh);


		JLabel lbl_tensp = new JLabel("Tên sản phẩm");
		lbl_tensp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_tensp.setBounds(315, 59, 93, 14);
		getContentPane().add(lbl_tensp);

		txt_tensp = new JTextField(spdto.getTenSP());
		txt_tensp.setEditable(false);
		txt_tensp.setBounds(316, 84, 129, 30);
		getContentPane().add(txt_tensp);
		txt_tensp.setColumns(10);

		JLabel lbl_gianhap = new JLabel("Giá nhập");
		lbl_gianhap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_gianhap.setBounds(316, 142, 93, 14);
		getContentPane().add(lbl_gianhap);

		txt_gianhap = new JTextField(String.format("%.0f", spdto.getGiaNhap()));
		txt_gianhap.setEditable(false);
		txt_gianhap.setColumns(10);
		txt_gianhap.setBounds(316, 167, 129, 30);
		getContentPane().add(txt_gianhap);

		JLabel lbl_giaban = new JLabel("Giá bán");
		lbl_giaban.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_giaban.setBounds(316, 222, 93, 14);
		getContentPane().add(lbl_giaban);

		txt_giaban = new JTextField(String.format("%.0f", spdto.getGiaBan()));
		txt_giaban.setEditable(false);
		txt_giaban.setColumns(10);
		txt_giaban.setBounds(316, 247, 129, 30);
		getContentPane().add(txt_giaban);

		JLabel lbl_soluong = new JLabel("Số lượng");
		lbl_soluong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_soluong.setBounds(315, 307, 93, 14);
		getContentPane().add(lbl_soluong);

		txt_soluong = new JTextField(String.valueOf(spdto.getSoLuong()));
		txt_soluong.setEnabled(false);
		txt_soluong.setColumns(10);
		txt_soluong.setBounds(315, 332, 129, 30);
		getContentPane().add(txt_soluong);

		JLabel lbl_mausac = new JLabel("Màu sắc");
		lbl_mausac.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_mausac.setBounds(607, 59, 93, 14);
		getContentPane().add(lbl_mausac);

		JComboBox cbb_mausac = new JComboBox();
		cbb_mausac.setEnabled(false);
		cbb_mausac.setModel(new DefaultComboBoxModel(new String[] {"Trắng", "Đen", "Vàng", "Xanh", "Xanh lá", "Hồng", "Tím", "Xám", "Đỏ"}));
		cbb_mausac.setBounds(607, 83, 93, 30);
		getContentPane().add(cbb_mausac);
		cbb_mausac.setSelectedItem(spdto.getMauSac());
		JLabel lbl_chip = new JLabel("Chip xử lý");
		lbl_chip.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_chip.setBounds(607, 142, 93, 14);
		getContentPane().add(lbl_chip);

		txt_chip = new JTextField(String.valueOf(ctspdto.getChip()));
		txt_chip.setEditable(false);
		txt_chip.setColumns(10);
		txt_chip.setBounds(607, 166, 129, 30);
		getContentPane().add(txt_chip);

		JLabel lbl_pin = new JLabel("Dung lượng pin");
		lbl_pin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_pin.setBounds(607, 222, 115, 14);
		getContentPane().add(lbl_pin);

		txt_pin = new JTextField(String.valueOf(ctspdto.getPin()));
		txt_pin.setEditable(false);
		txt_pin.setColumns(10);
		txt_pin.setBounds(607, 246, 129, 30);
		getContentPane().add(txt_pin);

		JLabel lbl_manhinh = new JLabel("Kích thước màn hình");
		lbl_manhinh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_manhinh.setBounds(607, 307, 148, 14);
		getContentPane().add(lbl_manhinh);

		JComboBox cbb_manhinh = new JComboBox();
		cbb_manhinh.setEnabled(false);
		cbb_manhinh.setModel(new DefaultComboBoxModel(new String[] {"6.1 Inch", "6.7 Inch"}));
		cbb_manhinh.setBounds(607, 331, 93, 30);
		getContentPane().add(cbb_manhinh);
		cbb_manhinh.setSelectedItem(String.valueOf(ctspdto.getManHinh()));

		JLabel lbl_hdh = new JLabel("Hệ điều hành");
		lbl_hdh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_hdh.setBounds(828, 59, 93, 14);
		getContentPane().add(lbl_hdh);

		txt_hdh = new JTextField(String.valueOf(ctspdto.getPhienBanHDH()));
		txt_hdh.setEditable(false);
		txt_hdh.setColumns(10);
		txt_hdh.setBounds(828, 83, 129, 30);
		getContentPane().add(txt_hdh);

		JLabel lbl_camerasau = new JLabel("Camera sau");
		lbl_camerasau.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_camerasau.setBounds(828, 142, 93, 14);
		getContentPane().add(lbl_camerasau);

		txt_camerasau = new JTextField(String.valueOf(ctspdto.getCameraSau()));
		txt_camerasau.setEditable(false);
		txt_camerasau.setColumns(10);
		txt_camerasau.setBounds(828, 166, 129, 30);
		getContentPane().add(txt_camerasau);

		JLabel lbl_cameratruoc = new JLabel("Camera trước");
		lbl_cameratruoc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbl_cameratruoc.setBounds(828, 222, 93, 14);
		getContentPane().add(lbl_cameratruoc);

		txt_cameratruoc = new JTextField(String.valueOf(ctspdto.getCameraTruoc()));
		txt_cameratruoc.setEditable(false);
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
		cbb_rom.setEnabled(false);
		cbb_rom.setModel(new DefaultComboBoxModel(new String[] {"64GB", "128GB", "512GB", "1TB"}));
		cbb_rom.setBounds(916, 331, 93, 30);
		getContentPane().add(cbb_rom);
		cbb_rom.setSelectedItem(String.valueOf(ctspdto.getRom()));

		JComboBox cbb_ram = new JComboBox();
		cbb_ram.setEnabled(false);
		cbb_ram.setModel(new DefaultComboBoxModel(new String[] {"2GB", "3GB", "4GB", "6GB"}));
		cbb_ram.setBounds(765, 331, 93, 30);
		getContentPane().add(cbb_ram);
		cbb_ram.setSelectedItem(String.valueOf(ctspdto.getRam()));

		JButton btn_huybo = new JButton("Tắt");
		btn_huybo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_huybo.setBounds(489, 398, 129, 23);
		getContentPane().add(btn_huybo);
	}


}

