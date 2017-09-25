package org.mihir.webservices.messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.mihir.webservices.messanger.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		// TODO Auto-generated method stub
		ErrorMessage erroMessage = new ErrorMessage("Server Error", 500, "https://www.oracle.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(erroMessage).build();
	}

	
	
}
