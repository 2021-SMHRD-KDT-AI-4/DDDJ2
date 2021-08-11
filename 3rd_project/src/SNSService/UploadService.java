package SNSService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DAO.SNSDAO;
import DTO.CheckDTO;
import DTO.SNSDTO;

@WebServlet("/UploadService")
public class UploadService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("EUC-KR");
		String id = request.getParameter("id");
		String content = request.getParameter("content");
		String img = request.getParameter("img");
	
		SNSDTO dto = new SNSDTO(id, content, img);
		SNSDAO dao = new SNSDAO();
		CheckDTO check;
		int cnt = dao.SNSupload(dto);
		
		if (cnt > 0) {
			System.out.println("정보수정 성공");
			check = new CheckDTO(true);
		}else {
			System.out.println("정보수정 실패");
			check = new CheckDTO(false);
		}
		Gson gson = new Gson();
		
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(check));
		
		
	}

}
