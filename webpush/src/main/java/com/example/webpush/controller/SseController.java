package com.example.webpush.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("sse")
public class SseController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping("/subscribe")
    public SseEmitter subscribe(){
        SseEmitter emitter = new SseEmitter(60 * 1000L); // 1분 timeout
        emitters.add(emitter);

        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onCompletion(() -> emitters.remove(emitter));

        try{
            emitter.send(SseEmitter.event().name("INIT").data("connected"));
        } catch (IOException e) {
            emitter.complete();
        }

        return emitter;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody Map<String, String> body){
        System.out.println("SSE send body:" + body);
        String message = body.get("message");
        for(SseEmitter emitter : emitters){
            try {
                emitter.send(SseEmitter.event().name("message").data(message));
            }catch(IOException e){
                emitter.complete();
            }
        }
        return ResponseEntity.ok("sent");
    }
}
