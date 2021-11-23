package model.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import model.persistencia.PPessoa;

@Entity(name = "pessoas")
@DynamicUpdate(value = true) 
@SelectBeforeUpdate(value = true)
@DynamicInsert(value = true)
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 30)
	private String nome;
	
	@Column(length = 14)
	private String cpf;
	
	@Column(length = 50)
	private String email;
	
	@Transient
	private List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
	
	@Transient
	public String msg;

	public Pessoa() {
		
	}
	
	
	public Pessoa(String nome) {
		this.nome = nome;
	}


	public Pessoa(String nome, String cpf, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}


	public Pessoa(String nome, String cpf, String email, List<Pessoa> listaPessoas) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.listaPessoas = listaPessoas;
	}


	public boolean cadastrar() {
		PPessoa pp = new PPessoa();
		return pp.cadastrar(this);
	}
	
	public boolean editar() {
		PPessoa pp = new PPessoa();
		return pp.editar(this);
	}
	
	public boolean localizar() {
		PPessoa pp = new PPessoa();
		return pp.localizar(this);
	}
	
	public boolean deletar() {
		PPessoa pp = new PPessoa();
		return pp.deletar(this);
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}


	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
	
	
	
}
