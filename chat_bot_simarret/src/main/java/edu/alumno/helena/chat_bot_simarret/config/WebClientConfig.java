package edu.alumno.helena.chat_bot_simarret.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import edu.alumno.helena.chat_bot_simarret.event.ChisteContadoEvent;
import edu.alumno.helena.chat_bot_simarret.service.ChistesContadosService;
import edu.alumno.helena.chat_bot_simarret.service.SseChistesContadosService;

@Configuration
public class WebClientConfig {
    @Value("${simarret.bromista.api.url}")
    private String baseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(baseUrl).build();
    }

    // Métodos asociados a los servicios SSE
    @Bean
    public ApplicationEventPublisher eventPublisher(ApplicationEventPublisher eventPublisher) {
        return eventPublisher;
    }

    @Bean
    public ApplicationListener<ChisteContadoEvent> jokeRequestedEventListener(
            ChistesContadosService counterService,
            SseChistesContadosService sseService) {
        return event -> {
            int numeroChistesContados = counterService.getNumeroChistesContados();
            sseService.sendActualizacionChistesContados(numeroChistesContados);
        };
    }
}
