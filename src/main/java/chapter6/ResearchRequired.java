package chapter6;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Аннотация для обозначения исследования
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ResearchRequired {
    String description() default "Requires research before usage";
}
