package application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PayPalService;

public class Program {
    public static void main(String[] args) throws Exception {

        Locale.setDefault(Locale.US);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre os dados do contrato:");
        System.out.print("Numero: ");
        int number = sc.nextInt();
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(), fmt);
        System.out.print("Valor do contrato: ");
        double totalValue = sc.nextDouble();

        Contract obj = new Contract(number, date, totalValue);

        System.out.print("Entre com o numero de parcelas: ");
        int n = sc.nextInt();
        ContractService contractService = new ContractService(new PayPalService());
        contractService.processContract(obj, n);

        System.out.println("Parcelas: ");
        for (Installment installment : obj.getInstalments()) {
            System.out.println(installment);
        }
        sc.close();
    }
}
