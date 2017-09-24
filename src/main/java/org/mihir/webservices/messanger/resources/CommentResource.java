package org.mihir.webservices.messanger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class CommentResource {
	
	@GET
	public String test() {
		return "sub resource";
	}
	
	@GET
	@Path("/{commentId}")
	public String testTwo() {
		return "Metthod to return commentId";
	}
}
