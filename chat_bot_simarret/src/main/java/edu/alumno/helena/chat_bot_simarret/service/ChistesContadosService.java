package edu.alumno.helena.chat_bot_simarret.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import edu.alumno.helena.chat_bot_simarret.event.ChisteContadoEvent;

@Service
public class ChistesContadosService {
    private final AtomicInteger chistesContados = new AtomicInteger(0);

    @EventListener
    public void handleChisteRequestedEvent(ChisteContadoEvent event){
        chistesContados.incrementAndGet();
    }

    public int getNumeroChistesContados(){
        return chistesContados.get();
    }
}
