package com.example.inopolis.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * (RetentionPolicy.RUNTIME) означает, что
 * @LoggingAfter должна быть доступна во время выполнения программы
 * */
@Retention(RetentionPolicy.RUNTIME)

/**
 * @Target(ElementType.METHOD): Эта строка определяет место, где
 * можно применять аннотацию @LoggingAfter.
 * В данном случае она может использоваться только перед объявлениями
 * методов (или конструкторов), обозначая, что аннотацию можно применять только к методам
 * */
@Target(ElementType.METHOD)

/**
 * Ключевое слово @interface указывает, что это аннотация
 * */
public @interface LoggingBefore {
}
