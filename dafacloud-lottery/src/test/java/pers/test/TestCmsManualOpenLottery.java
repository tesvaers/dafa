package pers.test;

import org.apache.http.Header;
import org.testng.annotations.Test;
import pers.dafacloud.constant.Lottery;
import pers.utils.dafaRequest.DafaRequest;
import pers.utils.httpclientUtils.HttpConfig;
import pers.utils.httpclientUtils.HttpCookies;
import pers.utils.httpclientUtils.HttpHeader;
import pers.utils.urlUtils.UrlBuilder;

public class TestCmsManualOpenLottery {

    private static String host = "http://pt02.dafacloud-test.com";

    private static Header[] headers = HttpHeader.custom()
            .contentType("application/x-www-form-urlencoded;charset=UTF-8")
            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
            .build();

    private static HttpConfig httpConfig = HttpConfig.custom()
            .headers(headers)
            .context(HttpCookies
                    .custom()
                    .setBasicClientCookie(host, "JSESSIONID", "BDE3B7AB9E46F22D175B874DAABB53C6")
                    .getContext());

    @Test(description = "手动开奖")
    public static void test01() {
        String manualOpen = host + "/v1/lottery/manualOpen";
        String lotteryName = "幸运飞艇";
        String body = UrlBuilder.custom()
                .addBuilder("lotteryCode", Lottery.getLotteryCodebyName(lotteryName))
                .addBuilder("lotteryName", lotteryName)
                .addBuilder("issue", "20200525030")
                .addBuilder("openNumber", "10,09,08,07,06,05,04,03,02,01")
                .addBuilder("remark", "测试环境测试")
                .fullBody();
        String result = DafaRequest.post(httpConfig.url(manualOpen).body(body));
        System.out.println(result);
    }

    @Test(description = "开奖纠错")
    public static void test02() {
        String updateOpenNumber = host + "/v1/lottery/updateOpenNumber";
        String lotteryName = "幸运飞艇";
        String body = UrlBuilder.custom()
                .addBuilder("lotteryCode", Lottery.getLotteryCodebyName(lotteryName))
                .addBuilder("lotteryName", lotteryName)
                .addBuilder("issue", "20200525056")
                .addBuilder("validateIssue", "20200525056")
                .addBuilder("openNumber", "01,02,03,04,05,06,07,08,09,10")
                .addBuilder("validateOpenNumber", "01,02,03,04,05,06,07,08,09,10")
                .addBuilder("reAward", "true")
                .addBuilder("reStat", "true")

                .addBuilder("remark", "测试环境测试")
                .fullBody();
        String result = DafaRequest.post(httpConfig.url(updateOpenNumber).body(body));
        System.out.println(result);

    }

}