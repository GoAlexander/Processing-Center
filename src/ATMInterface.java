
//Класс для интерфейса банкомата
//task #4+5

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Window;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

// Интерфейс банкомата (задание 4 Харчевникова А)
public class ATMInterface {

	public  int ATMNumber;
	public   int MIN_SUM = 10;
	public   int MAX_SUM = 50000;
	public  JFrame MainATM;
	public  JPanel panelAuthorisation;
	public  JTextField textFieldCardNumber, textFieldSum;
	public  JPanel panelCardNumber, panelPinNumbers, pinPadPanel, panelOperations;
	public  JComboBox comboBoxCurrency;

	public  JPasswordField passwordField;
	public  JButton enterButton, clearButton, zeroButton, buttonSuccess,  btnDrawOperation;

	public JButton btnCashOperation, btnBalance, buttonClear;

	public  String inputNumberCard = ""; // номер карты
	public  String input = "";// пин-код
	public  String[] valString = { "RUB", "EUR", "USD"};

	public  JScrollPane scrollPaneInform, scrollPaneResult;
	public JTextArea textAreaOut, textAreaOutPut, textAreaResult;
	
	public FrontOffice.Bond atmBonds = new FrontOffice.Bond(); // купюры в банкомате
	

	public  JButton[] numberButtons = new JButton[9];

