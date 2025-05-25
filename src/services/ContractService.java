package services;

import java.time.LocalDate;
import java.time.LocalDateTime;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService{

	private OnLinePaymentService onLinePaymentService;

	public ContractService(OnLinePaymentService onLinePaymentService) {
		this.onLinePaymentService = onLinePaymentService;
	}
	
	public void processContract(Contract contract, int months){
		
		double basicQuota = contract.getTotalvalue() / months;
		
		for(int i = 1; i <=months; i++) {
			LocalDateTime dueDate = contract.getDate().plusMonths(i);
			
			double interest = onLinePaymentService.interest(basicQuota, i);
			double fee = onLinePaymentService.paymentFee(basicQuota + interest);
			double quota = basicQuota + interest + fee;
			
			contract.getInstallments().add(new Installment());
		}
	}
}
