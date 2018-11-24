package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	static Stage pm;
	static Scene scenel;
	static String id;
	@Override
	public void start(Stage primaryStage) {
		try{
			Parent root = FXMLLoader.load(getClass().getResource("/application/login1.fxml"));
			Scene scene = new Scene(root, 1000, 1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			pm = primaryStage;
			scenel = scene;
			primaryStage.setTitle("Library Assisstant Login");
			primaryStage.setWidth(649.0);
			primaryStage.setHeight(448.0);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	catch(Exception e)
	{
		System.out.println(e);
	}
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
