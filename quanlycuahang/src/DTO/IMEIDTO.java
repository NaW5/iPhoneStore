package DTO;

import static java.sql.Types.NULL;

public class IMEIDTO {
	private int maIMEI;
	private int SANPHAM_idSP;
	private Integer idPhieuNhap; // Change to Integer to allow null
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

	public Integer getIdPhieuNhap() {
		return idPhieuNhap;
	}

	public void setIdPhieuNhap(Integer idPhieuNhap) {
		this.idPhieuNhap = idPhieuNhap;
	}

	public IMEIDTO(int maIMEI, int sANPHAM_idSP, Integer idPhieuNhap, int trangThai) {
		super();
		this.maIMEI = maIMEI;
		SANPHAM_idSP = sANPHAM_idSP;
		this.idPhieuNhap = idPhieuNhap;
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
		this.idPhieuNhap = NULL;
		this.SANPHAM_idSP = idsp;
	}
}