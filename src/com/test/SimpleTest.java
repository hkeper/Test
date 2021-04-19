package com.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleTest {

    public static void main(String[] args) {
        SimpleTest test = new SimpleTest();
        Map<Person, Integer> personData = test.parseData(fakeData());
        Map<Person, Integer> sortedPersonData = new LinkedHashMap<>();

        Map<Person, Integer> sortedByValue = personData.entrySet()
                .stream()
                .filter(x -> x.getValue() < 30)
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        sortedByValue.forEach((k, v) ->
            System.out.println(k.getFamilyName() + " " + k.getName() + " " + k.getMiddleName() + " -> " + v));
    }

    public Map<Person, Integer> parseData(String data) {
        Map<Person, Integer> personData = new HashMap<>();
        String[] rawPersonData = data.split("\n");
        Person person;
        if(rawPersonData.length > 1){
            for(String p: rawPersonData){
                String[] personNames = p.replaceAll("[^\\p{L}\\p{Z}]", "").split("\\s+");
                if(personNames.length == 3) {
                    person = new Person(personNames[0], personNames[1], personNames[2]);
                    personData.put(person, Integer.parseInt(p.replaceAll("[^\\p{N}]", "")));
                }
            }
        }
        return personData;
    }

    public static String fakeData() {
        return "Тетерин Глеб Ярославович, 14\n" +
                "Блинов Велор Ярославович, 21\n" +
                "Щербаков Гарри Протасьевич, 33\n" +
                "Носов Альфред Фролович, 65\n" +
                "Селиверстов Лавр Геласьевич, 9\n" +
                "Агафонов Корней Геннадиевич, 24\n" +
                "Сазонов Иосиф Павлович, 34\n" +
                "Данилов Осип Федотович, 12\n" +
                "Савин Вальтер Юлианович, 45\n" +
                "Филиппов Кассиан Артемович, 64\n" +
                "\n" +
                "\n";
    }

    public static class Person {
        private String familyName;
        private String name;
        private String middleName;
        public Person(String familyName, String middleName, String name) {
            this.familyName = familyName;
            this.name = name;
            this.middleName = middleName;
        }
        public String getFamilyName() {
            return familyName;
        }
        public String getName() {
            return name;
        }
        public String getMiddleName() {
            return middleName;
        }
    }

}