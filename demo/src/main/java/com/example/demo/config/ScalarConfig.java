package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Configuration
public class ScalarConfig {
    
    @Bean
    public GraphQLScalarType date(){
        return ExtendedScalars.Date;
    }

    @Bean
    public GraphQLScalarType dateTime(){
        return ExtendedScalars.DateTime;
    }


}
