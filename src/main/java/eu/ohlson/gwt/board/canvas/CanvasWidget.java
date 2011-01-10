package eu.ohlson.gwt.board.canvas;

import com.google.gwt.user.client.ui.Widget;

public class CanvasWidget extends Widget {

	private Canvas canvas;
	
	public CanvasWidget() {
		canvas = new Canvas(200,200);
		canvas.fillRect(20, 20, 30, 30);
	}
	
}
