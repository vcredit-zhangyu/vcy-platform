package com.vcredit.vzy.website;

import com.vcredit.vcs.framework.base.BaseApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/16
 */
@SpringBootApplication(scanBasePackages = "com.vcredit")
@EnableDiscoveryClient
@MapperScan("com.vcredit.vzy.dao.mapper")
public class VzyWebsiteApplication extends BaseApplication {

    public static void main(String[] args) {
        run(VzyWebsiteApplication.class, args);
    }

}
