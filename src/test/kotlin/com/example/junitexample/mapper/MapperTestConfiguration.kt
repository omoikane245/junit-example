package com.example.junitexample.mapper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import java.util.Properties
import javax.sql.DataSource

@SpringBootApplication
class MapperTestConfiguration {

	@Bean
	@ConfigurationProperties("datasource")
	fun datasource(): DataSource {
		val datasource = SimpleDriverDataSource()
		datasource.setDriverClass(com.mysql.jdbc.Driver::class.java)
		datasource.username = "root"
		datasource.password = "root"
		datasource.url = "jdbc:mysql://localhost:3306/test"
		val properties = Properties()
		properties.setProperty("autoCommit", "false")
		datasource.connectionProperties = properties
		return datasource
	}
}
