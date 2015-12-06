import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Window;
import javax.swing.JScrollPane;

public class ATM {

	private JFrame MainATM;
	private JPanel panelAuthorisation;
	private JTextField textFieldCardNumber;
	private JPanel panelCardNumber, panelPinNumbers, pinPadPanel, panelOperations;

	private JPasswordField passwordField;
	private JButton enterButton, clearButton, zeroButton, buttonSuccess;

	private String inputNumberCard = ""; // номер карты
	private String input = "";// пин-код

	private JButton[] numberButtons = new JButton[9];

	private static int counter = 2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATM window = new ATM();
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
	public ATM() throws SQLException, Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainATM = new JFrame();
		MainATM.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		MainATM.setTitle("\u0411\u0430\u043D\u043A");
		MainATM.setBounds(100, 100, 437, 433);
		MainATM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainATM.getContentPane().setLayout(null);
		
		panelOperations = new JPanel();
		panelOperations.setBounds(0, 0, 421, 395);
		MainATM.getContentPane().add(panelOperations);
		panelOperations.setLayout(null);
		
		JScrollPane scrollPaneInform = new JScrollPane();
		scrollPaneInform.setBounds(37, 27, 344, 208);
		panelOperations.add(scrollPaneInform);

		panelAuthorisation = new JPanel();
		panelAuthorisation.setBounds(0, 0, 545, 395);
		MainATM.getContentPane().add(panelAuthorisation);
		panelAuthorisation.setLayout(null);

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
				if (textFieldCardNumber.getText().length() == 16){
				inputNumberCard += textFieldCardNumber.getText();
				System.out.println(inputNumberCard);
				}
				else{
					JOptionPane.showMessageDialog(null, "Недостаточно цифр!");
				}
			}
		});
		buttonSuccess.setBounds(22, 69, 89, 23);
		panelCardNumber.add(buttonSuccess);

		JButton buttonClear = new JButton("\u041E\u0447\u0438\u0441\u0442\u0438\u0442\u044C");
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

		// 0-9 buttons

		for (int i = 0; i < 9; i++) {
			numberButtons[i] = new JButton(String.valueOf(i + 1));
			numberButtons[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(!inputNumberCard.isEmpty()){
					if (passwordField.getText().length() < 4) {
						input += e.getActionCommand();

						passwordField.setText(input);
						System.out.println(input);
					}

				}
					else {
						JOptionPane.showMessageDialog(null, "Введите номер карты!");
					}
				}
				

			});
			pinPadPanel.add(numberButtons[i]);
		}

		// panelPinNumbers.add(pinPadPanel);

		clearButton = new JButton("C");
		clearButton.addActionListener(new ClearListener());

		pinPadPanel.add(clearButton);
		zeroButton = new JButton("0");

		zeroButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (passwordField.getText().length() < 4) {
					input += e.getActionCommand();
					passwordField.setText(input);
					System.out.println(input);
				}

			}

		});

		pinPadPanel.add(zeroButton);

		enterButton = new JButton(/*"\u21B5"*/"Подтвердить");
		enterButton.addActionListener(new EnterListener());

		pinPadPanel.add(enterButton);
		panelPinNumbers.add(pinPadPanel, BorderLayout.CENTER);

		JLabel label = new JLabel("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043F\u0438\u043D-\u043A\u043E\u0434:");
		label.setBounds(33, 155, 122, 14);
		panelAuthorisation.add(label);

		// MainATM.pack();
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

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			//counter--;
			try {
				if (counter != 0) {

					if ((passwordField.getText().length() == 4) && (counter != 0)) {
						counter--;

						if (Authentication.authenticatePIN(input)) {
							JOptionPane.showMessageDialog(null, "Успешная Авторизация!");

							System.out.println("Counter:" + counter);

						} else {

							JOptionPane.showMessageDialog(null, "Пин-код введен неверно! Введите еще раз!");

							System.out.println("Counter:" + counter);
							passwordField.setText("");
							input = "";

						}

					}

				} else if (passwordField.getText().length() == 4){
					
					passwordField.setText("");
					input = "";
					JOptionPane.showMessageDialog(null, "Вы превысили лимит попыток! Ваша карта заблокирована!");
					counter = 2;
					enterButton.setEnabled(false);
					

				}

			} catch (Exception ex) {
				System.out.print("Error");
			}

		}

	}
}
