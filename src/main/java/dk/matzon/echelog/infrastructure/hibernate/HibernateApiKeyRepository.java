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

import dk.matzon.echelog.domain.model.ApiKey;
import dk.matzon.echelog.domain.model.ApiKeyRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
@SuppressWarnings("unchecked")
public class HibernateApiKeyRepository implements ApiKeyRepository {

    private SessionFactory sessionFactory;

    public HibernateApiKeyRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ApiKey findByKey(String _key) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(ApiKey.class);
        criteria.add(Restrictions.eq("key", _key));
        List<ApiKey> result = criteria.list();

        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public ApiKey findByUsernameAndPassword(String _username, String _password) {
        Session currentSession = sessionFactory.getCurrentSession();
        Criteria criteria = currentSession.createCriteria(ApiKey.class);
        criteria.add(Restrictions.eq("name", _username));
        criteria.add(Restrictions.eq("password", _password));
        List<ApiKey> result = criteria.list();

        if (result.size() > 0) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public ApiKey save(ApiKey _apiKey) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(_apiKey);
        return _apiKey;
    }
}
