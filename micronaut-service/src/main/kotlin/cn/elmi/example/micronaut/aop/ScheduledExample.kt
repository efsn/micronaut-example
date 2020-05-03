package cn.elmi.example.micronaut.aop

import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.TaskScheduler
import io.micronaut.scheduling.annotation.Scheduled
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ScheduledExample {
    @Inject
    @Named(TaskExecutors.SCHEDULED)
    lateinit var taskScheduler: TaskScheduler

    @Scheduled(fixedRate = "5m")
    internal fun everyFiveMinutes() {
        println("Executing everyFiveMinutes")
    }

    @Scheduled(fixedDelay = "5m")
    internal fun fiveMinutesAfterLastExecution() {
        println("Executing fiveMinutesAfterLastExecution")
    }

    @Scheduled(cron = "0 15 10 ? * MON")
    internal fun everyMondayAtTenFifteenAm() {
        println("Executing everyMondayAtTenFifteenAm")
    }

    @Scheduled(initialDelay = "1m")
    internal fun onceOneMinuteAfterStartup() {
        println("Executing onceOneMinuteAfterStartup")
    }

    @Scheduled(fixedRate = "\${my.task.rate:5m}", fixedDelay = "\${my.task.delay:1m}")
    internal fun configuredTask() {
        println("Executing configuredTask")
    }
}