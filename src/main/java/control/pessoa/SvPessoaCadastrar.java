package control.pessoa;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entidade.Pessoa;


@WebServlet("/SvPessoaCadastrar")
public class SvPessoaCadastrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		
		String cpf = request.getParameter("cpf");
		
		String email = request.getParameter("email");
		
		Pessoa p = new Pessoa(nome, cpf, email);
		
		if (p.cadastrar()) {
			
			p.listarPessoas();
			
			request.getSession().setAttribute("lista", p.getListaPessoas());
			
			request.setAttribute("msgCadastrado", p.msg);
			
		} else {
			
			request.setAttribute("msgErro", p.msg);
			
			request.setAttribute("pessoaCadastro", p);
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
