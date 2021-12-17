USE [Covid-19]
GO
INSERT [dbo].[TREATMENTPLACE] ([ID], [NAME], [CAPACITY], [QUANTITY]) VALUES (N'1', N'Bệnh viện 115', 200, 1)
INSERT [dbo].[TREATMENTPLACE] ([ID], [NAME], [CAPACITY], [QUANTITY]) VALUES (N'2', N'Bệnh viện dã chiến TPHCM', 250, 4)
INSERT [dbo].[TREATMENTPLACE] ([ID], [NAME], [CAPACITY], [QUANTITY]) VALUES (N'3', N'Trung tâm GDQP', 250, 1)
INSERT [dbo].[TREATMENTPLACE] ([ID], [NAME], [CAPACITY], [QUANTITY]) VALUES (N'4', N'Tòa nhà H', 200, 1)
INSERT [dbo].[TREATMENTPLACE] ([ID], [NAME], [CAPACITY], [QUANTITY]) VALUES (N'5', N'Bệnh viện trung tâm Q1', 150, 1)
GO
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'1', N'Nguyễn Văn A', 2001, N'Bến Nghé, Q1, TPHCM', N'F0', N'2', 800)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'2', N'Nguyễn Văn B', 2001, N'P26 , Q. Bình Thạnh, TPHCM', N'F2', N'5', 1500)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'3', N'Lê Văn C', 2000, N'Đa Kao. Q1, TPHCM', N'F2', N'1', 600)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'4', N'Lê Cảnh', 1995, N'P2, Q3, TPHCM', N'F2', N'2', 500)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'5', N'Huỳnh Liên', 1983, N'P5, Q3, TPHCM', N'F2', N'3', 50)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'6', N'Nguyễn Chính', 1975, N'Bạch Đằng, Tân Uyên, Bình Dương', N'F3', N'4', 150)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'7', N'Nguyễn Quốc', 1978, N'Hội Nghĩa, Tân Uyên, Bình Dương', N'F1', N'2', 250)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'8', N'Lưu Thanh', 2003, N'An Bình, Dĩ An, Bình Dương', N'F2', N'2', 125)
GO
INSERT [dbo].[ACCOUNT] ([USERNAME], [PASSWORD], [ROLE], [USERID], [ACTIVATED], [DATEPUBLISHED]) VALUES (N'admin', N'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', N'ADMIN', NULL, 1, CAST('2021-01-01 00:01:00' AS DateTime))
INSERT [dbo].[ACCOUNT] ([USERNAME], [PASSWORD], [ROLE], [USERID], [ACTIVATED], [DATEPUBLISHED]) VALUES (N'htkha19', N'430f710c03d9755b1b3e7b1a55eba710d17c5c0b4983d01e81f1bbb5649d744f', N'Manager', NULL, 1, CAST('2021-01-05 12:30:59.999' AS DateTime))
INSERT [dbo].[ACCOUNT] ([USERNAME], [PASSWORD], [ROLE], [USERID], [ACTIVATED], [DATEPUBLISHED]) VALUES (N'lqtrong19', N'cc5172eeacfe86618b6c13084879f2518aa7c8a922e0958daa030777f4f566ea', N'Manager', NULL, 1, CAST('2021-01-05 12:32:00' AS DateTime))
INSERT [dbo].[ACCOUNT] ([USERNAME], [PASSWORD], [ROLE], [USERID], [ACTIVATED], [DATEPUBLISHED]) VALUES (N'lvcanh1', N'fcf0f91bb6ee24134295a9319d21eede716b100b4eddade3bd83ef684c15c23f', N'User', N'4', 1, CAST('2021-02-28 23:59:59' AS DateTime))
INSERT [dbo].[ACCOUNT] ([USERNAME], [PASSWORD], [ROLE], [USERID], [ACTIVATED], [DATEPUBLISHED]) VALUES (N'lvmnhat19', N'8e9b26de4045787fdbf4f88cef1bfcd52e65d5d730b1089994e898859160c1e5', N'Manager', NULL, 1, CAST('2021-01-06 08:00:00' AS DateTime))
INSERT [dbo].[ACCOUNT] ([USERNAME], [PASSWORD], [ROLE], [USERID], [ACTIVATED], [DATEPUBLISHED]) VALUES (N'nqthong19', N'8eefccdd740a7f9fee73ae0cea3ad4a2edd3ea03d6198af43041fe311516d029', N'Manager', NULL, 1, CAST('2021-01-07 17:09:24' AS DateTime))
INSERT [dbo].[ACCOUNT] ([USERNAME], [PASSWORD], [ROLE], [USERID], [ACTIVATED], [DATEPUBLISHED]) VALUES (N'nvana1', N'c83084eb91c3e74d5e644f0ed04929ab1dae306b3f6b2024d6cf1a788f27de4e', N'User', N'1', 1, CAST('2021-01-31 11:07:12.003' AS DateTime))
GO
INSERT [dbo].[ACCOUNTHISTORY] ([USERNAME], [RECORD_LOGIN], [RECORD_LOGOUT]) VALUES (N'admin', CAST(N'2021-10-20T05:00:00.000' AS DateTime), CAST(N'2021-10-20T06:00:00.000' AS DateTime))
INSERT [dbo].[ACCOUNTHISTORY] ([USERNAME], [RECORD_LOGIN], [RECORD_LOGOUT]) VALUES (N'admin', CAST(N'2021-10-22T05:00:00.000' AS DateTime), CAST(N'2021-10-22T06:00:00.000' AS DateTime))
INSERT [dbo].[ACCOUNTHISTORY] ([USERNAME], [RECORD_LOGIN], [RECORD_LOGOUT]) VALUES (N'admin', CAST(N'2021-11-15T05:00:00.000' AS DateTime), CAST(N'2021-11-16T06:00:00.000' AS DateTime))
INSERT [dbo].[ACCOUNTHISTORY] ([USERNAME], [RECORD_LOGIN], [RECORD_LOGOUT]) VALUES (N'htkha19', CAST(N'2021-10-20T06:00:00.000' AS DateTime), CAST(N'2021-10-20T07:00:00.000' AS DateTime))
INSERT [dbo].[ACCOUNTHISTORY] ([USERNAME], [RECORD_LOGIN], [RECORD_LOGOUT]) VALUES (N'htkha19', CAST(N'2021-10-23T06:00:00.000' AS DateTime), CAST(N'2021-10-23T07:00:00.000' AS DateTime))
INSERT [dbo].[ACCOUNTHISTORY] ([USERNAME], [RECORD_LOGIN], [RECORD_LOGOUT]) VALUES (N'htkha19', CAST(N'2021-11-23T06:00:00.000' AS DateTime), CAST(N'2021-11-23T07:00:00.000' AS DateTime))
INSERT [dbo].[ACCOUNTHISTORY] ([USERNAME], [RECORD_LOGIN], [RECORD_LOGOUT]) VALUES (N'lqtrong19', CAST(N'2021-10-20T08:00:00.000' AS DateTime), CAST(N'2021-10-20T09:00:00.000' AS DateTime))
INSERT [dbo].[ACCOUNTHISTORY] ([USERNAME], [RECORD_LOGIN], [RECORD_LOGOUT]) VALUES (N'lqtrong19', CAST(N'2021-10-21T08:00:00.000' AS DateTime), CAST(N'2021-10-21T09:00:00.000' AS DateTime))
INSERT [dbo].[ACCOUNTHISTORY] ([USERNAME], [RECORD_LOGIN], [RECORD_LOGOUT]) VALUES (N'lqtrong19', CAST(N'2021-11-21T08:00:00.000' AS DateTime), CAST(N'2021-11-21T09:00:00.000' AS DateTime))
INSERT [dbo].[ACCOUNTHISTORY] ([USERNAME], [RECORD_LOGIN], [RECORD_LOGOUT]) VALUES (N'lvcanh1', CAST(N'2021-10-20T10:00:00.000' AS DateTime), CAST(N'2021-10-20T11:00:00.000' AS DateTime))
INSERT [dbo].[ACCOUNTHISTORY] ([USERNAME], [RECORD_LOGIN], [RECORD_LOGOUT]) VALUES (N'lvcanh1', CAST(N'2021-10-28T10:00:00.000' AS DateTime), CAST(N'2021-10-28T11:00:00.000' AS DateTime))
INSERT [dbo].[ACCOUNTHISTORY] ([USERNAME], [RECORD_LOGIN], [RECORD_LOGOUT]) VALUES (N'lvcanh1', CAST(N'2021-11-25T10:00:00.000' AS DateTime), CAST(N'2021-11-25T11:00:00.000' AS DateTime))
GO
INSERT [dbo].[MANAGEHISTORY] ([ID], [FROMSTATUS], [TOSTATUS], [RECORD]) VALUES (N'1', N'F1', N'F0', CAST(N'2021-11-27T15:00:00.000' AS DateTime))
INSERT [dbo].[MANAGEHISTORY] ([ID], [FROMSTATUS], [TOSTATUS], [RECORD]) VALUES (N'1', N'F2', N'F1', CAST(N'2021-11-22T15:00:00.000' AS DateTime))
INSERT [dbo].[MANAGEHISTORY] ([ID], [FROMSTATUS], [TOSTATUS], [RECORD]) VALUES (N'1', N'F3', N'F2', CAST(N'2021-11-15T15:00:00.000' AS DateTime))
INSERT [dbo].[MANAGEHISTORY] ([ID], [FROMSTATUS], [TOSTATUS], [RECORD]) VALUES (N'3', N'F3', N'F2', CAST(N'2021-11-27T15:00:00.000' AS DateTime))
INSERT [dbo].[MANAGEHISTORY] ([ID], [FROMSTATUS], [TOSTATUS], [RECORD]) VALUES (N'3', N'F4', N'F3', CAST(N'2021-11-22T15:00:00.000' AS DateTime))
INSERT [dbo].[MANAGEHISTORY] ([ID], [FROMSTATUS], [TOSTATUS], [RECORD]) VALUES (N'4', N'F2', N'F1', CAST(N'2021-11-25T15:00:00.000' AS DateTime))
INSERT [dbo].[MANAGEHISTORY] ([ID], [FROMSTATUS], [TOSTATUS], [RECORD]) VALUES (N'4', N'F3', N'F2', CAST(N'2021-11-19T15:00:00.000' AS DateTime))
INSERT [dbo].[MANAGEHISTORY] ([ID], [FROMSTATUS], [TOSTATUS], [RECORD]) VALUES (N'5', N'F4', N'F2', CAST(N'2021-11-25T15:00:00.000' AS DateTime))
INSERT [dbo].[MANAGEHISTORY] ([ID], [FROMSTATUS], [TOSTATUS], [RECORD]) VALUES (N'6', N'F4', N'F3', CAST(N'2021-11-25T15:00:00.000' AS DateTime))
INSERT [dbo].[MANAGEHISTORY] ([ID], [FROMSTATUS], [TOSTATUS], [RECORD]) VALUES (N'7', N'F2', N'F1', CAST(N'2021-11-27T15:00:00.000' AS DateTime))
INSERT [dbo].[MANAGEHISTORY] ([ID], [FROMSTATUS], [TOSTATUS], [RECORD]) VALUES (N'7', N'F4', N'F2', CAST(N'2021-11-22T15:00:00.000' AS DateTime))
GO
INSERT [dbo].[PACKAGE] ([ID], [NAME], [LIMITNUM], [LIMITTIME], [PRICE], [QUANTITY]) VALUES (N'1', N'A', 50, CAST(N'2021-11-27' AS Date), 50, 57)
INSERT [dbo].[PACKAGE] ([ID], [NAME], [LIMITNUM], [LIMITTIME], [PRICE], [QUANTITY]) VALUES (N'2', N'B', 50, CAST(N'2021-11-27' AS Date), 100, 20)
INSERT [dbo].[PACKAGE] ([ID], [NAME], [LIMITNUM], [LIMITTIME], [PRICE], [QUANTITY]) VALUES (N'3', N'C', 10, CAST(N'2021-11-27' AS Date), 20, 200)
INSERT [dbo].[PACKAGE] ([ID], [NAME], [LIMITNUM], [LIMITTIME], [PRICE], [QUANTITY]) VALUES (N'4', N'D', 20, CAST(N'2021-11-27' AS Date), 200, 500)
INSERT [dbo].[PACKAGE] ([ID], [NAME], [LIMITNUM], [LIMITTIME], [PRICE], [QUANTITY]) VALUES (N'5', N'E', 15, CAST(N'2021-11-27' AS Date), 25, 995)
GO
INSERT [dbo].[PACKAGEREGISTER] ([PERSONID], [PACKAGEID], [RECORD], [QUANTITY]) VALUES (N'1', N'1', CAST(N'2021-11-27T00:00:00.000' AS DateTime), 20)
INSERT [dbo].[PACKAGEREGISTER] ([PERSONID], [PACKAGEID], [RECORD], [QUANTITY]) VALUES (N'1', N'1', CAST(N'2021-11-27T11:00:00.000' AS DateTime), 10)
INSERT [dbo].[PACKAGEREGISTER] ([PERSONID], [PACKAGEID], [RECORD], [QUANTITY]) VALUES (N'1', N'2', CAST(N'2021-11-27T11:00:00.000' AS DateTime), 10)
INSERT [dbo].[PACKAGEREGISTER] ([PERSONID], [PACKAGEID], [RECORD], [QUANTITY]) VALUES (N'2', N'1', CAST(N'2021-11-27T11:00:00.000' AS DateTime), 10)
INSERT [dbo].[PACKAGEREGISTER] ([PERSONID], [PACKAGEID], [RECORD], [QUANTITY]) VALUES (N'2', N'2', CAST(N'2021-11-27T11:00:00.000' AS DateTime), 10)
INSERT [dbo].[PACKAGEREGISTER] ([PERSONID], [PACKAGEID], [RECORD], [QUANTITY]) VALUES (N'3', N'1', CAST(N'2021-11-27T11:00:00.000' AS DateTime), 20)
INSERT [dbo].[PACKAGEREGISTER] ([PERSONID], [PACKAGEID], [RECORD], [QUANTITY]) VALUES (N'3', N'2', CAST(N'2021-11-27T11:00:00.000' AS DateTime), 10)
INSERT [dbo].[PACKAGEREGISTER] ([PERSONID], [PACKAGEID], [RECORD], [QUANTITY]) VALUES (N'4', N'1', CAST(N'2021-11-27T11:00:00.000' AS DateTime), 20)
INSERT [dbo].[PACKAGEREGISTER] ([PERSONID], [PACKAGEID], [RECORD], [QUANTITY]) VALUES (N'5', N'1', CAST(N'2021-11-27T11:00:00.000' AS DateTime), 5)
INSERT [dbo].[PACKAGEREGISTER] ([PERSONID], [PACKAGEID], [RECORD], [QUANTITY]) VALUES (N'6', N'1', CAST(N'2021-11-27T11:00:00.000' AS DateTime), 3)
INSERT [dbo].[PACKAGEREGISTER] ([PERSONID], [PACKAGEID], [RECORD], [QUANTITY]) VALUES (N'7', N'1', CAST(N'2021-11-27T11:00:00.000' AS DateTime), 5)
INSERT [dbo].[PACKAGEREGISTER] ([PERSONID], [PACKAGEID], [RECORD], [QUANTITY]) VALUES (N'8', N'5', CAST(N'2021-11-27T11:00:00.000' AS DateTime), 5)
GO
INSERT [dbo].[Account_Bank] ([ID], [Password], [Role], [Active], [Balance], [UserID], [DATEPUBLISHED]) VALUES (N'admin', N'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', N'Admin', 1, 50003800, NULL, CAST('2021-01-01 00:06:00.999' AS DateTime))
INSERT [dbo].[Account_Bank] ([ID], [Password], [Role], [Active], [Balance], [UserID], [DATEPUBLISHED]) VALUES (N'huynhlien', N'd3b4aaa4e10ed7705286f4461a022c17dcc35ee0a48cd7c1bcaf85bfd68859ec', N'User', 1, 49800, NULL, CAST('2021-05-30 00:00:00' AS DateTime))
INSERT [dbo].[Account_Bank] ([ID], [Password], [Role], [Active], [Balance], [UserID], [DATEPUBLISHED]) VALUES (N'lecanh', N'3e54a00316e8c19f90a4ca6f4e1311e112b01c076e2342c9a18e79531b98b117', N'User', 1, 49500, NULL, CAST('2021-01-05 01:01:01' AS DateTime))
INSERT [dbo].[Account_Bank] ([ID], [Password], [Role], [Active], [Balance], [UserID], [DATEPUBLISHED]) VALUES (N'levanc', N'7fbd96adcb8b30607bb4d6ac0854abdebea97aec034b2cdc6d15600acfcf90ad', N'User', 1, 23600, NULL, CAST('2021-02-28 02:30: 59.999' AS DateTime))
INSERT [dbo].[Account_Bank] ([ID], [Password], [Role], [Active], [Balance], [UserID], [DATEPUBLISHED]) VALUES (N'nguyenchinh', N'566b90954e2968ccaeea170da9ad0b036e0e3e9bf442d00661acc194821dce90', N'User', 1, 55000, NULL, CAST('2021-01-11 02:31:00.01' AS DateTime))
INSERT [dbo].[Account_Bank] ([ID], [Password], [Role], [Active], [Balance], [UserID], [DATEPUBLISHED]) VALUES (N'nguyenquoc', N'f0698c62adda4bce806654d76adcb4ce48f84536b207aef00a11bd936ddace3a', N'User', 1, 55000, NULL, CAST('2021-06-25 23:10:10' AS DateTime))
INSERT [dbo].[Account_Bank] ([ID], [Password], [Role], [Active], [Balance], [UserID], [DATEPUBLISHED]) VALUES (N'nguyenvana', N'a4c5535dc7803174143cb8a229e9be99b27298ab334e0ec53f0def94b04034dc', N'User', 1, 8300,NULL, CAST('2021-10-11 10:10:10' AS DateTime))
INSERT [dbo].[Account_Bank] ([ID], [Password], [Role], [Active], [Balance], [UserID], [DATEPUBLISHED]) VALUES (N'nguyenvanb', N'48a27c551863747c09dfdfbc197fe01d7f6199545611c81df50a46aafbd191dd', N'User', 1, 2000, NULL, CAST('2021-01-21 08:08:08.888' AS DateTime))
GO
INSERT [dbo].[Customer] ([BankID], [CCCD]) VALUES (N'huynhlien', N'5')
INSERT [dbo].[Customer] ([BankID], [CCCD]) VALUES (N'lecanh', N'4')
INSERT [dbo].[Customer] ([BankID], [CCCD]) VALUES (N'levanc', N'3')
INSERT [dbo].[Customer] ([BankID], [CCCD]) VALUES (N'nguyenchinh', N'6')
INSERT [dbo].[Customer] ([BankID], [CCCD]) VALUES (N'nguyenquoc', N'7')
INSERT [dbo].[Customer] ([BankID], [CCCD]) VALUES (N'nguyenvana', N'1')
INSERT [dbo].[Customer] ([BankID], [CCCD]) VALUES (N'nguyenvanb', N'2')
GO
INSERT [dbo].[Transaction] ([Customer_ID], [money], [Content_], [Record]) VALUES (N'huynhlien', 50, N'Thanh toan', CAST(N'2021-11-26T00:00:00.000' AS DateTime))
INSERT [dbo].[Transaction] ([Customer_ID], [money], [Content_], [Record]) VALUES (N'huynhlien', 150, N'Thanh toan', CAST(N'2021-11-27T00:00:00.000' AS DateTime))
INSERT [dbo].[Transaction] ([Customer_ID], [money], [Content_], [Record]) VALUES (N'lecanh', 150, N'Thanh toan', CAST(N'2021-11-27T00:00:00.000' AS DateTime))
INSERT [dbo].[Transaction] ([Customer_ID], [money], [Content_], [Record]) VALUES (N'lecanh', 250, N'Thanh toan', CAST(N'2021-11-28T00:00:00.000' AS DateTime))
INSERT [dbo].[Transaction] ([Customer_ID], [money], [Content_], [Record]) VALUES (N'lecanh', 100, N'Thanh toan', CAST(N'2021-11-28T00:25:00.000' AS DateTime))
INSERT [dbo].[Transaction] ([Customer_ID], [money], [Content_], [Record]) VALUES (N'levanc', 250, N'Thanh toan', CAST(N'2021-11-24T00:25:00.000' AS DateTime))
INSERT [dbo].[Transaction] ([Customer_ID], [money], [Content_], [Record]) VALUES (N'levanc', 150, N'Thanh toan', CAST(N'2021-11-26T00:25:00.000' AS DateTime))
INSERT [dbo].[Transaction] ([Customer_ID], [money], [Content_], [Record]) VALUES (N'levanc', 1000, N'Thanh toan', CAST(N'2021-11-28T00:25:00.000' AS DateTime))
INSERT [dbo].[Transaction] ([Customer_ID], [money], [Content_], [Record]) VALUES (N'nguyenvana', 250, N'Thanh toan', CAST(N'2021-11-24T00:25:00.000' AS DateTime))
INSERT [dbo].[Transaction] ([Customer_ID], [money], [Content_], [Record]) VALUES (N'nguyenvana', 150, N'Thanh toan', CAST(N'2021-11-25T00:25:00.000' AS DateTime))
INSERT [dbo].[Transaction] ([Customer_ID], [money], [Content_], [Record]) VALUES (N'nguyenvana', 550, N'Thanh toan', CAST(N'2021-11-27T00:25:00.000' AS DateTime))
INSERT [dbo].[Transaction] ([Customer_ID], [money], [Content_], [Record]) VALUES (N'nguyenvana', 750, N'Thanh toan', CAST(N'2021-11-28T00:25:00.000' AS DateTime))
GO
