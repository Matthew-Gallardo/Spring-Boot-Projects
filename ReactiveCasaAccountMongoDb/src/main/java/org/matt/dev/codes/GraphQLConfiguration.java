package org.matt.dev.codes;

import graphql.GraphQLContext;
import graphql.language.Value;
import graphql.scalars.ExtendedScalars;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.Locale;

@Configuration
public class GraphQLConfiguration {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.GraphQLBigDecimal)
                .scalar(GraphQLScalarType.newScalar()
                        .name("Void")
                        .coercing(new Coercing<Void, Void>() {
                            @Override
                            public Void serialize(Object dataFetcherResult, GraphQLContext graphQLContext, Locale locale) {
                                return null;
                            }

                            @Override
                            public Void parseValue(Object input, GraphQLContext graphQLContext, Locale locale) {
                                return null;
                            }

                            @Override
                            public Void parseLiteral(Value<?> input, graphql.execution.CoercedVariables variables, GraphQLContext graphQLContext, Locale locale) {
                                return null;
                            }

                            @Override
                            public Value<?> valueToLiteral(Object input, GraphQLContext graphQLContext, Locale locale) {
                                return Coercing.super.valueToLiteral(input, graphQLContext, locale);
                            }
                        })
                        .build());
    }
}