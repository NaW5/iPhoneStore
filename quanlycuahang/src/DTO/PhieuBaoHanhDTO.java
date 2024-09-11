package DTO;

public class PhieuBaoHanhDTO {
	private int idBaoHanh;
	private String thoiGian;
	public PhieuBaoHanhDTO(int idBaoHanh, String thoiGian) {
		super();
		this.idBaoHanh = idBaoHanh;
		this.thoiGian = thoiGian;
	}
	public int getIdBaoHanh() {
		return idBaoHanh;
	}
	public void setIdBaoHanh(int idBaoHanh) {
		this.idBaoHanh = idBaoHanh;
	}
	public String getThoiGian() {
		return thoiGian;
	}
	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}
	
}
