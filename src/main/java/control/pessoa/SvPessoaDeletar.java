package control.pessoa;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Pessoa;


@WebServlet("/SvPessoaDeletar")
public class SvPessoaDeletar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));
		
		Pessoa p = new Pessoa(id);
		
		if (p.deletar()) {
			
			p.listarPessoas();
			
			request.getSession().setAttribute("lista", p.getListaPessoas());
			
			request.setAttribute("msgDeletado", p.msg);
			
		} else {
			
			request.setAttribute("msgErro", p.msg);
			
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
