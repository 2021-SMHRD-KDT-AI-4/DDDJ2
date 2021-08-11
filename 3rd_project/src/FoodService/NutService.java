package FoodService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FoodInfoDAO;
import DAO.NutDAO;
import DTO.CheckDTO;
import DTO.FoodInfoDTO;
import DTO.NutritionDTO;

@WebServlet("/NutService")
public class NutService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String food_name = request.getParameter("food_name");
		
		CheckDTO check;
		NutDAO ndao = new NutDAO();
		NutritionDTO ndto = ndao.selectAll(food_name);
		FoodInfoDAO fidao = new FoodInfoDAO();
		FoodInfoDTO fidto = fidao.selectAll(food_name);
		
		if (fidto == null) {
			check = new CheckDTO(false);
			
		}else {
			check = new CheckDTO(true);
			
		}
	
	}

}
