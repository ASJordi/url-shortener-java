package dev.asjordi.interceptors;

import dev.asjordi.exceptions.ServiceJDBCException;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;

@TransactionalJpa
@Interceptor
public class TransactionalJpaInterceptor {

    @Inject
    private EntityManager em;

    @AroundInvoke
    public Object manageTransaction(InvocationContext ctx) throws Exception {
        try {
            em.getTransaction().begin();
            Object result = ctx.proceed();
            em.getTransaction().commit();
            return result;
        } catch (ServiceJDBCException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
