package cn.elmi.example.micronaut.function.ali.validator

import io.micronaut.context.annotation.Factory
import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import javax.inject.Singleton
import javax.validation.Constraint

@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [])
annotation class DurationPattern(
    val message: String = "invalid duration ({validatedValue})"
)

@Retention(AnnotationRetention.RUNTIME)
annotation class TimeOff(
    @DurationPattern val duration: String
)

class DurationPatternValidator : ConstraintValidator<DurationPattern, String> {
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<DurationPattern>,
        context: ConstraintValidatorContext
    ) = value?.matches("^PT?[\\d]+[SMHD]$".toRegex()) ?: true
}

@Factory
class ValidatorFactory {

    @Singleton
    fun durationPatternValidator(): ConstraintValidator<DurationPattern, String> =
        ConstraintValidator { value, _, _ ->
            value?.matches("^PT?[\\d]+[SMHD]$".toRegex()) ?: true
        }
}

