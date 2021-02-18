//import cn.hutool.core.bean.BeanUtil;
//import com.atguigu.springcloud.entities.Payment;
//import org.junit.Test;
//
//import java.util.Date;
//
///**
// * hutool工具测试类
// * @author sunhcer
// * @date 2021/01/24 10:37
// **/
//public class HutoolTest {
//
//    @Test
//    public void testBeanUtil(){
//        Payment source = new Payment();
//        source.setId(1L);
//        source.setSerial("2222");
//        Payment target = new Payment();
//        BeanUtil.copyProperties(source,target);
//        target.setSerial("555");
//        System.out.println("source---"+source);
//        System.out.println("target---"+target);
//    }
//
//
//    @Test
//    public void getSecond(){
//        Date date = new Date();
//        Long seconds = date.getTime();
//        System.out.println(seconds);
//
//        long l = System.currentTimeMillis() / 1000;
//        System.out.println(l);
//    }
//}
