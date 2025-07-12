package com.showb.firstboot.users.enums;

import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum PersonalCode {
    SHOWB("이창섭"),
    SUZY("노아현"),
    ;


    private final String humanName;

    private static final Map<String, PersonalCode> PERSON_MAP = Stream.of(values())
            .collect(Collectors.toUnmodifiableMap(Enum::name, Function.identity()));


    PersonalCode(String humanName) {
        this.humanName = humanName;
    }

    @Nullable
    public static PersonalCode from(String warehouse) {
        return PERSON_MAP.get(warehouse);
    }

}
