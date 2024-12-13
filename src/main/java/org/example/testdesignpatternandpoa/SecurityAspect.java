package org.example.testdesignpatternandpoa;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Around("@annotation(securedBy)")
    public Object checkSecurity(ProceedingJoinPoint joinPoint, SecuredBy securedBy) throws Throwable {
        String[] roles = securedBy.roles();
        if (roles.length == 0) {
            throw new SecurityException("No roles defined in @SecuredBy annotation!");
        }

        if (!UserContext.hasRoles(roles)) {
            throw new SecurityException("Access denied! User does not have the required roles: " + String.join(", ", roles));
        }

        return joinPoint.proceed();
    }
}
