package dk.matzon.echelog.infrastructure;

import dk.matzon.echelog.application.Echelog;
import dk.matzon.echelog.domain.model.ChannelRepository;
import dk.matzon.echelog.domain.model.NetworkRepository;
import dk.matzon.echelog.infrastructure.persistence.staticdata.MockData;
import dk.matzon.echelog.infrastructure.persistence.staticdata.StaticChannelRepository;
import dk.matzon.echelog.infrastructure.persistence.staticdata.StaticNetworkRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
@SpringBootApplication
@ComponentScan(basePackages = "dk.matzon.echelog.interfaces.rest")
public class SpringBoot {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot.class, args);
    }

    @Bean
    public Echelog echelog() {
        return new Echelog(channelRepository(), networkRepository());
    }

    @Bean
    public ChannelRepository channelRepository() {
        return new StaticChannelRepository(mockData());
    }

    @Bean
    public NetworkRepository networkRepository() {
        return new StaticNetworkRepository(mockData());
    }

    @Bean
    public MockData mockData() {
        return new MockData();
    }

}