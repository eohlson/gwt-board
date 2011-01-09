import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

public class TestServer {

	private Server server;

	public TestServer() {
		this(8585);
	}

	public TestServer(Integer runningPort) {
		server = new Server(runningPort);
		
		ContextHandlerCollection contexts = new ContextHandlerCollection();
		
		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setWar("./src/main/webapp");
		webAppContext.setContextPath("/");
		
		contexts.setHandlers(new Handler[] 
			{ webAppContext });

		
		setHandler(contexts);
	}
	
	public void setHandler(ContextHandlerCollection contexts) {
		server.setHandler(contexts);
	}


	public void start() throws Exception {
		server.start();
		server.join();
	}

	public void stop() throws Exception {
		server.stop();
		server.join();
	}

	public boolean isStarted() {
		return server.isStarted();
	}

	public boolean isStopped() {
		return server.isStopped();
	}
	
	public static void main(String[] args) throws Exception {
		new TestServer().start();
	}

}
