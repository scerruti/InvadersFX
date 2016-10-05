package application.model;

import application.InvadersFX;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public abstract class TimeMotionObject extends GameObject {
	private ChangeListener<Number> timeChangeListener = new ChangeListener<Number>() {

		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			move(newValue.longValue() - oldValue.longValue());
		}
	};

	public TimeMotionObject(double x, double y) {
		super(x, y);

		InvadersFX.registerForTimeUpdates(timeChangeListener);
	}

	@Override
	public void kill() {
		super.kill();
		InvadersFX.unregisterForTimeUpdates(timeChangeListener);
	}

	public abstract void move(long duration);
}
