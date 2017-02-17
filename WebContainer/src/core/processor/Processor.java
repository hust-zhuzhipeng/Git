package core.processor;

import core.request.Request;
import core.response.Response;

public interface Processor {
	void process(Request request,Response response);
}
