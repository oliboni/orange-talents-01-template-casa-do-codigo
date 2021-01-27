package br.com.zup.casadocodigo.validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.List;

public class ExistValueValidator implements ConstraintValidator<ExistValue, Object> {
    private Class<?> Klass;
    private String domainAttribute;
    @PersistenceContext
    private EntityManager manager;


    @Override
    public void initialize(ExistValue param) {
        domainAttribute = param.fieldName();
        Klass = param.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value==null){
            return true;
        }

        Query query = manager.createQuery("SELECT 1 FROM " + Klass.getName() + " WHERE " + domainAttribute + " = :value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();

        return !list.isEmpty();
    }
}
