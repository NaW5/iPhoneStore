package GUI.JPanel_QuanLyCuaHangDienThoai;

import DAO.HoaDonDAO;
import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import GUI.Dialog.HoaDonDialog.suaHoaDon_Dialog;
import GUI.Dialog.HoaDonDialog.xemHoaDon_Dialog;
import GUI.Dialog.HoaDonDialog.xoaHoaDon_Dialog;
import GUI.Dialog.SanPhamDialog.themSanPham_Dialog;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class PhieuXuatGUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table_HD;
    DefaultTableModel tblModel;
    public HoaDonDAO hdDAO = new HoaDonDAO();
    public themSanPham_Dialog hdDialog = new themSanPham_Dialog();
    private JTextField txt_tuTien;
    private JTextField txt_denTien;

    public static String convertDateFormat(String dateString) {
        // Định dạng của ngày ban đầu
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");

        // Định dạng của ngày đầu ra
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Chuyển đổi thành đối tượng LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateString, inputFormatter);

        // Chuyển đổi sang định dạng mới
        String formattedDate = dateTime.format(outputFormatter);

        // Trả về chuỗi ngày sau khi chuyển đổi
        return formattedDate;
    }
    
    public static String removeDiacriticsAndhdaces(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        str = str.replaceAll("\\s+", ""); // Loại bỏ các khoảng trắng
        return str;
    }

    public void loadDataTalbe() {
    	DecimalFormat df = new DecimalFormat("#,###.##");
        ArrayList<HoaDonDTO> result = hdDAO.selectAll();
        System.out.println("Number of records retrieved: " + result.size());
        tblModel.setRowCount(0); // Clear existing data
        for (HoaDonDTO hd : result) {
        	String tenNV = NhanVienDAO.getInstance().selectById(hd.getNHANVIEN_idNV()).getHoTen();
        	String tenKH = KhachHangDAO.getInstance().selectById(hd.getKHACHHANG_idKH()).getTenKhachHang();
            tblModel.addRow(new Object[]{hd.getIdHoaDon(), hd.getThoiGian(), df.format(hd.getTongTien()), tenNV, tenKH});
        }
    }

    public void loadDataTalbeByCondition(String t) {
    	DecimalFormat df = new DecimalFormat("#,###.##");//where
        ArrayList<HoaDonDTO> result = hdDAO.selectByCondition(t);
        System.out.println("Number of records retrieved: " + result.size());
        tblModel.setRowCount(0); // Clear existing data
        for (HoaDonDTO hd : result) {
        	String tenNV = NhanVienDAO.getInstance().selectById(hd.getNHANVIEN_idNV()).getHoTen();
        	String tenKH = KhachHangDAO.getInstance().selectById(hd.getKHACHHANG_idKH()).getTenKhachHang();
            tblModel.addRow(new Object[]{hd.getIdHoaDon(), hd.getThoiGian(), df.format(hd.getTongTien()), tenNV, tenKH});
        }
    }
    
    public void loadDataTalbeFromDateToDate(Date start, Date end) {
    	java.sql.Date sqlStartDate = new java.sql.Date(start.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(end.getTime());

        DecimalFormat df = new DecimalFormat("#,###.##");
        ArrayList<HoaDonDTO> result = hdDAO.selectFromDayToDay(sqlStartDate, sqlEndDate);
        System.out.println("Number of records retrieved: " + result.size());
        tblModel.setRowCount(0); // Clear existing data
        for (HoaDonDTO hd : result) {
            String tenNV = NhanVienDAO.getInstance().selectById(hd.getNHANVIEN_idNV()).getHoTen();
            String tenKH = KhachHangDAO.getInstance().selectById(hd.getKHACHHANG_idKH()).getTenKhachHang();
            tblModel.addRow(new Object[]{hd.getIdHoaDon(), hd.getThoiGian(), df.format(hd.getTongTien()), tenNV, tenKH});
        }
    }
    private DefaultComboBoxModel<String> loadKhachHangData() {
        ArrayList<KhachHangDTO> danhSachKhachHang = KhachHangDAO.getInstance().selectAll();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (KhachHangDTO kh : danhSachKhachHang) {
            String item = kh.getIdKhachHang() + " - " + kh.getTenKhachHang();
            model.addElement(item);
        }
        return model;
    }
    
    private DefaultComboBoxModel<String> loadNhanVienData() {
        ArrayList<NhanVienDTO> danhSachNhanVien = NhanVienDAO.getInstance().selectAll();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (NhanVienDTO nv : danhSachNhanVien) {
            String item = nv.getIdNV() + " - " + nv.getHoTen();
            model.addElement(item);
        }
        return model;
    }
    
    public PhieuXuatGUI() {
        String[] columnNames = {"Mã hóa đơn", "Thời gian lập", "Tổng tiền", "Tên nhân viên", "Tên Khách hàng"};
        tblModel = new DefaultTableModel(columnNames, 0);
        loadDataTalbe();
        setLayout(null);
        JPanel panel_HoaDon = new JPanel();
        panel_HoaDon.setBounds(10, 11, 790, 557);
        add(panel_HoaDon, BorderLayout.NORTH);
        add(panel_HoaDon);

        JButton btn_them = new JButton("Thêm");
//        btn_them.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
////                int selectedRow = table_HD.getSelectedRow();
////                if (selectedRow != -1) {
////                    DefaultTableModel model = (DefaultTableModel) table_HD.getModel();
////                    int idhd = (int) model.getValueAt(selectedRow, 0);
////                    themSanPham_Dialog hddialog = new themSanPham_Dialog(idhd);
////                    hddialog.setSize(1000, 500);
////                    hddialog.setVisible(true);
////                } else {
//                    themHoaDon_Dialog hddialog = new themHoaDon_Dialog();
//                    hddialog.setSize(1200, 600);
//                    hddialog.setVisible(true);
////                }
//
//            }
//        });
        btn_them.setForeground(new Color(0, 0, 0));
        btn_them.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc_btn_them = new GridBagConstraints();
        gbc_btn_them.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_them.insets = new Insets(0, 0, 5, 5);
        gbc_btn_them.gridx = 0;
        gbc_btn_them.gridy = 0;
        btn_them.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(PhieuXuatGUI.class.getResource("icon_them.png"))));


        JButton btn_sua = new JButton("Sửa");
        btn_sua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int selectedRow = table_HD.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table_HD.getModel();
                    int idHD = (int) model.getValueAt(selectedRow, 0);
                    suaHoaDon_Dialog hddialog = new suaHoaDon_Dialog(idHD);
                    hddialog.setSize(1200, 600);
                    hddialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        GridBagConstraints gbc_btn_sua = new GridBagConstraints();
        gbc_btn_sua.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_sua.insets = new Insets(0, 0, 5, 5);
        gbc_btn_sua.gridx = 1;
        gbc_btn_sua.gridy = 0;
        btn_sua.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(PhieuXuatGUI.class.getResource("icon_sua.png"))));


        JButton btn_xoa = new JButton("Xóa");
        btn_xoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_HD.getSelectedRow();
                if (selectedRow != -1) {
                    DefaultTableModel model = (DefaultTableModel) table_HD.getModel();
                    int idhd = (int) model.getValueAt(selectedRow, 0);
                    xoaHoaDon_Dialog hddialog = new xoaHoaDon_Dialog(idhd);
                    hddialog.setSize(500, 300);
                    hddialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        GridBagConstraints gbc_btn_xoa = new GridBagConstraints();
        gbc_btn_xoa.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_xoa.insets = new Insets(0, 0, 5, 5);
        gbc_btn_xoa.gridx = 2;
        gbc_btn_xoa.gridy = 0;
        btn_xoa.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(PhieuXuatGUI.class.getResource("icon_xoa.png"))));
        GridBagConstraints gbc_btn_chitiet = new GridBagConstraints();
        gbc_btn_chitiet.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_chitiet.insets = new Insets(0, 0, 5, 5);
        gbc_btn_chitiet.gridx = 3;
        gbc_btn_chitiet.gridy = 0;

        table_HD = new JTable(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"M\u00E3 h\u00F3a \u0111\u01A1n", "Th\u1EDDi gian l\u1EADp", "T\u1ED5ng ti\u1EC1n", "T\u00EAn nh\u00E2n vi\u00EAn", "T\u00EAn kh\u00E1ch h\u00E0ng"
        	}
        ));
        table_HD = new JTable(tblModel);
        JScrollPane scrollPane = new JScrollPane(table_HD); // Tạo JScrollPane và đặt table_hd vào đó
        table_HD.getTableHeader().setBackground(Color.LIGHT_GRAY); // Đặt màu nền cho thanh tiêu đề

        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 7;
        gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 1;

        JButton btn_tailai = new JButton("Tải lại");
        btn_tailai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        scrollPane.setRowHeaderView(btn_tailai);
        
                JButton btn_chitiet = new JButton("Chi tiết");
                btn_chitiet.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int selectedRow = table_HD.getSelectedRow();
                        if (selectedRow != -1) {
                            DefaultTableModel model = (DefaultTableModel) table_HD.getModel();
                            int idhd = (int) model.getValueAt(selectedRow, 0);
                            xemHoaDon_Dialog hddialog = new xemHoaDon_Dialog(idhd);
                            hddialog.setSize(1200, 600);
                            hddialog.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần xem!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });
                btn_chitiet.setIcon(new ImageIcon(PhieuXuatGUI.class.getResource("/GUI/JPanel_QuanLyCuaHangDienThoai/icon_info.png")));
        GroupLayout gl_panel_HoaDon = new GroupLayout(panel_HoaDon);
        gl_panel_HoaDon.setHorizontalGroup(
        	gl_panel_HoaDon.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_HoaDon.createSequentialGroup()
        			.addComponent(btn_them)
        			.addGap(39)
        			.addComponent(btn_sua)
        			.addGap(37)
        			.addComponent(btn_xoa)
        			.addGap(39)
        			.addComponent(btn_chitiet)
        			.addContainerGap(237, Short.MAX_VALUE))
        		.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
        );
        gl_panel_HoaDon.setVerticalGroup(
        	gl_panel_HoaDon.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_HoaDon.createSequentialGroup()
        			.addGap(1)
        			.addGroup(gl_panel_HoaDon.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btn_them)
        				.addComponent(btn_sua)
        				.addComponent(btn_xoa)
        				.addComponent(btn_chitiet))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
        );
        panel_HoaDon.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel_HoaDon.setLayout(gl_panel_HoaDon);
        
        JLabel lbl_hk = new JLabel("Khách hàng");
        lbl_hk.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbl_hk.setBounds(828, 11, 101, 32);
        add(lbl_hk);
        
        JComboBox cbb_kh = new JComboBox();
        cbb_kh.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 // Lấy đối tượng đã chọn trong combobox
                Object selected = cbb_kh.getSelectedItem();
                if (selected != null) {
                    // Chuyển đối tượng đã chọn thành chuỗi
                    String selectedItem = selected.toString();
                    // Tách chuỗi để lấy mã khách hàng
                    String maKH = selectedItem.split(" - ")[0];
                    loadDataTalbeByCondition("KHACHHANG_idKH = " +maKH);
                }
        	}
        });
        cbb_kh.setBounds(828, 48, 166, 22);
        add(cbb_kh);
        cbb_kh.setModel(loadKhachHangData());
        
        JLabel lbl_nv = new JLabel("Nhân viên");
        lbl_nv.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbl_nv.setBounds(828, 86, 101, 32);
        add(lbl_nv);
        
        JComboBox cbb_nv = new JComboBox();
        cbb_nv.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 // Lấy đối tượng đã chọn trong combobox
                Object selected = cbb_nv.getSelectedItem();
                if (selected != null) {
                    // Chuyển đối tượng đã chọn thành chuỗi
                    String selectedItem = selected.toString();
                    // Tách chuỗi để lấy mã khách hàng
                    String maNV = selectedItem.split(" - ")[0];
                    loadDataTalbeByCondition("NHANVIEN_idNV = " + maNV);
                }
        	}
        });
        cbb_nv.setBounds(828, 122, 166, 22);
        add(cbb_nv);
        cbb_nv.setModel(loadNhanVienData());
        
        JLabel lbl_ngaybatdau = new JLabel("Từ ngày");
        lbl_ngaybatdau.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbl_ngaybatdau.setBounds(828, 163, 101, 32);
        add(lbl_ngaybatdau);
        
        JDateChooser dateChooser_ngaybatdau = new JDateChooser();
        dateChooser_ngaybatdau.setBounds(828, 206, 109, 22);
        add(dateChooser_ngaybatdau);
        JDateChooser dateChooser_ngayketthuc = new JDateChooser();
        
        dateChooser_ngaybatdau.getDateEditor().addPropertyChangeListener("date", (PropertyChangeListener) new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            	if ("date".equals(evt.getPropertyName())) {
                    // Lấy ngày mới từ sự kiện
                    Date startDate = dateChooser_ngaybatdau.getDate();
                    // Chuyển đổi java.util.Date thành java.sql.Date
                    java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());

                    // Kiểm tra xem ngày kết thúc đã được chọn hay chưa
                    if (dateChooser_ngayketthuc.getDate() != null) {
                        // Nếu đã chọn ngày kết thúc
                        Date endDate = dateChooser_ngayketthuc.getDate();
                        // Chuyển đổi java.util.Date thành java.sql.Date
                        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
                        // Gọi phương thức để tải dữ liệu từ ngày bắt đầu đến ngày kết thúc
                        loadDataTalbeFromDateToDate(sqlStartDate, sqlEndDate);
                    } else {
                        // Nếu chưa chọn ngày kết thúc, hiện hóa đơn từ ngày bắt đầu đến ngày hiện tại
                        java.sql.Date currentDate = java.sql.Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        // Gọi phương thức để tải dữ liệu từ ngày bắt đầu đến ngày hiện tại
                        loadDataTalbeFromDateToDate(sqlStartDate, currentDate);
                    }
                }
            }
        });
        
        
        JLabel lbl_ngayketthuc = new JLabel("Đến ngày");
        lbl_ngayketthuc.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbl_ngayketthuc.setBounds(828, 249, 101, 32);
        add(lbl_ngayketthuc);
        
        dateChooser_ngayketthuc.setBounds(828, 292, 109, 22);
        add(dateChooser_ngayketthuc);
        
        dateChooser_ngayketthuc.getDateEditor().addPropertyChangeListener("date", (PropertyChangeListener) new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            	if ("date".equals(evt.getPropertyName())) {
                    // Lấy ngày mới từ sự kiện
                    Date startDate = dateChooser_ngaybatdau.getDate();
                    // Chuyển đổi java.util.Date thành java.sql.Date
                    java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
                    // Lấy ngày hiện tại
                    Date endDate = dateChooser_ngayketthuc.getDate();
                    // Chuyển đổi java.util.Date thành java.sql.Date
                    java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
                    // Gọi phương thức để tải dữ liệu từ ngày được chọn đến ngày hiện tại
                    loadDataTalbeFromDateToDate(sqlStartDate, sqlEndDate);
                }
            }
        });
        
        JLabel lbl_tuTien = new JLabel("Từ số tiền (VNĐ)");
        lbl_tuTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbl_tuTien.setBounds(828, 344, 123, 32);
        add(lbl_tuTien);
        double maxTongTien = hdDAO.getMaxTotalAmount();
        txt_tuTien = new JTextField();
        txt_tuTien.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Lấy giá trị từ ô txt_denTien
                String denTienText = txt_denTien.getText();
                // Kiểm tra nếu ô không rỗng
                if (!denTienText.isEmpty()) {
                    // Chuyển đổi giá trị từ chuỗi sang số
                    double denTien = Double.parseDouble(denTienText);
                    // Gọi phương thức để tải lại dữ liệu với điều kiện giá tiền từ giá tiền hiện tại đến denTien
                    loadDataTalbeByCondition("tongTien <= " + denTien);
                } else {
                    // Nếu ô rỗng, tìm giá trị tối đa của cột "Tổng tiền" trong bảng "HoaDon"
                     // Giả sử đã có phương thức này trong HoaDonDAO
                    // Gọi phương thức để tải lại dữ liệu với điều kiện giá tiền từ giá tiền từ ô txt_tuTien đến maxTongTien
                    loadDataTalbeByCondition("tongTien >= " + txt_tuTien.getText() + " AND tongTien <= " + maxTongTien);
                }
        	}
        });
        txt_tuTien.setBounds(828, 387, 104, 32);
        add(txt_tuTien);
        txt_tuTien.setColumns(10);
        
        JLabel lbl_denTien = new JLabel("Đến số tiền (VNĐ)");
        lbl_denTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbl_denTien.setBounds(828, 440, 123, 32);
        add(lbl_denTien);
        
        txt_denTien = new JTextField();
        txt_denTien.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String denTienText = txt_denTien.getText();
                // Kiểm tra nếu ô không rỗng
                if (!denTienText.isEmpty()) {
                    // Chuyển đổi giá trị từ chuỗi sang số
                    double denTien = Double.parseDouble(denTienText);
                    loadDataTalbeByCondition("tongTien >= " + txt_tuTien.getText() + " AND tongTien <= " + denTien);
                } else {
                	loadDataTalbeByCondition("tongTien >= " + txt_tuTien.getText() + " AND tongTien <= " + maxTongTien);
                }
        	}
        });
        txt_denTien.setColumns(10);
        txt_denTien.setBounds(828, 483, 104, 32);
        add(txt_denTien);


    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinhdection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
//        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }

    public void refreshTable() {
        loadDataTalbe();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test SanPham_GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);

        // Tạo một instance của SanPham_GUI
        PhieuXuatGUI HoaDonGUI = new PhieuXuatGUI();

        // Thêm SanPham_GUI vào JFrame
        frame.getContentPane().add(HoaDonGUI);

        // Hiển thị JFrame
        frame.setVisible(true);
        //240x300
    }
}
