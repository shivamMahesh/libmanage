package application;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController  {

	@FXML
	private Label lLogin;
	@FXML
	private TextField tfUserid;
	
	@FXML
	private TextField tfPassword;

static Scene scenei;
	
	public void login(ActionEvent event) throws Exception {
		String id = tfUserid.getText();
		String ps = tfPassword.getText();
		if (id .equals(""))
			lLogin.setText("USER ID NOT ENTERED");
		else if (ps.equals(""))
			lLogin.setText("PASSWORD NOT ENTERED");
		else {
			String query_url = "http://localhost:3000/signin";
			String json = "{ \"id\" : " + "\"" + id + "\", \"password\" :\"" + ps + "\"}";

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

				System.out.println("result after Reading JSON Response");
				JSONObject myResponse = new JSONObject(result);
				int a = myResponse.getInt("id");
				Main.id = Integer.toString(a);
				Boolean active = myResponse.getBoolean("active");
				if (active.equals(false)) {
					try {
						query_url = "http://localhost:3000/mail";
						json = "{\"id\" :\"" + Main.id + "\",\"flag\" : \"2\"}";
						System.out.println(json);
						url = new URL(query_url);
						conn = (HttpURLConnection) url.openConnection();
						conn.setConnectTimeout(5000);
						conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
						conn.setDoOutput(true);
						conn.setDoInput(true);
						conn.setRequestMethod("POST");
						os = conn.getOutputStream();
						os.write(json.getBytes("UTF-8"));
						os.close();
						// read the response
						in = new BufferedInputStream(conn.getInputStream());
						result = IOUtils.toString(in, "UTF-8");
						System.out.println(result);
						Parent root = FXMLLoader.load(getClass().getResource("/application/verify.fxml"));
						Scene scene = new Scene(root, 400, 400);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						Main.pm.setTitle("VERIFY");
						Main.pm.setWidth(574.0);
						Main.pm.setHeight(385.0);
						Main.pm.setMaxWidth(574.0);
						Main.pm.setMaxHeight(385.0);
						Main.pm.setScene(scene);
						Main.pm.show();

					} catch (Exception e) {
						System.out.println(e.getMessage());
						lLogin.setText("MAIL NOT SENT");
					}
				} else {
					System.out.println(myResponse);
					in.close();
					conn.disconnect();
					query_url = "http://localhost:3000/checkreserve";
					url = new URL(query_url);
					conn = (HttpURLConnection) url.openConnection();
					try {
						conn.setRequestMethod("GET");
						in = new BufferedInputStream(conn.getInputStream());
						result = IOUtils.toString(in, "UTF-8");
						System.out.println(result);

						in.close();
						conn.disconnect();
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
					query_url = "http://localhost:3000/pending";
					url = new URL(query_url);
					conn = (HttpURLConnection) url.openConnection();
					try {
						conn.setRequestMethod("GET");
						in = new BufferedInputStream(conn.getInputStream());
						result = IOUtils.toString(in, "UTF-8");

						in.close();
						conn.disconnect();
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
					Parent root = FXMLLoader.load(getClass().getResource("/application/interface.fxml"));
					Scene scene = new Scene(root, 400, 400);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					scenei = scene;
					Main.pm.setTitle("Library Assistant Menu");
					Main.pm.setWidth(649.0);
					Main.pm.setHeight(448.0);
					Main.pm.setScene(scene);
					Main.pm.show();
				}
			} catch (Exception e) {
				lLogin.setText("WRONG CREDENTIALS!");
				System.out.println(e);
			}
		}
	}
@FXML
	public void signup(ActionEvent event)
{
	try {
		Parent root = FXMLLoader.load(getClass().getResource("/application/assistant.fxml"));
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Main.pm.setTitle("Libarary Assistant Signup");
		Main.pm.setWidth(570);
		Main.pm.setHeight(395);
		Main.pm.setScene(scene);
		Main.pm.show();
	} catch (Exception e)

	{
		System.out.println(e.getMessage());
	}
}
	@FXML
	public void forget(ActionEvent event)
	{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/forget.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.pm.setTitle("Libarary Assistant Signup");
			Main.pm.setWidth(570);
			Main.pm.setHeight(395);
			Main.pm.setScene(scene);
			Main.pm.show();
		} catch (Exception e)

		{
			System.out.println(e.getMessage());
		}
	}
}
