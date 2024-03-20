CREATE DATABASE Biolife;


CREATE TABLE Users (
   ID INT identity PRIMARY KEY,
   FullName varchar(255),
    UserName VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL
);

EXEC sp_rename 'Users', 'users', 'OBJECT';

-- Đổi tên cột FullName thành full_name
--Caution: Changing any part of an object name could break scripts and stored procedures.
EXEC sp_rename 'Users.FullName', 'fullname', 'COLUMN';

-- Đổi tên cột UserName thành user_name
EXEC sp_rename 'Users.UserName', 'username', 'COLUMN';

-- Đổi tên cột Password thành password
EXEC sp_rename 'Users.Password', 'password', 'COLUMN';

-- Đổi tên cột Email thành email
EXEC sp_rename 'Users.Email', 'email', 'COLUMN';

ALTER TABLE Users
ALTER COLUMN FullName varchar(255) NULL; 

ALTER TABLE Users  
ALTER COLUMN UserName varchar(255) NULL;

ALTER TABLE Users
ALTER COLUMN Password varchar(255) NULL;

ALTER TABLE Users 
ALTER COLUMN Email varchar(255) NULL;

CREATE TABLE Roles (
    ID INT identity PRIMARY KEY,
    Name VARCHAR(255) NOT NULL
);

CREATE TABLE Suppliers (
    ID INT identity PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Contact VARCHAR(255)
);

CREATE TABLE Products (
    ID INT identity PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Description TEXT,
    Price DECIMAL(10, 2) NOT NULL,
    StockQuantity INT NOT NULL,
    SupplierID INT,
	[Image] VARCHAR(255) NOT NULL,
    FOREIGN KEY (SupplierID) REFERENCES Suppliers(ID)
);

CREATE TABLE Orders (
    ID INT identity PRIMARY KEY,
    CreatedDate DATE,
    TotalPrice DECIMAL(10, 2) NOT NULL,
    UserID INT,
	Status varchar(255),
    FOREIGN KEY (UserID) REFERENCES Users(ID)
);

CREATE TABLE Carts (
    ID INT identity PRIMARY KEY,
    UserID INT,
    ProductID INT,
    Quantity INT,
	TotalPrice DECIMAL(10,2),
    Items INT, 
    FOREIGN KEY (UserID) REFERENCES Users(ID),
    FOREIGN KEY (ProductID) REFERENCES Products(ID)
);

CREATE TABLE OrderProduct (
    ID INT identity PRIMARY KEY,
    OrderID INT,
    ProductID INT,
    Quantity INT,
	Price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(ID),
    FOREIGN KEY (ProductID) REFERENCES Products(ID)
);

--Insert table Suppliers
INSERT INTO Suppliers (Name, Contact)
VALUES
('Fuji Fruits','abc@email.com'),
('Fresh Farm', 'freshfarm@email.com'),
('HaNoi Fruits', 'juicy@email.com'),
('Gold Fruit', 'goldfruit@email.com'),
('Klever Fruits', 'klever@email.com');

