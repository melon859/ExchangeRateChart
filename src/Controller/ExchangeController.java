package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ExchangeService;

@WebServlet("/exchange")
public class ExchangeController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String currency = request.getParameter("currency");
		
		ExchangeService exchangeService = new ExchangeService();
		
		PrintWriter out = response.getWriter();
		

		try {
			out.println(exchangeService.getExchangeData(currency));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
