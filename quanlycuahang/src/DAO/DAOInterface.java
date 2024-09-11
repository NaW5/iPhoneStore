package DAO;

import java.util.ArrayList;

public interface DAOInterface<T> {
	public int insert(T t);

	public int update(T t);

	public int delete(String t);

	public ArrayList<T> selectAll();
	
	public ArrayList<T> selectByCondition(String condition);

	public T selectById(int t);
	

}
