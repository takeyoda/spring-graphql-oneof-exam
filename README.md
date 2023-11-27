# spring-graphql-oneof-exam

A spring-graphql `@oneOf` directive example

---
## Requirement
- java 17

## Run
```
./gradlew bootrun
```

...and go to http://localhost:8080/graphiql

## Example query
```graphql
query u1 {
  findUser(by: {
    id: "u1"
  }) {
    id
    name
  }
}
```
