package GUI.JFrame_QuanLyCuaHangDienThoai;
import GUI.JPanel_QuanLyCuaHangDienThoai.*;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.awt.GridLayout;

public class QuanLyKho extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel selectedPanel;
	private TrangChuGUI trangChuGUI;
	private SanPhamGUI sanPhamGUI;
	private HoaDonGUI hoaDonGUI;
	private KhachHangGUI khachHangGUI;
	private NhanVienGUI nhanVienGUI;
	private PhieuNhapGUI phieuNhapGUI;
	private TaiKhoanGUI taiKhoanGUI;
	private ThongKeGUI thongKeGUI;
	private AdminGUI adminGUI;






	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new QuanLyKho(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuanLyKho(int idNVHienTai) {

		sanPhamGUI = new SanPhamGUI();
		hoaDonGUI = new HoaDonGUI(idNVHienTai);
		khachHangGUI = new KhachHangGUI();
		nhanVienGUI = new NhanVienGUI();
		phieuNhapGUI = new PhieuNhapGUI();
		trangChuGUI = new TrangChuGUI();
		taiKhoanGUI = new TaiKhoanGUI();
		thongKeGUI = new ThongKeGUI();
		adminGUI = new AdminGUI();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1252, 717);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_SideMenu = new JPanel();
		panel_SideMenu.setBackground(new Color(255, 255, 255));
		panel_SideMenu.setBounds(10, 11, 195, 645);
		contentPane.add(panel_SideMenu);
		panel_SideMenu.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_content = new JPanel();
        panel_content.setBackground(new Color(255, 255, 255));
        panel_content.setBounds(215, 11, 1001, 645);
        contentPane.add(panel_content);
        panel_content.setLayout(new CardLayout(0, 0));

		JPanel panel_admin = new JPanel();
		panel_SideMenu.add(panel_admin);
		panel_admin.setLayout(new CardLayout(0, 0));

		JLabel lbl_admin = new JLabel("Admin " + String.valueOf(idNVHienTai));
		panel_admin.add(lbl_admin, "name_151773607921700");
		lbl_admin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_admin.setBackground(Color.GRAY);
                selectPanel(panel_admin);
                panel_content.removeAll();
                panel_content.add(adminGUI);
                panel_content.revalidate();
                panel_content.repaint();
			}
		});
		lbl_admin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_admin.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QuanLyKho.class.getResource("icon_admin1.png"))));

		JPanel panel_trangChu = new JPanel();
		panel_SideMenu.add(panel_trangChu);
		panel_trangChu.setBackground(new Color(255, 255, 255));
		panel_trangChu.setLayout(new CardLayout(0, 0));

		JLabel lbl_trangChu = new JLabel("Trang Chủ");
		panel_trangChu.add(lbl_trangChu, "name_151766331942600");
		lbl_trangChu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_trangChu.setBackground(Color.GRAY);
                selectPanel(panel_trangChu);
                panel_content.removeAll();
                panel_content.add(trangChuGUI);
                panel_content.revalidate();
                panel_content.repaint();

			}
		});
		lbl_trangChu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_trangChu.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QuanLyKho.class.getResource("icon_trangchu.png"))));

		JPanel panel_SP = new JPanel();
		panel_SideMenu.add(panel_SP);
		panel_SP.setBackground(new Color(255, 255, 255));
		panel_SP.setLayout(new CardLayout(0, 0));

		JLabel lbl_SP = new JLabel("Sản Phẩm");
		panel_SP.add(lbl_SP, "name_151753602154300");
		lbl_SP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_SP.setBackground(Color.GRAY);
                selectPanel(panel_SP);
                panel_content.removeAll();
                panel_content.add(sanPhamGUI);
                panel_content.revalidate();
                panel_content.repaint();
			}
		});
		lbl_SP.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QuanLyKho.class.getResource("icon_sanpham.png"))));

		JPanel panel_HD = new JPanel();
		panel_SideMenu.add(panel_HD);
		panel_HD.setBackground(new Color(255, 255, 255));
		panel_HD.setLayout(new CardLayout(0, 0));

		JLabel lbl_HD = new JLabel("Hóa đơn");
		panel_HD.add(lbl_HD, "name_151744042761800");
		lbl_HD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_HD.setBackground(Color.GRAY);
                selectPanel(panel_HD);
                panel_content.removeAll();
                panel_content.add(hoaDonGUI);
                panel_content.revalidate();
                panel_content.repaint();
			}
		});
		lbl_HD.setIcon(new ImageIcon(QuanLyKho.class.getResource("/GUI/JPanel_QuanLyCuaHangDienThoai/bill-icon.png")));

		JPanel panel_KH = new JPanel();
		panel_SideMenu.add(panel_KH);
		panel_KH.setBackground(new Color(255, 255, 255));
		panel_KH.setLayout(new CardLayout(0, 0));

		JLabel lbl_KH = new JLabel("Khách Hàng");
		panel_KH.add(lbl_KH, "name_151694247924900");
		lbl_KH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_KH.setBackground(Color.GRAY);
                selectPanel(panel_KH);
                panel_content.removeAll();
                panel_content.add(khachHangGUI);
                panel_content.revalidate();
                panel_content.repaint();
			}
		});
		lbl_KH.setIcon(new ImageIcon(QuanLyKho.class.getResource("/GUI/JFrame_QuanLyCuaHangDienThoai/icon_nhanvien.png")));

		JPanel panel_NV = new JPanel();
		panel_SideMenu.add(panel_NV);
		panel_NV.setBackground(new Color(255, 255, 255));
		panel_NV.setLayout(new CardLayout(0, 0));

		JLabel lbl_NV = new JLabel("Nhân Viên");
		panel_NV.add(lbl_NV, "name_151678592184300");

		lbl_NV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_NV.setBackground(Color.GRAY);
				selectPanel(panel_NV);
				panel_content.removeAll();
				panel_content.add(nhanVienGUI);
				panel_content.revalidate();
				panel_content.repaint();
			}
		});
		lbl_NV.setIcon(new ImageIcon(QuanLyKho.class.getResource("/GUI/JFrame_QuanLyCuaHangDienThoai/icon_khachhang.png")));

		JPanel panel_PN = new JPanel();
		panel_SideMenu.add(panel_PN);
		panel_PN.setBackground(new Color(255, 255, 255));
		panel_PN.setLayout(new CardLayout(0, 0));

		JLabel lbl_PN = new JLabel("Phiếu Nhập");
		panel_PN.add(lbl_PN, "name_151669323479200");
		lbl_PN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_PN.setBackground(Color.GRAY);
                selectPanel(panel_PN);
                panel_content.removeAll();
                panel_content.add(phieuNhapGUI);
                panel_content.revalidate();
                panel_content.repaint();
			}
		});
		lbl_PN.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QuanLyKho.class.getResource("icon_phieunhap.png"))));

		JPanel panel_TaiKhoan = new JPanel();
		panel_SideMenu.add(panel_TaiKhoan);
		panel_TaiKhoan.setBackground(new Color(255, 255, 255));
		panel_TaiKhoan.setLayout(new CardLayout(0, 0));

		JLabel lbl_TaiKhoan = new JLabel("Tài Khoản");
		panel_TaiKhoan.add(lbl_TaiKhoan, "name_151645503491500");
		lbl_TaiKhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_TaiKhoan.setBackground(Color.GRAY);
                selectPanel(panel_TaiKhoan);
                panel_content.removeAll();
                panel_content.add(taiKhoanGUI);
                panel_content.revalidate();
                panel_content.repaint();
			}
		});
		lbl_TaiKhoan.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QuanLyKho.class.getResource("icon_taikhoan.png"))));

		JPanel panel_ThongKe = new JPanel();
		panel_SideMenu.add(panel_ThongKe);
		panel_ThongKe.setBackground(new Color(255, 255, 255));
		panel_ThongKe.setLayout(new CardLayout(0, 0));

		JLabel lbl_ThongKe = new JLabel("Thống Kê");
		panel_ThongKe.add(lbl_ThongKe, "name_151633112147600");
		lbl_ThongKe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_ThongKe.setBackground(Color.GRAY);
                selectPanel(panel_ThongKe);
                panel_content.removeAll();
                panel_content.add(thongKeGUI);
                panel_content.revalidate();
                panel_content.repaint();
			}
		});
		lbl_ThongKe.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QuanLyKho.class.getResource("icon_thongke.png"))));

		JPanel panel_DangXuat = new JPanel();
		panel_SideMenu.add(panel_DangXuat);
		panel_DangXuat.setBackground(new Color(255, 255, 255));
		panel_DangXuat.setLayout(new CardLayout(0, 0));

		JLabel lbl_DangXuat = new JLabel("Đăng Xuất");
		lbl_DangXuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				JFrame frame = new JFrame();
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.setSize(900, 460);
		        frame.getContentPane().add(new Login_GUI());
		        frame.setVisible(true);
			}
		});
		panel_DangXuat.add(lbl_DangXuat, "name_151622081050900");
		lbl_DangXuat.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QuanLyKho.class.getResource("icon_dangxuat.png"))));


//		revalidate();
//        repaint();
//		revalidate();
//        repaint();
//		revalidate();
//        repaint();
//		revalidate();
//        repaint();
//		revalidate();
//        repaint();
//		revalidate();
//        repaint();
//		revalidate();
//        repaint();
//		revalidate();
//        repaint();

	}
	private void selectPanel(JPanel panel) {
        if (selectedPanel != null) {
            selectedPanel.setBackground(new Color(255, 255, 255)); // Reset previous panel color
        }
        selectedPanel = panel;
        selectedPanel.setBackground(Color.GRAY); // Set background color for the selected panel
    }

}
