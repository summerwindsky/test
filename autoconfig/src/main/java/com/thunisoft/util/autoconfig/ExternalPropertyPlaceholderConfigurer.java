package com.thunisoft.util.autoconfig;

import model.AppInfo;
import model.PropertyDTO;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import util.http.HttpService;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class ExternalPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    //外部参数覆盖properties文件
    private boolean propertyOverride = false;
    private AppInfo appInfo;
    private PropertyDTO propertyDTO = new PropertyDTO();

    /**
     * Return a merged Properties instance containing both the
     * loaded properties and properties set on this FactoryBean.
     */
    protected Properties mergeProperties() throws IOException {
        Properties result = new Properties();

        if (this.localOverride) {
            // Load properties from file upfront, to let local properties override.
            loadProperties(result);
        }

        if (this.localProperties != null) {
            for (Properties localProp : this.localProperties) {
                CollectionUtils.mergePropertiesIntoMap(localProp, result);
            }
        }

        if (!this.localOverride) {
            // Load properties from file afterwards, to let those properties override.
            loadProperties(result);
        }

        // Load config property from database
        if (this.propertyOverride) {
            Properties dbprop = loadAllParamProperties();
            CollectionUtils.mergePropertiesIntoMap(dbprop, result);
        }

        return result;
    }

    protected Properties loadAllParamProperties() {
        Properties prop = new Properties();
        if (propertyOverride) {
            logger.info("--- launch external config property ----");

            String appName = appInfo.getAppName();
            String appVersion = appInfo.getAppVersion();
            if (!StringUtils.isEmpty(appName) && !StringUtils.isEmpty(appVersion)) {
                // TODO 异常控制
                try {
                    propertyDTO.setZjm(appName);
                    propertyDTO.setZjbb(appVersion);
                    // 调外部服务，获取参数
                    Map<String, String> paras = HttpService.visitServ(propertyDTO);
                    prop.putAll(paras);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                throw new RuntimeException("应用的参数配置出错，appName:[" + appName + "], appVersion:[" + appVersion + "]");
            }
        }

        return prop;
    }

    public boolean isPropertyOverride() {
        return propertyOverride;
    }

    public void setPropertyOverride(boolean propertyOverride) {
        this.propertyOverride = propertyOverride;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public PropertyDTO getPropertyDTO() {
        return propertyDTO;
    }

    public void setPropertyDTO(PropertyDTO propertyDTO) {
        this.propertyDTO = propertyDTO;
    }
}