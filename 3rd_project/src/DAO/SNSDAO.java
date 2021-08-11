package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.SNSDTO;

public class SNSDAO {

	DAO dao = new DAO();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	int cnt = 0;
	
	// SNS업로드
	public int SNSupload(SNSDTO dto) {
		
		dao.connection();
		
		try {
			String sql = "INSERT INTO T_SNS VALUES(T_SNS_SEQ.NEXTVAL, ?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getUser_id());
			psmt.setString(2, dto.getSns_content());
			psmt.setString(3, dto.getSns_img());
			
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return cnt;
	}
	// SNS 리스트 뷰
	public ArrayList<SNSDTO> viewSNS() {
		
		dao.connection();
		ArrayList<SNSDTO> list = new ArrayList<SNSDTO>();
		
		try {
			String sql = "SELECT * FROM T_SNS";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt(1);
				String id = rs.getString(2);
				String content = rs.getString(3);
				String img = rs.getString(4);
				
				SNSDTO dto = new SNSDTO(num, id, content, img);
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return list;
	}
}
