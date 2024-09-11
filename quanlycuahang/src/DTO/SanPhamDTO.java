package DTO;

public class SanPhamDTO {
	private int idSP;
	private String tenSP;
	private float giaNhap;
	private float giaBan;
	private int soLuong;
	private String hinhAnh;
	private String mauSac;
	private int isDelete;

	public SanPhamDTO(int idSP, String tenSP, float giaNhap, float giaBan, int soLuong, String hinhAnh, String mauSac, int isDelete) {
		this.idSP = idSP;
		this.tenSP = tenSP;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.soLuong = soLuong;
		this.hinhAnh = hinhAnh;
		this.mauSac = mauSac;
		this.isDelete = isDelete;
	}

	public SanPhamDTO() {

	}

	public SanPhamDTO(int id) {
		this.idSP = id;
	}

	public int getIdSP() {
		return idSP;
	}

	public void setIdSP(int idSP) {
		this.idSP = idSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public float getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(float giaNhap) {
		this.giaNhap = giaNhap;
	}

	public float getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getMauSac() {
		return mauSac;
	}

	public void setMauSac(String mauSac) {
		this.mauSac = mauSac;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
}
