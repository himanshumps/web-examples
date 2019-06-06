package io.vertx.example.web.helloworld;

import io.vertx.core.AbstractVerticle;
import io.vertx.example.util.Runner;
import io.vertx.example.web.helloworld.ServerVerticle;

import io.vertx.core.VertxOptions;
import io.vertx.core.Vertx;
import io.vertx.core.DeploymentOptions;

import java.util.concurrent.TimeUnit;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Server {

  // Convenience method so you can run it in your IDE
  public static void main(String[] args) {
	System.setProperty("vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.SLF4JLogDelegateFactory");
	VertxOptions vertxOptions = new VertxOptions();
    vertxOptions.setPreferNativeTransport(true).setWarningExceptionTime(TimeUnit.SECONDS.toNanos(2));
	Vertx vertx = Vertx.vertx(vertxOptions);
	DeploymentOptions deploymentOptions = new DeploymentOptions().setInstances(10);
    vertx.deployVerticle(ServerVerticle.class, deploymentOptions, result -> {
		if (result.succeeded()) {
			System.out.println("Server started on port 8080");
        } else {
			System.err.println("Server failed to start on port 8080");
			result.cause().printStackTrace();
		}
	}); 
  }

  
}
