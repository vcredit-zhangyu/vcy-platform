package com.vcredit.vzy.website.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.codec.Base64;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.vcredit.framework.ecm.client.builder.DownloadBuilder;
import com.vcredit.framework.ecm.client.component.EcmTemplate;
import com.vcredit.vzy.common.dto.*;
import com.vcredit.vzy.common.enums.DeviceStatusEnum;
import com.vcredit.vzy.common.utils.Constants;
import com.vcredit.vzy.common.vo.*;
import com.vcredit.vzy.dao.entity.DeviceDO;
import com.vcredit.vzy.dao.entity.DeviceDetailDO;
import com.vcredit.vzy.dao.mapper.DeviceDetailMapper;
import com.vcredit.vzy.dao.mapper.DeviceMapper;
import com.vcredit.vzy.service.device.DeviceService;
import com.vcredit.vzy.website.vo.UserContext;
import com.vcredit.vzy.website.vo.UserVO;

import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.apache.poi.util.IOUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
public class DeviceController {

    private final DeviceService deviceService;
    private final EcmTemplate ecmTemplate;
    private final DeviceMapper deviceMapper;
    private final DeviceDetailMapper deviceDetailMapper;

    /**
     * 设备信息提交 无则新增，有则更新
     */
    @PostMapping(value = "/info/commit")
    public ApiResponse<String> deviceInfoCommit(@RequestBody DeviceInfoCommitDTO deviceInfo) {
        try {
            String userInfo = UserContext.getCurrentUserInfo();
            UserVO oauth2UserVO = JSONObject.parseObject(userInfo, UserVO.class);
            deviceInfo.setLoginUser(oauth2UserVO.getUserName());
            deviceInfo.setLoginUserAcct(oauth2UserVO.getUserAccount());
            String result = deviceService.deviceInfoCommit(deviceInfo);
            if(StringUtils.isNotBlank(result)){
                return ApiResponse.error(result);
            }
            return ApiResponse.success("设备信息提交成功");
        }catch (Exception e){
            log.error("设备信息提交失败",e);
            return ApiResponse.error("系统异常");
        }
    }

    @GetMapping("/info/pageQuery")
    public ApiResponse<Pagination<DevicePageVo>> deviceInfoPageQuery(DevicePageQueryDTO devicePageQueryDTO) {
        return ApiResponse.success(deviceService.deviceInfoPageQuery(devicePageQueryDTO));
    }

    @GetMapping("/info/detail")
    public ApiResponse<DeviceDetailVo> deviceInfoDetail(@RequestParam("deviceId") Long deviceId) {
        return ApiResponse.success(deviceService.deviceInfoDetail(deviceId));
    }

    @PostMapping("/info/update")
    public ApiResponse<String> deviceInfoUpdate(@RequestBody DeviceInfoDTO deviceInfoDTO) {
        String userInfo = UserContext.getCurrentUserInfo();
        UserVO oauth2UserVO = JSONObject.parseObject(userInfo, UserVO.class);
        deviceService.deviceInfoUpdate(deviceInfoDTO);
        return ApiResponse.success("success");
    }

    @PostMapping("/status/update")
    public ApiResponse<String> deviceStatusUpdate(@RequestBody DeviceInfoDTO deviceInfoDTO) {
        String userInfo = UserContext.getCurrentUserInfo();
        UserVO oauth2UserVO = JSONObject.parseObject(userInfo, UserVO.class);
        deviceInfoDTO.setLoginUser(oauth2UserVO.getUserName());
        deviceInfoDTO.setLoginUserAcct(oauth2UserVO.getUserAccount());
        deviceService.deviceStatusUpdate(deviceInfoDTO);
        return ApiResponse.success("success");
    }

    @GetMapping("/enums/query")
    public ApiResponse<List<EnumsResponseVo>> enumsQuery() {
        return ApiResponse.success(deviceService.enumsQuery());
    }

    @PostMapping("/feedback/commit")
    public ApiResponse<String> feedCommit(@RequestBody DeviceFeedbackAddDTO deviceFeedbackAddDTO) {
        if(StringUtils.isBlank(deviceFeedbackAddDTO.getDeviceNo())) {
            return ApiResponse.error("设备号不能为空");
        }
        deviceService.feedbackCommit(deviceFeedbackAddDTO);
        return ApiResponse.success("success");
    }

