package model.persistencia;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.entidade.Pessoa;

public class PPessoa {
	
	Criteria crit;
	
	public PPessoa() {
		
	}
	
	public boolean cadastrar(Pessoa p) {
		
		Conexao conn = new Conexao();
		
		boolean retorno = true;

		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				boolean cpfExist = false;
				
				boolean emailExist = false;
				
				if (cpfExist(p, "s")) {
					
					cpfExist = true;
					
					p.setCpf("");
					
					retorno = false;
					
				}
				
				if (emailExist(p, "s")) {
					
					emailExist = true;
					
					p.setEmail("");
					
					retorno = false;
					
				}
				
				
				if (retorno) {
					
					conn.sessao.beginTransaction();
					
					conn.sessao.save(p);
					
					conn.sessao.getTransaction().commit();
					
					p.msg = "Pessoa cadastrado com sucesso!";
	
				} else {
					
					if(emailExist && cpfExist) {
						
						p.msg = "CPF e E-mail já cadastrados!";
						
					} else {
						
						if(emailExist) {
							p.msg = "E-mail já cadastrado!";
						} else {
							p.msg = "CPF já cadastrado!";
						}
					}
					
				}
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao cadastrar pessoa: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
				
	}
	
	public boolean localizar(Pessoa p) {
		
		ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Pessoa.class);
				
				crit.add(Restrictions.like("nome", "%" + p.getNome() + "%"));
				crit.addOrder(Order.asc("id"));
				
				lista = (ArrayList<Pessoa>) crit.list();
				
				try {
					p.setListaPessoas(lista);
				} catch (IndexOutOfBoundsException e) {
					retorno = false;
				}
			
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao localizar pessoa: " + e.toString();
				retorno = false;
			
			}
		}
	
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
		
	}
	
	public boolean editar(Pessoa p) {
		
		Conexao conn = new Conexao();
		
		boolean retorno = true;

		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				boolean cpfExist = false;
				
				boolean emailExist = false;
				
				if (cpfExist(p)) {
					
					cpfExist = true;
					
					p.setCpf("");
								
					retorno = false;
					
				}
				
				if (emailExist(p)) {
					
					emailExist = true;
					
					p.setEmail("");
					
					retorno = false;
					
				}
				
				
				if (retorno) {
					
					conn.sessao.beginTransaction();
					
					conn.sessao.update(p);
					
					conn.sessao.getTransaction().commit();
					
					p.msg = "Informações editadas com sucesso!";
	
				} else {
					
					if(emailExist && cpfExist) {
						
						p.msg = "CPF e E-mail já cadastrados!";
						
					} else {
						
						if(emailExist) {
							p.msg = "E-mail já cadastrado!";
						} else {
							p.msg = "CPF já cadastrado!";
						}
					}
					
				}
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao editar pessoa: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
		
	}
	
	public boolean deletar(Pessoa p) {
		
		Conexao conn = new Conexao();
		
		boolean retorno = true;

		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				if (retorno) {
					
					conn.sessao.beginTransaction();
					
					conn.sessao.delete(p);
					
					conn.sessao.getTransaction().commit();
					
					p.msg = "Pessoa deletada com sucesso!";
	
				}
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao deletar pessoa: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	
	}
	
	// Procura se existe alguma pessoa com o cpf, excluindo a pessoa que está sendo editada.
	public boolean cpfExist(Pessoa p) {
				
		Conexao conn = new Conexao();
		
		boolean retorno;
		
		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Pessoa.class);
							

				crit.add(Restrictions.and(Restrictions.not(Restrictions.eq("id", p.getId())), Restrictions.eq("cpf", p.getCpf())));
				
				
				if (crit.uniqueResult() != null) {
					
					retorno = true;
					
				} else {
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao localizar pessoa: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	
	}
	
	//	Procura se existe alguma pessoa com o cpf.
	public boolean cpfExist(Pessoa p, String a) {
		
		Conexao conn = new Conexao();
		
		boolean retorno;
		
		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Pessoa.class);
							

				crit.add(Restrictions.eq("cpf", p.getCpf()));
				
				
				if (crit.uniqueResult() != null) {
					
					retorno = true;
					
				} else {
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao localizar pessoa: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
		
	}
	
	// Procura se existe alguma pessoa com o email, excluindo a pessoa que está sendo editada.
	public boolean emailExist(Pessoa p) {
		
		Conexao conn = new Conexao();
		
		boolean retorno;
		
		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Pessoa.class);
							

				crit.add(Restrictions.and(Restrictions.not(Restrictions.eq("id", p.getId())), Restrictions.eq("email", p.getEmail())));
				
				
				if (crit.uniqueResult() != null) {
					
					retorno = true;
					
				} else {
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao localizar pessoa: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
			
	}
		
	//	Procura se existe alguma pessoa com o email.
	public boolean emailExist(Pessoa p, String a) {
			
		Conexao conn = new Conexao();
		
		boolean retorno;
		
		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Pessoa.class);
							

				crit.add(Restrictions.eq("email", p.getEmail()));
				
				
				if (crit.uniqueResult() != null) {
					
					retorno = true;
					
				} else {
									
					retorno = false;
			
				}

				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao localizar pessoa: " + e.toString();
				
				retorno = false;
				
			}
		}
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			
			retorno = false;
		}
		
		return retorno;
	}

	public boolean listarPessoas(Pessoa p) {
		
		ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
		
		Conexao conn = new Conexao();
	
		boolean retorno = true;
		
		if (!conn.conectar()) {
			
			p.msg = conn.msg;
			retorno = false;
			
		} else {
			
			try {
				
				crit = conn.sessao.createCriteria(Pessoa.class);
				
				crit.addOrder(Order.asc("id"));
				
				lista = (ArrayList<Pessoa>) crit.list();
				
				try {
					p.setListaPessoas(lista);
				} catch (IndexOutOfBoundsException e) {
					retorno = false;
				}
			
				
			} catch (HibernateException e) {
				
				p.msg = "Erro ao listar pessoas: " + e.toString();
				retorno = false;
			
			}
		}
	
		
		if (!conn.fechar()) {
			
			p.msg = conn.msg;
			retorno = false;
			
		}
		
		return retorno;
		
	}
}
