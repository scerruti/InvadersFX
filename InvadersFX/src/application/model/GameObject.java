package application.model;

import application.InvadersFX;
import application.controller.Game;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class GameObject {
	protected BooleanProperty alive = new SimpleBooleanProperty();

	protected DoubleProperty x;
	protected DoubleProperty y;
	
	protected String view;

	private ChangeListener<Bounds> boundsChangeListener = new ChangeListener<Bounds>() {
		@Override
		public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
			reposition(oldValue, newValue);
		}
	};

	public GameObject(double x, double y) {
		alive.set(true);

		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);

		InvadersFX.registerBoundsChanged(boundsChangeListener);
	}

	public String getView() {
		return view;
	}

	protected void reposition(Bounds oldValue, Bounds newValue) {
		x.set(x.get() / oldValue.getWidth() * newValue.getWidth());
		y.set(y.get() / oldValue.getHeight() * newValue.getHeight());
	}

	public BooleanProperty getAlive() {
		return alive;
	}

	public boolean isAlive() {
		return alive.get();
	}

	public void kill() {
		this.alive.set(false);
		InvadersFX.unregisterBoundsChanged(boundsChangeListener);
	}

	public DoubleProperty xProperty() {
		return x;
	}

	public DoubleProperty yProperty() {
		return y;
	}

	public double getX() {
		return this.x.doubleValue();
	}

	public void setX(double x) {
		this.x.set(x);
	}

	public double getY() {
		return this.y.doubleValue();
	}

	public void setY(double y) {
		this.y.set(y);
	}

	public void addToGameController(Game game) {
		game.addGenericObject(this);
	}

	public abstract Node getControllerObject(Pane game);


}
