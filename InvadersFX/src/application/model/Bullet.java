package application.model;

import application.InvadersFX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Bullet extends GameObject {
	
	public Bullet(double x, double y) {
		super(x, y);
		
		InvadersFX.registerForTimeUpdates(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				move(newValue.longValue() - oldValue.longValue());
			}
		});
	}	
	
	public void move(long duration) {
		double distance = (duration / 1000000);
		double temporaryY = y.getValue() - distance;
		if (temporaryY < 0.0) {
			temporaryY = 0.0;
			kill();
		}
		y.setValue(temporaryY);
	}
}
