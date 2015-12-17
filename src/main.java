import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("┬──────────────────────────────────────────────┬");
		System.out.println("│Программная реализация процессингового центра.│");
		System.out.println("│──────────────────────────────────────────────│");

		for(;;) { //в интерфейсе должен быть адекватная кнопка выхода, чтобы выйти только из самого интерфейса и вернуться в цикл
			System.out.println("\nПрограмма предоставляет следующие интерфейсы для работы:");
			System.out.println("1. Сайт.");
			System.out.println("2. Платежный терминал.");
			System.out.println("3. Банкомат.\n");
			System.out.println("──────────────────────────────────────────────");
			System.out.println("4. Терминал администратора (для правки данных пользователей).");
			System.out.println("5. Выйти из программы.\n");
			
			int menu = in.nextInt();
			if (menu==1) {
				System.out.println("<Website interface> is not implemented yet.");
			}
			else if (menu==2) {
				System.out.println("<PaymentTerminal> is not implemented yet.");				
			}
			else if (menu==3) {
				System.out.println("<ATM> is not implemented yet.");
				//Отправка 
			}
			else if (menu==4) { //открыть в новом терминале
				System.out.println("<AdmTerminal> is not implemented yet.");				
			}
			else if (menu==5) {
				System.out.println("Вы уверены, что хотите выйти из программы? (д/н)");
				String menu2 = new String((String) in.next());
				if (menu2.equals("д"))
					System.exit(0);
				//if (menu2.equals("н"))	
			}
			else
				System.out.println("Неверный аргумент, введите снова!");
		}
		
		
		//Запуск программы приводит к:
			//Вызов интерфейса вебсайта
			//Вызов интерфейса терминала
			//Вызов интерфейса банкомата
			//Вызов интерфейса администратора (там просто пути к конфигурационным файлам)

	}

}
