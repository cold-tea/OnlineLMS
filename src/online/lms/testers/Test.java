package online.lms.testers;

import java.util.Date;

import online.lms.models.Admin;
import online.lms.models.User;

public class Test {
	public static void main(String[] args) {
		User user = new User("userSome","passwordSome","Sandesh Pokhrel","Biratnagar-4","this@gmail.com",1001,"male",new Date()	);
		Admin admin = new Admin();
		admin.createUser(user);
	}
}
