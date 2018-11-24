package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class forgetcontroller {
    @FXML
    private Label label;
    @FXML
    private TextField ID;
    @FXML
    public void check(ActionEvent event) throws Exception {
        Main.id = ID.getText();
        String token = "", password = "";
        URL url;
        HttpURLConnection conn;
        OutputStream os;
        InputStream in;
        if (Main.id.equals(""))
            label.setText("USER ID NOT ENTERED");
        else {
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

                Parent root = FXMLLoader.load(getClass().getResource("/application/pass.fxml"));
                Scene scene = new Scene(root, 400, 400);
                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                Main.pm.setTitle("Search Member");
                Main.pm.setWidth(650);
                Main.pm.setHeight(458);
                Main.pm.setMaxWidth(650);
                Main.pm.setMaxHeight(458);
                Main.pm.setScene(scene);
                Main.pm.show();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                label.setText("ID NOT FOUND");
            }
        }
    }
    @FXML
    public  void back(ActionEvent event)
    {
        Main.pm.setTitle("Library Assistant Menu");
        Main.pm.setWidth(649.0);
        Main.pm.setHeight(448.0);
        Main.pm.setScene(Main.scenel);
        Main.pm.show();
    }
}
