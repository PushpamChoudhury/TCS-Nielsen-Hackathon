package api.neilsen.productreference.response.requestforhealthinfo;

import org.codehaus.jackson.annotate.JsonProperty;
import java.util.*;

public class ReuestForHealthInfo {
	@JsonProperty("Header") public Header Header;
	@JsonProperty("Characteristics") public List<Characteristics> Characteristics;
}
