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

package dk.matzon.echelog.infrastructure.hibernate;

import dk.matzon.echelog.domain.model.Channel;
import dk.matzon.echelog.domain.model.ChannelRepository;
import dk.matzon.echelog.domain.model.Network;
import dk.matzon.echelog.domain.model.NetworkRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
@SuppressWarnings("unchecked")
public class HibernateChannelRepository implements ChannelRepository {

    private SessionFactory sessionFactory;

    public HibernateChannelRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Channel> findAll(boolean includeArchived) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(Channel.class);
        return (List<Channel>) criteria.list();
    }

    @Override
    public List<Channel> findByNetwork(Network _network) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(Channel.class);
        criteria.add(Restrictions.eq("network", _network.getName()));
        return criteria.list();
    }

    @Override
    public Channel findByNetworkAndName(Network _network, String _name) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(Channel.class);
        criteria.add(Restrictions.eq("network", _network.getName()))
                .add(Restrictions.eq("name", _name));
        List<Channel> list = criteria.list();
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Channel save(Channel _channel) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(_channel);
        return _channel;
    }
}
