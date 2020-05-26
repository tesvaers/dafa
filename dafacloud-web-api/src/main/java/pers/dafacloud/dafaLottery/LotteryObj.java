package pers.dafacloud.dafaLottery;

import lombok.Data;

@Data
public class LotteryObj {
    //lotteryCode，文件名，用户数量，下单间隔
    private String lotteryCode;
    private int userCount;//用户数量
    private int bettingStepTime;//下注间隔

    public LotteryObj(String lotteryCode, int userCount, int bettingStepTime) {
        this.lotteryCode = lotteryCode;
        this.userCount = userCount;
        this.bettingStepTime = bettingStepTime;
    }

}
