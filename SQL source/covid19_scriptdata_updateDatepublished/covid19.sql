USE [master]
GO
/*
IF DB_ID('[Covid-19]') IS NOT NULL
	ALTER DATABASE [Covid-19] 
	SET SINGLE_USER 
	WITH ROLLBACK IMMEDIATE;
	GO
	DROP DATABASE [Covid-19]
GO 
*/
/****** Object:  Database [Covid-19]    Script Date: 11/26/2021 5:04:59 PM ******/
CREATE DATABASE [Covid-19]
GO
USE [Covid-19]
GO
/****** Object:  Table [dbo].[ACCOUNT]    Script Date: 11/26/2021 5:04:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[ACCOUNT](
	[USERNAME] [varchar](30) NOT NULL,
	[PASSWORD] [varchar](150) NULL,
	[ROLE] [varchar](10) NOT NULL,
	[USERID] [varchar](10) NULL,
	[ACTIVATED] [int] default(1),
	[DATEPUBLISHED] [date] NULL,
 CONSTRAINT [PK_ACCOUNT] PRIMARY KEY CLUSTERED 
(
	[USERNAME] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ACCOUNTHISTORY]    Script Date: 11/26/2021 5:04:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ACCOUNTHISTORY](
	[USERNAME] [varchar](30) NOT NULL,
	[RECORD_LOGIN] [datetime] NOT NULL,
	[RECORD_LOGOUT] [datetime] NOT NULL,
 CONSTRAINT [PK_ACCOUNTHISTORY] PRIMARY KEY CLUSTERED 
(
	[USERNAME] ASC,
	[RECORD_LOGIN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MANAGEDPERSON]    Script Date: 11/26/2021 5:04:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MANAGEDPERSON](
	[ID] [varchar](10) NOT NULL,
	[FULLNAME] [nvarchar](100) NULL,
	[YEAROFBIRTH] [int] NULL,
	[ADDRESS] [nvarchar](150) NULL,
	[STATUS] [nvarchar](20) NOT NULL,
	[TREATMENT] [varchar](10) NULL,
	[DEBT] [float] DEFAULT (0),
 CONSTRAINT [PK_MANAGEDPERSON] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MANAGEHISTORY]    Script Date: 11/26/2021 5:04:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MANAGEHISTORY](
	[ID] [varchar](10) NOT NULL,
	[FROMSTATUS] [nvarchar](20) NOT NULL,
	[TOSTATUS] [nvarchar](20) NOT NULL,
	[RECORD] [datetime] NOT NULL,
 CONSTRAINT [PK_MANAGEHISTORY_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC,
	[FROMSTATUS] ASC,
	[RECORD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PACKAGE]    Script Date: 11/26/2021 5:04:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PACKAGE](
	[ID] [varchar](10) NOT NULL,
	[NAME] [nvarchar](100) NULL,
	[LIMITNUM] [int] NULL,
	[LIMITTIME] [date] NULL,
	[PRICE] [float] not NULL,
	[QUANTITY] [int] not NULL,
 CONSTRAINT [PK_PACKAGE] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PACKAGEREGISTER]    Script Date: 11/26/2021 5:04:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PACKAGEREGISTER](
	[PERSONID] [varchar](10) NOT NULL,
	[PACKAGEID] [varchar](10) NOT NULL,
	[RECORD] [datetime] NOT NULL,
	[QUANTITY] [int] NOT NULL,
 CONSTRAINT [PK_PACKAGEREGISTER] PRIMARY KEY CLUSTERED 
(
	[PERSONID] ASC,
	[PACKAGEID] ASC,
	[RECORD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PAYMENTHISTORY]    Script Date: 11/26/2021 5:04:59 PM ******/
--SET ANSI_NULLS ON
--GO
--SET QUOTED_IDENTIFIER ON
--GO
--SET ANSI_PADDING ON
--GO
--CREATE TABLE [dbo].[PAYMENTHISTORY](
--	[ID] [varchar](10) NOT NULL,
--	[RECORD] [datetime] NOT NULL,
--	[MONEY] [float] NOT NULL,
-- CONSTRAINT [PK_PAYMENTHISTORY] PRIMARY KEY CLUSTERED 
--(
--	[ID] ASC,
--	[RECORD] ASC,
--	[MONEY] ASC
--)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
--) ON [PRIMARY]

