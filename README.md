# "Приложение контакты"
### Приложение представляет собой удобное средство управления списком контактов, написанное на языке Java с использованием Spring Framework.

## Возможности:
- Просмотр всех контактов: вывод списка контактов в формате «Ф. И. О. | Номер телефона | Адрес электронной почты».
- Добавление нового контакта: возможность добавления новых записей в список контактов. Данные вводятся в формате: Ф. И. О.; номер телефона; адрес электронной почты.
- Удаление контакта: удаление записи о контакте по адресу электронной почты.
- Сохранение контактов: сохранение текущих контактов в файл в формате «Ф. И. О;номер телефона;адрес электронной почты».


## Основные принципы работы:
- Конфигурационные файлы содержат пути к файлам для загрузки и сохранения контактов.
- Можно включать/выключать автоматическое заполнение контактов при запуске приложения через загрузку из файла default-contacts.txt.
- Для отключения загрузки: spring.profiles.active=noInit
- Для включения загрузки: spring.profiles.active=init
## Команды:
- print: вывести все сохранённые контакты.
- add: добавить новый контакт.
- del: удалить контакт по электронному адресу.
- stop: завершить работу приложения.
## Использованные технологии:
- Язык программирования: Java
- Веб-фреймворк: Spring Framework
- Система сборки проекта: Gradle

## Используемые технологии:

- Java
- Spring
- Gradle


____
✉ Почта для обратной связи:
<a href="">krp77@mail.ru</a>