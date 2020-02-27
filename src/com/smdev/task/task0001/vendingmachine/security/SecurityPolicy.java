package com.smdev.task.task0001.vendingmachine.security;

import org.apache.commons.lang3.StringUtils;

public class SecurityPolicy {

    public void authorize(SecuredResource resource, String pinCode) throws SecurityException {
        if (resource == null || StringUtils.isEmpty(pinCode)) {
            throw new SecurityException("Access denied.");
        }

        if (!"4444".equalsIgnoreCase(pinCode)) {
            throw new SecurityException("Access denied.");
        }
    }
}
