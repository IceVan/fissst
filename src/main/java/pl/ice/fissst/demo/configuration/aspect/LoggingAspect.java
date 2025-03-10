package pl.ice.fissst.demo.configuration.aspect;

import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@CommonsLog
@Aspect
@Component
public class LoggingAspect {

    //Pointcut for public methods
    @Pointcut("execution(public * *(..))")
    private void anyPublicOperation() {}

    //Pointcut for @Service annotation
    @Pointcut("@within(org.springframework.stereotype.Service)")
    private void inService() {}

    //Pointcut for public operations inside @Service class
    @Pointcut("anyPublicOperation() && inService()")
    private void servicePublicOperation() {}

    @Around("servicePublicOperation()")
    public Object publicServiceAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String uuid = UUID.randomUUID().toString();
        log.info("Execution of: " + proceedingJoinPoint.getSignature() + " With params: " + Arrays.toString(proceedingJoinPoint.getArgs()) + " and UUID: " + uuid);
        Object proceed = null;
        try{
            proceed = proceedingJoinPoint.proceed();
        }catch (Exception e){
            log.error("Function : " + proceedingJoinPoint.getSignature() + " threw [ " + e.toString() + " ] for arguments " + Arrays.toString(proceedingJoinPoint.getArgs()) + " and uuid " + uuid);
            throw e;
        }

        log.info("Result of function " + proceedingJoinPoint.getSignature() + " with uuid " + uuid + " : " + proceed.toString());
        return proceed;
    }
}
