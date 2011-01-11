package eu.ohlson.gwt.board.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import eu.ohlson.gwt.board.client.canvas.CanvasWidget;

public class Main implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel.get().add(new CanvasWidget());
	}
	
	

}
