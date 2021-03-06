package application.model;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class EnemyManager extends Service<Void> {
	private HashSet<Enemy> enemies = new HashSet<>();
	final private Timer enemySpawner  = new Timer();

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				enemySpawner.schedule(new TimerTask() {

					@Override
					public void run() {
						enemies.add(new Enemy(0, 0));
					}
				}, 0, 1000);
				return null;
			}

		};

	}

	public void stop() {
		enemySpawner.cancel();
		for (Enemy e : enemies) {
			e.kill();
		}
 		enemies.clear();
	}
}
