package application;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class requestController {
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
        String query_url = "http://localhost:3000/showrequest";
        URL url = new URL(query_url);
        String id="",ti="",a="",d="";
        String s1,s2,s3,s4="";
        s1=s2=s3="";
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try {
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");

            JSONArray json=new JSONArray(result);

            for(int i=0;i<json.length();i++)
            {
                JSONObject jsonObject = json.getJSONObject(i);

                id=Integer.toString(jsonObject.getInt("mem_id"));
                ti=jsonObject.getString("title");
                a=jsonObject.getString("author");
                d=jsonObject.getString("req_date");
                s1=s1+id+"\n";
                s2+=ti+"\n";
                s3+=a+"\n";
                s4+=d+"\n";

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
    public void generate(ActionEvent event) throws Exception{
            String file = "C:\\Users\\SHIVAM MAHESHWARI\\Desktop\\request.pdf";
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(file));
            Document doc = new Document(pdfDoc);
            Table table = new Table(4);
        table.addCell("MEMBER ID     ");
        table.addCell("TITLE         ");
        table.addCell("AUTHOR        ");
        table.addCell("REQUEST DATE  ");
        String query_url = "http://localhost:3000/showrequest";
        URL url = new URL(query_url);
        String id="",ti="",a="",d="";
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        try {
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            System.out.println(result);
            JSONArray json=new JSONArray(result);
            System.out.println(json);
            System.out.println(json.length());
            for(int i=0;i<json.length();i++)
            {
                JSONObject jsonObject = json.getJSONObject(i);
                System.out.println(jsonObject);
                id=Integer.toString(jsonObject.getInt("mem_id"));
                ti=jsonObject.getString("title");
                a=jsonObject.getString("author");
                d=jsonObject.getString("req_date");
                table.addCell(id+"    ");
                table.addCell(ti+"         ");
                table.addCell(a+"        ");
                table.addCell(d+"  ");

            }
            in.close();
            conn.disconnect();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            doc.add(new Paragraph("      "+"    LIST OF REQUESTS MADE"+"      "+ "    TIME   "+dateFormat.format(date)));
            doc.add(table);
            doc.close();
            System.out.println("Table created successfully..");
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