package application.model;

import application.InvadersFX;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;

public class GameObject {
	protected BooleanProperty alive = new SimpleBooleanProperty();
	
	protected DoubleProperty x;
	protected DoubleProperty y;
	
	public GameObject(double x, double y){
		alive.set(true);
		
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		
		InvadersFX.registerBoundsChanged(new ChangeListener<Bounds>() {

			@Override
			public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
				reposition(oldValue, newValue);
			}

		});
	}
	
	protected void reposition(Bounds oldValue, Bounds newValue) {
		x.set(x.get()/oldValue.getWidth()*newValue.getWidth());
		y.set(y.get()/oldValue.getHeight()*newValue.getHeight());
	}

	
	public BooleanProperty getAlive() {
		return alive;
	}

	public boolean isAlive() {
		return alive.get();
	}

	public void kill() {
		this.alive.set(false);
	}

	public DoubleProperty xProperty() {
		return x;
	}

	public DoubleProperty yProperty() {
		return y;
	}

	public void setX(double x) {
		this.x.set(x);
	}
	
	public void setY(double y) {
		this.y.set(y);
	}
	
}