--Insert table Users
INSERT INTO Users (FullName, UserName, Password, Email)
VALUES
('John Doe', 'john_doe', 'password123', 'john.doe@gmail.com'),
('Jane Smith', 'jane_smith', 'pass456', 'jane.smith@gmail.com'),
('Alice Johnson', 'alice_j', 'alicepassword', 'alice.j@gmail.com'),
('Bob Johnson', 'bob_j', 'bobpassword', 'bob.j@gmail.com'),
('Mary Davis', 'mary_d', 'marypassword', 'mary.d@gmail.com'),
('Michael Brown', 'michael_b', 'michaelpassword', 'michael.b@gmail.com'),
('Laura Wilson', 'laura_w', 'laurapassword', 'laura.w@gmail.com'),
('Daniel Clark', 'daniel_c', 'danielpassword', 'daniel.c@gmail.com'),
('Sarah Miller', 'sarah_m', 'sarahpassword', 'sarah.m@gmail.com'),
('James Taylor', 'james_t', 'jamespassword', 'james.t@gmail.com'),
('Jennifer Lee', 'jennifer_l', 'jenniferpassword', 'jennifer.l@gmail.com'),
('Kevin Johnson', 'kevin_j', 'kevinpassword', 'kevin.j@gmail.com'),
('Emily Davis', 'emily_d', 'emilypassword', 'emily.d@gmail.com'),
('Andrew Brown', 'andrew_b', 'andrewpassword', 'andrew.b@gmail.com'),
('Jessica Clark', 'jessica_c', 'jessicapassword', 'jessica.c@gmail.com'),
('Ryan Wilson', 'ryan_w', 'ryanpassword', 'ryan.w@gmail.com'),
('Sophia Smith', 'sophia_s', 'sophiapassword', 'sophia.s@gmail.com'),
('Matthew Davis', 'matthew_d', 'matthewpassword', 'matthew.d@gmail.com'),
('Amanda Johnson', 'amanda_j', 'amandapassword', 'amanda.j@gmail.com'),
('Ethan Miller', 'ethan_m', 'ethanpassword', 'ethan.m@gmail.com'),
('Olivia Taylor', 'olivia_t', 'oliviapassword', 'olivia.t@gmail.com'),
('Daniel Smith', 'daniel_s', 'danielpassword', 'daniel.s@gmail.com'),
('Grace Clark', 'grace_c', 'gracepassword', 'grace.c@gmail.com'),
('Nathan Davis', 'nathan_d', 'nathanpassword', 'nathan.d@gmail.com'),
('Sophia Brown', 'sophia_b', 'sophiapassword', 'sophia.b@gmail.com'),
('Vu Van Anh', 'vuvana', '123abc', 'vuvana@gmail.com'),
('Tran Thi Binh', 'tranthib', '456xyz', 'tranthib@gmail.com'),
('Nguyen Van Cuong', 'nguyenvanc', '789qwe', 'nguyenvanc@gmail.com'),
('Le Thi Dung', 'lethid', '147abc', 'lethid@gmail.com'),
('Pham Van Vinh', 'vinhpv', '258xyz', 'vinhpv@gmail.com');

--Insert table Products
INSERT INTO Products (Name, Description, Price, StockQuantity, SupplierID, [Image])
VALUES
('Apple', 'Fresh and juicy apples', 2.99, 100, 1, 'TAO-ENVY-T-FRUIT.jpg'),
('Banana', 'Ripe and sweet bananas', 1.99, 150, 1, 'banana.jpg"'),
('Orange', 'Juicy and tangy oranges', 3.49, 80, 2, 'orange.jpg"'),
('Strawberry', 'Sweet and succulent strawberries', 4.99, 120, 3, 'p-17.jpg"'),
('Grapes', 'Plump and delicious grapes', 3.79, 90, 4, 'grapes.jpg"'),
('Pineapple', 'Fresh and tropical pineapples', 2.49, 70, 1, 'pineapple.jpg'),
('Mango', 'Sweet and aromatic mangoes', 2.99, 100, 2, 'mango.jpg'),
('Watermelon', 'Refreshing watermelons', 0.99, 200, 3, 'watermelon270.jpg'),
('Peach', 'Juicy and flavorful peaches', 3.29, 110, 4, 'peach270.jpg'),
('Cherry', 'Delicious cherries', 5.99, 80, 1, 'cherry.jpg'),
('Blueberry', 'Sweet and nutritious blueberries', 4.49, 130, 2, 'blueberry.jpg'),
('Raspberry', 'Tangy and vibrant raspberries', 3.99, 100, 3, 'raspberry.jpg'),
('Kiwi', 'Tropical and tangy kiwis', 2.79, 90, 4, 'kiwi270.jpg'),
('Plum', 'Sweet and tart plums', 3.19, 120, 1, 'plum.jpg'),
('Lemon', 'Sour and refreshing lemons', 1.49, 180, 2, 'p-07.jpg'),
('Pear', 'Crisp and succulent pears', 2.69, 140, 3, 'pear.jpg'),
('Grapefruit', 'Tart and tangy grapefruits', 3.79, 100, 4, 'Grapefruit.jpg'),
('Apricot', 'Sweet and fragrant apricots', 3.99, 120, 1, 'apricot.jpg'),
('Cantaloupe', 'Sweet and aromatic cantaloupes', 1.99, 150, 2, 'cantaloupe.jpg'),
('Dragon Fruit', 'Exotic and visually stunning dragon fruits', 5.49, 60, 3, 'dragonfruit.jpg'),
('Avocado', 'Creamy and nutritious avocados', 4.99, 70, 4, 'p-01.jpg'),
('Coconut', 'Fresh and hydrating coconuts', 2.29, 200, 1, 'coconut.jpg'),
('Guava', 'Tropical and vitamin-rich guavas', 3.49, 90, 2, 'guava.jpg'),
('Passion Fruit', 'Tropical and aromatic passion fruits', 4.79, 80, 3, 'passionfruit.jpg'),
('Tomato', 'Cherry Tomato Seeds', 3.99, 100, 4, 'p-22.jpg');


