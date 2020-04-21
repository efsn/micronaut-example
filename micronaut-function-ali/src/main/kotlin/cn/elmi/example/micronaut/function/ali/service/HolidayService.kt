package cn.elmi.example.micronaut.function.ali.service

import cn.elmi.example.micronaut.function.ali.validator.DurationPattern
import io.micronaut.validation.Validated
import java.time.Duration
import javax.inject.Singleton
import javax.validation.constraints.NotBlank

@Singleton
@Validated
class HolidayService {
    fun startHoliday(
        @NotBlank user: String,
        //FIXME: can't get default message
        @DurationPattern(message = "invalid duration ({validatedValue})") duration: String
    ): String {
        val d = Duration.parse(duration)
        val mins = d.toMinutes()
        return "User $user is off on holiday for $mins minutes."
    }
}