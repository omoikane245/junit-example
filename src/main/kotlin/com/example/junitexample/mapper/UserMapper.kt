package com.example.junitexample.mapper

import com.example.junitexample.mapper.entity.UserEntity
import com.example.junitexample.model.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface UserMapper {
	fun selectAll(@Param("userIdList") userIdList: List<String>): List<UserEntity>

	fun bulkInsert(@Param("userList") userList: List<UserEntity>)

	fun update(@Param("user") user: UserEntity)

	fun delete(@Param("userId") userId: String)
}
