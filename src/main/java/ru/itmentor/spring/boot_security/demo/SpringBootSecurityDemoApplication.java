package ru.itmentor.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.services.RoleService;
import ru.itmentor.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {
	private final RoleService roleService;
	private final UserService userService;

	@Autowired
	public SpringBootSecurityDemoApplication(RoleService roleService, UserService userService) {
		this.roleService = roleService;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}



	}
