package application;
	
import application.model.Bullet;
import application.model.BulletManager;
import application.controller.Game;
import application.model.Enemy;
import application.model.EnemyManager;
import application.model.Ship;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class InvadersFX extends Application {
	private Stage primaryStage;
	private static InvadersFX instance;
	private Game gameController;
	private BulletManager bulletManager;

	public InvadersFX() {
		super();
		instance = this;
	}
	
	public static InvadersFX getInstance() {
		return instance;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		try {			
			Pane titlePane = (StackPane)FXMLLoader.load(getClass().getResource
				    ("/application/view/Title.fxml"));
//			Pane instructionPane = (Pane)FXMLLoader.load(getClass().getResource
//				    ("instruction.fxml"));
//			Pane endPane = (Pane)FXMLLoader.load(getClass().getResource
//				    ("end.fxml"));
			
			Scene titleScene = new Scene(titlePane);
			
			titleScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(titleScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showGame() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		Pane gamePane = (BorderPane)loader.load(getClass().getResource
			    ("/application/view/Game.fxml").openStream());
		gameController = (Game) loader.getController();

		Scene gameScene = new Scene(gamePane);
		gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setScene(gameScene);
		primaryStage.show();
		primaryStage.requestFocus();
		gamePane.requestFocus();
		
		EnemyManager enemyManager = new EnemyManager();
		enemyManager.start();
		
		bulletManager = new BulletManager();
		
		gameController.addShip(new Ship(0,0));
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static void registerForTimeUpdates(ChangeListener<Number> listener) {
		getInstance().gameController.registerForTimeUpdates(listener);
	}

	public static void unregisterForTimeUpdates(ChangeListener<Number> listener) {
		getInstance().gameController.unregisterForTimeUpdates(listener);		
	}
	
	public static void registerBoundsChanged(ChangeListener<Bounds> changeListener) {
		getInstance().gameController.registerBoundsChanged(changeListener);
	}

	public static void unregisterBoundsChanged(ChangeListener<Bounds> changeListener) {
		getInstance().gameController.unregisterBoundsChanged(changeListener);		
	}

	public static void addEnemy(Enemy enemy) {
		getInstance().gameController.addEnemy(enemy);
	}

	public static void addBullet(Bullet bullet) {
		getInstance().gameController.addBullet(bullet);		
	}

	public static void fire(Ship ship) {
		getInstance().bulletManager.fireFrom(ship);
	}
}
