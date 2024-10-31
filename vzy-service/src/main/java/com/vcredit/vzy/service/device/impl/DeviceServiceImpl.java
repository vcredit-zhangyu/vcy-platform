package com.vcredit.vzy.service.device.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.protobuf.ServiceException;
import com.vcredit.vzy.common.dto.*;
import com.vcredit.vzy.common.enums.*;
import com.vcredit.vzy.common.utils.CompareUtil;
import com.vcredit.vzy.common.utils.Constants;
import com.vcredit.vzy.common.vo.*;
import com.vcredit.vzy.dao.entity.*;
import com.vcredit.vzy.dao.mapper.DeviceFeedbackMapper;
import com.vcredit.vzy.dao.repository.DeviceDetailRepository;
import com.vcredit.vzy.dao.repository.DeviceLogRepository;
import com.vcredit.vzy.dao.repository.DeviceRepository;
import com.vcredit.vzy.dao.util.PageUtil;
import com.vcredit.vzy.service.device.DeviceService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
@Slf4j
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceDetailRepository deviceDetailRepository;
    private final DeviceLogRepository deviceLogRepository;
    private final DeviceFeedbackMapper deviceFeedbackMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deviceInfoCommit(DeviceInfoCommitDTO deviceInfo) {
        DeviceDO existDO = deviceRepository.queryByDeviceNo(deviceInfo.getDeviceNo());
        if (Objects.nonNull(existDO)) {
            return BizErrorEnum.DEVICE_EXIST.getErrorMsg();
        }
        DeviceDO deviceDO = new DeviceDO();
        deviceDO.setCreatedTime(LocalDateTime.now());
        deviceDO.setCreatedBy(deviceInfo.getLoginUserAcct());
        deviceDO.setUpdatedBy(deviceInfo.getLoginUserAcct());
        deviceDO.setUpdatedTime(LocalDateTime.now());
        deviceDO.setDeviceNo(StringUtils.isNotBlank(deviceInfo.getDeviceImei()) ? deviceInfo.getDeviceImei() : deviceInfo.getDeviceSerialNo());
        deviceDO.setDeviceName(deviceInfo.getDeviceName());
        deviceDO.setDeviceStatus(DeviceStatusEnum.WAIT_APPLY.getCode());
        deviceDO.setDeviceCharge(deviceInfo.getLoginUser());
        deviceDO.setDeviceChargeAcct(deviceInfo.getLoginUserAcct());
        deviceDO.setDelFlag(Constants.N);
        deviceRepository.save(deviceDO);
        DeviceDetailDO deviceDetailDO = new DeviceDetailDO();
        deviceDetailDO.setDeviceId(deviceDO.getId());
        deviceDetailDO.setDeviceType(deviceInfo.getDeviceType());
        deviceDetailDO.setDeviceModel(deviceInfo.getDeviceModel());
        deviceDetailDO.setDeviceBrand(deviceInfo.getDeviceBrand());
        deviceDetailDO.setDeviceSystem(deviceInfo.getDeviceSystem());
        deviceDetailDO.setDeviceSystemVersion(deviceInfo.getDeviceSystemVersion());
        deviceDetailDO.setDeviceLoginUser(deviceInfo.getDeviceLoginUser());
        deviceDetailDO.setDeviceLoginKey(deviceInfo.getDeviceLoginKey());
        deviceDetailDO.setDeviceImei(deviceInfo.getDeviceImei());
        deviceDetailDO.setDeviceSerialNo(deviceInfo.getDeviceSerialNo());
        deviceDetailDO.setDeviceResolution(deviceInfo.getDeviceResolution());
        deviceDetailDO.setDeviceColor(deviceInfo.getDeviceColor());
        deviceDetailDO.setDeviceSize(deviceInfo.getDeviceSize());
        deviceDetailDO.setDeviceAccessory(deviceInfo.getDeviceAccessory());
        deviceDetailDO.setDeviceRemark(deviceInfo.getDeviceRemark());
        deviceDetailDO.setDelFlag(Constants.N);
        deviceDetailDO.setCreatedTime(LocalDateTime.now());
        deviceDetailDO.setCreatedBy(deviceInfo.getLoginUserAcct());
        deviceDetailDO.setUpdatedBy(deviceInfo.getLoginUserAcct());
        deviceDetailDO.setUpdatedTime(LocalDateTime.now());
        deviceDetailRepository.save(deviceDetailDO);
        //记录日志
        DeviceLogDO deviceLogDO = new DeviceLogDO();
        deviceLogDO.setDeviceId(deviceDO.getId());
        deviceLogDO.setDeviceNo(deviceDO.getDeviceNo());
        deviceLogDO.setDeviceName(deviceDO.getDeviceName());
        deviceLogDO.setOperPerson(deviceInfo.getLoginUser());
        deviceLogDO.setOperPersonAcct(deviceInfo.getLoginUserAcct());
        deviceLogDO.setOperTime(LocalDateTime.now());
        deviceLogDO.setOperDetail("设备初始化新增");
        deviceLogDO.setDelFlag(Constants.N);
        deviceLogDO.setCreatedTime(LocalDateTime.now());
        deviceLogDO.setCreatedBy(OperPersonEnum.ADMIN.getCode());
        deviceLogDO.setUpdatedBy(OperPersonEnum.ADMIN.getCode());
        deviceLogDO.setUpdatedTime(LocalDateTime.now());
        deviceLogRepository.save(deviceLogDO);
        return StringUtils.EMPTY;
    }

    @Override
    public DeviceInfoVo queryByDeviceNo(String deviceNo) {
        DeviceDO deviceDO = deviceRepository.queryByDeviceNo(deviceNo);
        if (Objects.isNull(deviceDO)) {
            return null;
        }
        DeviceInfoVo deviceInfoVo = new DeviceInfoVo();
        DeviceDetailDO deviceDetailDO = deviceDetailRepository.queryByDeviceId(deviceDO.getId());
        deviceInfoVo.setId(deviceDO.getId());
        deviceInfoVo.setDeviceNo(deviceDO.getDeviceNo());
        deviceInfoVo.setDeviceCharge(deviceDO.getDeviceCharge());
        deviceInfoVo.setExpireDate(localDate2String(deviceDO.getExpireDate()));
        deviceInfoVo.setDeviceStatus(DeviceStatusEnum.getNameByCode(deviceDO.getDeviceStatus()));
        deviceInfoVo.setDeviceType(DeviceTypeEnum.getNameByCode(deviceDetailDO.getDeviceType()));
        deviceInfoVo.setDeviceName(deviceDO.getDeviceName());
        deviceInfoVo.setDeviceModel(deviceDetailDO.getDeviceModel());
        deviceInfoVo.setDeviceSystem(deviceDetailDO.getDeviceSystem());
        deviceInfoVo.setDeviceSystemVersion(deviceDetailDO.getDeviceSystemVersion());
        deviceInfoVo.setDeviceResolution(deviceDetailDO.getDeviceResolution());
        return deviceInfoVo;
    }

    @Override
    public List<EnumsResponseVo> enumsQuery() {
        List<EnumsResponseVo> result = new ArrayList<>();

        EnumsResponseVo deviceType = new EnumsResponseVo();
        deviceType.setEnumCode("deviceType");
        deviceType.setEnumList(DeviceTypeEnum.getCodeList());
        result.add(deviceType);

        EnumsResponseVo deviceStatus = new EnumsResponseVo();
        deviceStatus.setEnumCode("deviceStatus");
        deviceStatus.setEnumList(DeviceStatusEnum.getCodeList());
        result.add(deviceStatus);

        EnumsResponseVo applyType = new EnumsResponseVo();
        applyType.setEnumCode("applyType");
        applyType.setEnumList(ApplyTypeEnum.getCodeList());
        result.add(applyType);

        EnumsResponseVo applyStatus = new EnumsResponseVo();
        applyStatus.setEnumCode("applyStatus");
        applyStatus.setEnumList(ApplyStatusEnum.getCodeList());
        result.add(applyStatus);

        EnumsResponseVo approveResult = new EnumsResponseVo();
        approveResult.setEnumCode("approveResult");
        approveResult.setEnumList(ApproveResultEnum.getCodeList());
        result.add(approveResult);

        EnumsResponseVo approveReason = new EnumsResponseVo();
        approveReason.setEnumCode("approveReason");
        approveReason.setEnumList(ApproveReasonEnum.getCodeList());
        result.add(approveReason);

        EnumsResponseVo app = new EnumsResponseVo();
        app.setEnumCode("app");
        app.setEnumList(AppEnum.getCodeList());
        result.add(app);

        EnumsResponseVo operPerson = new EnumsResponseVo();
        operPerson.setEnumCode("operPerson");
        operPerson.setEnumList(OperPersonEnum.getCodeList());
        result.add(operPerson);

        EnumsResponseVo borrowTerm = new EnumsResponseVo();
        borrowTerm.setEnumCode("borrowTerm");
        borrowTerm.setEnumList(BorrowTermEnum.getCodeList());
        result.add(borrowTerm);

        EnumsResponseVo brand = new EnumsResponseVo();
        brand.setEnumCode("brand");
        brand.setEnumList(BrandEnum.getCodeList());
        result.add(brand);

        EnumsResponseVo iosBrand = new EnumsResponseVo();
        iosBrand.setEnumCode("iosBrand");
        iosBrand.setEnumList(IosBrandEnum.getCodeList());
        result.add(iosBrand);

        EnumsResponseVo iosSys = new EnumsResponseVo();
        iosSys.setEnumCode("iosSystem");
        iosSys.setEnumList(IosSystemEnum.getCodeList());
        result.add(iosSys);

        EnumsResponseVo androidSys = new EnumsResponseVo();
        androidSys.setEnumCode("androidSystem");
        androidSys.setEnumList(AndroidSystemEnum.getCodeList());
        result.add(androidSys);

        EnumsResponseVo handleResults = new EnumsResponseVo();
        handleResults.setEnumCode("handleResults");
        handleResults.setEnumList(HandleResultEnum.getCodeList());
        result.add(handleResults);

        return result;
    }

    @Override
    public Pagination<DevicePageVo> deviceInfoPageQuery(DevicePageQueryDTO devicePageQueryDTO) {
        if (StringUtils.isNotBlank(devicePageQueryDTO.getDeviceType())) {
            devicePageQueryDTO.setDeviceTypeList(Arrays.asList(devicePageQueryDTO.getDeviceType().split(",")));
        }
        if (StringUtils.isNotBlank(devicePageQueryDTO.getDeviceStatus())) {
            devicePageQueryDTO.setDeviceStatusList(Arrays.asList(devicePageQueryDTO.getDeviceStatus().split(",")));
        }
        IPage<DevicePageVo> tempResult = deviceRepository.deviceInfoPageQuery(devicePageQueryDTO);
        return PageUtil.convertPagination(tempResult);
    }

    @Override
    public DeviceDetailVo deviceInfoDetail(Long deviceId) {
        return deviceRepository.deviceInfoDetail(deviceId);
    }

    @Override
    public void deviceStatusUpdate(DeviceInfoDTO deviceInfoDTO) {
        //变更类型为废弃
        if (Objects.equals("void", deviceInfoDTO.getUpdateType())) {
            DeviceDO deviceDO = deviceRepository.getById(deviceInfoDTO.getDeviceId());
            if (Objects.isNull(deviceDO)){
                return;
            }
            deviceRepository.deviceStatusUpdate(deviceInfoDTO.getDeviceId(), DeviceStatusEnum.ABANDONED.getCode(), deviceInfoDTO.getLoginUserAcct());
            //记录日志
            DeviceLogDO deviceLogDO = new DeviceLogDO();
            deviceLogDO.setDeviceId(deviceDO.getId());
            deviceLogDO.setDeviceNo(deviceDO.getDeviceNo());
            deviceLogDO.setDeviceName(deviceDO.getDeviceName());
            deviceLogDO.setOperPerson(deviceInfoDTO.getLoginUser());
            deviceLogDO.setOperPersonAcct(deviceInfoDTO.getLoginUserAcct());
            deviceLogDO.setOperTime(LocalDateTime.now());
            deviceLogDO.setOperDetail("设备废弃");
            deviceLogDO.setDelFlag(Constants.N);
            deviceLogDO.setCreatedTime(LocalDateTime.now());
            deviceLogDO.setCreatedBy(OperPersonEnum.ADMIN.getCode());
            deviceLogDO.setUpdatedBy(OperPersonEnum.ADMIN.getCode());
            deviceLogDO.setUpdatedTime(LocalDateTime.now());
            deviceLogRepository.save(deviceLogDO);
        }
    }

    @Override
    public void deviceInfoUpdate(DeviceInfoDTO deviceInfoDTO) {
        if (Objects.nonNull(deviceInfoDTO.getDeviceId())) {
            DeviceDO existDeviceDO = deviceRepository.getById(deviceInfoDTO.getDeviceId());
            // 比对设备信息是否有变更
            DeviceDO deviceDO = new DeviceDO();
            deviceDO.setId(deviceInfoDTO.getDeviceId());
            deviceDO.setDeviceName(deviceInfoDTO.getDeviceName());
            deviceDO.setDeviceCharge(deviceInfoDTO.getDeviceCharge());
            deviceDO.setDeviceChargeAcct(deviceInfoDTO.getDeviceChargeAcct());
            deviceDO.setDeviceNo(
                StringUtils.isNotBlank(deviceInfoDTO.getDeviceImei()) ? deviceInfoDTO.getDeviceImei() : deviceInfoDTO.getDeviceSerialNo());
            deviceDO.setExpireDate(string2LocalDate(deviceInfoDTO.getExpireDate()));
            deviceDO.setUpdatedBy(deviceInfoDTO.getLoginUserAcct());
            deviceDO.setUpdatedTime(LocalDateTime.now());
            String deviceChangeContent = CompareUtil.compareObjects(existDeviceDO, deviceDO, "deviceStatus", "delFlag", "createdBy", "createdTime",
                "updatedBy", "updatedTime");

            //比对设备详情信息是否有变更
            DeviceDetailDO existDetailDO = deviceDetailRepository.queryByDeviceId(deviceInfoDTO.getDeviceId());
            DeviceDetailDO deviceDetailDO = new DeviceDetailDO();
            deviceDetailDO.setDeviceId(deviceInfoDTO.getDeviceId());
            deviceDetailDO.setDeviceType(deviceInfoDTO.getDeviceType());
            deviceDetailDO.setDeviceModel(deviceInfoDTO.getDeviceModel());
            deviceDetailDO.setDeviceBrand(deviceInfoDTO.getDeviceBrand());
            deviceDetailDO.setDeviceSystem(deviceInfoDTO.getDeviceSystem());
            deviceDetailDO.setDeviceSystemVersion(deviceInfoDTO.getDeviceSystemVersion());
            deviceDetailDO.setDeviceLoginUser(deviceInfoDTO.getDeviceLoginUser());
            deviceDetailDO.setDeviceLoginKey(deviceInfoDTO.getDeviceLoginKey());
            deviceDetailDO.setDeviceImei(deviceInfoDTO.getDeviceImei());
            deviceDetailDO.setDeviceSerialNo(deviceInfoDTO.getDeviceSerialNo());
            deviceDetailDO.setDeviceResolution(deviceInfoDTO.getDeviceResolution());
            deviceDetailDO.setDeviceColor(deviceInfoDTO.getDeviceColor());
            deviceDetailDO.setDeviceSize(deviceInfoDTO.getDeviceSize());
            deviceDetailDO.setDeviceAccessory(deviceInfoDTO.getDeviceAccessory());
            deviceDetailDO.setDeviceRemark(deviceInfoDTO.getDeviceRemark());
            deviceDetailDO.setUpdatedBy(deviceInfoDTO.getLoginUserAcct());
            deviceDetailDO.setUpdatedTime(LocalDateTime.now());
            String deviceDetailChangeContent = CompareUtil.compareObjects(existDetailDO, deviceDetailDO, "delFlag", "createdBy", "createdTime",
                "updatedBy", "updatedTime");

            String operDetail = deviceChangeContent.concat(deviceDetailChangeContent);
            List<EnumsVo> deviceChangeEnumList = DetailChangeEnum.getCodeList();
            for (EnumsVo enumsVo : deviceChangeEnumList) {
                if (operDetail.contains(enumsVo.getCode())) {
                    operDetail = operDetail.replace(enumsVo.getCode(), enumsVo.getName());
                }
            }
            //记录日志
            DeviceLogDO deviceLogDO = new DeviceLogDO();
            deviceLogDO.setDeviceId(deviceDO.getId());
            deviceLogDO.setDeviceNo(deviceDO.getDeviceNo());
            deviceLogDO.setDeviceName(deviceDO.getDeviceName());
            deviceLogDO.setOperPerson(deviceInfoDTO.getLoginUser());
            deviceLogDO.setOperPersonAcct(deviceInfoDTO.getLoginUserAcct());
            deviceLogDO.setOperTime(LocalDateTime.now());
            deviceLogDO.setOperDetail(operDetail);
            deviceLogDO.setDelFlag(Constants.N);
            deviceLogDO.setCreatedTime(LocalDateTime.now());
            deviceLogDO.setCreatedBy(OperPersonEnum.ADMIN.getCode());
            deviceLogDO.setUpdatedBy(OperPersonEnum.ADMIN.getCode());
            deviceLogDO.setUpdatedTime(LocalDateTime.now());
            deviceDetailRepository.updateByDeviceId(deviceDetailDO);
            if(null!=deviceDO.getDeviceNo()) {
                deviceRepository.updateDeviceInfo(deviceDO);
            }
            deviceLogRepository.save(deviceLogDO);
        }
    }

    @Override
    public List<DeviceDO> queryUpdateRemindList(String updateRemindTime) {
        return deviceRepository.queryUpdateRemindList(updateRemindTime);
    }

    @Override
    public List<DeviceDO> queryByStatus(String deviceStatus) {
        return deviceRepository.queryByStatus(deviceStatus);
    }

    @Override
    public void feedbackCommit(DeviceFeedbackAddDTO deviceFeedbackAddDTO) {
        DeviceDO deviceDO =deviceRepository.queryByDeviceNo(deviceFeedbackAddDTO.getDeviceNo());
        if(null==deviceDO) {
             ApiResponse.error("设备不存在");
        }
        if(null!=deviceFeedbackAddDTO.getContent() &&  null==deviceFeedbackAddDTO.getHandleResult()) {
            DeviceFeedbackDO deviceFeedbackDO =new DeviceFeedbackDO();
            deviceFeedbackDO.setDeviceId(Long.valueOf(deviceDO.getId()));
            deviceFeedbackDO.setContent(deviceFeedbackAddDTO.getContent());
            deviceFeedbackDO.setCreatedBy(deviceFeedbackAddDTO.getLoginUserAcct());
            deviceFeedbackMapper.insert(deviceFeedbackDO);
        } else {
            DeviceFeedbackDO deviceFeedbackDO =new DeviceFeedbackDO();
            deviceFeedbackDO.setId(Long.valueOf(deviceFeedbackAddDTO.getId()));
            deviceFeedbackDO.setHandleResult(deviceFeedbackAddDTO.getHandleResult());
            deviceFeedbackDO.setUpdatedBy(deviceFeedbackAddDTO.getLoginUserAcct());
            deviceFeedbackMapper.updateById(deviceFeedbackDO);
        }

    }

    @Override
    public Pagination<DeviceFeedbackVo> deviceFeedbackPageQuery(DeviceFeedbackQueryDTO deviceFeedbackQueryDTO) {
        IPage<DeviceFeedbackVo> result = deviceRepository.deviceFeedbackPageQuery(deviceFeedbackQueryDTO);
        return PageUtil.convertPagination(result);
    }

    private String localDate2String(LocalDate localDate) {
        if (localDate == null) {
            return StringUtils.EMPTY;
        }
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private LocalDate string2LocalDate(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
}
}
