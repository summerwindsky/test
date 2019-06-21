package motan;

import com.weibo.api.motan.config.ProtocolConfig;
import com.weibo.api.motan.config.RefererConfig;
import com.weibo.api.motan.config.RegistryConfig;
import com.weibo.api.motan.config.ServiceConfig;
import org.junit.Test;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-05-03 15:54
 */
public class MotanPropertiesLoadTest {

    @Test
    public void buildProperty() {
        // 1.注册服务
        ServiceConfig<MotanDemoService> motanDemoService = new ServiceConfig<MotanDemoService>();

        // 设置接口及实现类
        motanDemoService.setInterface(MotanDemoService.class);//设置服务接口，客户端在rpc调用时，会在协议中传递接口名称，从而实现与具体实现类一一对应
        motanDemoService.setRef(new MotanDemoServiceImpl());//设置接口实现类，实际的业务代码

        // 配置服务的group以及版本号
        motanDemoService.setGroup("motan-demo-rpc");//服务所属的组
        motanDemoService.setVersion("1.0");

        // 配置注册中心
        RegistryConfig registry = new RegistryConfig();
        registry.setRegProtocol("local");
        motanDemoService.setRegistry(registry);

        // 配置ZooKeeper注册中心
//        RegistryConfig zookeeperRegistry = new RegistryConfig();
//        zookeeperRegistry.setRegProtocol("zookeeper");//使用zookeeper作为注册中心
//        zookeeperRegistry.setAddress("127.0.0.1:2181");//zookeeper的连接地址
//        motanDemoService.setRegistry(zookeeperRegistry);

        // 配置RPC协议
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setId("motan");//使用motan应用协议
        protocol.setName("motan");
        motanDemoService.setProtocol(protocol);

        motanDemoService.setExport("motan:8002");//本服务的监控端口号是8010
        motanDemoService.export();//发布及在zookeeper上注册此服务

    }

    @Test
    public void getMotanService() {
        // 2.发现服务
        RefererConfig<MotanDemoService> motanDemoServiceReferer = new RefererConfig<MotanDemoService>();

        // 设置接口及实现类
        motanDemoServiceReferer.setInterface(MotanDemoService.class);
        // 配置服务的group以及版本号
        motanDemoServiceReferer.setGroup("motan-demo-rpc");
        motanDemoServiceReferer.setVersion("1.0");
        motanDemoServiceReferer.setRequestTimeout(300);
        // 配置注册中心
        RegistryConfig registry = new RegistryConfig();
        registry.setRegProtocol("local");
        motanDemoServiceReferer.setRegistry(registry);

//        RegistryConfig zookeeperRegistry = new RegistryConfig();
//        zookeeperRegistry.setRegProtocol("zookeeper");//使用zookeeper作为注册中心
//        zookeeperRegistry.setAddress("127.0.0.1:2181");//zookeeper的连接地址
//        motanDemoServiceReferer.setRegistry(zookeeperRegistry);
        // 配置RPC协议
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setId("motan");
        protocol.setName("motan");
        motanDemoServiceReferer.setProtocol(protocol);
        motanDemoServiceReferer.setDirectUrl("motan:8002");

        // 使用服务
        MotanDemoService service = motanDemoServiceReferer.getRef();

        service.buildProperty();
    }
}
