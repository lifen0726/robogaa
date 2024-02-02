CREATE TABLE Products (
    id int not null PRIMARY KEY identity(1, 1),
    name NVARCHAR(255) NOT NULL,
    price int not null,
    quantity NVARCHAR(50) NOT NULL,
	date date not null,
	note nvarchar(200) not null);

SELECT TOP (1000) [id]
      ,[name]
      ,[price]
      ,[quantity]
  FROM [lifenlu].[dbo].[Products]

 CREATE TABLE members (
    ID INT not null PRIMARY KEY identity(1000, 1),
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL, -- 假設密碼經過加密
    phone VARCHAR(20) NOT NULL
);
