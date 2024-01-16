package dev.prajwal.shared.di

import dev.prajwal.app.BirdsViewModel
import dev.prajwal.shared.domain.FetchBirdsDataUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import dev.prajwal.shared.service.FetchBirdsDataRepository

fun initKoin() =
    startKoin {
        modules(
            sharedViewModelModule +
            sharedUsecaseModule +
            sharedRepositoryModule
        )
    }

val sharedViewModelModule =
    module { factoryOf(::BirdsViewModel) }

val sharedRepositoryModule =
    module { singleOf(::FetchBirdsDataRepository) }

val sharedUsecaseModule =
    module { factoryOf(::FetchBirdsDataUseCase) }

object ViewModelDependencies : KoinComponent {
    fun getBirdsViewModel() = getKoin().get<BirdsViewModel>()
}