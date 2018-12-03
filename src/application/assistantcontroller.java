package application;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.apache.commons.io.IOUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class assistantcontroller{
    @FXML
    private Label aslabel;
    @FXML
    private TextField as_email;
    @FXML
    private TextField as_name;
    @FXML
    private TextField as_phno;
    @FXML
    private  TextField as_address;
    @FXML
    private PasswordField as_pass;
    @FXML
    private  PasswordField as_pass1;

    @FXML
    public void check(ActionEvent event) {
        String query_url = "http://localhost:3000/register";
        String name, email, address, phone, password;
        email = as_email.getText();
        name = as_name.getText();
        phone = as_phno.getText();
        address = as_address.getText();
        password = as_pass.getText();
        Boolean flag;
        if (email.equals(""))
            aslabel.setText("EMAIL NOT ENTERED");
        else if (name.equals(""))
            aslabel.setText("NAME NOT ENTERED");
        else if (phone.equals(""))
            aslabel.setText("PHONE NOT ENTERED");
        else if (address.equals(""))
            aslabel.setText("ADDRESS NOT ENTERED");
        else if (password.equals(""))
            aslabel.setText("PASSWORD NOT ENTERED");
        else if (as_pass1.getText().equals(""))
            aslabel.setText("CONFIRM PASSWORD NOT ENTERED");
        else if (!password.equals(as_pass1.getText())) {
            aslabel.setText("PASSWORD DOES'NT MATCH");
        } else if (phone.length() != 10) {
            flag = false;
            aslabel.setText("PHONE NUMBER SHOULD\nBE OF 10 DIGITS");
        } else {
            flag = true;
            System.out.println(phone);
            try {
                long a = Long.parseLong(phone);
            } catch (Exception e) {
                flag = false;
                aslabel.setText("PHONE NUMBER SHOULD BE\n INTEGER");
            }

            if (flag) {
                String json = "{\"name\" : \"" + name + "\" ,\"email\" : " + "\"" + email + "\", \"password\" :\"" + password + "\",\"address\":\"" + address + "\",\"phone\":\"" + phone + "\",\"flag\":\"2\"}";

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

                    aslabel.setText("REGISTERED SUCCESSFULLY WITH\n ID " + result + " \n NOW VERIFY YOU MAIL");
                    Main.id = result;
                    in.close();
                    conn.disconnect();
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
                        aslabel.setText("MAIL NOT FOUND");
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    aslabel.setText("NOT REGISTERED");
                }

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
