package control.pessoa;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Pessoa;

@WebServlet("/SvPessoaEditar")
public class SvPessoaEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));
		
		String nome = request.getParameter("nome");
		
		String cpf = request.getParameter("cpf");
		
		String email = request.getParameter("email");
		
		Pessoa p = new Pessoa(id, nome, cpf, email);
		
		if (p.editar()) {
			
			p.listarPessoas();
			
			request.getSession().setAttribute("lista", p.getListaPessoas());
			
			request.setAttribute("msgEditado", p.msg);
			
		} else {
			
			request.setAttribute("msgErro", p.msg);
			
			request.setAttribute("pessoaEdicao", p);
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
