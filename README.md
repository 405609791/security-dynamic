# security-dynamic
动态设置安全权限，数据库中怎加menu、menu_role

#pom.xml
	1、首先锁定数据库版本号：
	<dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
			//下
            <version>8.0.18</version>
    </dependency>
	2、添加数据库连接池
	<dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>1.1.21</version>
    </dependency>
	3、配置properties 文件
	
# 登录报错
		Invalid bound statement (not found): fun.codefarmer.securitydynamic.mapper.UserMapper.getUerByUsername
		mapper.xml namespace是否正确。
		如果正确，如果dao层和xml放在一个文件见，需要配置pom.xml 的<build> 中加入
		<resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
            </resource>
        </resources>
# mapper.xml 中 返回集合 注意事项：column='sql中的字段名' property='实体类字段名'
		<resultMap id="BaseResultMap" type="fun.codefarmer.securitydynamic.pojo.Menu">
			<id property="id" column="id"/>
			<result property="pattern" column="pattern"/>
			<collection property="roles" ofType="fun.codefarmer.securitydynamic.pojo.Role">
				<id property="id" column="rid"/>
				<result property="name" column="rname"/>
				<result property="nameZh" column="rnameZh"/>
			</collection>
		</resultMap>	
		
	