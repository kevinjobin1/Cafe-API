package ca.ulaval.glo4002.cafe;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class CafeServer implements Runnable {
  private static final String APPLICATION_PACKAGE = "ca.ulaval.glo4002.cafe";
  private static final String CONTEXT_PATH = "/";
  private static final String PATH_SPEC = CONTEXT_PATH + "*";
  private static final int PORT = 8181;
  private static final AppContext appContext = new AppContext();

  public static void main(String[] args) {
    new CafeServer().run();
  }

  private static ResourceConfig initResourceConfig() {
    return new ResourceConfig().register(appContext.cafeResource).register(appContext.reservationResource).register(appContext.customerResource)
                               .packages(APPLICATION_PACKAGE);
  }

  public void run() {

    Server server = new Server(PORT);
    ServletContextHandler contextHandler = new ServletContextHandler(server, CONTEXT_PATH);
    ResourceConfig packageConfig = initResourceConfig();
    ServletContainer container = new ServletContainer(packageConfig);
    ServletHolder servletHolder = new ServletHolder(container);

    contextHandler.addServlet(servletHolder, PATH_SPEC);

    try {
      server.start();
      server.join();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      if (server.isRunning()) {
        server.destroy();
      }
    }
  }
}
