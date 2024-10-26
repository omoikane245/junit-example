package com.example.junitexample.repository

import com.example.junitexample.mapper.entity.UserEntity
import com.example.junitexample.mapper.UserMapper
import com.example.junitexample.model.User
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@ExtendWith(MockKExtension::class)
class UserRepositoryTest {

	@InjectMockKs
	private lateinit var testSuite: UserRepository

	@MockK
	private lateinit var userMapper: UserMapper

	@BeforeEach
	fun before() {
		MockKAnnotations.init(this, relaxUnitFun = true)
		mockkStatic(LocalDateTime::class)
	}

	@Nested
	@DisplayName("Create系試験")
	inner class Create {

		private val now = LocalDateTime.of(2022, 12, 31, 0, 0)

		@Test
		@DisplayName("createで対象データの登録が行えるか確認")
		fun create_case1() {
			// setup
			val u001Entity= UserEntity(
				id = "U001",
				name = "USER0001",
				age = 20,
				emailAddress = "test@dmm.co.jp",
				createdBy = "junit",
				createdAt = now,
				updatedBy = "junit",
				updatedAt = now
			)
			val u001 = User(
				id = "U001",
				name = "USER0001",
				age = 20,
				emailAddress = "test@dmm.co.jp"
			)
			every { LocalDateTime.now() } returns now
			every { userMapper.bulkInsert(listOf(u001Entity)) } returns Unit

			// execute
			testSuite.create(listOf(u001), "junit")

			// verify
			verify { userMapper.bulkInsert(listOf(u001Entity)) }
		}
	}

	@Nested
	@DisplayName("FetchAll系試験")
	inner class FetchAll {

		private val now = LocalDateTime.of(2022, 12, 31, 0, 0)

		@Test
		@DisplayName("fetchAllで対象データを取得できるか確認")
		fun fetchAll_case1() {
			// setup
			val u002Record = UserEntity(
				id = "U002",
				name = "USER0002",
				age = 20,
				emailAddress = "test@dmm.co.jp",
				createdBy = "junit",
				createdAt = now,
				updatedBy = "junit",
				updatedAt = now
			)
			val u002Entity = UserEntity(
				id = "U002",
				name = "USER0002",
				age = 20,
				emailAddress = "test@dmm.co.jp"
			)
			every { userMapper.selectAll(listOf("U002")) } returns listOf(u002Record)

			// execute
			val actual = testSuite.fetchAll(listOf("U002"))

			// verify
			val expected = listOf(u002Entity)
			assertEquals(expected, actual)
			verify { userMapper.selectAll(listOf("U002")) }
		}
	}

	@Nested
	@DisplayName("Update系試験")
	inner class Update {

		private val now = LocalDateTime.of(2022, 12, 31, 0, 0)

		@Test
		@DisplayName("updateで対象データを更新できるか確認")
		fun update_case1() {
			// setup
			val u003Entity = UserEntity(
				id = "U003",
				name = "USER0003",
				age = 20,
				emailAddress = "test@dmm.co.jp",
				updatedBy = "junit",
				updatedAt = now
			)
			val u003 = User(
				id = "U003",
				name = "USER0003",
				age = 20,
				emailAddress = "test@dmm.co.jp"
			)
			every { LocalDateTime.now() } returns now
			every { userMapper.update(u003Entity) } returns Unit

			// execute
			testSuite.update(u003, "junit")

			// verify
			verify { userMapper.update(u003Entity) }
		}
	}

	@Nested
	@DisplayName("Delete系試験")
	inner class Delete {
		@Test
		@DisplayName("deleteで対象データを削除できるか確認")
		fun delete_case1() {
			// setup
			every { userMapper.delete("U004") } returns Unit

			// execute
			testSuite.delete("U004")

			// verify
			verify { userMapper.delete("U004") }
		}
	}
}
