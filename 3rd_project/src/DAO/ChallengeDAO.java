package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.ChallengeDTO;
import DTO.QuizDTO;

public class ChallengeDAO {

	DAO dao = new DAO();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	int cnt = 0;
		
	public ArrayList<ChallengeDTO> challengeChoice() {
		
		ChallengeDTO challengedto = null;
		ArrayList<ChallengeDTO> challengeList = new ArrayList<ChallengeDTO>();
		dao.connection();
		
		try {
			String sql = "SELECT * FROM T_CHALLENGE";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				String content = rs.getString("CHALLENGE_CONTENT");
				int point = rs.getInt("CHALLENGE_POINT");
				challengedto = new ChallengeDTO(content, point);
				challengeList.add(challengedto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return challengeList;
	}
	
}
