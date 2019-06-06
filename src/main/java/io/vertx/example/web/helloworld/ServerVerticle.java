package io.vertx.example.web.helloworld;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.Router;

public class ServerVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {

    Router router = Router.router(vertx);
	router.route().handler(LoggerHandler.create(true, LoggerFormat.SHORT));
    router.route().handler(routingContext -> {
      routingContext.response().putHeader("content-type", "text/html").end("Hello World!");
    });

    vertx.createHttpServer().requestHandler(router).listen(8080, result -> {
		if (result.succeeded()) {
			System.out.println("Server started on port 8080");
        } else {
			System.err.println("Server failed to start on port 8080");
			result.cause().printStackTrace();
		}
	});
  }
}