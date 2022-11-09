package com.example.mdbspringboot;

import com.example.mdbspringboot.model.Events;
import com.example.mdbspringboot.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
@EnableMongoRepositories
public class ReminderApplication implements CommandLineRunner {

	@Autowired
	ItemRepository reminderItemRepo;

	public static void main(String[] args) {
		SpringApplication.run(ReminderApplication.class, args);
	}

	@Override
	public void run(String... args) {
		reminderItemRepo.deleteAll();
		System.out.println("\t\t\tCREATE Events\n");
		createEvent();
		System.out.println("\t\t\tDISPLAY Events\n");
		showAllEvents();
		System.out.println("\t\t\tDISPLAY Events by Name\n");
		getUserByName("Lokesh");
		updateEventName("Session1");
	}

	//Create
	public void createEvent() {
		System.out.println("Creating events");
		reminderItemRepo.save(new Events("1", "Lokesh", "Session1", "2022/11/10", "10:00"));
		reminderItemRepo.save(new Events("2", "Harsha", "Session2", "2022/11/11", "11:00"));
		reminderItemRepo.save(new Events("3", "Ram", "Session1", "2022/11/10", "10:00"));
		System.out.println("Done creating events.");
	}

	//Read
	//1. Find all items
	public void showAllEvents() {
		reminderItemRepo.findAll().forEach(item -> System.out.println(getEventDetails(item)));
	}

	//2. Find item by name
	public void getUserByName(String name) {
		System.out.println("Getting user by name..." + name);
		Events item = reminderItemRepo.findItemByName(name);
		System.out.println(getEventDetails(item));
	}

	//3. Get name and quantity of all items in a category
	public void readeventsByevent(String event) {
		System.out.println("Getting events by event..." + event);
		List<Events> list = reminderItemRepo.findAll();
		list.forEach(item -> System.out.println("Name: " + item.getName() + " Event: " + item.getEvent()));
	}

//	public void findCountOfGroceryItems() {
//		long count = reminderItemRepo.count();
//		System.out.println("Count: " + count);
//	}




	//Update
	public void updateEventName(String event) {
		String newEvent = "Session3";
		List<Events> list = reminderItemRepo.findAll();
		list.forEach(item -> {
			//item.setEvent(newEvent);
			if(item.getEvent().equals(event)){
				item.setEvent(newEvent);
				reminderItemRepo.save(item);
			}
		});

		List<Events> itemsUpdated = reminderItemRepo.saveAll(list);
		if(itemsUpdated != null) {
			System.out.println("Event updated to " + itemsUpdated.size() + " items.");
		}
	}

	//Delete
	public void deleteGroceryItem(String id) {
		reminderItemRepo.deleteById(id);
		System.out.println("Item with id " + id + " deleted.");
	}

	//Print item details
	public String getEventDetails(Events item) {
		System.out.println(
				"Item Name: " + item.getName() + " | " +
						"Item Event: " + item.getEvent() + " | " +
						"Item Date: " + item.getDate() + " | " +
						"Item Time: " + item.getTime()
		);
		return "";
	}

}
