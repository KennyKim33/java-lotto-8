package lotto.validator;

import lotto.exception.ErrorMessage;

public interface Validator {

    default void validateNotEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_EMPTY.getMessage());
        }
    }

    void validate(String input);
}
