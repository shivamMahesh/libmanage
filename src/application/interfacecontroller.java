package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class interfacecontroller {

	
	public void interfere(ActionEvent event) throws Exception {
		String value = ((Button) event.getSource()).getText();
		if (value.equals("ADD ITEM")) {

			Parent root = FXMLLoader.load(getClass().getResource("/application/main.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.pm.setTitle("Add New Item");
			Main.pm.setWidth(574.0);
			Main.pm.setHeight(385.0);
			Main.pm.setMaxWidth(574.0);
			Main.pm.setMaxHeight(385.0);
			Main.pm.setScene(scene);
			Main.pm.show();

		} else if (value.equals("ADD PUBLISHER")) {
			Parent root = FXMLLoader.load(getClass().getResource("/application/member.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.pm.setTitle("Add New Publisher");
			Main.pm.setWidth(574.0);
			Main.pm.setHeight(385.0);
			Main.pm.setMaxWidth(574.0);
			Main.pm.setMaxHeight(385.0);
			Main.pm.setScene(scene);
			Main.pm.show();
		} else if (value.equals("ISSUE ITEM")) {
			Parent root = FXMLLoader.load(getClass().getResource("/application/issue.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.pm.setTitle("Issue Item");
			Main.pm.setWidth(574.0);
			Main.pm.setHeight(385.0);
			Main.pm.setMaxWidth(574.0);
			Main.pm.setMaxHeight(385.0);
			Main.pm.setScene(scene);
			Main.pm.show();

		} else if (value.equals("RETURN ITEM")) {
			Parent root = FXMLLoader.load(getClass().getResource("/application/return.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.pm.setTitle("Return Item");
			Main.pm.setWidth(574.0);
			Main.pm.setHeight(385.0);
			Main.pm.setMaxWidth(574.0);
			Main.pm.setMaxHeight(385.0);
			Main.pm.setScene(scene);
			Main.pm.show();

		} else if (value.equals("ITEMS LIST")) {

			Parent root = FXMLLoader.load(getClass().getResource("/application/bookslist.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.pm.setTitle("Items List");
			Main.pm.setWidth(574.0);
			Main.pm.setHeight(385.0);
			Main.pm.setMaxWidth(574.0);
			Main.pm.setMaxHeight(385.0);
			Main.pm.setScene(scene);
			Main.pm.show();


		} else if (value.equals("SEARCH MEMBER")) {

			Parent root = FXMLLoader.load(getClass().getResource("/application/memberlists.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.pm.setTitle("Search Member");
			Main.pm.setWidth(650);
			Main.pm.setHeight(458);
			Main.pm.setMaxWidth(650);
			Main.pm.setMaxHeight(458);
			Main.pm.setScene(scene);
			Main.pm.show();


		} else if (value.equals("SIGN OUT")) {
			Main.pm.setTitle("Library Assistant Menu");
			Main.pm.setWidth(649.0);
			Main.pm.setHeight(448.0);
			Main.pm.setScene(Main.scenel);
			Main.pm.show();
		} else if(value.equals("RESET PASSWORD")) {
            String token = "", password = "";
            URL url;
            HttpURLConnection conn;
            OutputStream os;
            InputStream in;
            String result = "", query_url = "";
            String json;
            try {
                query_url = "http://localhost:3000/mail1";
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
                Parent root = FXMLLoader.load(getClass().getResource("/application/pass.fxml"));
                Scene scene = new Scene(root, 400, 400);
                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                Main.pm.setTitle("Password");
                Main.pm.setWidth(650);
                Main.pm.setHeight(458);
                Main.pm.setMaxWidth(650);
                Main.pm.setMaxHeight(458);
                Main.pm.setScene(scene);
                Main.pm.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

		else if(value.equals("SHOW REQUEST"))
		{
			Parent root = FXMLLoader.load(getClass().getResource("/application/request.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.pm.setTitle("Show Request");
			Main.pm.setWidth(549);
			Main.pm.setHeight(348);
			Main.pm.setMaxWidth(549);
			Main.pm.setMaxHeight(348);
			Main.pm.setScene(scene);
			Main.pm.show();
		}
		else if(value.equals("SHOW PUBLISHERS"))
		{
			Parent root = FXMLLoader.load(getClass().getResource("/application/publisherlist.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.pm.setTitle("Show Publisher");
			Main.pm.setWidth(549);
			Main.pm.setHeight(348);
			Main.pm.setMaxWidth(549);
			Main.pm.setMaxHeight(348);
			Main.pm.setScene(scene);
			Main.pm.show();
		}
		else if(value.equals("SHOW PENDING"))
		{
			Parent root = FXMLLoader.load(getClass().getResource("/application/pending.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.pm.setTitle("Pending items");
			Main.pm.setWidth(549);
			Main.pm.setHeight(348);
			Main.pm.setMaxWidth(549);
			Main.pm.setMaxHeight(348);
			Main.pm.setScene(scene);
			Main.pm.show();
		}
		else
		{
			Parent root = FXMLLoader.load(getClass().getResource("/application/reserve.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.pm.setTitle("Reserve Items");
			Main.pm.setWidth(549);
			Main.pm.setHeight(348);
			Main.pm.setMaxWidth(549);
			Main.pm.setMaxHeight(348);
			Main.pm.setScene(scene);
			Main.pm.show();
		}
	}

	}