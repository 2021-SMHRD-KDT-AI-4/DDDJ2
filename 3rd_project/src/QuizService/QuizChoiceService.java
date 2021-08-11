package QuizService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.QuizDAO;
import DTO.CheckDTO;
import DTO.QuizDTO;

@WebServlet("/QuizChoiceService")
public class QuizChoiceService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Random r = new Random();
		QuizDAO quizdao = new QuizDAO();
		ArrayList<QuizDTO> quizlist = quizdao.quizchoice();
		int num = r.nextInt(quizlist.size());
		QuizDTO quizdto = quizlist.get(num);
		CheckDTO check = new CheckDTO(false);
		
		Gson gson = new Gson();
		
		PrintWriter out = response.getWriter();
		if (quizdto != null) {
			out.print(gson.toJson(quizdto));
		}else {
			out.print(gson.toJson(check));
		}
	}
}
