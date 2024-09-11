package GUI.Dialog.HoaDonDialog;

import BUS.*;
import DAO.KhuyenMaiDAO;
import DAO.PhieuBaoHanhDAO;
import DAO.SanPhamDAO;
import DAO.ctSanPhamDAO;
import DTO.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Locale;

public class xemHoaDon_Dialog extends JDialog {
	private JTextField txt_maSP;
	private JTextField txt_tenSP;
	private JTextField txt_rom;
	private JTextField txt_mauSac;
	private JTextField txt_maHD;
	private JTextField txt_tenNV;
	private JTextField txt_idKH;
	private JTextField txt_tongtien;
	public SanPhamBUS spBUS = new SanPhamBUS();
	public ctSanPhamBUS ctspBUS = new ctSanPhamBUS();
	public ctHoaDonBUS cthdBUS = new ctHoaDonBUS();
	public HoaDonBUS hdBUS = new HoaDonBUS();
	public KhachHangBUS khBUS = new KhachHangBUS();
	private JTextField txt_soluong;

	public xemHoaDon_Dialog(int idHD) {
		setTitle("Xem Hóa Đơn");
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		GroupLayout layout = new GroupLayout(contentPane);
		contentPane.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		ctHoaDonDTO newcthd = cthdBUS.getCTHoaDonById(idHD);
		int newIDSP = newcthd.getSANPHAM_idSP();

		JLabel lbl_maSP = new JLabel("Mã sản phẩm");
		lbl_maSP.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txt_maSP = new JTextField(String.valueOf(newIDSP));
		txt_maSP.setEnabled(true);
		txt_maSP.setEditable(false);

		SanPhamDTO sp = spBUS.laySanPhamTheoId(newIDSP);
		ctSanPhamDTO ctsp = ctspBUS.timctSanPhamTheoId(newIDSP);

		JLabel lbl_tenSP = new JLabel("Tên sản phẩm");
		lbl_tenSP.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txt_tenSP = new JTextField(sp.getTenSP());
		txt_tenSP.setEnabled(true);
		txt_tenSP.setEditable(false);

		JLabel lbl_rom = new JLabel("Bộ nhớ ");
		lbl_rom.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txt_rom = new JTextField(ctsp.getRom());
		txt_rom.setEnabled(true);
		txt_rom.setEditable(false);

		JLabel lbl_mauSac = new JLabel("Màu sắc");
		lbl_mauSac.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txt_mauSac = new JTextField(sp.getMauSac());
		txt_mauSac.setEnabled(true);
		txt_mauSac.setEditable(false);

		JLabel lbl_maHD = new JLabel("Mã hóa đơn");
		lbl_maHD.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txt_maHD = new JTextField(String.valueOf(idHD));
		txt_maHD.setEditable(false);

		JLabel lbl_NV = new JLabel("Nhân viên");
		lbl_NV.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txt_tenNV = new JTextField(String.valueOf(hdBUS.getHoaDonById(idHD).getNHANVIEN_idNV()));
		txt_tenNV.setEnabled(true);
		txt_tenNV.setEditable(false);

		JLabel lbl_KH = new JLabel("Khách hàng");
		lbl_KH.setFont(new Font("Tahoma", Font.PLAIN, 14));

		txt_idKH = new JTextField(String.valueOf(hdBUS.getHoaDonById(idHD).getKHACHHANG_idKH()));
		txt_idKH.setEnabled(true);
		txt_idKH.setEditable(false);

		JLabel lbl_tongtien = new JLabel("Tổng tiền");
		lbl_tongtien.setFont(new Font("Tahoma", Font.BOLD, 16));

		txt_tongtien = new JTextField(String.valueOf(hdBUS.getHoaDonById(idHD).getTongTien()));
		txt_tongtien.setEditable(false);
		txt_tongtien.setEnabled(true);
		double tongTien = hdBUS.getHoaDonById(idHD).getTongTien();
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		txt_tongtien.setText(currencyFormat.format(tongTien));
		txt_tongtien.setEditable(false);
		txt_tongtien.setEnabled(true);

		txt_soluong = new JTextField(String.valueOf(newcthd.getSoLuong()));
		txt_soluong.setEnabled(true);
		txt_soluong.setEditable(false);

		JButton btn_thoat = new JButton("Thoát");
		btn_thoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_thoat.setIcon(new ImageIcon(xemHoaDon_Dialog.class.getResource("/GUI/JFrame_QuanLyCuaHangDienThoai/icon_dangxuat.png")));
		btn_thoat.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel lbl_themHD = new JLabel("HÓA ĐƠN " + String.valueOf(idHD));
		lbl_themHD.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_themHD.setFont(new Font("Tahoma", Font.BOLD, 20));

		JLabel lbl_soluong = new JLabel("Số lượng");
		lbl_soluong.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lbl_baohanh = new JLabel("Bảo hành (tháng)");
		lbl_baohanh.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JComboBox<String> cbb_baohanh = new JComboBox<>();
		DefaultComboBoxModel<String> cbbmodel = new DefaultComboBoxModel<>();
		PhieuBaoHanhBUS phieuBaoHanhBUS = new PhieuBaoHanhBUS();
		ArrayList<PhieuBaoHanhDTO> dsPhieuBH = phieuBaoHanhBUS.getAllPhieuBaoHanh();
		for (PhieuBaoHanhDTO phieuBH : dsPhieuBH) {
			cbbmodel.addElement(phieuBH.getThoiGian());
		}
		cbb_baohanh.setModel(cbbmodel);
		cbb_baohanh.setEnabled(false);
		int maBH = newcthd.getPHIEUBAOHANH_idBaoHanh();
		String baoHanhDangChon = PhieuBaoHanhDAO.getInstance().selectByIdBH(maBH).getThoiGian();
		cbb_baohanh.setSelectedItem(baoHanhDangChon);
		cbb_baohanh.setPreferredSize(new Dimension(10, cbb_baohanh.getPreferredSize().height));
		JLabel lbl_khuyenmai = new JLabel("Khuyến mãi (%)");
		lbl_khuyenmai.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JComboBox<Integer> cbb_khuyenmai = new JComboBox<>();
		DefaultComboBoxModel<Integer> cbbmodelkm = new DefaultComboBoxModel<>();
		KhuyenMaiDAO kmDAO = KhuyenMaiDAO.getInstance();
		ArrayList<KhuyenMaiDTO> dskm = kmDAO.selectAll();
		for (KhuyenMaiDTO km : dskm) {
			cbbmodelkm.addElement((int) km.getPhanTram());
		}
		cbb_khuyenmai.setModel(cbbmodelkm);
		cbb_khuyenmai.setEnabled(false);
		int maKM = newcthd.getKHUYENMAI_idKM();
		int kmDangChon = (int) KhuyenMaiDAO.getInstance().selectByIdKM(maKM).getPhanTram();
		cbb_khuyenmai.setSelectedItem(kmDangChon);

		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(lbl_themHD)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(lbl_maSP)
										.addComponent(lbl_rom)
										.addComponent(lbl_soluong))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(txt_maSP)
										.addComponent(txt_rom)
										.addComponent(txt_soluong))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(lbl_tenSP)
										.addComponent(lbl_mauSac)
										.addComponent(lbl_khuyenmai))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(txt_tenSP)
										.addComponent(txt_mauSac)
										.addComponent(cbb_khuyenmai))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(lbl_maHD)
										.addComponent(lbl_NV)
										.addComponent(lbl_KH))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(txt_maHD)
										.addComponent(txt_tenNV)
										.addComponent(txt_idKH)))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(lbl_baohanh)
										.addComponent(lbl_tongtien))
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(cbb_baohanh)
										.addComponent(txt_tongtien)))
						.addComponent(btn_thoat, GroupLayout.Alignment.TRAILING)
		);

		layout.setVerticalGroup(
				layout.createSequentialGroup()
						.addComponent(lbl_themHD)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbl_maSP)
								.addComponent(txt_maSP)
								.addComponent(lbl_tenSP)
								.addComponent(txt_tenSP)
								.addComponent(lbl_maHD)
								.addComponent(txt_maHD))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbl_rom)
								.addComponent(txt_rom)
								.addComponent(lbl_mauSac)
								.addComponent(txt_mauSac)
								.addComponent(lbl_NV)
								.addComponent(txt_tenNV))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbl_soluong)
								.addComponent(txt_soluong)
								.addComponent(lbl_khuyenmai)
								.addComponent(cbb_khuyenmai)
								.addComponent(lbl_KH)
								.addComponent(txt_idKH))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbl_baohanh)
								.addComponent(cbb_baohanh))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(lbl_tongtien)
								.addComponent(txt_tongtien))
						.addComponent(btn_thoat)
		);
	}
}