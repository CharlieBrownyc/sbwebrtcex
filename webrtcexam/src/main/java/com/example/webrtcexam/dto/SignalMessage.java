package com.example.webrtcexam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignalMessage {
    
    private String type; // e.g., "offer", "answer", "candidate"
    private String sender; // ID of the sender
    private String receiver; // ID of the receiver (if applicable)
    private String sdp; // SDP data or ICE candidate information
    private String candidate; // ICE candidate information (if applicable)
    private String roomId; // ID of the room (if applicable)
    private String content; // Chat message content (if applicable)
}
