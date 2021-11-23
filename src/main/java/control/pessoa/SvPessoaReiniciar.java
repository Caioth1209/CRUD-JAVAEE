package control.pessoa;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SvPessoaReiniciar")
public class SvPessoaReiniciar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("pessoaCadastro", null);
		
		request.setAttribute("pessoaEdicao", null);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
