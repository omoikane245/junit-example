package com.example.junitexample.repository

import com.example.junitexample.config.annotation.DbAccess
import com.example.junitexample.mapper.entity.UserEntity
import com.example.junitexample.mapper.UserMapper
import com.example.junitexample.model.User
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class UserRepository(
	private val userMapper: UserMapper
) {

	@DbAccess("ユーザ情報登録")
	fun create(users: List<User>, operatorId: String) {
		users.map {
			UserEntity(
				id = it.id,
				name = it.name,
				age = it.age,
				emailAddress = it.emailAddress,
				createdBy = operatorId,
				createdAt = LocalDateTime.now(),
				updatedBy = operatorId,
				updatedAt = LocalDateTime.now()
			)
		}.let {
			userMapper.bulkInsert(it)
		}
	}

	@DbAccess("ユーザ情報取得")
	fun fetchAll(userIds: List<String>): List<UserEntity> {
		return userMapper.selectAll(userIds).map {
			UserEntity(
				id = it.id,
				name = it.name ?: "名無し",
				age = it.age ?: 0,
				emailAddress = it.emailAddress ?: "未登録メールアドレス"
			)
		}
	}

	@DbAccess("ユーザ情報更新")
	fun update(user: User, operatorId: String) {
		userMapper.update(
			UserEntity(
				id = user.id,
				name = user.name,
				age = user.age,
				emailAddress = user.emailAddress,
				updatedBy = operatorId,
				updatedAt = LocalDateTime.now()
			)
		)
	}

	@DbAccess("ユーザ情報削除")
	fun delete(userId: String) {
		userMapper.delete(userId)
	}
}
