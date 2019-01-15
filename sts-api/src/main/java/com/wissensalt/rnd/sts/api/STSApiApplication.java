package com.wissensalt.rnd.sts.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created on 1/11/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@ComponentScan({"com.wissensalt.rnd.sts.shared.data", "com.wissensalt.rnd.sts.api"})
@EntityScan("com.wissensalt.rnd.sts.shared.data.model")
@SpringBootApplication
public class STSApiApplication {

    public static void main(String [] args) {
        SpringApplication.run(STSApiApplication.class);
    }
}
