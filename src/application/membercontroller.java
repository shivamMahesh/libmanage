package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class membercontroller{
	@FXML
	private Label label;
	@FXML
	private TextField e;
	@FXML
	private TextField n;
	@FXML
	private TextField p;
	@FXML
	private TextField a;
	
	@FXML
	public void check(ActionEvent event) {
		String email = e.getText();
		String name = n.getText();
		String phone = p.getText();
		String address = a.getText();
		if (email.equals(""))
			label.setText("EMAIL NOT ENTERED");
		else if (name.equals(""))
			label.setText("NAME NOT ENTERED");
		else if (phone.equals(""))
			label.setText("PHONE NOT ENTERED");
		else if (address.equals(""))
			label.setText("ADDRESS NOT ENTERED");
		else {
			String query_url = "http://localhost:3000/addpublisher";
			String json = "{\"name\" : \"" + name + "\" ,\"email\" : " + "\"" + email + "\",\"address\":\"" + address + "\",\"phone\":\"" + phone + "\"}";

			try {
				URL url = new URL(query_url);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setRequestMethod("POST");
				OutputStream os = conn.getOutputStream();
				os.write(json.getBytes("UTF-8"));
				os.close();
				// read the response
				InputStream in = new BufferedInputStream(conn.getInputStream());
				String result = IOUtils.toString(in, "UTF-8");

				label.setText("REGISTERED SUCCESSFULLY\n ID " + result);
				Main.id = result;
				in.close();
				conn.disconnect();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				label.setText("NOT ADDED");
			}
		}
	}
	@FXML
	public  void back(ActionEvent event)
	{
		Main.pm.setTitle("Library Assistant Menu");
		Main.pm.setWidth(649.0);
		Main.pm.setHeight(448.0);
		Main.pm.setScene(MainController.scenei);
		Main.pm.show();
	}
}