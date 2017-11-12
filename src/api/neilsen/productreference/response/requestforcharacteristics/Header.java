package api.neilsen.productreference.response.requestforcharacteristics;

import org.codehaus.jackson.annotate.JsonProperty;
import java.util.*;

public class Header {
	@JsonProperty("Date") public String Date;
	@JsonProperty("MessageID") public Integer MessageID;
	@JsonProperty("API_Name") public String API_Name;
	@JsonProperty("API_Version") public Double API_Version;
	@JsonProperty("Content_Type") public String Content_Type;
}
