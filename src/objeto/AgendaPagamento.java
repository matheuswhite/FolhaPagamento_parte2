package objeto;

import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import javax.print.attribute.standard.MediaSize.Engineering;

public class AgendaPagamento {
	
	private int tipo;
	private int dia;
	private int diaSemana;
	private int count;
	private int countMax;
	
	public static final int DEFAULT = 0;
	public static final int SEMANAL = 1;
	public static final int MENSAL = 2;
	public static final int BISEMANAL = 3;
	public static final int CADA_SEMANA = 4;
	public static final int ULTIMO_DIA = 32;
	
	public static final int ERRO_TIPO = 1;
	public static final int ERRO_DIA = 2;
	public static final int ERRO_ENTRADA_INVALIDA = 3;
	
	public AgendaPagamento() {
		this.tipo = AgendaPagamento.DEFAULT;
		this.dia = AgendaPagamento.DEFAULT;
		this.diaSemana = AgendaPagamento.DEFAULT;
		this.count = AgendaPagamento.DEFAULT;
		this.countMax = AgendaPagamento.DEFAULT;
	}
	
	public AgendaPagamento(int tipo, int dia, int diaSemana) {
		this.tipo = tipo;
		this.dia = dia;
		this.diaSemana = diaSemana;
		this.count = AgendaPagamento.DEFAULT;
		this.countMax = AgendaPagamento.DEFAULT;
	}
	
	@Override
	public String toString() {
		String saida = "";
		
		saida += "|Tipo: ";
		switch (this.tipo) {
		case AgendaPagamento.DEFAULT:
			saida += "Nenhum |";
			break;
		case AgendaPagamento.SEMANAL:
			saida += "Semanal |";
			break;
		case AgendaPagamento.MENSAL:
			saida += "Mensal |";
			break;
		case AgendaPagamento.BISEMANAL:
			saida += "Bisemanal |";
			break;
		case AgendaPagamento.CADA_SEMANA:
			saida += "A cada " + this.countMax + " semanas |";
			break;
		default:
			saida += "Error |";
			break;
		}
		
		saida += "Dia: ";
		if(this.dia == AgendaPagamento.ULTIMO_DIA) {
			saida += "Ultimo dia do mes |";
		}
		else if(this.dia == AgendaPagamento.DEFAULT) {
			saida += "Nenhum |";
		}
		else {
			saida += this.dia + " |";
		}
		
		if(this.tipo != AgendaPagamento.MENSAL && this.tipo != AgendaPagamento.DEFAULT) {
			saida += "Dia da Semana: ";
			
			saida += diaDaSemana(this.diaSemana) + " |";
		}
		
		return saida;
	}
	
	private String diaDaSemana(int dia) {
		String saida = "";
		
		if(dia == GregorianCalendar.MONDAY) {
			saida += "Segunda";
		}
		else if(dia == GregorianCalendar.TUESDAY) {
			saida += "Terça";
		}
		else if(dia == GregorianCalendar.WEDNESDAY) {
			saida += "Quarta";
		}
		else if(dia == GregorianCalendar.THURSDAY) {
			saida += "Quinta";
		}
		else if(dia == GregorianCalendar.FRIDAY) {
			saida += "Sexta";
		}
		else if(dia == GregorianCalendar.SATURDAY) {
			saida += "Sabado";
		}
		else if(dia == GregorianCalendar.SUNDAY) {
			saida += "Domingo";
		}
		
		return saida;
	}
	
	private int diaDaSemana(String string) {
		int saida = -1;
		
		System.out.println("|" + string + "|");
		
		if(string.contains("segunda")) {
			saida = GregorianCalendar.MONDAY;
		}
		else if(string.contains("terca") || string.contains("terça")) {
			saida = GregorianCalendar.TUESDAY;
		}
		else if(string.contains("quarta")) {
			saida = GregorianCalendar.WEDNESDAY;
		}
		else if(string.contains("quinta")) {
			saida = GregorianCalendar.THURSDAY;
		}
		else if(string.contains("sexta")) {
			saida = GregorianCalendar.FRIDAY;
		}
		else if(string.contains("sabado") || string.contains("sábado")) {
			saida = GregorianCalendar.SATURDAY;
		}
		else if(string.contains("domingo")) {
			saida = GregorianCalendar.SUNDAY;
		}
		
		return saida;
	}
	
