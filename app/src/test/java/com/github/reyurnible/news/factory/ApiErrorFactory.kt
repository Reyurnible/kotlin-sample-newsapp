package com.github.reyurnible.news.factory

import com.github.reyurnible.news.repository.DomainError
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.factory
import com.github.salomonbrys.kodein.provider
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import okhttp3.ResponseBody
import org.mockito.Mockito
import retrofit2.Response

/**
 * Api Error Factory
 */
val errorFactory = Kodein.Module {
    // Domain Error
    bind<DomainError.Api>() with factory { statusCode: Int ->
        DomainError.Api(statusCode)
    }
    bind<DomainError.Network>() with provider {
        DomainError.Network
    }
    bind<DomainError.Unknown>() with provider {
        DomainError.Unknown(RuntimeException("mocked exception."))
    }

    // Retrofit Api Error
    bind<HttpException>("UnAuthorized") with provider {
        HttpException(Response.error<Any>(401, Mockito.mock(ResponseBody::class.java)))
    }
    bind<HttpException>("NotFound") with provider {
        HttpException(Response.error<Any>(404, Mockito.mock(ResponseBody::class.java)))
    }
    bind<HttpException>("Server") with provider {
        HttpException(Response.error<Any>(500, Mockito.mock(ResponseBody::class.java)))
    }

}
