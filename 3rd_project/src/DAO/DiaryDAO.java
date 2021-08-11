package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.DiaryDTO;
import DTO.FoodInfoDTO;
import DTO.NutritionDTO;

public class DiaryDAO {

	DAO dao = new DAO();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	int cnt = 0;
	
	public int makeDairy(String user_id, String date) {
		
		dao.connection();
		
		try {
			String sql = "INSERT INTO T_DIARY(USER_ID, EATEN_DATE) VALUES (?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user_id);
			psmt.setString(2, date);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return cnt;
	}
	
	public int inputDiary(String id, String food_name, String time, String day) {
		
		dao.connection();
		FoodInfoDAO fidao = new FoodInfoDAO();
		FoodInfoDTO food_dto = fidao.selectAll(food_name);
		NutDAO nut_dao = new NutDAO();
		NutritionDTO nut_dto = nut_dao.selectAll(food_name);
		
		try {
			String sql = "INSERT INTO T_DIARY VALUES(FOOD_CODE=?, USER_ID=?, EATEN_DATE=?,EATEN_TIME=?, NUT_CARBOHYDRATE=?, NUT_PROTEIN=?, NUT_FAT=?, NUT_SUGAR=?,NUT_NATRUM=?,FOOD_CALORY=?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, food_dto.getFood_code());
			psmt.setString(2, id);
			psmt.setString(3, day);
			psmt.setString(4, time);
			psmt.setFloat(5, nut_dto.getNut_carbohydrate());
			psmt.setFloat(6, nut_dto.getNut_protein());
			psmt.setFloat(7, nut_dto.getNut_fat());
			psmt.setFloat(8, nut_dto.getNut_sugar());
			psmt.setFloat(9, nut_dto.getNut_natrum());
			psmt.setInt(10, food_dto.getFood_calory());
			int cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return cnt;
	}
	
	public DiaryDTO total(String id, String day) {
		DiaryDTO diarydto = null;
		float carbohydrate = 0;
		float protein = 0;
		float fat = 0;
		float natrum = 0;
		float sugar = 0;
		int calory = 0;
		
		try {
			String sql = "SELECT * FROM T_DIARY WHERE USER_ID=? AND EATEN_DATE = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, day);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				carbohydrate += rs.getFloat("NUT_CARBOHYDRATE");
				protein += rs.getFloat("NUT_PROTEIN");
				fat += rs.getFloat("NUT_FAT");
				natrum += rs.getFloat("NUT_NATRUM");
				sugar += rs.getFloat("NUT_SUGAR");
				calory += rs.getInt("FOOD_CALORY");
			}
			diarydto = new DiaryDTO(carbohydrate, protein, fat, sugar, natrum, calory);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return diarydto;
	}
}
