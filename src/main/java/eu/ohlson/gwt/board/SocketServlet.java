package eu.ohlson.gwt.board;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

public class SocketServlet extends WebSocketServlet {

	private static final long serialVersionUID = 1L;

	private final Set<WebSocket> connectedClients = new CopyOnWriteArraySet<WebSocket>();

	@Override
	protected WebSocket doWebSocketConnect(HttpServletRequest request,
			String protocol) {
		return new StoreClientSocket();
	}

	private class StoreClientSocket implements WebSocket {

		private Outbound outbound;

		@Override
		public void onConnect(Outbound outbound) {
			this.outbound = outbound;
			connectedClients.add(this);
		}

		@Override
		public void onMessage(byte opcode, String data) {
			System.out.println("Opcode: " + opcode + "Message" + data);

			try {
				this.outbound.sendMessage("You said: " + data);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		@Override
		public void onFragment(boolean more, byte opcode, byte[] data,
				int offset, int length) {
			System.out.println("More: " + more + " opcode: " + opcode
					+ " data: " + new String(data) + " offset: " + offset
					+ " length: " + length);

		}

		@Override
		public void onMessage(byte opcode, byte[] data, int offset, int length) {
			System.out.println("On fragment: \n opcode: " + opcode + " data: "
					+ new String(data) + " offset: " + offset + " length: "
					+ length);
		}

		@Override
		public void onDisconnect() {
			connectedClients.remove(this);

		}

	}

}
