package com.mohan.practice.util;


import com.mohan.practice.enums.MatchEnum;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;


public interface StringUtil {


    static String objectToString(Object obj) {

        return Optional.ofNullable(obj).map(Object::toString).orElse("");
    }





    static boolean isValidEmail(String email) {

        String regex = "[\\w+]+[@]+[\\w+]+[\\.]+[\\w+]+[\\w+]";

        return email.matches(regex);

    }

    static int booleanToInteger(Boolean flag) {

        if (flag) {
            return 1;
        } else {
            return 0;
        }
    }

    static List<String> stripSpace(List<String> listOfStrings) {

        for (int i = 0; i < listOfStrings.size(); i++) {
            listOfStrings.set(i, listOfStrings.get(i).replaceAll("\\s+", ""));
        }

        return listOfStrings;
    }

    static String stripSpace(String name) {

        return name.replaceAll("\\s+", "");

    }

    static Map<String, String> splitStringListToMap(List<String> stringList) {

        return stringList
                .stream()
                .map(str -> str.split(":"))
                .collect(toMap(str -> str[0], str -> str[1]));

    }


    static Map<MatchEnum, List<String>> getMatchesAndDifferences(List<String>list1, List<String>list2){

        Map<MatchEnum, List<String>> resultMap = new HashMap<>();

        Predicate<String> isInList1 = list1::contains;
        Predicate<String> isNotInList1 = s -> !list1.contains(s);
        Predicate<String> isNotInList2 = s -> !list2.contains(s);

        List<String> matchingList = list2.stream().filter(isInList1).collect(Collectors.toList());
        List<String> listOneOnly = list1.stream().filter(isNotInList2).collect(Collectors.toList());
        List<String> listTwoOnly = list2.stream().filter(isNotInList1).collect(Collectors.toList());

        resultMap.put(MatchEnum.BOTH,matchingList);
        resultMap.put(MatchEnum.FIRST_ONLY,listOneOnly);
        resultMap.put(MatchEnum.SECOND_ONLY,listTwoOnly);

        return resultMap;

    }

    static boolean isEmptyObject(Object object){

        List<Field> fields = Arrays.asList(object.getClass().getDeclaredFields());
        List<String> names = fields.stream().map(Field::getName).collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(object.getClass().getSimpleName());
        stringBuilder.append("(");

        StringJoiner stringJoiner = new StringJoiner(", ");

        for (String name : names) {
            stringJoiner.add(name+"=null");
        }

        stringBuilder.append(stringJoiner.toString());
        stringBuilder.append(")");

        System.out.println("Created: "+stringBuilder.toString());
        System.out.println("Actual: "+object.toString());

        return stringBuilder.toString().equals(object.toString());

    }
}

