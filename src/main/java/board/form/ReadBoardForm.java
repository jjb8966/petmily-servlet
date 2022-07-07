package board.form;

import java.util.Date;
import java.util.List;

public class ReadBoardForm {
	
	private int bNumber;
	private int mNumber;
	private String name;
	private String kindOfBoard;
	private String title;
	private String content;
	private Date wrTime; 
	private String checkPublic;
	private List<ReplyForm> replies;
	
	public ReadBoardForm(int bNumber, int mNumber, String name, String kindOfBoard, String title, String content,
			Date wrTime, String checkPublic) {
		this.bNumber = bNumber;
		this.mNumber = mNumber;
		this.name = name;
		this.kindOfBoard = kindOfBoard;
		this.title = title;
		this.content = content;
		this.wrTime = wrTime;
		this.checkPublic = checkPublic;
	}

	public int getbNumber() {
		return bNumber;
	}

	public void setbNumber(int bNumber) {
		this.bNumber = bNumber;
	}

	public int getmNumber() {
		return mNumber;
	}
	
	public void setmNumber(int mNumber) {
		this.mNumber = mNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKindOfBoard() {
		return kindOfBoard;
	}

	public void setKindOfBoard(String kindOfBoard) {
		this.kindOfBoard = kindOfBoard;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWrTime() {
		return wrTime;
	}

	public void setWrTime(Date wrTime) {
		this.wrTime = wrTime;
	}

	public String getCheckPublic() {
		return checkPublic;
	}

	public void setCheckPublic(String checkPublic) {
		this.checkPublic = checkPublic;
	}
	
	public List<ReplyForm> getReplies() {
		return replies;
	}
	
	public void setReplies(List<ReplyForm> replies) {
	    this.replies = replies;
	}
}
