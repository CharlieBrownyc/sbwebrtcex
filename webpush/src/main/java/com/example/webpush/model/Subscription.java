package com.example.webpush.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

    public String endpoint;
    public Map<String, String> keys;
}
