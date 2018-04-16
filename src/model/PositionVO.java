package model;

public class PositionVO {
	private int studynum;
	private String groupposition;
	public int getStudynum() {
		return studynum;
	}
	@Override
	public String toString() {
		return "PositionVO [studynum=" + studynum + ", groupposition=" + groupposition + "]";
	}
	public void setStudynum(int studynum) {
		this.studynum = studynum;
	}
	public String getGroupposition() {
		return groupposition;
	}
	public void setGroupposition(String groupposition) {
		this.groupposition = groupposition;
	}
	
	
}
