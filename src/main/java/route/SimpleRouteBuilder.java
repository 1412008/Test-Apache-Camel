package route;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:D:/inputFolder").split().tokenize("\n").to("jms:queue:myjms");
	}

}
