package org.example.test_extensions;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@UnitTest
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface UnitApiTest 
{
    
}
