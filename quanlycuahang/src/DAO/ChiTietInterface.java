package DAO;

import java.util.ArrayList;


public interface ChiTietInterface<T> {
    public int insert(ArrayList<T> t);
    public int delete(String t);
    public int update(ArrayList<T> t, String pk);
    public ArrayList<T> selectAll(String t);
    public T selectById(int t);

    public ArrayList<T> selectById_ctp(int t);
}
