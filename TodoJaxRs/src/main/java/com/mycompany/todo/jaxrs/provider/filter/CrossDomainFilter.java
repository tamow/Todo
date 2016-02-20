package com.mycompany.todo.jaxrs.provider.filter;

import com.mycompany.todo.jaxrs.exception.RestException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.Response.Status.FORBIDDEN;
import javax.ws.rs.ext.Provider;

@Provider
public class CrossDomainFilter implements ContainerRequestFilter {

    @Context
    private HttpServletResponse httpServletResponse;

    private Set<String> allowDomainSet = new HashSet<>();

    public CrossDomainFilter() {
//        this.allowDomainSet.add("http://localhost:8383");
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String origin = containerRequestContext.getHeaderString("Origin");

        if (origin != null) {

            if (this.allowDomainSet.contains(origin)) {
                httpServletResponse.addHeader("Access-Control-Allow-Origin", origin);
                httpServletResponse.addHeader("Access-Control-Allow-Headers", "Content-Type");
                httpServletResponse.addHeader("Access-Control-Allow-Methods", "PUT, DELETE");
                httpServletResponse.addHeader("Access-Control-Expose-Headers", "Location");
            } else {
                throw new RestException(FORBIDDEN);
            }
        }
    }
}
