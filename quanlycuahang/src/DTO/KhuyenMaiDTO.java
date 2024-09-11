package DTO;

import java.sql.Date;

public class KhuyenMaiDTO {
	private int idKM;
	private String dieuKien;
	private float phanTram;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	public KhuyenMaiDTO(int idKM, String dieuKien, float phanTram, Date ngayBatDau, Date ngayKetThuc) {
		super();
		this.idKM = idKM;
		this.dieuKien = dieuKien;
		this.phanTram = phanTram;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
	}
	public int getIdKM() {
		return idKM;
	}
	public void setIdKM(int idKM) {
		this.idKM = idKM;
	}
	public String getDieuKien() {
		return dieuKien;
	}
	public void setDieuKien(String dieuKien) {
		this.dieuKien = dieuKien;
	}
	public float getPhanTram() {
		return phanTram;
	}
	public void setPhanTram(float phanTram) {
		this.phanTram = phanTram;
	}
	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	
}
