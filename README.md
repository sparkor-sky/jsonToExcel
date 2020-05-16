## jsonToExcel

This is a java maven project~, and this project contains a simple tool that can convert a json file that export from mysql or app log to excel~

##### dependency:
```
<dependencies>
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.7</version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>4.1.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>4.1.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.10</version>
    </dependency>
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.5</version>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>
</dependencies>
```

#### sample of sql file:
sql file is a file that export from mysql. You must delete all useless message and only contains values(……),(……)
for example:

```
(100080,'CNRS','CTR',10,1820143XXXX,1820143XXXX,3,'',1551692370841,1577164301033,'2019-12-24 05:12:05','2019-03-04 09:39:30','',2),
(100087,'CNRS','CTR',10,1820143XXXX,1820143XXXX,3,'',1551692370841,1577162785833,'2019-12-24 04:47:05','2019-03-04 09:39:30','',2),
(100143,'CTR',10,1820143XXXX,1820143XXXX,3,'',1551692370841,1570890616033,'2019-10-12 14:31:01','2019-03-04 09:39:30','',2),
(100210,'CNRS','CTR',10,1820143XXXX,1820143XXXX,3,'',1551692370841,1578049938054,'2020-01-03 11:13:06','2019-03-04 09:39:30','',2),
```

you can use this tool to convert a sql file to json file.

##### sample of json file:

json file must contains json objects only, you can think it is a json array with out '[', or ']' and each json object splits with line break '\n' without ',' 

for example:
```
{"id":"100080","name":"'lili'","customer":"'CTR'","quantity":"10","phone":"1820143XXXX","distributed_time":"'2019-12-24 05:12:05'","db_create_time":"1577164301033","db_update_time":"'2019-03-04 09:39:30'"}
{"id":"100087","name":"'jarvis'","customer":"'CTR'","quantity":"10","phone":"1820143XXXX","distributed_time":"'2019-12-24 04:47:05'","db_create_time":"1577162785833","db_update_time":"'2019-03-04 09:39:30'"}
{"id":"100143","name":"'luna'","customer":"'CTR'","quantity":"10","phone":"1820143XXXX","distributed_time":"'2019-10-12 14:31:01'","db_create_time":"1570890616033","db_update_time":"'2019-03-04 09:39:30'"}
{"id":"100210","name":"'jimi'","customer":"'CTR'","quantity":"10","phone":"1820143XXXX","distributed_time":"'2020-01-03 11:13:06'","db_create_time":"1578049938054","db_update_time":"'2019-03-04 09:39:30'"}
```

you can use this tool to convert a json file to a excel file~
