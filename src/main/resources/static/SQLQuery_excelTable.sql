/****** Script for SelectTopNRows command from SSMS  ******/

use db_cnc_copy;

/* DELETE ROWS */

delete 
from [stats$]
where المركز like 'المجموع';

delete 
from [stats$]
where المركز is null;

delete from room;
delete from center_concour;
delete from concour;

update [db_cnc_copy].[dbo].[user] set centerid = null;
delete from center;

select COUNT(*) from room;
select COUNT(*) from center_concour;
select COUNT(*) from center;
select COUNT(*) from concour;

select * from room;
select * from center_concour;
select * from center;
select * from concour;



SELECT *
FROM [stats$]



/* CENTER */
SELECT DISTINCT المركز, اللجنة, المؤسسة
FROM [db_cnc_copy].[dbo].['احصائيات الحضور والغياب$']

insert into center(city, jury, address, candidates)
SELECT المركز, اللجنة, المؤسسة, SUM([عدد المرشحين]) as المرشحين
FROM [stats$]
group by المركز, اللجنة, المؤسسة;



/* CONCOUR */
insert into concour(rank, title)
SELECT distinct [الدرجة المتبارى بشانها], التخصص
FROM [stats$]



/* ROOM */
insert into room([to], [from], candidates, name, jury, center)
SELECT إلى, من, [عدد المرشحين], [المدرج / القاعة], اللجنة, المركز
FROM [stats$]
ORDER BY من;



SELECT المركز, المؤسسة
FROM [db_cnc_copy].[dbo].['احصائيات الحضور والغياب$']
where المركز like 'أكادير'
order by التخصص;


