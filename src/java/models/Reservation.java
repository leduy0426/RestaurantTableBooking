package models;

public class Reservation {
    private int id;
    private String customerName;
    private String phone;
    private int tableNumber;
    private String reservationTime;
    private int numPeople;
    private String note;

    public Reservation() {
    }

    public Reservation(String customerName, String phone, int tableNumber, 
                       String reservationTime, int numPeople, String note) {
        this.customerName = customerName;
        this.phone = phone;
        this.tableNumber = tableNumber;
        this.reservationTime = reservationTime;
        this.numPeople = numPeople;
        this.note = note;
    }

    public Reservation(int id, String customerName, String phone, int tableNumber, 
                       String reservationTime, int numPeople, String note) {
        this.id = id;
        this.customerName = customerName;
        this.phone = phone;
        this.tableNumber = tableNumber;
        this.reservationTime = reservationTime;
        this.numPeople = numPeople;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
