## jsonToExcel

This is a java maven project~, and this project contains a simple tool that can convert a json file that export from mysql or app log to excel~

#### sample of sql file:
sql file is a file that export from mysql.

for example:
```
insert into test.user
 values(1801301,  'lili'  ,18,  'm'  ,10089,  'master'),
       (1801302,  'jimi'  ,18,  'f'  ,10090,  'master'),
       (1801303,  'xiaoming'  ,20,  'f'  ,10091,  'master'),
       (1801304,  'zhansan'  ,22,  'm'  ,10092,  'master'),
       (1801305,  'lisi'  ,22,  'm'  ,10093,  'doctor'),
       (1801305,  'wangwu'  ,22,  'm'   ,10093,  'doctor')
``` 
 
You must delete all useless message and only contains values that split with ','
for example:

```
(1801301,  'lili'  ,18,  'm'  ,10089,  'master'),
(1801302,  'jimi'  ,18,  'f'  ,10090,  'master'),
(1801303,  'xiag'  ,20,  'f'  ,10091,  'master'),
(1801304,  'sdgs'  ,22,  'm'  ,10092,  'master'),
(1801305,  'lisi'  ,22,  'm'  ,10093,  'doctor'),
(1801305,  'segs'  ,22,  'm'   ,10093,  'doctor')
```

Now you can use this tool to convert a sql file to json file.

##### sample of json file:

json file must contains json objects only, you can think it is a json array with out '[', or ']' and each json object splits with line break '\n' without ',' 

for example:
```
{"id": 1801301,  "name", "lili"  ,"age":18,  "gender":"m"  ,"company_id": 10089,  "degree": "master"},
{"id": 1801302,  "name", "jimi"  ,"age":18,  "gender":"f"  ,"company_id": 10090,  "degree": "master"},
{"id": 1801303,  "name", "xiag"  ,"age":20,  "gender":"f"  ,"company_id": 10091,  "degree": "master"},
{"id": 1801304,  "name", "sdgs"  ,"age":22,  "gender":"m"  ,"company_id": 10092,  "degree": "master"},
{"id": 1801305,  "name", "lisi"  ,"age":22,  "gender":"m"  ,"company_id": 10093,  "degree": "doctor"},
{"id": 1801305,  "name", "segs"  ,"age":22,  "gender":"m"  ,"company_id": 10093,  "degree": "doctor"},
```

And then you can use this tool to convert a json file to a excel file~