    @PostMapping("/feedback/query")
    public ApiResponse<Pagination<DeviceFeedbackVo>> feedbackQuery(@RequestBody DeviceFeedbackQueryDTO deviceFeedbackQueryDTO) {
        return ApiResponse.success(deviceService.deviceFeedbackPageQuery(deviceFeedbackQueryDTO));
    }

    @GetMapping("/report")
    public ApiResponse<DeviceReportVo> report() {
        DeviceReportVo deviceReportVo =new DeviceReportVo();
        List<DevcieBrandVo> brandDis =new LinkedList<>();
        DeviceVersionDis deviceVersionDis =new DeviceVersionDis();
        List<DeviceModelVo> modelDis=new LinkedList<>();

        //查询所有数据
        LambdaQueryWrapper<DeviceDetailDO> lambdaQueryWrapper =new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DeviceDetailDO::getDelFlag,0);
        List<DeviceDetailDO> deviceDetailDOS=deviceDetailMapper.selectList(lambdaQueryWrapper);

        //开始数据处理
        Map<String,Integer> branMap=new HashMap<>();
        Map<String,Integer> deviceModelMap=new HashMap<>();
        Map<String,Integer> versionMap=new HashMap<>();
        List<DeviceModelVo> iosModelList =new LinkedList<>();
        List<DeviceModelVo> androidModelList =new LinkedList<>();
        List<DeviceModelVo> hmModelList =new LinkedList<>();

        for(DeviceDetailDO deviceDetailDO :deviceDetailDOS) {
            //品牌统计
            if(StringUtils.isNotEmpty(deviceDetailDO.getDeviceBrand())) {
                if(!branMap.containsKey(deviceDetailDO.getDeviceBrand())) {
                    branMap.put(deviceDetailDO.getDeviceBrand(),1);
                } else {
                    branMap.put(deviceDetailDO.getDeviceBrand(),branMap.get(deviceDetailDO.getDeviceBrand())+1);
                }
            }

            //型号统计
            if(StringUtils.isNotEmpty(deviceDetailDO.getDeviceModel()) && StringUtils.isNotEmpty(deviceDetailDO.getDeviceBrand())) {
                if(!deviceModelMap.containsKey(deviceDetailDO.getDeviceModel()+","+deviceDetailDO.getDeviceBrand())) {
                    deviceModelMap.put(deviceDetailDO.getDeviceModel()+","+deviceDetailDO.getDeviceBrand(),1);
                } else {
                    deviceModelMap.put(deviceDetailDO.getDeviceModel()+","+deviceDetailDO.getDeviceBrand(),deviceModelMap.get(deviceDetailDO.getDeviceModel()+","+deviceDetailDO.getDeviceBrand())+1);
                }
            }

            //版本统计
            if(StringUtils.isNotEmpty(deviceDetailDO.getDeviceSystemVersion())) {
                String version="";
                int index=deviceDetailDO.getDeviceSystemVersion().indexOf(".");
                if(index>0) {
                    version=deviceDetailDO.getDeviceSystemVersion().substring(0,index);
                } else {
                    version=deviceDetailDO.getDeviceSystemVersion();
                }
                if(!versionMap.containsKey(deviceDetailDO.getDeviceSystem()+","+version)) {
                    versionMap.put(deviceDetailDO.getDeviceSystem()+","+version,1);
                } else {
                    versionMap.put(deviceDetailDO.getDeviceSystem()+","+version,versionMap.get(deviceDetailDO.getDeviceSystem()+","+version)+1);
                }
            }

        }


