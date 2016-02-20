package com.mycompany.todo.jaxrs;

import javax.servlet.annotation.WebServlet;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 *
 * @author ta_watanabe
 */
@WebServlet(
        urlPatterns = "/*",
        loadOnStartup = 1
)
public class RestApplication extends ServletContainer {

    public RestApplication() {
        super(new ResourceConfig().packages(RestApplication.class.getPackage().getName()));
    }

}
