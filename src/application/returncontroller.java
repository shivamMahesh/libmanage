package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class returncontroller {
	@FXML
	private Label returnlabel;
	@FXML
	private TextField itemid;
	@FXML
	private TextField memberid;
	@FXML
	private Label showfine;

	@FXML
	public void check(ActionEvent event) throws  Exception {
		String query_url = "http://localhost:3000/return";
		URL url = new URL(query_url);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String item_id = itemid.getText();
		String mem_id = memberid.getText();
		if (mem_id.equals(""))
			returnlabel.setText("MEMBER ID NOT ENTERED");
		else if (item_id.equals(""))
			returnlabel.setText("ITEM ID NOT ENTERED");
		else {
			String result = "ITEM CAN NOT BE RETURNED", label;
			String json = "{\"mem_id\" : \"" + mem_id + "\" ,\"item_id\" : " + "\"" + item_id + "\"}";

			try {
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

				returnlabel.setText("RETURN SUCCESSFULLY");
				showfine.setText(result);
				in.close();
				conn.disconnect();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				InputStream in = new BufferedInputStream(conn.getErrorStream());
				result = IOUtils.toString(in, "UTF-8");
				System.out.println(result);
				returnlabel.setText(result);
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