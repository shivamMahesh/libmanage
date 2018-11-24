package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class reserveController {
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    public void check(ActionEvent event) throws Exception{
        String query_url;
        URL url;
        String id1="",id2="",s="",e="";
        String s1,s2,s3,s4;
        s1=s2=s3=s4="";
        query_url = "http://localhost:3000/checkreserve";
        url = new URL(query_url);
        HttpURLConnection conn;
        conn = (HttpURLConnection) url.openConnection();
        InputStream in;
        String result;
        try {
            conn.setRequestMethod("GET");
            in = new BufferedInputStream(conn.getInputStream());
            result = IOUtils.toString(in, "UTF-8");


            in.close();
            conn.disconnect();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        query_url = "http://localhost:3000/showreserve";
        url = new URL(query_url);
        conn = (HttpURLConnection) url.openConnection();
        try {
            conn.setRequestMethod("GET");
             in = new BufferedInputStream(conn.getInputStream());
             result = IOUtils.toString(in, "UTF-8");

            JSONArray json=new JSONArray(result);

            for(int i=0;i<json.length();i++)
            {
                JSONObject jsonObject = json.getJSONObject(i);
                System.out.println(jsonObject);
                id1=Integer.toString(jsonObject.getInt("mem_id"));
                id2=Integer.toString(jsonObject.getInt("item_id"));
                s=jsonObject.getString("start_date");
                e=jsonObject.getString("end_date");
                s1+=id1+"\n";
                s2+=id2+"\n";
                s3+=s+"\n";
                s4+=e+"\n";
            }
            l1.setText(s1);
            l2.setText(s2);
            l3.setText(s3);
            l4.setText(s4);
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