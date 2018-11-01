package com.ikurek.drugsafe.di.modules

import com.ikurek.drugsafe.api.DrugsApi
import com.ikurek.drugsafe.api.UsersApi
import dagger.Module
import dagger.Provides

@Module
class ApiModule(val usersApi: UsersApi, val drugsApi: DrugsApi) {

    @Provides
    fun provideUsersApi(): UsersApi = usersApi

    @Provides
    fun provideDrugsApi(): DrugsApi = drugsApi
}