package com.showb.firstboot.exceptions;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FirstbootExceptionTest {
    @Test
    @Order(1)
    @DisplayName("ExceptionType 이 지정되면 예외의 메시지는 ExceptionType 의 메시지로 설정되어야 한다.")
    void exceptionTypeWithNoPlaceholder() {
        FirstbootException exception = new FirstbootException(TestExceptionType.TEST);

        assertEquals("테스트 예외 메시지", exception.getMessage());
    }

    @Test
    @Order(2)
    @DisplayName("플레이스홀더가 1개인 ExceptionType 이 지정되면 예외의 메시지는 ExceptionType 의 메시지를 기반으로 " +
            "플레이스홀더가 messageParams 로 치환되어 설정되어야 한다.")
    void exceptionTypeWithOnePlaceholder() {
        FirstbootException exception = new FirstbootException(TestExceptionType.TEST_WITH_ONE_PLACEHOLDER, "문자열 파라미터1");

        assertEquals("테스트 예외 메시지 문자열 파라미터1", exception.getMessage());
    }

    @Test
    @Order(3)
    @DisplayName("플레이스홀더가 2개인 ExceptionType 이 지정되면 예외의 메시지는 ExceptionType 의 메시지를 기반으로 " +
            "플레이스홀더가 messageParams 로 치환되어 설정되어야 한다.")
    void exceptionTypeWithTwoPlaceholders() {
        FirstbootException exception = new FirstbootException(
                TestExceptionType.TEST_WITH_TWO_PLACEHOLDERS,
                "문자열 파라미터1",
                123
        );

        assertEquals("테스트 예외 메시지 문자열 파라미터1, 123", exception.getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("플레이스홀더가 3개인 ExceptionType 이 지정되면 예외의 메시지는 ExceptionType 의 메시지를 기반으로 " +
            "플레이스홀더가 messageParams 로 치환되어 설정되어야 한다.")
    void exceptionTypeWithThreePlaceholders() {
        FirstbootException exception = new FirstbootException(
                TestExceptionType.TEST_WITH_THREE_PLACEHOLDERS,
                "문자열 파라미터1",
                123,
                new TestObject()
        );

        assertEquals("테스트 예외 메시지 문자열 파라미터1, 123, TestObject 의 toString() 결과값", exception.getMessage());
    }

    @Test
    @Order(5)
    @DisplayName("플레이스홀더가 3개인 ExceptionType 에 messageParams 는 4개가 전달되어도 3번째 messageParams 까지만 치환되어야 한다.")
    void exceptionTypeWithThreePlaceholdersAndFourMessageParams() {
        FirstbootException exception = new FirstbootException(
                TestExceptionType.TEST_WITH_THREE_PLACEHOLDERS,
                "문자열 파라미터1",
                123456,
                new TestObject(),
                "추가 파라미터"
        );

        assertEquals("테스트 예외 메시지 문자열 파라미터1, 123456, TestObject 의 toString() 결과값", exception.getMessage());
    }


    enum TestExceptionType implements ExceptionType {
        TEST("테스트 예외 메시지"),
        TEST_WITH_ONE_PLACEHOLDER("테스트 예외 메시지 {}"),
        TEST_WITH_TWO_PLACEHOLDERS("테스트 예외 메시지 {}, {}"),
        TEST_WITH_THREE_PLACEHOLDERS("테스트 예외 메시지 {}, {}, {}")
        ;

        private final String message;


        TestExceptionType(String message) {
            this.message = message;
        }

        @Override
        public String getType() {
            return this.name();
        }

        @Override
        public String getMessage() {
            return this.message;
        }
    }

    static class TestObject {
        @Override
        public String toString() {
            return "TestObject 의 toString() 결과값";
        }
    }
}
