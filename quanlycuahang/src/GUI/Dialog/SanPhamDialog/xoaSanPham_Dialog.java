package GUI.Dialog.SanPhamDialog;

import javax.swing.JDialog;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;


import BUS.SanPhamBUS;
// Sửa phần này nha tú.ơ..................................................................................

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;

import java.awt.Color;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class xoaSanPham_Dialog extends JDialog{
	private String imagePath;
	private int idSP;
	public SanPhamBUS spBUS = new SanPhamBUS();

	public xoaSanPham_Dialog(int idSP) {
		SanPhamDTO spdto = SanPhamDAO.getInstance().selectById(idSP);
		// Sửa phần này nha tú.ơ..................................................................................

		getContentPane().setLayout(null);
		JLabel lbl_suasp = new JLabel("XÓA SẢN PHẨM" + String.valueOf(idSP));
		lbl_suasp.setBounds(0, 0, 478, 17);
		lbl_suasp.setBackground(new Color(128, 255, 255));
		lbl_suasp.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_suasp.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lbl_suasp);

		JButton btn_xoa = new JButton("Xóa");
		btn_xoa.setBounds(80, 135, 129, 23);
		getContentPane().add(btn_xoa);
		btn_xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spBUS.xoaSanPham(Integer.toString(idSP));
			}
		});

		JButton btn_huybo = new JButton("Hủy bỏ");
		btn_huybo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_huybo.setBounds(260, 135, 129, 23);
		getContentPane().add(btn_huybo);

		JLabel lbl_thongbao = new JLabel("Bạn có muốn xóa sản phẩm " + Integer.toString(idSP) + "?");
		lbl_thongbao.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_thongbao.setBounds(120, 68, 240, 14);
		getContentPane().add(lbl_thongbao);
	}



}

