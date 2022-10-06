package main;

import controllers.ResourceServerController;
import controllers.ResourceServerControllerMBean;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resources.TestResource;
import servlets.*;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) throws Exception {

        final TestResource testResource = new TestResource();

        Server server = new Server(8083);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        ResourceServerControllerMBean resourceServer = new ResourceServerController(testResource);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=ResourceServerController");
        mbs.registerMBean(resourceServer, name);

        context.addServlet(new ServletHolder(new ResourceServlet(testResource)), "/resource");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("pages_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");

        server.join();

    }
}