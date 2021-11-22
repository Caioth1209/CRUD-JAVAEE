package model.persistencia;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Conexao {
	SessionFactory sf;
	Session sessao;
	
	public String msg;
	
	public Conexao() {
		
	}
	
	public boolean conectar() {
		
		try {
			
			sf = new Configuration().configure().buildSessionFactory();
			sessao = sf.openSession();
			
			return true;
			
		} catch (HibernateException e) {
			
			msg = "Erro ao fazer conexão: " + e.toString();
			
			return false;
			
		} 
			
	}
	
	public boolean fechar() {
		
		try {
			
			if (sessao != null && sessao.isOpen()) {
				
				sessao.close();
				
				return true;
				
			} else {
				
				msg = "Erro ao fechar conexão...";
				
				return false;
			}
			
		} catch (HibernateException e) {
			
			msg = "Erro ao fechar conexão: " + e.toString();
			
			return false;
		}
	}
}
