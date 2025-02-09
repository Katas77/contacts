package org.example.impl;

import org.example.model.Contact;
import org.example.config.InitSavePaths;
import org.example.config.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeMap;


@Component
@PropertySource(value = "classpath:application-init.yaml", factory = YamlPropertySourceFactory.class)
@Profile("init")
public class WorkingFileInit implements WorkingFile {
    InitSavePaths path;
    private TreeMap<String, Contact> contactsMap = new TreeMap<>();

    @Autowired
    public WorkingFileInit(InitSavePaths simplePath) {
        this.path = simplePath;
    }

    @PostConstruct
    public void initContacts() {
        File file = new File(path.getPathInit());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (; ; ) {
                String[] contactOne = reader.readLine().split(";");
                String email = contactOne[2].trim();
                contactsMap.put(email, new Contact(contactOne[0].trim(), contactOne[1].trim(), email));
                if (contactOne.length != 3) {
                    break;
                }
            }
        } catch (IOException | NullPointerException  e) {
        }
        if (contactsMap.isEmpty()) {
            return;
        }
        ArrayList<String> newContactsStr = new ArrayList<>();
        contactsMap.values().forEach(contacts -> newContactsStr.add(contacts.getFullName() + ";" + contacts.getPhoneNumber() + ";" + contacts.getEmail()));
        try {
            Files.write(Paths.get(path.getPathSave()), newContactsStr);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        }

    }

    public void printContacts() {
        contactsMap = new TreeMap<>();
        if (readContacts().values().isEmpty()) {
            System.out.println("Список контактов пустой");
        } else {
            readContacts().values().forEach(contact -> System.out.println(contact.getFullName() + "; " + contact.getPhoneNumber() + "; " + contact.getEmail()));
        }
    }

    public void addContact(String contact) {
        contactsMap = new TreeMap<>();
        readContacts();
        String[] contactArr = contact.split(";");
        String email = contactArr[2].trim();
        contactsMap.put(email, new Contact(contactArr[0].trim(), contactArr[1].trim(), email));
        recordingFile();
        System.out.println("Контакт    с " + contactArr[0].trim() + " добавлен ");
    }

    public void deleteContactByEmail(String email) {
        contactsMap = new TreeMap<>();
        readContacts();
        if (contactsMap.values().stream().noneMatch(contact -> contact.getEmail().equals(email))) {
            System.out.println("В списках нет контакта  с email - " + email);
            return;
        }
        String fullName = contactsMap.get(email).getFullName();
        contactsMap.remove(email);
        recordingFile();
        System.out.println("Контакт  " + fullName + "  с email  " + email + "  удален ");
    }

    private void recordingFile() {
        ArrayList<String> newContactsStr = new ArrayList<>();
        contactsMap.values().forEach(contacts -> newContactsStr.add(contacts.getFullName() + ";" + contacts.getPhoneNumber() + ";" + contacts.getEmail()));
        if (newContactsStr.isEmpty()) {
            return;
        }
        try {
            Files.write(Paths.get(path.getPathSave()), newContactsStr);
        } catch (Exception exception) {
        }
    }

    public TreeMap<String, Contact> readContacts() {
        File file = new File(path.getPathSave());
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            for (; ; ) {
                String[] contactOne = reader.readLine().split(";");
                String email = contactOne[2].trim();
                contactsMap.put(email, new Contact(contactOne[0].trim(), contactOne[1].trim(), email));
                if (contactOne.length != 3) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return contactsMap;
    }
}

