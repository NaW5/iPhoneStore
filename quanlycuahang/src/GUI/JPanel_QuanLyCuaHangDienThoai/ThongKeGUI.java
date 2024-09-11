package GUI.JPanel_QuanLyCuaHangDienThoai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import BUS.HoaDonBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import BUS.ctHoaDonBUS;

import DTO.HoaDonDTO;
import DTO.ctHoaDonDTO;

public class ThongKeGUI extends JPanel {

	private final JLabel lbl_tien;
	public HoaDonBUS hoaDonBUS = new HoaDonBUS();
	public ctHoaDonBUS cthoaDonBUS = new ctHoaDonBUS();
	public KhachHangBUS khachHangBUS = new KhachHangBUS();
	public NhanVienBUS nhanVienBUS = new NhanVienBUS();
	public SanPhamBUS spBUS = new SanPhamBUS();
	private static final long serialVersionUID = 1L;
	private JPanel panel_chart;

	public ThongKeGUI() {
		setLayout(new BorderLayout());

		JPanel panel_top = new JPanel();
		panel_top.setLayout(new GridLayout(1, 3));

		JPanel panel_SP = new JPanel();
		panel_SP.setLayout(new BorderLayout());
		JLabel lbl_SP_text = new JLabel("Sản phẩm hiện có", SwingConstants.CENTER);
		lbl_SP_text.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_SP.add(lbl_SP_text, BorderLayout.NORTH);
		int soLuongTon = spBUS.tinhTongSoLuong();
		JLabel lbl_SP_num = new JLabel(String.valueOf(soLuongTon), SwingConstants.CENTER);
		lbl_SP_num.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel_SP.add(lbl_SP_num, BorderLayout.CENTER);
		panel_top.add(panel_SP);

		JPanel panel_KH = new JPanel();
		panel_KH.setLayout(new BorderLayout());
		JLabel lbl_KH_text = new JLabel("Số lượng khách hàng", SwingConstants.CENTER);
		lbl_KH_text.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_KH.add(lbl_KH_text, BorderLayout.NORTH);
		int soLuongKH = khachHangBUS.tinhTongSoLuongKhachHang();
		JLabel lbl_KH_num = new JLabel(String.valueOf(soLuongKH), SwingConstants.CENTER);
		lbl_KH_num.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel_KH.add(lbl_KH_num, BorderLayout.CENTER);
		panel_top.add(panel_KH);

		JPanel panel_NV = new JPanel();
		panel_NV.setLayout(new BorderLayout());
		JLabel lbl_NV_text = new JLabel("Nhân viên đang hoạt động", SwingConstants.CENTER);
		lbl_NV_text.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_NV.add(lbl_NV_text, BorderLayout.NORTH);
		int soLuongNV = nhanVienBUS.tinhTongSoLuongNhanVien();
		JLabel lbl_NV_num = new JLabel(String.valueOf(soLuongNV), SwingConstants.CENTER);
		lbl_NV_num.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel_NV.add(lbl_NV_num, BorderLayout.CENTER);
		panel_top.add(panel_NV);

		add(panel_top, BorderLayout.NORTH);

		panel_chart = new JPanel();
		panel_chart.setLayout(new BorderLayout());
		add(panel_chart, BorderLayout.CENTER);

		JPanel panel_buttons = new JPanel();
		JButton btn_general = new JButton("Thống kê tổng quát");
		btn_general.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showGeneralStatistics();
			}
		});
		panel_buttons.add(btn_general);

		JButton btn_daily = new JButton("Thống kê ngày");
		btn_daily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showChart("day");
			}
		});
		panel_buttons.add(btn_daily);

		JButton btn_monthly = new JButton("Thống kê tháng");
		btn_monthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showChart("month");
			}
		});
		panel_buttons.add(btn_monthly);

		JButton btn_yearly = new JButton("Thống kê năm");
		btn_yearly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showChart("year");
			}
		});
		panel_buttons.add(btn_yearly);

		add(panel_buttons, BorderLayout.SOUTH);

		lbl_tien = new JLabel("", SwingConstants.CENTER);
		lbl_tien.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lbl_tien, BorderLayout.NORTH);

		reloadDoanhThu();
	}

	private void showChart(String type) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<HoaDonDTO> HDlist = hoaDonBUS.getAllHoaDon();

		for (HoaDonDTO HD : HDlist) {
			ctHoaDonDTO cthd = cthoaDonBUS.getCTHoaDonById(HD.getIdHoaDon());
			float giaNhap = spBUS.laySanPhamTheoId(cthd.getSANPHAM_idSP()).getGiaNhap();
			int soLuong = cthd.getSoLuong();
			double doanhThu = HD.getTongTien() - soLuong * giaNhap;

			String key = "";
			LocalDate ngayLap = HD.getNgayLap().toLocalDate();
			if (type.equals("day")) {
				key = ngayLap.toString();
			} else if (type.equals("month")) {
				key = ngayLap.getMonthValue() + "-" + ngayLap.getYear();
			} else if (type.equals("year")) {
				key = String.valueOf(ngayLap.getYear());
			}
			dataset.addValue(doanhThu, "Doanh Thu", key);
		}

		JFreeChart barChart = ChartFactory.createBarChart(
				"Thống kê doanh thu",
				"Thời gian",
				"Doanh thu",
				dataset,
				PlotOrientation.VERTICAL,
				true, true, false);

		ChartPanel chartPanel = new ChartPanel(barChart);
		panel_chart.removeAll();
		panel_chart.add(chartPanel, BorderLayout.CENTER);
		panel_chart.revalidate();
		panel_chart.repaint();
	}

	private void reloadDoanhThu() {
		DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
		ArrayList<HoaDonDTO> HDlist = hoaDonBUS.getAllHoaDon();
		double tongDoanhThu = 0;
		for (HoaDonDTO HD : HDlist) {
			ctHoaDonDTO cthd = cthoaDonBUS.getCTHoaDonById(HD.getIdHoaDon());
			float giaNhap = spBUS.laySanPhamTheoId(cthd.getSANPHAM_idSP()).getGiaNhap();
			int soLuong = cthd.getSoLuong();
			tongDoanhThu += (HD.getTongTien() - soLuong * giaNhap);
		}
		String tongDoanhThuFormatted = decimalFormat.format(tongDoanhThu);
		lbl_tien.setText("Tổng doanh thu: " + tongDoanhThuFormatted + " VND");
	}

	private void showGeneralStatistics() {
		int soLuongTon = spBUS.tinhTongSoLuong();
		int soLuongKH = khachHangBUS.tinhTongSoLuongKhachHang();
		int soLuongNV = nhanVienBUS.tinhTongSoLuongNhanVien();

		JOptionPane.showMessageDialog(this,
				"Số sản phẩm còn lại trong kho: " + soLuongTon + "\n" +
						"Số lượng khách hàng: " + soLuongKH + "\n" +
						"Số lượng nhân viên: " + soLuongNV,
				"Thống kê tổng quát",
				JOptionPane.INFORMATION_MESSAGE);
	}
}