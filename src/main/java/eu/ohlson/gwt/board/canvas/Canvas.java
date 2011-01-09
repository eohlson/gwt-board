package eu.ohlson.gwt.board.canvas;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

public class Canvas {

	public static final String tag = "canvas";

	private final Element root;

	private final JavaScriptObject drawContext;

	public Canvas(int width, int height) {
		root = createElement();
		drawContext = getContext(root);
	}
	
	public Element getElement() {
		return root;
	}

	private native JavaScriptObject getContext(Element canvasElement) /*-{
		return canvasElement.getContext('2d');
	}-*/;

	private native Element createElement()/*-{
		var e = $doc.createElement(this.@eu.ohlson.gwt.board.canvas.Canvas::tag);
		return e;
	}-*/;

}
