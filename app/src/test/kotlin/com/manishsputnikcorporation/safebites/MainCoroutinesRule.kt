package com.manishsputnikcorporation.safebites

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@OptIn(ExperimentalCoroutinesApi::class)
class MainCoroutinesRule : TestRule {

  private val testCoroutineDispatcher = StandardTestDispatcher()

  override fun apply(base: Statement, description: Description): Statement =
      object : Statement() {
        override fun evaluate() {
          Dispatchers.setMain(testCoroutineDispatcher)
          base.evaluate()
          Dispatchers.resetMain()
        }
      }

  fun runTest(block: suspend TestScope.() -> Unit) = TestScope().runTest(testBody = block)
}
