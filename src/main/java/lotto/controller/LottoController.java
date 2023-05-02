package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    public void run() {
        int purchaseAmount = InputView.inputPurchaseAmount();
        LottoTickets lottoTickets = LottoTickets.create(purchaseAmount);
        ResultView.printLottoTickets(lottoTickets);

        String winningNumbersString = InputView.inputWinningNumbers();

        LottoGame lottoGame = new LottoGame(lottoTickets, winningNumbersString);
        WinningStatistics winningStatistics = lottoGame.calculateWinningStatistics();
        double earningsRate = winningStatistics.calculateEarningsRate(lottoTickets.findLottoTicketCount());

        ResultView.printLottoResult(winningStatistics, earningsRate);
    }
}

