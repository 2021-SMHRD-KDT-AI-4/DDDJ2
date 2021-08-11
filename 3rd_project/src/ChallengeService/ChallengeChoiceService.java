package ChallengeService;

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

import DAO.ChallengeDAO;
import DTO.ChallengeDTO;
import DTO.CheckDTO;

@WebServlet("/ChallengeChoiceService")
public class ChallengeChoiceService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Random r = new Random();
		ChallengeDAO challengedao = new ChallengeDAO();
		ArrayList<ChallengeDTO> challengeList = challengedao.challengeChoice();
		int num = r.nextInt(challengeList.size());
		ChallengeDTO challengedto = challengeList.get(num);
		CheckDTO check = new CheckDTO(false);
		
		Gson gson = new Gson();
		
		PrintWriter out = response.getWriter();
		if (challengedto != null) {
			out.print(gson.toJson(challengedto));
		}else {
			out.print(gson.toJson(check));
		}
	
	}

}
