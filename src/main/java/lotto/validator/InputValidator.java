package lotto.validator;

import lotto.exception.ErrorMessage;

public class InputValidator {

    public void validateAmountInput(String input) {
        validateNotEmpty(input);
        validateNumeric(input);
    }

    public void validateWinningNumbersInput(String input) {
        validateNotEmpty(input);
        validateWinningNumbersFormat(input);
    }

    public void validateBonusNumberInput(String input) {
        validateNotEmpty(input);
        validateNumeric(input);
    }

    private void validateNumeric(String input) {
        if (!input.matches("\\d+")) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NUMERIC.getMessage());
        }
    }

    private void validateNotEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_EMPTY.getMessage());
        }
    }

    private void validateWinningNumbersFormat(String input) {
        if (!input.matches("^\\d+(,\\d+)*$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_FORMAT.getMessage());
        }
    }
}
