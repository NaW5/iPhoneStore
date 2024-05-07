package GUI.Dialog;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BUS.TaiKhoanBUS;
import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import com.toedter.calendar.JDateChooser;
public class SuaTaiKhoan extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel hi = new JPanel();

    private JTextField jTxt_tenDangNhap;
    private JTextField jTxt_matKhau;
    private JTextField jTxt_chucVu;
    private TaiKhoanBUS tkBUS;
    private int idNv;

    /**
     * Launch the application.
     */


    public SuaTaiKhoan() {

        tkBUS =  new TaiKhoanBUS();
        setTitle("Sửa Tài Khoản");
        setBounds(100, 100, 507, 590);
        getContentPane().setLayout(new BorderLayout());
        hi.setLayout(new FlowLayout());
        hi.setBorder(new EmptyBorder(5, 5, 5, 5));

        getContentPane().add(hi, BorderLayout.CENTER);
        {

            getContentPane().setLayout(null);

            JPanel jP_header = new JPanel();
            jP_header.setBackground(new Color(128, 255, 255));
            jP_header.setBounds(0, 0, 501, 69);
            getContentPane().add(jP_header);
            jP_header.setLayout(null);

            JLabel jL_suaNhanVien = new JLabel("SỬA TÀI KHOẢN");
            jL_suaNhanVien.setBounds(141, 10, 206, 32);
            jL_suaNhanVien.setBackground(new Color(128, 255, 255));
            jL_suaNhanVien.setFont(new Font("Dialog", Font.BOLD, 24));
            jL_suaNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
            jP_header.add(jL_suaNhanVien);

            JPanel jP_center = new JPanel();
            jP_center.setBounds(22, 80, 457, 391);
            getContentPane().add(jP_center);
            jP_center.setLayout(new GridLayout(8, 1, 0, 0));

            JLabel jL_tenDangNhap = new JLabel("Tên Đăng Nhập:");
            jL_tenDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
            jP_center.add(jL_tenDangNhap);

            jTxt_tenDangNhap = new JTextField();
            jP_center.add(jTxt_tenDangNhap);
            jTxt_tenDangNhap.setColumns(10);

            JLabel jL_matKhau = new JLabel("Mật Khẩu:");
            jL_matKhau.setFont(new Font("Tahoma", Font.PLAIN, 16));
            jP_center.add(jL_matKhau);

            jTxt_matKhau = new JTextField();
            jP_center.add(jTxt_matKhau);
            jTxt_matKhau.setColumns(10);

            JLabel jL_chucVu = new JLabel("Chức Vụ:");
            jL_chucVu.setFont(new Font("Tahoma", Font.PLAIN, 16));
            jP_center.add(jL_chucVu);

            jTxt_chucVu = new JTextField();
            jP_center.add(jTxt_chucVu);
            jTxt_chucVu.setColumns(10);


            JPanel jP_btn = new JPanel();
            jP_btn.setBounds(66, 481, 354, 59);
            getContentPane().add(jP_btn);
            jP_btn.setLayout(new GridLayout(1, 2, 5, 5));

            JButton btn_suaTaiKhoan = new JButton("Sửa Tài Khoản");
            btn_suaTaiKhoan.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get the data from the input fields
                    String tenDangNhap = jTxt_tenDangNhap.getText();
                    if(!kiemTraDauVao(tenDangNhap)){
                        return;
                    }
                    String matKhau = jTxt_matKhau.getText();
                    if(!kiemTraDauVao(matKhau)){
                        return;
                    }
                    String chucVu = jTxt_chucVu.getText();
                    //còn cái chức vụ nữa
                    // Update the NhanVienDTO object with the new data
                    TaiKhoanDTO tkdto = new TaiKhoanDTO();
                    tkdto.setNHANVIEN_idNV(idNv);
                    tkdto.setUseName(tenDangNhap);
                    tkdto.setMatKhau(matKhau);
                    tkdto.setChucVu(chucVu);

                    tkdto.setTrangThai(1);

                    // Update the database
                    int result = tkBUS.update(tkdto);
                    if(result > 0){
                        JOptionPane.showMessageDialog(null, "Sửa nhân viên thành công!");

                    } else {
                        JOptionPane.showMessageDialog(null, "Sửa nhân viên thất bại!");
                    }

                }
            });
            btn_suaTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 16));
            btn_suaTaiKhoan.setBackground(new Color(128, 255, 255));
            jP_btn.add(btn_suaTaiKhoan);

            JButton btn_huyBo = new JButton("Hủy Bỏ");
            btn_huyBo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });
            btn_huyBo.setFont(new Font("Tahoma", Font.PLAIN, 16));
            btn_huyBo.setBackground(new Color(221, 55, 92));
            jP_btn.add(btn_huyBo);

        }
    }
    public SuaTaiKhoan(int idNv, String tenDangNhap, String matKhau, String chucVu){
        this(); // Call the no-argument constructor to initialize the GUI components
        this.idNv= idNv;

        // Set the text fields with the existing data
        jTxt_tenDangNhap.setText(tenDangNhap);
        jTxt_matKhau.setText(matKhau);
        jTxt_chucVu.setText(chucVu);
    }

    public boolean kiemTraDauVao(String hoTen){
        if (hoTen.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không được để trống!");
            return false;
        }
        // Kiểm tra xem tên nhân viên có chứa ký tự không hợp lệ nào không
        if (!hoTen.matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(null, "Chỉ được chứa chữ cái không dấu, chữ số và không chứa khoảng trắng!");
            return false;
        }
        return true;
    }

    public boolean kiemTraChucVu(String chucVu){
        if (!chucVu.matches("[a-zA-Z\\s]+")) {
            JOptionPane.showMessageDialog(null, "Chỉ được chứa chữ cái không dấu, chữ số và không chứa khoảng trắng!");
            return false;
        }
        return true;
    }


}