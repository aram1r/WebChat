package system.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    @Before("execution(* system.services.*.* (..))")
    public void beforeServiceMethodInvocation(JoinPoint jp) {
        System.out.println("Invocation of method " + jp.getSignature());
    }

    @Around("execution(* system.services.*.* (..))")
    public Object aroundServiceMethodExecution(ProceedingJoinPoint jp) throws Throwable {
        long start = System.currentTimeMillis();
        Object res = jp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Execution of method " + jp.getSignature() + " took " + (end-start) + " ms");
        return res;
    }


}
