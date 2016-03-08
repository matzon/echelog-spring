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

import dk.matzon.echelog.domain.model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

/**
 * Created by Brian Matzon on 07-03-2016.
 */
@SuppressWarnings("unchecked")
public class HibernateEntryRepository implements EntryRepository {

    private SessionFactory sessionFactory;

    public HibernateEntryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Entry> findAll(Network _network, Channel _channel, Date _start, Date _end) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(Entry.class);
        criteria.add(Restrictions.eq("network", _network.getName()));
        criteria.add(Restrictions.eq("channel", _network.getName()));
        criteria.add(Restrictions.gt("date", _start));
        criteria.add(Restrictions.lt("date", _end));
        return criteria.list();
    }
}
