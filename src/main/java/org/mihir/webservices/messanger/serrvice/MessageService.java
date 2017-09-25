package org.mihir.webservices.messanger.serrvice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.mihir.webservices.messanger.database.DatabaseClass;
import org.mihir.webservices.messanger.exception.DataNotFoundException;
import org.mihir.webservices.messanger.model.Message;

public class MessageService {
	
	
	private java.util.Map<Long, Message> messages = DatabaseClass.getMessages();
	
	
	public MessageService() {
		messages.put(1L, new Message(1, "Hello World!", "Mihir"));
		messages.put(2L, new Message(2, "Hello Jersey", "Mihir"));
	}
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	public Message getMessage(long id) {
		 Message message = messages.get(id);

		 if(message == null) {
			 throw new DataNotFoundException("message with id "+ id + " not found");
		 }
		 return message;
	}
	public Message addMessage(Message msg) {
		msg.setId(messages.size() + 1);
		messages.put(msg.getId(), msg);
		return msg;
	}
	public Message updateMessage(Message message) {
		if(message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
	public List<Message> getAllMessagesForYear(int year){
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
	}
}
