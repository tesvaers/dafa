package pers.testDao;

import org.apache.ibatis.session.SqlSession;
import pers.dafacloud.mapper.gameBettingInfo.GameBettingInfoMapper;
import pers.dafacloud.utils.SqlSessionFactoryUtils;
import pers.utils.fileUtils.FileUtil;

import java.util.List;
import java.util.Map;

public class OpenNumGame {


    public static void main(String[] args) {
        gameOpenNum();
    }


    /**
     * 线上棋牌下注记录，导入到数据库
     */
    public static void gameOpenNum() {
        SqlSession sqlSessionTransaction = SqlSessionFactoryUtils.openSqlSession("lotteryGame");
        GameBettingInfoMapper gameBetingInfoMapper = sqlSessionTransaction.getMapper(GameBettingInfoMapper.class);

        SqlSession sqlSessionTransaction2 = SqlSessionFactoryUtils.openSqlSession("ali");
        GameBettingInfoMapper gameBetingInfoMapper2 = sqlSessionTransaction2.getMapper(GameBettingInfoMapper.class);

        getInsetGameOpenNum(gameBetingInfoMapper, gameBetingInfoMapper2);
    }

    /**
     * 棋牌下注记录，先查询线上数据再写入测试库
     */
    public static void getInsetGameOpenNum(GameBettingInfoMapper gameBetingInfoMapper,
                                           GameBettingInfoMapper gameBetingInfoMapper2) {

        String maxId = "0";
        for (int j = 0; j < 10000; j++) {
            System.out.println("maxId :" + maxId);
            List<Map> list = gameBetingInfoMapper.getGameOpenNum(maxId);
            System.out.print("查询数据量" + list.size());
            if (list.size() == 0) {
                break;
            }
            if (list.size() < 10000) {
                int result = gameBetingInfoMapper2.insertGameOpenNum(list);
                System.out.println(" - 写入尾数量：" + result);
            } else {
                int result = gameBetingInfoMapper2.insertGameOpenNum(list);
                System.out.println(" - 写入数据量：" + result);
            }
            maxId = list.get(list.size() - 1).get("id").toString();
            list.clear();
        }


    }


}
