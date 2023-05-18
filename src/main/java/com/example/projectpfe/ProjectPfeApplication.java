package com.example.projectpfe;

import com.example.projectpfe.model.UserRole;
import com.example.projectpfe.pojo.emuns.RoleEnum;
import com.example.projectpfe.pojo.req.AuthReq;
import com.example.projectpfe.pojo.req.UserReq;
import com.example.projectpfe.pojo.req.UserRoleReq;
import com.example.projectpfe.repository.UserRepository;
import com.example.projectpfe.repository.UserRoleRepository;
import com.example.projectpfe.service.AuthService;
import com.example.projectpfe.service.UserRoleService;
import com.example.projectpfe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })

public class ProjectPfeApplication {
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserService userService;


	@Autowired
	private AuthService authService;
	public static void main(String[] args) {
		SpringApplication.run(ProjectPfeApplication.class, args);
	}

	@Bean
	CommandLineRunner start() {
		return args -> {
			// auth

			AuthReq authReq1 = new AuthReq();
			authReq1.setIgg("L9012359");
			authReq1.setPassword("azerty");
			authService.createAuth(authReq1);

			AuthReq authReq2 = new AuthReq();
			authReq2.setIgg("J9012300");
			authReq2.setPassword("azerty");
			authService.createAuth(authReq2);


			AuthReq authReq3 = new AuthReq();
			authReq3.setIgg("J9012399");
			authReq3.setPassword("azerty");
			authService.createAuth(authReq3);

			AuthReq authReq4 = new AuthReq();
			authReq4.setIgg("J9012377");
			authReq4.setPassword("azerty");
			authService.createAuth(authReq4);

			AuthReq authReq5 = new AuthReq();
			authReq5.setIgg("J9012366");
			authReq5.setPassword("azerty");
			authService.createAuth(authReq5);


			UserRoleReq roleOffer = new UserRoleReq();
			roleOffer.setRole(RoleEnum.OFFER);
			UserRole roleOffre1=  userRoleService.createUserRole(roleOffer);

			UserRoleReq roleReferential = new UserRoleReq();
			roleReferential.setRole(RoleEnum.REFERENTIAL);
			UserRole roleReferential1= userRoleService.createUserRole(roleReferential);

			UserRoleReq delegation = new UserRoleReq();
			delegation.setRole(RoleEnum.DELEGATION);
			UserRole roleDelegation1 = userRoleService.createUserRole(delegation);

			UserRoleReq contactCadre = new UserRoleReq();
			contactCadre.setRole(RoleEnum.CONTRACT_CADRE);
			 UserRole contactCadre1=userRoleService.createUserRole(contactCadre);

			UserRoleReq validatorContractCadre = new UserRoleReq();
			validatorContractCadre.setRole(RoleEnum.VALIDATOR_CONTRACT_CADRE);
			userRoleService.createUserRole(validatorContractCadre);

			UserRoleReq report = new UserRoleReq();
			report.setRole(RoleEnum.REPORT);
			userRoleService.createUserRole(report);

			UserRoleReq document = new UserRoleReq();
			document.setRole(RoleEnum.DOCUMENT);
			userRoleService.createUserRole(document);

			UserRoleReq contractMass = new UserRoleReq();
			contractMass.setRole(RoleEnum.CONTRACT_MASS);
			userRoleService.createUserRole(contractMass);

			UserRoleReq materRole = new UserRoleReq();
			materRole.setRole(RoleEnum.MASTER_ROLE);
			userRoleService.createUserRole(materRole);

			//list roles

			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(roleOffre1);
			userRoles.add(roleDelegation1);
			userRoles.add(roleReferential1);

			Set<UserRole> userRoles2 = new HashSet<>();
			userRoles2.add(roleOffre1);
			userRoles2.add(roleDelegation1);

			//users

			UserReq user1 = new UserReq();
			user1.setIgg("J1234567");
			user1.setFirstName("sanae");
			user1.setLastName("aoubahassou");
			user1.setCountry("Maroc");
			user1.setCity("Casa");
			user1.setUserRoles(userRoles);
			userService.createUser(user1);

			UserReq user2 = new UserReq();
			user2.setIgg("J9999998");
			user2.setFirstName("soso");
			user2.setLastName("boubahassou");
			user2.setCountry("Maroc");
			user2.setCity("Rabat");
			user2.setUserRoles(userRoles2);
			userService.createUser(user2);

			UserReq user3 = new UserReq();
			user3.setIgg("J9654308");
			user3.setFirstName("laila");
			user3.setLastName("zadouk");
			user3.setCountry("Maroc");
			user3.setCity("fes");
		    userService.createUser(user3);


			UserReq user4 = new UserReq();
			user4.setIgg("L9012359");
			user4.setFirstName("fati");
			user4.setLastName("zadouk");
			user4.setCountry("Maroc");
			user4.setCity("errachia");
			userService.createUser(user4);

			UserReq user5 = new UserReq();
			user5.setIgg("J9012300");
			user5.setFirstName("halima");
			user5.setLastName("el tachari");
			user5.setCountry("Maroc");
			user5.setCity("kenitra");
			userService.createUser(user5);


			UserReq user6 = new UserReq();
			user6.setIgg("J9012399");
			user6.setFirstName("salma");
			user6.setLastName("nomsalma");
			user6.setCountry("Maroc");
			user6.setCity("kenitra");
			userService.createUser(user6);


		};
	}

}
