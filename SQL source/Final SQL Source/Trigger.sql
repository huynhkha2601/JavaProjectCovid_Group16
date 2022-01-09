use [Covid-19]
go


/*-------------------------------------------------------------------------------------------------*/
/*	Trigger when insert 1 managedperson into a treatmentplace
	if add a person to treatment -> quantity increases by 1
*/
create or alter trigger Treatment_Insert_Quantiy on ManagedPerson
for insert as
begin
	update tp
	set tp.QUANTITY = tp.QUANTITY +	tb.newquantity
	from treatmentplace tp
	join (select i.TREATMENT, count(*) as newquantity
	from inserted i
	group by i.TREATMENT) as tb on tp.ID = tb.TREATMENT
end
go

/*-------------------------------------------------------------------------------------------------*/
/*	Trigger when update 1 managedperson into old treatmentplace to new treatmentplace
	if add a person to treatment -> quantity of new treatmentplace increases by 1
	and the quantity of old treatmentplace decreases by 1
*/

create or alter trigger Treatment_Update_Quantiy on ManagedPerson
after update as
begin
	update TREATMENTPLACE
	set QUANTITY = QUANTITY + 1 where ID = (select TREATMENT from inserted)
	update TREATMENTPLACE
	set QUANTITY = QUANTITY - 1 where ID = (select TREATMENT from deleted)
end
go

/*-------------------------------------------------------------------------------------------------*/
/*	Trigger when delete 1 managedperson
	if delete a person to treatment -> the quantity of treatmentplace decreases by 1
*/

create or alter trigger Treatment_Delete_Quantiy on ManagedPerson
for delete as
begin
	update tp
	set tp.QUANTITY = tp.QUANTITY -	tb.newquantity
	from treatmentplace tp
	join (select d.TREATMENT, count(*) as newquantity
	from deleted d
	group by d.TREATMENT) as tb on tp.ID = tb.TREATMENT
end
go

/*-------------------------------------------------------------------------------------------------*/
/*	Trigger when insert 1 PackageRegister
	if insert a column to PackageRegister -> the quantity of package decreases by 1 quantity of register
		and debt in MANAGEDPERSON increases price * quantity of register
*/
create or alter trigger PackageRegister_Insert on PackageRegister
for insert as
begin
	update MANAGEDPERSON
	set DEBT += (select (i.QUANTITY*p.PRICE) from inserted i join PACKAGE p on p.ID = i.PACKAGEID)
	where ID = (select PERSONID from inserted)

	update PACKAGE
	set QUANTITY -= (select QUANTITY from inserted)
	where ID = (select PACKAGEID from inserted)
end
go
/*-------------------------------------------------------------------------------------------------*/
/*	Trigger when update 1 PackageRegister */
create or alter trigger PackageRegister_Update on PackageRegister
for update as
begin

	update mp
	set mp.DEBT = (select sum(sl*PRICE) from Package p join
	(select PACKAGEID ,sum(QUANTITY) as sl from PACKAGEREGISTER pr
	group by PACKAGEID) as t
	on p.ID = t.PACKAGEID)
	from MANAGEDPERSON mp join inserted i on i.PERSONID = mp.ID

	update PACKAGE
	set QUANTITY += (select QUANTITY from deleted) - (select QUANTITY from inserted)
	where ID = (select PACKAGEID from inserted)
end
go

/*-------------------------------------------------------------------------------------------------*/
/*	Trigger when delete 1 PackageRegister
	if delete a column to PackageRegister -> the quantity of package increases by 1 quantity of register
		and debt in MANAGEDPERSON decreases by price * quantity of register
*/
create or alter trigger PackageRegister_Delete on PackageRegister
for delete as
begin
	update MANAGEDPERSON
	set DEBT -= (select (d.QUANTITY*p.PRICE) from deleted d join PACKAGE p on p.ID = d.PACKAGEID)
	where ID = (select PERSONID from deleted)

	update PACKAGE
	set QUANTITY += (select QUANTITY from deleted)
	where ID = (select PACKAGEID from deleted)
end
go

/*-------------------------------------------------------------------------------------------------*/
/*	Trigger when insert,update 1 column of ManageHistory
	if insert,update a column to ManageHistory -> change status of ManagedPerson
*/

create or alter trigger ManageHistory_Insert_Update on ManageHistory
for insert, update as
begin
	update MANAGEDPERSON
	set STATUS = (select TOSTATUS from inserted)
	where ID = (select ID from inserted)
end
go
/*-------------------------------------------------------------------------------------------------*/
/*	Trigger when delete 1 column of ManageHistory
	if delete a column to ManageHistory -> change status of ManagedPerson
*/

create or alter trigger ManageHistory_Delete on ManageHistory
for delete as
begin
	update MANAGEDPERSON
	set [STATUS] = (select FROMSTATUS from deleted)
	where ID = (select ID from deleted)
end
go

/*-------------------------------------------------------------------------------------------------*/
/*Trigger for "He thong thanh toan"*/
/*Trigger when insert 1 traction:*/
create or alter trigger Insert_transaction on [Transaction]
after insert as
begin
	declare @money int;
	select @money = [money] from inserted

	update MANAGEDPERSON
	set DEBT -= @money where ID = 
	(select mp.ID from inserted i join Customer c on c.BankID = i.Customer_ID
	join MANAGEDPERSON mp on mp.ID = c.CCCD)

	update [Account_Bank]
	set [Balance] = [Balance] + @money
	where [Role] = 'Admin'

	update [Account_Bank]
	set [Balance] = [Balance] - @money
	where [ID] = (select i.[Customer_ID]
				  from [inserted] as i inner join [Customer] as c
				  on i.[Customer_ID] = c.[BankID])
end
go


/*Trigger when delete 1 traction:*/
create or alter trigger Delete_transaction on [Transaction] for delete as
begin
	declare @money int;
	select @money = [money]
	from deleted

	update MANAGEDPERSON
	set DEBT += @money where ID = 
	(select mp.ID from deleted d join Customer c on c.BankID = d.Customer_ID
	join MANAGEDPERSON mp on mp.ID = c.CCCD)

	update [Account_Bank]
	set [Balance] = [Balance] - @money
	where [Role] = 'Admin'

	update [Account_Bank]
	set [Balance] = [Balance] + @money
	where [ID] = (select d.[Customer_ID]
				  from [deleted] as d inner join [Customer] as c
				  on d.[Customer_ID] = c.[BankID])
end
go


/*Trigger when update 1 traction:*/
create or alter trigger Update_transaction on [Transaction] after update as
begin
	declare @new_money int;
	select @new_money = [money]
	from inserted

	
	declare @old_money int;
	select @old_money = [money]
	from deleted

	update MANAGEDPERSON
	set DEBT += @old_money - @new_money where ID = 
	(select mp.ID from inserted i join Customer c on c.BankID = i.Customer_ID
	join MANAGEDPERSON mp on mp.ID = c.CCCD)



	update [Account_Bank]
	set [Balance] = [Balance] + (@new_money - @old_money)
	where [Role] = 'Admin'

	update [Account_Bank]
	set [Balance] = [Balance] - (@new_money - @old_money)
	where [ID] = (select i.[Customer_ID]
				  from inserted as i inner join [Customer] as c
				  on i.[Customer_ID] = c.[BankID])
end
go