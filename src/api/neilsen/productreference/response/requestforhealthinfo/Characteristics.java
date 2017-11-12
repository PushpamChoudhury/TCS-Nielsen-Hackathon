package api.neilsen.productreference.response.requestforhealthinfo;

import org.codehaus.jackson.annotate.JsonProperty;

public class Characteristics {
	@JsonProperty("VITAMIN_TYPE") public String VITAMIN_TYPE;
	@JsonProperty("UPC_CODE") public Long UPC_CODE;
	@JsonProperty("FORMULATION") public String FORMULATION;
}
