package application;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.scene.control.TextField;
import org.apache.commons.io.IOUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class pascontroller{
    @FXML
    private Label label;
    @FXML
    private PasswordField pass;
    @FXML
    private  PasswordField pass1;
    @FXML
    private TextField tok;
    @FXML
    public void check(ActionEvent event) throws Exception {
        String token="",password="",json;
        URL url ;
        HttpURLConnection conn ;
        OutputStream os ;
        InputStream in ;
        String result="",query_url="";
        token=tok.getText();
        password = pass.getText();
        if(password.equals(""))
            label.setText("PASSWORD NOT ENTERED");
        else if(pass1.getText().equals(""))
            label.setText("CONFIRM PASSWORD NOT ENTERED");
        else if(token.equals(""))
            label.setText("TOKEN NOT ENTERED");
        else if (!password.equals(pass1.getText())) {
            label.setText("PASSWORD DOESN'T MATCH");
        } else {

                try {
                    query_url = "http://localhost:3000/verify";
                    json = "{\"id\" : \"" + Main.id + "\" ,\"token\" : " + "\"" +token + "\", \"flag\" :\"2\"}";
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

                    in.close();
                    conn.disconnect();


                    try {
                        query_url = "http://localhost:3000/pass";
                        json = "{\"id\" : \"" + Main.id + "\" ,\"password\" : " + "\"" + password + "\", \"flag\" :\"2\"}";
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

                        in.close();
                        conn.disconnect();
                        label.setText("PASSWORD CHANGED SUCCESSFULLY");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        label.setText("PASSWORD NOT CHANGED ");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    label.setText("PASSWORD NOT CHANGED ");
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
