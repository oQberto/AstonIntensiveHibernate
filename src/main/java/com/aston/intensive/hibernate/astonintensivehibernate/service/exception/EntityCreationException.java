package com.aston.intensive.hibernate.astonintensivehibernate.service.exception;
// тут дело вкуса, но я бы вынес все исключения в отдельную папку и сделал бы хендлер 
public class EntityCreationException extends RuntimeException {

    public EntityCreationException(String message) {
        super(message);
    }
}
