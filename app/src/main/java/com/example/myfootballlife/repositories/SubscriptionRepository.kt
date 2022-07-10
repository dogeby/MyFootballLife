package com.example.myfootballlife.repositories

import com.example.myfootballlife.data.local.SubscriptionDbDao
import com.example.myfootballlife.data.models.Subscription
import javax.inject.Inject

class SubscriptionRepository @Inject constructor(
    private val subscriptionDbDao: SubscriptionDbDao
) {
    suspend fun subscribe(subscription:Subscription) {
        subscriptionDbDao.insertSubscription(subscription)
    }

    suspend fun unSubscribe(id:String) {
        subscriptionDbDao.deleteSubscription(id)
    }

    suspend fun getAllSubscription() = subscriptionDbDao.getAllSubscription()

    suspend fun getSubscription(teamName: String) = subscriptionDbDao.getSubscription(teamName)
}