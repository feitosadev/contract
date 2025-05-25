package aplication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import services.ContractService;
import services.PayPalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);		
		Scanner entry = new Scanner(System.in);
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre com os dados do Contrato");
		System.out.println("Número: ");
		int ContractNumber = entry.nextInt();
		System.out.println("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(entry.next(), dateTimeFormatter);
		System.out.println("Valor do contrato: ");
		double totalValue = entry.nextDouble();
		
		Contract contract = new Contract(ContractNumber, date, totalValue);
		
		System.out.print("Entre com o número de parcelas: ");
		int numberInterest = entry.nextInt();
		
		ContractService contractService = new ContractService(new PayPalService());
		
		contractService.processContract(contract, numberInterest);
		
		System.out.println("Parcelas.");
		for(Installment installment : contract.getInstallments()) {
			System.out.println(installment);
		}
		
		entry.close();
	}
}