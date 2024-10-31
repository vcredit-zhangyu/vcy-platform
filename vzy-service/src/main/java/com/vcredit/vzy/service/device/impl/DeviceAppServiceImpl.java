package com.vcredit.vzy.service.device.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vcredit.vzy.common.dto.*;
import com.vcredit.vzy.common.utils.Constants;
import com.vcredit.vzy.common.vo.DeviceAppVo;
import com.vcredit.vzy.common.vo.DeviceDownLoadVo;
import com.vcredit.vzy.dao.entity.DeviceAppDO;
import com.vcredit.vzy.dao.entity.DeviceAppDownloadDO;
import com.vcredit.vzy.dao.entity.DeviceDO;
import com.vcredit.vzy.dao.mapper.DeviceAppDownLoadMapper;
import com.vcredit.vzy.dao.repository.DeviceAppRepository;
import com.vcredit.vzy.dao.repository.DeviceRepository;
import com.vcredit.vzy.dao.util.PageUtil;
import com.vcredit.vzy.service.device.DeviceAppService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/17
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DeviceAppServiceImpl implements DeviceAppService {

    private final DeviceAppRepository deviceAppRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceAppDownLoadMapper deviceAppDownLoadMapper;

    @Override
    public void deviceAppCommit(List<DeviceAppCommitDTO> deviceAppCommitList) {
        deviceAppCommitList.forEach(deviceAppCommitDTO -> {
            DeviceDO existDO = deviceRepository.queryByDeviceNo(deviceAppCommitDTO.getDeviceNo());
            if (existDO == null) {
                log.warn("设备deviceNo:{}不存在", deviceAppCommitDTO.getDeviceNo());
                return;
            }
            DeviceAppDO existAppDO = deviceAppRepository.queryByDeviceIdAndApp(existDO.getId(), deviceAppCommitDTO.getApp());
            boolean existApp = existAppDO != null;
            DeviceAppDO deviceAppDO;
            if (existApp) {
                deviceAppDO = existAppDO;
            } else {
                deviceAppDO = new DeviceAppDO();
                deviceAppDO.setCreatedBy("app");
                deviceAppDO.setCreatedTime(LocalDateTime.now());
            }
            deviceAppDO.setUpdatedBy("app");
            deviceAppDO.setUpdatedTime(LocalDateTime.now());
            deviceAppDO.setDeviceId(existDO.getId());
            deviceAppDO.setLastApp(deviceAppCommitDTO.getApp());
            deviceAppDO.setAppTime(string2LocalDateTime(deviceAppCommitDTO.getAppTime()));
            deviceAppDO.setDelFlag(Constants.N);
            if (existApp) {
                deviceAppRepository.updateById(deviceAppDO);
            } else {
                deviceAppRepository.save(deviceAppDO);
            }
        });
    }

    @Override
    public Pagination<DeviceAppVo> deviceAppPageQuery(DeviceAppQueryDTO queryDTO) {
        IPage<DeviceAppVo> tempResult = deviceAppRepository.deviceAppPageQuery(queryDTO);
        return PageUtil.convertPagination(tempResult);
    }

    @Override
    public DeviceAppDownloadResDTO deviceAppDownLoadQuery(DeviceAppDownloadReq deviceAppDownloadReq) {
        DeviceAppDownloadResDTO deviceAppDownloadResDTO =new DeviceAppDownloadResDTO();
        //查询数据
        List<String> list1 =deviceAppDownloadReq.getModel();
        List<DeviceAppDownloadDO> list= deviceAppDownLoadMapper.deviceAppDownLoadQuery(deviceAppDownloadReq.getModel(),deviceAppDownloadReq.getVersion());
        //处理数据
        List<DeviceDownLoadVo> testList =new LinkedList<>();
        List<DeviceDownLoadVo> prodList =new LinkedList<>();
        for(DeviceAppDownloadDO deviceAppDownloadDO :list) {
            if(deviceAppDownloadDO.getEnvironment().equals("env")) {
                DeviceDownLoadVo deviceDownLoadVo =new DeviceDownLoadVo();
                BeanUtils.copyProperties(deviceAppDownloadDO,deviceDownLoadVo);
                prodList.add(deviceDownLoadVo);
            } else if(deviceAppDownloadDO.getEnvironment().equals("test")) {
                DeviceDownLoadVo deviceDownLoadVo =new DeviceDownLoadVo();
                BeanUtils.copyProperties(deviceAppDownloadDO,deviceDownLoadVo);
                testList.add(deviceDownLoadVo);
            }
        }
        deviceAppDownloadResDTO.setTestEvInfo(testList);
        deviceAppDownloadResDTO.setProdEvInfo(prodList);
        return deviceAppDownloadResDTO;
    }


    private LocalDateTime string2LocalDateTime(String time) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
