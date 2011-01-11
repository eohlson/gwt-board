package eu.ohlson.gwt.board.client.canvas;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

public class Canvas {

	public static final String tag = "canvas";

	private final Element root;

	private final JavaScriptObject drawContext;

	public Canvas(int width, int height) {
		root = createElement();
		drawContext = getContext(root);
		
		setWidth(width);
		setHeight(height);
	}
	
	public Element getElement() {
		return root;
	}

	private native JavaScriptObject getContext(Element canvasElement) /*-{
		return canvasElement.getContext('2d');
	}-*/;

	private native Element createElement()/*-{
		var e = $doc.createElement(this.@eu.ohlson.gwt.board.client.canvas.Canvas::tag);
		return e;
	}-*/;
	
	
	public native void fillRect(int x, int y, int width, int height) /*-{
		this.@eu.ohlson.gwt.board.client.canvas.Canvas::drawContext.fillRect(x,y,width,height);
	}-*/;
	
	public native void moveTo(int x, int y) /*-{
		this.@eu.ohlson.gwt.board.client.canvas.Canvas::drawContext.moveTo(x,y);
	}-*/;
	
	public native void lineTo(int x, int y) /*-{
		this.@eu.ohlson.gwt.board.client.canvas.Canvas::drawContext.lineTo(x,y);
	}-*/;
	
	public native void fillStyle(String fillStyle) /*-{
		this.@eu.ohlson.gwt.board.client.canvas.Canvas::drawContext.fillStyle = fillStyle;
	}-*/;
	
	public native void stroke() /*-{
		this.@eu.ohlson.gwt.board.client.canvas.Canvas::drawContext.stroke();
	}-*/;
	
	public native void clear() /*-{
		this.@eu.ohlson.gwt.board.client.canvas.Canvas::drawContext.clearRect(0,0,this.@eu.ohlson.gwt.board.client.canvas.Canvas::root.width,this.@eu.ohlson.gwt.board.client.canvas.Canvas::root.height);
	}-*/;
	
	
	private void setWidth(int width) {
		DOM.setElementAttribute(getElement(), "width", Integer.toString(width));
	}
	
	private void setHeight(int height) {
		DOM.setElementAttribute(getElement(), "height", Integer.toString(height));
	}
	
	
	
	
	
	
	
}
