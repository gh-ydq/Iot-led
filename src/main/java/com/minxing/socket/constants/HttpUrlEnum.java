package com.minxing.socket.constants;

public enum HttpUrlEnum {
	
	PG_HTTP_URL("http://localhost:8090/tracker-admin/upstream/pg",""),
	PL_HTTP_URL("",""),
	PC_HTTP_URL("",""),
	;
	
	private String url;
	private String dec;
	
	private HttpUrlEnum(String url,String dec){
		this.url = url;
		this.dec = dec;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDec() {
		return dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}
	
	
}
