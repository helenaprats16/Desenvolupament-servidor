package edu.alumno.helena.chat_bot_simarret.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import edu.alumno.helena.chat_bot_simarret.service.ChistesContadosService;
import edu.alumno.helena.chat_bot_simarret.service.SseChistesContadosService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("api/sse")
public class SseChistesContadosController {
    private final SseChistesContadosService sseService;
    private final ChistesContadosService counterService;

    public SseChistesContadosController(SseChistesContadosService sseService, ChistesContadosService counterService) {
        this.sseService = sseService;
        this.counterService = counterService;
    }

    @GetMapping(value = "/counter", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamContadorChistes() {
        SseEmitter emitter = new SseEmitter(5000L); // Timeout 5 segundos
        sseService.addEmitter(emitter);
        sseService.sendActualizacionChistesContados(counterService.getNumeroChistesContados()); // Envia valor inicial
        return emitter;
    }
}