package com.example.airports;

import com.example.airports.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static java.lang.System.exit;

@SpringBootApplication
public class AirportsDataApplication implements CommandLineRunner {

	@Autowired
	private DataService dataService;

	public static void main(String[] args) throws Exception{
		SpringApplication app = new SpringApplication(AirportsDataApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0) {
			try {
				if(Integer.parseInt(args[0]) < 15 && Integer.parseInt(args[0]) > 0){
					System.out.println(dataService.getMessage(Integer.parseInt(args[0])));
				} else {
					System.out.println("Invalid argument");
					exit(0);
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid argument");
                exit(0);
			}
		} else {
			System.out.println(dataService.getMessage());
		}

		System.out.println(dataService.getData(new Scanner(System.in).nextLine()));

		exit(0);
	}

}
