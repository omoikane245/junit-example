package com.example.junitexample.config.annotation

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DbAccess(val reason: String)
