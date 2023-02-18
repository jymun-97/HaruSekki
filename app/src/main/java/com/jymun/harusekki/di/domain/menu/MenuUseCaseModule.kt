package com.jymun.harusekki.di.domain.menu

import com.jymun.harusekki.data.repository.menu.MenuRepository
import com.jymun.harusekki.domain.menu.DeleteMenuUseCase
import com.jymun.harusekki.domain.menu.InsertMenuUseCase
import com.jymun.harusekki.domain.menu.LoadMenuUseCase
import com.jymun.harusekki.util.dispatcher.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class MenuUseCaseModule {

    @Provides
    fun provideLoadMenuUseCase(
        dispatcherProvider: DispatcherProvider,
        menuRepository: MenuRepository
    ) =
        LoadMenuUseCase(dispatcherProvider, menuRepository)

    @Provides
    fun provideInsertMenuUseCase(
        dispatcherProvider: DispatcherProvider,
        menuRepository: MenuRepository
    ) =
        InsertMenuUseCase(dispatcherProvider, menuRepository)

    @Provides
    fun provideDeleteMenuUseCase(
        dispatcherProvider: DispatcherProvider,
        menuRepository: MenuRepository
    ) =
        DeleteMenuUseCase(dispatcherProvider, menuRepository)
}