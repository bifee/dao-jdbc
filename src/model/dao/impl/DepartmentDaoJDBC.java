package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartmentDaoJDBC implements DepartmentDao{
	
private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
		st = conn.prepareStatement("INSERT INTO department\r\n"
				+ "(Name)\r\n"
				+ "VALUES\r\n"
				+ "(?)", Statement.RETURN_GENERATED_KEYS);
		
		st.setString(1, obj.getName());
		int ra = st.executeUpdate();
		if(ra > 0) {
			ResultSet rs = st.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				obj.setId(id);
			}
			DB.closeResultSet(rs);
		}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE department\r\n"
					+ "SET Name = ?\r\n"
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getName());
			st.setInt(6, obj.getId());	
			st.executeUpdate();
			
		}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
			}
	}

	@Override
	public void deleteById(Integer Id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM department\r\n"
					+ "WHERE Id = ?");
			
			st.setInt(1, Id);
			st.executeUpdate();
		}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
			}
	}

	@Override
	public Department findById(Integer Id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT *\r\n"
					+ "FROM department\r\n"
					+ "WHERE department.Id = ?");
			
			st.setInt(1, Id);
			rs = st.executeQuery();
			if(rs.next()) {
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				return obj;
				
			}
			return null;
		}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
	}


	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT *\r\n"
					+ "FROM department\r\n"
					+ "ORDER BY Name");
			rs = st.executeQuery();
			List<Department> departmentList = new ArrayList<>();
			
			while(rs.next()) {
				Department obj = new Department();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				departmentList.add(obj);
			}
			return departmentList;
		}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally {
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
	}

}
