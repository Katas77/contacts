package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Scanner;

@Component
public class WorkingConsole {
    private final WorkingFile workingFile;
    private final String[] commandArr = {"print", "add", "del", "stop"};
    private final Scanner Scanner = new Scanner(System.in);
    private boolean exit;

    public WorkingConsole(WorkingFile workingFile) {
        this.workingFile = workingFile;
    }

    public void start() {
        while (true) {

            System.out.println("Введите одну из команд :\n" +
                    "•\tprint (Выводить все имеющиеся контакты ) \n" +
                    "•\tadd (Добавить новый контакт в список контактов)\n" +
                    "•\tdel (Удалить контакт по email)\n" +
                    "•\tstop (Выход из приложения ).\n");
            String command = Scanner.nextLine();
            if (command.equalsIgnoreCase("stop")) {
                System.out.println("Вы вышли из приложения  ");
                return;
            }
            commands(command);


        }

    }

    public void commands(String command) {
        if (Arrays.stream(commandArr).noneMatch(com -> com.equalsIgnoreCase(command))) {
            System.out.println("Команда не корректна");
        } else {
            switch (command) {
                case "print" -> workingFile.printContacts();
                case "add" -> commandAdd();
                case "del" -> commandDel();
            }

        }
    }


    public void commandAdd() {
        while (true) {
            System.out.println("введите новый контакт   в формате -  Agafonov Andrey Yurievich; +890999999; someEmail@example.example.");
            String contact = Scanner.nextLine();
            validCommandAdd(contact);
            if (exit) {
                return;
            }
        }

    }

    public void commandDel() {
        while (true) {
            System.out.println("Введите  Email контакта подлежащего удалению.");
            String email = Scanner.nextLine();
            validCommandDel(email);
            if (exit) {
                return;
            }
        }
    }

    public void validCommandAdd(String contact) {
        exit = false;
        if (!(contact.replaceAll("[^;]", "").length() == 2)) {
            System.out.println("Поставьте между фио и номером телефоном,   а также   между номером телефоном и  Email точку с запятой  ");
        } else {
            workingFile.addContacts(contact);
            exit = true;
        }

    }

    public void validCommandDel(String email) {
        exit = false;
        if (!(email.replaceAll("[^@]", "").length() == 1)) {
            System.out.println("Введите корректный Email в формате <имя пользователя>@<домен> ");
        } else {
            workingFile.delContact(email);
            exit = true;
        }

    }

}