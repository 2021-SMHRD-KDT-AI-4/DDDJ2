package SNSService;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SNSDAO;
import DTO.SNSDTO;

@WebServlet("/ViewService")
public class ViewService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		SNSDTO dto;
		
		ArrayList<SNSDTO> list = new ArrayList<SNSDTO>();
		
		SNSDAO dao = new SNSDAO();
		
		list = dao.viewSNS();
	
	}

}
