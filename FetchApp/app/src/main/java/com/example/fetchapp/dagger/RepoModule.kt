package com.example.fetchapp.dagger

import com.example.fetchapp.repo.ItemRepository
import com.example.fetchapp.repo.ItemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule{

    @Binds
    @Singleton
    abstract fun bindItemRepository(itemRepository: ItemRepositoryImpl) : ItemRepository
}