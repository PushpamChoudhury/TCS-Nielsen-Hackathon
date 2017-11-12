package api.neilsen.productreference.response.requestbydescription;

import org.codehaus.jackson.annotate.JsonProperty;

public class Header {
	@JsonProperty("API_Version") public Float API_Version;
	@JsonProperty("Message_ID") public Integer Message_ID;
	@JsonProperty("Content_Type") public String Content_Type;
	@JsonProperty("Date") public String Date;
	@JsonProperty("API_Name") public String API_Name;
}
