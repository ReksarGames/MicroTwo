package com.example.microone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Message")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Message extends AbstractEntity {
    private String text;
    private String from;

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
