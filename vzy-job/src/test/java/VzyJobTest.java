import com.vcredit.genc.dingtalk.message.RobotCustomMessage;
import com.vcredit.genc.dingtalk.utils.DingTalkUtil;
import com.vcredit.vzy.job.VzyJobApplication;
import com.vcredit.vzy.job.job.DeviceBorrowExpireRemindJob;
import com.vcredit.vzy.job.job.DeviceExpireJob;
import com.vcredit.vzy.job.job.DeviceUpdateRemindJob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/5/13
 */
@Slf4j
@SpringBootTest(classes = VzyJobApplication.class)
public class VzyJobTest {

    @Autowired
    private DeviceBorrowExpireRemindJob deviceBorrowExpireRemindJob;

    @Autowired
    private DeviceUpdateRemindJob deviceUpdateRemindJob;

    @Autowired
    private DeviceExpireJob deviceExpireJob;

    @Test
    void dingTest() {
        String token = "21f519828528881836f2bbddba744101e0d10f70cd655fa0787d44889c383826";
        String title = "设备提醒";
        //String content = "@16621238846 <br/>设备更新时间距离当前时间大于3个月";
        List<String> notifyMobiles = new ArrayList<>(Arrays.asList("16621238846".split(",")));

        String atAdmin = notifyMobiles.stream().map(s -> "@" + s).collect(Collectors.joining(" "));
        String content = atAdmin + " <br/> 以下设备更新时间距离当前时间大于请及时更新设备信息：<br/>";

        // 拼接参数
        RobotCustomMessage message = RobotCustomMessage.builder().accessToken(token).msgType("markdown").notifyMobiles(notifyMobiles)
            .markdown(RobotCustomMessage.Markdown.builder().title(title).text(content).build()).build();

        //自定义机器人发送群消息
        DingTalkUtil.sendCustomRobotMessage(message);
    }

    @Test
    void deviceBorrowExpireRemindJobTest() {
        deviceBorrowExpireRemindJob.execute(null);
    }

    @Test
    void deviceUpdateRemindJobTest() {
        deviceUpdateRemindJob.execute(null);
    }


    @Test
    void deviceExpireJobTest() {
        deviceExpireJob.execute(null);
    }

}
