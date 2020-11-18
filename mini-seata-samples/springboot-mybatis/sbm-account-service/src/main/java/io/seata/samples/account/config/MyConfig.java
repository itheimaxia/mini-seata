package io.seata.samples.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.brianxia.miniseata.client.spring.GlobalTransactionScanner;

/**
 * @author brianxia
 * @version 1.0
 * @date 2020/11/18 15:40
 */
@Configuration
@Import(GlobalTransactionScanner.class)
public class MyConfig {
}
