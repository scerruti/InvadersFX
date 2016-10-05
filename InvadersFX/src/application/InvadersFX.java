package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.controller.Collider;
import application.controller.Game;
import application.model.EnemyManager;
import application.model.GameObject;
import application.model.Ship;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class InvadersFX extends Application {
	private Stage primaryStage;
	private static InvadersFX instance;
	private Game gameController;
	private EnemyManager enemyManager;
	private IntegerProperty score = new SimpleIntegerProperty(0);

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
			URL location = getClass().getResource("/application/view/Title.fxml");
			ResourceBundle resources = ResourceBundle.getBundle("application/resources/Invaders");
			FXMLLoader loader = new FXMLLoader(location, resources);
			Pane titlePane = (StackPane) loader.load();
			// Pane instructionPane =
			// (Pane)FXMLLoader.load(getClass().getResource
			// ("instruction.fxml"));
			// Pane endPane = (Pane)FXMLLoader.load(getClass().getResource
			// ("end.fxml"));

			Scene titleScene = new Scene(titlePane);

			titleScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(titleScene);
			primaryStage.show();
			titlePane.requestFocus();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showGame() throws Exception {
		score.set(0);
		
		FXMLLoader loader = new FXMLLoader();
		Pane gamePane = (BorderPane) loader.load(getClass().getResource("/application/view/Game.fxml").openStream());
		gameController = (Game) loader.getController();

		Scene gameScene = new Scene(gamePane);
		gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setScene(gameScene);
		primaryStage.show();
		gamePane.requestFocus();

		enemyManager = new EnemyManager();
		enemyManager.start();

		gameController.addShip(new Ship(0, 0));
	}
	
	public void endGame() throws Exception {
		enemyManager.stop();
		
		FXMLLoader loader = new FXMLLoader();
		Pane gameOverPane = (StackPane) loader.load(getClass().getResource("/application/view/GameOver.fxml").openStream());

		Scene gameOverScene = new Scene(gameOverPane);
		gameOverScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		primaryStage.setScene(gameOverScene);
		primaryStage.show();
		gameOverPane.requestFocus();
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

	public static void addGameObject(GameObject object) {
		getInstance().gameController.add(object);
	}

	public static void removeGameNode(Node node) {
		getInstance().gameController.remove(node);
	}

	public static void checkForCollisions(Collider node) {
		getInstance().gameController.collision(node);
	}

	public static void enemyKilled() {
		getInstance().incrementScore();
	}

	private void incrementScore() {
		score.set(score.intValue()+1);
		System.out.println(score.intValue());
	}
	
	public static IntegerProperty getScoreProperty() {
		return getInstance().score;
	}

	public static void gameOver() {
		try {
			getInstance().endGame();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
