package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import lotto.domain.strategy.GenerateStrategy;

public class LottoTicket {
	private static final Integer NUMBERS_COUNT = 6;
	private final Set<Integer> numbers;

	public LottoTicket(GenerateStrategy strategy) {
		this.numbers = strategy.generate();
		isNumbersSizeEqCount();
	}

	public List<Integer> getNumbers() {
		List<Integer> numberList = new ArrayList<>(numbers);
		Collections.sort(numberList);
		return Collections.unmodifiableList(numberList);
	}

	private void isNumbersSizeEqCount() {
		if (numbers.size()!= NUMBERS_COUNT) {
			throw new IllegalArgumentException("올바르지 않은 추첨 결과입니다.");
		}
	}
}
