package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.FoodInfoDTO;
import DTO.NutritionDTO;

public class NutDAO {

	DAO dao = new DAO();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	int cnt = 0;
	
	public NutritionDTO selectAll(String food_name) {
		
		dao.connection();
		FoodInfoDAO fidao = new FoodInfoDAO();
		FoodInfoDTO fidto = fidao.selectAll(food_name);
		NutritionDTO ndto = null;
		
		try {
			String sql = "SELECT * FROM T_NUTRITION WHERE FOOD_CODE = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, fidto.getFood_code());
			
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				int nut_code = rs.getInt("NUT_CODE");
				int nut_protein = rs.getInt("NUT_PROTEIN");
				int nut_fat = rs.getInt("NUT_FAT");
				int nut_carbohydrate = rs.getInt("NUT_CARBOHYDRATE");
				int nut_sugar = rs.getInt("NUT_SUGAR");
				int nut_natrum = rs.getInt("NUT_NATRUM");
				
				ndto = new NutritionDTO(nut_code, nut_protein, nut_fat, nut_carbohydrate, nut_sugar, nut_natrum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return ndto;
	}
	
}
