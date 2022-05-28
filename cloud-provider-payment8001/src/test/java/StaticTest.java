import cn.hutool.core.date.DateUtil;
import com.atguigu.springcloud.entities.StaticVo;
import com.atguigu.springcloud.util.OneUtil;
import org.junit.Test;

public class StaticTest {
    @Test
    public void test1(){
        StaticVo staticVo = StaticVo.getStaticVo();
        System.out.println("1----"+staticVo.count1);
        System.out.println("2----"+staticVo.count2);
        System.out.println("3----"+staticVo.count3);
        System.out.println(System.nanoTime());
    }


    @Test
    public void test2(){
        Boolean validCron = OneUtil.isValidCron("0 0 0 L-3 * ? 2020");
        System.out.println(validCron);
    }
}
