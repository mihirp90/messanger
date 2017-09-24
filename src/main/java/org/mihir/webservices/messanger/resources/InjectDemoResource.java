package org.mihir.webservices.messanger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("annotation")
	public String getParamUsingAnnotation(@MatrixParam("param") String matrixParam,
			@HeaderParam("customHeaderValue") String headerValue, @CookieParam("name") String cookie) {
		return "Matrix Param is " + matrixParam + " Header Param: " + headerValue + " Cookie Param : " + cookie;
	}

	@GET
	@Path("context")
	public String getContextParam(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		String path = uriInfo.getAbsolutePath().toString();
		String mediaType = headers.getClass().toString();
		return "Path : " + path + " Media-Type :  " + mediaType;
	}
}
