package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.FoodInfoDTO;

public class FoodInfoDAO {

	DAO dao = new DAO();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	int cnt = 0;
	
	public FoodInfoDTO selectAll(String food_name) {
		
		dao.connection();
		FoodInfoDTO fidto = null;
		try {
			String sql = "SELECT * FROM T_FOODINFO WHERE FOOD_NAME = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, food_name);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				int food_code = rs.getInt("FOOD_CODE");
				String f_name = rs.getString("FOOD_NAME");
				String food_company = rs.getString("FOOD_COMPANY");
				int food_amount = rs.getInt("FOOD_AMOUNT");
				int food_calory = rs.getInt("FOOD_CALORY");
				fidto = new FoodInfoDTO(food_code, f_name, food_company, food_amount, food_calory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return fidto;
	}
	
}
