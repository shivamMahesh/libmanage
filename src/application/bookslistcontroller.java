package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class bookslistcontroller {
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
    private Label l6;
    @FXML
    private Label l7;
    @FXML
    private TextField sf1;
    @FXML
    private TextField sf2;
    @FXML
    public void check(ActionEvent event) throws Exception{
        String query_url = "http://localhost:3000/showitem";
        URL url = new URL(query_url);
        String st=sf1.getText(),sa=sf2.getText();
        String id="",ti="",a="",ty="",e="",q="",pub="";
        String s1,s2,s3,s4,s5,s6,s7;
        s1=s2=s3=s4=s5=s6=s7="";
        int flag;
        if(st.equals("") && sa.equals(""))
            flag=0;
        else if(!st.equals("") && sa.equals(""))
            flag=1;
        else if(st.equals("") && !sa.equals(""))
            flag=2;
        else
            flag=3;
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try {
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");

            JSONArray json=new JSONArray(result);
            int flag1;
            for(int i=0;i<json.length();i++) {
                JSONObject jsonObject = json.getJSONObject(i);
                System.out.println(jsonObject);
                id = Integer.toString(jsonObject.getInt("id"));
                ti = jsonObject.getString("title");
                a = jsonObject.getString("author");
                ty = jsonObject.getString("type");
                e = jsonObject.getString("edition");
                q = Integer.toString(jsonObject.getInt("qty"));
                pub = Integer.toString(jsonObject.getInt("pub_id"));
                if (flag == 0)
                    flag1 = 1;
                else if (flag == 1 && ti.toLowerCase().contains(st.toLowerCase()))
                    flag1 = 1;
                else if (flag == 2 && a.toLowerCase().contains(sa.toLowerCase()))
                    flag1 = 1;
                else if (flag == 3 && ti.toLowerCase().contains(st.toLowerCase()) && a.toLowerCase().contains(sa.toLowerCase()))
                    flag1 = 1;
                else flag1 = 0;
                if (flag1 == 1) {
                    s1 = s1 + id + "\n";
                    s2 += ti + "\n";
                    s3 += a + "\n";
                    s4 += pub + "\n";
                    s5 += ty + "\n";
                    s6 += q + "\n";
                    s7 += e + "\n";
                }
            }
            l1.setText(s1);
            l2.setText(s2);
            l3.setText(s3);
            l4.setText(s4);
            l5.setText(s5);
            l6.setText(s6);
            l7.setText(s7);
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