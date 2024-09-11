package DTO;

public class IMEIDTO {
	private int maIMEI;
	private int SANPHAM_idSP;
	private int idPhieuNhap;
	private int idPhieuXuat;
	private int trangThai;
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	public int getMaIMEI() {
		return maIMEI;
	}
	public void setMaIMEI(int maIMEI) {
		this.maIMEI = maIMEI;
	}
	public int getSANPHAM_idSP() {
		return SANPHAM_idSP;
	}
	public void setSANPHAM_idSP(int sANPHAM_idSP) {
		SANPHAM_idSP = sANPHAM_idSP;
	}
	public int getIdPhieuNhap() {
		return idPhieuNhap;
	}
	public void setIdPhieuNhap(int idPhieuNhap) {
		this.idPhieuNhap = idPhieuNhap;
	}
	public int getIdPhieuXuat() {
		return idPhieuXuat;
	}
	public void setIdPhieuXuat(int idPhieuXuat) {
		this.idPhieuXuat = idPhieuXuat;
	}
	public IMEIDTO(int maIMEI, int sANPHAM_idSP, int idPhieuNhap, int idPhieuXuat, int trangThai) {
		super();
		this.maIMEI = maIMEI;
		SANPHAM_idSP = sANPHAM_idSP;
		this.idPhieuNhap = idPhieuNhap;
		this.idPhieuXuat = idPhieuXuat;
		this.trangThai = trangThai;
	}
	public IMEIDTO() {
	}
	public IMEIDTO(int maIMEI2, int idSP, int trangThai) {
		this.maIMEI = maIMEI2;
		this.SANPHAM_idSP = idSP;
		this.trangThai = trangThai;
	}
	public IMEIDTO(int maIMEI2, int idsp) {
		this.maIMEI = maIMEI2;
		this.idPhieuNhap = 5501;
		this.idPhieuXuat = 11101;
		this.SANPHAM_idSP = idsp;
	}
	
}
