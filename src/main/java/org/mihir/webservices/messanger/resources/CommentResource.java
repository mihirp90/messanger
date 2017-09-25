package org.mihir.webservices.messanger.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.mihir.webservices.messanger.model.Comment;
import org.mihir.webservices.messanger.model.Profile;
import org.mihir.webservices.messanger.serrvice.CommentService;

@Path("/")
public class CommentResource {
	
	private CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("messageId") long Id){
		return commentService.getAllComments(Id);
	}
	@POST
	public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
		return commentService.addComment(messageId, comment);
	}
	/*@GET
	public String test() {
		return "sub resource";
	}
	
	@GET
	@Path("/{commentId}")
	public String testTwo(@PathParam("commentId") long messageId, @PathParam("commentId") long commentId) {
		return "Message-ID : " + messageId +  " Comment-Id : " + commentId;
	}*/
	
	@PUT
	@PathParam("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId, Comment comment) {
		comment.setId(commentId);
		return commentService.updateComment(messageId, comment);
	}
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId ) {
		commentService.removeComment(messageId, commentId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId ) {
		return commentService.getComment(messageId, commentId);
	}
	
}
