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
import dk.matzon.echelog.domain.model.ApiKeyRepository;
import dk.matzon.echelog.domain.model.ChannelRepository;
import dk.matzon.echelog.domain.model.EntryRepository;
import dk.matzon.echelog.domain.model.NetworkRepository;
import dk.matzon.echelog.infrastructure.hibernate.HibernateApiKeyRepository;
import dk.matzon.echelog.infrastructure.hibernate.HibernateChannelRepository;
import dk.matzon.echelog.infrastructure.hibernate.HibernateEntryRepository;
import dk.matzon.echelog.infrastructure.hibernate.HibernateNetworkRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManagerFactory;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
@Configuration
@Profile("production")
public class ProductionConfiguration {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public Echelog echelog() {
        return new Echelog(networkRepository(), channelRepository(), entryRepository());
    }

    @Bean
    public ChannelRepository channelRepository() {
        return new HibernateChannelRepository(sessionFactory());
    }

    @Bean
    public NetworkRepository networkRepository() {
        return new HibernateNetworkRepository(sessionFactory());
    }

    @Bean
    public EntryRepository entryRepository() {
        return new HibernateEntryRepository(sessionFactory());
    }

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Bean
    public ApiKeyRepository apiKeyRepository() {
        return new HibernateApiKeyRepository(sessionFactory());
    }

}