package com.mengzhiayuan.naruto.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * --------------http请求返回的最外层对象----------------
 * @date ：2021/7/20 12:23
 */


/**
 * 对于JVM来说，要进行序列化的类必须要有一个标记，只有持有这个标记JVM才允许类创建的对象可以通过其IO系统转换为字节数据，从而实现持久化，而这个标记就是Serializable接口。而在反序列化的过程中则需要使用serialVersionUID来确定由那个类来加载这个对象，所以我们在实现Serializable接口的时候，一般还会要去尽量显示地定义serialVersionUID。
 * 这个serialVersionUID的详细的工作机制是：在序列化的时候系统将serialVersionUID写入到序列化的文件中去，当反序列化的时候系统会先去检测文件中的serialVersionUID是否跟当前的文件的serialVersionUID是否一致，如果一直反序列化不成功，就说明当前类跟序列化后的类发生了变化，比如是成员变量的数量或者是类型发生了变化，那么在反序列化时就会发生crash，并且回报出错误：
 * 如果我们在序列化中没有显示地声明serialVersionUID，则序列化运行时将会根据该类的各个方面计算该类默认的serialVersionUID值。但是，Java官方强烈建议所有要序列化的类都显示地声明serialVersionUID字段，因为如果高度依赖于JVM默认生成serialVersionUID，可能会导致其与编译器的实现细节耦合，这样可能会导致在反序列化的过程中发生意外的InvalidClassException异常。
 * 因此，为了保证跨不同Java编译器实现的serialVersionUID值的一致，实现Serializable接口的必须显示地声明serialVersionUID字段
 * */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> implements Serializable {


    private static final long serialVersionUID = -6753410071054719007L;
    /*错误码*/
    private Integer code;

    /*提示信息*/
    private String msg;

    /*具体内容*/
    private T data;
}
