package librarycli;
import java.util.*;
public class Book implements Borrowable {

	private String title;
	private String isbn;
	ArrayList<String> authors = new ArrayList<String>();
	private int availableCopies;
	private String publisher;
	
	
	
	
	public Book(String title, String isbn, ArrayList<String> authors, int availableCopies, String publisher) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.authors = authors;
		this.availableCopies = availableCopies;
		this.publisher = publisher;
	}
	
	public Book(String title, String isbn, int availableCopies, String publisher) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.availableCopies = availableCopies;
		this.publisher = publisher;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public ArrayList<String> getAuthors() {
		return authors;
	}

	public void setAuthors(ArrayList<String> authors) {
		this.authors = authors;
	}

	public int getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public void addBook(Scanner sc, LibrarySystem library)
	{
		String judulBuku;
		String isbn;
		String author;
		int available;
		String publisher;
		ArrayList<String>tempAuthor=new ArrayList<>();
	
		System.out.print("Masukkan Judul Buku : ");
		judulBuku=sc.nextLine();
		System.out.print("Masukkan ISBN [xxx-xxxx-xx-x], x adalah angka 0-9: ");
		isbn=sc.nextLine();
		System.out.print("Masukkan Author [0 for stop]: ");
		author=sc.nextLine();
		if (!author.equals("0"))
		{
			tempAuthor.add(author);
		}
	

		while(!author.equals("0"))
		{
			System.out.print("Masukkan Author [0 for stop]: ");
			author=sc.nextLine();
			if (!author.equals("0"))
			{
				tempAuthor.add(author);
			}
		}
		System.out.print("Masukkan Stok Buku : ");
		available=sc.nextInt();
		sc.nextLine();
		System.out.print("Masukkan Publisher : ");
		publisher=sc.nextLine();
		library.book.add(new Book(judulBuku, isbn,tempAuthor, available, publisher));
	}
	@Override
	public String toString() {
		return "Title = " + title +"\n"+ "ISBN = " + isbn +"\n"+ "Authors = " + authors +"\n"+ "Available Copies = "
				+ availableCopies+"\n" + "Publisher = " + publisher+ "\n";
	}
	
	@Override
	public void borrowItem() {
		// TODO Auto-generated method stub
		int temp=getAvailableCopies()-1;
		setAvailableCopies(temp);

	}
	

	@Override
	public void returnItem() {
		// TODO Auto-generated method stub
		int temp=getAvailableCopies()+1;
		setAvailableCopies(temp);
	}

}
