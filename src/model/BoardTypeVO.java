package model;

public class BoardTypeVO {
	private int studynum;
	private String boardid;
	private String boardname;
	private String chkprivate;
	public int getStudynum() {
		return studynum;
	}
	public void setStudynum(int studynum) {
		this.studynum = studynum;
	}
	public String getBoardid() {
		return boardid;
	}
	public void setBoardid(String boardid) {
		this.boardid = boardid;
	}
	@Override
	public String toString() {
		return "BoardTypeVO [studynum=" + studynum + ", boardid=" + boardid + ", boardname=" + boardname
				+ ", chkprivate=" + chkprivate + "]";
	}
	public String getChkprivate() {
		return chkprivate;
	}
	public void setChkprivate(String chkprivate) {
		this.chkprivate = chkprivate;
	}
	public String getBoardname() {
		return boardname;
	}
	public void setBoardname(String boardname) {
		this.boardname = boardname;
	}
}
