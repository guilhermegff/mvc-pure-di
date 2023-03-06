package com.example.mvcpuredi

import com.example.mvcpuredi.screens.*
import com.example.mvcpuredi.usecases.FetchUserDetailUseCase
import com.example.mvcpuredi.usecases.FetchUsersUseCase
import java.lang.reflect.Field

class Injector(private val compositionRoot: PresentationCompositionRoot) {
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
                return compositionRoot.dialogsNavigator
            }
            ScreensNavigator::class.java -> {
                return compositionRoot.screensNavigator
            }
            FetchUsersUseCase::class.java -> {
                return compositionRoot.fetchUsersUseCase
            }
            FetchUserDetailUseCase::class.java -> {
                return compositionRoot.fetchUserDetailUseCase
            }
            ViewMvcFactory::class.java -> {
                return compositionRoot.viewMvcFactory
            }
            else -> {
                throw Exception("unsupported service type: $type")
            }
        }
    }
}