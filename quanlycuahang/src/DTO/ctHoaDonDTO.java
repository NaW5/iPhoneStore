package DTO;

public class ctHoaDonDTO {
	private int soLuong;
	private float donGia;
	private double thanhTien;
	private int KHUYENMAI_idKM;
	private int PHIEUBAOHANH_idBaoHanh;
	private int SANPHAM_idSP;
	private int HOADON_idHoaDon;
	public ctHoaDonDTO(int soLuong, float donGia, double thanhTien, int kHUYENMAI_idKM, int pHIEUBAOHANH_idBaoHanh,
			int sANPHAM_idSP, int hOADON_idHoaDon) {
		super();
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
		this.KHUYENMAI_idKM = kHUYENMAI_idKM;
		this.PHIEUBAOHANH_idBaoHanh = pHIEUBAOHANH_idBaoHanh;
		this.SANPHAM_idSP = sANPHAM_idSP;
		this.HOADON_idHoaDon = hOADON_idHoaDon;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public ctHoaDonDTO() {
		super();
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public float getDonGia() {
		return donGia;
	}
	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	public int getKHUYENMAI_idKM() {
		return KHUYENMAI_idKM;
	}
	public void setKHUYENMAI_idKM(int kHUYENMAI_idKM) {
		KHUYENMAI_idKM = kHUYENMAI_idKM;
	}
	public int getPHIEUBAOHANH_idBaoHanh() {
		return PHIEUBAOHANH_idBaoHanh;
	}
	public void setPHIEUBAOHANH_idBaoHanh(int pHIEUBAOHANH_idBaoHanh) {
		PHIEUBAOHANH_idBaoHanh = pHIEUBAOHANH_idBaoHanh;
	}
	public int getSANPHAM_idSP() {
		return SANPHAM_idSP;
	}
	public void setSANPHAM_idSP(int sANPHAM_idSP) {
		SANPHAM_idSP = sANPHAM_idSP;
	}
	public int getHOADON_idHoaDon() {
		return HOADON_idHoaDon;
	}
	public void setHOADON_idHoaDon(int hOADON_idHoaDon) {
		HOADON_idHoaDon = hOADON_idHoaDon;
	}
	
}
