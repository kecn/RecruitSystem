package se.kth.ict.ffm.recruitsystem.logger;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import se.kth.ict.ffm.recruitsystem.controller.ApplicationFacade;
import se.kth.ict.ffm.recruitsystem.util.dto.ApplicationFromViewDTO;

/**
 * Logs all method calls including parameters, return values and exceptions.
 */
@Log
@Interceptor
public class LogInterceptor {
    private static final Logger LOGGER = Logger.getLogger("se.kth.ict.ffm.recruitsystem");
    private static final Level LEVEL = Level.FINE;

    /**
     * Logs entry to and exit from a method. Also logs parameter values, 
     * return value and exceptions.
     *
     * @param ctx 
     * @return The value returned by the intercepted method.
     * @throws Exception
     */
    @AroundInvoke
    public Object logInvocation(InvocationContext ctx) throws Exception {
        Method targetMethod = logEntry(ctx);
        Object returnValue = null;
        try {
            returnValue = ctx.proceed();
        } catch (Exception e) {
            logException(targetMethod, e);
        }
        logExit(targetMethod, returnValue);
        return returnValue;
    }

    private void logExit(Method targetMethod, Object returnValue) {
        Object[] args = {targetMethod.getDeclaringClass().getCanonicalName(),
            targetMethod.getName()};
        LOGGER.log(LEVEL, "Call to {0}.{1} completed", args);
        if (!isVoid(targetMethod)) {
            LOGGER.log(LEVEL, "    Return value: {0}", returnValue);
        }
    }

    private void logException(Method targetMethod, Exception e) throws Exception {
        Object[] args = {targetMethod.getDeclaringClass().getCanonicalName(),
            targetMethod.getName(), e.getClass().getCanonicalName()};
        LOGGER.log(LEVEL, "{0}.{1} threw {2}", args);
        throw (e);
    }

    private Method logEntry(InvocationContext ctx) throws NoSuchMethodException {
        Method targetMethod = ctx.getMethod();
        Object[] params = ctx.getParameters();
        if(targetMethod.equals(ApplicationFacade.class.getMethod("submitApplication",ApplicationFromViewDTO.class)))
            LOGGER.log(LEVEL, "{0} Submited an application.", Arrays.toString(params));
        
        return targetMethod;
    }

    private boolean isVoid(Method targetMethod) {
        return targetMethod.getReturnType().isAssignableFrom(Void.TYPE);
    }
}
