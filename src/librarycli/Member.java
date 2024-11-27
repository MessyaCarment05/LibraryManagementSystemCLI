package librarycli;

import java.util.ArrayList;
import java.util.Scanner;

import librarycli.Book;
import librarycli.LibrarySystem;
import librarycli.MeetingRoom;
import librarycli.Member;
import librarycli.User;
public class Member extends User {
	int count=0;
	private ArrayList<Book>borrowed = new ArrayList<>();
	private ArrayList<MeetingRoom>borrowedMeeting = new ArrayList<>();
	public Member(String userID, String name, String email, String phoneNumber) {
		super(userID, name, email, phoneNumber);
		// TODO Auto-generated constructor stub
	}
	
	
	public ArrayList<MeetingRoom> getBorrowedMeeting() {
		return borrowedMeeting;
	}


	public void setBorrowedMeeting(ArrayList<MeetingRoom> borrowedMeeting) {
		this.borrowedMeeting = borrowedMeeting;
	}


	public ArrayList<Book> getBorrowed() {
		return borrowed;
	}
	public void setBorrowed(ArrayList<Book> borrowed) {
		this.borrowed = borrowed;
	}
	public void addMember (Scanner sc, LibrarySystem library)
	{
		int flag=0;
		int countLoop=0;
		String id = null;
		String nama;
		String email;
		String phone;
		while(flag==0)
		{
			countLoop=0;
			System.out.print("Masukkan ID (UXXXX), X=[0-9]   : ");
			id=sc.nextLine();
			for (int i=0; i<library.getUser().size(); i++)
			{
				if(!library.getUser().get(i).getUserID().equals(id))
				{
					countLoop++;
				}
			}
			
			if(countLoop==library.getUser().size())
			{
				flag=1;
			}
			else if(flag==0)
			{
				System.out.println("ID sudah terdapat dalam sistem");
			}
			
			
		}
		
		System.out.print("Masukkan Nama  : ");
		nama=sc.nextLine();
		System.out.print("Masukkan Email : ");
		email=sc.nextLine();
		System.out.print("Masukkan No. Telepon : ");
		phone=sc.nextLine();
		library.user.add(new Member(id, nama, email, phone));
	}
	public void printBorrowed()
	{
		if (borrowed.size()==0 && borrowedMeeting.size()==0)
		{
			System.out.println("Tidak ada buku dan meeting room yang dipinjam");
			return;
		}
		System.out.println("Meeting Room Borrowed : ");
		for (int i=0; i<borrowedMeeting.size(); i++)
		{
			System.out.printf("%s\n", borrowedMeeting.get(i).toString());
		}
		System.out.println();
		System.out.println("Book Borrowed : ");
		for (int i=0; i<borrowed.size(); i++)
		{
			System.out.printf("%s\n", borrowed.get(i).toString());
		}
	}
	public void borrowBook(Scanner sc, LibrarySystem library)
	{
		int flag=0;
		int index = 0;
		library.printAvailableBook();
		if (library.getBook().size()==0)
		{
			return;
		}
		String input=null;
		while(flag==0)
		{
			System.out.print("Masukkan Judul: ");
			input=sc.nextLine();
			for (int i=0; i<library.getBook().size(); i++)
			{
				if (library.getBook().get(i).getTitle().equals(input))
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
				System.out.println("Judul buku yang dimasukkan tidak tersedia");
			}
			
		}
		
		for (int i=0; i<library.getBook().size(); i++)
		{
			if (library.getBook().get(i).getTitle().equals(input))
			{
				borrowed.add(library.getBook().get(i));
				borrowed.get(count).borrowItem();
				count++;
				break;
			}
		}
		
	
		
	}
	public void returnBook(Scanner sc, LibrarySystem library)
	{
		String input = null;
		int flag=0;
		printBorrowed();
		if(borrowed.size()==0)
		{
			return;
		}
	
	
		while(flag==0)
		{
			System.out.print("Masukkan Judul: ");
			input=sc.nextLine();
			for (int i=0; i<borrowed.size(); i++)
			{
				if (borrowed.get(i).getTitle().equals(input))
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
				System.out.println("Judul buku yang dimasukkan tidak ada di daftar pinjam");
			}
			
		}
		for (int i=0; i<borrowed.size(); i++)
		{
			if (borrowed.get(i).getTitle().equals(input))
			{
				borrowed.get(i).returnItem();
				borrowed.remove(i);
				count--;
				break;
			}
		}
		
	}
	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		
		System.out.println("Menu Member (Self-Service)");
		System.out.println("===========================");
		System.out.println("1. Show my borrowed item(s)");
		System.out.println("2. Borrow a book");
		System.out.println("3. Return a book");
		System.out.println("4. Exit");
	}

}
