package com.example.mvcpuredi.di

import com.example.mvcpuredi.di.presentation.PresentationComponent
import com.example.mvcpuredi.Service
import com.example.mvcpuredi.screens.common.DialogsNavigator
import com.example.mvcpuredi.screens.common.ScreensNavigator
import com.example.mvcpuredi.screens.common.ViewMvcFactory
import com.example.mvcpuredi.usecases.FetchUserDetailUseCase
import com.example.mvcpuredi.usecases.FetchUsersUseCase
import java.lang.reflect.Field

class Injector(private val component: PresentationComponent) {
    fun inject(client: Any) {
        for (field in getAllFields(client)) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field)
            }
        }
    }

    private fun getAllFields(client: Any): Array<out Field> {
        val clientCLass = client::class.java
        return clientCLass.declaredFields
    }

    private fun isAnnotatedForInjection(field: Field): Boolean {
        val fieldAnnotation = field.annotations
        for (annotation in fieldAnnotation) {
            if (annotation is Service) {
                return true
            }
        }
        return false
    }

    private fun injectField(client: Any, field: Field) {
        val isAccessibleInitially = field.isAccessible
        field.isAccessible = true
        field.set(client, getServiceForClass(field.type))
        field.isAccessible = isAccessibleInitially
    }

    private fun getServiceForClass(type: Class<*>): Any {
        when (type) {
            DialogsNavigator::class.java -> {
                return component.dialogsNavigator()
            }
            ScreensNavigator::class.java -> {
                return component.screensNavigator()
            }
            FetchUsersUseCase::class.java -> {
                return component.fetchUsersUseCase()
            }
            FetchUserDetailUseCase::class.java -> {
                return component.fetchUserDetailUseCase()
            }
            ViewMvcFactory::class.java -> {
                return component.viewMvcFactory()
            }
            else -> {
                throw Exception("unsupported service type: $type")
            }
        }
    }
}