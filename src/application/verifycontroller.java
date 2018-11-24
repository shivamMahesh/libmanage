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

public class verifycontroller  {

    @FXML
    private Label status;
    @FXML
    private TextField token;

    static Scene scenei;

    public void check(ActionEvent event) throws Exception
    {
        String tok=token.getText();


        String query_url = "http://localhost:3000/verify";
        String json = "{ \"id\" : "+"\""+Main.id+"\", \"token\" :\""+tok+"\"}";

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

            in.close();
            conn.disconnect();
            status.setText("verified");
            Parent root = FXMLLoader.load(getClass().getResource("/application/interface.fxml"));
            Scene scene = new Scene(root,400,400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            scenei=scene;
            Main.pm.setTitle("Library Assistant Menu");
            Main.pm.setWidth(649.0);
            Main.pm.setHeight(448.0);
            Main.pm.setScene(scene);
            Main.pm.show();
        } catch (Exception e) {

            System.out.println(e);
            status.setText("not verified");
        }
    }
    @FXML
    public void back(ActionEvent event)
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/application/login1.fxml"));
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
