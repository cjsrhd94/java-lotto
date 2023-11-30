package lotto.controller;
import java.util.List;
import java.util.Set;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.LottoChecker;
import lotto.domain.LottoPurchase;
import lotto.domain.Result;
import lotto.domain.strategy.AutoLottoGenerator;
import lotto.domain.util.StringSplitter;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {
    public static void main(String[] args) {
        LottoPurchase purchase = new LottoPurchase(InputView.inputTotalAmount(), InputView.inputManualQuantity());
        // 리팩토링 필요 부분
        List<LottoTicket> manualLottoTickets
            = StringSplitter.bulkConvertStrToLottoNumSet(InputView.inputManualNumbers(purchase.getManualQuantity()));
        List<LottoTicket> autoLottoTickets = new AutoLottoGenerator().generate(purchase.calcAutoQuantity());

        LottoTickets lottoTickets = new LottoTickets(manualLottoTickets).add(new LottoTickets(autoLottoTickets));

        ResultView.showHowManyBuyTicket(purchase);
        ResultView.showLottoTickets(lottoTickets.getLottoTickets());

        Set<LottoNumber> winningNums = StringSplitter.convertStrToLottoNumSet(InputView.inputWinningNumbers());

        Result result = new Result();
        ResultView.showResultStatics(
            result.aggregateResult(
                lottoTickets.checkTickets(new LottoChecker(winningNums, InputView.inputBonusNumber()))));
        ResultView.showReturnRate(result.calcReturnRate(purchase.getTotalAmount()));
    }
}
