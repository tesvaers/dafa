package pers.dafacloud.testCase.user;

import org.testng.Assert;
import org.testng.annotations.Test;
import pers.dafacloud.utils.enums.Param;
import pers.dafacloud.utils.enums.Path;
import pers.dafacloud.utils.httpUtils.Request;
import org.testng.Reporter;

public class UserManage {
    Path path = Path.userManage;
    Param param = Param.userManage;

    @Test(priority = 1,description = "获取下级wesley1son的信息")
    public void testUserManage(){
        String url = path.value + param.params;
        System.out.println(url);
        String s = Request.doGet(url);
        Reporter.log(s);
        Assert.assertEquals(true,s.contains("获取成功"),"获取下级信息失败");
        System.out.println("获取下级wesley1son的信息成功");
    }
}