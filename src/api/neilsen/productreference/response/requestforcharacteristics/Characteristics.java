package api.neilsen.productreference.response.requestforcharacteristics;

import org.codehaus.jackson.annotate.JsonProperty;

public class Characteristics {
	@JsonProperty("DEAL") public String DEAL;
	@JsonProperty("APPLICATION_AREA") public String APPLICATION_AREA;
	@JsonProperty("SCENT") public String SCENT;
	@JsonProperty("UPC_CODE") public Long UPC_CODE;
	@JsonProperty("BRAND_HIGH") public String BRAND_HIGH;
	@JsonProperty("PACKAGE_GENERAL_SHAPE") public String PACKAGE_GENERAL_SHAPE;
	@JsonProperty("TOTAL_SIZE") public String TOTAL_SIZE;
	@JsonProperty("COMMON_CONSUMER_NAME") public String COMMON_CONSUMER_NAME;
	@JsonProperty("MULTI") public Integer MULTI;
	@JsonProperty("FORM") public String FORM;
	@JsonProperty("TARGET_SKIN_CONDITION") public String TARGET_SKIN_CONDITION;
	@JsonProperty("BRAND_OWNER_HIGH") public String BRAND_OWNER_HIGH;
	@JsonProperty("BRAND_OWNER_LOW") public String BRAND_OWNER_LOW;
	@JsonProperty("BASE_SIZE") public String BASE_SIZE;
	@JsonProperty("BRAND_LOW") public String BRAND_LOW;
}
