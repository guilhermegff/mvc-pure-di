package com.example.mvcpuredi.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.mvcpuredi.di.presentation.PresentationComponent
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun newPresentationComponent(
        //presentationModule: PresentationModule,
        //useCasesModule: UseCasesModule,
    ): PresentationComponent

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder
        fun build(): ActivityComponent
    }
}