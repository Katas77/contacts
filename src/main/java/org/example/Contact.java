package org.example;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Contact {
    private final String fullName;
    private final String phoneNumber;
    private final String email;

    @Override
    public String toString() {
        return "Contacts{" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
