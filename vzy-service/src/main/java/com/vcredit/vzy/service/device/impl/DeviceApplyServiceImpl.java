package com.vcredit.vzy.service.device.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vcredit.vzy.common.dto.*;
import com.vcredit.vzy.common.enums.ApplyStatusEnum;
import com.vcredit.vzy.common.enums.ApplyTypeEnum;
import com.vcredit.vzy.common.enums.BorrowTermEnum;
import com.vcredit.vzy.common.enums.DeviceStatusEnum;
import com.vcredit.vzy.common.enums.OperPersonEnum;
import com.vcredit.vzy.common.utils.Constants;
import com.vcredit.vzy.common.vo.DeviceApplyDetailVo;
import com.vcredit.vzy.common.vo.DeviceApplyVo;
import com.vcredit.vzy.dao.entity.DeviceApplyDO;
import com.vcredit.vzy.dao.entity.DeviceDO;
import com.vcredit.vzy.dao.entity.DeviceExplainDo;
import com.vcredit.vzy.dao.entity.DeviceLogDO;
import com.vcredit.vzy.dao.mapper.DeviceApplyExplainMapper;
import com.vcredit.vzy.dao.mapper.DeviceApplyMapper;
import com.vcredit.vzy.dao.repository.DeviceApplyRepository;
import com.vcredit.vzy.dao.repository.DeviceLogRepository;
import com.vcredit.vzy.dao.repository.DeviceRepository;
import com.vcredit.vzy.dao.util.PageUtil;
import com.vcredit.vzy.service.device.DeviceApplyService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceApplyServiceImpl implements DeviceApplyService {

    private final DeviceApplyRepository deviceApplyRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceLogRepository deviceLogRepository;
    private final DeviceApplyExplainMapper deviceApplyExplainMapper;
    private final DeviceApplyMapper deviceApplyMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyCommit(DeviceApplyCommitDTO deviceApplyCommitDTO) {
        log.info("deviceApplyCommitDTO:{}", deviceApplyCommitDTO);

        DeviceApplyDO applyDO = new DeviceApplyDO();
        applyDO.setDeviceId(deviceApplyCommitDTO.getDeviceId());
        applyDO.setApplyType(deviceApplyCommitDTO.getApplyType());
        applyDO.setApplyTime(LocalDateTime.now());
        applyDO.setApplyStatus(ApplyStatusEnum.WAIT_AUDIT.getCode());
        applyDO.setApplyPeriod(deviceApplyCommitDTO.getApplyPeriod());
        applyDO.setApplyPerson(deviceApplyCommitDTO.getLoginUser());
        applyDO.setApplyPersonAcct(deviceApplyCommitDTO.getLoginUserAcct());
        applyDO.setDelFlag(Constants.N);
        applyDO.setCreatedBy(deviceApplyCommitDTO.getLoginUserAcct());
        applyDO.setCreatedTime(LocalDateTime.now());
        applyDO.setUpdatedBy(deviceApplyCommitDTO.getLoginUserAcct());
        applyDO.setUpdatedTime(LocalDateTime.now());
        applyDO.setApplyReason(deviceApplyCommitDTO.getApplyReason());
        //deviceApplyRepository.save(applyDO);
        try{
            deviceApplyMapper.insert(applyDO);
        }catch (Exception e) {
            log.info(e.getMessage());
        }

        //更新状态为申请中
        deviceRepository.deviceStatusUpdate(deviceApplyCommitDTO.getDeviceId(), DeviceStatusEnum.APPLYING.getCode(),
            deviceApplyCommitDTO.getLoginUserAcct());
    }

    @Override
    public Pagination<DeviceApplyVo> applyPageQuery(DeviceApplyPageQueryDTO queryDTO) {
        if (StringUtils.isNotBlank(queryDTO.getApplyTimeEnd())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // 将String转换为LocalDate
            LocalDate date = LocalDate.parse(queryDTO.getApplyTimeEnd(), formatter);
            // 加一天
            LocalDate nextDay = date.plusDays(1);
            // 将LocalDate转换回String
            String nextDayStr = nextDay.format(formatter);
            queryDTO.setApplyTimeEnd(nextDayStr);
        }
        IPage<DeviceApplyVo> tempResult = deviceApplyRepository.applyPageQuery(queryDTO);
        return PageUtil.convertPagination(tempResult);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void applyApprove(DeviceApplyApproveDTO deviceApplyApproveDTO) {
        DeviceApplyDO applyDO = deviceApplyRepository.getById(deviceApplyApproveDTO.getApplyId());
        if (applyDO == null) {
            log.warn("申请单不存在,applyId:{}", deviceApplyApproveDTO.getApplyId());
            return;
        }
        applyDO.setApplyStatus(deviceApplyApproveDTO.getApproveResult());
        applyDO.setApproveResult(deviceApplyApproveDTO.getApproveResult());
        applyDO.setApprovePerson(deviceApplyApproveDTO.getLoginUser());
        applyDO.setApprovePersonAcct(deviceApplyApproveDTO.getLoginUserAcct());
        applyDO.setApproveTime(LocalDateTime.now());
        applyDO.setApproveReason(deviceApplyApproveDTO.getApproveReason());
        applyDO.setUpdatedBy(deviceApplyApproveDTO.getLoginUserAcct());
        applyDO.setApplyReason(deviceApplyApproveDTO.getApplyReason());
        applyDO.setUpdatedTime(LocalDateTime.now());

        DeviceDO deviceDO = deviceRepository.getById(applyDO.getDeviceId());
        DeviceLogDO deviceLogDO = new DeviceLogDO();
        deviceLogDO.setDeviceId(deviceDO.getId());
        deviceLogDO.setDeviceNo(deviceDO.getDeviceNo());
        deviceLogDO.setDeviceName(deviceDO.getDeviceName());
        deviceLogDO.setOperPerson(OperPersonEnum.SYSTEM.getName());
        deviceLogDO.setOperPersonAcct(OperPersonEnum.SYSTEM.getCode());
        deviceLogDO.setOperTime(LocalDateTime.now());
        deviceLogDO.setDelFlag(Constants.N);
        deviceLogDO.setCreatedTime(LocalDateTime.now());
        deviceLogDO.setCreatedBy(OperPersonEnum.SYSTEM.getCode());
        deviceLogDO.setUpdatedBy(OperPersonEnum.SYSTEM.getCode());
        deviceLogDO.setUpdatedTime(LocalDateTime.now());
        //审批通过后，修改设备状态
        if (ApplyStatusEnum.APPROVE_PASS.getCode().equals(deviceApplyApproveDTO.getApproveResult())) {
            //借用
            if (ApplyTypeEnum.BORROW.getCode().equals(applyDO.getApplyType())) {
                deviceDO.setDeviceStatus(DeviceStatusEnum.USING.getCode());
                deviceDO.setDeviceCharge(applyDO.getApplyPerson());
                deviceDO.setDeviceChargeAcct(applyDO.getApplyPersonAcct());
                BorrowTermEnum borrowTermEnum = BorrowTermEnum.getBorrowTermEnum(applyDO.getApplyPeriod());
                if (Objects.isNull(borrowTermEnum)) {
                    log.warn("借用期限不存在,applyId:{}", deviceApplyApproveDTO.getApplyId());
                    return;
                }
                if ("day".equals(borrowTermEnum.getUnit())) {
                    deviceDO.setExpireDate(LocalDate.now().plusDays(borrowTermEnum.getNum()));
                }
                if ("month".equals(borrowTermEnum.getUnit())) {
                    deviceDO.setExpireDate(LocalDate.now().plusMonths(borrowTermEnum.getNum()));
                }
                deviceLogDO.setOperDetail("借用申请审核通过<br/>申请人：".concat(applyDO.getApplyPerson()).concat("<br/>借用期限：")
                    .concat(BorrowTermEnum.getNameByCode(applyDO.getApplyPeriod())).concat("<br/>审核人：").concat(applyDO.getApprovePerson()));
            }
            //归还
            if (ApplyTypeEnum.RETURN.getCode().equals(applyDO.getApplyType())) {
                deviceDO.setDeviceStatus(DeviceStatusEnum.WAIT_APPLY.getCode());
                deviceDO.setDeviceCharge(applyDO.getApprovePerson());
                deviceDO.setDeviceChargeAcct(applyDO.getApprovePersonAcct());
                deviceDO.setExpireDate(null);
                deviceLogDO.setOperDetail("归还申请审核通过<br/>申请人：".concat(applyDO.getApplyPerson())
                    .concat("<br/>审核人：").concat(applyDO.getApprovePerson()));
            }
        }
        //审批拒绝后，修改设备状态
        if (ApplyStatusEnum.APPROVE_REFUSE.getCode().equals(deviceApplyApproveDTO.getApproveResult())) {
            //借用
            if (ApplyTypeEnum.BORROW.getCode().equals(applyDO.getApplyType())) {
                deviceDO.setDeviceStatus(DeviceStatusEnum.WAIT_APPLY.getCode());
                deviceLogDO.setOperDetail("借用申请审核拒绝<br/>申请人：".concat(applyDO.getApplyPerson()).concat("<br/>借用期限：")
                    .concat(BorrowTermEnum.getNameByCode(applyDO.getApplyPeriod())).concat("<br/>审核人：").concat(applyDO.getApprovePerson()));
            }
            //归还
            if (ApplyTypeEnum.RETURN.getCode().equals(applyDO.getApplyType())) {
                deviceDO.setDeviceStatus(DeviceStatusEnum.USING.getCode());
                deviceLogDO.setOperDetail("归还申请审核拒绝<br/>申请人：".concat(applyDO.getApplyPerson())
                    .concat("<br/>审核人：").concat(applyDO.getApprovePerson()));
            }
        }
        deviceApplyRepository.updateById(applyDO);
        deviceDO.setUpdatedBy(deviceApplyApproveDTO.getLoginUserAcct());
        deviceRepository.deviceApprove(deviceDO);
        deviceLogRepository.save(deviceLogDO);

    }

    @Override
    public DeviceApplyDetailVo applyDetail(Long applyId) {
        return deviceApplyRepository.applyDetail(applyId);
    }

    @Override
    public String applyExplain() {
        LambdaQueryWrapper<DeviceExplainDo> lambdaQueryWrapper =new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DeviceExplainDo::getDeletedFlag,0);
        List<DeviceExplainDo> deviceExplainDo=deviceApplyExplainMapper.selectList(lambdaQueryWrapper);
        if(null!=deviceExplainDo) {
            return deviceExplainDo.get(0).getExplainContent();
        }
        return "";
    }

    @Override
    public void applyExplainCommit(DeviceApplyExplainDTO deviceApplyExplainDTO) {
        LambdaQueryWrapper<DeviceExplainDo> lambdaQueryWrapper =new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DeviceExplainDo::getDeletedFlag,0);
        List<DeviceExplainDo> deviceExplainDo=deviceApplyExplainMapper.selectList(lambdaQueryWrapper);
        if(null==deviceExplainDo || deviceExplainDo.size()==0) {
            DeviceExplainDo deviceExplainDo1 =new DeviceExplainDo();
            deviceExplainDo1.setExplainContent(deviceApplyExplainDTO.getContent());
            deviceApplyExplainMapper.insert(deviceExplainDo1);
        } else {
            //更新
            for(DeviceExplainDo deviceExplainDo1:deviceExplainDo) {
                DeviceExplainDo deviceExplainDo2 =new DeviceExplainDo();
                deviceExplainDo1.setExplainContent(deviceApplyExplainDTO.getContent());
                deviceApplyExplainMapper.updateById(deviceExplainDo1);
            }
        }
    }


}
