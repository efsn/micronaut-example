package cn.elmi.example.micronaut.function.ali.configuration

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.core.annotation.Introspected
import io.micronaut.inject.annotation.NamedAnnotationMapper
import io.micronaut.inject.visitor.VisitorContext
import javax.annotation.Nonnull

class EntityIntrospectedAnnotationMapper : NamedAnnotationMapper {

    @Nonnull
    override fun getName(): String = "javax.persistence.Entity"

    override fun map(
        annotation: AnnotationValue<Annotation>?,
        visitorContext: VisitorContext?
    ): MutableList<AnnotationValue<*>> {
        val builder =
            AnnotationValue
                .builder(Introspected::class.java)
                .member("excludedAnnotations", "javax.persistence.Transient")
        return mutableListOf(builder.build())
    }
}

