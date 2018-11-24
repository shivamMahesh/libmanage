package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class publisherlist {
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private Label l5;
    @FXML
    public void check(ActionEvent event) throws Exception{
        String query_url = "http://localhost:3000/showpublisher";
        URL url = new URL(query_url);
        String id="",n="",a="",p="",e="";
        String s1,s2,s3,s4,s5;
        s1=s2=s3=s4=s5="";
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try {
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            System.out.println(result);
            JSONArray json=new JSONArray(result);

            for(int i=0;i<json.length();i++)
            {
                JSONObject jsonObject = json.getJSONObject(i);

                id=Integer.toString(jsonObject.getInt("id"));
                n=jsonObject.getString("name");
                a=jsonObject.getString("address");
                p=jsonObject.getString("phone");
                e=jsonObject.getString("email");
                s1=s1+id+"\n";
                s2+=n+"\n";
                s3+=a+"\n";
                s4+=p+"\n";
                s5+=e+"\n";
            }
            l1.setText(s1);
            l2.setText(s2);
            l3.setText(s3);
            l4.setText(s4);
            l5.setText(s5);
            in.close();
            conn.disconnect();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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