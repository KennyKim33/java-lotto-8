package lotto.controller;

import lotto.domain.Bill;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import lotto.validator.InputValidator;
import lotto.view.InputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachineManager {
    private static final String DELIMITER = ",";

    private final InputView inputView;
    private final InputValidator inputValidator;
    private final InputHandler inputHandler;

    public LottoMachineManager(InputView inputView, InputValidator inputValidator) {
        this.inputView = inputView;
        this.inputValidator = inputValidator;
        this.inputHandler = new InputHandler();
    }

    public Bill createBill() {
        return inputHandler.retryUntilValid(() -> {
            String input = inputView.readPurchaseAmount();
            inputValidator.validateAmountInput(input);
            return new Bill(toInt(input));
        });
    }

    public WinningNumbers createWinningNumbers() {
        Lotto winningLotto = createWinningLotto();
        return inputHandler.retryUntilValid(() -> {
            BonusNumber bonusNumber = createBonusNumber();
            return new WinningNumbers(winningLotto, bonusNumber);
        });
    }

    private Lotto createWinningLotto() {
        return inputHandler.retryUntilValid(() -> {
            String input = inputView.readWinningNumbers();
            inputValidator.validateWinningNumbersInput(input);
            return new Lotto(parseNumbers(input));
        });
    }

    private BonusNumber createBonusNumber() {
        return inputHandler.retryUntilValid(() -> {
            String input = inputView.readBonusNumber();
            inputValidator.validateBonusNumberInput(input);
            return new BonusNumber(toInt(input));
        });
    }

    private List<Integer> parseNumbers(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private int toInt(String input) {
        return Integer.parseInt(input);
    }
}
