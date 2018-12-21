
## 说明
* 这是一个spring boot快速集成的消息队列nsq服务, 
* [nsq和Kafka对比](https://www.liuin.cn/2018/07/11/%E5%88%86%E5%B8%83%E5%BC%8F%E6%B6%88%E6%81%AF%E9%98%9F%E5%88%97-NSQ-%E5%92%8C-Kafka-%E5%AF%B9%E6%AF%94/)

### 部署nsq服务
* 安装[nsq](https://github.com/nsqio/nsq) tag v1.1.0
* nsq启动注意命令要使用ip，否则客户端使用的是操作系统的osname
```
nohup nsqlookupd -broadcast-address="mq-server"  > /data/nsqlog/nsqlookupd.log &
nohup nsqd -broadcast-address="mq-server" --lookupd-tcp-address=mq-server:4160  > /data/nsqlog/nsqd.log &
nohup nsqadmin --lookupd-http-address=mq-server:4161 > /data/nsqlog/nsqadmin.log &
ss -nlt
```
* 客户端使用的是[JavaNSQClient](https://github.com/brainlag/JavaNSQClient)
### 客户端使用
* 配置host: 172.0.0.1  mq-server
* 配置文件添加
```
mq:
  consumer:
    host: mq-server
    port: 4161
  producer:
    host: 192.168.31.251
    port: 4150
```
* 用例代码
```java
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = TaskApp.class)
@ContextConfiguration
public class QueueTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    QueueConsumer queueConsumer;
    @Autowired
    QueueProducer queueProducer;
    @Test
    public void testQueueSendAndHandler(){
        //消费端
		queueConsumer.listener(QueueChannelEnum.COIN_SCAN_IN_CEN_HANDLER.appendPrefix(CoinEnum.ETH.name()), "c1", (message) -> {
			QMessage<Tx> qm = QMessageUtil.parse(message, Tx.class);
			ethService.addTxToSys( CoinEnum.ETH, qm.getData());
			message.finished();
		});

        //发送端
        DcTx dt = new DcTx();
        dt.setDcId(1001);
        dt.setSeqNo("xxx121");
        queueProducer.send("chaneel-one", dt);
        //queueProducer.send(QueueChannelEnum.COIN_SCAN_IN_BIZ_HANDLER.appendPrefix(wallet.getSysId()), dt); //通道建议用枚举

    }
}
```
