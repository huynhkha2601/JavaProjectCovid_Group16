USE [DB_HeThongThanhToan]
GO
/****** Object:  Table [dbo].[Account_Bank]    Script Date: 11/26/2021 3:07:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account_Bank](
	[ID] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[Balance] [float] NULL,
	[Role] [nvarchar](50) NULL,
 CONSTRAINT [PK_Account_Bank] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 11/26/2021 3:07:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[BankID] [nvarchar](50) NOT NULL,
	[Fullname] [nvarchar](50) NULL,
 CONSTRAINT [PK_Customer] PRIMARY KEY CLUSTERED 
(
	[BankID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Transaction]    Script Date: 11/26/2021 3:07:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transaction](
	[Customer_ID] [nvarchar](50) NOT NULL,
	[money] [float] NULL,
	[Content_] [nvarchar](50) NULL,
	[Record] [datetime] NOT NULL,
 CONSTRAINT [PK_Transaction_1] PRIMARY KEY CLUSTERED 
(
	[Customer_ID] ASC,
	[Record] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD  CONSTRAINT [FK_Customer_Account_Bank] FOREIGN KEY([BankID])
REFERENCES [dbo].[Account_Bank] ([ID])
GO
ALTER TABLE [dbo].[Customer] CHECK CONSTRAINT [FK_Customer_Account_Bank]
GO
ALTER TABLE [dbo].[Transaction]  WITH CHECK ADD  CONSTRAINT [FK_Transaction_Customer1] FOREIGN KEY([Customer_ID])
REFERENCES [dbo].[Customer] ([BankID])
GO
ALTER TABLE [dbo].[Transaction] CHECK CONSTRAINT [FK_Transaction_Customer1]
GO
ALTER TABLE [dbo].[Account_Bank]  WITH CHECK ADD  CONSTRAINT [CK_Account_Bank] CHECK  (([Role]=N'Admin' OR [Role]='User' OR [Role]='Manager'))
GO
ALTER TABLE [dbo].[Account_Bank] CHECK CONSTRAINT [CK_Account_Bank]
GO
