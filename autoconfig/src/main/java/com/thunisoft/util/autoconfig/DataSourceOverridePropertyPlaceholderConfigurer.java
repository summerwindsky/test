package com.thunisoft.util.autoconfig;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class DataSourceOverridePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
        //数据库覆盖properties文件  
        private boolean dataBasePropertyOverride = false;  
          
        private DataSource dataSource;  
        private String paramSql;  
        private String paramKeyColumn;  
        private String paramValueColumn;  
      
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
            if(this.dataBasePropertyOverride){  
                Properties dbprop = loadAllParamProperties();  
                CollectionUtils.mergePropertiesIntoMap(dbprop, result);  
            }  
              
            return result;  
        }  
          
        protected Properties loadAllParamProperties(){  
            Properties prop = new Properties();  
            if(dataBasePropertyOverride){
              // 模拟
                prop.setProperty("test", "from-database!!!!!!!!!!");
                prop.setProperty("port", "from-database!!!!!!!!!!");

                // TODO 数据库
//                logger.info("--- launch dataBase config property ----");
//                validParam();
//
//                JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//                List<Map<String, Object>> list = jdbcTemplate.queryForList(paramSql);
//                for(Map<String, Object> colMap : list){
//                    String key = StringUtils.trimAllWhitespace(colMap.get(paramKeyColumn) != null ? colMap.get(paramKeyColumn).toString() : "");
//                    String value = StringUtils.trimAllWhitespace(colMap.get(paramValueColumn) != null ? colMap.get(paramValueColumn).toString() : "");
//                    prop.put(key, value);
//                    logger.info("--- load database param key:["+key+"] value:["+value+"]");
//                }
            }  
              
            return prop;  
        }  
          
        private void validParam(){  
            if(dataBasePropertyOverride){  
                if(dataSource == null){  
                    throw new IllegalArgumentException("DataBase Property Override  launch, DataSource is null");  
                }  
                  
                if(StringUtils.isEmpty(paramSql)){  
                    throw new IllegalArgumentException("DataBase Property Override  launch, paramSql is null!");  
                }  
                if(StringUtils.isEmpty(paramKeyColumn)){  
                    throw new IllegalArgumentException("DataBase Property Override  launch, paramKeyColumn is null!");  
                }  
      
                if(StringUtils.isEmpty(paramValueColumn)){  
                    throw new IllegalArgumentException("DataBase Property Override  launch, paramValueColumn is null!");  
                }  
            }  
        }  
      
      
        public boolean isDataBasePropertyOverride() {  
            return dataBasePropertyOverride;  
        }  
      
        public void setDataBasePropertyOverride(boolean dataBasePropertyOverride) {  
            this.dataBasePropertyOverride = dataBasePropertyOverride;  
        }  
      
        public DataSource getDataSource() {
            return dataSource;  
        }  
      
        public void setDataSource(DataSource dataSource) {  
            this.dataSource = dataSource;  
        }  
      
        public String getParamSql() {  
            return paramSql;  
        }  
      
        public void setParamSql(String paramSql) {  
            this.paramSql = paramSql;  
        }  
      
        public String getParamKeyColumn() {  
            return paramKeyColumn;  
        }  
      
        public void setParamKeyColumn(String paramKeyColumn) {  
            this.paramKeyColumn = paramKeyColumn;  
        }  
      
        public String getParamValueColumn() {  
            return paramValueColumn;  
        }  
      
        public void setParamValueColumn(String paramValueColumn) {  
            this.paramValueColumn = paramValueColumn;  
        }  
          
      
    }  