package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime getDaytime() {
        int hour = LocalDateTime.now().getHour();

        if (6 <= hour && hour < 12) {
            return new Morning();
        }

        if (12 <= hour && hour < 18) {
            return new Day();
        }

        if (18 <= hour && hour < 23) {
            return new Evening();
        }

        return new Night();
    }
}
// END
