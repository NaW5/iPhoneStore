package GUI.Dialog.SanPhamDialog;

import javax.swing.JDialog;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

// Sửa phần này nha tú.ơ..................................................................................

import DAO.IMEIDAO;

import DTO.IMEIDTO;

import java.awt.Color;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class xoaCTSP_Dialog extends JDialog{

	public xoaCTSP_Dialog(int maIMEI) {
		IMEIDTO ctspdto = IMEIDAO.getInstance().selectById(maIMEI);
		// Sửa phần này nha tú.ơ..................................................................................

		getContentPane().setLayout(null);
		JLabel lbl_suasp = new JLabel("XÓA IMEI " + String.valueOf(maIMEI));
		lbl_suasp.setBounds(0, 0, 468, 17);
		lbl_suasp.setBackground(new Color(128, 255, 255));
		lbl_suasp.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_suasp.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lbl_suasp);

		JButton btn_xoa = new JButton("Xóa");
		btn_xoa.setBounds(80, 135, 129, 23);
		getContentPane().add(btn_xoa);
		btn_xoa.addActionListener(new ActionListener() {
			// Sửa phần này nha tú.ơ..................................................................................

			public void actionPerformed(ActionEvent e) {
				IMEIDAO.getInstance().delete(Integer.toString(maIMEI));
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

		JLabel lbl_thongbao = new JLabel("Bạn có muốn xóa IMEI sản phẩm ?");
		lbl_thongbao.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_thongbao.setBounds(120, 68, 240, 14);
		getContentPane().add(lbl_thongbao);
	}



}


