package lotto.factory;

import lotto.controller.LottoController;
import lotto.controller.LottoMachineManager;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoNumberGenerator;
import lotto.service.LottoMachine;
import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoControllerFactory {
    public static LottoController create() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        InputValidator inputValidator = new InputValidator();

        LottoMachineManager manager = new LottoMachineManager(inputView, inputValidator);

        LottoNumberGenerator numberGenerator = new LottoNumberGenerator();
        LottoGenerator lottoGenerator = new LottoGenerator(numberGenerator);
        LottoMachine lottoMachine = new LottoMachine(lottoGenerator);

        return new LottoController(manager, lottoMachine, outputView);
    }
}
