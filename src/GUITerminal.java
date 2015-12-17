import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class GUITerm {

	private JFrame frame;
	private JTextField cardNum;
	private JTextField pinCode;
	private JTextField sum;
	private JComboBox val;
	private int counter=3;
	String currency;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITerm window = new GUITerm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUITerm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panCard = new JPanel();
		panCard.setBorder(new TitledBorder(null,
				"\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043D\u043E\u043C\u0435\u0440 \u043A\u0430\u0440\u0442\u044B",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panCard.setBounds(10, 11, 414, 47);
		frame.getContentPane().add(panCard);
		panCard.setLayout(null);

		cardNum = new JTextField();
		cardNum.setBounds(10, 16, 394, 20);
		panCard.add(cardNum);
		cardNum.setColumns(10);

		JPanel panPin = new JPanel();
		panPin.setBorder(new TitledBorder(null, "\u0412\u0432\u0435\u0434\u0438\u0442\u0435 PIN-\u043A\u043E\u0434",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panPin.setBounds(10, 63, 414, 47);
		frame.getContentPane().add(panPin);
		panPin.setLayout(null);

		pinCode = new JTextField();
		pinCode.setBounds(10, 16, 394, 20);
		panPin.add(pinCode);
		pinCode.setColumns(10);

		JPanel panSum = new JPanel();
		panSum.setBorder(new TitledBorder(null,
				"\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0441\u0443\u043C\u043C\u0443 \u0442\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u0438 \u0438 \u0432\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0432\u0430\u043B\u044E\u0442\u0443",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panSum.setBounds(10, 116, 414, 47);
		frame.getContentPane().add(panSum);
		panSum.setLayout(null);

		sum = new JTextField();
		sum.setBounds(10, 16, 339, 20);
		panSum.add(sum);
		sum.setColumns(10);

		String[] valString = { "RUB", "EUR", "USD" };
		val = new JComboBox(valString);
		val.setBounds(347, 16, 57, 20);
		panSum.add(val);

		JPanel panRes = new JPanel();
		panRes.setBorder(new TitledBorder(null, "\u041F\u0435\u0447\u0430\u0442\u044C \u0447\u0435\u043A\u0430",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panRes.setBounds(10, 163, 414, 118);
		frame.getContentPane().add(panRes);
		panRes.setLayout(null);

		JTextArea res = new JTextArea();
		res.setBounds(10, 21, 394, 86);
		res.setEditable(false);
		panRes.add(res);

		JButton autorise = new JButton(
				"\u0410\u0432\u0442\u043E\u0440\u0438\u0437\u043E\u0432\u0430\u0442\u044C\u0441\u044F");
		autorise.setBounds(10, 287, 194, 23);
		frame.getContentPane().add(autorise);
		autorise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				int temp = 0;
				res.setText("Идет обработка");
				if (counter > 0){
					if (!(isEmpty(cardNum))) {
						temp++;
					}
				if (!(isEmpty(pinCode))) {
					temp++;
				}
				if (!(isEmpty(sum))) {
					temp++;
				}
				if (temp != 3)
					res.setText("Заполните обязательные поля");
				else if (temp == 3) {
					// проверка на наличие букв
					if (!(checkSymbols(cardNum)) && (!(checkSymbols(pinCode))) && (!(checkSymbols(sum)))) {

						if (checkNum(cardNum) && checkPin(pinCode) && validation()) {
							if (correctPin(pinCode.getText())) {
								currency = (String) val.getSelectedItem();
								transaction();
								printCheck(res);
							} else {
								counter--;
								res.setText("PIN-код введен неверно");
								if(counter == 0)
									res.setText("PIN-код введен неверно 3 раза. Ваша карта заблокирована");
							}
						}}
					}
				}
				if(counter == 0)
					res.setText("PIN-код введен неверно 3 раза. Ваша карта заблокирована");
			}
		});

		JButton clear = new JButton("\u041E\u0447\u0438\u0441\u0442\u0438\u0442\u044C");
		clear.setBounds(223, 287, 201, 23);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				cardNum.setText("");
				pinCode.setText("");
				sum.setText("");
				res.setText("");
			}
		});

		frame.getContentPane().add(clear);
	}

	private boolean isEmpty(JTextField a) {
		if (a.getText().equals("")) {
			a.setText("Обязательное поле для заполнения");
			return true;
		} else
			return false;
	}

	private boolean checkNum(JTextField a) { // true - длина совпадает, false -
												// не совпадает
		if (a.getText().length() == 16)
			return true;
		else {
			if (a.getText().length() > 16)
				a.setText("Слишком длинный номер карты");
			if (a.getText().length() < 16)
				a.setText("Слишком короткий номер карты");
			return false;
		}
	}

	private boolean checkPin(JTextField a) {// true - длина совпадает, false -
											// не совпадает
		if (a.getText().length() == 4)
			return true;
		else {
			if (a.getText().length() > 4)
				a.setText("Слишком длинный PIN-код");
			if (a.getText().length() < 4)
				a.setText("Слишком короткий PIN-код");
			return false;
		}
	}

	private boolean correctPin(String a) {// делает Ксюша
		return false;
	}

	private boolean validation() {// Ksusha
		return true;
	}

	private boolean transaction() {// Ksusha
		return true;
	}

	public int getCardNum() {
		return Integer.parseInt(cardNum.getText(), 10);
	}

	public int getPin() {
		return Integer.parseInt(pinCode.getText(), 10);
	}

	public int getSum() {
		return Integer.parseInt(sum.getText(), 10);
	}

	private boolean checkSymbols(JTextField a) {// true - есть символы, false -
												// нет символов
		if (a.getText().matches("(?i).*[a-zа-я].*")) {
			a.setText("Ошибка! Содержатся недопустимые символы");
			return true;
		} else
			return false;
	}

	private void printCheck(JTextArea a) {
		if (transaction())
			a.setText("Ваша транзакция на сумму " + sum.getText() + " " + val.getSelectedIndex()
					+ " выполнена успешно\n");
	}
	public String getCurrency(){
		return currency;
	}
}