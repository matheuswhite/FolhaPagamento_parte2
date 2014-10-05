package objeto;

public class Horista extends Funcionario
{

	private float preco_hora;
	
	public Horista(Funcionario obj_horista)
	{
		super(obj_horista);
		this.preco_hora= ((Horista) obj_horista).getPreco_hora();
	}
	
	public Horista(String nome, String enderenco, int matricula,
			boolean sindicato, int tipo_pagamento, float preco_hora) {
		super(nome, enderenco, matricula, sindicato, tipo_pagamento);
		// TODO Auto-generated constructor stub
		this.preco_hora=preco_hora;
	}

	public Horista(String nome, String enderenco, int matricula,
			boolean sindicato, int tipo_pagamento, float preco_hora,
			Sindicato obj_sindicato) {
		// TODO Auto-generated constructor stub
		super(nome, enderenco, matricula, sindicato, tipo_pagamento, obj_sindicato);
		this.preco_hora=preco_hora;
	}

	public float getPreco_hora() {
		return preco_hora;
	}
	public void setPreco_hora(float preco_hora) {
		this.preco_hora = preco_hora;
	}

	@Override
	public String toString() {
		return "Horista ["+ super.toString() +", preco_hora=" + preco_hora + "]";
	}
	
	@Override
	public boolean igual(Funcionario obj_funcionario) 
	{
		boolean retorno=false;
		retorno=super.igual(obj_funcionario);
		if(retorno)
		{
			if(((Horista)obj_funcionario).getPreco_hora()==this.preco_hora)
				retorno=true;
			else
				retorno=false;
		}
		return retorno;
	}
	
	@Override
	public String pagamento()
	{
		String pagamento;
		String aux="\n";
		float salario=0;
		for(CartaoPonto obj_cartao : this.list_cartao)
		{
			if(obj_cartao.horasTrabalhadas()<=8)
			{
				salario+=obj_cartao.horasTrabalhadas()*this.getPreco_hora();
				aux=aux.concat("\nDia: "+ obj_cartao.getData() + " Hora de inicio-" +
						obj_cartao.horaInicial.get(obj_cartao.horaInicial.HOUR_OF_DAY)+":"+obj_cartao.horaInicial.get(obj_cartao.horaInicial.MINUTE));
				aux=aux.concat(" Hora de termino-"+obj_cartao.horaFinal.get(obj_cartao.horaFinal.HOUR_OF_DAY)+":"+obj_cartao.horaFinal.get(obj_cartao.horaFinal.MINUTE)+
						"("+obj_cartao.horasTrabalhadas()+" horas) Salario:"+((obj_cartao.horasTrabalhadas())*this.getPreco_hora()));
			}

			else
			{
				salario+=8*this.getPreco_hora();
				salario+=(obj_cartao.horasTrabalhadas()-8)*this.getPreco_hora()*1.5;
				aux=aux.concat("\nDia: "+ obj_cartao.getData() + " Hora de inicio-" +
						obj_cartao.horaInicial.get(obj_cartao.horaInicial.HOUR_OF_DAY)+":"+obj_cartao.horaInicial.get(obj_cartao.horaInicial.MINUTE));
				aux=aux.concat(" Hora de termino-"+obj_cartao.horaFinal.get(obj_cartao.horaFinal.HOUR_OF_DAY)+":"+obj_cartao.horaFinal.get(obj_cartao.horaFinal.MINUTE)+
						"("+obj_cartao.horasTrabalhadas()+" horas) Salario:"+((obj_cartao.horasTrabalhadas()-8)*this.getPreco_hora()*1.5+8*this.getPreco_hora()));
			}
		}
		if(this.isSindicato())
		{
			for(Pagamento obj_pagamento : this.list_pagamento)
			{
				salario-=obj_pagamento.getTaxaSindicato();
			}
		salario-=this.getObj_sindicato().getTaxa();
		}
		pagamento="O funcionario "+this.getNome()+" recebeu de salario "+ salario +" pois:";
		pagamento=pagamento.concat(aux);
		if(this.isSindicato())
		{
			for(Pagamento obj_pagamento : this.list_pagamento)
			{
				pagamento=pagamento.concat("\nTaxas sindicais: -" + obj_pagamento.getTaxaSindicato());
			}
			pagamento=pagamento.concat("\nMensalidade sindical: -" + this.getObj_sindicato().getTaxa());
		}
		pagamento=pagamento.concat("\nMetodo de pagamento: ");
		switch(this.getTipo_pagamento())
		{
		case 0:
			pagamento=pagamento.concat("Cheque pelos correios\n");
			break;
		case 1:
			pagamento=pagamento.concat("Cheque em maos\n");
			break;
		case 2:
			pagamento=pagamento.concat("Deposito\n");
			break;
		}
		return pagamento;
	}
	
	


	
	
	

}
