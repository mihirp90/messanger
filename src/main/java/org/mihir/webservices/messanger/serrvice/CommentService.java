package org.mihir.webservices.messanger.serrvice;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.mihir.webservices.messanger.database.DatabaseClass;
import org.mihir.webservices.messanger.model.Comment;
import org.mihir.webservices.messanger.model.ErrorMessage;
import org.mihir.webservices.messanger.model.Message;

public class CommentService {

private java.util.Map<Long, Message> messages = DatabaseClass.getMessages();
	
	
	public CommentService() {
		messages.put(1L, new Message(1, "Hello World!", "Mihir"));
		messages.put(2L, new Message(2, "Hello Jersey", "Mihir"));
	}
	public ArrayList<Comment> getAllComments(long messageId){
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	public Comment getComment(long messageId, long commentId ) {
		ErrorMessage erroMessage = new ErrorMessage("Not Found", 404, "https://www.oracle.com");
		Response response = Response.status(Status.NOT_FOUND).entity(erroMessage).build();
		Message message = messages.get(messageId);
		if(message == null) {
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if(comment == null) {
			throw new WebApplicationException(response);
		}
		return comment;
	}
	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	public Comment updateComment(long messageId, Comment comment) {
		if(comment.getId() <= 0) {
			return null;
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comments.put(comment.getId(), comment);
		return comment;
	}
	public Comment removeComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
	/*public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		
		for(Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
		
	}
	public List<Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if(start+size > list.size()) return new ArrayList<Message>();
		return list.subList(start, start+size);
	}*/
}
