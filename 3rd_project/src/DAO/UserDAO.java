package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.CheckDTO;
import DTO.UserDTO;

public class UserDAO {

	DAO dao = new DAO();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	int cnt = 0;
	
	public int join(UserDTO dto) {
		dao.connection();
		
		try {
			String sql = "INSERT INTO T_USER VALUES (?,?,?,?,NULL,NULL,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUser_id());
			psmt.setString(2, dto.getUser_pw());
			psmt.setString(3, dto.getUser_name());
			psmt.setString(4, dto.getUser_category());
			psmt.setInt(5, dto.getUser_age());
			psmt.setString(6, dto.getUser_gender());
			psmt.setString(7, dto.getUser_tel());
			
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		
		return cnt;
	}
	
	public int update(UserDTO dto) {
		
		dao.connection();
		
		try {
			
			String sql = "UPDATE T_USER SET USER_PW=?, USER_AGE=?, USER_CATEGORY=?, USER_NAME=?, USER_TEL=? "
					+ "WHERE USER_ID=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUser_pw());
			psmt.setInt(2, dto.getUser_age());
			psmt.setString(3, dto.getUser_category());
			psmt.setString(4, dto.getUser_name());
			psmt.setString(5, dto.getUser_tel());
			psmt.setString(6, dto.getUser_id());
			
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return cnt;
	}
	
	public int profileUpdate(String id, String img) {
		
		dao.connection();
		
		try {
			String sql = "UPDATE T_USER SET USER_PROFILE=? WHERE USER_ID=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, img);
			psmt.setString(2, id);
			
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return cnt;
	}

	public CheckDTO login(UserDTO dto) {
		
		dao.connection();
		CheckDTO check = new CheckDTO(false);
		try {
			String sql = "SELECT * FROM T_USER WHERE ID=? AND PW=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUser_id());
			psmt.setString(2, dto.getUser_pw());
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				check = new CheckDTO(true);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return check;
	}
}