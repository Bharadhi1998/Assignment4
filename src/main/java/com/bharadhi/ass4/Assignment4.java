package com.bharadhi.ass4;

import com.bharadhi.ass3.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Assignment4  {
    Person person = new Person();
    HashMap<Integer, Person> detail = new HashMap<>();
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
         Assignment4 ass = new Assignment4();
        int choice = 0;
        while (choice != 5) {
            System.out.println("Menu");
            System.out.println("1.Add \n2.Update \n3.Display \n4.Save \n5.Exit");
            System.out.println("Enter your choice");
            choice = sc.nextInt();
            int index;
            if (choice == 1) {
                System.out.println("Enter the name: ");
                String name = sc.next();
                System.out.println("Enter the gender: ");
                String gender = sc.next();
                System.out.println("Enter the age: ");
                int age = sc.nextInt();
                System.out.println("Enter index: ");
                index = sc.nextInt();
                Person person = new Person(name, gender, age);
                ass.add(index, person);
            } else if (choice == 2) {
                System.out.println("Enter index: ");
                index = sc.nextInt();
                ass.update(index);
            } else if (choice == 3) {
                ass.display();
            } else if (choice == 4) {
                ass.save();
            }
        }
    }

        public void add(Integer index, Person person) {
            detail.put(index, person);
            System.out.println("Person is added");
        }

        public void display() {
            System.out.println(detail);
        }

        public void update(Integer index) {
            Person person = detail.get(index);
            System.out.println(person);
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the name: ");
            String name = sc.next();
            System.out.println("Enter the gender: ");
            String gender = sc.next();
            System.out.println("Enter the age: ");
            int age = sc.nextInt();
            person.setName(name);
            person.setGender(gender);
            person.setAge(age);
        }

        public void save() throws Exception {
            for (Map.Entry<Integer, Person> entry : detail.entrySet()) {
                Person value = entry.getValue();
                JAXBContext contextObj = JAXBContext.newInstance(Person.class);
                Marshaller marshallerObj = contextObj.createMarshaller();
                marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                String name = value.getName();
                marshallerObj.marshal(value, new FileOutputStream(name + ".xml"));
            }
        }
    }



