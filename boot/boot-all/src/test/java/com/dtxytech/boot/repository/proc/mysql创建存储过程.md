mysql创建存储过程
##无参数存储过程
```delimiter //
create procedure myFist_proc() ## 创建存储过程
begin 
    select stu_id from score where grade>80 and c_name='计算机';
    select name from student where id in 
    (select stu_id from(select * from score where grade>80 and c_name='计算机')a 
    where a.stu_id in(select stu_id from score where grade>80 and c_name='英语'));
    end;//
delimiter;
show create procedure myFist_proc();
call myFist_proc();
```

##带有输出参数的存储过程
```
delimiter //
create procedure mySecond_proc(out sumStudent int ) ## 创建存储过程
begin 
    select count(*) into sumStudent from score where grade>80;
end;//
delimiter;
call mySecond_proc(@sumStudent);
select @sumStudent;
```


##带有输入参数的存储过程
```
delimiter //
create procedure myThird_proc(in minScore int ) ## 创建存储过程
begin 
    select count(*) from score where grade>minScore;
end;//
delimiter;
call myThird_proc(90); 大于90的
select @sumStudent;
```

##带有输入输出参数的存储过程
```
delimiter //
create procedure myFourth_proc(in minScore int,out sumStudent int ) ## 创建存储过程
begin 
    select count(*) into sumStudent from score where grade>minScore;
end;//
delimiter;
call myFourth_proc(90,@sumStudent);
select @sumStudent;
```