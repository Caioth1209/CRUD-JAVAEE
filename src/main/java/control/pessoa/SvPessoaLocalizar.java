package control.pessoa;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Pessoa;


@WebServlet("/SvPessoaLocalizar")
public class SvPessoaLocalizar extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomeProcura = request.getParameter("nomeProcura");
		
		Pessoa p = new Pessoa(nomeProcura);
		
		if (p.localizar()) {
			
			request.getSession().setAttribute("lista", p.getListaPessoas());
			
		} else {
			
			request.getSession().setAttribute("msgErro", p.msg);
			
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
