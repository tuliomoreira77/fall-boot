package br.com.tm.fall.boot.annot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Defines the scope of the Rice
 * if this annotation is present every time the class is required from the Container a brand new instance is built
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RiceScoped {
}
