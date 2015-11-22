package co.sunny.entities;

public class GenerateCSVVO {
	private String dataType;
	private String timeStampTo;
	private String timeStampFrom;

	public String getTimeStampTo() {
		return timeStampTo;
	}

	public void setTimeStampTo(String timeStampTo) {
		this.timeStampTo = timeStampTo;
	}

	public String getTimeStampFrom() {
		return timeStampFrom;
	}

	public void setTimeStampFrom(String timeStampFrom) {
		this.timeStampFrom = timeStampFrom;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}
