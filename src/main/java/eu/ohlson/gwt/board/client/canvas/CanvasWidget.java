package eu.ohlson.gwt.board.client.canvas;

import java.util.LinkedList;

import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

public class CanvasWidget extends Widget implements HasAllMouseHandlers {

	private Canvas canvas;

	private LinkedList<Postit> postits = new LinkedList<Postit>();

	private Postit dragged = null;

	public CanvasWidget() {
		canvas = new Canvas(800, 800);
		setElement(canvas.getElement());

		registerListeners();
	}

	private void registerListeners() {
		addMouseDownHandler(new MouseDownHandler() {

			@Override
			public void onMouseDown(MouseDownEvent event) {
				Postit hitPostit = getHittedPostit(event.getX(), event.getY());
				if (hitPostit == null) {
					dragged = addNewPostit(event);
					redraw();
				}

				dragged = hitPostit;
			}

		});

		addMouseUpHandler(new MouseUpHandler() {

			@Override
			public void onMouseUp(MouseUpEvent event) {
				if (dragged != null) {
					dragged.x = event.getX();
					dragged.y = event.getY();
				}
				
				dragged = null;

				redraw();
			}
		});

		addMouseMoveHandler(new MouseMoveHandler() {

			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if (dragged != null) {
					dragged.x = event.getX();
					dragged.y = event.getY();
				}

				redraw();
			}
		});
	}

	private Postit getHittedPostit(int x, int y) {
		for (Postit p : postits) {
			if (x > p.x && x < (p.x + p.width) && y > p.y
					&& y < (p.y + p.width)) {
				return p;
			}
		}

		return null;
	}

	private Postit addNewPostit(MouseDownEvent event) {
		System.out.println("Create");
		
		Postit postit = new Postit();
		postit.x = event.getX();
		postit.y = event.getY();
		postit.width = 50;
		postit.height = 20;

		postits.add(postit);
		
		return postit;
	}

	private void redraw() {
		canvas.clear();

		for (Postit postit : postits) {
			canvas.fillStyle("black");
			canvas.fillRect(postit.x, postit.y, postit.width, postit.height);

			canvas.fillStyle(postit.color);
			canvas.fillRect(postit.x + 1, postit.y + 1, postit.width - 2,
					postit.height - 2);
		}

	}

	@Override
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return addDomHandler(handler, MouseDownEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return addDomHandler(handler, MouseUpEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return addDomHandler(handler, MouseOutEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return addDomHandler(handler, MouseOverEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return addDomHandler(handler, MouseMoveEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
		return addDomHandler(handler, MouseWheelEvent.getType());
	}
}
