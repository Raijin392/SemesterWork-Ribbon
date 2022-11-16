package ru.kpfu.itis.ribbon.service;

import ru.kpfu.itis.ribbon.DAO.SubscriptionDAO;
import ru.kpfu.itis.ribbon.Exception.DatabaseException;
import ru.kpfu.itis.ribbon.Exception.PropertiesException;
import ru.kpfu.itis.ribbon.provider.ConnectionProvider;

public class SubscriptionService {

    private final SubscriptionDAO subscriptionDAO;

    public SubscriptionService() throws PropertiesException, DatabaseException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
        subscriptionDAO = new SubscriptionDAO(connectionProvider);
    }

    public boolean deleteSubscription(Integer authorID, Integer subscriberID) {
        subscriptionDAO.deleteSubscription(authorID, subscriberID);
        return true;
    }

    public boolean offerSubscription(Integer authorID, Integer subscriberID) {
        subscriptionDAO.offerSubscription(authorID, subscriberID);
        return true;
    }

}
