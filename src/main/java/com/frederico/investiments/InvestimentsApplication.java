package com.frederico.investiments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvestimentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestimentsApplication.class, args);
	}

//    @Bean
//    ApplicationRunner caching(InvestorRepository repo){
//        return args -> Set.of("Felix", "Garfield")
//                .stream()
//                .map(name -> repo.save(new Investor(null, 1, name, null)))
//                .forEach(inv -> {
//                    var id = inv.id();
//                    var one = repo.findById(id);
//                    var two = repo.findById(id);
//                  //  Assert.state(one.uuid.equals(two.uuid), "the UUID matches, even though we didn't persist it");
//                    Assert.state( one  != two  , "they shouldn't be the same references, though");
//                });
//    }

}