	private static int counter = 3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMInterface window = new ATMInterface(1);
					window.MainATM.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ATMInterface(int ATMNumber) throws SQLException, Exception {
		this.ATMNumber = ATMNumber;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		

		MainATM = new JFrame();
		MainATM.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		MainATM.setTitle("\u0411\u0430\u043D\u043A\u043E\u043C\u0430\u0442 \u21161");
		MainATM.setBounds(100, 100, 437, 448);
		MainATM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainATM.getContentPane().setLayout(null);

		panelOperations = new JPanel();
		panelOperations.setBounds(0, 0, 421, 418);
		MainATM.getContentPane().add(panelOperations);
		panelOperations.setLayout(null);

		panelOperations.setVisible(false);

		// scrollPaneInform.setColumnHeaderView(textAreaOut);

		textAreaOutPut = new JTextArea();
		textAreaOutPut.setEditable(false);
		// scrollPaneInform.setRowHeaderView(textAreaOutPut);

		scrollPaneInform = new JScrollPane(textAreaOutPut);
		scrollPaneInform.setBounds(25, 27, 180, 263);
		panelOperations.add(scrollPaneInform);
		// panelOperations.add(label);

		textFieldSum = new JTextField();
		textFieldSum.setBounds(225, 46, 184, 20);
		panelOperations.add(textFieldSum);
		textFieldSum.setColumns(10);

		btnDrawOperation = new JButton(
				"\u0421\u043D\u044F\u0442\u0438\u0435 \u043D\u0430\u043B\u0438\u0447\u043D\u044B\u0445");
		btnDrawOperation.setBounds(25, 313, 175, 37);

		panelOperations.add(btnDrawOperation);
		btnDrawOperation.addActionListener(new DrawListener());


		JLabel label_Result = new JLabel("\u0420\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442:");
		label_Result.setBounds(225, 139, 135, 14);
		panelOperations.add(label_Result);

		btnBalance = new JButton("\u0411\u0430\u043B\u0430\u043D\u0441");
		/*btnBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String result = String.valueOf(AccountingSystem.getBalance(inputNumberCard));
				textAreaResult.append("Ваш баланс = " + result +"\n");
			}
		});*/
		btnBalance.setBounds(231, 313, 180, 37);
		panelOperations.add(btnBalance);

		textAreaResult = new JTextArea();
		textAreaResult.setEditable(false);

		scrollPaneResult = new JScrollPane(textAreaResult);
		scrollPaneResult.setBounds(225, 164, 184, 126);
		panelOperations.add(scrollPaneResult);

		JLabel label_Sum = new JLabel("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0441\u0443\u043C\u043C\u0443:");
		label_Sum.setBounds(227, 28, 95, 14);
		panelOperations.add(label_Sum);

		JLabel label_3 = new JLabel("\u0427\u0435\u043A:");
		label_3.setBounds(25, 11, 68, 14);
		panelOperations.add(label_3);

		
		comboBoxCurrency = new JComboBox(valString);
		comboBoxCurrency.setBounds(331, 100, 60, 28);
		panelOperations.add(comboBoxCurrency);

		JLabel lblNewLabelCurrency = new JLabel("\u0412\u0430\u043B\u044E\u0442\u0430:");
		lblNewLabelCurrency.setBounds(227, 103, 95, 14);
		panelOperations.add(lblNewLabelCurrency);
		
		JButton buttonExit = new JButton("\u0412\u044B\u0445\u043E\u0434");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainATM.setVisible(false);
			}
		});
		buttonExit.setBounds(322, 370, 89, 28);
		panelOperations.add(buttonExit);

		panelAuthorisation = new JPanel();
		panelAuthorisation.setBounds(0, 0, 421, 418);
		MainATM.getContentPane().add(panelAuthorisation);
		panelAuthorisation.setLayout(null);

		// panelAuthorisation.setBorder(BorderFactory.createEmptyBorder(10, 10,
		// 10, 10));
		// panelAuthorisation.setMaximumSize(new Dimension(1100, 700));

		panelCardNumber = new JPanel();
		panelCardNumber.setBorder(new TitledBorder(null,
				"\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043D\u043E\u043C\u0435\u0440 \u043A\u0430\u0440\u0442\u044B",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCardNumber.setBounds(32, 11, 349, 112);
		panelAuthorisation.add(panelCardNumber);
		panelCardNumber.setLayout(null);

		textFieldCardNumber = new JTextField();

		textFieldCardNumber.setBounds(22, 28, 301, 23);
		panelCardNumber.add(textFieldCardNumber);
		textFieldCardNumber.setColumns(10);

		buttonSuccess = new JButton("\u0413\u043E\u0442\u043E\u0432\u043E");
		buttonSuccess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(isValidCardNumber()){
					inputNumberCard += textFieldCardNumber.getText();
					System.out.println(inputNumberCard);

				} else {

					JOptionPane.showMessageDialog(null, "Неверный формат!", "Ошибка", JOptionPane.WARNING_MESSAGE);
					textFieldCardNumber.setText("");

				}
			}
		});
		buttonSuccess.setBounds(22, 69, 89, 23);
		panelCardNumber.add(buttonSuccess);

		buttonClear = new JButton("\u041E\u0447\u0438\u0441\u0442\u0438\u0442\u044C");
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textFieldCardNumber.setText("");
				inputNumberCard = "";
				System.out.println(inputNumberCard + "success");
			}
		});
		buttonClear.setBounds(138, 69, 89, 23);
		panelCardNumber.add(buttonClear);

		panelPinNumbers = new JPanel();
		panelPinNumbers.setBounds(32, 180, 349, 174);
		panelAuthorisation.add(panelPinNumbers);

		panelPinNumbers.setLayout(new BorderLayout());
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Bank", Font.BOLD, 30));
		panelPinNumbers.add(passwordField, BorderLayout.NORTH);

		pinPadPanel = new JPanel();
		pinPadPanel.setBackground(SystemColor.inactiveCaption);
		pinPadPanel.setLayout(new GridLayout(4, 3));

		// panelPinNumbers.add(pinPadPanel);

		// 0-9 buttons
		for (int i = 0; i < 9; i++) {
			numberButtons[i] = new JButton(String.valueOf(i + 1));
			numberButtons[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (!inputNumberCard.isEmpty()) {
						if (passwordField.getText().length() < 4) {
							input += e.getActionCommand();

							passwordField.setText(input);
							System.out.println(input);
						}

					} else {

						JOptionPane.showMessageDialog(null, "Введите номер карты!", "Ошибка",
								JOptionPane.WARNING_MESSAGE);
					}
				}

			});
			pinPadPanel.add(numberButtons[i]);
		}

		clearButton = new JButton("C");
		clearButton.addActionListener(new ClearListener());

		pinPadPanel.add(clearButton);
		zeroButton = new JButton("0");

		zeroButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (!inputNumberCard.isEmpty()) {
					if (passwordField.getText().length() < 4) {
						input += e.getActionCommand();
						passwordField.setText(input);
						System.out.println(input);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Введите номер карты!");
				}

			}

		});

		pinPadPanel.add(zeroButton);

		enterButton = new JButton(/* "\u21B5" */"Подтвердить");
		enterButton.addActionListener(new EnterListener());

		pinPadPanel.add(enterButton);
		panelPinNumbers.add(pinPadPanel, BorderLayout.CENTER);

		JLabel label = new JLabel("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043F\u0438\u043D-\u043A\u043E\u0434:");
		label.setBounds(33, 155, 122, 14);
		panelAuthorisation.add(label);

		initializeOperations();

	}

	private boolean isValidCardNumber() {
		
		if(textFieldCardNumber.getText().matches("(?i).*[a-za-я].*"))
		{
			return false;
			
		} else if (textFieldCardNumber.getText().length() != 16) {
			return false;

		} else {
			return true;
		}
	}

	private boolean isValidDraw() {
		String message = "";

		// если не число
		if (!NumberChek(textFieldSum.getText())) {
			message = "Введите число";

			// если < 10
		} else if (Integer.parseInt(textFieldSum.getText()) < MIN_SUM) {
			message = "Минимум для снятия: 10";

			// если > 250
		} else if (Integer.parseInt(textFieldSum.getText()) > MAX_SUM) {
			message = "Максимум для снятия: 50000";

			// если не кратно 10
		} else if (Integer.parseInt(textFieldSum.getText()) % 10 != 0) {
			message = "Сумма должна быть кратной 10";
		}
			
		// если все ок
		else {
			return true;
		}

		// сообщение пользователю
		JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.WARNING_MESSAGE);
		textFieldSum.requestFocus();
		textFieldSum.selectAll();
		textFieldSum.setText(null);
		return false;
	}

	private void Draw() throws SQLException, Exception {
		if (isValidDraw()) {
			String money = textFieldSum.getText();
			int convMoney;
		
			if (comboBoxCurrency.getSelectedItem() == "EUR")
			{
				convMoney = Converion.convInt(2, Integer.parseInt(money));
	            money = String.valueOf(convMoney);
				
			}
			else if (comboBoxCurrency.getSelectedItem() == "USD")
			{
				convMoney = Converion.convInt(1, Integer.parseInt(money));	
				money = String.valueOf(convMoney);
				
			}
			
			try{	
			
			if(FrontOffice.transactionControl(inputNumberCard, input, money, "atm") == 1)
			{
				
				
			textAreaResult.setText(null);
			textAreaResult.append(FrontOffice.answerMessage(inputNumberCard, input, money, "atm"));
			
			textAreaResult.append("Вы сняли наличные в размере:" + money + " " + comboBoxCurrency.getSelectedItem() + "\n");
		 
			textAreaResult.append("Баланс:" + "\n" + "\n");
			printCheck("Снятие");
			
			
			atmBonds =  FrontOffice.bondSelection(atmBonds, Integer.parseInt(money));
			textAreaResult.append("Текущее состояние купюр в кассетах:" + "\n" );
			
			textAreaResult.append(atmBonds.toString());
			}
			else 
			   {
				textAreaResult.setText(null);
				textAreaResult.append(FrontOffice.answerMessage(inputNumberCard, input, money, "atm"));
			  }
			}
			catch(Exception e)
			{
				
				textAreaResult.append("В кассетах недостаточно купюр!" + "\n" );
			}
			


			
		}
	}

	private boolean isValidCash() {
		String message = "";

		// если не число
		if (!NumberChek(textFieldSum.getText())) {
			message = "Введите число";

			// если < 100
		} else if (Double.parseDouble(textFieldSum.getText()) < 10.0) {
			message = "Минимум для пополнения: 10";

			// если > 500
		} else if (Double.parseDouble(textFieldSum.getText()) > 50000.0) {
			message = "Максимум для пополнения: 50000";
		} else if (Integer.parseInt(textFieldSum.getText()) % 10 != 0) {
			message = "Сумма должна быть кратной 10";

			// если все ок
		} else {
			return true;
		}
		JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.WARNING_MESSAGE);
		textFieldSum.requestFocus();
		textFieldSum.selectAll();
		textFieldSum.setText(null);
		return false;

	}

	private void Cash() throws SQLException, Exception {
		if (isValidCash()) {
			textAreaResult.setText(null);
			int money = Integer.parseInt(textFieldSum.getText());

			textAreaResult.append("Вы внесли наличные в размере:" + "\n");
			textAreaResult.append(textFieldSum.getText() + "\n");
			textAreaResult.append("Баланс:" + "\n");
			printCheck("Внесение");

		}
	}

	/// проверка строки на Integer
	private boolean NumberChek(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void initializeOperations() {

		JLabel label1 = new JLabel(
				"\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0441\u0443\u043C\u043C\u0443 \u043E\u043F\u0435\u0440\u0430\u0446\u0438\u0438:");
		label1.setBounds(227, 27, 172, 19);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 11));

	}

	private void printCheck(String message) {
		textAreaOutPut.setText(null);
		textAreaOutPut.setLineWrap(true);
	
		textAreaOutPut.append("          ОАО <Банк>   " + "\n");

		textAreaOutPut.append(message + " наличных" + "\n"); // Или другая
																// операция

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd         HH:mm:ss");
		Date currentDate = new Date();
		System.out.println(dateFormat.format(currentDate));

		textAreaOutPut.append(dateFormat.format(currentDate) + "\n" + "\n");

		textAreaOutPut.append("Сумма: " + textFieldSum.getText()+ " " + comboBoxCurrency.getSelectedItem() + "\n");

		textAreaOutPut.append("Баланс карты:" /*+ String.valueOf(AccountingSystem.getBalance(inputNumberCard)*/ +"\n");
		textAreaOutPut.append("Комиссия:" + "0 RUB" + "\n");
		textAreaOutPut.append("Номер карты:" + inputNumberCard + "\n");

		textAreaOutPut.append("Банкомат:" + "№" + ATMNumber + "\n" + "\n");

		textAreaOutPut.append("***СПАСИБО!***");

	}

	class ClearListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			passwordField.setText("");
			System.out.println(input);
			input = "";
			System.out.println(input);

		}

	}

	class EnterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				if (counter != 0) {

					if ((passwordField.getText().length() == 4) && (counter != 0)) {

						counter--;

						if (FrontOffice.pinChecking(inputNumberCard, input)) {
							
						
							JOptionPane.showMessageDialog(null, "Успешная Авторизация!", "Cообщение",
									JOptionPane.INFORMATION_MESSAGE);
							panelOperations.setVisible(true);
							panelAuthorisation.setVisible(false);

							System.out.println("Counter:" + counter);

						} else {

							JOptionPane.showMessageDialog(null, "Пин-код введен неверно! Введите еще раз!", "Ошибка",
									JOptionPane.WARNING_MESSAGE);

							System.out.println("Counter:" + counter);
							passwordField.setText("");
							input = "";

						}

					}

				} else if (passwordField.getText().length() == 4) {

					passwordField.setText("");
					input = "";

					JOptionPane.showMessageDialog(null, "Вы превысили лимит попыток! Ваша карта заблокирована!",
							"Ошибка", JOptionPane.WARNING_MESSAGE);
					counter = 3;
					enterButton.setEnabled(false);

				}

			} catch (Exception ex) {
				System.out.print("Error");
			}

		}

	}

	class CashListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				Cash();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}
	class DrawListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				Draw();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}