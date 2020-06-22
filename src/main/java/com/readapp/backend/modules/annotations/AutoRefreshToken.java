package com.readapp.backend.modules.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoRefreshToken {
    RefreshLevel level = RefreshLevel.NORMAL;
}

enum RefreshLevel{
    NORMAL
}


