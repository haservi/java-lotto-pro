package lotto.view;

import lotto.domain.CustomLottoGenerator;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoMoney;

import java.util.Scanner;

public class InputView {

    private static final String ENTER_PURCHASE_AMOUNT = "구매금액을 입력해 주세요.";
    private static final String ENTER_LAST_WEEK_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ENTER_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String ENTER_CUSTOM_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String ENTER_CUSTOM_LOTTO_NUMBER = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String REMAIN_CUSTOM_LOTTO_COUNT = "[남은 수동 로또 입력 회수(%d/%d)]";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String ERROR_PREFIX = "[ERROR] %s";

    private InputView() {

    }

    public static LottoMoney getLottoPurchasePrice() {
        System.out.println(ENTER_PURCHASE_AMOUNT);
        return getLottoMoney();
    }

    private static LottoMoney getLottoMoney() {
        try {
            return new LottoMoney(SCANNER.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(String.format(ERROR_PREFIX, e.getMessage()));
            return getLottoMoney();
        }
    }

    public static int getCustomLottoCount(LottoMoney lottoMoney) {
        System.out.println();
        System.out.println(ENTER_CUSTOM_LOTTO_COUNT);
        return customPurchaseCount(lottoMoney);
    }

    private static int customPurchaseCount(LottoMoney lottoMoney) {
        try {
            return lottoMoney.getValidLottoPurchaseCount(SCANNER.nextLine());
        } catch (Exception e) {
            System.out.println(String.format(ERROR_PREFIX, e.getMessage()));
            return customPurchaseCount(lottoMoney);
        }
    }

    public static String getLastWeekWinningNumber() {
        System.out.println();
        System.out.println(ENTER_LAST_WEEK_WINNING_NUMBER);
        return SCANNER.nextLine();
    }

    public static int getBonusNumber() {
        System.out.println(ENTER_BONUS_NUMBER);
        return getNumber();
    }

    private static int getNumber() {
        int bonusNumber;
        try {
            bonusNumber = Integer.parseInt(SCANNER.nextLine());
        } catch (Exception e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
        return bonusNumber;
    }

    public static void customLottoNumberScript() {
        System.out.println();
        System.out.println(ENTER_CUSTOM_LOTTO_NUMBER);
    }


    public static LottoGenerator getCustomLottoNumbers(int count, int total) {
        try {
            LottoGenerator lottoGeneratorList = new CustomLottoGenerator(SCANNER.nextLine());
            return lottoGeneratorList;
        } catch (Exception e) {
            System.out.println(String.format(ERROR_PREFIX, e.getMessage() + String.format(REMAIN_CUSTOM_LOTTO_COUNT, count, total)));
            return getCustomLottoNumbers(count, total);
        }
    }
}
