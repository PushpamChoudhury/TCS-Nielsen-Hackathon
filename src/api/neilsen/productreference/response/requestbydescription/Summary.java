package api.neilsen.productreference.response.requestbydescription;

import org.codehaus.jackson.annotate.JsonProperty;

public class Summary {
	@JsonProperty("PageSize") public Integer PageSize;
	@JsonProperty("PageNo") public Integer PageNo;
	@JsonProperty("TotalPages") public Integer TotalPages;
	@JsonProperty("TotalRecords") public Integer TotalRecords;
}
