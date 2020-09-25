package com.example.FirstApp.Exceptions.CustomExceptions;

public class EntityNotFoundException extends RuntimeException {

public EntityNotFoundException(String message)
{
    super(message);
}

}
