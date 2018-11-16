package com.ikurek.drugsafe.di.modules

import com.ikurek.drugsafe.database.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(var database: AppDatabase) {

    @Provides
    fun provideDatabase(): AppDatabase = this.database
}