package objeto;

import java.util.ArrayList;

public class Funcionario 
{
	protected String nome;
	protected String enderenco;
	protected int matricula;
	protected boolean sindicato;
	protected int tipo_pagamento;
	protected Sindicato obj_sindicato;
	public ArrayList<CartaoPonto> list_cartao;
	public ArrayList<Venda> list_venda;
	public ArrayList<Pagamento> list_pagamento;
	protected AgendaPagamento agenda;
	
	protected static final int cheque_correios = 0;
	protected static final int cheque_maos     = 1;
	protected static final int deposito        = 2;
	
	public Funcionario(Funcionario obj_funcionario)
	{
		this.agenda = new AgendaPagamento();
		this.nome = obj_funcionario.getNome();
		this.enderenco = obj_funcionario.getEnderenco();
		this.matricula = obj_funcionario.getMatricula();
		this.sindicato = obj_funcionario.isSindicato();
		this.tipo_pagamento = obj_funcionario.getTipo_pagamento();
		this.list_venda=new ArrayList<Venda>(obj_funcionario.list_venda);
		this.list_pagamento=new ArrayList<Pagamento>(obj_funcionario.list_pagamento);
		this.list_cartao=new ArrayList<CartaoPonto>(obj_funcionario.list_cartao);
		if(this.sindicato)
			this.obj_sindicato=obj_funcionario.getObj_sindicato();
		else 
			this.obj_sindicato=null;
	}
	public Funcionario(String nome, String enderenco, int matricula,
			boolean sindicato, int tipo_pagamento) {
		this.agenda = new AgendaPagamento();
		this.nome = nome;
		this.enderenco = enderenco;
		this.matricula = matricula;
		this.sindicato = sindicato;
		this.tipo_pagamento = tipo_pagamento;
		this.list_venda=new ArrayList<Venda>();
		this.list_pagamento=new ArrayList<Pagamento>();
		this.list_cartao=new ArrayList<CartaoPonto>();
		if(this.sindicato)
			this.obj_sindicato=obj_sindicato;
		else 
			this.obj_sindicato=null;
	}
	
	public Funcionario(String nome, String enderenco, int matricula,
			boolean sindicato, int tipo_pagamento, Sindicato obj_sindicato) {
		this.agenda = new AgendaPagamento();
		this.nome = nome;
		this.enderenco = enderenco;
		this.matricula = matricula;
		this.sindicato = sindicato;
		this.tipo_pagamento = tipo_pagamento;
		if(this.sindicato)
			this.obj_sindicato=obj_sindicato;
		else 
			this.obj_sindicato=null;

		this.list_venda=new ArrayList<Venda>();
		this.list_pagamento=new ArrayList<Pagamento>();
		this.list_cartao=new ArrayList<CartaoPonto>();
	}
	
	public void setAgenda(AgendaPagamento agenda) {
		this.agenda = agenda;
	}
	
	public AgendaPagamento getAgendaPagamento() {
		return this.agenda;
	}
	
	public Sindicato getObj_sindicato() {
		return obj_sindicato;
	}

	public void setObj_sindicato(Sindicato obj_sindicato) {
		this.obj_sindicato = obj_sindicato;
	}

	public String getNome() 
	{
		return nome;
	}
	
	public void setNome(String nome) 
	{
		if(!nome.isEmpty())
			this.nome = nome;
	}
	public int getMatricula() 
	{
		return matricula;
	}
	
	public void setMatricula(int matricula) 
	{
		if(this.matricula>0)
			this.matricula = matricula;
	}

	public boolean isSindicato() {
		return sindicato;
	}

	public void setSindicato(boolean sindicato) {
		this.sindicato = sindicato;
	}

	public int getTipo_pagamento() {
		return tipo_pagamento;
	}

	public void setTipo_pagamento(int tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}

	public String getEnderenco() {
		return enderenco;
	}

	public void setEnderenco(String enderenco) {
		this.enderenco = enderenco;
	}
	
	
	public boolean igual(Funcionario obj_funcionario)
	{
		boolean retorno=false;
		if(this.nome==obj_funcionario.getNome())
			if(this.enderenco==obj_funcionario.getEnderenco())
				if(this.matricula==obj_funcionario.getMatricula())
					if(this.sindicato==obj_funcionario.isSindicato())
						if(this.tipo_pagamento==obj_funcionario.getTipo_pagamento())
							if(this.list_cartao==obj_funcionario.list_cartao)
								if(this.list_pagamento==obj_funcionario.list_pagamento)
									if(this.list_venda==obj_funcionario.list_venda)
										retorno=true;
		return retorno;
	}
	
	@Override
	public String toString() {
		String retorno = "nome=" + nome + ", enderenco=" + enderenco
				+ ", matricula=" + matricula ;
		switch(this.tipo_pagamento)
		{
		case 0:
			retorno = retorno + ", tipo de pagamento= cheque pelos correios";
			break;
		case 1:
			retorno = retorno+", tipo de pagamento= cheque em maos";
			break;
		case 2:
			retorno = retorno + ", tipo de pagamento= deposito";
			break;
		}
		if(this.sindicato)
			retorno = retorno + ", sindicato=sim, taxa do sindicato="+this.obj_sindicato.getTaxa()+ ", id="+ this.obj_sindicato.getMatricula_sindicato();
		else
			retorno = retorno + ", sindicato=nao";
		return retorno;
	}
	public void addVenda(Venda obj_venda)
	{
		this.list_venda.add(obj_venda);
	}
	public void limpaVendas()
	{
		this.list_venda.clear();
	}
	
	public String pagamento()
	{
		return null;
	}
	
	public void addTaxa(Pagamento obj_pagamento)
	{
		this.list_pagamento.add(obj_pagamento);
	}
	
	public void addPonto(CartaoPonto obj_cartao)
	{
		this.list_cartao.add(obj_cartao);
	}


}
