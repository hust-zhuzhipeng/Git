package core.processor;

import java.io.IOException;

import core.request.Request;
import core.response.Response;

public class StaticResourceProcessor implements Processor{

	@Override
	public void process(Request request, Response response) {
		try {
			response.sendStaticResource();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