        //数据组装
        for(String key :branMap.keySet()) {
            DevcieBrandVo devcieBrandVo =new DevcieBrandVo();
            devcieBrandVo.setBrand(key);
            devcieBrandVo.setNums(branMap.get(key));
            brandDis.add(devcieBrandVo);
        }
        deviceReportVo.setBrandDis(brandDis);
        for(String key:deviceModelMap.keySet()) {
            String[] str =key.split(",");
            DeviceModelVo deviceModelVo =new DeviceModelVo();
            deviceModelVo.setSystem(str[0]);
            if(str.length>1) {
                deviceModelVo.setVersion(str[1]);
            } else {
                deviceModelVo.setVersion("");
            }

            deviceModelVo.setNums(deviceModelMap.get(key));
            modelDis.add(deviceModelVo);
        }
        deviceReportVo.setModelDis(modelDis);
        for(String key: versionMap.keySet()) {
            String[] str =key.split(",");
            if(str[0].equals("IOS")) {
                DeviceModelVo deviceModelVo =new DeviceModelVo();
                deviceModelVo.setSystem("IOS");
                deviceModelVo.setVersion(str[1]);
                deviceModelVo.setNums(versionMap.get(key));
                iosModelList.add(deviceModelVo);
            }
            if(str[0].equals("ANDROID")) {
                DeviceModelVo deviceModelVo =new DeviceModelVo();
                deviceModelVo.setSystem("ANDROID");
                deviceModelVo.setVersion(str[1]);
                deviceModelVo.setNums(versionMap.get(key));
                androidModelList.add(deviceModelVo);
            }
            if(str[0].equals("HARMONYOS")) {
                DeviceModelVo deviceModelVo =new DeviceModelVo();
                deviceModelVo.setSystem("HARMONYOS");
                deviceModelVo.setVersion(str[1]);
                deviceModelVo.setNums(versionMap.get(key));
                hmModelList.add(deviceModelVo);
            }
        }
        deviceVersionDis.setAndroidModelList(androidModelList);
        deviceVersionDis.setHmModelList(hmModelList);
        deviceVersionDis.setIosModelList(iosModelList);
        deviceReportVo.setVersionDis(deviceVersionDis);

        return ApiResponse.success(deviceReportVo);


    }



    @GetMapping("/download")
    public void download( HttpServletResponse response) {

        try {
            DownloadBuilder builder = ecmTemplate.download().uuid("2f6771765ce94dc9ac9814b9591abe91");
            DownloadBuilder.DownloadResult downloadResult = builder.downloadResult();
            String fileName = "template.xlsx";
            String headStr = "attachment; filename=\"" + URLEncoder.encode(fileName, StandardCharsets.UTF_8) + "\"";
            response.setHeader("Content-Disposition", headStr);
            response.setHeader("filename", fileName);
            try (ServletOutputStream out = response.getOutputStream()) {
                IOUtils.copy(builder.inputStream(), out);
                out.flush();
            }
        } catch (Exception e) {
            log.error("download方法抛出异常，", e);
        }
    }

    @PostMapping("/import")
    public ApiResponse<String> importBatch(MultipartFile file, HttpServletResponse response) throws Exception {
        List<ImportExcelDTO> list =new ArrayList<>();
        try{
            list = EasyExcel.read(file.getInputStream(), ImportExcelDTO.class, null).sheet().doReadSync();
        }catch (Exception e) {
            log.info(e.getMessage());
        }

        if (CollectionUtils.isEmpty(list)) {
            log.error("导入数据无内容");
        }
        //存入数据库
//        String userInfo = UserContext.getCurrentUserInfo();
//        UserVO oauth2UserVO = JSONObject.parseObject(userInfo, UserVO.class);
        for(ImportExcelDTO importExcelDTO : list) {
            DeviceDO deviceDO =new DeviceDO();
            BeanUtil.copyProperties(importExcelDTO,deviceDO);
            deviceDO.setCreatedTime(LocalDateTime.now());
            deviceDO.setCreatedBy("sys");
            deviceDO.setUpdatedBy("sys");
            deviceDO.setUpdatedTime(LocalDateTime.now());
            deviceDO.setDeviceNo(StringUtils.isNotBlank(importExcelDTO.getDeviceImei()) ? importExcelDTO.getDeviceImei() : importExcelDTO.getDeviceSerialNo());
            deviceDO.setDeviceStatus(DeviceStatusEnum.WAIT_APPLY.getCode());
            deviceDO.setDeviceCharge("sys");
            deviceDO.setDeviceChargeAcct("sys");
            deviceDO.setDelFlag(Constants.N);
            log.info(JSONObject.toJSONString(deviceDO));
            deviceMapper.insert(deviceDO);
            DeviceDetailDO deviceDetailDO =new DeviceDetailDO();
            deviceDetailDO.setDeviceId(deviceDO.getId());
            deviceDetailDO.setDelFlag(Constants.N);
            BeanUtil.copyProperties(importExcelDTO,deviceDetailDO);
            deviceDetailDO.setCreatedTime(LocalDateTime.now());
            deviceDetailDO.setCreatedBy("sys");
            deviceDetailDO.setUpdatedBy("sys");
            deviceDetailDO.setUpdatedTime(LocalDateTime.now());
            log.info(JSONObject.toJSONString(deviceDetailDO));
            deviceDetailMapper.insert(deviceDetailDO);
        }

        return ApiResponse.success("成功");
    }






}
