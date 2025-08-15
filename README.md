# 🚌 Bus Ticket Reservation System

A simple **Java JDBC** project to manage bus ticket bookings.  
This system allows passengers to view available buses, book tickets, and manage bookings.  
It connects to a MySQL database for storing and retrieving data.

---

## 📌 Features
- View all available buses (covering all 33 districts of Telangana)
- Add passenger details
- Book bus tickets
- Store and retrieve data from MySQL
- Simple console-based interface

---

## 🛠️ Technologies Used
- **Java** (Core Java, JDBC)
- **MySQL** (Database)
- **Eclipse IDE**
- **Git & GitHub** (Version Control)

---

## 📂 Project Structure
```

BusTicketReservation/
│
├── Bus.java           # Handles bus details
├── Passenger.java     # Stores passenger information
├── Booking.java       # Handles booking logic
└── README.md          # Project documentation

````

---

## ⚙️ Database Setup
1. **Create Database**
```sql
CREATE DATABASE busreservation;
USE busreservation;
````

2. **Create Tables**

```sql
CREATE TABLE bus (
    bus_no INT PRIMARY KEY,
    ac BOOLEAN,
    capacity INT,
    source VARCHAR(50),
    destination VARCHAR(50)
);

CREATE TABLE passenger (
    name VARCHAR(50),
    gender VARCHAR(10),
    age INT
);

CREATE TABLE booking (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    bus_no INT,
    passenger_name VARCHAR(50),
    date DATE,
    FOREIGN KEY (bus_no) REFERENCES bus(bus_no)
);
```

3. **Insert Bus Data (Example)**

```sql
INSERT INTO bus VALUES (101, true, 45, 'Hyderabad', 'Warangal');
INSERT INTO bus VALUES (102, false, 50, 'Nizamabad', 'Karimnagar');
```

---

## 🚀 How to Run

1. Clone this repository:

```bash
git clone https://github.com/your-username/BusTicketReservation.git
```

2. Open the project in **Eclipse** or any Java IDE.
3. Configure MySQL username & password in the JDBC connection string in `Booking.java`.
4. Compile and run the main class.

---

## 📸 Screenshots

<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/2d1ae7f6-6be5-4c10-949d-488420e13402" />
<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/ff5cdc04-1f01-46b9-aaa7-92e8eb2b390d" />
<img width="1366" height="768" alt="image" src="https://github.com/user-attachments/assets/594c1fc1-c3fe-4aaa-b919-d2ca4500079a" />


## 👩‍💻 Author

**Jayasri Cheekatla**
[GitHub Profile](https://github.com/chjayasri30)

---

