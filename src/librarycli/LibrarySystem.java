package librarycli;

import java.util.*;



import java.time.LocalDateTime;
public class LibrarySystem {
	int indexUser;
	int transID=1;
	Scanner sc=new Scanner(System.in);
	ArrayList<User>user=new ArrayList<>();
	ArrayList<Book> book=new ArrayList<>();
	ArrayList<Transaction> transaction=new ArrayList<>();
	ArrayList<MeetingRoom> meeting=new ArrayList<>();
	//Member memberCast=null;
	public LibrarySystem() {
		super();
	}
	
	public ArrayList<User> getUser() {
		return user;
	}

	public void setUser(ArrayList<User> user) {
		this.user = user;
	}

	public ArrayList<Book> getBook() {
		return book;
	}

	public void setBook(ArrayList<Book> book) {
		this.book = book;
	}

	public ArrayList<MeetingRoom> getMeeting() {
		return meeting;
	}

	public void setMeeting(ArrayList<MeetingRoom> meeting) {
		this.meeting = meeting;
	}
	
	public ArrayList<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(ArrayList<Transaction> transaction) {
		this.transaction = transaction;
	}

	public void systemMenu()
	{
		initAll();
		int stay=1;
		String inputID = null;
		while(stay==1)
		{
			System.out.print("Input UserID [0 to finish]: ");
			inputID=sc.nextLine();
			int search=searchUser(inputID);	
			while(search==0)
			{
				System.out.println("User Not Found");
				System.out.print("Input UserID [0 to finish]: ");
				inputID=sc.nextLine();
				search=searchUser(inputID);	
			}
		
			System.out.println("User Information : ");
			viewInformationUser(indexUser);
			if (search==1)
			{
				
				int stayLibrarian=1;
				int librarianChoice;
				while (stayLibrarian==1)
				{
					user.get(indexUser).showMenu();
					System.out.print("Choice: ");
					librarianChoice=sc.nextInt();
					sc.nextLine();
					Librarian librarianTemp=(Librarian) user.get(indexUser);
					if(librarianChoice==1)
					{
						String judulBuku=null;
						String isbn=null;
						String author=null;
						int available=0;
						String publisher=null;
						ArrayList<String>tempAuthor=new ArrayList<>();					
						Book temp=new Book(judulBuku, isbn, tempAuthor, available, publisher);
						temp.addBook(sc, this);
						
					}
					else if(librarianChoice==2)
					{
						
						String id = null;
						String nama = null;
						String email = null;
						String phone = null;
						Librarian temp=new Librarian(id, nama,email, phone);
						temp.addLibrarian(sc, this);
				
					}
					else if(librarianChoice==3)
					{
						String id = null;
						String nama = null;
						String email = null;
						String phone = null;
						Member temp=new Member(id, nama,email, phone);
						temp.addMember(sc, this);
					
					}
					else if(librarianChoice==4)
					{
						librarianTemp.borrowMeeting(sc, this);						
					}
					else if(librarianChoice==5)
					{
						librarianTemp.returnMeeting(sc, this);
					}
					else if(librarianChoice==6)
					{
						viewTransaction();
					}
					else if(librarianChoice==7)
					{
						stayLibrarian=0;
					}
					
					
				}
				
			}
			else if (search==2)
			{
				
				int stayMember=1;
				int memberChoice ;
				while (stayMember==1)
				{
					user.get(indexUser).showMenu();
					System.out.print("Choice: ");
					memberChoice=sc.nextInt();
					sc.nextLine();
					Member memberTemp=(Member) user.get(indexUser);
					if(memberChoice==1)
					{
						memberTemp.printBorrowed();
					}
					else if(memberChoice==2)
					{
						memberTemp.borrowBook(sc, this);
					}
					else if(memberChoice==3)
					{
						memberTemp.returnBook(sc, this);
					}
					else if(memberChoice==4)
					{
						stayMember=0;
						
					}
					
				}
				
			}
			
		}
		
		
		
	}
	public void initAll()
	{
		user.add(new Librarian("U0001", "Alicia", "alicia@gmail.com", "08728716331"));
		user.add(new Member("U0002", "Kevin", "kevin@gmail.com", "0812812912"));
		meeting.add(new MeetingRoom("Meeting Room A", 10, 1, true));
		meeting.add(new MeetingRoom("Meeting Room B", 20, 2, true));
		meeting.add(new MeetingRoom("Meeting Room C", 30, 3, true));
	}
	public int searchUser(String userID)
	{
		for (int i=0; i<user.size(); i++)
		{
			if(user.get(i).getUserID().equals(userID))
			{
				if(user.get(i) instanceof Librarian)
				{
					indexUser=i;
					return 1;
				}
				else if(user.get(i) instanceof Member)
				{
					indexUser=i;
					return 2;
				}
				
			
				
			}
		}
		
		return 0;
	}
	public void printAvailableBook()
	{
		if(book.size()==0)
		{
			System.out.println("Tidak ada buku yang tersedia");
			return;
		}
		else
		{
			for (int i=0; i<book.size(); i++)
			{
				
				if(book.get(i).getAvailableCopies()>0)
				{
				
					System.out.printf("%s\n", book.get(i).toString());
					
				}
			}
		}
	}
	public void printAvailableMeeting()
	{
		if(meeting.size()==0)
		{
			System.out.println("Tidak ada meeting room yang tersedia");
			return;
		}
		else
		{
			for (int i=0; i<meeting.size(); i++)
			{
				if(meeting.get(i).isAvailable()==true)
				{

					System.out.printf("%s\n", meeting.get(i).toString());
				}
			}
		}
	}
	public void viewInformationUser(int indexUser)
	{
		if(user.size()==0)
		{
			System.out.println("Tidak ada user");
			return;
		}
		else
		{
			System.out.printf("%s\n", user.get(indexUser).toString());
		}
	}
	
	public int countCopiesBook() {
		int countCopies=0;
		for (int i=0; i<book.size(); i++) {
			countCopies+=book.get(i).getAvailableCopies();
		}
		return countCopies;
	}
	public void viewTransaction()
	{
		if(transaction.size()==0)
		{
			System.out.println("Tidak ada transaksi");
			return;
		}
		System.out.println("===============================================================================================================================");
		System.out.println(String.format("| %-10s | %-15s | %-18s | %-15s | %-15s | %-30s |", "TransactionID", "Transaction Type","User Name", "User ID", "Item","Transaction Date"));
		System.out.println("===============================================================================================================================");
		for(int i=0; i<transaction.size(); i++)
		{
			transaction.get(i).viewsTransaction();
		}
		System.out.println("===============================================================================================================================");
		
	}
}
