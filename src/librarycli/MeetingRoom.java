package librarycli;

public class MeetingRoom implements Borrowable {
	private String name;
	private int capacity;
	private int floor;
	private boolean available;
	
	public MeetingRoom(String name, int capacity, int floor, boolean available) {
		
		this.name = name;
		this.capacity = capacity;
		this.floor = floor;
		this.available = available;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	@Override
	public String toString() {
		return "Meeting Room Name = " + name + "\n" + "Capacity = " + capacity +"\n"+  "Floor = " + floor + "\n"+ "Available = " + available +"\n";
	}

	@Override
	public void borrowItem() {
		// TODO Auto-generated method stub
		setAvailable(false);
	}

	@Override
	public void returnItem() {
		// TODO Auto-generated method stub
		setAvailable(true);
	}

}
