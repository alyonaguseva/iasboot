package ru.rushydro.vniig.ias.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import ru.rushydro.vniig.ias.dao.entity.Bing3Exchange;
import ru.rushydro.vniig.ias.service.Bing3ExchangeService;
import ru.rushydro.vniig.ias.service.InterrogationService;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {


    @Autowired
    private InterrogationService interrogationService;

    @Autowired
    private Bing3ExchangeService bing3ExchangeService;

    @Value("${bing3.exchange:false}")
    private boolean bing3Exchange;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(interrogationService::interrogationInclinometers,
                triggerContext -> interrogationService.getInterrogationInclinometersNext());

        scheduledTaskRegistrar.addTriggerTask(interrogationService::interrogationStringSensors,
                triggerContext -> interrogationService.getinterrogationStringSensorsNext());

        if (bing3Exchange) {
            scheduledTaskRegistrar.addTriggerTask(bing3ExchangeService::updateData,
                    triggerContext -> interrogationService.getBing3ExchangeNext());
        }

    }

}
