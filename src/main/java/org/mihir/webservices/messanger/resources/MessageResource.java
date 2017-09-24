package org.mihir.webservices.messanger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.mihir.webservices.messanger.model.Message;
import org.mihir.webservices.messanger.resources.beans.MessageFilterBean;
import org.mihir.webservices.messanger.serrvice.MessageService;

@Path("/messages")
/*
 * If all method have Produces and Consumes then we can declare @Producces
 * and @Consumes at class level instead method level.
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

	MessageService msgService = new MessageService();

	@GET
	// @Produces(MediaType.APPLICATION_JSON)
	/*
	 * public List<Message> getMessages(@QueryParam("year") int
	 * year, @QueryParam("start") int start,
	 * 
	 * @QueryParam("size") int size) { if (year > 0) { return
	 * msgService.getAllMessagesForYear(year); } if (start >= 0 && size > 0) {
	 * return msgService.getAllMessagesPaginated(start, size); } return
	 * msgService.getAllMessages(); }
	 */
	// Using BeanParam to take any kind of parameter.
	public List<Message> getMessages(@BeanParam MessageFilterBean filter) {
		if (filter.getYear() > 0) {
			return msgService.getAllMessagesForYear(filter.getYear());
		}
		if (filter.getStart() >= 0 && filter.getSize() > 0) {
			return msgService.getAllMessagesPaginated(filter.getStart(), filter.getSize());
		}
		return msgService.getAllMessages();
	}

	@POST
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message) {
		return msgService.addMessage(message);
	}

	@PUT
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long Id, Message message) {
		message.setId(Id);
		return msgService.updateMessage(message);
	}

	@DELETE
	// @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long Id) {
		msgService.removeMessage(Id);
	}

	@GET
	@Path("/{messageId}")
	// @Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long messageId) {
		return msgService.getMessage(messageId);
	}
	
	//calling sub resource
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

}