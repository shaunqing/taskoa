package cn.taskoa.common.utils;

public class Message {

	public final static String SUCCESS = "success";
	public final static String WARNING = "warning";
	public final static String DANGER = "danger";
	
	private String content;
	private String ctype;

	public Message() {

	}

	public Message(String content, String ctype) {
		this.content = content;
		this.ctype = ctype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

}
