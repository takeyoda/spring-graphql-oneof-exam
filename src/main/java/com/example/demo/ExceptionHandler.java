package com.example.demo;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandler extends DataFetcherExceptionResolverAdapter {
  private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

  public ExceptionHandler() {
    setThreadLocalContextAware(true);
  }

  @Override
  protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
    log.error("エラー発生", ex);
    return GraphqlErrorBuilder.newError(env)
        .errorType(ErrorType.INTERNAL_ERROR)
        .message(ex.toString())
        .build();
  }
}
