package co.sunny.entities;

public class DataVO {

	private String timeStamp;
	private float ch4Ppm;
	private float co2Flux;
	private float h2oFlux;

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public float getCh4Ppm() {
		return ch4Ppm;
	}

	public void setCh4Ppm(float ch4Ppm) {
		this.ch4Ppm = ch4Ppm;
	}

	public float getCo2Flux() {
		return co2Flux;
	}

	public void setCo2Flux(float co2Flux) {
		this.co2Flux = co2Flux;
	}

	public float getH2oFlux() {
		return h2oFlux;
	}

	public void setH2oFlux(float h2oFlux) {
		this.h2oFlux = h2oFlux;
	}

}
