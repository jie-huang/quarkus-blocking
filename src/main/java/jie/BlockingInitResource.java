package jie;

import java.util.function.Function;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class BlockingInitResource {
  static class Data {
    public Data(String s) {
      this.s = s;
    }

    String s;
  }

  Uni<Data> initData() {
    return Uni.createFrom().item(new Data("hello"));
  }

  Data data = null;

  public Data getData() {
    return data;
  }

  @PostConstruct
  @Blocking
  public void init() {
    System.out.println("xxxxxx init() is called");
    initData().onItem().transform(new Function<Data, Void>() {
      @Override
      public Void apply(Data d) {
        data = d;
        System.out.println("xxxxxx init() is done");
        return null;
      }
    }).await().indefinitely();
  }
}
