package com.github.reyurnible.news.helper

import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.Times
import org.mockito.stubbing.Answer
import org.mockito.stubbing.Stubber

interface CanUseEasyMockito<out T> {
    fun createTestTarget(): T

    fun Want(toBeReturned: Any): Stubber = Mockito.doReturn(toBeReturned)
    fun WantNull(): Stubber = Mockito.doReturn(null)
    fun WannaDoNothing(): Stubber = Mockito.doNothing()
    fun WannaThrow(toBeThrown: Throwable): Stubber = Mockito.doThrow(toBeThrown)
    fun <T> WannaDo(answer: Answer<T>): Stubber = Mockito.doAnswer(answer)

    fun <T> Stubber.When(mocked: T): T = `when`(mocked)

    fun doMockInvocations(f: () -> Unit) {
        f()
    }

    fun <T> T.toSpy(): T = Mockito.spy(this)

    fun <T> T.verifyInvocations(times: Int): T = Mockito.verify(this, Times(times))

    fun <T> verify(mocked: T, times: Int): T = Mockito.verify(mocked, Times(times))

    fun initMocks() {
        MockitoAnnotations.initMocks(this)
    }

    fun validate() {
        Mockito.validateMockitoUsage()
    }
}

inline fun <reified T, U> CanUseEasyMockito<U>.createMock(): T where T : Any = Mockito.mock(T::class.java)
inline fun <reified T> CanUseEasyMockito<T>.given(testCase: (T) -> Unit) where T : Any {
    testCase(createTestTarget())
}
