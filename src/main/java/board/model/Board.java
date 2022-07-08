package board.model;

import java.sql.Blob;
import java.util.Date;

public class Board {

	private int bNumber;
	private int mNumber;
	private String kindOfBoard;
	private String title;
	private String content;
	private String imgPath;
	private Blob video;
	private Date wrTime;
	private String checkPublic;

	public Board(int bNumber, int mNumber, String kindOfBoard, String title, String content, String imgPath, Blob video,
			Date wrTime, String checkPublic) {
		this.bNumber = bNumber;
		this.mNumber = mNumber;
		this.kindOfBoard = kindOfBoard;
		this.title = title;
		this.content = content;
		this.imgPath = imgPath;
		this.video = video;
		this.wrTime = wrTime;
		this.checkPublic = checkPublic;
	}

	public Board(int mNumber, String kindOfBoard, String title, String content, String checkPublic) {
		this.mNumber = mNumber;
		this.kindOfBoard = kindOfBoard;
		this.title = title;
		this.content = content;
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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Blob getVideo() {
		return video;
	}

	public void setVideo(Blob video) {
		this.video = video;
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

}
