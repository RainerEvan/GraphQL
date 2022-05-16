package com.example.demo.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class BookNotFoundException extends RuntimeException implements GraphQLError{

    private Map<String,Object> extensions = new HashMap<>();

    public BookNotFoundException(String message, Long bookId){
        super(message);
        extensions.put("invalid book id: ", bookId);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }
    

}
