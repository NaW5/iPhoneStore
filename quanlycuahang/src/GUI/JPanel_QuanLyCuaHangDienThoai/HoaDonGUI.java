package GUI.JPanel_QuanLyCuaHangDienThoai;

import BUS.*;
import DTO.HoaDonDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.ctHoaDonDTO;
import GUI.Dialog.HoaDonDialog.suaHoaDon_Dialog;
import GUI.Dialog.HoaDonDialog.themHoaDon_Dialog;
import GUI.Dialog.HoaDonDialog.xemHoaDon_Dialog;
import GUI.Dialog.HoaDonDialog.xoaHoaDon_Dialog;
import GUI.Dialog.SanPhamDialog.themSanPham_Dialog;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
public class HoaDonGUI extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table_HD;
    DefaultTableModel tblModel;
    public themSanPham_Dialog hdDialog = new themSanPham_Dialog();
    private JTextField txt_tuTien;
    private JTextField txt_denTien;
    public HoaDonBUS hoaDonBUS = new HoaDonBUS();
    public KhachHangBUS khachHangBUS = new KhachHangBUS();
    public NhanVienBUS nhanVienBUS = new NhanVienBUS();

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

    private void createStyledPDF(Document document, HoaDonDTO hoaDonDTO, NhanVienBUS nvBUS, KhachHangBUS khBUS, ctHoaDonBUS cthdBUS, SanPhamBUS spBUS) throws DocumentException {
        com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.BOLD);
        com.itextpdf.text.Font regularFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, com.itextpdf.text.Font.NORMAL);

        document.add(new Paragraph("Thông tin hóa đơn", boldFont));
        document.add(new Paragraph("Mã hóa đơn: " + hoaDonDTO.getIdHoaDon(), regularFont));
        document.add(new Paragraph("Thời gian lập: " + hoaDonDTO.getThoiGian(), regularFont));
        document.add(new Paragraph("Tên nhân viên: " + nvBUS.selectById(hoaDonDTO.getNHANVIEN_idNV()).getHoTen(), regularFont));
        document.add(new Paragraph("Tên khách hàng: " + khBUS.selectKh(hoaDonDTO.getKHACHHANG_idKH()).getTenKhachHang(), regularFont));
        document.add(new Paragraph("Tổng tiền: " + hoaDonDTO.getTongTien(), regularFont));
        document.add(new Paragraph(" ")); // Add a blank line

        document.add(new Paragraph("Chi tiết hóa đơn:", boldFont));
        PdfPTable table = new PdfPTable(3); // 3 columns
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        PdfPCell cell1 = new PdfPCell(new Paragraph("Sản phẩm", boldFont));
        PdfPCell cell2 = new PdfPCell(new Paragraph("Số lượng", boldFont));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Đơn giá", boldFont));
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);

        ctHoaDonDTO chiTiet = cthdBUS.getCTHoaDonById(hoaDonDTO.getIdHoaDon());
        table.addCell(new Paragraph(spBUS.laySanPhamTheoId(chiTiet.getSANPHAM_idSP()).getTenSP(), regularFont));
        table.addCell(new Paragraph(String.valueOf(chiTiet.getSoLuong()), regularFont));
        table.addCell(new Paragraph(String.valueOf(chiTiet.getDonGia()), regularFont));

        document.add(table);
        document.add(new Paragraph("-----------------------------------", regularFont));
    }

    public void loadDataTalbe() {
        DecimalFormat df = new DecimalFormat("#,###.##");
        ArrayList<HoaDonDTO> result = hoaDonBUS.getAllHoaDon();
        System.out.println("Number of records retrieved: " + result.size());
        tblModel.setRowCount(0); // Clear existing data
        for (HoaDonDTO hd : result) {
            String tenNV = nhanVienBUS.selectnv(hd.getNHANVIEN_idNV()).getHoTen();
            String tenKH = khachHangBUS.selectKh(hd.getKHACHHANG_idKH()).getTenKhachHang();
            tblModel.addRow(new Object[]{hd.getIdHoaDon(), hd.getThoiGian(), df.format(hd.getTongTien()), tenNV, tenKH});
        }
    }

    public void loadDataTalbeByCondition(String t) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        ArrayList<HoaDonDTO> result = hoaDonBUS.getHoaDonByCondition(t);
        System.out.println("Number of records retrieved: " + result.size());
        tblModel.setRowCount(0); // Clear existing data
        for (HoaDonDTO hd : result) {
            String tenNV = nhanVienBUS.selectnv(hd.getNHANVIEN_idNV()).getHoTen();
            String tenKH = khachHangBUS.selectKh(hd.getKHACHHANG_idKH()).getTenKhachHang();
            tblModel.addRow(new Object[]{hd.getIdHoaDon(), hd.getThoiGian(), df.format(hd.getTongTien()), tenNV, tenKH});
        }
    }

    public void loadDataTalbeFromDateToDate(Date start, Date end) {
        java.sql.Date sqlStartDate = new java.sql.Date(start.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(end.getTime());

        DecimalFormat df = new DecimalFormat("#,###.##");
        ArrayList<HoaDonDTO> result = hoaDonBUS.getHoaDonFromDayToDay(sqlStartDate, sqlEndDate);
        System.out.println("Number of records retrieved: " + result.size());
        tblModel.setRowCount(0); // Clear existing data
        for (HoaDonDTO hd : result) {
            String tenNV = nhanVienBUS.selectnv(hd.getNHANVIEN_idNV()).getHoTen();
            String tenKH = khachHangBUS.selectKh(hd.getKHACHHANG_idKH()).getTenKhachHang();
            tblModel.addRow(new Object[]{hd.getIdHoaDon(), hd.getThoiGian(), df.format(hd.getTongTien()), tenNV, tenKH});
        }
    }
    private DefaultComboBoxModel<String> loadKhachHangData() {
        ArrayList<KhachHangDTO> danhSachKhachHang = khachHangBUS.getAll();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (KhachHangDTO kh : danhSachKhachHang) {
            String item = kh.getIdKhachHang() + " - " + kh.getTenKhachHang();
            model.addElement(item);
        }
        return model;
    }

    private DefaultComboBoxModel<String> loadNhanVienData() {
        ArrayList<NhanVienDTO> danhSachNhanVien = nhanVienBUS.getAll();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (NhanVienDTO nv : danhSachNhanVien) {
            String item = nv.getIdNV() + " - " + nv.getHoTen();
            model.addElement(item);
        }
        return model;
    }

    public HoaDonGUI(int idNVHienTai) {
        String[] columnNames = {"Mã hóa đơn", "Thời gian lập", "Tổng tiền", "Tên nhân viên", "Tên Khách hàng"};
        tblModel = new DefaultTableModel(columnNames, 0);
        loadDataTalbe();
        setLayout(null);
        JPanel panel_HoaDon = new JPanel();
        panel_HoaDon.setBounds(10, 11, 790, 557);
        add(panel_HoaDon, BorderLayout.NORTH);
        add(panel_HoaDon);

        JButton btn_them = new JButton("Thêm");
        btn_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                themHoaDon_Dialog hddialog = new themHoaDon_Dialog(idNVHienTai);
                hddialog.setSize(1200, 600);
                hddialog.setVisible(true);
            }
        });
        btn_them.setForeground(new Color(0, 0, 0));
        btn_them.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc_btn_them = new GridBagConstraints();
        gbc_btn_them.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn_them.insets = new Insets(0, 0, 5, 5);
        gbc_btn_them.gridx = 0;
        gbc_btn_them.gridy = 0;
        btn_them.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(HoaDonGUI.class.getResource("icon_them.png"))));


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
        btn_sua.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(HoaDonGUI.class.getResource("icon_sua.png"))));


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
        btn_xoa.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(HoaDonGUI.class.getResource("icon_xoa.png"))));
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
        btn_chitiet.setIcon(new ImageIcon(HoaDonGUI.class.getResource("/GUI/JPanel_QuanLyCuaHangDienThoai/icon_info.png")));

        JButton btn_XuatPDF = new JButton("Xuất PDF");
        btn_XuatPDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_HD.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        // Tạo một instance của JFileChooser
                        JFileChooser fileChooser = new JFileChooser();

                        // Hiển thị hộp thoại lưu file
                        int result = fileChooser.showSaveDialog(null);

                        // Kiểm tra nếu người dùng đã chọn nơi lưu và bấm nút "Lưu"
                        if (result == JFileChooser.APPROVE_OPTION) {
                            // Lấy đường dẫn đã chọn bởi người dùng
                            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                            // Tiếp tục xử lý như bình thường, sử dụng đường dẫn đã chọn để lưu file PDF
                            DefaultTableModel model = (DefaultTableModel) table_HD.getModel();
                            int idhd = (int) model.getValueAt(selectedRow, 0);
                            HoaDonDTO hoaDonDTO = hoaDonBUS.getHoaDonById(idhd);
                            Document document = new Document();
                            PdfWriter.getInstance(document, new FileOutputStream(filePath + ".pdf"));
                            document.open();
                            NhanVienBUS nvBUS =  new NhanVienBUS();
                            KhachHangBUS khBUS = new KhachHangBUS();
                            createStyledPDF(document, hoaDonDTO, nvBUS, khBUS, new ctHoaDonBUS(), new SanPhamBUS());
                            document.close();
                            JOptionPane.showMessageDialog(null, "Xuất PDF thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (FileNotFoundException | DocumentException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Xuất PDF thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn hóa đơn cần xuất PDF!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btn_XuatPDF.setIcon(new ImageIcon(HoaDonGUI.class.getResource("/GUI/JPanel_QuanLyCuaHangDienThoai/PDF-icon.png")));
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
                                .addGap(27)
                                .addComponent(btn_XuatPDF)
                                .addContainerGap(81, Short.MAX_VALUE))
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
        );
        gl_panel_HoaDon.setVerticalGroup(
                gl_panel_HoaDon.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_HoaDon.createSequentialGroup()
                                .addGap(1)
                                .addGroup(gl_panel_HoaDon.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btn_XuatPDF, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(gl_panel_HoaDon.createParallelGroup(Alignment.LEADING, false)
                                                .addComponent(btn_them, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btn_sua, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btn_xoa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btn_chitiet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE))
        );
        panel_HoaDon.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel_HoaDon.setLayout(gl_panel_HoaDon);

        JLabel lbl_hk = new JLabel("Khách hàng");
        lbl_hk.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
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
        lbl_nv.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
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
        lbl_ngaybatdau.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
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
        lbl_ngayketthuc.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
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
        lbl_tuTien.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
        lbl_tuTien.setBounds(828, 344, 123, 32);
        add(lbl_tuTien);
        double maxTongTien = hoaDonBUS.getMaxTotalAmount();
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
                    // Gọi phương thức để tải lại dữ liệu với điều kiện giá tiền từ giá tiền từ ô txt_tuTien đến maxTongTien
                    loadDataTalbeByCondition("tongTien >= " + txt_tuTien.getText() + " AND tongTien <= " + maxTongTien);
                }
            }
        });
        txt_tuTien.setBounds(828, 387, 104, 32);
        add(txt_tuTien);
        txt_tuTien.setColumns(10);

        JLabel lbl_denTien = new JLabel("Đến số tiền (VNĐ)");
        lbl_denTien.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
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

}




