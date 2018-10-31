package com.ikurek.drugsafe.di.modules

import com.ikurek.drugsafe.api.ApiInterface
import dagger.Module
import dagger.Provides

@Module
class ApiModule(val apiInterface: ApiInterface) {

    @Provides
    fun provideApiInterface(): ApiInterface = apiInterface

}