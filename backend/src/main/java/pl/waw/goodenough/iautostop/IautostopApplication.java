package pl.waw.goodenough.iautostop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pl.waw.goodenough.iautostop.repository.HereMapApiRepository;
import pl.waw.goodenough.iautostop.repository.HereRouteRepository;
import pl.waw.goodenough.iautostop.repository.MapApiRepository;
import pl.waw.goodenough.iautostop.repository.RouteApiRepository;

@SpringBootApplication
public class IautostopApplication {

    public static void main(String[] args) {
        SpringApplication.run(IautostopApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    public RouteApiRepository routeRepository(RestTemplate restTemplate) {
        return new HereRouteRepository(restTemplate);
    }

    @Bean
    public MapApiRepository mapApiRepository(RestTemplate restTemplate) {
        return new HereMapApiRepository(restTemplate);
    }
}
