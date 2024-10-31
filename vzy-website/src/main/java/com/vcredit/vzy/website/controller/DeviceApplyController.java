package com.vcredit.vzy.website.controller;

import com.alibaba.fastjson.JSONObject;
import com.vcredit.vzy.common.dto.*;
import com.vcredit.vzy.common.enums.BizErrorEnum;
import com.vcredit.vzy.common.vo.DeviceApplyDetailVo;
import com.vcredit.vzy.common.vo.DeviceApplyVo;
import com.vcredit.vzy.service.device.DeviceApplyService;
import com.vcredit.vzy.website.vo.UserContext;
import com.vcredit.vzy.website.vo.UserVO;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/device")
public class DeviceApplyController {

    private final DeviceApplyService deviceApplyService;
    private final RedisTemplate<Object, Object> redisTemplate;

    @PostMapping("/apply/commit")
    public ApiResponse<String> applyCommit(@RequestBody DeviceApplyCommitDTO deviceApplyCommitDTO) {
        String lockKey = "device_apply_commit_".concat(String.valueOf(deviceApplyCommitDTO.getDeviceId()));
        String requestId = UUID.randomUUID().toString(); // 生成唯一的请求标识
        try {
            if (tryLock(lockKey, requestId, 15)) { // 尝试获取锁，超时时间15秒
                String userInfo = UserContext.getCurrentUserInfo();
                UserVO oauth2UserVO = JSONObject.parseObject(userInfo, UserVO.class);
                deviceApplyCommitDTO.setLoginUser(oauth2UserVO.getUserName());
                deviceApplyCommitDTO.setLoginUserAcct(oauth2UserVO.getUserAccount());
                deviceApplyService.applyCommit(deviceApplyCommitDTO);
                return ApiResponse.success("success");
            } else {
                return ApiResponse.error(BizErrorEnum.DEVICE_IS_CHARGE);
            }
        } catch (Exception e) {
            return ApiResponse.error("系统异常");
        } finally {
            releaseLock(lockKey);
        }
    }

    @GetMapping("/apply/pageQuery")
    public ApiResponse<Pagination<DeviceApplyVo>> applyPageQuery(DeviceApplyPageQueryDTO deviceApplyPageQueryDTO) {

        return ApiResponse.success(deviceApplyService.applyPageQuery(deviceApplyPageQueryDTO));
    }

    @PostMapping("/apply/approve")
    public ApiResponse<String> approve(@RequestBody DeviceApplyApproveDTO deviceApplyApproveDTO) {
        String lockKey = "device_apply_approve_".concat(String.valueOf(deviceApplyApproveDTO.getApplyId()));
        String requestId = UUID.randomUUID().toString(); // 生成唯一的请求标识
        try {
            if (tryLock(lockKey, requestId, 15)) { // 尝试获取锁，超时时间15秒
                String userInfo = UserContext.getCurrentUserInfo();
                UserVO oauth2UserVO = JSONObject.parseObject(userInfo, UserVO.class);
                deviceApplyApproveDTO.setLoginUser(oauth2UserVO.getUserName());
                deviceApplyApproveDTO.setLoginUserAcct(oauth2UserVO.getUserAccount());
                deviceApplyService.applyApprove(deviceApplyApproveDTO);
                return ApiResponse.success("success");
            } else {
                return ApiResponse.error(BizErrorEnum.DEVICE_IS_CHARGE);
            }
        } catch (Exception e) {
            return ApiResponse.error("系统异常");
        } finally {
            releaseLock(lockKey);
        }
    }

    @GetMapping("/apply/detail")
    public ApiResponse<DeviceApplyDetailVo> applyDetail(@RequestParam("applyId") Long applyId) {
        return ApiResponse.success(deviceApplyService.applyDetail(applyId));
    }

    /**
     * 尝试获取分布式锁
     *
     * @param lockKey    锁的键
     * @param requestId  请求标识
     * @param expireTime 超时时间，单位秒
     * @return 是否成功获取锁
     */
    public boolean tryLock(String lockKey, String requestId, long expireTime) {
        log.info("tryLock lockKey={}, requestId={}, expireTime={}", lockKey, requestId, expireTime);
        Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, requestId, expireTime, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(result);
    }

    /**
     * 释放锁
     *
     * @param lockKey 锁的键
     */
    public void releaseLock(String lockKey) {
        log.info("releaseLock lockKey={}", lockKey);
        boolean result = redisTemplate.opsForValue().get(lockKey) != null;
        if (result) {
            redisTemplate.delete(lockKey);
        }
    }

    @GetMapping("/apply/explain")
    public ApiResponse<String> applyExplain() {
        return ApiResponse.success(deviceApplyService.applyExplain());
    }

    @PostMapping("/apply/explain/commit")
    ApiResponse<String> explainCommit (@RequestBody DeviceApplyExplainDTO deviceApplyExplainDTO) {
        deviceApplyService.applyExplainCommit(deviceApplyExplainDTO);
        return ApiResponse.success("更新说明成功");
    }


}
