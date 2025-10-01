package librarycli;
import java.time.LocalDateTime;
import java.util.*;

public class Librarian extends User {
	Member member = null;
	int transID=1;
//
	public Librarian(String userID, String name, String email, String phoneNumber) {
		super(userID, name, email, phoneNumber);
		// TODO Auto-generated constructor stub
	}
	

	public void addLibrarian (Scanner sc, LibrarySystem library)
	{
		int flag=0;
		String id = null;
		String nama;
		String email;
		String phone;
		int countLoop=0;
		
		while(flag==0)
		{
			countLoop=0;
			System.out.print("Masukkan ID (UXXXX), X=[0-9]   : ");
			id=sc.nextLine();
			while(!id.matches("U\\d{4}")) {
				System.out.print("Masukkan ID (UXXXX), X=[0-9]   : ");
				id=sc.nextLine().trim();
			}
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
		System.out.print("Masukkan Nama : ");
		nama=sc.nextLine();
		System.out.print("Masukkan Email (@gmail.com) : ");
		email=sc.nextLine();
		while(!email.contains("@gmail.com")) {
			System.out.print("Masukkan Email (@gmail.com) : ");
			email=sc.nextLine();
		}
		System.out.print("Masukkan No. Telepon : ");
		phone=sc.nextLine();
		library.user.add(new Librarian(id, nama, email, phone));
	}
	public void printBorrowedMeeting()
	{
		if(member.getBorrowedMeeting().size()==0)
		{
			System.out.println("Tidak ada meeting room yang dipinjam");
			return;
		}
		for (int i=0; i<member.getBorrowedMeeting().size(); i++)
		{
			System.out.printf("%s\n", member.getBorrowedMeeting().get(i).toString());
		}
	}


	public void borrowMeeting(Scanner sc, LibrarySystem library)
	{
		int flag=0;
		library.printAvailableMeeting();
		if(library.getMeeting().size()==0)
		{
			return;
		}
		int tanda=0;
		String input = null;
		String inputID=null;
		
		while (tanda==0)
		{
			System.out.print("Masukkan ID User (Member) : ");
			inputID=sc.nextLine();
			for(int i=0; i<library.getUser().size(); i++)
			{
				if(library.getUser().get(i).getUserID().equals(inputID) && library.getUser().get(i)instanceof Member)
				{
					member= (Member) library.getUser().get(i);
					tanda=1;
				}
			}
			if(tanda==0)
			{
				System.out.println("User Not Found or User is a Librarian");
			}
		}
		
		
		while(flag==0)
		{
			System.out.print("Masukkan Nama Meeting Room : ");
			input=sc.nextLine();
			for (int i=0; i<library.getMeeting().size(); i++)
			{
				if(library.getMeeting().get(i).getName().equals(input))
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
				System.out.println("Meeting room not found");
			}
		}
		
		for (int i=0; i<library.getMeeting().size(); i++)
		{
			if(library.getMeeting().get(i).getName().equals(input))
			{
				int indexMember=0;
				member.getBorrowedMeeting().add(library.getMeeting().get(i));
				library.getTransaction().add(new Transaction(member, library.getMeeting().get(i),"Borrow",LocalDateTime.now(), transID));
				transID++;
				for(int j=0; j<member.getBorrowedMeeting().size(); j++)
				{
					if(member.getBorrowedMeeting().get(j).getName().equals(library.getMeeting().get(i).getName()))
					{
						indexMember=j;
						break;
					}
				}
				member.getBorrowedMeeting().get(indexMember).borrowItem();
				
				break;
			}
		}
	
	}
	public void returnMeeting(Scanner sc, LibrarySystem library)
	{
		
		String input=null;
		int flag=0;
		String inputID=null;
		int tanda=0;
		while (tanda==0)
		{
			System.out.print("Masukkan ID User (Member) : ");
			inputID=sc.nextLine();
			for(int i=0; i<library.getUser().size(); i++)
			{
				if(library.getUser().get(i).getUserID().equals(inputID) && library.getUser().get(i)instanceof Member)
				{
					member= (Member) library.getUser().get(i);
					tanda=1;
				}
			}
			if(tanda==0)
			{
				System.out.println("User Not Found or User is a Librarian");
			}
		}
		
		printBorrowedMeeting();
		if(member.getBorrowedMeeting().size()==0)
		{
			
			return;
		}
		while(flag==0)
		{
			System.out.print("Masukkan Nama Meeting Room : ");
			input=sc.nextLine();
			for (int i=0; i<member.getBorrowedMeeting().size(); i++)
			{
				if(member.getBorrowedMeeting().get(i).getName().equals(input))
				{
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
				System.out.println("Meeting room not found");
			}
		}
		
		
		for (int i=0; i<member.getBorrowedMeeting().size(); i++)
		{
			if (member.getBorrowedMeeting().get(i).getName().equals(input))
			{
				member.getBorrowedMeeting().get(i).returnItem();
				library.getTransaction().add(new Transaction(member, library.getMeeting().get(i),"Return",LocalDateTime.now(), transID));
				transID++;
		
				member.getBorrowedMeeting().remove(i);
				
				break;
			}
		}
		
	}
	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		System.out.println("Menu Librarian");
		System.out.println("================================");
		System.out.println("1. Add a Book");
		System.out.println("2. Add a Librarian");
		System.out.println("3. Add a Member");
		System.out.println("4. Process borrowing a Meeting Room key");
		System.out.println("5. Process returning a Meeting Room key");
		System.out.println("6. Show Transaction");
		System.out.println("7. Exit");
	}
	
}
