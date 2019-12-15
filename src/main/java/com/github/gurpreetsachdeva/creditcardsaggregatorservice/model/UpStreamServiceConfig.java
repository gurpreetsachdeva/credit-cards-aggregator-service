package com.github.gurpreetsachdeva.creditcardsaggregatorservice.model;

public class UpStreamServiceConfig {

//	public String[][] serviceURLs = {
//			{ "https://app.clearscore.com/api/global/backend-tech-test/v1/cards", "10", "eligibility","ServiceName1" } };
//
	

	public String url;
	public String field;
	public String serviceName;
	int normalizedScale;

	public UpStreamServiceConfig(String url, int normalizedScale, String field, String serviceName) {
		super();
		this.url = url;
		this.field = field;
		this.serviceName = serviceName;
		this.normalizedScale=normalizedScale;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getNormalizedScale() {
		return normalizedScale;
	}

	public void setNormalizedScale(int normalizedScale) {
		this.normalizedScale = normalizedScale;
	}

	@Override
	public String toString() {
		return "UpStreamServiceConfig [url=" + url + ", field=" + field + ", serviceName=" + serviceName
				+ ", normalizedScale=" + normalizedScale + "]";
	}
}
