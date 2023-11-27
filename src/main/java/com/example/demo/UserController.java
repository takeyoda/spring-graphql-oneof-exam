package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class UserController {

  private final List<User> users;

  public UserController() {
    List<User> users = new ArrayList<>();
    users.add(new User("u1", "taro"));
    users.add(new User("u2", "jiro"));
    users.add(new User("u3", "saburo"));
    this.users = users;
  }

  @QueryMapping
  public Flux<User> findUser(@Argument("by") FindUserCondition condition) {
    Predicate<User> filter;
    if (condition.id() != null) {
      filter = u -> u.id().equals(condition.id());
    } else if (condition.nameContaining() != null) {
      filter = u -> u.name().contains(condition.nameContaining());
    } else {
      throw new IllegalStateException();
    }
    return Flux.fromStream(users.stream().filter(filter));
  }
}
