package application.controller;

import javafx.scene.Node;

public interface Collider {

	boolean collidesWith(Node otherNode);

	void impact();

}
