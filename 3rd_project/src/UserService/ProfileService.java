package UserService;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.UserDAO;
import DTO.CheckDTO;

@WebServlet("/ProfileService")
public class ProfileService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user_id = request.getParameter("id");
		String img_path = request.getParameter("img");
		
		UserDAO userdao = new UserDAO();
		CheckDTO check;
		int cnt = userdao.profileUpdate(user_id, img_path);
		
		if (cnt > 0) {
			System.out.println("프로필 업로드 성공");
			check = new CheckDTO(true);
		}else {
			System.out.println("프로필 업로드 실패");
			check = new CheckDTO(false);
		}
		Gson gson = new Gson();
		
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(check));
	
	}

}
