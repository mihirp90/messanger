package org.mihir.webservices.messanger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.mihir.webservices.messanger.model.ErrorMessage;

@Provider
public class DataNotFoundExcceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		// TODO Auto-generated method stub
		ErrorMessage erroMessage = new ErrorMessage(ex.getMessage(), 404, "https://www.oracle.com");
		return Response.status(Status.NOT_FOUND).entity(erroMessage).build();
	}

	
	
}
