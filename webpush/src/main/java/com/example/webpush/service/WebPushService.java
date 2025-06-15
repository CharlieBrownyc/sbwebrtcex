package com.example.webpush.service;




import com.example.webpush.model.Subscription;
import jakarta.annotation.PostConstruct;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;

import org.apache.http.HttpResponse;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WebPushService {
    @Value("${vapid.publicKey}")
    private String publicKey;
    @Value("${vapid.privateKey}")
    private String privateKey;
    @Value("${vapid.subject}")
    private String subject;

    private PushService pushService;
    private final Map<String, Subscription> subscriptions = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        System.out.println("publicKey:" + publicKey);
        System.out.println("privateKey:" + privateKey);
        System.out.println("subject:" + subject);
        pushService = new PushService(publicKey, privateKey, subject);
    }

    public void subscribe(Subscription sub) {
        subscriptions.put(sub.getEndpoint(), sub);
    }

    public void send(String message) {
        subscriptions.values().forEach(sub -> {
            try {
                Notification notification = new Notification(
                        sub.getEndpoint(),
                        sub.getKeys().get("p256dh"),
                        sub.getKeys().get("auth"),
                        message
                );
                HttpResponse response = pushService.send(notification);
                System.out.println("응답 코드: " + response.getStatusLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public String getPublicKey() {
        return publicKey;
    }
}
