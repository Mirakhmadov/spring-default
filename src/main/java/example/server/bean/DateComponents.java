package example.server.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class DateComponents {

    @Bean
    public Timestamp getNow(){
        return Timestamp.valueOf(LocalDateTime.now());
    }
}
