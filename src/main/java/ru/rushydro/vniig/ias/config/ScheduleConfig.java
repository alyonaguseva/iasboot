package ru.rushydro.vniig.ias.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import ru.rushydro.vniig.ias.service.InterrogationService;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {


    @Autowired
    private InterrogationService interrogationService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(interrogationService::interrogationInclinometers,
                triggerContext -> interrogationService.getInterrogationInclinometersNext());

        scheduledTaskRegistrar.addTriggerTask(interrogationService::interrogationStringSensors,
                triggerContext -> interrogationService.getinterrogationStringSensorsNext());
    }

}
