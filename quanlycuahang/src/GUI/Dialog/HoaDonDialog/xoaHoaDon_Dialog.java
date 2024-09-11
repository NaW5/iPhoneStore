package GUI.Dialog.HoaDonDialog;

import BUS.HoaDonBUS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class xoaHoaDon_Dialog extends JDialog{
	public HoaDonBUS hdBUS = new HoaDonBUS();

	public xoaHoaDon_Dialog(int idHD) {
		getContentPane().setLayout(null);
		JLabel lbl_suasp = new JLabel("XÓA HÓA ĐƠN " + String.valueOf(idHD));
		lbl_suasp.setBounds(0, 0, 446, 17);
		lbl_suasp.setBackground(new Color(128, 255, 255));
		lbl_suasp.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_suasp.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lbl_suasp);

		JButton btn_xoa = new JButton("Xóa");
		btn_xoa.setBounds(60, 136, 129, 23);
		getContentPane().add(btn_xoa);
		btn_xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hdBUS.deleteHoaDon(Integer.toString(idHD));
			}
		});

		JButton btn_huybo = new JButton("Hủy bỏ");
		btn_huybo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btn_huybo.setBounds(240, 136, 129, 23);
		getContentPane().add(btn_huybo);

		JLabel lbl_thongbao = new JLabel("Bạn có muốn xóa hóa đơn " + String.valueOf(idHD) + "?");
		lbl_thongbao.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_thongbao.setBounds(100, 69, 240, 14);
		getContentPane().add(lbl_thongbao);
	}



}

