package edu.patronovskiy.studentorder.domain.register;

import edu.patronovskiy.studentorder.domain.Person;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

//результат проверки в городском реестре населения конкретного члена семьи
public class AnswerCityRegisterItem {
    //внутренние классы
    public enum CityStatus {
        YES, NO, ERROR;
    }

    public static class CityError {
        private String code;
        private String text;

        public CityError(String code, String text) {
            this.code = code;
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public String getText() {
            return text;
        }
    }

    //собственные поля и методы
    private CityStatus status;
    private Person person;
    private CityError error;

    public AnswerCityRegisterItem(CityStatus status, Person person, CityError error) {
        this.status = status;
        this.person = person;
        this.error = error;
    }

    public AnswerCityRegisterItem(CityStatus status, Person person) {
        this.status = status;
        this.person = person;
    }

    public CityStatus getStatus() {
        return status;
    }

    public Person getPerson() {
        return person;
    }

    public CityError getError() {
        return error;
    }
}
