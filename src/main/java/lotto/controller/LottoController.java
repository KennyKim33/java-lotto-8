package lotto.controller;

import lotto.domain.Bill;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import lotto.dto.LottoResult;
import lotto.service.LottoMachine;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {
    private final LottoMachineManager lottoMachineManager;
    private final LottoMachine lottoMachine;
    private final OutputView outputView;

    public LottoController(
            LottoMachineManager lottoMachineManager,
            LottoMachine lottoMachine,
            OutputView outputView) {
        this.lottoMachineManager = lottoMachineManager;
        this.lottoMachine = lottoMachine;
        this.outputView = outputView;
    }

    public void run() {
        Bill bill = lottoMachineManager.createBill();
        List<Lotto> tickets = lottoMachine.generateLottos(bill);
        outputView.printPurchasedTickets(tickets, bill.getTicketCount());

        WinningNumbers winningNumbers = lottoMachineManager.createWinningNumbers();
        LottoResult result = lottoMachine.calculateResult(tickets, winningNumbers, bill);
        outputView.printResult(result);
    }
}
