package board.form;

public class WriteReplyForm {

    int bNumber;
    int mNumber;
    String reply;

    public WriteReplyForm(int bNumber, int mNumber, String reply) {
        this.bNumber = bNumber;
        this.mNumber = mNumber;
        this.reply = reply;
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

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
