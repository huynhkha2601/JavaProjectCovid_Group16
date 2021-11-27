USE [Covid-19]
GO
INSERT [dbo].[TREATMENTPLACE] ([ID], [NAME], [CAPACITY], [QUANTITY]) VALUES (N'1', N'Bệnh viện 115', 200, 1)
INSERT [dbo].[TREATMENTPLACE] ([ID], [NAME], [CAPACITY], [QUANTITY]) VALUES (N'2', N'Bệnh viện dã chiến TPHCM', 250, 4)
INSERT [dbo].[TREATMENTPLACE] ([ID], [NAME], [CAPACITY], [QUANTITY]) VALUES (N'3', N'Trung tâm GDQP', 250, 1)
INSERT [dbo].[TREATMENTPLACE] ([ID], [NAME], [CAPACITY], [QUANTITY]) VALUES (N'4', N'Tòa nhà H', 200, 1)
INSERT [dbo].[TREATMENTPLACE] ([ID], [NAME], [CAPACITY], [QUANTITY]) VALUES (N'5', N'Bệnh viện trung tâm Q1', 150, 1)
GO
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'1', N'Nguyễn Văn A', 2001, N'Bến Nghé, Q1, TPHCM', N'F0', N'2', 2500)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'2', N'Nguyễn Văn B', 2005, N'Phạm Ngũ Lão, Q1, TPHCM', N'F1', N'5', 1500)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'3', N'Lê Văn C', 2000, N'Đa Kao. Q1, TPHCM', N'F2', N'1', 2000)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'4', N'Lê Cảnh', 1995, N'P2, Q3, TPHCM', N'F1', N'2', 1000)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'5', N'Huỳnh Liên', 1983, N'P5, Q3, TPHCM', N'F2', N'3', 250)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'6', N'Nguyễn Chính', 1975, N'Bạch Đằng, Tân Uyên, Bình Dương', N'F3', N'4', 150)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'7', N'Nguyễn Quốc', 1978, N'Hội Nghĩa, Tân Uyên, Bình Dương', N'F1', N'2', 250)
INSERT [dbo].[MANAGEDPERSON] ([ID], [FULLNAME], [YEAROFBIRTH], [ADDRESS], [STATUS], [TREATMENT], [DEBT]) VALUES (N'8', N'Lưu Thanh', 2003, N'An Bình, Dĩ An, Bình Dương', N'F0', N'2', 125)
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
