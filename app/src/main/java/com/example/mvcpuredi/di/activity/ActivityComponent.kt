package com.example.mvcpuredi.di.activity

import com.example.mvcpuredi.di.presentation.PresentationComponent
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun newPresentationComponent(
        //presentationModule: PresentationModule,
        //useCasesModule: UseCasesModule,
    ): PresentationComponent
}