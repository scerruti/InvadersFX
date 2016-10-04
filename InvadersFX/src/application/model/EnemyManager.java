package application.model;

import java.util.Timer;
import java.util.TimerTask;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class EnemyManager extends Service<Void> {
	
	public EnemyManager() {
	}

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				Timer enemySpawner = new Timer();
				enemySpawner.schedule(
					    new TimerTask() {

					        @Override
					        public void run() {
					        	new Enemy(0,0);
					        }
					    }, 0, 1000);
				return null;
			}
			
		};

	}

}