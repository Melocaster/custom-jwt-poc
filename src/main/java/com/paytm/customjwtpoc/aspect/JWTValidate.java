package com.paytm.customjwtpoc.aspect;

import com.paytm.customjwtpoc.annotation.Authorized;
import com.paytm.customjwtpoc.util.JWTUtil0;
import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JWTValidate {
  @Around("@annotation(com.paytm.customjwtpoc.annotation.Authorized)")
  public Object validateAspect(ProceedingJoinPoint pjp) throws Throwable {
    MethodSignature signature = (MethodSignature) pjp.getSignature();
    Object[] args = pjp.getArgs();
    String header = (String) args[0];

    if (header == null || !header.startsWith("Bearer ")) {
      throw new RuntimeException("Wrong Auth token");
    }

    String authToken = header.substring(7);
    String userPermission = JWTUtil0.getPermission(authToken);

    Method method = signature.getMethod();

    Authorized validateAction = method.getAnnotation(Authorized.class);
    String resource = validateAction.resource();
    String permissionRequired = validateAction.permission();

    System.out.println("resource is: " + resource);
    System.out.println("permission is: " + permissionRequired);

    if (userPermission.contains(resource) && userPermission.contains(permissionRequired)) {

      return pjp.proceed();

    }

    return "UnAuthorized";
  }
}
