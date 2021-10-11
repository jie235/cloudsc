package xbc.moka.cloudsc.common.enums;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(EnumString.List.class)
@Constraint(validatedBy = EnumStringValidator.class)
public @interface EnumString {

    String message() default "value not in enum values.";

    Class<?>[] group() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return data must int this value array
     */
    String[] value();

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        EnumString[] value();
    }
}