	private String helpMensagem(int tipo) {
		String saida = "";
		
		switch (tipo) {
		case AgendaPagamento.ERRO_TIPO:
			saida += "Escolha entre: 'mensal' ou 'semanal'";
			break;
		case AgendaPagamento.ERRO_DIA:
			saida += "Escolha um dia entre 1 e 28\nPara escolher um dia depois de 28 digite $";
			break;
		case AgendaPagamento.ERRO_ENTRADA_INVALIDA:
			saida += "Entrada invalida";
			break;
		default:
			break;
		}
		
		return saida;
	}
	
	public void novaAgenda() {
		Scanner scan = new Scanner(System.in);
		String entrada = null; 
		String tipo1 = null; 
		String tipo2 = null; 
		String semana = null; 
		String dia = null;
		int args2 = 0;
		boolean exit = false;
		boolean erro = false;
		
		while(!exit) {
			System.out.println("Digite a agenda a ser criada");
			System.out.println("[Tipo] [Dia - 00] [Dia da Semana] - ($ para escolher o ultimo dia do mes)");
		
			try {
				entrada = scan.nextLine();
				entrada = entrada.toLowerCase();
				
				tipo1 = entrada.substring(0, 6);
				tipo2 = entrada.substring(0, 7);
				
				dia = entrada.substring(7);
			}
			catch (NumberFormatException ex) {
				System.out.println("Erro - " + helpMensagem(AgendaPagamento.ERRO_ENTRADA_INVALIDA));
				erro = true;
			}
			catch (StringIndexOutOfBoundsException ex) {
				System.out.println("Erro - " + helpMensagem(AgendaPagamento.ERRO_ENTRADA_INVALIDA));
				erro = true;
			}
			catch(InputMismatchException ex) {
				System.out.println("Erro - " + helpMensagem(AgendaPagamento.ERRO_ENTRADA_INVALIDA));
				erro = true;
			}
			
			if(!erro) {
				if(tipo1.contentEquals("mensal")) {
					if(dia.contentEquals("$")) {
						this.tipo = AgendaPagamento.MENSAL;
						this.dia = AgendaPagamento.ULTIMO_DIA;
						exit = true;
					}
					try {
						if((Integer.parseInt(dia) > 1 && Integer.parseInt(dia) < 28)) {
							this.tipo = AgendaPagamento.MENSAL;
							this.dia = Integer.parseInt(dia);
							exit = true;
						}
					}
					catch (NumberFormatException ex){
							System.out.println("Erro - " + helpMensagem(AgendaPagamento.ERRO_DIA));
					}
				}
				
				else if(tipo2.contentEquals("semanal")) {
					try {
						semana = entrada.substring(10);
						
						args2 = Integer.parseInt(entrada.substring(8,9));
					}
					catch (NumberFormatException ex) {
						System.out.println("Erro - " + helpMensagem(AgendaPagamento.ERRO_ENTRADA_INVALIDA));
					}
					catch (StringIndexOutOfBoundsException ex) {
						System.out.println("Erro - " + helpMensagem(AgendaPagamento.ERRO_ENTRADA_INVALIDA));
					}
					
					if(args2 == 1) {
						this.tipo = AgendaPagamento.SEMANAL;
					}
					else if(args2 == 2) {
						this.tipo = AgendaPagamento.BISEMANAL;
					}
					else {
						this.tipo = AgendaPagamento.CADA_SEMANA;
						this.countMax = args2;
					}
					
					this.diaSemana = diaDaSemana(semana);
					exit = true;
				}
				else {
					System.out.println("Erro - " + helpMensagem(AgendaPagamento.ERRO_TIPO));
				}
			}
			else {
				System.out.println("Erro U");
			}	
		}
		//scan.close();
	}
}
