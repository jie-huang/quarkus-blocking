package jie;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.smallrye.mutiny.Uni;

@Path("/")
public class Hello {
  @Inject
  BlockingInitResource res;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/hello1")
  public String hello1() {
    return res.getData().s;
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/hello2")
  public Uni<String> hello2() {
    return Uni.createFrom().item(res.getData().s);
  }
}
