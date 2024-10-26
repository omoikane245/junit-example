package com.example.junitexample.mapper.entity

import java.time.LocalDateTime

data class UserEntity(
	val id: String,
	val name: String?,
	val age: Int?,
	val emailAddress: String?,
	val createdBy: String? = null,
	val createdAt: LocalDateTime? = null,
	val updatedBy: String? = null,
	val updatedAt: LocalDateTime? = null
)
