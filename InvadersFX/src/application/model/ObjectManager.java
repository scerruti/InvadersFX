package application.model;

import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<GameObject> objects;

	long enemyTimer = 0;
	int enemySpawnTime = 1000;

	public ObjectManager() {
		objects = new ArrayList<GameObject>();
	}

	public void addObject(GameObject o) {
		objects.add(o);
	}

	public void update() {
		manageEnemies();
		checkCollision();
		purgeObjects();
	}

	private void purgeObjects() {
		for (int i = 0; i < objects.size(); i++) {
			if (!objects.get(i).isAlive()) {
				objects.remove(i);
			}
		}
	}

	private void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addObject(new Enemy(new Random().nextInt(100), 0));
			enemyTimer = System.currentTimeMillis();
		}
	}

	private void checkCollision() {
		// Collision detection needs to happen in the controller
	}

	public void reset() {
		objects.clear();
	}
}