--Insert table Orders
INSERT INTO Orders (CreatedDate, TotalPrice, UserID, Status)
VALUES
('2023-09-10', 150.99, 1, 'Completed'),
('2023-09-11', 75.49, 2, 'Pending'),
('2023-09-12', 90.00, 2, 'Completed'),
('2023-09-13', 120.00, 2, 'Pending'),
('2023-09-14', 200.00, 3, 'Completed'),
('2023-09-15', 180.00, 5, 'Completed'),
('2023-09-16', 160.00, 5, 'Pending'),
('2023-09-17', 110.00, 6, 'Completed'),
('2023-09-18', 250.00, 6, 'Completed'),
('2023-09-19', 300.00, 8, 'Pending'),
('2023-09-20', 180.00, 8, 'Pending'),
('2023-09-21', 220.00, 8, 'Completed'),
('2023-09-22', 150.00, 13, 'Completed'),
('2023-09-23', 170.00, 14, 'Pending'),
('2023-09-24', 120.00, 15, 'Completed'),
('2023-09-25', 200.00, 16, 'Completed'),
('2023-09-26', 140.00, 17, 'Pending'),
('2023-09-27', 230.00, 18, 'Completed'),
('2023-09-28', 170.00, 19, 'Pending'),
('2023-09-29', 150.00, 20, 'Completed'),
('2023-09-30', 180.00, 21, 'Pending'),
('2023-10-01', 200.00, 22, 'Completed'),
('2023-10-02', 210.00, 23, 'Completed'),
('2023-10-03', 140.00, 24, 'Pending'),
('2023-10-04', 190.00, 25, 'Pending'),
('2023-10-05', 270.00, 26, 'Completed'),
('2023-10-06', 320.00, 27, 'Pending'),
('2023-10-07', 260.00, 28, 'Completed'),
('2023-10-08', 170.00, 29, 'Pending'),
('2023-10-09', 230.00, 30, 'Pending');

--Insert table OrderProduct(error)
INSERT INTO OrderProduct (OrderID, ProductID, Quantity, Price)
VALUES
(1, 1, 5, 10.99),
(1, 2, 3, 15.99),
(2, 3, 7, 7.99),
(2, 1, 4, 12.99),
(3, 4, 2, 14.99),
(3, 5, 8, 11.99),
(4, 2, 6, 18.99),
(4, 3, 3, 9.99),
(5, 1, 10, 22.99),
(5, 5, 5, 17.99),
(6, 4, 8, 19.99),
(6, 2, 4, 13.99),
(7, 3, 5, 15.99),
(7, 5, 7, 21.99),
(8, 1, 3, 11.99),
(8, 4, 2, 12.99),
(9, 2, 6, 18.99),
(9, 3, 4, 16.99),
(10, 5, 5, 17.99),
(10, 1, 7, 24.99),
(11, 4, 4, 14.99),
(11, 2, 8, 23.99),
(12, 3, 6, 17.99),
(12, 5, 5, 19.99),
(13, 1, 7, 21.99),
(13, 4, 3, 12.99),
(14, 2, 9, 26.99),
(14, 3, 6, 18.99),
(15, 5, 4, 14.99),
(15, 1, 10, 27.99);

--Insert table Roles
INSERT INTO Roles (Name)
VALUES
('Admin'),
('User')
