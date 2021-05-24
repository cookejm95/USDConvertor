import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.*;

import org.json.JSONObject;

public class Convertor {

	public Convertor() {

		JFrame frame = new JFrame("James' Currency Convertor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		JLabel title = new JLabel("James' Currency Convertor");
		frame.add(title);
		JPanel panel = new JPanel();
		JLabel usd = new JLabel("Enter USD Amount:");
		JTextField amount = new JTextField(10);
		JLabel currency = new JLabel("Choose Currency:");
		String[] options = new String[] { "GBP", "AUD", "CAD", "INR", "EUR" };
		JComboBox<String> dropdown = new JComboBox<String>(options);
		JButton convert = new JButton("CONVERT");
		JLabel result = new JLabel("Result:");
		JTextField resultAmount = new JTextField(10);
		JLabel rate = new JLabel("Current Rate: -");
		panel.add(usd);
		panel.add(amount);
		panel.add(currency);
		panel.add(dropdown);
		panel.add(convert);
		panel.add(result);
		panel.add(resultAmount);
		panel.add(rate);
		frame.add(panel, BorderLayout.CENTER);
		convert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String) dropdown.getSelectedItem();
				try {
					double dollarAmount = Double.parseDouble(amount.getText());

					switch (s) {
					case "EUR":
						try {
							double conversion = dollarAmount * sentHttpGetRequest("EUR");
							conversion = conversion * 100;
							conversion = Math.round(conversion);
							conversion = conversion / 100;
							resultAmount.setText(conversion + "");
							rate.setText("Current Rate: " + sentHttpGetRequest("EUR"));
						} catch (IOException e1) {

							e1.printStackTrace();
						}

						break;
					case "AUD":
						try {
							double conversion = dollarAmount * sentHttpGetRequest("AUD");
							conversion = conversion * 100;
							conversion = Math.round(conversion);
							conversion = conversion / 100;
							resultAmount.setText(conversion + "");
							rate.setText("Current Rate: " + sentHttpGetRequest("AUD"));
						} catch (IOException e1) {

							e1.printStackTrace();
						}
						break;
					case "CAD":
						try {
							double conversion = dollarAmount * sentHttpGetRequest("CAD");
							conversion = conversion * 100;
							conversion = Math.round(conversion);
							conversion = conversion / 100;
							resultAmount.setText(conversion + "");
							rate.setText("Current Rate: " + sentHttpGetRequest("CAD"));
						} catch (IOException e1) {

							e1.printStackTrace();
						}
						break;
					case "GBP":
						try {
							double conversion = dollarAmount * sentHttpGetRequest("GBP");
							conversion = conversion * 100;
							conversion = Math.round(conversion);
							conversion = conversion / 100;
							resultAmount.setText(conversion + "");
							rate.setText("Current Rate: " + sentHttpGetRequest("GBP"));
						} catch (IOException e1) {

							e1.printStackTrace();
						}
						break;
					case "INR":
						try {
							double conversion = dollarAmount * sentHttpGetRequest("INR");
							conversion = conversion * 100;
							conversion = Math.round(conversion);
							conversion = conversion / 100;
							resultAmount.setText(conversion + "");
							rate.setText("Current Rate: " + sentHttpGetRequest("INR"));
						} catch (IOException e1) {

							e1.printStackTrace();
						}
						break;
					default:
						resultAmount.setText("something wrong");
						break;
					}
				} catch (NumberFormatException ex) {
					resultAmount.setText("Invalid Amount.");
				}
			}
		});
		frame.setVisible(true);

	}

	public double sentHttpGetRequest(String symbol) throws IOException {

		URL site = new URL("https://api.exchangerate.host/latest?base=USD&symbols=" + symbol);
		HttpURLConnection request = (HttpURLConnection) site.openConnection();
		request.connect();
		// request.setRequestMethod("GET");
		int responseCode = request.getResponseCode();
		double exchangeRate = 0;

		if (responseCode == request.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JSONObject obj = new JSONObject(response.toString());
			exchangeRate = obj.getJSONObject("rates").getDouble(symbol);

		}
		return exchangeRate;

	}

	public static void main(String[] args) throws IOException {
		Convertor convertor = new Convertor();
	}

}
