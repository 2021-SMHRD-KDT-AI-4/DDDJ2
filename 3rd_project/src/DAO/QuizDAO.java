package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.QuizDTO;

public class QuizDAO {

	DAO dao = new DAO();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	int cnt = 0;
		
	public ArrayList<QuizDTO> quizchoice() {
		
		QuizDTO quizdto = null;
		ArrayList<QuizDTO> quizlist = new ArrayList<QuizDTO>();
		dao.connection();
		
		try {
			String sql = "SELECT * FROM T_QUIZ";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				String content = rs.getString("QUIZ_CONTENT");
				int point = rs.getInt("QUIZ_POINT");
				quizdto = new QuizDTO(content, point);
				quizlist.add(quizdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return quizlist;
	}
	
}