--GO
--SET ANSI_PADDING OFF
--GO
/****** Object:  Table [dbo].[RELATED]    Script Date: 11/26/2021 5:04:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RELATED](
	[PERSON_ID1] [varchar](10) NOT NULL,
	[PERSON_ID2] [varchar](10) NOT NULL,
	[RECORD] [datetime] NOT NULL,
 CONSTRAINT [PK_RELATED] PRIMARY KEY CLUSTERED 
(
	[PERSON_ID1] ASC,
	[PERSON_ID2] ASC,
	[RECORD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TREATMENTPLACE]    Script Date: 11/26/2021 5:04:59 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TREATMENTPLACE](
	[ID] [varchar](10) NOT NULL,
	[NAME] [nvarchar](100) NULL,
	[CAPACITY] [int] NULL,
	[QUANTITY] [int] DEFAULT(0),
 CONSTRAINT [PK_TREATMENTPLACE] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

/****** Object:  Table [dbo].[Account_Bank]    Script Date: 11/26/2021 3:07:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account_Bank](
	[ID] [nvarchar](30) NOT NULL,
	[Password] [nvarchar](150) NOT NULL,
	[Balance] [float] NULL,
	[Role] [nvarchar](20) NULL,
	[DATEPUBLISHED] [date] NULL,
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
	[BankID] [nvarchar](30) NOT NULL,
	[CCCD] [varchar](10) NOT NULL,
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
	[Customer_ID] [nvarchar](30) NOT NULL,
	[money] [float] NOT NULL,
	[Content_] [nvarchar](50) NULL,
	[Record] [datetime] NOT NULL,
CONSTRAINT [PK_Transaction_1] PRIMARY KEY CLUSTERED 
(
	[Customer_ID] ASC,
	[Record] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


ALTER TABLE [dbo].[ACCOUNT]  WITH CHECK ADD  CONSTRAINT [FK_ACCOUNT_MANAGEDPERSON] FOREIGN KEY([USERID])
REFERENCES [dbo].[MANAGEDPERSON] ([ID])
GO
ALTER TABLE [dbo].[ACCOUNT] CHECK CONSTRAINT [FK_ACCOUNT_MANAGEDPERSON]
GO
ALTER TABLE [dbo].[ACCOUNTHISTORY]  WITH CHECK ADD  CONSTRAINT [FK_ACCOUNTHISTORY_ACCOUNT] FOREIGN KEY([USERNAME])
REFERENCES [dbo].[ACCOUNT] ([USERNAME])
GO
ALTER TABLE [dbo].[ACCOUNTHISTORY] CHECK CONSTRAINT [FK_ACCOUNTHISTORY_ACCOUNT]
GO
ALTER TABLE [dbo].[MANAGEDPERSON]  WITH CHECK ADD  CONSTRAINT [FK_MANAGEDPERSON_TREATMENTPLACE1] FOREIGN KEY([TREATMENT])
REFERENCES [dbo].[TREATMENTPLACE] ([ID])
GO
ALTER TABLE [dbo].[MANAGEDPERSON] CHECK CONSTRAINT [FK_MANAGEDPERSON_TREATMENTPLACE1]
GO
ALTER TABLE [dbo].[MANAGEHISTORY]  WITH CHECK ADD  CONSTRAINT [FK_MANAGEHISTORY_MANAGEDPERSON1] FOREIGN KEY([ID])
REFERENCES [dbo].[MANAGEDPERSON] ([ID])
GO
ALTER TABLE [dbo].[MANAGEHISTORY] CHECK CONSTRAINT [FK_MANAGEHISTORY_MANAGEDPERSON1]
GO
ALTER TABLE [dbo].[PACKAGEREGISTER]  WITH CHECK ADD  CONSTRAINT [FK_PACKAGEREGISTER_MANAGEDPERSON1] FOREIGN KEY([PERSONID])
REFERENCES [dbo].[MANAGEDPERSON] ([ID])
GO
ALTER TABLE [dbo].[PACKAGEREGISTER] CHECK CONSTRAINT [FK_PACKAGEREGISTER_MANAGEDPERSON1]
GO
ALTER TABLE [dbo].[PACKAGEREGISTER]  WITH CHECK ADD  CONSTRAINT [FK_PACKAGEREGISTER_PACKAGE1] FOREIGN KEY([PACKAGEID])
REFERENCES [dbo].[PACKAGE] ([ID])
GO
ALTER TABLE [dbo].[PACKAGEREGISTER] CHECK CONSTRAINT [FK_PACKAGEREGISTER_PACKAGE1]
GO
--ALTER TABLE [dbo].[PAYMENTHISTORY]  WITH CHECK ADD  CONSTRAINT [FK_PAYMENTHISTORY_MANAGEDPERSON1] FOREIGN KEY([ID])
--REFERENCES [dbo].[MANAGEDPERSON] ([ID])
--GO
--ALTER TABLE [dbo].[PAYMENTHISTORY] CHECK CONSTRAINT [FK_PAYMENTHISTORY_MANAGEDPERSON1]
--GO
ALTER TABLE [dbo].[RELATED]  WITH CHECK ADD  CONSTRAINT [FK_RELATED_MANAGEDPERSON2] FOREIGN KEY([PERSON_ID1])
REFERENCES [dbo].[MANAGEDPERSON] ([ID])
GO
ALTER TABLE [dbo].[RELATED] CHECK CONSTRAINT [FK_RELATED_MANAGEDPERSON2]
GO
ALTER TABLE [dbo].[RELATED]  WITH CHECK ADD  CONSTRAINT [FK_RELATED_MANAGEDPERSON3] FOREIGN KEY([PERSON_ID2])
REFERENCES [dbo].[MANAGEDPERSON] ([ID])
GO
ALTER TABLE [dbo].[RELATED] CHECK CONSTRAINT [FK_RELATED_MANAGEDPERSON3]
GO
ALTER TABLE [dbo].[ACCOUNT]  WITH CHECK ADD  CONSTRAINT [CK_ACCOUNT_ACTIVATED] CHECK  (([ACTIVATED]=(1) OR [ACTIVATED]=(0)))
GO
ALTER TABLE [dbo].[ACCOUNT] CHECK CONSTRAINT [CK_ACCOUNT_ACTIVATED]
GO
ALTER TABLE [dbo].[ACCOUNT]  WITH CHECK ADD  CONSTRAINT [CK_ACCOUNT_ROLE] CHECK  (([ROLE]='ADMIN' OR [ROLE]='MANAGER' OR [ROLE]='USER'))
GO
ALTER TABLE [dbo].[ACCOUNT] CHECK CONSTRAINT [CK_ACCOUNT_ROLE]
GO
ALTER TABLE [dbo].[MANAGEDPERSON]  WITH CHECK ADD  CONSTRAINT [CK_MANAGEDPERSON_STATUS] CHECK  (([STATUS]=N'KHỎI BỆNH' OR [STATUS]=N'F0' OR [STATUS]=N'F1' OR [STATUS]=N'F2' OR [STATUS]=N'F3' OR [STATUS]=N'F4' OR [STATUS]=N'F5'))
GO
ALTER TABLE [dbo].[MANAGEDPERSON] CHECK CONSTRAINT [CK_MANAGEDPERSON_STATUS]
GO
ALTER TABLE [dbo].[MANAGEHISTORY]  WITH CHECK ADD  CONSTRAINT [CK_MANAGEHISTORY_FROMSTATUS] CHECK  (([FROMSTATUS]=N'KHỎI BỆNH' OR [FROMSTATUS]=N'F0' OR [FROMSTATUS]=N'F1' OR [FROMSTATUS]=N'F2' OR [FROMSTATUS]=N'F3' OR [FROMSTATUS]=N'F4' OR [FROMSTATUS]=N'F5'))
GO
ALTER TABLE [dbo].[MANAGEHISTORY] CHECK CONSTRAINT [CK_MANAGEHISTORY_FROMSTATUS]
GO
ALTER TABLE [dbo].[MANAGEHISTORY]  WITH CHECK ADD  CONSTRAINT [CK_MANAGEHISTORY_TOSTATUS] CHECK  (([TOSTATUS]=N'KHỎI BỆNH' OR [TOSTATUS]=N'F0' OR [TOSTATUS]=N'F1' OR [TOSTATUS]=N'F2' OR [TOSTATUS]=N'F3' OR [TOSTATUS]=N'F4' OR [TOSTATUS]=N'F5'))
GO
ALTER TABLE [dbo].[MANAGEHISTORY] CHECK CONSTRAINT [CK_MANAGEHISTORY_TOSTATUS]
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


USE [master]
GO
ALTER DATABASE [Covid-19] SET  READ_WRITE 
GO

