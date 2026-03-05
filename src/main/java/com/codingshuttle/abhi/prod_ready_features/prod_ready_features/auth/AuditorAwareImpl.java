package com.codingshuttle.abhi.prod_ready_features.prod_ready_features.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        //when we learn security ,we follow rhe below steps to authenticate and authorize a user and then we will get
        // the username from the security context and return it here
        // 1. get security context
        // 2. get authentication object from security context
        // 3. get principal from authentication object
        // 4. return username from principal
        return Optional.of("Abhishek");
    }
}
