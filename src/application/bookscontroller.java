package application;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.io.IOUtils;

public class bookscontroller{
	@FXML
	private Label itemlabel;
	@FXML
	private TextField ti;
	@FXML
	private TextField ty;
	@FXML
	private TextField a;
	@FXML
	private TextField p;
	@FXML
	private TextField e;
	@FXML
	private TextField pr;
	@FXML
	public void check(ActionEvent event) {
		String title = ti.getText();
		String type = ty.getText();
		String author = a.getText();
		String pub_id = p.getText();
		String edition = e.getText();
		String price = pr.getText();
		if (title.equals(""))
			itemlabel.setText("TITLE NOT ENTERED");
		else if (type.equals(""))
			itemlabel.setText("TYPE NOT ENTERED");
		else if (author.equals(""))
			itemlabel.setText("AUTHOR NOT ENTERED");
		else if (pub_id.equals(""))
			itemlabel.setText("PUBLISHER NOT ENTERED");
		else if (edition.equals(""))
			itemlabel.setText("EDITION NOT ENTERED");
		else if (price.equals(""))
			itemlabel.setText("PRICE NOT ENTERED");
		else {
			String result = "PUBLISHER NOT FOUND";
			String query_url = "http://localhost:3000/additem";
			String json = "{" +
					"\"title\" : \"" + title +
					"\" ,\"author\" : \"" + author +
					"\", \"type\" :\"" + type +
					"\", \"price\" :\"" + price +
					"\", \"edition\" :\"" + edition +
					"\", \"pub_id\" :\"" + pub_id +
					"\"}";

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
				result = IOUtils.toString(in, "UTF-8");

				itemlabel.setText("ADDED SUCCESSFULLY\n ID:" + result);
				in.close();
				conn.disconnect();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				itemlabel.setText(result);
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
