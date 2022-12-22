package com.example.demo.config;

import com.gonmunoz.padaejbbeans.ejb.beans.HelloWorld;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
@Log4j2
public class JndiConfiguration {

    @Value("${jndi.module.name}")
    private String moduleName;

    @Value("${jndi.path}")
    private String jndiPath;

    @Bean
    public Context context() throws NamingException {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.URL_PKG_PREFIXES,
                "org.jboss.ejb.client.naming");
        jndiProperties.put(Context.PROVIDER_URL,
                "http-remoting://127.0.0.1:8080/");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        return new InitialContext(jndiProperties);
    }

    @Bean
    public HelloWorld helloWorld(Context context)
            throws NamingException {

        String EXCLAMATION_QUOTE = "!";
        String ejbPath = moduleName + HelloWorld.class.getSimpleName() + EXCLAMATION_QUOTE + jndiPath;
        log.info("ejbPath: {}", ejbPath);
        return (HelloWorld)
                context.lookup(ejbPath);
    }
}
