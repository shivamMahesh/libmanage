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



public class membersearch {
    @FXML
    private TextField memberid1;
    @FXML
    private TextField membername1;
    @FXML
    private Label showmember1;
    @FXML
    private Label showbooks1;

    @FXML
    public void search(ActionEvent event)throws Exception {
        String result="",label="";
        String query_url = "http://localhost:3000/showmember";
        URL url = new URL(query_url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        String id = "", mn = "", json = "";
        id = memberid1.getText();
        mn = membername1.getText();


        try {
            if (!id.equals("")) {
                json = "{\"id\" : \"" + id + "\",\"flag\":\"1\"}";
            } else if (!mn.equals("")) {
                json = "{\"name\" : \"" + mn + "\",\"flag\":\"2\"}";
            } else {
                throw new Exception("ENTER PROPERLY");
            }


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

            JSONObject myResponse = new JSONObject(result);
            id=Integer.toString(myResponse.getInt("id"));
            label = "id:" + id + "\n";
            label += "name:" + myResponse.getString("name") + "\n";
            label += "email:" + myResponse.getString("email") + "\n";
            label += "address:" + myResponse.getString("address") + "\n";
            label += "phone:" + myResponse.getString("phone") + "\n";
            showmember1.setText(label);
            in.close();
            conn.disconnect();

            label="";
            query_url = "http://localhost:3000/showissue";
            url = new URL(query_url);
            conn = (HttpURLConnection) url.openConnection();

            try{
                    json = "{\"id\" : \"" + id + "\"}";


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

                JSONArray js=new JSONArray(result);
                label="";
                for(int i=0;i<js.length();i++)
                {
                    JSONObject jsonObject = js.getJSONObject(i);


                    label += "id:" + Integer.toString(jsonObject.getInt("item_id")) + "\n";
                    label += "author:" + jsonObject.getString("author") + "\n";
                    label+="issue:"+jsonObject.getString("issue_date")+"\n";
                    label+="return:"+jsonObject.get("return_date")+"\n\n";
                }

                showbooks1.setText(label);
                in.close();
                conn.disconnect();



            } catch (Exception ex) {
                in = new BufferedInputStream(conn.getErrorStream());
                result = IOUtils.toString(in, "UTF-8");
                showbooks1.setText(result);
            }


        } catch (Exception ex) {
            InputStream in = new BufferedInputStream(conn.getErrorStream());
            result = IOUtils.toString(in, "UTF-8");
            showmember1.setText(result);
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
