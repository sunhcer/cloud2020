package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.annotation.ControllerWebLog;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.NotNullVo;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
@Api(tags = "订单相关接口")
public class PaymentController {

    private ExecutorService pool= Executors.newFixedThreadPool(10);

    //---------------
    @Autowired
    private PaymentService paymentService;

    //用端口号来区分集群中相同服务名的机器
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    // 测试
    @ResponseBody
    @GetMapping("payment/swagger")
    @ApiOperation(value="swagger接口",notes = "swaggernote")
    @ControllerWebLog(name="测试日志")
    @ApiResponses({
            @ApiResponse(code = 200,message = "返回字段注释",response =Payment.class ),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "entId",value="企业id",dataType = "String",paramType = "query",required = true),
            @ApiImplicitParam(name = "taxYear",value = "所属年份",dataType = "String",paramType = "query",required = true)
    })
    public CommonResult testSwagger(String entId,String taxYear){
        Payment payment = new Payment();
        payment.setId(23L);
        payment.setSerial("阿卡军事大国");
        return CommonResult.succ(payment);
    }

    /**
     * 
     * @author sunhcer.shi
     * @date 2021/03/16 18:58
     * @param payment 
     * @return com.atguigu.springcloud.entities.CommonResult 
     */
    @PostMapping("payment/create")
    @ControllerWebLog(name="创建订单")
    @ApiOperation("创建订单")
    public CommonResult create(@ApiParam("订单对象") @RequestBody Payment payment){
        log.info("测试热部署-------");
        String test = stringRedisTemplate.opsForValue().get("test");
        log.info("获取缓存key：test 结果："+test);
        int result=paymentService.create(payment);
        log.info("---------------新增订单---------------");
        return CommonResult.succ("操作成功"+result+serverPort);
    }

    @GetMapping("payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        for (int i = 0; i <200 ; i++) {
            Payment payment=paymentService.getPaymentById(id);
            log.info("第"+i+"次执行结果:"+payment.toString());
        }
//        return CommonResult.succ(""+payment+serverPort);
        return CommonResult.succ(""+serverPort);
    }

    @GetMapping("payment/getSync/{id}")
    public CommonResult getSyncPaymentById(@PathVariable("id")Long id){
        for (int i = 0; i <200 ; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    syncGet(id);
                }
            });
        }

//        return CommonResult.succ("听说你很帅哦"+payment+serverPort);
        return CommonResult.succ("听说你很帅哦"+serverPort);
    }

    private void syncGet(Long id) {
        Payment payment=paymentService.getPaymentById(id);
        log.info(Thread.currentThread().getName()+"执行结果:"+payment.toString());
    }


    @GetMapping(value = "payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (int i= 0; i <services.size() ; i++) {
            System.out.println("service "+services.get(i));
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        for (int i = 0; i <instances.size() ; i++) {
            ServiceInstance serviceInstance = instances.get(i);
            System.out.println(serviceInstance.getServiceId()+" "+serviceInstance.getHost()+" "+serviceInstance.getPort()+ " "+serviceInstance.getUri());
        }
        return this.discoveryClient;
    }

    /*mybatis分页插件pagehelper*/
    @GetMapping(value = "payment/getPage")
    @ControllerWebLog(name = "接口日志 POST 请求测试", intoDb = false)
    public CommonResult getPage(@RequestParam int pageNum,
                                @RequestParam(required =false ) Integer id,
                                @RequestParam int pageSize){
        return CommonResult.succ(paymentService.getPaymentList(pageNum,pageSize));
    }

    /*Redisson实现分布式锁*/
    @RequestMapping("/deductStock")
    public String deduckStock(){
        String lockKey="product_101";
        RLock redissonLock = redisson.getLock(lockKey);//拿到锁对象
        try{
            redissonLock.lock();//上锁
            int stock=Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock>0){
                int realStock=stock-1;
                stringRedisTemplate.opsForValue().set("stock",realStock+"");
                System.out.println("扣减成功,剩余库存:"+realStock);
            }else{
                System.out.println("扣减库存失败,库存不足");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            redissonLock.unlock();//释放锁
        }
        return "success";
    }


    @PostMapping("/testNull")
    public CommonResult testNull(@Valid @RequestBody NotNullVo notNullVo){
        log.info("参数:");
        return CommonResult.succ(notNullVo);
    }

}
