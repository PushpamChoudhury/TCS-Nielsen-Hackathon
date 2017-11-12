package api.neilsen.productreference.response.requestbyupc;

import org.codehaus.jackson.annotate.JsonProperty;

public class Header {
	@JsonProperty("Date") public String Date;
	@JsonProperty("API_Name") public String API_Name;
	@JsonProperty("API_Version") public Float API_Version;
	@JsonProperty("Message_ID") public Integer Message_ID;
	@JsonProperty("Content_Type") public String Content_Type;
}
