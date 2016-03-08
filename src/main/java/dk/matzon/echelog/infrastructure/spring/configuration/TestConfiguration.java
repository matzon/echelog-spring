/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Brian Matzon
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dk.matzon.echelog.infrastructure.spring.configuration;

import dk.matzon.echelog.application.Echelog;
import dk.matzon.echelog.domain.model.ChannelRepository;
import dk.matzon.echelog.domain.model.EntryRepository;
import dk.matzon.echelog.domain.model.NetworkRepository;
import dk.matzon.echelog.infrastructure.hibernate.HibernateChannelRepository;
import dk.matzon.echelog.infrastructure.hibernate.HibernateEntryRepository;
import dk.matzon.echelog.infrastructure.hibernate.HibernateNetworkRepository;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
@Configuration
@Profile({"default", "integrationtest"})
public class TestConfiguration {

    @Bean
    public Echelog echelog() {
        return new Echelog(networkRepository(), channelRepository(), entryRepository());
    }

    @Bean
    public ChannelRepository channelRepository() {
        return new HibernateChannelRepository(sessionFactory().getObject());
    }

    @Bean
    public NetworkRepository networkRepository() {
        return new HibernateNetworkRepository(sessionFactory().getObject());
    }

    @Bean
    public EntryRepository entryRepository() {
        return new HibernateEntryRepository(sessionFactory().getObject());
    }

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
        factoryBean.setPersistenceUnitName("echelog");
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
}