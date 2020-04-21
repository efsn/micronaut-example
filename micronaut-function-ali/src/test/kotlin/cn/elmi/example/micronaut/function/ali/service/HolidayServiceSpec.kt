package cn.elmi.example.micronaut.function.ali.service

import io.micronaut.test.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import javax.validation.ConstraintViolationException

@MicronautTest
class HolidayServiceSpec(
    private val holidayService: HolidayService
) {

    @Test
    fun `validate holiday duration`() {
        val exception = assertThrows<ConstraintViolationException> {
            holidayService.startHoliday("Arthur", "junk")
        }
        assertEquals("startHoliday.duration: invalid duration (junk)", exception.message)
    }

}