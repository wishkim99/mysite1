package com.poscoict.mysite.config.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.poscoict.mysite.service", "com.poscoict.mysite.repository", "com.poscoict.mysite.aspect"})
public class Appconfig {

}
